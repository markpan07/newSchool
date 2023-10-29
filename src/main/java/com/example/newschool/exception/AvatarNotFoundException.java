package com.example.newschool.exception;

public class AvatarNotFoundException extends NotFoundException{
    private Long id;

    public AvatarNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Avatar for student with id " + id + " was not found";
    }
}
