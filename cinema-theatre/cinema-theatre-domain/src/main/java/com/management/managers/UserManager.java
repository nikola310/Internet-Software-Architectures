package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dto.UserDTO;
import com.management.entities.User;
import com.management.interfaces.UnitOfWorkInterface;
import com.management.interfaces.UserManagerInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class UserManager implements UserManagerInterface {

	private UnitOfWorkInterface uow;

	@Autowired
	public UserManager(UnitOfWorkInterface uow) {
		this.uow = uow;
	}

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
	
	public ArrayList<UserDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<User> listEntities = uow.getUserRepository().ReadAll();
		ArrayList<UserDTO> listDTO = new ArrayList<UserDTO>();

		for (User tmp : listEntities) {
			try {
				UserDTO dto = mapper.map(tmp, UserDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		User tmp;

		try {
			tmp = mapper.map(dto, User.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getUserRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getUserRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}
}
