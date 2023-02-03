import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;
import static helpers.URLs.*;
import static org.junit.Assert.assertTrue;


public class ChangeTabInConstructorTest extends BaseTest {

    AccountPage accountPage;
    LoginPage loginPage;
    LoginSignInFieldsPage loginSignInFieldsPage;
    RegistrationPage registrationPage;
    RecoveryPasswordPage recoveryPasswordPage;
    MainPage mainPage;
    HeaderPage headerPage;



    @Before
    public void setUp() {
        super.setUp();
        driver.get(URL);

    }

    @Test
    public void changeBetweenTabsInConstructor() {
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

        mainPage.clickTabConstructor("Булки");

        WebElement BunTitleInList = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleInListConstructor, "Начинки")));
        boolean bunElementIsDisplayed = BunTitleInList.isDisplayed();
        assertTrue(bunElementIsDisplayed);

        mainPage.clickTabConstructor("Соусы");

        WebElement sauceTitleInList = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleInListConstructor, "Соусы")));
        boolean sauceElementIsDisplayed = sauceTitleInList.isDisplayed();
        assertTrue(sauceElementIsDisplayed);
    }

}

