package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // WebDriver methods
    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSrc(WebDriver driver) {
        return driver.getPageSource();
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getWindowID(WebDriver driver) {
        return driver.getWindowHandle();
    }

    protected Set<String> getWindowIDs(WebDriver driver) {
        return driver.getWindowHandles();
    }

    protected void navigateToPageUrl(WebDriver driver, String pageUrl) {
        driver.navigate().to(pageUrl);
    }

    protected void navigateBack(WebDriver driver, String pageUrl) {
        driver.navigate().back();
    }

    protected void navigateForward(WebDriver driver, String pageUrl) {
        driver.navigate().forward();
    }

    protected void refreshPage(WebDriver driver, String pageUrl) {
        driver.navigate().refresh();
    }

    protected void switchToOtherWindowByCurrentId(WebDriver driver, String windowId) {
        for (String id : getWindowIDs(driver)) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    protected void switchToWindowByPageTitle(WebDriver driver, String pageTitle) {
        for (String id : getWindowIDs(driver)) {
            driver.switchTo().window(id);
            if (getPageTitle(driver).equals(pageTitle)) {
                break;
            }
        }
    }

    protected void closeAllWindowsExceptWindowById(WebDriver driver, String windowId) {
        for (String id : getWindowIDs(driver)) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(windowId);
    }

    private Alert waitForAlertPresence(WebDriver driver) {
        return getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    protected void dismissAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    protected void sendKeysToAlert(WebDriver driver, String keysToSend) {
        waitForAlertPresence(driver).sendKeys(keysToSend);
    }

    protected String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    // WebElement methods
    private By byXPath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    protected WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(byXPath(locator));
    }

    protected List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(byXPath(locator));
    }

    protected void clickElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    protected void sendKeysToElement(WebDriver driver, String locator, String keysToSend) {
        WebElement element = getWebElement(driver, locator);
        element.clear();
        element.sendKeys(keysToSend);
    }

    protected String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    protected String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    protected void checkCheckBoxOrRadioButton(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void uncheckCheckBox(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected void selectOptionInDropDown(WebDriver driver, String locator, String optionTextValue) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(optionTextValue);
    }

    protected String getSelectedOptionValueInDropDown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    protected boolean isDropDownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    protected int getNumberOfElements(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    protected void switchToFrame(WebDriver driver, String locator) {
        getExplicitWait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byXPath(locator)));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    // Action methods
    private Actions getActions(WebDriver driver) {
        return new Actions(driver);
    }

    protected void hoverMouseToElement(WebDriver driver, String locator) {
        getActions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    protected void rightClickOnElement(WebDriver driver, String locator) {
        getActions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    protected void doubleClickOnElement(WebDriver driver, String locator) {
        getActions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    protected void clickAndHoldOnElement(WebDriver driver, String locator) {
        getActions(driver).clickAndHold(getWebElement(driver, locator)).perform();
    }

    protected void releaseMouse(WebDriver driver, String locator) {
        getActions(driver).release(getWebElement(driver, locator)).perform();
    }

    protected void pressKeyOnElement(WebDriver driver, String locator, Keys keys) {
        getActions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    protected void scrollToElement(WebDriver driver, String locator) {
        getActions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    // JavascriptExecutor methods
    private JavascriptExecutor getJSExecutor(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    protected void clickElementByJS(WebDriver driver, String locator) {
        getJSExecutor(driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    protected void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        getJSExecutor(driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", getWebElement(driver, locator), attributeName, attributeValue);
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        getJSExecutor(driver).executeScript("arguments[0].removeAttribute(arguments[1]);", getWebElement(driver, locator), attributeRemove);
    }

    protected String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) getJSExecutor(driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    protected void highlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        getJSExecutor(driver).executeScript("arguments[0].setAttribute('style', 'border: 2px dashed red;');", element);
        sleepForSeconds(1);
        getJSExecutor(driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        return (Boolean) getJSExecutor(driver).executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", getWebElement(driver, locator));
    }

    // Wait methods
    protected void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected void waitForElementVisible(WebDriver driver, String locator) {
        getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(byXPath(locator)));
    }

    protected void waitForAllElementsVisible(WebDriver driver, String locator) {
        getExplicitWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXPath(locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator) {
        getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(byXPath(locator)));
    }

    // Custom methods
    protected void selectOptionInCustomDropDown(WebDriver driver, String dropDown, String allOptionsLocator, String optionValue) {
        clickElement(driver, dropDown);
        sleepForSeconds(1);
        List<WebElement> allOptions = getListWebElement(driver, allOptionsLocator);
        for (WebElement option : allOptions) {
            if (option.getText().trim().equals(optionValue)) {
                option.click();
                return;
            }
        }
    }
}
