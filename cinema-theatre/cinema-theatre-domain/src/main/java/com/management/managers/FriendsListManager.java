package com.management.managers;

import org.modelmapper.ModelMapper;

import com.management.dto.FriendslistDTO;
import com.management.entities.Friendslist;
import com.management.interfaces.FriendsListManagerInterface;
import com.management.interfaces.UnitOfWorkInterface;

/**
 * @author Zivko Stanisic
 *
 */
public class FriendsListManager implements FriendsListManagerInterface{
	
	private UnitOfWorkInterface uow;

	public FriendsListManager(UnitOfWorkInterface uow) {
		this.uow = uow;
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
		uow.getFriendsListRepository().Add(list);

		return true;
	}

	public FriendslistDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		FriendslistDTO dto;

		try {
			Friendslist list = uow.getFriendsListRepository().Read(id);
			dto = mapper.map(list, FriendslistDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public boolean Update(FriendslistDTO dto) {
		ModelMapper mapper = new ModelMapper();
		@SuppressWarnings("unused")
		Friendslist tmp;

		try {
			tmp = mapper.map(dto, Friendslist.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		uow.getFriendsListRepository().Update();
		uow.commitChanges();
		
		return true;
	}

	public boolean Delete(int id) {
		try {
			uow.getFriendsListRepository().Delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		
		return true;
	}

}
