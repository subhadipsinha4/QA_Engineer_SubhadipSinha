package com.qa.pages;

import com.qa.utils.CommonActions;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlayAbleAdScreen {
    WebDriver driver;
    CommonActions commonActions;

    public PlayAbleAdScreen(WebDriver driver){
        this.driver=driver;
        commonActions=new CommonActions(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//canvas[@id='skeletonAd']")
    public WebElement clickOnCollectButton;

    @Description("Click on the Start Collect Gold Button")
    public PlayAbleAdScreen clickOnStartCollectGoldButton(){
        commonActions.waitForSometime(3000);
        clickOnCollectButton.click();
        return this;
    }



}
