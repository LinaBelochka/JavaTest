import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;

public class OknoOsnovaniy extends BaseTest {

    @FindBy (xpath="//select[contains(@class, 'form-control')]")
    WebElement reason;
    @FindBy (xpath="(//div [contains(text(), 'Укажите основания изменений')]/parent::div//div)[14]//input")
    WebElement doc;
    @FindBy (xpath="(//*[contains(text(), 'Сохранить')])[2]")
    WebElement saveOsnovanie;


    public void fillOsnovanie(WebDriver driver) {

        PageFactory.initElements(driver, this);

        Select reason1 = new Select(reason);
        reason1.selectByVisibleText("Изменения по объекту");

        doc.sendKeys("-");

        saveOsnovanie.click();
    }
}
