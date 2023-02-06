package javalin.presentation.middleware;

import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import javalin.data.models.User;
import javalin.presentation.controller.UserController;
import org.apache.commons.lang3.StringUtils;

public class TokenValidator {
    public void validate(Context ctx) {
        UserController service = new UserController();
        String authHeader = ctx.header("Authorization");
        if (StringUtils.isBlank(authHeader) || !StringUtils.contains(authHeader, "Bearer")) {
            throw new UnauthorizedResponse();
        } else {
            String token = authHeader.split("Bearer")[1].trim().strip();
            User founduser = service.validateToken(token);
            if (founduser == null)
                throw new UnauthorizedResponse();
        }
    }
}
