package com.management.interfaces;
/**
 * 
 * @author Zivko Stanisic
 *
 * @param <T>
 */
public interface ManagerInterface<T> {
	
	public boolean Create(T dto);
	
	public T Read(int id);
	
	public boolean Update(T dto);
	
	public boolean Delete(int id);
}
