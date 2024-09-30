package StepDefs;

import Testdata.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductListingPage;

import static StepDefs.BaseTest.driver;
import static StepDefs.BaseTest.wait;
import static utils.DriverFactory.getDriver;

public class LoginStepDefinition {
    LoginPage loginPage;
    ProductListingPage productListingPage;

    @Given("I am on the SwagLabs login page")
    public void open_browser_and_launch_site() {
        loginPage = new LoginPage(driver, wait);
        Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE);
    }
    @When("I enter username {string}")
    public void i_enter(String userName) {
        loginPage.enterUserName(userName);
    }
    @When("I enter password {string}")
    public void enter(String password) {
        loginPage.enterPassword(password);
    }
    @When("I click the Login Button")
    public void i_am_able_to_login_successfully() {
        loginPage.clickOnLoginButton();
    }
    @Then("I should be on the {string} page")
    public void i_am_able_to_see_products(String products) {
        productListingPage=new ProductListingPage(getDriver(),BaseTest.wait);
        Assert.assertEquals(productListingPage.getProductListHeader(),products);
    }
    @Then("I should see the Header in the Login Page")
    public void i_should_see_the_header_in_the_login_page() {
        loginPage.verifyLoginPageHeader();
    }
    @Then("I should see the Username and Password fields")
    public void i_should_see_the_username_and_password_fields() {
        loginPage.verifyuserNameAndPasswordFields();
    }
    @Then("I should see the Login button")
    public void i_should_see_the_login_button() {
        loginPage.verifyloginButton();
    }
    @Then("I should see error message contains {string}")
    public void i_should_see_error_message(String msg) {
        loginPage.verifyErrorMessage(msg);
    }
}
