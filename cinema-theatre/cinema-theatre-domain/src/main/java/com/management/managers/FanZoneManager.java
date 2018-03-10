package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.context.UnitOfWork;
import com.management.dto.FanZoneDTO;
import com.management.entities.FanZone;
import com.management.interfaces.FanZoneManagerInterface;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public class FanZoneManager implements FanZoneManagerInterface {

	private UnitOfWork uow;

	@Autowired
	public FanZoneManager(UnitOfWork uow) {
		this.uow = uow;
	}

	public boolean Create(FanZoneDTO dto) {
		ModelMapper mapper = new ModelMapper();
		FanZone fanzone;

		try {
			fanzone = mapper.map(dto, FanZone.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		uow.getFanZoneRepository().Add(fanzone);

		return true;
	}

	public FanZoneDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		FanZoneDTO dto;

		try {
			FanZone fanzone = uow.getFanZoneRepository().Read(id);
			dto = mapper.map(fanzone, FanZoneDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<FanZoneDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<FanZone> listEntities = uow.getFanZoneRepository().ReadAll();
		ArrayList<FanZoneDTO> listDTO = new ArrayList<FanZoneDTO>();

		for (FanZone tmp : listEntities) {
			try {
				FanZoneDTO dto = mapper.map(tmp, FanZoneDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(FanZoneDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		FanZone tmp;

		try {
			tmp = mapper.map(dto, FanZone.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getHistoryRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getHistoryRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}

}
