package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.ActorPerformancesDTO;
import com.management.entities.Actorperformances;
import com.management.interfaces.ActorPerformancesManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Service
public class ActorPerformancesManager implements ActorPerformancesManagerInterface{
	
	private UnitOfWorkInterface uow;

	@Autowired
	public ActorPerformancesManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(ActorPerformancesDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Actorperformances ActorPerformances;

		try {
			ActorPerformances = mapper.map(dto, Actorperformances.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getActorPerformancesRepository().Add(ActorPerformances);

		return true;
	}

	public ActorPerformancesDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		ActorPerformancesDTO dto;

		try {
			Actorperformances ActorPerformances = uow.getActorPerformancesRepository().Read(id);
			dto = mapper.map(ActorPerformances, ActorPerformancesDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<ActorPerformancesDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Actorperformances> listEntities = uow.getActorPerformancesRepository().ReadAll();
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
		@SuppressWarnings("unused")
		Actorperformances tmp;

		try {
			tmp = mapper.map(dto, Actorperformances.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getActorPerformancesRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getActorPerformancesRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
