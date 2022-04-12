package com.github.ga1robe.tempCollector.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import com.github.ga1robe.tempCollector.model.TempRecord;
import com.github.ga1robe.tempCollector.model.TempStatistics;

import org.springframework.stereotype.Service;

@Service
public class TempService {
    //list of all records
    private static List<TempRecord> records = new ArrayList<TempRecord>();
    //to imrove performance we keep calculated statistics per date and city for cities
    private static Map<LocalDate,Map<String,TempStatistics>> cityStatistics = new HashMap<LocalDate,Map<String,TempStatistics>>();
    //to imrove performance we keep calculated statistics per date for country
    private static Map<LocalDate,TempStatistics> countryStatistics = new HashMap<LocalDate,TempStatistics>();
    

    @PostConstruct
    public void addSampleData() {
        System.out.println("Adding sample data on startup");
        addRecord(new TempRecord(0L, LocalDate.now(),LocalTime.of(5, 30), "Warszawa", 30));
        addRecord(new TempRecord(1L, LocalDate.now(),LocalTime.of(5, 30),"Warszawa",30));
        addRecord(new TempRecord(2L, LocalDate.now(),LocalTime.of(12, 0),"Warszawa",32));
        addRecord(new TempRecord(3L, LocalDate.now(),LocalTime.of(23, 0),"Warszawa",10));
        addRecord(new TempRecord(4L, LocalDate.now(),LocalTime.of(3, 0),"Warszawa",11));

        addRecord(new TempRecord(5L, LocalDate.now(),LocalTime.of(8, 0),"Poznań",33));
        addRecord(new TempRecord(6L, LocalDate.now(),LocalTime.of(10, 0),"Poznań",43));
        addRecord(new TempRecord(7L, LocalDate.now(),LocalTime.of(23, 59),"Poznań",12));
        addRecord(new TempRecord(8L, LocalDate.now(),LocalTime.of(5, 0),"Poznań",12));
    }

    public TempStatistics getCountryStatistics(LocalDate date) {
        return countryStatistics.get(date);
    }

    public Collection<TempStatistics> getCityStatistics(LocalDate date) {
        return cityStatistics.get(date).values();
    }

    public TempStatistics getCityStatistics(LocalDate date,String city) {
        if (!cityStatistics.containsKey(date)) return null;
        return cityStatistics.get(date).get(city);
    }

    public void addRecord(TempRecord record) {
        records.add(record);

        synchronized(countryStatistics) {
            //get country stat for date or create new one
            TempStatistics countryTs = countryStatistics.computeIfAbsent(record.getDate(), d -> new TempStatistics(d));
            apply(countryTs, record);
        }

        synchronized(cityStatistics) {
            //get city map for date or create new one, treeMap to keep data sorted by key (city)
            Map<String,TempStatistics> mapTs = cityStatistics.computeIfAbsent(record.getDate(), d -> new TreeMap<String,TempStatistics>());
            //find record for city or create new one
            TempStatistics cityTs = mapTs.computeIfAbsent(record.getCity(), d -> new TempStatistics(record.getDate(),record.getCity()));
            apply(cityTs, record);
        }
    }

    private void apply(TempStatistics stat,TempRecord record) {
        //add record
        stat.getRecords().add(record);
        //recalculate statistics for temp
        stat.setAvgTemp(stat.getRecords().stream().mapToDouble(TempRecord::getTemp).average().orElse(Double.NaN));
        //if record is for night, recalculate night avg temp
        if (isNight(record)) {
            stat.setAvgTempNight(stat.getRecords().stream().filter(r -> isNight(r)).mapToDouble(TempRecord::getTemp).average().orElse(Double.NaN));
        }
        //if record is for day, recalculate day avg temp
        if (isDay(record)) {
            stat.setAvgTempDay(stat.getRecords().stream().filter(r -> isDay(r)).mapToDouble(TempRecord::getTemp).average().orElse(Double.NaN));
        }
    }

    private boolean isNight(TempRecord record) {
        LocalTime time = record.getTime();
        return time.isBefore(LocalTime.of(5, 0)) || time.isAfter(LocalTime.of(20,0)); // night is < 5:00 and > 20:00
    }

    private boolean isDay(TempRecord record) {
        LocalTime time = record.getTime();
        return time.isAfter(LocalTime.of(6,0)) && time.isBefore(LocalTime.of(19, 0)); // day is between 6:00 and 19:00
    }

    public void clear() {
        records.clear();
        cityStatistics.clear();
        countryStatistics.clear();
    }
}