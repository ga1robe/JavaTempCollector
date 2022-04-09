package com.github.ga1robe.tempCollector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import com.github.ga1robe.tempCollector.model.TempRecord;
import com.github.ga1robe.tempCollector.service.TempService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TempCollectorApplicationTests {

	@Autowired
	TempService tempService;

	@Test
	void testAvgTemp() {
		//clear sample data
		tempService.clear();
		tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(10, 0),"Kraków",10));
        tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(12, 0),"Kraków",20));
		assertEquals(15,tempService.getCityStatistics(LocalDate.now(),"Kraków").getAvgTemp());
		assertEquals(15,tempService.getCountryStatistics(LocalDate.now()).getAvgTemp());
	}

	@Test
	void testAvgNightTemp() {
		//clear sample data
		tempService.clear();
		tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(3, 0),"Chorzów",10)); //night
        tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(22, 0),"Chorzów",20)); //night
		tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(16, 0),"Chorzów",20)); //day
		assertEquals(15,tempService.getCityStatistics(LocalDate.now(),"Chorzów").getAvgTempNight());
		assertEquals(15,tempService.getCountryStatistics(LocalDate.now()).getAvgTempNight());
	}

	@Test
	void testAvgDayTemp() {
		//clear sample data
		tempService.clear();
		tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(7, 0),"Kielce",10)); //day
        tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(10, 0),"Kielce",20)); //day
		tempService.addRecord(new TempRecord(null, LocalDate.now(),LocalTime.of(22, 0),"Kielce",20)); //night
		assertEquals(15,tempService.getCityStatistics(LocalDate.now(),"Kielce").getAvgTempDay());
		assertEquals(15,tempService.getCountryStatistics(LocalDate.now()).getAvgTempDay());
	}

}
