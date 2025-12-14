import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import helpers.TestValue;



public class LoginTest extends BaseTest {

    public final String VALID_LOG = "222";
    public final String VALID_PAS = "222";
    public final String INVALID_LOG = "789";
    public final String INVALID_PAS = "789";
    public final String LOGIN_PAGE = "http://localhost:"+ TestValue.PORT +"/bh/login?returnUrl=%2F";


    @FindBy(xpath = "//span[contains(text(), 'Выход ')]")
    WebElement exit;

    @Test //проверка авторизации с валидными данными
    void checkValidCredentials() {
        authWithValidCredentials();
        sleep();
        Assert.assertEquals(driver.getCurrentUrl(), TestValue.AUTH_PAGE);
        exit.click();
    }

    @Test //проверка авторизации с невалидными логином и паролем
    void checkInvalidCredentials() {
        BaseTest.setTimeout();
        visitAuthPage();
        inputCredentials(driver, INVALID_LOG, INVALID_PAS, false);
        BaseTest.setTimeout();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE);
    }
    @Test //проверка авторизации с валидным логином и невалидным паролем
    void checkInvalidCredantialsWithCorrectLoginIncorrectPassword(){
        BaseTest.setTimeout();
        visitAuthPage();
        inputCredentials(driver, VALID_LOG, INVALID_PAS, false);
        BaseTest.setTimeout();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE);
    }
    @Test //проверка авторизации с не валидным логином и валидным паролем
    void checkInvalidCredantialsWithCorrectPassIncorrectLogin(){
        BaseTest.setTimeout();
        visitAuthPage();
        inputCredentials(driver,INVALID_LOG, VALID_PAS, false);
        BaseTest.setTimeout();
        Assert.assertEquals(driver.getCurrentUrl(),LOGIN_PAGE);
    }
    @Test //проверка авторизации с пустыми логином и паролем
    void checkInvalidCredantialsEmptyLoginAndPassword(){

        BaseTest.setTimeout();
        visitAuthPage();
        String login = "";
        String password = "";
        inputCredentials(driver, login, password, false);
        BaseTest.setTimeout();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE);
        WebElement texth2 = driver.findElement(By.xpath("//div/h2"));
        WebElement texth4 = driver.findElement(By.xpath("//div/h4"));
        Assert.assertEquals(texth2.getText(), "БАРС-Балансодержатель");
        Assert.assertEquals(texth4.getText(), "Вход в систему");
        WebElement inputlog = driver.findElement(By.xpath("(//div[@class='invalid-feedback'])[1]/div"));
        WebElement inputpass = driver.findElement(By.xpath("(//div[@class='invalid-feedback'])[2]/div"));
        Assert.assertEquals(inputlog.getText(), "Введите имя пользователя");
        Assert.assertEquals(inputpass.getText(), "Введите пароль");
    }
    @Test //проверка чекбокса запомнить пользователя.
    void checkboxRememberUser(){
        sleep();
        visitAuthPage();
        inputCredentials(driver, VALID_LOG, VALID_PAS, true);
        driver.switchTo().newWindow(WindowType.TAB);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        String url = driver.switchTo().window((String) windowHandles[0]).getCurrentUrl();
        driver.close();
        driver.switchTo().window((String) windowHandles[1]);
        driver.get("http://localhost:"+ TestValue.PORT +"/bh/");
        sleep();
        Assert.assertEquals(driver.getCurrentUrl(), url);
        //(Ошибка нет возможности разлогиниться)
//        exit.click();
//        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE);
         }

    public void visitAuthPage() {
        driver.get(TestValue.AUTH_PAGE);
    }

    public void authWithValidCredentials() {
        visitAuthPage();
        setTimeout();
        inputCredentials(driver, VALID_LOG, VALID_PAS, false);
    }

    public void inputCredentials(WebDriver driver, String login, String password, boolean checkboxRememberUser) {
        WebElement loginEl = driver.findElement(By.id("username"));
        WebElement passwordEl = driver.findElement(By.id("password"));

        if (checkboxRememberUser) {
            WebElement checkbox = driver.findElement(By.xpath("//input[@id='isRememberUser']"));
            checkbox.click();
        }
        loginEl.sendKeys(login);
        passwordEl.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='login-button " +
                "bars-button mat-button _mat-animation-noopable']"));
        loginButton.click();
    }

}