import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.DriverManager;


import java.util.concurrent.TimeUnit;

public class BaseTest extends DriverManager {

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
// Закрыть браузер
        driver.quit();
    }




}
