package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.ActorPerformancesDTO;
import com.management.entities.Actorperformances;
import com.management.interfaces.ActorPerformancesManagerInterface;
import com.management.repositories.ActorPerformancesRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
public class ActorPerformancesManager implements ActorPerformancesManagerInterface{
	
	@Autowired
	private ActorPerformancesRepository actorPerformancesRepository;

	public boolean Create(ActorPerformancesDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Actorperformances actorPerformances;

		try {
			actorPerformances = mapper.map(dto, Actorperformances.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		actorPerformancesRepository.save(actorPerformances);

		return true;
	}

	public ActorPerformancesDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		ActorPerformancesDTO dto;

		try {
			Actorperformances actorPerformances = actorPerformancesRepository.findOne(id);
			dto = mapper.map(actorPerformances, ActorPerformancesDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<ActorPerformancesDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Actorperformances> listEntities = (ArrayList<Actorperformances>) actorPerformancesRepository.findAll();
		ArrayList<ActorPerformancesDTO> listDTO = new ArrayList<ActorPerformancesDTO>();

		for (Actorperformances tmp : listEntities) {
			try {
				ActorPerformancesDTO dto = mapper.map(tmp, ActorPerformancesDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(ActorPerformancesDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Actorperformances tmp;

		try {
			tmp = mapper.map(dto, Actorperformances.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		actorPerformancesRepository.save(tmp);
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			actorPerformancesRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
