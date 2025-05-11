package com.qa.testcases;

import com.qa.base.BaseClass;
import com.qa.pages.HomePage;
import com.qa.utils.CommonActions;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class PlayableAdTestCases extends BaseClass {

    public PlayableAdTestCases(){}


//    @Test
//    @Description("Verify User Should Able To Click Start Collect Gold Button")
//    public void testStartCollectGoldButton(){
//        PlayAbleAdScreen playAbleAdScreen =new PlayAbleAdScreen(driver);
//        playAbleAdScreen.clickOnStartCollectGoldButton();
//    }


//    @Test
//    @Description("Open the browser and Click On Language")
//    public void testUserShouldAbleToClickAnyLanguage(){
//        test.info("Starting login and logout flow");
//        HomePage homePage=new HomePage(driver,test);
//        test.info("Clicked on Language");
//        homePage.clickOnLanguage();
//        test.info("Logged in successfully");
//    }

    @Test(priority = 2)
    @Description("Open the browser and Click On Language")
    public void testUserRegistration() throws InterruptedException {
        HomePage homePage=new HomePage(driver,test);

        CommonActions commonActions=new CommonActions(driver);
        String newUserEmail=commonActions.getRandomEmailId();
        String password="Bonify"+newUserEmail;

        homePage.clickOnNueBaiBonify()
                .verifyRegistrationPage()
                .enterUserRegistrationDetailsAndSubmit(newUserEmail,password)
                .verifyAccountConfirmationDetails(newUserEmail);
    }

    @Test(priority = 1)
    @Description("Login with existing user and logout")
    public void verifyLoginAndLogoutFlowWithExistingUser() {
        HomePage homePage=new HomePage(driver,test);
        String existingUserEmail="subhadipsinha12345@gmail.com";
        String existingPassword="A@123456";

        homePage.clickOnEinloggenButton()
                .verifyLoginPage()
                .loginWithExistingUser(existingUserEmail,existingPassword)
                .verifyTwoFactorAuthDetails()
                .clickOnLogout()
                .verifySuccessfulLogOut();
    }
}
