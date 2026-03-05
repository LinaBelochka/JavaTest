import helpers.TestValue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.Locale;
import java.util.Random;


public class BaseTest {

    @FindBy(id = "username")
    static WebElement username;
    @FindBy (id = "password")
    static WebElement password;
    @FindBy (xpath="//button[@class='login-button " + "bars-button mat-button _mat-animation-noopable']")
    static WebElement vhod;


    protected static  WebDriver driver;
    protected static WebDriverWait wait;
    static Random random = new Random();


    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        BaseSeleniumPage.setDriver(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(
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

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



//    // Генерация случайного float
//    public static float randomFloat = Math.round((10 + random.nextFloat() * 998000) * 100) / 100.0f;
//    // Форматирование числа: точка → заменяется на запятую
//    public static String randomNumber = String.format(Locale.ENGLISH, "%.2f", randomFloat).replace(',', '.');
//
//    // Генерация случайного long
//    public static long random10Digit = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_000L);
//    public static String randomNumber1 = String.valueOf(random10Digit);
//
//    // Генерация случайного long
//    public static long random10Digit2 = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_00L);
//    public static String randomNumber2 = String.valueOf(random10Digit);



}