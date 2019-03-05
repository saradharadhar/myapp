package com.saradharadhar.pupstoplayoutpage;

public class Restaurants {

    private String name;
    private String hours;
    private String type;

    public Restaurants(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String photo;
    double rating;

    public Restaurants() {
    }

    public Restaurants(String name, String hours, double rating, String type) {
        this.name = name;
        this.hours = hours;
        this.rating = rating;
        this.type = type;
    }

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

    public String getRating() {

        double r=rating;

        String rat=Double.toString(r);

        return rat;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
