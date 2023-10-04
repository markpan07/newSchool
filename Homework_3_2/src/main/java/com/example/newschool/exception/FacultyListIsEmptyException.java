package com.example.newschool.exception;

public class FacultyListIsEmptyException extends NotFoundException {

    @Override
    public String getMessage() {
        return "It seems the faculty list is empty yet...";
    }
}
