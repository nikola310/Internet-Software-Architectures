package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.SeatDTO;
import com.management.entities.Seat;
import com.management.interfaces.SeatManagerInterface;
import com.management.repositories.SeatRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class SeatManager implements SeatManagerInterface{
	
	private SeatRepository seatRepository;

	@Autowired
	public SeatManager(SeatRepository seatRepository) {
		this.seatRepository = seatRepository;
	}

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
}
