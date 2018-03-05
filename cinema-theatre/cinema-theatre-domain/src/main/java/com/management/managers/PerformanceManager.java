package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;

import com.management.dto.PerformanceDTO;
import com.management.entities.Performance;
import com.management.interfaces.PerformanceManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class PerformanceManager implements PerformanceManagerInterface{
	
	private UnitOfWorkInterface uow;

	public PerformanceManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(PerformanceDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Performance Performance;

		try {
			Performance = mapper.map(dto, Performance.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getPerformanceRepository().Add(Performance);

		return true;
	}

	public PerformanceDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		PerformanceDTO dto;

		try {
			Performance Performance = uow.getPerformanceRepository().Read(id);
			dto = mapper.map(Performance, PerformanceDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<PerformanceDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Performance> listEntities = uow.getPerformanceRepository().ReadAll();
		ArrayList<PerformanceDTO> listDTO = new ArrayList<PerformanceDTO>();

		for (Performance tmp : listEntities) {
			try {
				PerformanceDTO dto = mapper.map(tmp, PerformanceDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(PerformanceDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		Performance tmp;

		try {
			tmp = mapper.map(dto, Performance.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getPerformanceRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getPerformanceRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
