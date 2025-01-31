package com.tester;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestFaceBook {

    WebDriver driver;

    public TestFaceBook() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage"); // Giải quyết vấn đề với bộ nhớ chia sẻ
        options.addArguments("--headless"); // Nếu bạn muốn chạy Chrome không có giao diện đồ họa
        options.addArguments("--disable-gpu"); // Tắt GPU

        driver = new ChromeDriver(options);
    }

    @Test
    public void testLogin() {
        try {
            driver.get("https://www.facebook.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            username.sendKeys("");

            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
            password.sendKeys("123456");

            WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
            login.click();

            wait.until(ExpectedConditions.urlContains("https://www.facebook.com/"));

            WebElement dasboard = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='banner']")));

            if(dasboard.isDisplayed()){
                System.out.println("Test passed: ...");
            }else{
                System.out.println("Test failed: ...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
