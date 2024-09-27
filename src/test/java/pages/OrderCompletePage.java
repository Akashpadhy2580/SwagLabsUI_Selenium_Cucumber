package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

public class OrderCompletePage {

    private RemoteWebDriver driver;
    private FluentWait<RemoteWebDriver> wait;
    private By successHeader = By.xpath("//h2[@class='complete-header' and text()='Thank you for your order!']");

    public OrderCompletePage(RemoteWebDriver driver, FluentWait<RemoteWebDriver> wait) {
        PageFactory.initElements(driver,this);
        this.driver=driver;
        this.wait=wait;
    }

    public void getSuccessHeaderText() {

    }
}
