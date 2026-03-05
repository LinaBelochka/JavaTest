import helpers.TestValue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseTest {

    @FindBy (id = "username")
    private WebElement username;
    @FindBy (id = "password")
    private WebElement password;
    @FindBy (xpath="//button[@class='login-button " + "bars-button mat-button _mat-animation-noopable']")
    private WebElement vhod;

    private WebDriver driver;
    private WebDriverWait wait;


    public  LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

    }
    public void login(WebDriver driver, WebDriverWait wait){
        this.driver.get(TestValue.AUTH_PAGE);
        PageFactory.initElements(this.driver, this);
        this.wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(TestValue.USERNAME);
        password.sendKeys(TestValue.PASSWORD);
        vhod.click();
    }

}
