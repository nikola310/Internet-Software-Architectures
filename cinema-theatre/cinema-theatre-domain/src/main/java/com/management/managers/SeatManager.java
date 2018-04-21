package com.management.managers;

import java.util.ArrayList;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.SeatDTO;
import com.management.dto.SeatTakenDTO;
import com.management.entities.Seat;
import com.management.entities.User;
import com.management.interfaces.SeatManagerInterface;
import com.management.repositories.SeatRepository;
import com.management.repositories.UserRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class SeatManager implements SeatManagerInterface {

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private UserRepository userRepository;

	public boolean Create(SeatDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Seat seat;

		try {
			seat = mapper.map(dto, Seat.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		seatRepository.save(seat);

		return true;
	}

	public SeatDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		SeatDTO dto;

		try {
			Seat seat = seatRepository.findOne(id);
			dto = mapper.map(seat, SeatDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<SeatDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Seat> listEntities = (ArrayList<Seat>) seatRepository.findAll();
		ArrayList<SeatDTO> listDTO = new ArrayList<SeatDTO>();

		for (Seat tmp : listEntities) {
			try {
				SeatDTO dto = mapper.map(tmp, SeatDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(SeatDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Seat tmp;

		try {
			tmp = mapper.map(dto, Seat.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		seatRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			seatRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean TakeSeat(SeatTakenDTO dto) {
		try {
			Seat seat = seatRepository.findOne(dto.getId());
			Date now = new Date();

			if (seat.getSeatModified().before(now)) {
				if (!seat.isSeatTaken()) {
					seat.setSeatModified(now);
					seat.setUser(userRepository.findUserByUserEmail(dto.getEmail()));
					seat.setSeatTaken(true);
					seatRepository.save(seat);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public boolean LeaveSeat(SeatTakenDTO dto) {
		try {
			Seat seat = seatRepository.findOne(dto.getId());
			User user = userRepository.findUserByUserEmail(dto.getEmail());

			if (user == seat.getUser()) {
				if (seat.isSeatTaken()) {
					seat.setUser(null);
					seat.setSeatTaken(false);
					seatRepository.save(seat);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public SeatRepository getSeatRepository() {
		return seatRepository;
	}

	public void setSeatRepository(SeatRepository seatRepository) {
		this.seatRepository = seatRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
}
