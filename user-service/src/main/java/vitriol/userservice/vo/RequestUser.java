package vitriol.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotNull(message = "Email cannot be Null")
    @Size(min = 2, message = "Email not be less than 2")
    @Email
    private String email;

    @NotNull(message = "Name cannot be Null")
    @Size(min = 2, message = "Name not be less than 2")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or greater than 8")
    private String password;

}
