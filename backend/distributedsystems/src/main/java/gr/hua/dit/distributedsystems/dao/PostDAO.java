package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import gr.hua.dit.distributedsystems.entities.Post;

public interface PostDAO {

	public List<Post> findAll();
	
	public Post findById(int id);
	
	public void save(Post post);
	
	public void delete(Post post);
	
}
