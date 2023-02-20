package gr.hua.dit.distributedsystems.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import gr.hua.dit.distributedsystems.dao.RoomDAO;
import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;
import gr.hua.dit.distributedsystems.payload.response.RoomCard;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDAO roomDAO;
	
	@Override
	@Transactional
	public List<Room> getRooms() {
		return roomDAO.findAll();
	}

	@Override
	@Transactional
	public Room getRoomById(int rid, User user) {
		Room room = roomDAO.findById(rid);
		
		if(room == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Room does not exist"
			);
		}
		
		if(!(user.isMemberOf(room)) && !(user.isAdminOf(room))) {
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Sorry, only member of this room can enter"
			);
		}
		
		String role = "";
		
		if (user.isMemberOf(room)) {
			role = "member";
		} else {
			role = "admin";
		}
		
		room.setUserRole(role);
		return room;
	}

	@Override
	@Transactional
	public void saveRoom(Room room) {
		roomDAO.save(room);
	}

	@Override
	@Transactional
	public void deleteRoom(int rid) {
		roomDAO.delete(rid);
	}
	
	@Override
	@Transactional
	public Room getRoomForAdmin(int rid, User admin) {
		Room room = roomDAO.findById(rid);
		
		if(room == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Room does not exist"
			);
		}
		
		if(!(admin.isAdminOf(room))) {
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Sorry, you are not the admin of this room"
			);
		}
		return roomDAO.findById(room.getId());
	}
	
	
}
