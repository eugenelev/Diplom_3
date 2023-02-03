package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private WebDriver driver;

    public By sectionProfile = By.xpath("//a[text()='Профиль']");

    public By logoutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
