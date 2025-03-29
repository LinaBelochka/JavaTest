import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AutorisationTest  {

    WebDriver driver = new ChromeDriver();

    @Test
    void checkValidCredentials() {


        visitAuthPage();
        setTimeout();
        String login = "222";
        String password = "222";
        inputCredentials(driver, login, password);
        String expectedUrl = "http://localhost:8080/bh/";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(driver.getCurrentUrl());
        Assertions.assertEquals(expectedUrl,driver.getCurrentUrl());
        driver.quit();
    }

    private void setTimeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    private void visitAuthPage() {
        driver.get("http://localhost:8080/bh/login?returnUrl=%2F");
    }


    @Test
    void checkInvalidCredentials() {

        setTimeout();
        visitAuthPage();
        String expectedUrl = "http://localhost:8080/bh/login?returnUrl=%2F";
        String login = "111";
        String password = "111";
        inputCredentials(driver, login, password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(expectedUrl,driver.getCurrentUrl());
        driver.quit();
    }



    private void inputCredentials(WebDriver driver, String login, String password) {
        WebElement loginEl = driver.findElement(By.id("username"));

        WebElement passwordEl = driver.findElement(By.id("password"));

        loginEl.sendKeys(login);
        passwordEl.sendKeys(password);
        WebElement vhod = driver.findElement(By.xpath("//button[@class='login-button " +
                "bars-button mat-button _mat-animation-noopable']"));
        vhod.click();
    }
}