package com.parabank.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

/**
 * Screenshot utility for capturing screenshots during test execution
 */
public class ScreenshotUtils {
    private static String screenshotPath = "target/screenshots/";

    static {
        File directory = new File(screenshotPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Capture screenshot
     *
     * @param driver WebDriver instance
     * @param screenshotName Name of the screenshot
     * @return File path of the screenshot
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
            String fileName = screenshotPath + screenshotName + "_" + timeStamp + ".png";
            
            File destinationFile = new File(fileName);
            FileUtils.copyFile(sourceFile, destinationFile);
            
            LogUtils.info("Screenshot captured: " + fileName);
            return fileName;
        } catch (IOException e) {
            LogUtils.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Set screenshot path
     *
     * @param path Path for screenshots
     */
    public static void setScreenshotPath(String path) {
        screenshotPath = path;
        File directory = new File(screenshotPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
