@All
Feature: Verify save PDF feature

  @positive 
  Scenario: Success to verify cost of product
		Given User is on Magento page
		When User click on My Account
		Then User login the application
		Then User click My Orders
		Then User click View Order
		When User verify order is displayed in Recent Orders and status is pending
		Then User click Print Order
		Then User verify order is saved as PDF
 