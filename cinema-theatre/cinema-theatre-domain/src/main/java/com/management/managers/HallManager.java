package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.HallDTO;
import com.management.entities.Hall;
import com.management.interfaces.HallManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Service
public class HallManager implements HallManagerInterface{
	
	private UnitOfWorkInterface uow;

	@Autowired
	public HallManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(HallDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Hall Hall;

		try {
			Hall = mapper.map(dto, Hall.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getHallRepository().Add(Hall);

		return true;
	}

	public HallDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		HallDTO dto;

		try {
			Hall Hall = uow.getHallRepository().Read(id);
			dto = mapper.map(Hall, HallDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<HallDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Hall> listEntities = uow.getHallRepository().ReadAll();
		ArrayList<HallDTO> listDTO = new ArrayList<HallDTO>();

		for (Hall tmp : listEntities) {
			try {
				HallDTO dto = mapper.map(tmp, HallDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(HallDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		Hall tmp;

		try {
			tmp = mapper.map(dto, Hall.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getHallRepository().Update();
		uow.commitChanges();

		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getHallRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}
}
