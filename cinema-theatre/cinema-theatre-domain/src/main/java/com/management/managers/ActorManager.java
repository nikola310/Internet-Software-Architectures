package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.ActorDTO;
import com.management.entities.Actor;
import com.management.interfaces.ActorManagerInterface;
import com.management.repositories.ActorRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class ActorManager implements ActorManagerInterface{
	
	
	private ActorRepository actorRepository;
	
	/**
	 * 
	 */
	@Autowired
	public ActorManager(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
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
		actorRepository.save(Actor);

		return true;
	}

	public ActorDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		ActorDTO dto;

		try {
			Actor Actor = actorRepository.findOne(id);
			dto = mapper.map(Actor, ActorDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<ActorDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Actor> listEntities = (ArrayList<Actor>) actorRepository.findAll();
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
		Actor tmp;

		try {
			tmp = mapper.map(dto, Actor.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		actorRepository.save(tmp);
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			actorRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
