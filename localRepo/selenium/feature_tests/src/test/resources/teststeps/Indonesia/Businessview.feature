@indonesia @businessview
Feature: Indonesia App

  Background:

    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the DetailedView tab is opened



#    And the DetailedView tab is opened
#

  Scenario: 1.Validate switch

    And the BusinessView tab is opened on DetailedView page
    And the Provisions tab is opened on DetailedView page

  Scenario: 2.Validate Http Code filter drop-down values2

    Then Verify all the filters are present

      | Business Group  |
      | Plant |
      | Category         |
      | Expiry date     |
      | Provision Quantity |


#
