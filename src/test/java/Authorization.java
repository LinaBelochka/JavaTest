import helpers.TestValue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class Authorization extends BaseTest {

    @FindBy (id = "username")
    WebElement username;
    @FindBy (id = "password")
    WebElement password;
    @FindBy (xpath="//button[@class='login-button " + "bars-button mat-button _mat-animation-noopable']")
    WebElement vhod;

    @Test
    public  void logIn() {
        driver.get(TestValue.AUTH_PAGE);
        username.sendKeys(TestValue.USERNAME);
        password.sendKeys(TestValue.PASSWORD);
        vhod.click();
    }
}
