@All
Feature: Verify account feature

  @positive 
  Scenario: Success to verify cost of product
		Given User is on Magento page
		When User click My Account link
		Then User click Create Account
		And User fill user information
		And User verify registration is done
		When User go to TV menu
		Then User add product
		When User click SHARE WISHLIST
		Then User enter e-mail and message
		And User click SHARE WISHLIST again
		And Wishlist is shared
 