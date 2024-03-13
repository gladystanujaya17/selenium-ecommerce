@All
Feature: Magento pop up window

  @positive 
  Scenario: Success to verify cost of product
		Given User is on Magento page
		When User click mobile link button
		Then User is in Mobile page
		When User click Add to Compare Sony XPeria
		And User click Add to Compare iPhone
		And User click Compare button
		Then There will be pop up window
		And User close it	
 