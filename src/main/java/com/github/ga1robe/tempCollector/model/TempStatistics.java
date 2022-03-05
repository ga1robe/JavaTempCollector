package com.github.ga1robe.tempCollector.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TempStatistics {
    private List<TempRecord> records = new ArrayList<TempRecord>();
    private LocalDate date;
    private String city;
    private Double avgTemp;
    private Double avgTempNight;
    private Double avgTempDay;

    public TempStatistics(LocalDate date) {
        this.date = date;
    }
    public TempStatistics(LocalDate date,String city) {
        this.date = date;
        this.city = city;
    }

    public String toString() {
        return String.format("Stat for: %s city: %s (all: %.2f, day: %.2f, night: %.2f)",this.date,this.city,this.avgTemp,this.avgTempDay,this.avgTempNight);
    }

    public List<TempRecord> getRecords() {
        return this.records;
    }

    public void setRecords(List<TempRecord> records) {
        this.records = records;
    }

    public LocalDate getDate() {
        return this.date;
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

    public Double getAvgTemp() {
        return this.avgTemp;
    }

    public void setAvgTemp(Double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Double getAvgTempNight() {
        return this.avgTempNight;
    }

    public void setAvgTempNight(Double avgTempNight) {
        this.avgTempNight = avgTempNight;
    }

    public Double getAvgTempDay() {
        return this.avgTempDay;
    }

    public void setAvgTempDay(Double avgTempDay) {
        this.avgTempDay = avgTempDay;
    }
    
}
