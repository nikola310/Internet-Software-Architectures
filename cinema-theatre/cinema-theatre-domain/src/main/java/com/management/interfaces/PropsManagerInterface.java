package com.management.interfaces;

import java.util.List;

import com.management.dto.PropsDTO;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
public interface PropsManagerInterface extends ManagerInterface<PropsDTO> {

	public List<PropsDTO> getNullAllowedProps();
}
