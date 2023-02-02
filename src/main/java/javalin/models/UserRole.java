package javalin.models;

import io.javalin.core.security.RouteRole;
public enum UserRole implements RouteRole {
NONE, USER, ADMIN
}
