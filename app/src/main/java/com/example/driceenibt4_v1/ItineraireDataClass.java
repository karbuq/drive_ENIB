package com.example.driceenibt4_v1;

public class ItineraireDataClass {

    public String depart,arrivee,date,place;

    public ItineraireDataClass(String depart, String arrivee, String date, String place) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.date = date;
        this.place = place;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}




