package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dto.TicketDTO;
import com.management.entities.Ticket;
import com.management.interfaces.TicketManagerInterface;
import com.management.repositories.TicketRepository;

public class TicketManager implements TicketManagerInterface {

	private TicketRepository ticketRepository;
	
	@Autowired
	public TicketManager(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public boolean Create(TicketDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Ticket ticket;

		try {
			ticket = mapper.map(dto, Ticket.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		ticketRepository.save(ticket);

		return true;
	}
	public TicketDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		TicketDTO dto;

		try {
			Ticket ticket = ticketRepository.findOne(id);
			dto = mapper.map(ticket, TicketDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<TicketDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Ticket> listEntities = (ArrayList<Ticket>) ticketRepository.findAll();
		ArrayList<TicketDTO> listDTO = new ArrayList<TicketDTO>();

		for (Ticket tmp : listEntities) {
			try {
				TicketDTO dto = mapper.map(tmp, TicketDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(TicketDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Ticket tmp;

		try {
			tmp = mapper.map(dto, Ticket.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		ticketRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			ticketRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}
}
