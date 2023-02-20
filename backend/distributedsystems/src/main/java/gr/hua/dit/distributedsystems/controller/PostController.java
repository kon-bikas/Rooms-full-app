package gr.hua.dit.distributedsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gr.hua.dit.distributedsystems.dao.PostDAO;
import gr.hua.dit.distributedsystems.entities.Post;
import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;
import gr.hua.dit.distributedsystems.repository.UserRepo;
import gr.hua.dit.distributedsystems.service.PostService;
import gr.hua.dit.distributedsystems.service.RoomService;
import gr.hua.dit.distributedsystems.service.UserService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private PostService postService;
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/room/{rid}/makepost")
	public Post addPost(
			@PathVariable int rid, 
			@RequestBody Post post, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User creator = userService.getUserFromHeaderToken(authHeader);
		
		//getRoomById because members and admin can make a post
		Room room = roomService.getRoomById(rid, creator);
		
		String content = post.getContent();
		
		if(room == null) {
			throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room does not exist"
            );

		}
		
		Post apost = new Post(content, creator.getUsername());
		
		apost.setRoom(room);
		postDAO.save(apost);
		
		return apost;
		
	}
	
	
	@DeleteMapping("/{pid}/delete")
	public void DeletePost(
			@PathVariable int pid, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User admin = userService.getUserFromHeaderToken(authHeader);
		
		Post post = postDAO.findById(pid);
		
		if (post == null) {
			throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post does not exist"
            );
		}
		
		Room room = roomService.getRoomForAdmin(post.getRoom().getId(), admin);
		
		postDAO.delete(post);
		
	}
	
}






