package com.saradharadhar.pupstoplayoutpage;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurants implements Parcelable{

    private String name;
    private String hours;
    private String type;
    private String phone;
    private Double latitude;
    private Double longitude;
    private String image1,image2,image3;

    protected Restaurants(Parcel in) {
        name = in.readString();
        hours = in.readString();
        type = in.readString();
        phone = in.readString();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        image1 = in.readString();
        image2 = in.readString();
        image3 = in.readString();
        photo = in.readString();
        rating = in.readDouble();
    }

    public static final Creator<Restaurants> CREATOR = new Creator<Restaurants>() {
        @Override
        public Restaurants createFromParcel(Parcel in) {
            return new Restaurants(in);
        }

        @Override
        public Restaurants[] newArray(int size) {
            return new Restaurants[size];
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

    public Restaurants(String image1, String image2, String image3) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    public String getPhone() {


        return phone;
    }

    public void setPhone(String phone) {


        this.phone = phone;
    }

    public String getLatitude() {
        double r=latitude;

        String lat=Double.toString(r);

        return lat;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        double r=longitude;

        String log=Double.toString(r);

        return log;
    }
    public void setLongitude(Double logitude) {
        this.longitude = logitude;
    }

    public Restaurants(String phone, Double latitude, Double longitude) {
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }


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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hours);
        dest.writeString(type);
        dest.writeString(phone);
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        dest.writeString(image1);
        dest.writeString(image2);
        dest.writeString(image3);
        dest.writeString(photo);
        dest.writeDouble(rating);
    }
}
