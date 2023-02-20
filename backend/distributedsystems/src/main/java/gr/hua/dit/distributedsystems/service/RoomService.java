package gr.hua.dit.distributedsystems.service;

import java.util.List;

import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;

public interface RoomService {

	public List<Room> getRooms();
	
	public Room getRoomById(int rid, User user);
	
	public void saveRoom(Room room);
	
	public void deleteRoom(int rid);

	public Room getRoomForAdmin(int rid, User admin);
	
}
