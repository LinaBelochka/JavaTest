import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar extends BaseTest {
    // для инициализации
    public NavBar(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    // кнопка редактирования
    @FindBy(xpath = "//*[@mattooltip=\"Редактировать\"]")
    public static WebElement change;
    // кнопка добавить
    @FindBy (xpath = "//*[@ng-reflect-message='Добавить']")
    public static WebElement add;
    //кнопка для копирования
    @FindBy (xpath = "//*[@mattooltip='Копия']")
    public static WebElement copyButton;

    //кнопка для сохранения в окне редактирования
    @FindBy(xpath = "//*[contains(text(), 'Сохранить')]")
    public static WebElement save;





}
