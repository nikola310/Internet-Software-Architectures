package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.FriendDTO;
import com.management.dto.FriendslistDTO;
import com.management.dto.ProfileDTO;
import com.management.dto.UserBasicDTO;
import com.management.entities.Friendslist;
import com.management.entities.User;
import com.management.interfaces.FriendsListManagerInterface;
import com.management.repositories.FriendsListRepository;
import com.management.repositories.UserRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class FriendsListManager implements FriendsListManagerInterface {

	@Autowired
	private FriendsListRepository friendsListRepository;

	@Autowired
	private UserRepository userRepository;

	public boolean Create(FriendslistDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Friendslist list;

		try {
			list = mapper.map(dto, Friendslist.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		friendsListRepository.save(list);

		return true;
	}

	public FriendslistDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		FriendslistDTO dto;

		try {
			Friendslist list = friendsListRepository.findOne(id);
			dto = mapper.map(list, FriendslistDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<FriendslistDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Friendslist> listEntities = (ArrayList<Friendslist>) friendsListRepository.findAll();
		ArrayList<FriendslistDTO> listDTO = new ArrayList<FriendslistDTO>();

		for (Friendslist tmp : listEntities) {
			try {
				FriendslistDTO dto = mapper.map(tmp, FriendslistDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(FriendslistDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Friendslist tmp;

		try {
			tmp = mapper.map(dto, Friendslist.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		friendsListRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			friendsListRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<UserBasicDTO> GetUserBasicInformation(String email) {
		ArrayList<UserBasicDTO> retVal = new ArrayList<UserBasicDTO>();
		
		try {
			ArrayList<User> listEntities = (ArrayList<User>) userRepository.findAll();
			for (User user : listEntities) {
				if (!email.equals(user.getUserEmail())) {
					UserBasicDTO dto = new UserBasicDTO();
					dto.setId(user.getUserId());
					dto.setName(user.getUserName());
					dto.setSurname(user.getUserSurname());

					retVal.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return retVal;
	}

	public ArrayList<FriendDTO> GetFriendsBasicInformation(String email) {
		ArrayList<FriendDTO> retVal = new ArrayList<FriendDTO>();

		try {
			User user = userRepository.findUserByUserEmail(email);
			ArrayList<Friendslist> frindslist = (ArrayList<Friendslist>) friendsListRepository.findAll();

			for (Friendslist tmp : frindslist) {
				if (tmp.getUserByUserId() == user) {
					if (tmp.getFriendsStatus() == 'A') {
						User friend = tmp.getUserByUseUserId();
						FriendDTO dto = new FriendDTO();
						dto.setSurname(friend.getUserSurname());
						dto.setName(friend.getUserName());
						dto.setId(tmp.getFriendsId());
						dto.setStatus("A");
						retVal.add(dto);
					}

				} else if (tmp.getUserByUseUserId() == user) {
					if (tmp.getFriendsStatus() == 'A') {

						User friend = tmp.getUserByUserId();
						FriendDTO dto = new FriendDTO();
						dto.setSurname(friend.getUserSurname());
						dto.setName(friend.getUserName());
						dto.setId(tmp.getFriendsId());
						dto.setStatus("A");
						retVal.add(dto);

					} else if (tmp.getFriendsStatus() == 'P') {

						User friend = tmp.getUserByUserId();
						FriendDTO dto = new FriendDTO();
						dto.setSurname(friend.getUserSurname());
						dto.setName(friend.getUserName());
						dto.setId(tmp.getFriendsId());
						dto.setStatus("P");
						retVal.add(dto);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return retVal;
	}

	public boolean IsFriend(String email, int id) {
		try {
			User user = userRepository.findUserByUserEmail(email);
			User friend = userRepository.findOne(id);

			ArrayList<Friendslist> frindslist = (ArrayList<Friendslist>) friendsListRepository.findAll();

			for (Friendslist tmp : frindslist) {
				if (tmp.getUserByUserId() == user) {
					if (tmp.getUserByUseUserId() == friend) {
						if (tmp.getFriendsStatus() == 'A' || tmp.getFriendsStatus() == 'P') {
							return true;
						}
					}
				} else if (tmp.getUserByUseUserId() == user) {
					if (tmp.getUserByUserId() == friend) {
						if (tmp.getFriendsStatus() == 'A' || tmp.getFriendsStatus() == 'P') {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean AddFriend(String email, int id) {
		try {
			User user = userRepository.findUserByUserEmail(email);
			User friend = userRepository.findOne(id);

			Friendslist list = new Friendslist();
			list.setUserByUserId(user);
			list.setUserByUseUserId(friend);
			list.setUserByUseUserId2(user);
			list.setFriendsStatus('P');

			friendsListRepository.save(list);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ProfileDTO GEtFriend(int id) {
		ProfileDTO dto = new ProfileDTO();
		try {
			ModelMapper mapper = new ModelMapper();
			User user = userRepository.findOne(id);
			dto = mapper.map(user, ProfileDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dto;
	}

	public boolean Accept(int id) {
		try {
			Friendslist list = friendsListRepository.findOne(id);
			list.setFriendsStatus('A');
			friendsListRepository.save(list);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean Refuse(int id) {
		try {
			Friendslist list = friendsListRepository.findOne(id);
			list.setFriendsStatus('R');
			friendsListRepository.save(list);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public FriendsListRepository getFriendsListRepository() {
		return friendsListRepository;
	}

	public void setFriendsListRepository(FriendsListRepository friendsListRepository) {
		this.friendsListRepository = friendsListRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	

}
