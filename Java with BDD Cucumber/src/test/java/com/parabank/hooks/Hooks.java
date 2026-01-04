package com.parabank.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import com.parabank.base.DriverFactory;
import com.parabank.config.ConfigReader;
import com.parabank.utils.LogUtils;

/**
 * Cucumber Hooks for test setup and teardown
 */
public class Hooks {

    /**
     * Setup method before each scenario
     */
    @Before
    public void setUp() {
        String browser = ConfigReader.getProperty("browser", "chrome");
        WebDriver driver = DriverFactory.initializeDriver(browser);
        LogUtils.info("Browser initialized: " + browser);
        LogUtils.info("=".repeat(50));
    }

    /**
     * Teardown method after each scenario
     */
    @After
    public void tearDown() {
        LogUtils.info("=".repeat(50));
        LogUtils.info("Closing browser");
        DriverFactory.quitDriver();
    }
}
