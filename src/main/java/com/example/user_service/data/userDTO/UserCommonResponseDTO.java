package com.example.user_service.data.userDTO;

public class UserCommonResponseDTO<T> {
    private boolean status = true;
    private T data;

    public UserCommonResponseDTO(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
