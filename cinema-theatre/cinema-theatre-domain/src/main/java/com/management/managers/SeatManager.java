package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dto.SeatDTO;
import com.management.entities.Seat;
import com.management.interfaces.SeatManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class SeatManager implements SeatManagerInterface{
	
	private UnitOfWorkInterface uow;

	@Autowired
	public SeatManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(SeatDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Seat Seat;

		try {
			Seat = mapper.map(dto, Seat.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getSeatRepository().Add(Seat);

		return true;
	}

	public SeatDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		SeatDTO dto;

		try {
			Seat Seat = uow.getSeatRepository().Read(id);
			dto = mapper.map(Seat, SeatDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<SeatDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Seat> listEntities = uow.getSeatRepository().ReadAll();
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
		@SuppressWarnings("unused")
		Seat tmp;

		try {
			tmp = mapper.map(dto, Seat.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getSeatRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getSeatRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
