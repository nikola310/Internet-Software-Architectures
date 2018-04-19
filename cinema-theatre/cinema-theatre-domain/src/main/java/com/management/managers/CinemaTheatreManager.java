package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.CinemaTheatreBasicDTO;
import com.management.dto.CinemaTheatreDTO;
import com.management.dto.HallEventDTO;
import com.management.entities.CinemaTheatre;
import com.management.entities.Event;
import com.management.entities.Hall;
import com.management.entities.Performance;
import com.management.interfaces.CinemaTheatreManagerInterface;
import com.management.repositories.CinemaTheatreRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class CinemaTheatreManager implements CinemaTheatreManagerInterface {

	private CinemaTheatreRepository cinemaTheatreRepository;

	@Autowired
	public CinemaTheatreManager(CinemaTheatreRepository cinemaTheatreRepository) {
		this.cinemaTheatreRepository = cinemaTheatreRepository;
	}

	public boolean Create(CinemaTheatreDTO dto) {
		ModelMapper mapper = new ModelMapper();
		CinemaTheatre cinemaTheatre;

		try {
			cinemaTheatre = mapper.map(dto, CinemaTheatre.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		cinemaTheatreRepository.save(cinemaTheatre);

		return true;
	}

	public CinemaTheatreDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		CinemaTheatreDTO dto;

		try {
			CinemaTheatre cinemaTheatre = cinemaTheatreRepository.findOne(id);
			dto = mapper.map(cinemaTheatre, CinemaTheatreDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<CinemaTheatreDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<CinemaTheatre> listEntities = (ArrayList<CinemaTheatre>) cinemaTheatreRepository.findAll();
		ArrayList<CinemaTheatreDTO> listDTO = new ArrayList<CinemaTheatreDTO>();

		for (CinemaTheatre tmp : listEntities) {
			try {
				CinemaTheatreDTO dto = mapper.map(tmp, CinemaTheatreDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public ArrayList<CinemaTheatreDTO> ReadAllTheatres() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<CinemaTheatre> listEntities = (ArrayList<CinemaTheatre>) cinemaTheatreRepository.findAll();
		ArrayList<CinemaTheatreDTO> listDTO = new ArrayList<CinemaTheatreDTO>();

		for (CinemaTheatre tmp : listEntities) {
			try {
				CinemaTheatreDTO dto = mapper.map(tmp, CinemaTheatreDTO.class);
				if (dto.getCtType() == 'T')
					listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public ArrayList<CinemaTheatreDTO> ReadAllCinemas() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<CinemaTheatre> listEntities = (ArrayList<CinemaTheatre>) cinemaTheatreRepository.findAll();
		ArrayList<CinemaTheatreDTO> listDTO = new ArrayList<CinemaTheatreDTO>();

		for (CinemaTheatre tmp : listEntities) {
			try {
				CinemaTheatreDTO dto = mapper.map(tmp, CinemaTheatreDTO.class);
				if (dto.getCtType() == 'C')
					listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(CinemaTheatreDTO dto) {
		ModelMapper mapper = new ModelMapper();
		CinemaTheatre tmp;

		try {
			tmp = mapper.map(dto, CinemaTheatre.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		cinemaTheatreRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			cinemaTheatreRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<CinemaTheatreBasicDTO> GetAllCinemaTheatreBasicInformation() {

		ArrayList<CinemaTheatre> listEntities = (ArrayList<CinemaTheatre>) cinemaTheatreRepository.findAll();
		ArrayList<CinemaTheatreBasicDTO> listDTO = new ArrayList<CinemaTheatreBasicDTO>();

		for (CinemaTheatre tmp : listEntities) {

			CinemaTheatreBasicDTO dto = new CinemaTheatreBasicDTO();
			dto.setId(tmp.getCtId());
			dto.setName(tmp.getCtName());
			listDTO.add(dto);

			System.out.println(dto.getName());

		}

		return listDTO;
	}

	public ArrayList<HallEventDTO> GetAllHallEvents(int id) {
		ArrayList<HallEventDTO> retVal = new ArrayList<HallEventDTO>();
		try {
			CinemaTheatre entity = cinemaTheatreRepository.findOne(id);
			
			for (Hall hall : entity.getHalls()) {
				for (Event event : hall.getEvents()) {
					Performance per = event.getPerformance();
					HallEventDTO dto = new HallEventDTO();
					dto.setId(hall.getHallId());
					dto.setHall(hall.getHallName());
					dto.setPerformance(per.getPerDescription());
					dto.setDate(event.getEventDate().toString());
					retVal.add(dto);
					
					System.err.println(dto.getHall());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return retVal;
	}
}
