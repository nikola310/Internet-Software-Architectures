package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.EventDTO;
import com.management.entities.Event;
import com.management.interfaces.EventManagerInterface;
import com.management.repositories.EventRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class EventManager implements EventManagerInterface{
	
	private EventRepository eventRepository;

	@Autowired
	public EventManager(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public boolean Create(EventDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Event event;

		try {
			event = mapper.map(dto, Event.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		eventRepository.save(event);

		return true;
	}

	public EventDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		EventDTO dto;

		try {
			Event event = eventRepository.findOne(id);
			dto = mapper.map(event, EventDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<EventDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Event> listEntities = (ArrayList<Event>) eventRepository.findAll();
		ArrayList<EventDTO> listDTO = new ArrayList<EventDTO>();

		for (Event tmp : listEntities) {
			try {
				EventDTO dto = mapper.map(tmp, EventDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(EventDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Event tmp;

		try {
			tmp = mapper.map(dto, Event.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		eventRepository.save(tmp);
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			eventRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
