package com.saradharadhar.pupstoplayoutpage;

public class Orders {

    private String name,placedBy,timeAndDate;

    public String getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(String placedBy) {
        this.placedBy = placedBy;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public Orders(String placedBy, String timeAndDate) {
        this.placedBy = placedBy;
        this.timeAndDate = timeAndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Orders() {
    }

    public Orders(String name) {
        this.name = name;
    }
}
