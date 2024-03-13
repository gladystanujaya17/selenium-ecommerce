@All
Feature: Magento verify list based on Name

  @positive 
  Scenario: Success to verify list
		Given User is on Magento page
		When User click Mobile link button
		Then User is in Mobile page
		When User click Sort by Name
		Then Product is sorted by name
 