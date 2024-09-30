package StepDefs;

import Testdata.Constants;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductListingPage;
import utils.PropertiesFileUtility;

import java.util.List;

public class ProductsPageStepDefinition {
    ProductListingPage productListingPage;

    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() throws Exception {
        productListingPage=BaseTest.loginpage.doLogin(PropertiesFileUtility.getValueFromPropertiesFile("username"),PropertiesFileUtility.getValueFromPropertiesFile("password"));
    }
    @Then("I should be redirected to the {string} page")
    public void i_should_be_redirected_to_the_page(String products) {
        Assert.assertEquals(productListingPage.getProductListHeader(),products);
    }
    @Then("I should see all products in products page")
    public void i_should_see_products_in_products_page() {
        List<String> acutProducts = productListingPage.productLists();
        System.out.println("Acutal Products are:" + acutProducts);
        Assert.assertEquals(acutProducts, Constants.EXPECTED_PRODUCTS_LIST);
    }
}
