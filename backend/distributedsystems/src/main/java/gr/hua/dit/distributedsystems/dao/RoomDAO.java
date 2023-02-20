package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import gr.hua.dit.distributedsystems.entities.Room;

public interface RoomDAO {

	public List<Room> findAll();
	
	public Room findById(int id);
	
	public void save(Room room);
	
	public void delete(int id);
	
}
