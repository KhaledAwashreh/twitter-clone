package javalin.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserLoginDto {
    private String username;
    private String password;
}