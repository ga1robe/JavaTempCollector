package com.github.ga1robe.tempCollector.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TempRecord {
    private LocalDate date;
    private LocalTime time;
    private String city;
    private int temp;

    public TempRecord(LocalDate date,LocalTime time,String city,int temp) {
        this.date = date;
        this.time = time;
        this.city = city;
        this.temp = temp;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemp() {
        return this.temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

}
