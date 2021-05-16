package com.selimhorri.pack.model.dto;

public class Credential {

    private Integer userId;
    private String username;
    private String password;
    private Boolean enabled;
    private String role;

    public Credential() {

    }

    public Credential(String username, String password, Boolean enabled, String role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public Credential(Integer userId, String username, String password, Boolean enabled, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
