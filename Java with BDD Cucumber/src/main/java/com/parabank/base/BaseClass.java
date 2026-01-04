package com.parabank.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

/**
 * Base class containing common WebDriver actions and utilities
 */
public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final long TIMEOUT = 10;

    /**
     * Initialize driver and wait
     *
     * @param driver WebDriver instance
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    /**
     * Navigate to URL
     *
     * @param url URL to navigate
     */
    protected void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    /**
     * Find element with explicit wait
     *
     * @param locator By locator
     * @return WebElement
     */
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Click on element
     *
     * @param locator By locator
     */
    protected void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Type text in element
     *
     * @param locator By locator
     * @param text Text to type
     */
    protected void typeText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from element
     *
     * @param locator By locator
     * @return Text content
     */
    protected String getText(By locator) {
        return findElement(locator).getText();
    }

    /**
     * Check if element is displayed
     *
     * @param locator By locator
     * @return true if displayed, false otherwise
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Wait for element visibility
     *
     * @param locator By locator
     */
    protected void waitForElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element invisibility
     *
     * @param locator By locator
     */
    protected void waitForElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Select option by visible text
     *
     * @param locator By locator
     * @param visibleText Text to select
     */
    protected void selectByVisibleText(By locator, String visibleText) {
        WebElement dropdown = findElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Select option by value
     *
     * @param locator By locator
     * @param value Value to select
     */
    protected void selectByValue(By locator, String value) {
        WebElement dropdown = findElement(locator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    /**
     * Get title of page
     *
     * @return Page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     *
     * @return Current URL
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
