/**
 * 
 */
package com.management.managers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.ReservationDTO;
import com.management.entities.Props;
import com.management.entities.Reservation;
import com.management.interfaces.ReservationManagerInterface;
import com.management.repositories.ReservationRepository;

/**
 * @author Nikola Stojanovic
 *
 */
@Service
@Transactional
public class ReservationManager implements ReservationManagerInterface {

	private ReservationRepository reservationRepository;
	
	@Autowired
	public ReservationManager(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Create(java.lang.Object)
	 */
	public boolean Create(ReservationDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Reservation rs;

		try {
			rs = mapper.map(dto, Reservation.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		reservationRepository.save(rs);

		return true;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Read(int)
	 */
	public ReservationDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		ReservationDTO dto;

		try {
			Reservation rs = reservationRepository.findOne(id);
			dto = mapper.map(rs, ReservationDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#ReadAll()
	 */
	public ArrayList<ReservationDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Reservation> listEntities = (ArrayList<Reservation>) reservationRepository.findAll();
		ArrayList<ReservationDTO> listDTO = new ArrayList<ReservationDTO>();

		for (Reservation tmp : listEntities) {
			try {
				ReservationDTO dto = mapper.map(tmp, ReservationDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Update(java.lang.Object)
	 */
	public boolean Update(ReservationDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Reservation tmp;

		try {
			tmp = mapper.map(dto, Reservation.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		reservationRepository.save(tmp);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.management.interfaces.ManagerInterface#Delete(int)
	 */
	public boolean Delete(int id) {
		try {
			reservationRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}

	public List<Reservation> getByProps(Props p) {
		return reservationRepository.findByProps(p);
	}

}
