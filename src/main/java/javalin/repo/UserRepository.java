package javalin.repo;

import javalin.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static  final Map<Long, User> USERS = new HashMap<Long, User>();
}
