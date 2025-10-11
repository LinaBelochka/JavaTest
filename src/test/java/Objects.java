import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Objects {
    Actions act = new Actions(autorisationTest.driver);
    static AutorisationTest autorisationTest;
    static OknoOsnovaniy oknoOsnovaniy;


    @BeforeAll
    static void beforeAll() {
        autorisationTest = new AutorisationTest();
        autorisationTest.authWithValidCredentials();
        oknoOsnovaniy = new OknoOsnovaniy();

    }

//    @AfterAll
//    static void afterAll() {
//        autorisationTest.driver.quit();
//    }
//    void addZemelUchastok(){
//
//       String kadastrNomer1 = "89:10:010209:777";
//       String addDatain1 ="01.01.1999";
//       String addName1  = "Земельный участок 777";
//       String addPlCommon1 = "1000";
//       WebElement element1 = landPlotTab;
//
//       addObject( element1, addDatain1,  addName1);
//       kadastrNomer.sendKeys(kadastrNomer1);
//       addPlCommon.sendKeys(addPlCommon1);
//       save.click();
//
//    }
//
//    @Test
//    void addNedvizhImushestvo(){
//        String kadastrNomer1 = "89:10:010209:777";
//        String addDatain1 ="01.01.1999";
//        String addName1  = "Земельный участок 777";
//        String addPlCommon1 = "1000";
//        WebElement element1 = realEstate;
//        addObject(element1, addDatain1,  addName1);
//        save.click();
//
//    }


    @Test
    public void addObject() {

        /**авторизация в бх  */
         WebDriver driver = autorisationTest.driver;

         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        /**добавление объекта земельный участок*/
        WebElement object = driver.findElement(By.xpath("(//a[@href='/bh/objects'])"));
        object.click();

        WebElement landPlotTab = driver.findElement(By.xpath("//*[contains(text(), ' Земельный участок ')]"));
        landPlotTab.click();


         WebElement add = driver.findElement(By.xpath("//*[@ng-reflect-message='Добавить']"));
        add.click();

        WebElement kadastrNomer = driver.findElement(By.id("ROBJECT_ADD_KADASTR_NO"));
        kadastrNomer.sendKeys("89:10:010209:777");

        WebElement addDatain = driver.findElement(By.xpath("//*[@id='ROBJECT_ADD_DATEIN']"));
        addDatain.sendKeys("01.01.1999");

        WebElement addName = driver.findElement(By.id("ROBJECT_ADD_NAME"));
        addName.sendKeys("Земельный участок 777");

        WebElement addPlCommon = driver.findElement(By.id("ROBJECT_ADD_PL_COMMON"));
        addPlCommon.sendKeys("1000");

        WebElement oktmolist = driver.findElement(By.xpath("//*[@id=\"mat-dialog-1\"]/app-add-edit-reestr-element/mat-dialog-content/app-reestr-element/div/div/div[1]/div[7]/attr-template/div/div/div/button[2]"));
        oktmolist.click();

        WebElement oktmo = driver.findElement(By.xpath("//*[contains(text(), 'Ямало-Ненецкий автономный округ (ОКТМО)')]"));
        act.doubleClick(oktmo).perform();

        WebElement save = driver.findElement(By.xpath("//*[contains(text(), 'Сохранить')]"));
        save.click();

        oknoOsnovaniy.osnovanie(driver);

           /**добавление объекта недвижимое имущество*/
         WebElement realEstate = driver.findElement(By.xpath("//*[contains(text(), ' Недвижимое имущество ')]"));
         realEstate.click();
         add.click();
         WebElement addAdress =driver.findElement(By.xpath("//*[@id=\"mat-dialog-6\"]/app-add-edit-reestr-element/mat-dialog-content/app-reestr-element/div/div/div[1]/div[1]/attr-template/div/div/div/button[2]"));
         addAdress.click();
         WebElement region =driver.findElement(By.xpath("//*[@id=\"mat-dialog-7\"]/app-ref-fias-component/div[2]/div[1]/table/tr[1]/td[2]"));
         region.click();

         region.sendKeys("1321");
//         WebElement adressAltay = driver.findElement(By.xpath("[ng-reflect-result=\"Алтайский край\"]"));
//         adressAltay.click();

    }
}




//    public void addCar() {
//
//        /**авторизация в бх  */
//        WebDriver driver = autorisationTest.driver;
//
//        WebElement object = driver.findElement(By.xpath("(//a[@href='/bh/objects'])"));
//        object.click();
//
//
//        WebElement add = driver.findElement(By.xpath("//*[@ng-reflect-message='Добавить']"));
//        add.click();
//
//        WebElement addDatain = driver.findElement(By.xpath("//*[@id='ROBJECT_ADD_DATEIN']"));
//        addDatain.sendKeys("01.01.1999");
//
//        WebElement addName = driver.findElement(By.id("ROBJECT_ADD_NAME"));
//        addName.sendKeys("Земельный участок 777");
//
//        WebElement addPlCommon = driver.findElement(By.id("ROBJECT_ADD_PL_COMMON"));
//        addPlCommon.sendKeys("1000");
//
//        WebElement oktmolist = driver.findElement(By.xpath("(//*[@class='fas fa-list'])[6]"));
//        oktmolist.click();
//
//        WebElement oktmo = driver.findElement(By.xpath("//*[contains(text(), 'Ямало-Ненецкий автономный округ (ОКТМО)')]"));
//        act.doubleClick(oktmo).perform();
//
//        WebElement save = driver.findElement(By.xpath("//*[contains(text(), 'Сохранить')]"));
//        save.click();
//
//        oknoOsnovaniy.osnovanie(driver);



