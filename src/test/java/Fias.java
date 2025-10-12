import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Fias extends BaseTest{
    public void fillFias(WebDriver driver){
        WebElement region = findByExpath("//*[contains(text(), 'Регион')]/parent::tr//input", driver);
        region.click();
        WebElement region1 = findByExpath("//*[contains(text(), 'Астраханская область')]",driver);
        region1.click();
        WebElement gorod = findByExpath("//*[contains(text(), 'Город')]/parent::tr//input",driver);
        gorod.sendKeys("Астрахань");
        WebElement ok = findByExpath("(//*[contains(text(),\"ОК\")])[2]",driver);
        ok.click();
    }
}
