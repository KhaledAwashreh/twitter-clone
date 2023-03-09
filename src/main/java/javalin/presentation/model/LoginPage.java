package javalin.presentation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginPage {
    private String username;
    private String password;
    private boolean hasUsername;
    private boolean userExists;



}
