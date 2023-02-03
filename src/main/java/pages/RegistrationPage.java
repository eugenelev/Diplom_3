package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;

    // Локатор кнопки Зарегистрироваться
    public By buttonRegistration = By.xpath("//button[text()='Зарегистрироваться']");

    // Локатор кнопки-ссылки Войти
    public By buttonLinkLogin = By.xpath("//*[contains(text(),'Войти')]");

    // Локатор для ошибки регистрации
    public By errorText = By.xpath("//*[starts-with(@class, 'input__error')]");

    public By titleRegistration = By.xpath("//*[contains(text(),'Регистрация')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegistrationButton() {
        driver.findElement(buttonRegistration).click();
    }

    public String getErrorTextRegistration() {
        String text = driver.findElement(errorText).getText();
        return text;
    }

    public void waitForTitleRegistration() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(titleRegistration));
    }

        public void clickLoginButtonLink() {
        driver.findElement(buttonLinkLogin).click();
    }
}
