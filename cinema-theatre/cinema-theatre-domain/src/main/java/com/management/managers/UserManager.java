package com.management.managers;

import java.util.ArrayList;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.LoginDTO;
import com.management.dto.ProfileDTO;
import com.management.dto.RegistrationDTO;
import com.management.dto.UserDTO;
import com.management.entities.User;
import com.management.interfaces.UserManagerInterface;
import com.management.repositories.UserRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class UserManager implements UserManagerInterface {

	private UserRepository userRepository;

	@Autowired
	public UserManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean Create(RegistrationDTO dto, String token) {
		ModelMapper mapper = new ModelMapper();
		User user;
		try {
			user = mapper.map(dto, User.class);
			if (token == null) {
				return false;
			}
			user.setUserPhone(new Integer(dto.getUserPhone()));
			Date expiration = new Date();
			expiration.setTime(expiration.getTime() + (24 * 60 * 60 * 1000));

			user.setUserToken(token);
			user.setUserExpiration(expiration);
			user.setUserCreationDate(new Date());
			user.setUserRank(0);
			user.setUserAdmin('N');

			userRepository.save(user);

			return true;

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	public UserDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		UserDTO dto;

		try {
			User user = userRepository.findOne(id);
			dto = mapper.map(user, UserDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<UserDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<User> listEntities = (ArrayList<User>) userRepository.findAll();
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
		User tmp;

		try {
			tmp = mapper.map(dto, User.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		userRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			userRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean Confirmation(String token) {
		if (token == null) {
			return false;
		}

		try {
			User user = userRepository.findUserByUserToken(token);
			String userToken = user.getUserToken();

			if (token.equals(userToken)) {
				Date today = new Date();

				if (today.after(user.getUserExpiration())) {
					userRepository.delete(user);
					return false;
				}

				user.setUserActive(true);
				userRepository.save(user);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean Login(LoginDTO dto) {
		try {
			User user = userRepository.findUserByUserEmail(dto.getEmail());
			if (!dto.getPassword().equals(user.getUserPassword())) {
				return false;
			}
			
			if (!user.isUserActive()) {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ProfileDTO ReadProfile(LoginDTO dto) {
		if (!Login(dto)) {
			return null;
		}
		ProfileDTO profile = new ProfileDTO();
		try {
			ModelMapper mapper = new ModelMapper();
			User user = userRepository.findUserByUserEmail(dto.getEmail());
			profile = mapper.map(user, ProfileDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return profile;
	}

}
