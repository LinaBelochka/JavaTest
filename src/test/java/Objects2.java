import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;


public class Objects2 {

    Actions act = new Actions(autorisationTest.driver);
    static AutorisationTest autorisationTest;
    static OknoOsnovaniy oknoOsnovaniy;




    @BeforeAll
    static void beforeAll() {
        autorisationTest = new AutorisationTest();
        autorisationTest.authWithValidCredentials();
        oknoOsnovaniy = new OknoOsnovaniy();}
    @AfterAll
    static void afterAll(){
        autorisationTest.driver.close();
        autorisationTest.driver.quit();
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

    @FindBy(id ="ROBJECT_ADD_PL")
    WebElement addPlCommon;

    @FindBy(xpath = "(//*[@title=\"Открыть справочник\"])[3]")
    WebElement katZemList;
    @FindBy(xpath = "//*[contains(text(),'Земли сельскохозяйственного назначения')]")
    WebElement katZem;

    @FindBy(xpath = "//button[text()=\"ОК\"]")
    WebElement ok;

    @FindBy (xpath = "(//*[@title=\"Открыть справочник\"])[5]")
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
    public void addObject(){
        PageFactory.initElements(autorisationTest.driver, this);

        WebDriver driver = autorisationTest.driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        for (int i = 0; i < 10; i++) {


        /**добавление объекта земельный участок*/
        object.click();
        landPlotTab.click();
        add.click();
        kadastrNomer.sendKeys("89:10:010209:778");
        addDatain.sendKeys("01.01.1999");
        addName.sendKeys("Земельный участок 777");
        addPlCommon.sendKeys("1000");
        katZemList.click();

        katZem.click();
        ok.click();
                //act.doubleClick(katZem);
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
        oknoOsnovaniy.osnovanie(driver);}
    }

    @FindBy (xpath ="//*[contains(text(), ' Недвижимое имущество ')]" )
    WebElement realEstate;
    @FindBy(xpath = "//*[@id=\"mat-dialog-6\"]/app-add-edit-reestr-element/mat-dialog-content/app-reestr-element/div/div/div[1]/div[1]/attr-template/div/div/div/button[2]")
    WebElement addAdress;
    @FindBy(xpath = "//*[@id=\"mat-dialog-7\"]/app-ref-fias-component/div[2]/div[1]/table/tr[1]/td[2]")
    WebElement region;

    @Test
public void addNedvizhimoe(){
    PageFactory.initElements(autorisationTest.driver, this);

    WebDriver driver = autorisationTest.driver;

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    /**добавление недвижимого имущества*/
    realEstate.click();
    add.click();
    addAdress.click();
    region.click();
    region.sendKeys("1321");

}
    }


