package gr.hua.dit.distributedsystems.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import gr.hua.dit.distributedsystems.config.JwtUtils;
import gr.hua.dit.distributedsystems.dao.UserDAO;
import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;
import gr.hua.dit.distributedsystems.payload.request.UsernameRequest;
import gr.hua.dit.distributedsystems.payload.response.RoomCard;
import gr.hua.dit.distributedsystems.payload.response.UserProfile;
import gr.hua.dit.distributedsystems.repository.UserRepo;
import gr.hua.dit.distributedsystems.service.UserService;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("")
	public List<User> getAll() {
		return userDAO.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable int id) {
		User user = userDAO.findById(id);
		
		if(user == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "user not found"
			);
		}
		
		return user;
		
	}
	
	@PostMapping("")
	public User save(@RequestBody User user) {
		user.setId(0);
		userDAO.save(user);
		return user;
	}
	
	@GetMapping("/{id}/member")
	public Set<Room> getMemberRooms(@PathVariable int id) {
		User user = userDAO.findById(id);
		
		if(user == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "user not found"
			);
		}
		
		
		
		return user.getMemberRooms();
		
	}
	
	@GetMapping("/name/{username}")
	public User getUserByName(@PathVariable String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(
					HttpStatus.NOT_FOUND, "User does not exist"
				));
	}
	
	@GetMapping("/profile/{username}")
	public UserProfile getUserProfile(
			@PathVariable String username,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User auser = userService.getUserFromHeaderToken(authHeader);
		
		User userWithProfile = userRepo.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "User does not exist"
					));
		
		List<RoomCard> roomCards = new ArrayList<>();
		
		userWithProfile.getAdminRooms().forEach((room) -> {
			roomCards.add(new RoomCard(
					room.getId(),
					room.getName(),
					room.getDescription(),
					auser.getRole(room)
				));
		});
		
		userWithProfile.getMemberRooms().forEach((room) -> {
			roomCards.add(new RoomCard(
					room.getId(),
					room.getName(),
					room.getDescription(),
					auser.getRole(room)
				));
		});
		
		return new UserProfile(
					userWithProfile.getId(), username, userWithProfile.getEmail(), roomCards
				);
		
	}
	
}












