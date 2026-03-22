import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DocTest extends BaseTest {

    private static Actions act;
    static LoginTest loginTest;
    static ReasonModal reasonModal;
    static Fias fias;
    static NavBar navBar;
    String text = "Решение суда";
    static RundomNumbers rundomNumbers;

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

    // === Элементы страницы ===
    @FindBy (xpath="//a[@href='/bh/documents']")
    WebElement document;
    @FindBy (xpath = "//*[contains(text(), ' Приказ ')]")
    WebElement prikaz;
    @FindBy(xpath = "//div[@ng-reflect-message='Наименование документа']/parent::div//button[@title='Открыть справочник']")
    WebElement name;
    @FindBy(id = "RDOCUMENT_EDIT_DOC_NO")
    WebElement num;
    @FindBy(id = "RDOCUMENT_COPY_DOC_NO")
    WebElement copyNum;
    @FindBy(xpath = "//app-reference//div[contains(text(),' уведомление1504')]")
    WebElement getName;
    @FindBy(xpath = "//button[text()=\"ОК\"]")
    WebElement ok;
    @FindBy(xpath = "//*[contains(text(), 'Сохранить')]")
    WebElement save;
    @FindBy(xpath = "//i[@class='fas fa-file-upload']")
    WebElement download;
    @FindBy (xpath = "//*[contains(text(), 'Из реестра')]")
    WebElement reestr;
    @FindBy (xpath = "//span[contains(text(), 'Выбор')]")
    WebElement choice;
    @FindBy (xpath = "//*[contains(text(), 'ОК')]")
    WebElement okArh;
    @FindBy (xpath = "//*[contains(text(), 'Закрыть')]")
    WebElement close;


    @Test(description = "ДОБАВЛЕНИЕ ДОКУМЕНТА")
    public void addDoc(){
    document.click();
    prikaz.click();
    NavBar.add.click();
    name.click();
    getName.click();
    ok.click();
    save.click();
    }

    @Test(description = "РЕДАКТИРОВАНИЕ ДОКУМЕНТА")
    public void changeDoc(){
    document.click();
    prikaz.click();
    NavBar.change.click();
    num.clear();
    num.sendKeys(RundomNumbers.randomNumber9);
    save.click();
    }
    @Test(description = "КПИРОВАНИЕ ДОКУМЕНТА")
    public void copyDoc() {
        document.click();
        prikaz.click();
        NavBar.copyButton.click();
        BaseTest.sleep();
        copyNum.clear();
        copyNum.sendKeys("копия"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        save.click();
        sleep();
    }
    @Test(description = "добавление документа в архив документов из реестра")
    public void addArhiveDoc() {
        document.click();
        prikaz.click();
        NavBar.arhiveDoc.click();
        BaseTest.sleep();
        download.click();
        BaseTest.sleep();
        reestr.click();
        BaseTest.sleep(2000);
        choice.click();
        BaseTest.sleep(2000);
        okArh.click();
        close.click();
    }


}