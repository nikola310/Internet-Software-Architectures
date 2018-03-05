package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;

import com.management.dto.EventDTO;
import com.management.entities.Event;
import com.management.interfaces.EventManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class EventManager implements EventManagerInterface{
	
	private UnitOfWorkInterface uow;

	public EventManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

	public boolean Create(EventDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Event Event;

		try {
			Event = mapper.map(dto, Event.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getEventRepository().Add(Event);

		return true;
	}

	public EventDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		EventDTO dto;

		try {
			Event Event = uow.getEventRepository().Read(id);
			dto = mapper.map(Event, EventDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<EventDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Event> listEntities = uow.getEventRepository().ReadAll();
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
		@SuppressWarnings("unused")
		Event tmp;

		try {
			tmp = mapper.map(dto, Event.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getEventRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getEventRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
