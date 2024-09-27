package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import pages.*;
import utils.DriverFactory;

import java.util.Properties;


public class BaseTest {

    public static RemoteWebDriver driver;
    public static FluentWait<RemoteWebDriver> wait;
    public DriverFactory df;
    public Properties prop;
    public LoginPage loginpage;
    public ProductListingPage prodlistingPage;
    public ProductDetailsPage proDetailsPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public CheckoutOverviewPage checkoutoverviewPage;
    public OrderCompletePage ordercomPage;


    @Before
    public void setup() {

        df = new DriverFactory();
        prop = df.initProperties();

        driver = df.initDriver(prop);
        wait=df.defineWait(driver,60,1000);
        loginpage = new LoginPage(driver,wait);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quits the WebDriver session, closing all browser windows
            driver = null; // Sets driver to null to avoid using a terminated instance
        }
    }
    @AfterStep
    public static void takeScreenshot(Scenario s)
    {
        if (s.isFailed()){
        byte[] b=driver.getScreenshotAs(OutputType.BYTES);
        s.attach(b,s.getName()+".png", "Test Failed");}
    }
}
