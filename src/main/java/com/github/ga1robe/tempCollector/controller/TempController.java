package com.github.ga1robe.tempCollector.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import com.github.ga1robe.tempCollector.model.TempRecord;
import com.github.ga1robe.tempCollector.service.TempService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@SessionAttributes("name")
public class TempController {
	
	@Autowired
    TempService service;

	@GetMapping("/")
	public RedirectView index(ModelMap model){
		return new RedirectView("enter");
	}

	
	@RequestMapping(value="/enter", method = RequestMethod.GET)
	public String showEnter(ModelMap model){
		return "enter";
	}
	@RequestMapping(value="/enter", method = RequestMethod.POST)
	public String postEnter(ModelMap model, @RequestParam String city, @RequestParam String temp){
		try {
			if (city.isEmpty()) {
				throw new IllegalArgumentException("Missing city parameter");
			} 
			TempRecord record = new TempRecord(LocalDate.now(), LocalTime.now(), city, Double.parseDouble(temp));
			service.addRecord(record);
			model.put("success","Dane wprowadzone");
		} catch (NumberFormatException e) {
			model.put("city",city);
			model.put("error","Nieprawidłowa temperatura");
			return "enter";
		} catch (Exception e) {
			model.put("error","Bład wprowadzania danych, podaj miasto oraz temperaturę");
			return "enter";
		}
		return "redirect:list";
	}
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String showList(ModelMap model){
		model.put("countryStatistics", this.service.getCountryStatistics(LocalDate.now()));
		model.put("cityStatistics", this.service.getCityStatistics(LocalDate.now()));
		return "list";
	}
}