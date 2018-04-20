package com.management.interfaces;

import java.util.List;

import com.management.dto.BidDTO;
import com.management.entities.User;

/**
 * @author Nikola Stojanovic
 *
 */
public interface BidManagerInterface extends ManagerInterface<BidDTO> {

	public List<BidDTO> getBids(User user);
	
	public List<BidDTO> getNotAccepted(User user);
	
	public List<BidDTO> readBidsByProps(int id);
	
	public boolean acceptBid(BidDTO dto);
	
	public boolean rejectBid(BidDTO dto);
}
