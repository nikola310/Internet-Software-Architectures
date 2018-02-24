package com.management.managers;

import org.modelmapper.ModelMapper;

import com.management.context.UnitOfWork;
import com.management.dto.UserDTO;
import com.management.entities.User;
import com.management.interfaces.UserManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class UserManager implements UserManagerInterface{
	private UnitOfWork uow = new UnitOfWork();
	
	
	public boolean Create(UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user;
		
		try {
			user = mapper.map(dto, User.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}		
		uow.getUserRepository().Add(user);
		
		return true;
	}

	public UserDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		UserDTO dto;
		
		try {
			User user = uow.getUserRepository().Read(id);
			dto = mapper.map(user, UserDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}		
		
		return dto;
	}

	public boolean Update(UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user;
		
		try {
			user = mapper.map(dto, User.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}		
		uow.getUserRepository().Update(user);
		
		return true;
	}

	public boolean Delete(UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user;
		
		try {
			user = mapper.map(dto, User.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}		
		uow.getUserRepository().Delete(user);
		
		return true;
	}

}
