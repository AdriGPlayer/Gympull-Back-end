package edu.unideh.GymPull.dto;

public class LoginRequest {
    private String email;
    private String name;
    private String password;

    public LoginRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
