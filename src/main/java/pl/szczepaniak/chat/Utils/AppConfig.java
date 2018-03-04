package pl.szczepaniak.chat.Utils;

public class AppConfig {
    //security properties
    public final static String SERVER_PASSWORD_SECURITY = "clientpassword";
    public final static String CLIENT_NAME_SECURITY = "client";
    public final static Integer ACCESS_TOKEN_VALIDITY_SECONDS = 3600;
    public final static Integer REFRESH_TOKEN_VALIDITY_SECONDS = 28*24*3600;
}
