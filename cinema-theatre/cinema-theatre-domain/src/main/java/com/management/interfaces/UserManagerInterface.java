/**
 * 
 */
package com.management.interfaces;

import com.management.dto.UserDTO;

/**
 * @author Zivko Stanisic
 *
 */
public interface UserManagerInterface extends ManagerInterface<UserDTO>{

	public void addRegistrationToken(String token);
}
