import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;


public class AutorisationTest extends BaseTest {

    public final String VALIDLOG = "222";
    public final String VALIDPAS = "222";
    public final String INVALID_LOG = "789";
    public final String INVALID_PAS = "789";
    public final String PORT = "8081";
    public final String LOGIN_PAGE = "http://localhost:"+ PORT +"/bh/login?returnUrl=%2F";
    public final  String URL_AUTHORIZED_USER = "http://localhost:"+ PORT +"/bh/";


    @Test
    void checkValidCredentials() {

        authWithValidCredentials();
        String expectedUrl = URL_AUTHORIZED_USER;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }

    public void authWithValidCredentials() {
        visitAuthPage();
        setTimeout();
        String login = VALIDLOG;
        String password = VALIDPAS;
        inputCredentials(driver, login, password, false);
    }


    @Test
    void checkInvalidCredentials() {

        setTimeout();
        visitAuthPage();
        String expectedUrl = LOGIN_PAGE;
        String login = INVALID_LOG;
        String password = INVALID_PAS;
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
     Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }
    @Test
    void checkInvalidCredantialsWithCorrectLoginIncorrectPassword(){

        setTimeout();
        visitAuthPage();
        String expectedUrl = LOGIN_PAGE;
        String login = VALIDLOG;
        String password = INVALID_PAS;
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }
 @Test
    void checkInvalidCredantialsWithCorrectPassIncorrectLogin(){

        setTimeout();
        visitAuthPage();
        String expectedUrl = LOGIN_PAGE;
        String login = INVALID_LOG;
        String password = VALIDPAS;
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
     Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }
   @Test
    void checkInvalidCredantialsEmptyLoginAndPassword(){

        setTimeout();
        visitAuthPage();
        String expectedUrl = LOGIN_PAGE;
        String login = "";
        String password = "";
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        String expectedLogText = "Введите имя пользователя";
        String expectedPassText = "Введите пароль";
        String expectedTexth2 = "БАРС-Балансодержатель";
        String expectedTexth4 = "Вход в систему";
        WebElement texth2 = driver.findElement(By.xpath("//div/h2"));
        WebElement texth4 = driver.findElement(By.xpath("//div/h4"));
        Assert.assertEquals(texth2.getText(), expectedTexth2);
        Assert.assertEquals(texth4.getText(), expectedTexth4);
        WebElement inputlog = driver.findElement(By.xpath("(//div[@class='invalid-feedback'])[1]/div"));
        WebElement inputpass = driver.findElement(By.xpath("(//div[@class='invalid-feedback'])[2]/div"));
        Assert.assertEquals(inputlog.getText(), expectedLogText);
        Assert.assertEquals(inputpass.getText(), expectedPassText);

    }
    @Test
    void checkboxRememberUser(){
        setTimeout();
        visitAuthPage();
        String login = VALIDLOG;
        String password = VALIDPAS;
        boolean checkbox =true;
        String expectedUrl = LOGIN_PAGE;
        WebElement loginEl = driver.findElement(By.id("username"));
        WebElement passwordEl = driver.findElement(By.id("password"));
        loginEl.sendKeys(login);
        passwordEl.sendKeys(password);
        driver.findElement(By.id("isRememberUser")).click();
        WebElement vhod = driver.findElement(By.xpath("//button[@class='login-button " +
                "bars-button mat-button _mat-animation-noopable']"));
        vhod.click();
        driver.switchTo().newWindow(WindowType.TAB);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[0]);
        driver.close();
        driver.switchTo().window((String) windowHandles[1]);
        driver.get("http://localhost:"+ PORT +"/bh/");
        WebElement exit = driver.findElement(By.xpath("//nav//i"));
        exit.click();
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
         }


    private void setTimeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void visitAuthPage() {
        driver.get("http://localhost:"+ PORT +"/bh/");

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
        WebElement vhod = driver.findElement(By.xpath("//button[@class='login-button " +
                "bars-button mat-button _mat-animation-noopable']"));
        vhod.click();
    }

}