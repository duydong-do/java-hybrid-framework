package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.AccountDashboardPageUI;

public class AccountDashboardPageObject extends BasePage {
    private WebDriver driver;

    public AccountDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, AccountDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, AccountDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getWelcomeMessage() {
        waitForElementVisible(driver, AccountDashboardPageUI.WELCOME_MESSAGE);
        return getElementText(driver, AccountDashboardPageUI.WELCOME_MESSAGE);
    }

    public String getContactInformation() {
        waitForElementVisible(driver, AccountDashboardPageUI.CONTACT_INFORMATION);
        return getElementText(driver, AccountDashboardPageUI.CONTACT_INFORMATION);
    }

    public HomePageObject selectLogoutInAccountHeaderDropDown() {
        waitForElementClickable(driver, AccountDashboardPageUI.ACCOUNT_HEADER_DROPDOWN);
        selectOptionInCustomDropDown(driver, AccountDashboardPageUI.ACCOUNT_HEADER_DROPDOWN, AccountDashboardPageUI.ACCOUNT_HEADER_DROPDOWN_OPTIONS, "Log Out");
        return PageGeneratorManager.getHomePage(driver);
    }
}
