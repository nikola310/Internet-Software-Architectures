package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.HistoryDTO;
import com.management.entities.History;
import com.management.interfaces.HistoryManagerInterface;
import com.management.repositories.HistoryRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class HistoryManager implements HistoryManagerInterface{
	
	private HistoryRepository historyRepository;

	@Autowired
	public HistoryManager(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}

	public boolean Create(HistoryDTO dto) {
		ModelMapper mapper = new ModelMapper();
		History history;

		try {
			history = mapper.map(dto, History.class);
			historyRepository.save(history);
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
			History history = historyRepository.findOne(id);
			dto = mapper.map(history, HistoryDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<HistoryDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<History> listEntities = (ArrayList<History>) historyRepository.findAll();
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
		History tmp;

		try {
			tmp = mapper.map(dto, History.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		historyRepository.save(tmp);
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			historyRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
