package com.tester;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestResgisterShop {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

     @Test
    public void testDuplicateName() {
        driver.get("http://127.0.0.1:8000/register");

        driver.findElement(By.id("name")).sendKeys("Admin");
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("password_confirmation")).sendKeys("password123");
        driver.findElement(By.id("address")).sendKeys("123 Test St");
        driver.findElement(By.id("phone")).sendKeys("1234567890");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));

        assert errorAlert.getText().contains("The name has already been taken.");
    }

    @Test
    public void testWeakPassword() {
        driver.get("http://127.0.0.1:8000/register");

        driver.findElement(By.id("name")).sendKeys("newuser");
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("password")).sendKeys("short");
        driver.findElement(By.id("password_confirmation")).sendKeys("short");
        driver.findElement(By.id("address")).sendKeys("123 Test St");
        driver.findElement(By.id("phone")).sendKeys("1234567890");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));

        assert errorAlert.getText().contains("The password field must be at least 8 characters.");
    }

    @Test
    public void testSuccessfulRegistration() {
        driver.get("http://127.0.0.1:8000/register");

        driver.findElement(By.id("name")).sendKeys("newuser");
        driver.findElement(By.id("email")).sendKeys("newuser@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("password_confirmation")).sendKeys("password123");
        driver.findElement(By.id("address")).sendKeys("123 Test St");
        driver.findElement(By.id("phone")).sendKeys("1234567890");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://127.0.0.1:8000/login"));

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals("http://127.0.0.1:8000/login");
    
    }
}
