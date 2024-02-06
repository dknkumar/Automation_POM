@USSC @managealert
Feature: Manage Alerts App

  Background:

    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the ManageAlert app is clicked in the USSC launcher page

  Scenario: USSC_2102_05- Validate Http Code filter drop-down values
    And the filters button is clicked in the manage alerts log page
    And filter components are selected
      | HTTP Response Code |
    And the button labeled OK is clicked
    And click on http response code drop down
    Then the HTTPResponseCode dropdown contains
      | 200 |
      | 400 |
      | 403 |
      | 404 |
      | 500 |
      | 502 |
      | 504 |
      | All |

  Scenario Outline: USSC_2102_50- Verify Ascending sorting functionality for sort objects -Alert Rule Name,Alert Date, GTIN, Lot, Expiration date, Serial Number, Request time, Status, Issue, Resolution, Http status, Http Response code, Requester GLN, Requester Company, Verified, Additional Info, Responder GLN and Responder Company
    And the filters button is clicked in the manage alerts log page
    And filter components are selected
      | HTTP Status |
    And the button labeled OK is clicked
    And the button labeled Go is clicked
    And select all columns to display under personalisation in manage alert app
    And the button labeled OK is clicked
    And the view sort setting button is clicked
    And the sort option <columnName> is selected from sort by pop up
    And the button labeled OK is clicked
    Then the <columnName> column is sorted in ascending order in manage alert app
    Examples:
      | columnName       |
      | Verified         |
      | ExpirationDate   |
      | SerialNumber     |
      | RequestTime      |
      | ResponderCompany |
      | ResponderGln     |
      | RequesterGln     |
      | RequesterCompany |
      | HTTPStatus       |
      | HTTPResponseCode |
      | BatchNumber      |

  Scenario Outline: USSC_2102_51- Verify Descending sorting functionality for sort objects
    And the filters button is clicked in the manage alerts log page
    And filter components are selected
      | HTTP Status |
    And the button labeled OK is clicked
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the radio button descending order is clicked in manage alert page
    And the sort option <columnName> is selected from sort by pop up
    And the button labeled OK is clicked
    Then the <columnName> column is sorted in descending order in manage alert app
    Examples:
      | columnName            |
      | AdditionalInformation |
      | Issue                 |
      | Resolution            |
      | GTIN                  |
      | AlertRuleName         |
      | AlertDate             |
      | BatchNumber           |
      | Status                |

  @test5
  Scenario Outline: USSC_2102_51- Verify Descending sorting functionality for sort objects
    And the filters button is clicked in the manage alerts log page
    And filter components are selected
      | HTTP Status |
    And the button labeled OK is clicked
    And the button labeled Go is clicked
    And select all columns to display under personalisation in manage alert app
    And the button labeled OK is clicked
    And the view sort setting button is clicked
    And the radio button descending order is clicked in manage alert page
    And the sort option <columnName> is selected from sort by pop up
    And the button labeled OK is clicked
    Then the <columnName> column is sorted in descending order in manage alert app
    Examples:
      | columnName       |
      | ExpirationDate   |
      | Verified         |
      | SerialNumber     |
      | ResponderCompany |
      | ResponderGln     |
      | RequesterGln     |
      | HTTPStatus       |
      | RequesterCompany |
      | HTTPResponseCode |

  Scenario Outline:USSC_2102_53 , USSC_2102_38- App Title & Tile Name,Hide Fiter bar
    Then the title of the page is '<appName>'
    And the button labeled Hide Filter Bar is clicked
    Then the manage alert page hides filter components
    Examples:
      | appName       |
      | Manage Alerts |

  Scenario Outline:USSC_2102_01- Alert rule name, GTIN, UUID column values are not truncated when all columns are selected from personalization
    And select all columns to display under personalisation in manage alert app
    And the button labeled OK is clicked
    Then the user should be able see <columnName> values without any truncation issue in Alert Management screen
    Examples:
      | columnName      |
      | UIID            |
      | AlertRuleName   |
      | GTIN            |

  Scenario Outline: USSC_2102_02- Sort by 'None' option is available
    And the view sort setting button is clicked
    And the sort option <columnName> is selected from sort by pop up
    And the button labeled OK is clicked
    And the view sort setting button is clicked
    And the sort option None is selected from sort by pop up
    And the button labeled OK is clicked
    Then the <columnName> column is not sorted in manage alert app
    Examples:
    | columnName |
    | BatchNumber|


  Scenario Outline: USSC_2102_03- Search by Alert Date, Verification Fail Reason should result valid output
    And the filters button is clicked in the manage alerts log page
    And filter components are selected
      | Alert Date                  |
      | Alert Rule Name             |
      | Verification Failure Reason |
    And the button labeled OK is clicked
    And the dates are selected
    And the verification failure reason is entered
      | Manufacturer_policy |
    And the button labeled Go is clicked
    Then the user should be able to see <field> in the Verification Fail reason
    Examples:
      | field               |
      | Manufacturer_policy |

  Scenario Outline: USSC_2102_04- Create view and make default
    And select all columns to display under personalisation in manage alert app
    And the button labeled OK is clicked
    And get verification log data
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the manage alerts log page
    And filter components are selected
      | Verified               |
      | Status                 |
      | HTTP Status            |
      | HTTP Response Code     |
    And the button labeled OK is clicked
    And Save view '<ViewName>' for filter and Save button clicked in filter modal pop up
    And '<ViewName>' is set as a default view on the verification log page
    And Go button clicked in filter modal pop up
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the ManageAlert app is clicked in the USSC launcher page
    Then verify the selected filter components are available
      | Verified               |
      | Status                 |
      | HTTP Status            |
      | HTTP Response Code     |
    And set 'Verified' filter value in managealert page
    And set 'Status' filter value in managealert page
    And set 'HTTP Status' filter value in managealert page
    And set 'HTTP Response Code' filter value in managealert page
    And the button labeled Go is clicked
    Then the results contains filtered values in the response
      | Verified              |  |
      | Status                |  |
      | HTTPStatus            |  |
      | HTTPResponseCode      |  |
    Examples:
      | ViewName            | user              |
      | Auto_Standard       | USSC_Manufacturer |

#  Scenario: USSC_2102_06- Verify tool tip functionality for Issues and Resolutions column
#    And select an alert from alert records
#    And click on the Issue column
#    And select ExpiredProduct from the column
#    And select an alert from alert records
#    And click on the Resolution column
#    And select RecheckProduct from the column

  Scenario: USSC_2102_07- Verify Group By popup
    And the view group setting button is clicked
    Then the Group by pop up should contain the following Radio Buttons
    | Ascending          |
    | Descending         |
    | Alert Rule Name    |
    | GTIN               |
    | Lot/Batch Number   |
    | Status             |
    | Issue              |
    | Resolution         |
    | Requester GLN      |
    | Requester Company  |
    | Verified           |
    | Responder GLN      |
    | Responder Company  |
    | (Not Grouped)      |

  Scenario Outline: USSC_2102_08- Group by Ascending
    #The Batch Number scenario will fail because of PHARMANETWORK-23161
    And the view group setting button is clicked
    And the group option <columnValue> is selected from group by pop up
    And the button labeled OK is clicked
    Then the <columnValue> column is grouped in ascending order in manage alert app
    Examples:
      | columnValue       |
      | AlertRuleName     |
      | GTIN              |
      | BatchNumber       |
      | Status            |
      | Issue             |
      | Resolution        |
      | RequesterGln      |
      | RequesterCompany  |
      | Verified          |
      | ResponderGln      |
      | ResponderCompany  |


















