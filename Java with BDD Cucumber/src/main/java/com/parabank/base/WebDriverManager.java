package com.parabank.base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.WebDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
import com.parabank.config.ConfigReader;
import com.parabank.utils.LogUtils;

/**
 * Advanced WebDriver initialization with options
 */
public class WebDriverManager {
    private static WebDriver driver;

    /**
     * Initialize Chrome driver with options
     *
     * @return WebDriver instance
     */
    private static WebDriver initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Common options
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        // Headless mode if configured
        String headless = ConfigReader.getProperty("headlessMode", "false");
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless");
            options.addArguments("--window-size=" + 
                ConfigReader.getProperty("windowWidth", "1920") + "," +
                ConfigReader.getProperty("windowHeight", "1080"));
        }
        
        driver = new ChromeDriver(options);
        LogUtils.info("Chrome driver initialized with options");
        return driver;
    }

    /**
     * Initialize Firefox driver with options
     *
     * @return WebDriver instance
     */
    private static WebDriver initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        // Common options
        options.addArguments("--width=" + ConfigReader.getProperty("windowWidth", "1920"));
        options.addArguments("--height=" + ConfigReader.getProperty("windowHeight", "1080"));
        
        // Headless mode if configured
        String headless = ConfigReader.getProperty("headlessMode", "false");
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless");
        }
        
        driver = new FirefoxDriver(options);
        LogUtils.info("Firefox driver initialized with options");
        return driver;
    }

    /**
     * Initialize Edge driver with options
     *
     * @return WebDriver instance
     */
    private static WebDriver initializeEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        // Common options
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        // Headless mode if configured
        String headless = ConfigReader.getProperty("headlessMode", "false");
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless");
            options.addArguments("--window-size=" + 
                ConfigReader.getProperty("windowWidth", "1920") + "," +
                ConfigReader.getProperty("windowHeight", "1080"));
        }
        
        driver = new EdgeDriver(options);
        LogUtils.info("Edge driver initialized with options");
        return driver;
    }

    /**
     * Initialize WebDriver based on browser configuration
     *
     * @return WebDriver instance
     */
    public static WebDriver initializeWebDriver() {
        String browser = ConfigReader.getProperty("browser", "chrome").toLowerCase();
        
        switch (browser) {
            case "firefox":
                return initializeFirefoxDriver();
            case "edge":
                return initializeEdgeDriver();
            case "chrome":
            default:
                return initializeChromeDriver();
        }
    }

    /**
     * Get current WebDriver instance
     *
     * @return WebDriver instance
     */
    public static WebDriver getWebDriver() {
        return driver;
    }

    /**
     * Quit WebDriver
     */
    public static void quitWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            LogUtils.info("WebDriver closed");
        }
    }
}
