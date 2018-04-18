package com.management.interfaces;

import java.util.ArrayList;

import com.management.dto.CinemaTheatreDTO;

/**
 * @author Zivko Stanisic
 *
 */
public interface CinemaTheatreManagerInterface extends ManagerInterface<CinemaTheatreDTO> {
	
	public ArrayList<CinemaTheatreDTO> ReadAllTheatres();
	public ArrayList<CinemaTheatreDTO> ReadAllCinemas();
}
