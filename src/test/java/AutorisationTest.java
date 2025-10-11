import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AutorisationTest  {

   public WebDriver driver = new ChromeDriver();
    public String validLog = "222";
    public  String validPas = "222";
    public  String invalidLog = "789";
    public  String invalidpas = "789";
    public String port = "8081";
    public  String loginPage = "http://localhost:"+port+"/bh/login?returnUrl=%2F";
    public  String urlAuthorizedUser = "http://localhost:"+port+"/bh/";






    @Test
    void checkValidCredentials() {

        authWithValidCredentials();
        String expectedUrl = urlAuthorizedUser;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(driver.getCurrentUrl());
        assertEquals(expectedUrl,driver.getCurrentUrl());
        driver.quit();
    }

    public void authWithValidCredentials() {
        visitAuthPage();
        setTimeout();
        String login = validLog;
        String password = validPas;
        inputCredentials(driver, login, password, false);
        //change1
    }


    @Test
    void checkInvalidCredentials() {

        setTimeout();
        visitAuthPage();
        String expectedUrl = loginPage;
        String login = invalidLog;
        String password = invalidpas;
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedUrl,driver.getCurrentUrl());
        driver.quit();
    }
    @Test
    void checkInvalidCredantialsCorrectLog(){

        setTimeout();
        visitAuthPage();
        String expectedUrl = loginPage;
        String login = validLog;
        String password = invalidpas;
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedUrl,driver.getCurrentUrl());
        driver.quit();
    }
    @Test
    void checkInvalidCredantialsCorrectPass(){

        setTimeout();
        visitAuthPage();
        String expectedUrl = loginPage;
        String login = invalidLog;
        String password = validPas;
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedUrl,driver.getCurrentUrl());
        driver.quit();
    }
    @Test
    void checkInvalidCredantialsEmpty(){

        setTimeout();
        visitAuthPage();
        String expectedUrl = loginPage;
        String login = "";
        String password = "";
        inputCredentials(driver, login, password, false);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedUrl,driver.getCurrentUrl());
        String expectedLogText = "Введите имя пользователя";
        String expectedPassText = "Введите пароль";
        String expectedTexth2 = "БАРС-Балансодержатель";
        String expectedTexth4 = "Вход в систему";
        WebElement texth2 = driver.findElement(By.xpath("//div/h2"));
        WebElement texth4 = driver.findElement(By.xpath("//div/h4"));
        assertEquals(expectedTexth2,texth2.getText());
        assertEquals(expectedTexth4,texth4.getText());
        WebElement inputlog = driver.findElement(By.xpath("(//div[@class='invalid-feedback'])[1]/div"));
        WebElement inputpass = driver.findElement(By.xpath("(//div[@class='invalid-feedback'])[2]/div"));
        assertEquals(expectedLogText,inputlog.getText());
        assertEquals(expectedPassText,inputpass.getText());
        driver.quit();
    }
    @Test
    void checkboxRememberUser(){
        setTimeout();
        visitAuthPage();
        String login = validLog;
        String password = validPas;
        boolean checkbox =true;
        String expectedUrl = loginPage;
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
        driver.get("http://localhost:"+port+"/bh/");
        WebElement exit = driver.findElement(By.xpath("//nav//i"));
        exit.click();
        assertEquals(expectedUrl,driver.getCurrentUrl());

        driver.quit();

    }


    private void setTimeout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void visitAuthPage() {
        driver.get("http://localhost:"+port+"/bh/");
        driver.manage().window().maximize();
    }


    public void inputCredentials(WebDriver driver, String login, String password, boolean checkboxRememberUser) {
        WebElement loginEl = driver.findElement(By.id("username"));

        WebElement passwordEl = driver.findElement(By.id("password"));
if (checkboxRememberUser){
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