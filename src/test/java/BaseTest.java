import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static WebDriver driver = new ChromeDriver();

    @AfterAll
    public static void kill(){
        driver.close();
        driver.quit();
    }

    public static  WebElement findByExpath(String xpath,WebDriver driver){
        return driver.findElement(By.xpath(xpath));

    }
}
