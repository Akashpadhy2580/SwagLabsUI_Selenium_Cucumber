@smoke @login @regression
Feature: Login and Logout Feature

  Background:
    Given I am on the SwagLabs login page
#######################################################################################################################################################################################################

  @currentRun
  Scenario: Validating if all the elements in the Login Page are present correctly
    Then I should see the Header in the Login Page
    And I should see the Username and Password fields
    And I should see the Login button

##########################################################################################################################################################################################################

  @currentRun
  Scenario Outline: Successful login with valid credentials
    When I enter username "<Username>"
    And I enter password "<Password>"
    And I click the Login Button
    Then I should be on the "Products" page

    Examples:
      | Username                | Password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |

###########################################################################################################################################################################################################

  Scenario Outline: Unsuccessful login with invalid credentials
    When I enter username "<Username>"
    And I enter password "<Password>"
    And I click the Login Button
    Then I should see error message contains "Username and password do not match"

    Examples:
      | Username    | Password    |
      | Admin       | Admin123    |
      | Invalid     | Invalid     |
      | Invalid     | admin123    |
      | Admin       | Invalid     |
      | Admin!@#    | admin123    |
      | Admin       | admin123@%$ |
      | ' OR '1'='1 | admin123    |

##########################################################################################################################################################################################################

  Scenario Outline: Unsuccessful login without username or blank Credentials
    When I enter username "<Username>"
    And I enter password "<Password>"
    And I click the Login Button
    Then I should see error message contains "Username is required"

    Examples:
      | Username | Password |
      |          |          |
      |          | admin123 |

##########################################################################################################################################################################################################

  Scenario Outline: Unsuccessful login without password
    When I enter username "<Username>"
    And I enter password "<Password>"
    And I click the Login Button
    Then I should see error message contains "Password is required"

    Examples:
      | Username | Password |
      | Admin    |          |
#
