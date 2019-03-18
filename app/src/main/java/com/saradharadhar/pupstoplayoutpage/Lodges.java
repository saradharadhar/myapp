package com.saradharadhar.pupstoplayoutpage;

public class Lodges {
    private String name;
    private String hours;
    private String photo;
    double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRating() {
        double r=rating;

        String rat=Double.toString(r);

        return rat;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Lodges(String name, String hours, String photo, double rating) {
        this.name = name;
        this.hours = hours;
        this.photo = photo;
        this.rating = rating;
    }

    public Lodges() {
    }
}
