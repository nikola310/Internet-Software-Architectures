package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.FanZoneDTO;
import com.management.entities.FanZone;
import com.management.interfaces.FanZoneManagerInterface;
import com.management.repositories.FanZoneRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@Service
public class FanZoneManager implements FanZoneManagerInterface {

	@Autowired
	private FanZoneRepository fanZoneRepository;

	public boolean Create(FanZoneDTO dto) {
		ModelMapper mapper = new ModelMapper();
		FanZone fanzone = new FanZone();

		try {
			fanzone = mapper.map(dto, FanZone.class);
			fanZoneRepository.save(fanzone);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public FanZoneDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		FanZoneDTO dto;

		try {
			FanZone fanzone = fanZoneRepository.findOne(id);
			dto = mapper.map(fanzone, FanZoneDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<FanZoneDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<FanZone> listEntities = (ArrayList<FanZone>) fanZoneRepository.findAll();
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
		FanZone tmp;

		try {
			tmp = mapper.map(dto, FanZone.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		fanZoneRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			fanZoneRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

}
