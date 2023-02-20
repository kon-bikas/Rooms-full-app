package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import gr.hua.dit.distributedsystems.entities.Request;

public interface RequestDAO {

	public List<Request> findAll();
	
	public Request findById(int id);
	
	public void save(Request request);
	
	public void delete(int id);
	
}
