@All
Feature: Verify Advanced Search

  @positive @verify
  Scenario: Success verify
    Given User is on Parabank homepage
    When User click Advanced Search
    Then User in the Advanced Search page
    When User enter price filed
    Then User click Search
    And User note the Price and Product Name
    When User reenter price field
    Then User click Search again
    And User note the Price and Product Name again

  
