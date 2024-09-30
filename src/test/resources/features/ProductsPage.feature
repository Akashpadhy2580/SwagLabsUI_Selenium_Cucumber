@productsPage @regression
Feature: Products Page

  Background:
    Given I am on the SwagLabs login page
    When I login with valid credentials
    Then I should be redirected to the "Products" page
#######################################################################################################################################################################################################

  @smoke @tests
  Scenario: Validating if all the products are present correctly
    Then I should see all products in products page

#######################################################################################################################################################################################################
