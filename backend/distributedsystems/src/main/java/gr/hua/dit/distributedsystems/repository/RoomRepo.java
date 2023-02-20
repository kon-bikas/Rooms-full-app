package gr.hua.dit.distributedsystems.repository;

import gr.hua.dit.distributedsystems.entities.Room;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {

	Optional<Room> findByName(String name);
	
}
