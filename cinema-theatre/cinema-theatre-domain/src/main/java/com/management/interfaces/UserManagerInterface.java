package com.management.interfaces;

import java.util.ArrayList;

import com.management.dto.RegistrationDTO;
import com.management.dto.UserDTO;

/**
 * @author Zivko Stanisic
 *
 */
public interface UserManagerInterface {

	public boolean Create(RegistrationDTO dto, String token);

	public UserDTO Read(int id);

	public ArrayList<UserDTO> ReadAll();

	public boolean Update(UserDTO dto);

	public boolean Delete(int id);

	public boolean Confirmation(String token);
}
