Feature: LoginFeature
  This feature deals with the login functionality of the applications

  Scenario: Login with correct username and password
    # Given I navigate to the login page
    Given I enter the following for Login
      | username | password      |
      | admin    | adminpassword |
    And I click login button
    # Then I should see the userform page

  # Scenario: Login with incorrect username and password fails
  #   Given I navigate to the login page
  #   And I enter the following for Login
  #     | username | password      |
  #     | admin    | adminpassword |
  #   And I click login button
  #   Then I should see the userform page wrongly
