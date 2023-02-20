package gr.hua.dit.distributedsystems.service;

import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;

public interface UserService {

	public User getUserFromHeaderToken(String authHeader);
	
	public boolean isUserMemberOrAdminOf(Room room, User user);
	
}
