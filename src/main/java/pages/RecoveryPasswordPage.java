package pages;

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

        public void clickButtonLinkLogin() {
        driver.findElement(buttonLinkLogin).click();
    }

    public void waitForTitleRecover() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(titleRecoverPassword));
    }


}
