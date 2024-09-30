package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.List;

public class ProductListingPage {

    private RemoteWebDriver driver;
    private FluentWait<RemoteWebDriver> wait;

    @FindBy(xpath="//*[@id='header_container']/div[2]/span")
    private WebElement productListHeader;
    @FindBy(xpath="//div[@class='inventory_list']/div//following::div[@class='inventory_item_name ']")
    private List<WebElement> productlist;

    public ProductListingPage(RemoteWebDriver driver, FluentWait<RemoteWebDriver> wait) {
        PageFactory.initElements(driver,this);
        this.driver=driver;
        this.wait=wait;
    }

    @Step("Getting Product List Header")
    public String getProductListHeader() {
        wait.until(ExpectedConditions.visibilityOf(productListHeader));
        return productListHeader.getText();
    }

    @Step("Getting Products List")
    public List<String> productLists() {
        List<String> prodListsSection = new ArrayList<>();
        List<WebElement> prodLists = wait.until(ExpectedConditions.visibilityOfAllElements(productlist));
        for (WebElement e : prodLists) {
            prodListsSection.add(e.getText());
        }
        return prodListsSection;
    }

    @Step("Selecting product")
    public ProductDetailsPage selectProduct(String selectedProdName) {
        List<WebElement> prodListing = wait.until(ExpectedConditions.visibilityOfAllElements(productlist));
        for (WebElement e : prodListing) {
            if (e.getText().trim().equals(selectedProdName)) {
                e.click();
                break;
            }
        }
        return new ProductDetailsPage(driver,wait);
    }
}
