import helpers.User;
import helpers.UserClient;
import helpers.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import static helpers.URLs.*;
import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {
    private AccountPage accountPage;
    private LoginPage loginPage;
    private LoginSignInFieldsPage loginSignInFieldsPage;
    private RegistrationPage registrationPage;
    private RecoveryPasswordPage recoveryPasswordPage;
    private MainPage mainPage;
    private HeaderPage headerPage;
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
    @DisplayName("Выход из учетки")
    public void logoutFromAccount() {
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
        accountPage.clickLogoutButton();

        WebElement element = driver.findElement(/**/loginPage.buttonLogin);
        boolean elementIsDisplayedAfterLogout = element.isDisplayed();

        assertTrue(elementIsDisplayedAfterLogout);
    }
}
