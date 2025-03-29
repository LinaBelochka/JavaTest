
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.*;



import java.time.Duration;




public class Autorisation {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("http://localhost:8080/bh/login?returnUrl=%2F");

        WebElement login = driver.findElement(By.id("username"));
        login.sendKeys("222");
WebElement password = driver.findElement(By.id("password"));
password.sendKeys("222");
WebElement vhod = driver.findElement(By.xpath("//button[@class='login-button " +
        "bars-button mat-button _mat-animation-noopable']"));
vhod.click();

driver.close();


    }



}
