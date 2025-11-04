import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.time.Duration.ofSeconds;



public class AddObjectsTest extends BaseTest{



    private static Actions act;
    static AutorisationTest autorisationTest;
    static OknoOsnovaniy oknoOsnovaniy;
    static Fias fias;

//    public AddObjectsTest() {
//        act = new Actions(BaseTest.driver);
//    }

    @BeforeClass
    static void beforeAll() {
        autorisationTest = new AutorisationTest();
        autorisationTest.authWithValidCredentials();
        oknoOsnovaniy = new OknoOsnovaniy();
        fias = new Fias();
        act = new Actions(BaseTest.driver);
            }



    @FindBy (xpath="//a[@href='/bh/objects']")
     WebElement object;

    @FindBy (xpath = "//*[contains(text(), ' Земельный участок ')]")
     WebElement landPlotTab;

    @FindBy (xpath = "//*[@ng-reflect-message='Добавить']")
     WebElement add;

    @FindBy (id = "ROBJECT_ADD_KADASTR_NO")
    WebElement kadastrNomer;

    @FindBy (xpath = "//*[@id='ROBJECT_ADD_VVOD_DATE']")
     WebElement addDatain;

    @FindBy(id = "ROBJECT_ADD_NAME")
     WebElement addName;

    @FindBy(id ="ROBJECT_ADD_PL_COMMON")
    WebElement addPlCommon;

    @FindBy(xpath = "//div[@ng-reflect-message='Категория земель']/parent::div//button[@title='Открыть справочник']")
    WebElement katZemList;
    @FindBy(xpath = "//*[contains(text(),'Земли сельскохозяйственного назначения')]")
    WebElement katZem;

    @FindBy(xpath = "//button[text()=\"ОК\"]")
    WebElement ok;

    @FindBy (xpath = "//input[@id='ROBJECT_ADD_OKTMO']/parent::div/div/button[@title='Открыть справочник']")
    WebElement oktmolist;

    @FindBy (xpath = "//*[contains(text(), 'Удмуртская Республика (ОКТМО)')]")
    WebElement oktmo;

    @FindBy(xpath = "//*[contains(text(), 'Сохранить')]")
     WebElement save;

    @FindBy(id="ROBJECT_ADD_COMIS_COAST")
    WebElement comisCoast;

    @FindBy(id="ROBJECT_ADD_KADASTR_COAST")
    WebElement kadastrCoast;

    @Test
    /**добавление объекта земельный участок*/
    public void addObject() {
        PageFactory.initElements(driver, this);

        WebDriver driver = BaseTest.driver;

        driver.manage().timeouts().implicitlyWait(ofSeconds(5));

        object.click();
        landPlotTab.click();
        waitForSpinnerToDisappear();
        add.click();
        kadastrNomer.sendKeys("89:10:010209:778");
        addDatain.sendKeys("01.01.1999");
        addName.sendKeys("Земельный участок автотест");
        addPlCommon.sendKeys("1000");
        katZemList.click();
        katZem.click();
        ok.click();
        comisCoast.sendKeys("2000");
        new Actions(driver)
                .scrollToElement(kadastrCoast)
                .perform();
        kadastrCoast.sendKeys("3000");
        new Actions(driver)
                .scrollToElement(oktmolist)
                .perform();
        oktmolist.click();
        act.doubleClick(oktmo).perform();
        save.click();
        oknoOsnovaniy.fillOsnovanie(driver);}


    @FindBy (xpath ="//*[contains(text(), ' Недвижимое имущество ')]" )
    WebElement realEstate;
    @FindBy(xpath = "//*[@id=\"ROBJECT_ADD_ADRESS\"]/parent::div//button[@title=\"Открыть справочник\"]")
    WebElement adressList;


    @Test  /**добавление недвижимого имущества*/
    public void addNedvizhimoe() {
        PageFactory.initElements(driver, this);

        WebDriver driver = BaseTest.driver;

       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        object.click();
        realEstate.click();
        waitForSpinnerToDisappear();
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      wait.until(ExpectedConditions.elementToBeClickable(add));

        add.click();
        adressList.click();

        fias.fillFias(driver);
        addName.sendKeys("Недвижимое имущество автотест");
        save.click();
        oknoOsnovaniy.fillOsnovanie(driver);

}

    @Test
    public void add(){

    }

    }


