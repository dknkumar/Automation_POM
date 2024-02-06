@indonesia @businessview
Feature: Indonesia App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the DetailedView tab is opened
#    And the Summary tab is opened


  Scenario: 3.Verify the Subtabs Business View/Provision (With toggle switch)

    And the BusinessView tab is opened on DetailedView page
    And the Provisions tab is opened on DetailedView page

  Scenario: 4.Verify the Global Filters of Business view in Detailed view screen
    Then Verify all the filters are present
      | Business Group  |
      | Plant |
      | Category         |
      | Expiry date     |
      | Provision Quantity |


  Scenario: 5.Verify the Business group filter values in Business view

    Then <"Business Group"> should have following drop-down values

      | Select all           |
      | Beauty & Wellbeing   |
      | Home Care            |
      | Ice Cream            |
      | Nutrition            |
      | Personal Care        |


  Scenario: 6.Verify the plant filter values in Business view

    Then <"Plant"> should have following drop-down values
      | Select all     |
      | 9000           |
      | 9001           |
      | 9002           |
      | 9003           |
      | 9006           |

  Scenario: 7.Verify the Category filter values in Business view

    Then <"Category filter"> should have following drop-down values
      |Select all           |
      |Beverages            |
      |Deodorants & Fragrances|
      |Fabric Cleaning      |
      |Fabric Enhancers     |
      |Hair Care            |
#      |Home & Hygine        |
#      |Non Corporate Personal Care Category|
#      |Oral Care|
#      |Professional Cleaning Category|
#      |Scratch Cooking Aids|
#      |Skin care|
#      |Skin Cleansing|


  Scenario: 8.Verify the Expiry date (Categorical- <6mos, 6mos-12mos, >12 mos) filter values in Business view
    Then <"Expiry date"> should have following drop-down values
      | Select all|
      | <6 Months|
      | >12 Months|
      |6-12 Months|



  Scenario: 9.Verify the Provision Quantity filter values in Business view
    Then <"Provision Quantity"> should have following drop-down values
      |All                |
      |Provision Quantity |

  Scenario: 10.Verify the Loss Tree filter values in Business view
    Then <"Loss Tree"> should have following drop-down values
      |Select all|
      |Destroy  |
      |Exclude|
      |Liquidate|

  Scenario: 26.Verify the columns in Business view table
    Then <"Columns"> should have following drop-down values
#      |Select All   |
#      | SKU         |
#      | Description |
#      | Plant       |
#      | Location    |
#      | Batch / Lot |
#      | SLOC |
#      | Status SLED|
      | Active SKU Mapped|
      | SLED/BB|
      | Category|
      | Loss Tree General|
      | Actual Qty|
      | Actual Value|
      | Qty Prov left for Sale |
      | Qty Prov|
      | Value Prov|
      | Status|
      | Action From BW |
      | Product Cost/CS|
      | Offer Price/CS |
      | Retail/CS|
      |Disc From Retail|
      | BG |
      | Status |

  Scenario Outline: search
    When click columnsDropDownPath button
    And enter the '<data>' to search
#    And enter '<Columns Value>'

    Examples:
      |data|
#      |Select All   |
#      | SKU         |
#      | Description |
        |Plant|
#      |Location|
#      | Batch / Lot |
#      | SLOC        |
#       | Status SLED |

  Scenario: checkbox
    And check the users count
    When click columnsDropDownPath button
  And Select name in columns1 filter
#      |Select All   |
#      | SKU         |
#      | Description |
      |Plant|
      |Location|
      | Batch / Lot |
      | SLOC        |
      | Status SLED |

  Scenario: users count
#  And check the users count
  And Get message count


