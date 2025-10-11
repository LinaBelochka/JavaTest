import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;


public class OknoOsnovaniy {

//    @FindBy (xpath = "//select[contains(@class, 'form-control')]")
//    WebElement reason;
//    @FindBy (xpath ="//*[contains(text(), 'Изменения по объекту')]" )
//    WebElement reason1;
//    @FindBy (xpath ="//*[@id=\"mat-dialog-5\"]/app-enter-history/div/div[2]/div[1]/div[2]/div[5]/div[2]/input" )
//    WebElement

    public void osnovanie(WebDriver driver) {
        WebElement reason = driver.findElement(By.xpath("//select[contains(@class, 'form-control')]"));
        //reason.click();
//        WebElement reason1 = driver.findElement(By.xpath("//*[contains(text(), 'Изменения по объекту')]"));
//        reason1.click();

        Select drpCountry = new Select(reason);
        drpCountry. selectByVisibleText("Изменения по объекту");



        WebElement doc = driver.findElement(By.xpath("//*[@id=\"mat-dialog-5\"]/app-enter-history/div/div[2]/div[1]/div[2]/div[5]/div[2]/input"));
        doc.sendKeys("-");

        WebElement saveOsnovanie = driver.findElement(By.xpath("(//*[contains(text(), 'Сохранить')])[2]"));
        saveOsnovanie.click();
    }
}
