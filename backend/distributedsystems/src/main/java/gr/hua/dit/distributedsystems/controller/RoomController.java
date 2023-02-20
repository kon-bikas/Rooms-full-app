package gr.hua.dit.distributedsystems.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gr.hua.dit.distributedsystems.config.JwtUtils;
import gr.hua.dit.distributedsystems.dao.PostDAO;
import gr.hua.dit.distributedsystems.dao.RoomDAO;
import gr.hua.dit.distributedsystems.dao.UserDAO;
import gr.hua.dit.distributedsystems.entities.Post;
import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;
import gr.hua.dit.distributedsystems.payload.request.RoomRequest;
import gr.hua.dit.distributedsystems.payload.request.UsernameRequest;
import gr.hua.dit.distributedsystems.payload.response.RoomCard;
import gr.hua.dit.distributedsystems.repository.RoomRepo;
import gr.hua.dit.distributedsystems.repository.UserRepo;
import gr.hua.dit.distributedsystems.service.RoomService;
import gr.hua.dit.distributedsystems.service.UserService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
//	private RoomDAO roomDAO;
	
	@Autowired 
	private PostDAO postDAO;
	
	@Autowired 
	private UserDAO userDAO;
	
	@Autowired
	private UserRepo userRepo; 
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<Room> getAllRooms() {
		return roomService.getRooms();
	}
	
	@GetMapping("/roomcards")
	public List<RoomCard> getAllRoomCards(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User user = userService.getUserFromHeaderToken(authHeader);
		
		List<Room> rooms = roomService.getRooms();
			
		List<RoomCard> roomCards = new ArrayList<>();
		
		for(Room r: rooms) {
			roomCards.add(new RoomCard(
						r.getId(),
						r.getName(),
						r.getDescription(),
						user.getRole(r)
					));
		}
		
		return roomCards;
		
	}
	
	@GetMapping("/{rid}")
	public Room getRoom(
			@PathVariable int rid, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User user = userService.getUserFromHeaderToken(authHeader);
		
		//getRoomById because both member and admin can access it
		Room room = roomService.getRoomById(rid, user);
		
		return room;
		
	}
	
	@GetMapping("/get")
	public Room getRoomByName(
			@RequestBody RoomRequest roomName,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User user = userService.getUserFromHeaderToken(authHeader);
		
		//getRoomById because both member and admin can access it
		Room room = roomRepo.findByName(roomName.getName())
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Room does not exist"
				));
		
		return room;
		
	}
	
	@PostMapping("/create")
	public Room save(
			@RequestBody Room room, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User admin = userService.getUserFromHeaderToken(authHeader);
		
		admin.addAdminRoom(room);
		roomRepo.save(room);
		
		return room;
	}
	
	
	@PatchMapping("/{rid}/kick")
	public User kickMember(
			@PathVariable int rid, 
			@RequestBody UsernameRequest usernameReq, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User member = userRepo.findByUsername(usernameReq.getUsername())
				.orElseThrow(() -> new ResponseStatusException(
							HttpStatus.NOT_FOUND, "User does not exist or is not in this room"
						));
		User admin = userService.getUserFromHeaderToken(authHeader);
		
		if (admin.equals(member)) {
			throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "You can't kick yourself from the room when you are the admin"
            );
		}
		
		Room room = roomService.getRoomForAdmin(rid, admin);
		
		String memberRole = "member";
		if (!memberRole.equals(member.getRole(room))) {
			throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "This user is already not a member of this room"
            );
		}
		
		member.removeMemberRoom(room);
		userDAO.save(member);
		
		return member;
		
	}
	
	
}






