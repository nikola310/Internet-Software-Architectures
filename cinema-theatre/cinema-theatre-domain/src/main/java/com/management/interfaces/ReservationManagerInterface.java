package com.management.interfaces;

import java.util.List;

import com.management.dto.ReservationDTO;
import com.management.entities.Props;
import com.management.entities.Reservation;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public interface ReservationManagerInterface extends ManagerInterface<ReservationDTO> {

	public List<Reservation> getByProps(Props p);
}
