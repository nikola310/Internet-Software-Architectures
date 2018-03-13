package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.HistoryDTO;
import com.management.entities.History;
import com.management.interfaces.HistoryManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Service
public class HistoryManager implements HistoryManagerInterface{
	
	private UnitOfWorkInterface uow;

	@Autowired
	public HistoryManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(HistoryDTO dto) {
		ModelMapper mapper = new ModelMapper();
		History History;

		try {
			History = mapper.map(dto, History.class);
			uow.getHistoryRepository().Add(History);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		

		return true;
	}

	public HistoryDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		HistoryDTO dto;

		try {
			History History = uow.getHistoryRepository().Read(id);
			dto = mapper.map(History, HistoryDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<HistoryDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<History> listEntities = uow.getHistoryRepository().ReadAll();
		ArrayList<HistoryDTO> listDTO = new ArrayList<HistoryDTO>();

		for (History tmp : listEntities) {
			try {
				HistoryDTO dto = mapper.map(tmp, HistoryDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(HistoryDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		History tmp;

		try {
			tmp = mapper.map(dto, History.class);
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
