package artem.mironenko.jwtauthentication.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}
