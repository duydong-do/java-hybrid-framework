package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject selectRegisterInAccountHeaderDropDown() {
        waitForElementClickable(driver, HomePageUI.ACCOUNT_HEADER_DROPDOWN);
        selectOptionInCustomDropDown(driver, HomePageUI.ACCOUNT_HEADER_DROPDOWN, HomePageUI.ACCOUNT_HEADER_DROPDOWN_OPTIONS, "Register");
        return PageGeneratorManager.getRegisterPage(driver);
    }

    public LoginPageObject selectLoginInAccountHeaderDropDown() {
        waitForElementClickable(driver, HomePageUI.ACCOUNT_HEADER_DROPDOWN);
        selectOptionInCustomDropDown(driver, HomePageUI.ACCOUNT_HEADER_DROPDOWN, HomePageUI.ACCOUNT_HEADER_DROPDOWN_OPTIONS, "Log In");
        return PageGeneratorManager.getLoginPageObject(driver);
    }

    public String getLogoutSuccessMessage() {
        waitForElementVisible(driver, HomePageUI.LOGOUT_SUCCESS_MESSAGE);
        return getElementText(driver, HomePageUI.LOGOUT_SUCCESS_MESSAGE);
    }
}
