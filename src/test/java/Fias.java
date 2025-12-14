import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class  Fias extends BaseTest{
    public void fias(WebDriver driver){
        //выбор региона
        WebElement region = findByXpath("//*[contains(text(), 'Регион')]/parent::tr//input", driver);
        region.click();
        WebElement region1 = findByXpath("//ngb-highlight[contains(text(), 'Республика Алтай')]",driver);
        region1.click();
        // выбор города
        WebElement gorod = findByXpath("//*[contains(text(), 'Город')]/parent::tr//input",driver);
        gorod.sendKeys("г Горно-Алтайск");
        WebElement ok = findByXpath("(//*[contains(text(),\"ОК\")])[2]",driver);
        ok.click();
    }
}
