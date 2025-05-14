package com.example.ceybank.auth.models;

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}


//import java.util.List;
//
//public class JwtResponse {
//    private String token;
//    private List<String> roles;
//
//    public JwtResponse(String token, List<String> roles) {
//        this.token = token;
//        this.roles = roles;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public List<String> getRoles() {
//        return roles;
//    }
//}

