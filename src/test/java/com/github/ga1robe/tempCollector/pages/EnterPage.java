package com.github.ga1robe.tempCollector.pages;

import com.github.ga1robe.tempCollector.locators.EnterWebLocators;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnterPage {
    public static WebElement element = null;
    private static WebDriver driver = null;
    WebDriverWait wait;
    private static EnterWebLocators enterWebLocators;
    static Logger log = Logger.getLogger(EnterPage.class);

    public EnterPage(WebDriver driver) {
        this.driver = driver;
        enterWebLocators = new EnterWebLocators(driver);
        PageFactory.initElements(driver, enterWebLocators);
        wait = new WebDriverWait(driver, 10);
    }

    public static void fillCityInput(WebDriver driver, String city) {
        try {
            element = cityInput(driver);
            element.sendKeys(city);
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
            log.info("Enter city as ".concat(city));
        };
    }

    public static void fillTempInput(WebDriver driver, int temp) {
        try {
            element = tempInput(driver);
            element.sendKeys(String.valueOf(temp));
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
            log.info("Enter temp as ".concat(String.valueOf(temp)));
        };
    }

    public static void clickBtn() {
        if(enterWebLocators.getSaveBtn().isEnabled()){
            enterWebLocators.getSaveBtn().click();
        }
    }

    private static WebElement cityInput(WebDriver driver) {
        try {
            element = enterWebLocators.getCityInput();
        }
        catch (NoSuchElementException noSuchElementException) {
            System.out.println("NoSuchElementException: " + noSuchElementException.getMessage());
            log.warn("NoSuchElementException: " + noSuchElementException.getMessage());
        }
        finally {
            log.info("City input element scrolls");
        }
        return element;
    }

    private static WebElement tempInput(WebDriver driver) {
        try {
            element = enterWebLocators.getTempInput();
        }
        catch (NoSuchElementException noSuchElementException) {
            System.out.println("NoSuchElementException: " + noSuchElementException.getMessage());
            log.warn("NoSuchElementException: " + noSuchElementException.getMessage());
        }
        finally {
            log.info("Temp input element scrolls");
        }
        return element;
    }
}
