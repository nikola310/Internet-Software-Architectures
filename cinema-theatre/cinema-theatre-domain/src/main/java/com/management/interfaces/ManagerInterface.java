package com.management.interfaces;

import java.util.ArrayList;

/**
 * 
 * @author Zivko Stanisic
 *
 * @param <T>
 */
public interface ManagerInterface<T> {
	
	public boolean Create(T dto);
	
	public T Read(int id);
	
	public ArrayList<T> ReadAll();
	
	public boolean Update(T dto);
	
	public boolean Delete(int id);
}
