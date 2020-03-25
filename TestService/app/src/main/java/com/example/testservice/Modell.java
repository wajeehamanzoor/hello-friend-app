package com.example.testservice;

public class Modell {

    public String lat;
    public String lon;

    public String name;
    //public String Phone;


    public Modell(String lat, String lon, String name) {
        this.lat = lat;
        this.lon = lon;
        this.name=name;
       // this.Phone=Phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 /*  public String getPhone() {
        return Phone;
    }

   public void setPhone(String phone) {
       Phone = phone;
   }
*/


    public Modell() {
    }



    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }



}
