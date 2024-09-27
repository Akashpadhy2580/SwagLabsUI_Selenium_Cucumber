package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class CartPage {

	private RemoteWebDriver driver;
	private FluentWait<RemoteWebDriver> wait;
	@FindBy(xpath="//span[@class='title']")
	private WebElement cartPageHeader;
	@FindBy(xpath="//div[@class='inventory_item_name' and text()='Sauce Labs Bike Light']")
	private WebElement prodHeader;
	@FindBy(id="checkout")
	private WebElement checkoutBtn;

	public CartPage(RemoteWebDriver driver, FluentWait<RemoteWebDriver> wait) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
		this.wait=wait;
	}

	public String getCartPageHeader() {
		return wait.until(ExpectedConditions.visibilityOf(cartPageHeader)).getText();
	}

	public Boolean isProdHeaderDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(prodHeader)).isDisplayed();
	}

	public CheckoutPage clickonCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
		return new CheckoutPage(driver,wait);
	}
}
