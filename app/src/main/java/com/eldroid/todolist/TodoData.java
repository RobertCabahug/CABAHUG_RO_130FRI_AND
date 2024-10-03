package com.eldroid.todolist;

public class TodoData {

    private int img;
    private String description;

    // Constructor
    public TodoData(int img, String description) {
        this.img = img;
        this.description = description;
    }

    // Getter for image
    public int getImg() {
        return img;
    }

    // Setter for image
    public void setImg(int img) {
        this.img = img;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }
}
