@All
Feature: Verify purchase feature

  @positive 
  Scenario: Success to verify cost of product
		Given User is on Magento page
		When User click my account
		Then User login with the account
		When User click My Wishlist
		Then User click Add to Cart
		And User click Proceed to Checkout
		And User enter shipping information
		And User click Continue button
		And User choose Payment Information
		And Verify shipping cost is added to total
		And User click Place Order
		And Verify Order is generated
 