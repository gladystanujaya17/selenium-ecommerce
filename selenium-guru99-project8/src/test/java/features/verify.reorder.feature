@All
Feature: Verify repurchase feature

  @positive 
  Scenario: Success to verify cost of product after repurchase
		Given User is on Magento page
		When User click my account
		Then User login with the account
		When User click Reorder
		Then User change the Quantity and update it
		And User verify the grand total is changed
		When User click Proceed to Checkout
		Then User complete the shipping information
		And User verify order is generated