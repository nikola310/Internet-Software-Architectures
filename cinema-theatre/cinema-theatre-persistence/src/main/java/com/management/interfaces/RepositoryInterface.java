package com.management.interfaces;

/**
 * 
 * @author Zivko Stanisic
 *
 * @param <T>
 */
public interface RepositoryInterface<T> {
	
	public void Add(T entity);
	
	public T Read(int id);
	
	public void Update(T entity);
	
	public void Delete(int id);
	
}
