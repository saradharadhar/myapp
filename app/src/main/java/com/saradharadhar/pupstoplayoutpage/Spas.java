package com.saradharadhar.pupstoplayoutpage;

public class Spas {

    private String name;private String hours; private String address;private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    double rating;

    public Spas() {
    }

    public Spas(String name, String hours,String address, double rating,String photo) {
        this.name = name;
        this.hours = hours;
        this.rating = rating;
        this.address=address;
        this.photo=photo;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
