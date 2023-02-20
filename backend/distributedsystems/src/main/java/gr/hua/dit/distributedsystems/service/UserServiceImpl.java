package gr.hua.dit.distributedsystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import gr.hua.dit.distributedsystems.config.JwtUtils;
import gr.hua.dit.distributedsystems.entities.Room;
import gr.hua.dit.distributedsystems.entities.User;
import gr.hua.dit.distributedsystems.repository.UserRepo;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public User getUserFromHeaderToken(String authHeader) {
		//removeBearer - added in JwtUtils class
		String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.removeBearer(authHeader));
		
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "User does not exist"
            ));
		
	}

	@Override
	public boolean isUserMemberOrAdminOf(Room room, User user) {
		return !(user.isAdminOf(room)) && !(user.isMemberOf(room));
	}

	
	
}
