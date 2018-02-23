package com.management.interfaces;

public interface BaseReposytoryInterface<T> {
	
	public void Add(T entity);
	
	public void Read(int id);
	
	public void Update(T entity);
	
	public void Delete(T entity);
	
}
