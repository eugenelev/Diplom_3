import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;
import static helpers.URLs.*;
import static org.junit.Assert.assertTrue;


public class ChangeTabInConstructorTest extends BaseTest {

    private AccountPage accountPage;
    private LoginPage loginPage;
    private LoginSignInFieldsPage loginSignInFieldsPage;
    private RegistrationPage registrationPage;
    private RecoveryPasswordPage recoveryPasswordPage;
    private MainPage mainPage;
    private HeaderPage headerPage;

    @Before
    public void setUp() {
        super.setUp();
        driver.get(URL);

    }

    @Test
    @DisplayName("Переключение на вкладку 'Начинки' в конструкторе")
    public void moveToTabFillingTabsInConstructor() {
        accountPage = new AccountPage(driver);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        mainPage.clickTabConstructor("Начинки");

        WebElement fillingTitleInList = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleInListConstructor, "Начинки")));
        boolean fillingElementIsDisplayed = fillingTitleInList.isDisplayed();
        assertTrue(fillingElementIsDisplayed);
    }

    @Test
    @DisplayName("Переключение на вкладку 'Соусы' в конструкторе")
    public void moveToTabSauceTabsInConstructor() {
        accountPage = new AccountPage(driver);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        mainPage.clickTabConstructor("Соусы");

        WebElement sauceTitleInList = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleInListConstructor, "Соусы")));
        boolean sauceElementIsDisplayed = sauceTitleInList.isDisplayed();
        assertTrue(sauceElementIsDisplayed);
    }
    
    @Test
    @DisplayName("Переключение на вкладку 'Булки' в конструкторе")
    public void moveToTabBunTabsInConstructor() {
        accountPage = new AccountPage(driver);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        mainPage.clickTabConstructor("Начинки");
        mainPage.clickTabConstructor("Булки");

        WebElement fillingTitleInList = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleInListConstructor, "Булки")));
        boolean fillingElementIsDisplayed = fillingTitleInList.isDisplayed();
        assertTrue(fillingElementIsDisplayed);
    }

}

