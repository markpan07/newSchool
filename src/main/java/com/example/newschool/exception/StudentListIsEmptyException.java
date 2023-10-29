package com.example.newschool.exception;

public class StudentListIsEmptyException extends NotFoundException {

    @Override
    public String getMessage() {
        return "It seems the student list is empty yet...";
    }
}
