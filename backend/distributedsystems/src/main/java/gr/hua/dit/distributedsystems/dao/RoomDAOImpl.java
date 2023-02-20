package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.distributedsystems.entities.Room;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Room> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Room", Room.class);
		List<Room> rooms = query.getResultList();
		return rooms;
	}

	@Override
	public Room findById(int id) {
		return entityManager.find(Room.class, id);
	}

	@Override
	public void save(Room room) {
		Room aroom = entityManager.merge(room);
	}

	@Override
	public void delete(int id) {
		Room room = entityManager.find(Room.class, id);
		entityManager.remove(room);
	}

	
	
}
