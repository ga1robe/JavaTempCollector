package com.github.ga1robe.tempCollector.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterWebLocators {
    WebDriver driver;

    //    @FindBy(id="city")
    @FindBy(xpath="//*[@id=\"city\"]")
    private WebElement cityInput;

//    @FindBy(id="temp")
    @FindBy(xpath="//*[@id=\"temp\"]")
    private WebElement tempInput;

    //.btn
    //html body div.mx-auto form button.btn.btn-primary
    ///html/body/div/form/button[@class="btn btn-primary"]
    @FindBy(xpath="/html/body/div/form/button[@class=\"btn btn-primary\"]")
    private WebElement saveBtn;

    public EnterWebLocators(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getCityInput() {
        return cityInput;
    }

    public WebElement getTempInput() {
        return tempInput;
    }

    public WebElement getSaveBtn() {
        return saveBtn;
    }
}
