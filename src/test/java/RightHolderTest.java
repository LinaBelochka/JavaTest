import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RightHolderTest extends BaseTest {
LoginTest loginTest = new LoginTest();
ReasonModal reasonModal = new ReasonModal();
NavBar navBar;
Fias fias;
Actions act;




    @BeforeTest
    public void beforeTest() {
        loginTest.authWithValidCredentials();
        this.navBar = new NavBar(driver);
        fias = new Fias();
        act = new Actions(BaseTest.driver);
    }

    //inn for change
    @FindBy(id = "RCONTRAGENT_EDIT_INN")
    WebElement innChange;
    // inn for equals
    @FindBy(id = "RCONTRAGENT_INN")
    WebElement innEquals;
    //редактирование балансодержателя
    @FindBy(xpath ="//*[@mattooltip='Редактирование записи балансодержателя']")
    WebElement editBalanceHolder;
    //kpp for change
    @FindBy(id = "RCONTRAGENT_EDIT_KPP")
    WebElement kppChange;
    //kpp for equals
    @FindBy(id = "RCONTRAGENT_KPP")
    WebElement kppEquals;
    @FindBy(xpath = "//*[contains(text(), ' не соответствует маске')]")
    WebElement errorInputMask;
    @FindBy(xpath = "//*[contains(text(), 'Превышена максимальная длина строки')]")
    WebElement errorMaxLength;
    @FindBy(xpath = "//*[contains(@class,'update-button')]")
    WebElement completeChangeButton;
    @FindBy(xpath = "//*[contains(text(),'Внесение изменений окончено')]")
    WebElement changeCompletePlaceholder;



    @Test(description = "изменение обязательного атрибута, участвующий в истории", alwaysRun=true)
    public void changeRequiredAttributeHistory() {
        editBalanceHolder.click();
        innChange.clear();
        innChange.sendKeys(RundomNumbers.randomNumber10);
        navBar.save.click();
        reasonModal.reasonModal(driver, "Изменение по контрагенту");
        sleep(6000);
        Assert.assertEquals(RundomNumbers.randomNumber10, innEquals.getAttribute("value"));
        System.out.println(RundomNumbers.randomNumber10 + " " + innEquals.getAttribute("value"));

}

    @Test(description = "изменение обязательного атрибута, без истории", alwaysRun=true)
    public void changeRequiredAttributeNotHistory() {
        editBalanceHolder.click();
        kppChange.clear();
        kppChange.sendKeys(RundomNumbers.randomNumber9);
        Assert.assertEquals(kppChange.getDomAttribute("style"), "color: green;" );
        navBar.save.click();
        sleep();
        Assert.assertEquals(kppEquals.getAttribute("value"), RundomNumbers.randomNumber9 );
        System.out.println(RundomNumbers.randomNumber9 + " " + kppEquals.getAttribute("value"));

    }

    @Test(description = "Изменение обязательного атрибут, не верные символы" , alwaysRun=true)
    public void changeRequiredAttributeErrorSymbols() {
        editBalanceHolder.click();
        kppChange.clear();
        kppChange.sendKeys("jkhfb");
        Assert.assertEquals( kppChange.getDomAttribute("style"),"color: red;");
        navBar.save.click();
        sleep();
        errorInputMask.isDisplayed();
    }


    @Test(description="Изменение обязательного атрибута, c превышением количества символов", alwaysRun=true)
    public void changeRequiredAtrWithErrorMaxSymbols() {
        editBalanceHolder.click();
        kppChange.clear();
        kppChange.sendKeys("1234567890");
        navBar.save.click();
        errorMaxLength.isDisplayed();

        }

    @Test(description="Окончание внесение изменений")
    public void finishMakingChanges() {
       completeChangeButton.click();
       changeCompletePlaceholder.isDisplayed();
       sleep(3000);
    }



}
