package javalin.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDto {

    @NonNull
    private long userId;
    @NonNull
    private String username;
    @NonNull
    private String password;
}