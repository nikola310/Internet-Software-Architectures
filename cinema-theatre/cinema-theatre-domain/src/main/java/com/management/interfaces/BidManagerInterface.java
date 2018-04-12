/**
 * 
 */
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
}
