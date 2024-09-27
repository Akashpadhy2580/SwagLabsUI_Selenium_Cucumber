package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ProductDetailsPage {

    private RemoteWebDriver driver;
    private FluentWait<RemoteWebDriver> wait;
    @FindBy(xpath="//div[@class='inventory_details_name large_size' and text()='Sauce Labs Bike Light']")
    private WebElement selectedProd;
    @FindBy(xpath="//a[@class='shopping_cart_link']")
    private WebElement cartIcon;
    @FindBy(id="add-to-cart")
    private WebElement addtoCartBtn;

    public ProductDetailsPage(RemoteWebDriver driver, FluentWait<RemoteWebDriver> wait) {
        PageFactory.initElements(driver,this);
        this.driver=driver;
        this.wait=wait;
    }

    public String getSelectedProd() {
        return wait.until(ExpectedConditions.visibilityOf(selectedProd)).getText();
    }

    public CartPage clickonCartandIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(addtoCartBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        return new CartPage(driver,wait);
    }
}
