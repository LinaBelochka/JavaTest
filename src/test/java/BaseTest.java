import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import java.util.Random;


public class BaseTest {

    protected static  WebDriver driver;
    protected static WebDriverWait wait;
    Random random = new Random();
    protected static JavascriptExecutor js;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        BaseSeleniumPage.setDriver(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;

    }

    @AfterClass
    //закрытие браузера после теста
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
    //ожидание
    public static void setTimeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    //ожидание исчезновения спиннера
    public static void waitForSpinnerToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'spinner-fon')]")));
    }
    //поиск по xpath
    public static WebElement findByXpath(String xpath, WebDriver driver){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }

    public static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}