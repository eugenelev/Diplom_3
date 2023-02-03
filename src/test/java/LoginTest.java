import helpers.User;
import helpers.UserClient;
import helpers.UserGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import static helpers.URLs.*;
import static org.junit.Assert.assertEquals;


public class LoginTest  extends BaseTest {

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
        driver.get(URL);
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
    public void loginFromMainPage() {
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        mainPage.clickLoginButton();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlAfterLogin = URL;
        String actualUrlAfterLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlAfterLogin, actualUrlAfterLogin);
    }

    @Test
    public void loginFromPersonalAccount() {
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        headerPage.clickPersonalAccountButton();
        loginPage.waitForTitleLoginPage();
        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlAfterLogin = URL;
        String actualUrlAfterLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlAfterLogin, actualUrlAfterLogin);
    }

    @Test
    public void loginFromRegistration() {
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        driver.get(REGISTRATION_PATH);
        registrationPage.waitForTitleRegistration();
        registrationPage.clickLoginButtonLink();

        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlAfterLogin = URL;
        String actualUrlAfterLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlAfterLogin, actualUrlAfterLogin);
    }

    @Test
    public void loginFromRecoverPasswordForm() {
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);
        mainPage = new MainPage(driver, loginPage);

        driver.get(LOGIN_PATH);
        loginPage.waitForTitleLoginPage();

        loginPage.clickRecoverPasswordButtonLink();
        recoveryPasswordPage.clickButtonLinkLogin();

        loginPage.loginUser(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlAfterLogin = URL;
        String actualUrlAfterLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlAfterLogin, actualUrlAfterLogin);
    }

}
