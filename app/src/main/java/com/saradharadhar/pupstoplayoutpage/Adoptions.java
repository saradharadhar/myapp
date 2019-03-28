package com.saradharadhar.pupstoplayoutpage;

public class Adoptions {

    String age,breed,colour;
    String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Adoptions(String photo) {
        this.photo = photo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Adoptions(String age, String breed, String colour) {
        this.age = age;
        this.breed = breed;
        this.colour = colour;
    }

    public Adoptions() {
    }
}
