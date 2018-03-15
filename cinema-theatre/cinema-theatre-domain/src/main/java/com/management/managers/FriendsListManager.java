package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.FriendslistDTO;
import com.management.entities.Friendslist;
import com.management.interfaces.FriendsListManagerInterface;
import com.management.repositories.FriendsListRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class FriendsListManager implements FriendsListManagerInterface{
	
	private FriendsListRepository friendsListRepository;

	@Autowired
	public FriendsListManager(FriendsListRepository friendsListRepository) {
		this.friendsListRepository = friendsListRepository;
	}
	
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

}
