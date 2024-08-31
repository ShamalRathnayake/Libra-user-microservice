package com.example.user_service.data.userDTO;

public class UserLoginResponseDTO {

    private Boolean status = true;
    private UserResponseDTO user;
    private String token;

    public UserLoginResponseDTO(UserResponseDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
