package com.management.interfaces;

import java.util.List;

import com.management.dto.RankscaleDTO;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public interface RankscaleManagerInterface extends ManagerInterface<RankscaleDTO> {

	public List<RankscaleDTO> getActive();
}
