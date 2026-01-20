import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar extends BaseTest {
    // кнопка редактирования
    @FindBy(xpath = "//*[@mattooltip=\"Редактировать\"]")
   static WebElement change;
    // кнопка добавить
    @FindBy (xpath = "//*[@ng-reflect-message='Добавить']")
    static WebElement add;
    // Конструктор для инициализации
    public NavBar(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    //скролл вправа
    @FindBy (xpath = "[class=\"ag-theme-balham ag-theme-balham-top\"]")
    static WebElement scroll;

}
