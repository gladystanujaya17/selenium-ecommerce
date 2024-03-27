@All
Feature: Verify discount coupon feature

  @positive
  Scenario: Success to verify discount coupon in Magento
		Given User is on Magento page
		When User go to Mobile page
		Then User add iPhone to cart
		When User enter coupon code
		Then User verify the discount is generated
		
	@negative
  Scenario: Failed to verify discount coupon in Magento
		Given User is on Magento page
		When User go to Mobile page
		Then User add iPhone to cart
		When User enter different coupon code
		Then User verify the discount is not generated