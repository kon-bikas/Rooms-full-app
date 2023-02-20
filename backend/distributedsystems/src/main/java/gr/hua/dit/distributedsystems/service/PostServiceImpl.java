package gr.hua.dit.distributedsystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import gr.hua.dit.distributedsystems.dao.PostDAO;
import gr.hua.dit.distributedsystems.entities.Post;
import gr.hua.dit.distributedsystems.entities.User;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Post getPost(int pid, User user) {
		
		Post post = postDAO.findById(pid);
		
		if(post == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "post not found"
			);
		}
		
		if (userService.isUserMemberOrAdminOf(post.getRoom(), user)) {
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Sorry, you are not associated with this room"
			);
		}
		
		return postDAO.findById(pid);
		
		
	}

	
	
}
