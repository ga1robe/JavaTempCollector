package com.github.ga1robe.tempCollector.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ListWebLocators {
    WebDriver driver;

    //locators
    //table.table:nth-child(2) > tbody:nth-child(2) > tr:nth-child(1) > th:nth-child(1)
    //CSS Path: html body div.mx-auto table.table tbody tr
    //xpath:/html/body/div/table[1]/tbody/tr
    @FindBy(xpath="/html/body/div/table[1]/tbody/tr")
    private List<WebElement> table1Rows;


    //xpath: /html/body/div/table[2]/tbody/tr
    @FindBy(xpath="/html/body/div/table[1]/tbody/tr")
    private WebElement table2row;

    public ListWebLocators(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public List<WebElement> getTable1Rows() {
        return table1Rows;
    }

    public WebElement getTable2row() {
        return table2row;
    }
}
