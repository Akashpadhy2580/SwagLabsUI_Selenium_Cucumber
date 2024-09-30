package pages;


import Testdata.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import static StepDefs.BaseTest.driver;
import static StepDefs.BaseTest.wait;

public class LoginPage {

    private RemoteWebDriver driver;
    private FluentWait<RemoteWebDriver> wait;

    @FindBy(id="user-name")
    private WebElement username;
    @FindBy(id="password")
    private WebElement password;
    @FindBy(id="login-button")
    private WebElement loginBtn;
    @FindBy(xpath="//div[@class='login_logo']")
    private WebElement loginheader;

    @FindBy(xpath="//div[contains(@class,'error-message')]/h3")
    private WebElement errorMsg;

    public LoginPage(RemoteWebDriver driver, FluentWait<RemoteWebDriver> wait) {
        PageFactory.initElements(driver,this);
        this.driver=driver;
        this.wait=wait;
    }
    public String getLoginPageTitle() {
        wait.until(ExpectedConditions.titleIs(Constants.LOGIN_PAGE_TITLE));
        return driver.getTitle();
    }
    public void verifyuserNameAndPasswordFields() {
        Assert.assertTrue(username.isDisplayed());
        Assert.assertTrue(password.isDisplayed());
    }
    public void verifyLoginPageHeader() {
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(loginheader)).getText(),"Swag Labs");
    }
    public void verifyloginButton() {
        Assert.assertTrue(loginBtn.isDisplayed());
    }

    public void verifyErrorMessage(String msg) {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(errorMsg)).getText().contains(msg));
    }
    public void enterUserName(String un)
    {
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(un);
    }
    public void enterPassword(String pwd)
    {
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pwd);
    }
    public void clickOnLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public ProductListingPage doLogin(String un, String pwd) {
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(un);
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pwd);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return new ProductListingPage(driver,wait);
    }
}
