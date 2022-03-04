package com.github.ga1robe.tempCollector.model;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public void apply(TempRecord record) {
        //add record
        this.records.add(record);
        //recalculate statistics
        this.avgTemp = this.records.stream().mapToInt(TempRecord::getTemp).average().orElse(Double.NaN);
        this.avgTempNight = this.records.stream().filter(r -> isNight(r)).mapToInt(TempRecord::getTemp).average().orElse(Double.NaN);
        this.avgTempDay = this.records.stream().filter(r -> isDay(r)).mapToInt(TempRecord::getTemp).average().orElse(Double.NaN);
    }

    private static boolean isNight(TempRecord record) {
        LocalTime time = record.getTime();
        return time.isBefore(LocalTime.of(5, 0)) || time.isAfter(LocalTime.of(20,0));
    }

    private static boolean isDay(TempRecord record) {
        LocalTime time = record.getTime();
        return time.isAfter(LocalTime.of(6,0)) && time.isBefore(LocalTime.of(19, 0));
    }

    public String toString() {
        return "Stat for:%s city:%s (all:%.2f,day:%.2f,night:%.2f)".formatted(this.date,this.city,this.avgTemp,this.avgTempDay,this.avgTempNight);
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
