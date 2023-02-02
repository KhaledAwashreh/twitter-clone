package javalin.middlewear;

import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import javalin.models.User;
import javalin.services.UserService;
import org.apache.commons.lang3.StringUtils;

public class TokenValidator {
    public void validate(Context ctx) {
        System.out.print("testCall");
        UserService service = new UserService();
        String authHeader = ctx.header("AUTHORIZATION");
        if (StringUtils.isBlank(authHeader) || !StringUtils.contains(authHeader, "Bearer")) {
            throw new UnauthorizedResponse();
        } else {
            String token = authHeader.split("Bearer")[1].trim().strip();
            User founduser = service.validateToken(token);
            if (founduser != null)
                throw new UnauthorizedResponse();
        }
    }
}
