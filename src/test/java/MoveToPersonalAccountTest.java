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


public class MoveToPersonalAccountTest extends BaseTest  {

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
    public void moveToProfileByClickPersonalAccount() {
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

        WebElement elTitleSectionProfile = driver.findElement(accountPage.sectionProfile);
        boolean elTitleSectionProfileisActive = elTitleSectionProfile.getAttribute("class").contains("active");

        String expectedUrlAfterLogin = ACCOUNT_PATH;
        String actualUrlAfterLogin = driver.getCurrentUrl();

        assertTrue(elTitleSectionProfileisActive);
        assertEquals(expectedUrlAfterLogin, actualUrlAfterLogin);
    }

}
