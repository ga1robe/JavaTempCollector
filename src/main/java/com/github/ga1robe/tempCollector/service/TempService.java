package com.github.ga1robe.tempCollector.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.github.ga1robe.tempCollector.model.TempRecord;
import com.github.ga1robe.tempCollector.model.TempStatistics;

import org.springframework.stereotype.Service;

@Service
public class TempService {
    //list of all records
    private static List<TempRecord> records = new ArrayList<TempRecord>();
    //calculated statistics for cities per date
    private static Map<LocalDate,List<TempStatistics>> cityStatistics = new HashMap<LocalDate,List<TempStatistics>>();
    private static Map<LocalDate,TempStatistics> countryStatistics = new HashMap<LocalDate,TempStatistics>();
    

    @PostConstruct
    public void addSampleData() {
        System.out.println("Adding sample data on startup");
        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(10, 0),"Warszawa",30));
        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(12, 0),"Warszawa",32));
        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(23, 0),"Warszawa",10));
        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(3, 0),"Warszawa",11));

        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(8, 0),"Poznań",33));
        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(10, 0),"Poznań",43));
        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(23, 59),"Poznań",12));
        addRecord(new TempRecord(LocalDate.now(),LocalTime.of(5, 0),"Poznań",12));
    }

    public TempStatistics getCountryStatistics(LocalDate date) {
        return countryStatistics.get(date);
    }

    public List<TempStatistics> getCityStatistics(LocalDate date) {
        return cityStatistics.get(date);
    }

    public synchronized void addRecord(TempRecord record) {
        records.add(record);
        //add record to country statistics
        TempStatistics ts = countryStatistics.computeIfAbsent(record.getDate(), d -> new TempStatistics(d));
        ts.apply(record);
        //get list for date or create new one
        List<TempStatistics> listTs = cityStatistics.computeIfAbsent(record.getDate(), d -> new ArrayList<TempStatistics>());
        //find record for city or create new one
        TempStatistics cts = listTs.stream()
            .filter(tempStat -> record.getCity().equalsIgnoreCase(tempStat.getCity()))
            .findAny()
            .orElseGet(() -> {
                    TempStatistics t = new TempStatistics(record.getDate(),record.getCity());
                    listTs.add(t);
                    listTs.sort(Comparator.comparing(TempStatistics::getCity));
                    return t;
                }
            );
        cts.apply(record);
    }
}