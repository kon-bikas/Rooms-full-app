package gr.hua.dit.distributedsystems.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "rooms",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Room {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name = "name")
	@Size(max = 30)
	@NotBlank
	private String name;
	
	@Column(name = "description")
	@Size(max = 360)
	@NotBlank
	private String description;
	
	private String userRole;
	
	@OneToMany(mappedBy = "room" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Request> requests;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Post> posts;	
	
	public Room() {
		
	}
	
	public Room(String name, String description, List<Request> requests, List<Post> posts) {
		this.name = name;
		this.description = description;
		this.requests = requests;
		this.posts = posts;
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
	
	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void addPost(Post post) {
		if(posts == null) {
			posts = new ArrayList<>();
		}
		
		posts.add(post);
		
	}
	
	public void addRequest(Request request) {
		if(requests == null) {
			requests = new ArrayList<>();
		}
		
		requests.add(request);

	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(!(obj instanceof Room)) {
			return false;
		}
		
		Room room = (Room) obj; //obj is an instance of room so we typecasting it to Room
		if(!(this.name.equals(room.name))) {
			return false;
		}
		
		return true;
		
	}
	
	public boolean hasRequested(User user) {
		
		for (Request req: this.requests) {
			if (user.equals(req.getSender())) {
				return true;
			}
		}
		
		return false;
		
	}
	
}






