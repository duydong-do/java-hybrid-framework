package com.magento.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountDashboardPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Page_Object_Model extends BaseTest {
    private WebDriver driver;
    private String firstName, lastName, fullName, emailAddress, password;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private AccountDashboardPageObject accountDashboardPage;

    @BeforeClass
    public void beforeClass() {
        driver = getWebDriver();
        driver.get("https://live.techpanda.org/");
        homePage = PageGeneratorManager.getHomePage(driver);

        firstName = "Dong";
        lastName = "Do";
        fullName = firstName + " " + lastName;
        emailAddress = "dong.sdet" + getRandomNumber() + "@gmail.com";
        password = "SeJava4@";
    }

    @Test
    public void TC_01_Register() {
        registerPage = homePage.selectRegisterInAccountHeaderDropDown();

        registerPage.sendKeysToFirstNameTextBox(firstName);
        registerPage.sendKeysToLastNameTextBox(lastName);
        registerPage.sendKeysToEmailTextBox(emailAddress);
        registerPage.sendKeysToPasswordTextBox(password);
        registerPage.sendKeysToConfirmPasswordTextBox(password);

        accountDashboardPage = registerPage.clickRegisterButton();

        Assert.assertEquals(accountDashboardPage.getRegisterSuccessMessage(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(accountDashboardPage.getWelcomeMessage(), "Hello, " + fullName + "!");

        String contactInfo = accountDashboardPage.getContactInformation();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        homePage = accountDashboardPage.selectLogoutInAccountHeaderDropDown();

        Assert.assertEquals(homePage.getLogoutSuccessMessage(), "YOU ARE NOW LOGGED OUT");
    }

    @Test
    public void TC_02_Login() {
        loginPage = homePage.selectLoginInAccountHeaderDropDown();

        loginPage.sendKeysToEmailTextBox(emailAddress);
        loginPage.sendKeysToPasswordTextBox(password);

        accountDashboardPage = loginPage.clickLoginButton();

        Assert.assertEquals(accountDashboardPage.getWelcomeMessage(), "Hello, " + fullName + "!");

        String contactInfo = accountDashboardPage.getContactInformation();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        homePage = accountDashboardPage.selectLogoutInAccountHeaderDropDown();

        Assert.assertEquals(homePage.getLogoutSuccessMessage(), "YOU ARE NOW LOGGED OUT");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
