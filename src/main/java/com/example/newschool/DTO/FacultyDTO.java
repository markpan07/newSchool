package com.example.newschool.DTO;

import java.util.Objects;

public class FacultyDTO {
    private long id;
    private String name;
    private String color;

    public FacultyDTO() {
    }

    public FacultyDTO(String name, String color) {
        this.name = name;
        this.color = color;
        this.id = 0L;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "FacultyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyDTO)) return false;
        FacultyDTO that = (FacultyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
