package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecoveryPasswordPage {

    private WebDriver driver;

    // Локатор кнопки-ссылки Войти
    public By buttonLinkLogin = By.xpath("//a[text()='Войти']");

    public By titleRecoverPassword = By.xpath("//*[contains(text(),'Восстановление пароля')]");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на кнопку-ссылку 'Войти'")
    public void clickButtonLinkLogin() {
        driver.findElement(buttonLinkLogin).click();
    }

    @Step("Ожидаем заголвок 'Восстановление пароля' на экране Восстановление пароля")
    public void waitForTitleRecover() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(titleRecoverPassword));
    }


}
