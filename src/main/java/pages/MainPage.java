package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage{

    private LoginPage loginPage;
    private WebDriver driver;

    public By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    public By createOrderButton = By.xpath("//button[text()='Оформить заказ']");

    public By titleSectionConstructorBurger = By.xpath("//h1[text()='Соберите бургер']");

    public String tabsConstructor = "//section[contains(@class, 'BurgerIngredients')]//span[text()='%s']";

    public String ingredientsTitleInListConstructor = "//h2[contains(text(), '%s')]";



    public MainPage(WebDriver driver, LoginPage loginPage) {
        this.driver = driver;
        this.loginPage = loginPage;

    }

    @Step("Ожидаем кнопку 'Оформить заказ' на главной странице")
    public void waitForCreateOrderButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    @Step("Клие на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        loginPage.waitForTitleLoginPage();
    }

    @Step("Клик на таб 'Конструктор'")
    public void clickTabConstructor(String ingredient) {
        driver.findElement(By.xpath(String.format(tabsConstructor, ingredient))).click();
    }

}
