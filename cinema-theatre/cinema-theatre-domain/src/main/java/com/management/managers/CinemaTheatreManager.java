package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.CinemaTheatreDTO;
import com.management.entities.CinemaTheatre;
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
}
