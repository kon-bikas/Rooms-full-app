package gr.hua.dit.distributedsystems.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 3, max = 20, message = "username should be between 3 and 20 characters long")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email(message = "That is not a valid email")
    private String email;

    @NotBlank
//    @Size(max = 40, message = "password should be 40 or less characters long")
    @JsonIgnore
    private String password;
    

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
			CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
	})
    @JoinTable(name = "member_rooms",
    		joinColumns = @JoinColumn(name = "member_id"),
    		inverseJoinColumns = @JoinColumn(name = "room_id")
    )
	Set<Room> memberRooms = new HashSet<Room>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {
			CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
	})
    @JoinColumn(name = "admin_id")
    Set<Room> adminRooms = new HashSet<Room>();
    
    
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Set<Room> getMemberRooms() {
		return memberRooms;
	}

	public void setMemberRooms(Set<Room> memberRooms) {
		this.memberRooms = memberRooms;
	}

	public Set<Room> getAdminRooms() {
		return adminRooms;
	}

	public void setAdminRooms(Set<Room> adminRooms) {
		this.adminRooms = adminRooms;
	}
	
	public User() {
    	
    }
    
    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(min = 6, max = 30) String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

    public void addMemberRoom(Room room) {
    	memberRooms.add(room);
    }
    
    public void removeMemberRoom(Room room) {
    	memberRooms.remove(room);
    }
    
    public void addAdminRoom(Room room) {
    	adminRooms.add(room);
    }
    
    public boolean isMemberOf(Room room) {
    	if(memberRooms == null) {
    		return false;
    	}
    	
    	return memberRooms.contains(room);
    	
    }
    
    public boolean isAdminOf(Room room) {
    	if(adminRooms == null) {
    		return false;
    	}
    	
    	return adminRooms.contains(room);
    	
    }
    
    
    public String getRole(Room room) {
    	if (isAdminOf(room)) {
    		return "admin";
    	} else if (isMemberOf(room)) {
    		return "member";
    	} else if (room.hasRequested(this)) {
    		return "requested";
    	} else {
    		return null;
    	}
    	
    }
    
    @Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(!(obj instanceof User)) {
			return false;
		}
		
		User user = (User) obj; //obj is an instance of room so we typecasting it to Room
		if(!(this.username.equals(user.username))) {
			return false;
		}
		
		return true;
		
	}
    
    
}







