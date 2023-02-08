package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSignInFieldsPage {

    RegistrationPage registrationPage;
    LoginPage loginPage;
    private WebDriver driver;

    // Локатор поля принятия Имя
    public By fieldName = By.xpath("//label[text()='Имя']/following-sibling::input[@name='name']");

    // Локатор поля принятия Email
    public By fieldEmail = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");

    // Локатор поля принятия Password
    public By fieldPassword = By.xpath("//input[@name='Пароль']");

    public LoginSignInFieldsPage(WebDriver driver, LoginPage loginPage, RegistrationPage registrationPage) {
        this.driver = driver;
        this.loginPage = loginPage;
        this.registrationPage = registrationPage;
    }

    @Step("Заполнить поле 'Имя'")
    public void setName(String name) {
        driver.findElement(fieldName).click();
        driver.findElement(fieldName).clear();
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Заполнить поле 'Email'")
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).clear();
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Заполнить поле 'password'")
    public void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).clear();
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Выполнить регистрацию юзера")
    public void registrationUser(String name, String email, String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(registrationPage.buttonRegistration)));
        setName(name);
        setFieldEmail(email);
        setFieldPassword(password);
        registrationPage.clickRegistrationButton();
    }

    @Step("Авторизоваться юзером")
    public void loginUser(String email, String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(loginPage.buttonLogin)));
        setFieldEmail(email);
        setFieldPassword(password);
        loginPage.clickLoginButton();
    }

}
