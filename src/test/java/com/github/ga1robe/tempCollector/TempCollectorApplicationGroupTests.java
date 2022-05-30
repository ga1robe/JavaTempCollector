package com.github.ga1robe.tempCollector;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.github.ga1robe.tempCollector.pages.EnterPage;
import com.github.ga1robe.tempCollector.pages.ListPage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TempCollectorApplicationGroupTests {
    private WebDriver driver;
    private String baseUrl;
    private String baseUrl2;
    static Logger log;

    @BeforeClass(alwaysRun = false)
    public void beforeClass() {
        System.out.println("This method runs before class");
    }
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        log = LogManager.getLogger(TempCollectorApplicationGroupTests.class);
        log.setLevel(Level.INFO);
        System.out.println("[beforeMethod] This method runs before every method and open Browser");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        baseUrl2 = "http://localhost:8080/list";

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(21, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(21, TimeUnit.SECONDS);
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyConfigurator.configure(props);

        driver.get(baseUrl);
    }

    @Test(groups = { "test", "Warszawa" })
    public void Cities1Series() {
        log.info("[Cities1Series] Running Test - Cities 1 Series");
        // Given
        // Arrange
        EnterPage enterPage = new EnterPage(driver);
        enterPage.fillCityInput(driver, "Warszwa");
        EnterPage.fillTempInput(driver, 20);
        EnterPage.clickBtn();
        ListPage listPage = new ListPage(driver);
        String city = new String();
        String summaryTemps = new String();
        Map<String,String> summaryInCity = listPage.getSummaryInCity(driver, "Warszawa");
        for (Map.Entry<String,String> entry : summaryInCity.entrySet()) {
            city = entry.getKey();
            summaryTemps = entry.getValue();
            log.info("City = " + city + ", Summary = " + summaryTemps);
        }
        // When
        // Act
        String[] splitedSummaryTemps = new String[3];
        String avgTemp = new String();
        String avgTempDaily = new String();
        String avgTempAtNight = new String();
        if(city.length() > 0 && summaryTemps.length() > 0){
            splitedSummaryTemps = summaryTemps.split(" ", 3);
            avgTemp = splitedSummaryTemps[0];
            avgTempDaily = splitedSummaryTemps[1];
            if(splitedSummaryTemps.length > 2)
                avgTempAtNight = splitedSummaryTemps[2];
        }
        // Then
        // Assert
        assertThat("Average temperature is not equals ".concat(String.valueOf(23)), avgTemp, equalTo(String.valueOf(23)));
        assertThat("Average daily temperature is not equals ".concat(String.valueOf(32)), avgTempDaily, equalTo(String.valueOf(32)));
        assertThat("Average night temperature is not equals ".concat(String.valueOf(10)), avgTempAtNight, equalTo(String.valueOf(10)));
    }
    @Test(groups = { "test", "Chorzów" })
    public void Cities2Series() {
        log.info("[Cities2Series] Running Test - Cities 2 Series");
        // Given
        // Arrange
        EnterPage enterPage = new EnterPage(driver);
        enterPage.fillCityInput(driver, "Chorzów");
        EnterPage.fillTempInput(driver, 10);
        EnterPage.clickBtn();
        ListPage listPage = new ListPage(driver);
        String city = new String();
        String summaryTemps = new String();
        Map<String,String> summaryInCity = listPage.getSummaryInCity(driver, "Chorzów");
        for (Map.Entry<String,String> entry : summaryInCity.entrySet()) {
            city = entry.getKey();
            summaryTemps = entry.getValue();
            log.info("City = " + city + ", Summary = " + summaryTemps);
        }
        // When
        // Act
        String[] splitedSummaryTemps = new String[3];
        String avgTemp = new String();
        String avgTempDaily = new String();
        String avgTempAtNight = new String();
        if(city.length() > 0 && summaryTemps.length() > 0){
            splitedSummaryTemps = summaryTemps.split(" ", 3);
            avgTemp = splitedSummaryTemps[0];
            if(splitedSummaryTemps.length > 1)
                avgTempDaily = splitedSummaryTemps[1];
            if(splitedSummaryTemps.length > 2)
                avgTempAtNight = splitedSummaryTemps[2];
        }
        // Then
        // Assert
        assertThat("Average temperature is not equals 10", avgTemp, equalTo(String.valueOf(10)));
        if(avgTempDaily.length() > 0)
            assertThat("Average daily temperature is not equals 10", avgTempDaily, equalTo(String.valueOf(10)));
        else
            assertThat("Average daily temperature is not equals 10", avgTempDaily, equalTo(String.valueOf("")));
        if(avgTempAtNight.length() > 0)
            assertThat("Average night temperature is not equals ".concat(String.valueOf(10)), avgTempAtNight, equalTo(String.valueOf(10)));
        else
            assertThat("Average night temperature is not equals ".concat(String.valueOf(10)), avgTempAtNight, equalTo(""));
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        log.info("This method runs after every method and close Browser");
        try{ Thread.sleep(5000);
            driver.close();
        }catch (Exception e){
            System.err.println("ERROR [closeBrowser] Nothing to do with it");
            log.error("[closeBrowser] Nothing to do with it");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        log.info("[tearDown] This method runs after class");
        try{ Thread.sleep(5000);
            driver.close();
        }catch (Exception e){
            System.err.println("ERROR [tearDown] Nothing to do with it");
            log.error("[tearDown] Nothing to do with it");
        }
    }
}
