package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import gr.hua.dit.distributedsystems.entities.User;

public interface UserDAO {

	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);
	
	public void delete(User user);
	
}
