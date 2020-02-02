package conduit.test.controller.auth;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String permission;

    public JwtResponse(String jwttoken, String permission) {
        this.jwttoken = jwttoken;
        this.permission = permission;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getPermission() {
        return this.permission;
    }
}
