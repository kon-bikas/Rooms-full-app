package gr.hua.dit.distributedsystems.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {

	@NotBlank
    @Size(min = 3, max = 20, message = "username should be between 3 and 20 characters long")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email(message = "That is not a valid email")
    private String email;

    @NotBlank
//    @Size(min = 6, max = 40, message = "password should be between 6 and 40")
    private String password;

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

	
}
