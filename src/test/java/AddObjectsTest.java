import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import java.text.SimpleDateFormat;
import java.util.Date;




public class AddObjectsTest extends BaseTest{

    private static Actions act;
    static LoginTest loginTest;
    static OknoOsnovaniy oknoOsnovaniy;
    static Fias fias;

    @BeforeClass
    public void beforeAll() {
        loginTest = new LoginTest();
        loginTest.authWithValidCredentials();
        oknoOsnovaniy = new OknoOsnovaniy();
        fias = new Fias();
        act = new Actions(BaseTest.driver);
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
                            }

    // вкладка объекты
    @FindBy (xpath="//a[@href='/bh/objects']")
     WebElement object;
    // раздел земельный участок
    @FindBy (xpath = "//*[contains(text(), ' Земельный участок ')]")
     WebElement landPlotTab;
    // кнопка добавить
    @FindBy (xpath = "//*[@ng-reflect-message='Добавить']")
     WebElement add;
    // поле кадастровый номер
    @FindBy (id = "ROBJECT_ADD_KADASTR_NO")
    WebElement kadastrNomer;
    // поле дата ввода даты
    @FindBy (xpath = "//*[@id='ROBJECT_ADD_DATEIN']")
     WebElement addDatain;
    // поле наименование
    @FindBy(id = "ROBJECT_ADD_NAME")
     WebElement addName;
    // поле площадь общая
    @FindBy(id ="ROBJECT_ADD_PL")
    WebElement addPlCommon;
    // список категория земель
    @FindBy(xpath = "//div[@ng-reflect-message='Категория земель']/parent::div//button[@title='Открыть справочник']")
    WebElement katZemList;
    // элемент категория земель
    @FindBy(xpath = "//app-reference//div[contains(text(),'Земли сельскохозяйственного назначения')]")
    WebElement katZem;
    // кнопка ок
    @FindBy(xpath = "//button[text()=\"ОК\"]")
    WebElement ok;
    // список октмо
    @FindBy (xpath = "//input[@id='ROBJECT_ADD_OKTMO_REG']/parent::div/div/button[@title='Открыть справочник']")
    WebElement oktmolist;
    // элемент октмо
    @FindBy (xpath = "//*[contains(text(), 'Удмуртская Республика (ОКТМО)')]")
    WebElement oktmo;
    // кнопка сохранить в окне добавления объекта
    @FindBy(xpath = "//*[contains(text(), 'Сохранить')]")
     WebElement save;
    // поле балансовая стоимость
    @FindBy(id="ROBJECT_ADD_COMIS_COAST")
    WebElement comisCoast;
    // поле кадастровая стоимость
    @FindBy(id="ROBJECT_ADD_KADASTR_COAST")
    WebElement kadastrCoast;

    // раздел недвижимое имущество
    @FindBy (xpath ="//*[contains(text(), ' Недвижимое имущество ')]" )
    WebElement realEstate;
    // список адрес
    @FindBy(xpath = "//*[@id=\"ROBJECT_ADD_ADRESS\"]/parent::div//button[@title=\"Открыть справочник\"]")
    WebElement adressList;

    @Test(description = "добавление объекта земельный участок")
    public void addZU() {

        object.click();
        landPlotTab.click();
        waitForSpinnerToDisappear();
        add.click();
        kadastrNomer.sendKeys("89:10:010209:778");
        addDatain.sendKeys("01.01.1999");
        addName.sendKeys("Земельный участок автотест" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        addPlCommon.sendKeys("1000");
        katZemList.click();
        katZem.click();
        ok.click();
        comisCoast.sendKeys("2000");
        act.scrollToElement(kadastrCoast).perform();
        kadastrCoast.sendKeys("3000");
        act.scrollToElement(oktmolist).perform();
        oktmolist.click();
        sleep();
        act.doubleClick(oktmo).perform();
        save.click();
        oknoOsnovaniy.Osnovanie(driver);
    }

    @Test(description = "добавление недвижимого имущества")
    public void addNedvizhimoe() {
        object.click();
        realEstate.click();
        waitForSpinnerToDisappear();
        add.click();
        adressList.click();
        fias.fias(driver);
        addName.sendKeys("Недвижимое имущество автотест"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        save.click();
        oknoOsnovaniy.Osnovanie(driver);

}
    // раздел движимое имущество
    @FindBy(xpath = "//div[contains(text(), 'Движимое имущество')]//mat-icon")
    WebElement isMovable;
    // раздел автотранспорт
    @FindBy(xpath = "//mat-tree-node/li[contains(text(), 'Автотранспорт')]")
    WebElement avto;


    @Test(description = "добавление объекта движимое имущество Автотранспорт")
    public void addDvizhimoe(){
        object.click();
        isMovable.click();
        avto.click();
        waitForSpinnerToDisappear();
        add.click();
        addName.sendKeys("Авто"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        save.click();
        oknoOsnovaniy.Osnovanie(driver);

    }

    }


