package gr.hua.dit.distributedsystems.service;

import gr.hua.dit.distributedsystems.entities.Post;
import gr.hua.dit.distributedsystems.entities.User;

public interface PostService {

	public Post getPost(int pid, User user);
	
}
