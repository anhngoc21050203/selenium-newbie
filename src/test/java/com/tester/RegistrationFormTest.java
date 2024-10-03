package com.tester;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationFormTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Cấu hình ChromeDriver
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:5501/app/Views/User/company-info/test.html"); // Thay đổi đường dẫn tới file HTML
    }

    @Test
    public void testValidRegistration() {
        fillForm("Nguyễn Văn A", "email@example.com", "Password1@", "Password1@", "male", "2000-01-01");
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Đăng ký']"));
        submitButton.click();
        // Xác nhận đăng ký thành công (có thể cần kiểm tra thông báo thành công)
    }

    @Test
    public void testInvalidEmail() {
        fillForm("Nguyễn Văn A", "invalid-email", "Password1@", "Password1@", "male", "2000-01-01");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        // Xác nhận thông báo lỗi cho email không hợp lệ
    }

    @Test
    public void testWeakPassword() {
        fillForm("Nguyễn Văn A", "email@example.com", "weakpass", "weakpass", "male", "2000-01-01");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        // Xác nhận thông báo lỗi cho mật khẩu yếu
    }

    @Test
    public void testPasswordMismatch() {
        fillForm("Nguyễn Văn A", "email@example.com", "Password1@", "Password2@", "male", "2000-01-01");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        // Xác nhận thông báo lỗi cho xác nhận mật khẩu không khớp
    }

    @Test
    public void testResetButton() {
        fillForm("Nguyễn Văn A", "email@example.com", "Password1@", "Password1@", "male", "2000-01-01");
        driver.findElement(By.xpath("//button[text()='Reset']")).click();
        // Xác nhận các trường đã được reset về trạng thái trống
    }

    @Test
    public void testCloseButton() {
        driver.findElement(By.xpath("//button[text()='Thoát']")).click();
        // Xác nhận cửa sổ đã được đóng (khó kiểm tra tự động)
    }

    private void fillForm(String fullName, String email, String password, String confirmPassword, String gender, String dob) {
        driver.findElement(By.id("fullname")).sendKeys(fullName);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirm_password")).sendKeys(confirmPassword);
        if (gender.equals("male")) {
            driver.findElement(By.id("male")).click();
        } else {
            driver.findElement(By.id("female")).click();
        }
        driver.findElement(By.id("dob")).sendKeys(dob);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

