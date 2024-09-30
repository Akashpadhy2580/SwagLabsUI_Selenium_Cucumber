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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {

    public static RemoteWebDriver driver;
    public static FluentWait<RemoteWebDriver> wait;
    public DriverFactory df;
    public static Properties prop;
    public static LoginPage loginpage;
    public ProductListingPage prodlistingPage;
    public ProductDetailsPage proDetailsPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public CheckoutOverviewPage checkoutoverviewPage;
    public OrderCompletePage ordercomPage;


    @Before
    public void setup() {

        df = new DriverFactory();
        prop = initProperties();
        if(driver==null) {
            driver = df.initDriver(prop);
            wait = df.defineWait(driver, 60, 1000);
        }
        loginpage = new LoginPage(driver,wait);

    }
    public static Properties initProperties() {
        FileInputStream ip = null;
        prop = new Properties();
        String env = System.getProperty("env"); // will be passing through maven as mvn clean install -Denv
        try {
            if (env == null) {
                System.out.println("Running on QA environment as no envrionment is specified");

                ip = new FileInputStream("./src/test/resources/config/config.properties");
            } else {
                System.out.println("Running on the environment:" + env);
                switch (env) {
                    case "staging":
                        ip = new FileInputStream("./src/test/resources/config/staging.config.properties");
                        break;
                    default:
                        throw new Exception("Wrong Env Name: " + env);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // prop = new Properties();
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
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
