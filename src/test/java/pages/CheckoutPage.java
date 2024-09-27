package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class CheckoutPage {

    private RemoteWebDriver driver;
    private FluentWait<RemoteWebDriver> wait;
    @FindBy(xpath="continue")
    private WebElement checkoutPageHeader;
    @FindBy(id="first-name")
    private WebElement firstName;
    @FindBy(id="last-name")
    private WebElement lastName;
    @FindBy(id="postal-code")
    private WebElement zipCode;
    @FindBy(id="continue")
    private WebElement continueBtn;

    public CheckoutPage(RemoteWebDriver driver, FluentWait<RemoteWebDriver> wait) {
        PageFactory.initElements(driver,this);
        this.driver=driver;
        this.wait=wait;
    }

    public String getCheckoutPageHeader() {
        return wait.until(ExpectedConditions.visibilityOf(checkoutPageHeader)).getText();
    }

    public CheckoutOverviewPage clickonContinue(String fn, String ln, String zip) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fn);
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(ln);
        wait.until(ExpectedConditions.visibilityOf(zipCode)).sendKeys(zip);
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        return new CheckoutOverviewPage(driver);

    }
}
