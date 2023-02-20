package gr.hua.dit.distributedsystems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import gr.hua.dit.distributedsystems.entities.Request;
import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;
import gr.hua.dit.distributedsystems.service.RoomService;
import gr.hua.dit.distributedsystems.service.UserService;
import gr.hua.dit.distributedsystems.dao.RequestDAO;
import gr.hua.dit.distributedsystems.dao.RoomDAO;
import gr.hua.dit.distributedsystems.dao.UserDAO;

import gr.hua.dit.distributedsystems.payload.response.RequestCard;

@RestController
@RequestMapping("/requests")
public class RequestController {

	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomDAO roomDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<Request> getAllRequests() {
		return requestDAO.findAll();
	}
	
	@PostMapping("/send/room/{rid}")
	public Request makeRequest(
			@PathVariable int rid, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		Room room = roomDAO.findById(rid);
		User sender = userService.getUserFromHeaderToken(authHeader);
		
		if(room == null) {
			throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Room does not exist"
            );
		}
		
		if (sender.getRole(room) != null) {
			throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "You can't make a request to this room."
            );
		}
		
		Request request = new Request(sender, room, sender.getUsername());
		room.addRequest(request);
		requestDAO.save(request);
		return request;
		
	}

	@GetMapping("/{rid}")
	public List<Request> getRoomRequests(
			@PathVariable int rid, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User admin = userService.getUserFromHeaderToken(authHeader);
		
		Room room = roomService.getRoomForAdmin(rid, admin);
		
		return room.getRequests();
		
	}
	
	
	
	
	@PutMapping("/{req_id}/acceptrequest")
	public User acceptRequest(
			@PathVariable int req_id, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User admin = userService.getUserFromHeaderToken(authHeader);
		
		Request request = requestDAO.findById(req_id);
		
		
		if(request == null) {
			throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Request does not exist"
            );
		}
		
		
		int rid = request.getRoom().getId();
		Room room = roomService.getRoomForAdmin(rid, admin);
		
		User sender = request.getSender();
		sender.addMemberRoom(room);
		userDAO.save(sender);
		
		requestDAO.delete(req_id);
		
		return sender;
		
		
	}
	
	@DeleteMapping("/{req_id}/declinerequest")
	public void declineRequest(
			@PathVariable int req_id, 
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User admin = userService.getUserFromHeaderToken(authHeader);
		Request request = requestDAO.findById(req_id);
		
		
		if(request == null) {
			throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Request does not exist"
            );
		}
		
		
		int rid = request.getRoom().getId();
		Room room = roomService.getRoomForAdmin(rid, admin);
		
		requestDAO.delete(req_id);
	
		
	}
	
	@GetMapping("/all/user/requests")
	public List<RequestCard> getAllUserRequests(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		
		User user = userService.getUserFromHeaderToken(authHeader);
		
		List<RequestCard> requests = new ArrayList<>();
			
		user.getAdminRooms().forEach((room) -> {
			
			for(Request req: room.getRequests()) {
				requests.add(
						new RequestCard(
								req.getId(), 
								room.getName(), 
								req.getSender().getUsername())
				);
			}
			
		});
		
		return requests;
		
	}	
		
	
}


















