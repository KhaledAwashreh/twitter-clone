package javalin.data;

import org.jdbi.v3.core.Jdbi;

public class JdbiProvider {
    public String connectionString;
   Jdbi jdbi  = Jdbi.create(connectionString);
}
