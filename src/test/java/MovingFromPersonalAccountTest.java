import helpers.User;
import helpers.UserClient;
import helpers.UserGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import static helpers.URLs.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;





public class MovingFromPersonalAccountTest extends BaseTest {
    AccountPage accountPage;
    LoginPage loginPage;
    LoginSignInFieldsPage loginSignInFieldsPage;
    RegistrationPage registrationPage;
    RecoveryPasswordPage recoveryPasswordPage;
    MainPage mainPage;
    HeaderPage headerPage;
    private User user;
    private UserClient UserClient;
    private String token;


    @Before
    public void setUp() {
        super.setUp();
        driver.get(LOGIN_PATH);
        user = UserGenerator.getUser();
        UserClient = new UserClient();
        ValidatableResponse createResponse = UserClient.create(user);
        token = createResponse.extract().path("accessToken");
    }

    @After
    public void cleanUp() {
        if ( token != null) {
            UserClient.delete(token);
        }
    }

    @Test
    public void moveToConstructorFromPersonalAccount() {
        accountPage = new AccountPage(driver);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        loginPage.waitForTitleLoginPage();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();
        headerPage.clickPersonalAccountButton();
        headerPage.clickSectionConstructor();


        WebElement elementTitleSectionConstructorBurger = driver.findElement(mainPage.titleSectionConstructorBurger);
        boolean elementTitleSectionConstructorBurgerIsDisplayed = elementTitleSectionConstructorBurger.isDisplayed();

        assertTrue(elementTitleSectionConstructorBurgerIsDisplayed);
    }

    @Test
    public void moveToConstructorByClickLogoSiteFromPersonalAccount() {
        accountPage = new AccountPage(driver);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        loginPage.waitForTitleLoginPage();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();
        headerPage.clickPersonalAccountButton();
        headerPage.clickByLogoStellarBurgers();


        WebElement elementTitleSectionConstructorBurger = driver.findElement(mainPage.titleSectionConstructorBurger);
        boolean elementTitleSectionConstructorBurgerIsDisplayed = elementTitleSectionConstructorBurger.isDisplayed();

        assertTrue(elementTitleSectionConstructorBurgerIsDisplayed);
    }

}

