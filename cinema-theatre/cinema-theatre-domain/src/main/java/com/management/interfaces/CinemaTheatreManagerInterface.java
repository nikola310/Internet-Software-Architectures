package com.management.interfaces;

import java.util.ArrayList;

import com.management.dto.CinemaTheatreBasicDTO;
import com.management.dto.CinemaTheatreDTO;
import com.management.dto.HallEventDTO;

/**
 * @author Zivko Stanisic
 *
 */
public interface CinemaTheatreManagerInterface extends ManagerInterface<CinemaTheatreDTO> {
	
	public ArrayList<CinemaTheatreDTO> ReadAllTheatres();
	
	public ArrayList<CinemaTheatreDTO> ReadAllCinemas();
	
	public ArrayList<CinemaTheatreBasicDTO> GetAllCinemaTheatreBasicInformation();
	
	public ArrayList<HallEventDTO> GetAllHallEvents(int id);
}
