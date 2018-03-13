package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.ActorDTO;
import com.management.entities.Actor;
import com.management.interfaces.ActorManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
@Service
public class ActorManager implements ActorManagerInterface{
	
	private UnitOfWorkInterface uow;

	@Autowired
	public ActorManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(ActorDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Actor Actor;

		try {
			Actor = mapper.map(dto, Actor.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getActorRepository().Add(Actor);

		return true;
	}

	public ActorDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		ActorDTO dto;

		try {
			Actor Actor = uow.getActorRepository().Read(id);
			dto = mapper.map(Actor, ActorDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<ActorDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Actor> listEntities = uow.getActorRepository().ReadAll();
		ArrayList<ActorDTO> listDTO = new ArrayList<ActorDTO>();

		for (Actor tmp : listEntities) {
			try {
				ActorDTO dto = mapper.map(tmp, ActorDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(ActorDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		Actor tmp;

		try {
			tmp = mapper.map(dto, Actor.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getActorRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getActorRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
