package com.github.ga1robe.tempCollector.pages;

import com.github.ga1robe.tempCollector.locators.EnterWebLocators;
import com.github.ga1robe.tempCollector.locators.ListWebLocators;
import com.github.ga1robe.tempCollector.locators.ListWebLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class ListPage {
    public static WebElement element = null;
    private static WebDriver driver = null;
    WebDriverWait wait;
    private static ListWebLocators listWebLocators;
    static Logger log = Logger.getLogger(ListPage.class);

    public ListPage(WebDriver driver) {
        this.driver = driver;
        listWebLocators = new ListWebLocators(driver);
        PageFactory.initElements(driver, listWebLocators);
        wait = new WebDriverWait(driver, 10);
    }

    public Map<String,String> getSummaryInCity(WebDriver driver) {
        Map<String,String> summary = new HashMap<String,String>();
        List<WebElement> list = SummaryInCity(driver);
        for (WebElement item:
        list) {
            String[] splitedItem = item.getText().split(" ", 2);
            summary.put(splitedItem[0],splitedItem[1]);
        }
        return  summary;
    }

    public Map<String, String> getSummaryInCity(WebDriver driver, String city) {
        Map<String,String> summary = new HashMap<String,String>();
        List<WebElement> list = SummaryInCity(driver);
        for (WebElement item:
                list) {
            String[] splitedItem = item.getText().split(" ", 2);
            if(splitedItem[0].equals(city))
                summary.put(splitedItem[0],splitedItem[1]);
        }
        return  summary;
    }

    private List<WebElement> SummaryInCity(WebDriver driver) {
        List<WebElement> summary = new ArrayList<>();
        List<WebElement> listWebLocatorsTable1Rows = new ArrayList<>();
        try {
            listWebLocatorsTable1Rows = listWebLocators.getTable1Rows();
        }
        catch(StaleElementReferenceException StaleElementReferenceException){
            System.out.println("StaleElementReferenceException: " + StaleElementReferenceException.getMessage());
            log.warn("StaleElementReferenceException: " + StaleElementReferenceException.getMessage());
        }
        catch (NoSuchElementException noSuchElementException) {
            System.out.println("NoSuchElementException: " + noSuchElementException.getMessage());
            log.warn("NoSuchElementException: " + noSuchElementException.getMessage());
        }
        finally {
            log.info("Get Rows in Summary ");
        };
        return  listWebLocatorsTable1Rows;
    }
}
