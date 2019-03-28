package com.saradharadhar.pupstoplayoutpage;

import android.os.Parcel;
import android.os.Parcelable;

public class Trainers implements Parcelable{
    private String name;
    private String hours;
    private String photo;
    double rating;
    private String image1,image2,image3;

    protected Trainers(Parcel in) {
        name = in.readString();
        hours = in.readString();
        photo = in.readString();
        rating = in.readDouble();
        image1 = in.readString();
        image2 = in.readString();
        image3 = in.readString();
    }

    public static final Creator<Trainers> CREATOR = new Creator<Trainers>() {
        @Override
        public Trainers createFromParcel(Parcel in) {
            return new Trainers(in);
        }

        @Override
        public Trainers[] newArray(int size) {
            return new Trainers[size];
        }
    };

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public Trainers(String image1, String image2, String image3) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
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

    public Trainers(String name, String hours, String photo, double rating) {
        this.name = name;
        this.hours = hours;
        this.photo = photo;
        this.rating = rating;
    }

    public Trainers() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hours);
        dest.writeString(photo);
        dest.writeDouble(rating);
        dest.writeString(image1);
        dest.writeString(image2);
        dest.writeString(image3);
    }
}
