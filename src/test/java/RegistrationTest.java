import helpers.User;
import helpers.UserGenerator;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.LoginSignInFieldsPage;
import pages.RecoveryPasswordPage;
import pages.RegistrationPage;
import static helpers.URLs.LOGIN_PATH;
import static helpers.URLs.REGISTRATION_PATH;
import static org.junit.Assert.assertEquals;


public class RegistrationTest  extends BaseTest {


    private final String ERROR_TEXT = "Некорректный пароль";
    private RecoveryPasswordPage recoveryPasswordPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    LoginSignInFieldsPage loginSignInFieldsPage;
    private User user;

    @Before
    public void setUp() {
        super.setUp();
        driver.get(REGISTRATION_PATH);
        user = UserGenerator.getUser();

    }

    @Test
    public void checkSuccesfullRegistration() {
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);

        loginSignInFieldsPage.registrationUser(user.getName(), user.getEmail(), user.getPassword());

        loginPage.waitForTitleLoginPage();
        String expectedUrlAfterRegistration = LOGIN_PATH;
        String actualUrlAfterRegistration = driver.getCurrentUrl();

        assertEquals(expectedUrlAfterRegistration, actualUrlAfterRegistration);
    }

    @Test
    public void checkImpossubleRegistrationWithPasswordLessThanSixSymbols() {
        registrationPage = new RegistrationPage(driver);
        loginSignInFieldsPage = new LoginSignInFieldsPage(driver, loginPage, registrationPage);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
        loginPage = new LoginPage(driver, recoveryPasswordPage, loginSignInFieldsPage);

        loginSignInFieldsPage.registrationUser(user.getName(), user.getEmail(), "12345");

        String actualTextErrorRegistration = registrationPage.getErrorTextRegistration();

        assertEquals(ERROR_TEXT, actualTextErrorRegistration);
    }
}