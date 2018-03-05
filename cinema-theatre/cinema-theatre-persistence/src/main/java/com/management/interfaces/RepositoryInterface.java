package com.management.interfaces;

import java.util.ArrayList;

/**
 * 
 * @author Zivko Stanisic
 *
 * @param <T>
 */
public interface RepositoryInterface<T> {
	
	public void Add(T entity);
	
	public T Read(int id);
	
	public ArrayList<T> ReadAll();
	
	public void Update();
	
	public void Delete(int id);
	
}
