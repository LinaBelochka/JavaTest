import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import halpers.TestValue;



public class AutorisationTest extends BaseTest {

    public final String VALIDLOG = "222";
    public final String VALIDPAS = "222";
    public final String INVALID_LOG = "789";
    public final String INVALID_PAS = "789";
    public final String LOGIN_PAGE = "http://localhost:"+ TestValue.PORT +"/bh/login?returnUrl=%2F";



    @Test
    void checkValidCredentials() {

        authWithValidCredentials();
        String expectedUrl = TestValue.AUTH_PAGE;
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
        BaseTest.setTimeout();
        String login = VALIDLOG;
        String password = VALIDPAS;
        inputCredentials(driver, login, password, false);
    }


    @Test
    void checkInvalidCredentials() {

        BaseTest.setTimeout();
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

        BaseTest.setTimeout();
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

        BaseTest.setTimeout();
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

        BaseTest.setTimeout();
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
        BaseTest.setTimeout();
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
        driver.get("http://localhost:"+ TestValue.PORT +"/bh/");
        WebElement exit = driver.findElement(By.xpath("//nav//i"));
        exit.click();
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
         }


    public void visitAuthPage() {
        driver.get(TestValue.AUTH_PAGE);

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