import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddObjectsTest extends BaseTest{

    private static Actions act;
    static LoginTest loginTest;
    static ReasonModal reasonModal;
    static Fias fias;
    static NavBar navBar;
    String text = "Решение суда";
    static RundomNumbers rundomNumbers;


    // получение количества активных элементов
    public Integer getCountActiveElements() {
        WebElement text  = driver.findElement(By.xpath("//*[@class=\"grid-active-info\"]"));
        String text1 = text.getText();
        String[] parts = text1.split(" ");
        String activeCountStr = parts[1];
        return Integer.parseInt(activeCountStr);
    }

    @BeforeClass
    public void beforeAll() {
        loginTest = new LoginTest();
        loginTest.authWithValidCredentials();
        reasonModal = new ReasonModal();
        fias = new Fias();
        act = new Actions(BaseTest.driver);
        PageFactory.initElements(driver, this);
        act = new Actions(driver);
        navBar = new NavBar(driver);
        rundomNumbers = new RundomNumbers();
                            }
     // площадь
     String pl = "2000.20";
    // вкладка объекты
    @FindBy (xpath="//a[@href='/bh/objects']")
     WebElement object;
    // раздел земельный участок
    @FindBy (xpath = "//*[contains(text(), ' Земельный участок ')]")
     WebElement landPlotTab;
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
    @FindBy(id ="ROBJECT_ADD_PL_COMMON")
    WebElement addPlCommon;
    // поле площадь при редактировании
    @FindBy(id ="ROBJECT_EDIT_PL_COMMON")   // ROBJECT_EDIT_PL
    WebElement addPlEdit;
    //площадь для сравнения
    @FindBy(xpath ="(//*[@col-id=\"PL_COMMON\"])[2]")
    WebElement plForCompare;
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
    @FindBy (xpath = "//input[@id='ROBJECT_ADD_OKTMO']/parent::div/div/button[@title='Открыть справочник']")
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
    //поле кадастровая стоимость для редактирования
    @FindBy(id="ROBJECT_EDIT_KADASTR_COAST")
    WebElement kadastrCoastEdit;
    // поле балансовая стоимость для сравнения
    @FindBy(xpath = "(//*[@col-id='KADASTR_COAST'])[2]")
    WebElement coastObject;
    //количестово действующих объектов


    // раздел недвижимое имущество
    @FindBy (xpath ="//*[contains(text(), ' Недвижимое имущество ')]" )
    WebElement realEstate;
    // список адрес
    @FindBy(xpath = "//*[@id=\"ROBJECT_ADD_ADRESS\"]/parent::div//button[@title=\"Открыть справочник\"]")
    WebElement adressList;

    // раздел движимое имущество
    @FindBy(xpath = "//div[contains(text(), 'Движимое имущество')]//mat-icon")
    WebElement isMovable;
    // раздел автотранспорт
    @FindBy(xpath = "//mat-tree-node/li[contains(text(), 'Автотранспорт')]")
    WebElement avto;


    @Test(description = "добавление объекта земельный участок/", alwaysRun=true)
    public void addZU() {
        object.click();
        sleep();
        landPlotTab.click();
        waitForSpinnerToDisappear();
        NavBar.add.click();
        kadastrNomer.sendKeys("89:10:089108:89");
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
        reasonModal.reasonModal(driver, text);
    }
    @Test(description = "добавление недвижимого имущества", alwaysRun=true)
    public void addNedvizhimoe() {
        object.click();
        realEstate.click();
        waitForSpinnerToDisappear();
        NavBar.add.click();
        adressList.click();
        fias.fias(driver);
        addName.sendKeys("Недвижимое имущество автотест"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        save.click();
        reasonModal.reasonModal(driver, text);
}
    @Test(description = "добавление объекта движимое имущество Автотранспорт", alwaysRun=true)
    public void addDvizhimoe(){
        object.click();
        isMovable.click();
        avto.click();
        waitForSpinnerToDisappear();
        NavBar.add.click();
        addName.sendKeys("Авто"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        save.click();
        reasonModal.reasonModal(driver, text);
    }
    @Test(description = "редактирование объекта, обязательное поле, с историей", alwaysRun=true)
    public void changeObject() {
        driver.navigate().refresh();
        PageFactory.initElements(driver, this);
        object.click();
        landPlotTab.click();
        NavBar.change.click();
        addPlEdit.clear();
        addPlEdit.sendKeys(RundomNumbers.randomNumberFloat);
        save.click();
        sleep();
        reasonModal.reasonModal(driver, text);
        sleep();
        Assert.assertEquals(plForCompare.getText(),RundomNumbers.randomNumberFloat);
    }
    @Test(description = "редактирование объекта, необязательное поле, без истории", alwaysRun=true)
    public void changeObject2() {
        driver.navigate().refresh();
        object.click();
        landPlotTab.click();
        NavBar.change.click();
        kadastrCoastEdit.clear();
        kadastrCoastEdit.sendKeys(String.valueOf(RundomNumbers.randomNumberFloat));
        save.click();
        sleep();
        Assert.assertEquals(coastObject.getText(), RundomNumbers.randomNumberFloat);
    }
    @Test (description = "Создание копии объекта", alwaysRun = true)
    public void copyObject() {
        object.click();
        landPlotTab.click();
        sleep();
        Integer countObj = getCountActiveElements()+1;
        NavBar.copyButton.click();
        sleep();
        save.click();
        reasonModal.reasonModal(driver, text);
        sleep();
        Assert.assertEquals(countObj, getCountActiveElements());
    }

}


