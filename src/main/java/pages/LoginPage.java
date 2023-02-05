package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    LoginSignInFieldsPage loginSignInFieldsPage;
    private RecoveryPasswordPage recoveryPasswordPage;
    private WebDriver driver;

    // Локатор кнопки Войти
    public By buttonLogin = By.xpath("//button[text()='Войти']");

    public By titleLoginPage = By.xpath("//*[contains(text(),'Вход')]");

    // Локатор кнопки-ссылки Восстановить пароль
    public By buttonLinkRecoverPassword = By.xpath("//a[text()='Восстановить пароль']");


    public LoginPage(WebDriver driver, RecoveryPasswordPage recoveryPasswordPage, LoginSignInFieldsPage loginSignInFieldsPage) {
        this.driver = driver;
        this.recoveryPasswordPage = recoveryPasswordPage;
        this.loginSignInFieldsPage = loginSignInFieldsPage;
    }

    @Step("Заполнить поле 'Имя'")
    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Авторизоваться")
    public void loginUser(String email, String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(buttonLogin)));
        loginSignInFieldsPage.setFieldEmail(email);
        this.loginSignInFieldsPage.setFieldPassword(password);
        clickLoginButton();
    }

    @Step("Ожидаем заголовок 'Вход' на экране авторизации")
    public void waitForTitleLoginPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(titleLoginPage));
    }

    @Step("Нажать на кнопку 'Восстановить пароль'")
    public void clickRecoverPasswordButtonLink() {
        driver.findElement(buttonLinkRecoverPassword).click();
        recoveryPasswordPage.waitForTitleRecover();
    }

}
