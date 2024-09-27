package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {
    private WebDriver driver;

    private By checkoutOverviewPgHeader = By.xpath("//span[@class='title' and text()='Checkout: Overview']");
    private By finishBtn = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getOverviewPageHeaderText() {

    }

    public void clickonFinish() {


    }
}
