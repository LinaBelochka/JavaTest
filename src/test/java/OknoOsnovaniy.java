import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class OknoOsnovaniy extends BaseTest {



    public void fillOsnovanie(WebDriver driver) {
        WebElement reason = findByExpath("//select[contains(@class, 'form-control')]", driver);
        //reason.click();
//        WebElement reason1 = driver.findElement(By.xpath("//*[contains(text(), 'Изменения по объекту')]"));
//        reason1.click();
        Select reason1 = new Select(reason);
        reason1.selectByVisibleText("Изменения по объекту");



        WebElement doc = findByExpath("(//div [contains(text(), 'Укажите основания изменений')]/parent::div//div)[14]//input",driver);
        doc.sendKeys("-");


        WebElement saveOsnovanie =findByExpath("(//*[contains(text(), 'Сохранить')])[2]",driver);
        saveOsnovanie.click();
    }
}
