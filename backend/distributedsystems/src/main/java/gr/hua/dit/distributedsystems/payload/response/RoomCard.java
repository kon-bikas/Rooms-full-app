package gr.hua.dit.distributedsystems.payload.response;

public class RoomCard {

	private int id;
	private String name;
	private String description;
	private String role; //This is the role of the user that made the request
	
	public RoomCard(int id, String name, String description, String role) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.role = role;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
