import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.time.Duration;


public class BaseTest {
    protected static WebDriver driver;

    @BeforeTest
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        BaseSeleniumPage.setDriver(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();


    }

    public void waitForSpinnerToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'spinner-fon')]")
        ));
    }

    public static WebElement findByExpath(String xpath, WebDriver driver){
        return driver.findElement(By.xpath(xpath));

    }

}