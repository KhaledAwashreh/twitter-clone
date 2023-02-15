package javalin.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserLoginDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}