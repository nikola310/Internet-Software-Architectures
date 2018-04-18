package com.management.interfaces;

import java.util.ArrayList;

import com.management.dto.FriendDTO;
import com.management.dto.FriendslistDTO;
import com.management.dto.ProfileDTO;
import com.management.dto.UserBasicDTO;

/**
 * @author Zivko Stanisic
 *
 */
public interface FriendsListManagerInterface extends ManagerInterface<FriendslistDTO> {

	public ArrayList<UserBasicDTO> GetUserBasicInformation(String email);

	public ArrayList<FriendDTO> GetFriendsBasicInformation(String email);

	public boolean IsFriend(String email, int id);

	public boolean AddFriend(String email, int id);

	public ProfileDTO GEtFriend(int id);
	
	public boolean Accept(int id);
	
	public boolean Refuse(int id);
}
