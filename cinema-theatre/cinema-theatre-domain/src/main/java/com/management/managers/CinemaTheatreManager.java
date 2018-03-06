package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dto.CinemaTheatreDTO;
import com.management.entities.CinemaTheatre;
import com.management.interfaces.CinemaTheatreManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class CinemaTheatreManager implements CinemaTheatreManagerInterface {

	private UnitOfWorkInterface uow;

	@Autowired
	public CinemaTheatreManager(UnitOfWorkInterface uow) {
		this.uow = uow;
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
		uow.getCinemaTheatreRepository().Add(cinemaTheatre);

		return true;
	}

	public CinemaTheatreDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		CinemaTheatreDTO dto;

		try {
			CinemaTheatre cinemaTheatre = uow.getCinemaTheatreRepository().Read(id);
			dto = mapper.map(cinemaTheatre, CinemaTheatreDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}
	
	public ArrayList<CinemaTheatreDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<CinemaTheatre> listEntities = uow.getCinemaTheatreRepository().ReadAll();
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
		@SuppressWarnings("unused")
		CinemaTheatre tmp;

		try {
			tmp = mapper.map(dto, CinemaTheatre.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getCinemaTheatreRepository().Update();
		uow.commitChanges();

		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getCinemaTheatreRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}
}
