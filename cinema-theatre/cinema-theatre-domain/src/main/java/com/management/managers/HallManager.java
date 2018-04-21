package com.management.managers;

import java.util.ArrayList;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.HallDTO;
import com.management.entities.CinemaTheatre;
import com.management.entities.Hall;
import com.management.entities.Seat;
import com.management.interfaces.HallManagerInterface;
import com.management.repositories.CinemaTheatreRepository;
import com.management.repositories.HallRepository;
import com.management.repositories.SeatRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class HallManager implements HallManagerInterface {

	@Autowired
	private HallRepository hallRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private CinemaTheatreRepository cinemaTheatreRepository;

	public boolean Create(HallDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Hall hall;

		try {
			hall = mapper.map(dto, Hall.class);

			CinemaTheatre ct = cinemaTheatreRepository.findOne(dto.getCtId());
			hall.setCinemaTheatre(ct);
			hallRepository.save(hall);

			for (int i = 0; i < dto.getLen(); i++) {
				Seat seat = new Seat();
				seat.setSeatModified(new Date());
				seat.setSeatTaken(false);
				seat.setHall(hall);
				seatRepository.save(seat);
				hall.getSeats().add(seat);

			}
			hallRepository.save(hall);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		hallRepository.save(hall);

		return true;
	}

	public HallDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		HallDTO dto;

		try {
			Hall hall = hallRepository.findOne(id);
			dto = mapper.map(hall, HallDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<HallDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Hall> listEntities = (ArrayList<Hall>) hallRepository.findAll();
		ArrayList<HallDTO> listDTO = new ArrayList<HallDTO>();

		for (Hall tmp : listEntities) {
			try {
				HallDTO dto = mapper.map(tmp, HallDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(HallDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Hall tmp;

		try {
			tmp = mapper.map(dto, Hall.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		hallRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			hallRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

	public HallRepository getHallRepository() {
		return hallRepository;
	}

	public void setHallRepository(HallRepository hallRepository) {
		this.hallRepository = hallRepository;
	}

	public SeatRepository getSeatRepository() {
		return seatRepository;
	}

	public void setSeatRepository(SeatRepository seatRepository) {
		this.seatRepository = seatRepository;
	}

	public CinemaTheatreRepository getCinemaTheatreRepository() {
		return cinemaTheatreRepository;
	}

	public void setCinemaTheatreRepository(
			CinemaTheatreRepository cinemaTheatreRepository) {
		this.cinemaTheatreRepository = cinemaTheatreRepository;
	}
	
	
}
