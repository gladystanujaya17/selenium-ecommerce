@All
Feature: Magento verify you can not add more product in the web

  @positive 
  Scenario: Success to verify cost of product
		Given User is on Magento page
		When User click mobile link button
		Then User is in Mobile page
		When User click Add Cart in Sony Xperia mobile
		Then User change the QTY 
		When user click Empty Cart
		Then verify cart is empty		
 