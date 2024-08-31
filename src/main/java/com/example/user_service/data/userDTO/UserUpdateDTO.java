package com.example.user_service.data.userDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UserUpdateDTO extends UserCreateDTO{

    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be a positive integer")
    @Min(value = 1, message = "ID must be greater than 0")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
