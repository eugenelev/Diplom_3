package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage{

    private WebDriver driver;

    public By sectionPersonalAccount = By.xpath("//*[contains(text(),'Личный Кабинет')]");

    public By sectionConstructor = By.xpath("//p[text()='Конструктор']");

    public By logoStellarBurgers = By.xpath("//div[contains(@class, 'header__logo')]");




    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на раздел 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(sectionPersonalAccount).click();
    }

    @Step("Клик на раздел 'Конструктор'")
    public void clickSectionConstructor() {
        driver.findElement(sectionConstructor).click();
    }

    @Step("Клик на лого 'Stellar Burgers'")
    public void clickByLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }



}
