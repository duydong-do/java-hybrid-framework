package com.magento.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_Selenium_Wrapper extends BasePage {
    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, password;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://live.techpanda.org/");

        firstName = "Dong";
        lastName = "Do";
        fullName = firstName + " " + lastName;
        emailAddress = "dong.sdet" + getRandomNumber() + "@gmail.com";
        password = "SeJava4@";
    }

    @Test
    public void TC_01_Register() {
        clickElement(driver, "//div[@class='account-cart-wrapper']//span[text()='Account']");
        clickElement(driver, "//div[@id='header-account']//li/a[text()='Register']");

        sendKeysToElement(driver, "//input[@id='firstname']", firstName);
        sendKeysToElement(driver, "//input[@id='lastname']", lastName);
        sendKeysToElement(driver, "//input[@id='email_address']", emailAddress);
        sendKeysToElement(driver, "//input[@id='password']", password);
        sendKeysToElement(driver, "//input[@id='confirmation']", password);

        clickElement(driver, "//button[@title='Register']");
        acceptAlert(driver);

        Assert.assertEquals(getElementText(driver, "//li[@class='success-msg']//span"), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(getElementText(driver, "//div[@class='welcome-msg']//strong"), "Hello, " + fullName + "!");

        String contactInfo = getElementText(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div/p");
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        clickElement(driver, "//div[@class='account-cart-wrapper']//span[text()='Account']");
        clickElement(driver, "//div[@id='header-account']//li/a[text()='Log Out']");

        Assert.assertEquals(getElementText(driver, "//div[@class='page-title']/h1"), "YOU ARE NOW LOGGED OUT");
    }

    @Test
    public void TC_02_Login() {
        clickElement(driver, "//div[@class='account-cart-wrapper']//span[text()='Account']");
        clickElement(driver, "//div[@id='header-account']//li/a[text()='Log In']");

        sendKeysToElement(driver, "//input[@id='email']", emailAddress);
        sendKeysToElement(driver, "//input[@id='pass']", password);

        clickElement(driver, "//button[@title='Login']");
        acceptAlert(driver);

        Assert.assertEquals(getElementText(driver, "//div[@class='welcome-msg']//strong"), "Hello, " + fullName + "!");

        String contactInfo = getElementText(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div/p");
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        clickElement(driver, "//div[@class='account-cart-wrapper']//span[text()='Account']");
        clickElement(driver, "//div[@id='header-account']//li/a[text()='Log Out']");

        Assert.assertEquals(getElementText(driver, "//div[@class='page-title']/h1"), "YOU ARE NOW LOGGED OUT");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }
}
