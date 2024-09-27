package utils;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {
    public static ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<RemoteWebDriver>();
    Properties prop;
    public DesiredCapabilities capabilities;
    private static final Logger log = LogManager.getLogger(DriverFactory.class);


    public static RemoteWebDriver getDriver() {
        return tlDriver.get();
    }

    // This method is used to intilize the driver
    public RemoteWebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser");
        log.info("Brower Name is:" + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                log.info("Running on Remote machine");
                init_remoteDriver(browserName);
            } else {
                log.info("Running on Local machine");
                tlDriver.set(new ChromeDriver());
            }
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                log.info("Running on Remote machine");
                init_remoteDriver("firefox");
            } else {
                log.info("Running on local machine");
                tlDriver.set(new FirefoxDriver());
            }
        } else {
            log.warn("Please pass correct browser Name:" + browserName);
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        getDriver().get(prop.getProperty("URL"));
        return getDriver();
    }

    private void init_remoteDriver(String browserName) {
        System.out.println("Running test on remote browser:" + browserName);
        capabilities=new DesiredCapabilities();

        //os
        if(prop.getProperty("os").equalsIgnoreCase("windows"))
        {
            capabilities.setPlatform(Platform.WIN11);
        }
        else if(prop.getProperty("os").equalsIgnoreCase("linux"))
        {
            capabilities.setPlatform(Platform.LINUX);

        }
        else if (prop.getProperty("os").equalsIgnoreCase("mac"))
        {
            capabilities.setPlatform(Platform.MAC);
        }
        else
        {
            System.out.println("No matching os");
            return;
        }
        try {
            switch (browserName.toLowerCase().trim()) {
                case "chrome":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), capabilities));
                    break;
                case "firefox":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), capabilities));
                    break;
                default:
                    System.out.println("wrong browser info..can not run on grid remote machine....");
                    break;
            }
        } catch (MalformedURLException e) {
        }
    }

    public Properties initProperties() {
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
                        log.info("No Env found" + env);
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
    public static FluentWait<RemoteWebDriver> wait;
    public FluentWait<RemoteWebDriver> defineWait
            (RemoteWebDriver driver, int max, long interval)
    {
        wait=new FluentWait<RemoteWebDriver>(getDriver());
        wait.withTimeout(Duration.ofSeconds(max));
        wait.pollingEvery(Duration.ofMillis(interval));
        return(wait);
    }



}
