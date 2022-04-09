package com.github.ga1robe.tempCollector.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="TEMPRECORDS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TempRecord {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;

    @NotNull
    @Size(min=2, max=25, message="Surname must be 2-25 characters long")
    private String city;

    @NotNull
    private double temp;

//    public TempRecord(LocalDate date,LocalTime time,String city,double temp) {
//        this.date = date;
//        this.time = time;
//        this.city = city;
//        this.temp = temp;
//    }

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

    public double getTemp() {
        return this.temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

}
