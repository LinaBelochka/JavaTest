import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import java.text.SimpleDateFormat;
import java.util.Date;




public class AddObjectsTest extends BaseTest{

    private static Actions act;
    static AutorisationTest autorisationTest;
    static OknoOsnovaniy oknoOsnovaniy;
    static Fias fias;

    @BeforeClass
    public void beforeAll() {
        autorisationTest = new AutorisationTest();
        autorisationTest.authWithValidCredentials();
        oknoOsnovaniy = new OknoOsnovaniy();
        fias = new Fias();
        act = new Actions(BaseTest.driver);
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
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

    @FindBy(xpath = "//div[@ng-reflect-message='Категория земель']/parent::div//button[@title='Открыть справочник']")
    WebElement katZemList;
    @FindBy(xpath = "//app-reference//div[contains(text(),'Земли сельскохозяйственного назначения')]")
    WebElement katZem;

    @FindBy(xpath = "//button[text()=\"ОК\"]")
    WebElement ok;

    @FindBy (xpath = "//input[@id='ROBJECT_ADD_OKTMO_REG']/parent::div/div/button[@title='Открыть справочник']")
    WebElement oktmolist;
    @FindBy (xpath = "//*[contains(text(), 'Удмуртская Республика (ОКТМО)')]")
    WebElement oktmo;

    @FindBy(xpath = "//*[contains(text(), 'Сохранить')]")
     WebElement save;

    @FindBy(id="ROBJECT_ADD_COMIS_COAST")
    WebElement comisCoast;

    @FindBy(id="ROBJECT_ADD_KADASTR_COAST")
    WebElement kadastrCoast;

    @Test(description = "добавление объекта земельный участок")
    public void addObject() {

        wait.until(ExpectedConditions.visibilityOf(object)).click();
        landPlotTab.click();
        waitForSpinnerToDisappear();
        add.click();
        wait.until(ExpectedConditions.visibilityOf(kadastrNomer)).sendKeys("89:10:010209:778");
        addDatain.sendKeys("01.01.1999");
        addName.sendKeys("Земельный участок автотест" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        wait.until(ExpectedConditions.visibilityOf(addPlCommon)).sendKeys("1000");
        katZemList.click();
        wait.until(ExpectedConditions.visibilityOf(katZem)).click();
        ok.click();
        comisCoast.sendKeys("2000");
        act.scrollToElement(kadastrCoast).perform();
        kadastrCoast.sendKeys("3000");
        act.scrollToElement(oktmolist).perform();
        oktmolist.click();
        act.doubleClick(oktmo).perform();
        save.click();
        oknoOsnovaniy.fillOsnovanie(driver);
    }


    @FindBy (xpath ="//*[contains(text(), ' Недвижимое имущество ')]" )
    WebElement realEstate;
    @FindBy(xpath = "//*[@id=\"ROBJECT_ADD_ADRESS\"]/parent::div//button[@title=\"Открыть справочник\"]")
    WebElement adressList;


    @Test(description = "добавление недвижимого имущества")
    public void addNedvizhimoe() {
        wait.until(ExpectedConditions.visibilityOf(object)).click();
        realEstate.click();
        waitForSpinnerToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(add));
        add.click();
        adressList.click();
        fias.fillFias(driver);
        addName.sendKeys("Недвижимое имущество автотест"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        save.click();
        oknoOsnovaniy.fillOsnovanie(driver);

}

    @FindBy(xpath = "//div[contains(text(), 'Движимое имущество')]//mat-icon")
    WebElement isMovable;
    @FindBy(xpath = "//mat-tree-node/li[contains(text(), 'Автотранспорт')]")
    WebElement avto;


    @Test(description = "добавление объекта движимое имущество Автотранспорт")
    public void add(){
        wait.until(ExpectedConditions.visibilityOf(object)).click();
        wait.until(ExpectedConditions.visibilityOf(isMovable)).click();
        wait.until(ExpectedConditions.visibilityOf(avto)).click();
        waitForSpinnerToDisappear();
        wait.until(ExpectedConditions.visibilityOf(add)).click();
        wait.until(ExpectedConditions.visibilityOf(addName)).sendKeys("Авто"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        save.click();
        oknoOsnovaniy.fillOsnovanie(driver);

    }

    }


