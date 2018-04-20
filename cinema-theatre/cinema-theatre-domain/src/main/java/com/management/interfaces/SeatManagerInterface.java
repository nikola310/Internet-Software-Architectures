package com.management.interfaces;

import com.management.dto.SeatDTO;
import com.management.dto.SeatTakenDTO;

/**
 * @author Zivko Stanisic
 *
 */
public interface SeatManagerInterface extends ManagerInterface<SeatDTO> {

	public boolean TakeSeat(SeatTakenDTO dto);
	
	public boolean LeaveSeat(SeatTakenDTO dto);
}
