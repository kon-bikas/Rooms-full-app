package gr.hua.dit.distributedsystems.payload.response;

import java.util.List;

public class UserProfile {

	private int id;
	private String username;
	private String email;
	private List<RoomCard> roomCards;
	
	public UserProfile(int id, String username, String email, List<RoomCard> roomCards) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roomCards = roomCards;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RoomCard> getRoomCards() {
		return roomCards;
	}

	public void setRoomCards(List<RoomCard> roomCards) {
		this.roomCards = roomCards;
	}
	
	
	
}
