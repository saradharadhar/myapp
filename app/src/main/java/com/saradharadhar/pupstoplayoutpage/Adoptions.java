package com.saradharadhar.pupstoplayoutpage;

import android.os.Parcel;
import android.os.Parcelable;

public class Adoptions implements Parcelable {

    String age,breed,colour;
    String photo;

    protected Adoptions(Parcel in) {
        age = in.readString();
        breed = in.readString();
        colour = in.readString();
        photo = in.readString();
    }

    public static final Creator<Adoptions> CREATOR = new Creator<Adoptions>() {
        @Override
        public Adoptions createFromParcel(Parcel in) {
            return new Adoptions(in);
        }

        @Override
        public Adoptions[] newArray(int size) {
            return new Adoptions[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(age);
        dest.writeString(breed);
        dest.writeString(colour);
        dest.writeString(photo);
    }
}
