import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;

public class ReasonModal extends BaseTest {
    //поле указание причины
    @FindBy (xpath="//select[contains(@class, 'form-control')]")
    WebElement reason;
    //поле указание основания
    @FindBy (xpath="(//div [contains(text(), 'Укажите основания изменений')]/parent::div//div)[14]//input")
    WebElement doc;
    //кнопка сохранение
    @FindBy (xpath="(//*[contains(text(), 'Сохранить')])[2]")
    WebElement saveReasonButton;


    public void reasonModal(WebDriver driver) {
        setTimeout();
        PageFactory.initElements(driver, this);
        // указание причины
        Select reason1 = new Select(reason);
        reason1.selectByVisibleText("Изменения по объекту");
        // указание основания
        doc.sendKeys("-");
        //сохранение документа основания
        saveReasonButton.click();
    }
}
