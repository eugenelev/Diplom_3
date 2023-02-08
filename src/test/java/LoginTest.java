import helpers.User;
import helpers.UserClient;
import helpers.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import static helpers.URLs.*;
import static org.junit.Assert.assertEquals;


public class LoginTest  extends BaseTest {

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
    @DisplayName("Авторизация с главной страницы")
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
    @DisplayName("Авторизация с экрана личного кабинета")
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
    @DisplayName("Авторизация с экрана регистрации")
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
    @DisplayName("Авторизация с экрана восстановление пароля")
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
