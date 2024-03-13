@All
Feature: Magento verify cost of product in list page and details page

  @positive 
  Scenario: Success to verify cost of product
		Given User is on Magento page
		When User click Mobile link button
		Then User is in Mobile page
		Then User is read the cost of Sony Xperia mobile
		When User click Sony Xperia mobile
		Then User read the Sony Xperia mobile from detail page
		Then User compare value in list page and details page
 