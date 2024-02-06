@USSC @writelogs
Feature: Verify Write Log App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the WriteLog app is clicked in the USSC launcher page

  Scenario Outline: 15.1,15.2,15.3,15.4,15.5,15.6 - Verify title of Write Logs and filtering with different filter components
    Then the title of the page is '<Title>'
    Then the title of the Write log table is '<Title>'
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And set 'GTIN' filter value '<GTIN>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | GTIN | <GTIN> |
    And clear filter gtin value
    And set 'lot' filter value '<LOT>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | LOT | <LOT> |
    And clear filter lot value
    And set 'messageID' filter value '<MessageId>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | MessageId | <MessageId> |
    And clear filter messageId value
    And set 'disposition' filter value '<Disposition>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | Disposition | <Disposition> |
    And clear filter disposition value
    And set filter value MessageTime '<MessageTime>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | MessageTime | <MessageTime> |
    And clear filter MessageTime value
    And set filter value EventTime '<EventTime>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | EventTime | <EventTime> |
    Examples:
      | Title     | GTIN           | LOT       | MessageId                  | Disposition        | MessageTime | EventTime  |
      | Write Log | 00363391180998 | auto63868 | automationtest622099135896 | auto_true_recalled | 2018-12-09  | 2020-02-26 |

  Scenario Outline: 15.7,15.8,15.9- Write Log App"-> Filter -> LOT Number, GTIN and DISPOSITION Field length
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And set 'GTIN' filter value '<GTIN>' in write logs page
    And set 'lot' filter value '<LOT>' in write logs page
    And set 'disposition' filter value '<Disposition>' in write logs page
    Then verify the maximum allowed length
      | GTIN        | <GTIN>        |
      | LOT         | <LOT>         |
      | Disposition | <Disposition> |

    Examples:
      | GTIN            | LOT                                                                                                   | Disposition                                                                                           |
      | 003031065300459 | abxvcnmdsnkjfhdsjgdhgljdflkgjlkdfjgoidfoigjerljtlkgrjedklgjdfkljgkldfjgkljdfjglkdfjlkgjdfsfdghjkdfshg | abxvcnmdsnkjfhdsjgdhgljdflkgjlkdfjgoidfoigjerljtlkgrjedklgjdfkljgkldfjgkljdfjglkdfjlkgjdfsfdghjkdfshg |


  Scenario: USSC_18 , USSC_2011_01- Sorting of MessageId column in ascending order and descending order and verify write log record count
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And the button labeled Go is clicked
    Then the write log record count is displayed
    And the view sort setting button is clicked
    And the sort option MessageId is selected in write log page
    And the button labeled OK is clicked
    Then the MessageId column is sorted in ascending order in write log page
    And the view sort setting button is clicked
    And the sort option MessageId is selected in write log page
    And the radio button descending order is clicked in write log page
    And the button labeled OK is clicked
    Then the MessageId column is sorted in descending order in write log page

  Scenario: USSC_18.1 - Sorting of MessageTime column in ascending order and descending order
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option MessageTime is selected in write log page
    And the button labeled OK is clicked
    Then the MessageTime column is sorted in ascending order in write log page
    And the view sort setting button is clicked
    And the sort option MessageTime is selected in write log page
    And the radio button descending order is clicked in write log page
    And the button labeled OK is clicked
    Then the MessageTime column is sorted in descending order in write log page

  Scenario: USSC_18.2 - Sorting of EventTime column in ascending order and descending order
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option EventTime is selected in write log page
    And the button labeled OK is clicked
    Then the EventTime column is sorted in ascending order in write log page
    And the view sort setting button is clicked
    And the sort option EventTime is selected in write log page
    And the radio button descending order is clicked in write log page
    And the button labeled OK is clicked
    Then the EventTime column is sorted in descending order in write log page

  Scenario: USSC_18.3 - Sorting of disposition column in ascending order and descending order
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option Disposition is selected in write log page
    And the button labeled OK is clicked
    Then the Disposition column is sorted in ascending order in write log page
    And the view sort setting button is clicked
    And the sort option Disposition is selected in write log page
    And the radio button descending order is clicked in write log page
    And the button labeled OK is clicked
    Then the Disposition column is sorted in descending order in write log page

  Scenario: USSC_18.4 - Sorting of GTIN column in ascending order and descending Order
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option GTIN is selected in write log page
    And the button labeled OK is clicked
    Then the GTIN column is sorted in ascending order in write log page
    And the view sort setting button is clicked
    And the sort option GTIN is selected in write log page
    And the radio button descending order is clicked in write log page
    And the button labeled OK is clicked
    Then the GTIN column is sorted in descending order in write log page

  Scenario: USSC_18.5 - Sorting of Lot/Batch number column in ascending order and descending order
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option LOT is selected in write log page
    And the button labeled OK is clicked
    Then the LOT column is sorted in ascending order in write log page
    And the view sort setting button is clicked
    And the sort option LOT is selected in write log page
    And the radio button descending order is clicked in write log page
    And the button labeled OK is clicked
    Then the LOT column is sorted in descending order in write log page

  Scenario Outline: USSC_15.11,USSC_16_2 - set default filter component as message id , message time and search
    And the filters button is clicked in the write log page
    And filter components are selected
      | Message ID         |
      | Message Time (UTC) |
    And the button labeled OK is clicked
    Then verify selected filter components are available in write log app
      | MessageId   |
      | MessageTime |
    And set 'messageID' filter value '<MessageId>' in write logs page
    And set filter value MessageTime '<MessageTime>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | MessageId   | <MessageId>   |
      | MessageTime | <MessageTime> |
    Examples:
      | MessageId                  | MessageTime |
      | automationtest555645784339 | 2018-12-09  |
#NA -  PHARMANETWORK-15346 _ As part of UI5 upgrade, feature of variants drop down from filters dialog is removed. Hence These tests are not valid.
  @testNASkip
  Scenario Outline: USSC_15.12 - "Write Log App" -> Filter -> Select only Event Time, Disposition ->  Populate  with values -> save Filter -> search
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the write log page
    And filter components are selected
      | Event Time (UTC) |
      | Disposition      |
    And the button labeled OK is clicked
    And set 'EventTime' filter value in write logs page
    And set 'disposition' filter value in write logs page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    And '<ViewName>' is set as default view
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as 'USSC_Manufacturer' in the login page
    And the WriteLog app is clicked in the USSC launcher page
    Then verify selected filter components are available in write log app
      | EventTime   |
      | Disposition |
    And verify default value for filter component in write log page
      | EventTime   | <DefaultEventTime> |
      | Disposition | <Disposition>      |
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | EventTime   | <EventTime>   |
      | Disposition | <Disposition> |
    And clear filter disposition value
    And clear filter EventTime value
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    Examples:
      | EventTime  | Disposition | DefaultEventTime            | ViewName      |
      | 2020-02-26 | active      | Feb 26, 2020 - Feb 26, 2020 | Auto_Standard |

  @testNASkip
  Scenario Outline: USSC_15.13 - "Write Log App" -> Filter -> Select only GTIN, LOT->  Populate  with values -> save Filter -> search
    And get data for filters
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the write log page
    And filter components are selected
      | GTIN             |
      | Lot/Batch Number |
    And the button labeled OK is clicked
    And set 'GTIN' filter value '<GTIN>' in write logs page
    And set 'lot' filter value '<LOT>' in write logs page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    And '<ViewName>' is set as default view
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as 'USSC_Manufacturer' in the login page
    And the WriteLog app is clicked in the USSC launcher page
    Then verify selected filter components are available in write log app
      | GTIN |
      | LOT  |
    And verify default value for filter component in write log page
      | GTIN | <GTIN> |
      | LOT  | <LOT>  |
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | GTIN | <GTIN> |
      | LOT  | <LOT>  |
    And clear filter gtin value
    And clear filter lot value
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    Examples:
      | GTIN           | LOT          | ViewName      |
      | 11512489000014 | AD80226L1AR1 | Auto_Standard |

  @testNASkip
  Scenario Outline: USSC_15.14 - set default filter component as message id , message time and search
    And get data for filters
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the write log page
    And filter components are selected
      | Message ID         |
      | Message Time (UTC) |
    And the button labeled OK is clicked
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    And '<ViewName>' is set as default view
    Then verify selected filter components are available in write log app
      | MessageId   |
      | MessageTime |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as 'USSC_Manufacturer' in the login page
    And the WriteLog app is clicked in the USSC launcher page
    Then verify selected filter components are available in write log app
      | MessageId   |
      | MessageTime |
    And set 'messageID' filter value in write logs page
    And set 'MessageTime' filter value in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the results
      | MessageId   |  |
      | MessageTime |  |
    And clear filter messageId value
    And clear filter MessageTime value
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    Examples:
      | MessageId                           | MessageTime | ViewName      |
      | 005056BA6ACA1ED5B0E9C0665D40DEMO003 | 2018-12-09  | Auto_Standard |

  @testNASkip
  Scenario Outline: USSC_15.15 - set default filter component as event time , disposition and search
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the write log page
    And filter components are selected
      | Event Time (UTC) |
      | Disposition      |
    And the button labeled OK is clicked
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    And '<ViewName>' is set as default view
    Then verify selected filter components are available in write log app
      | EventTime   |
      | Disposition |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as 'USSC_Manufacturer' in the login page
    And the WriteLog app is clicked in the USSC launcher page
    Then verify selected filter components are available in write log app
      | EventTime   |
      | Disposition |
    And set filter value EventTime '<EventTime>' in write logs page
    And set 'disposition' filter value '<Disposition>' in write logs page
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
      | EventTime   | <EventTime>   |
      | Disposition | <Disposition> |
    And clear filter disposition value
    And clear filter EventTime value
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    Examples:
      | EventTime  | Disposition | ViewName      |
      | 2020-02-26 | active      | Auto_Standard |

  Scenario Outline: USSC_15.16 ,USSC_16.4,USSC_16.3,USSC_2011_03- set default filter component as GTIN , LOT and search
    And the button labeled Go is clicked
    Then the write log results contains the filtered values in the response
   #   | Status     | <Status> |
      | WrittenEpc |  |
      | QueuedEpc  |  |
    And the filters button is clicked in the write log page
  #  And switch to my view if '<ViewName>' exists
   # And clear all filter component fields in write logs page
    And filter components are selected
      | GTIN             |
      | Lot/Batch Number |
    And the button labeled OK is clicked
  #  And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
  #  And Go button is clicked in filter modal pop up in write log app
  #  And Show Filter Bar button is clicked in write log app
    Then verify selected filter components are available in write log app
      | GTIN |
      | LOT  |
 #   And the user clicks on sign out button
 #   And the button labeled OK is clicked
 #   And the user clicks log in again
 #   And the user logs in as 'USSC_Manufacturer' in the login page
 #   And the WriteLog app is clicked in the USSC launcher page
  #  Then verify selected filter components are available in write log app
      | GTIN |
      | LOT  |
    And set 'GTIN' filter value '<GTIN>' in write logs page
    And set 'lot' filter value '<LOT>' in write logs page
    And the button labeled Go is clicked
    Then the write log record count is displayed
    Then the write log results contains the filtered values in the response
      | GTIN | <GTIN> |
      | LOT  | <LOT>  |
    And clear filter gtin value
    And clear filter lot value
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up in writelog page
    Examples:
      | GTIN           | LOT         | Status | ViewName      |
      | 00363391180998 | BATCHTSP001 | 100.0% | Auto_Standard |


  Scenario Outline: USSC_17.2 , USSC 16.2- Write Log App -> Click Download Icon
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option GTIN is selected in write log page
    And the button labeled OK is clicked
    And the button labeled Go is clicked
    And the download pdf button is clicked
    Then verify file '<pdfFileName>' is downloaded
    And the download button is clicked
    Then verify file '<downloadFileName>' is downloaded
    Examples:
      | pdfFileName  | downloadFileName |
      | PDFSheet.pdf | Export.xlsx      |

  Scenario Outline: USSC_2008_1,USSC_2008_2:  verify the header details,Serial number field value
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And set 'messageID' filter value '<MessageId>' in write logs page
    And the button labeled Go is clicked
    And click on first record of write log
    And click on repository explorer link in Write Log
    Then verify default recorded event table from write log page navigation
    Then verify default serial number field '<Serial Number Ghost Text>' on navigation from write log page
    Examples:
      | MessageId                 | Serial Number Ghost Text |
      | automationtest69091749469 | Enter Serial Number...   |


  Scenario Outline: USSC_2008_6, USSC_2008_3, USSC_2008_7: Verify the Recorded events data when an commisioning and de-commisioning event is performed
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And set 'GTIN' filter value '<GTIN>' in write logs page
    And set 'lot' filter value '<LOT>' in write logs page
    And set 'disposition' filter value '<Disposition>' in write logs page
    And the button labeled Go is clicked
    And click on first record of write log
    And click on repository explorer link in Write Log
    And Validate the GS1 elements on Blockchain Explorer screen
      | GTIN            | <GTINBC>          |
      | Lot             | <LOTBC>           |
      | Expiration Date | <Expiration Date> |
      | PI Count        | <PI Count>        |
    And Search the '<Serial Number>'
    Then Validate the recorded event
      | Timestamp             | <Timestamp>             |
      | Action                | <Action>                |
      | Disposition           | <Disposition>           |
      | GLN                   | <GLN>                   |
      | Written to Blockchain | <Written to Blockchain> |
    Examples:
      | GTIN           | LOT       | Disposition             | Expiration Date           | PI Count    | GTINBC               | LOTBC                       | Serial Number | Timestamp                   | Action | GLN           | Written to Blockchain    |
      | 00363391180998 | auto26230 | commissioning_auto_test | Expiration Date: 22-07-15 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: auto26230 | 9282745585    | 2021-02-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-18T06:14:25.636Z |
      | 00363391180998 | auto54286 | autoinactive            | Expiration Date: 21-07-15 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: auto54286 | 9282745585    | 2020-02-26T12:39:06.515506Z | Delete | 0363391100002 | 2022-11-18T06:27:50.692Z |


  Scenario Outline: USSC_2008_5: Click on 'Go back' icon in the block chain explorer screen to successfully navigate back to Write log details screen
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And set 'GTIN' filter value '<GTIN>' in write logs page
    And the button labeled Go is clicked
    And click on first record of write log
    And click on repository explorer link in Write Log
    Then Navigation menu title is '<BC Navigation Menu title>'
    And click on back navigation button
    Then Navigation menu title is '<Write Log Details Navigation Menu title>'
    Examples:
      | GTIN           | BC Navigation Menu title | Write Log Details Navigation Menu title |
      | 00363391180998 | Repository Explorer      | Write Log Details                       |


  Scenario Outline: USSC_2008_8: Verify the Recorded events data when an shipping event is performed
    And the filters button is clicked in the write log page
    And select all fields for filter criteria
    And the button labeled OK is clicked
    And clear all filter values in write log page
    And set 'messageID' filter value '<MessageId>' in write logs page
    And set 'GTIN' filter value '<GTIN>' in write logs page
    And set 'lot' filter value '<LOT>' in write logs page
    And the button labeled Go is clicked
    And click on first record of write log
    And click on repository explorer link in Write Log
    And Validate the GS1 elements on Blockchain Explorer screen
      | GTIN            | <GTINBC>          |
      | Lot             | <LOTBC>           |
      | Expiration Date | <Expiration Date> |
      | PI Count        | <PI Count>        |
    And Search the '<Serial Number>'
    Then Validate the recorded event
      | Timestamp             | <Timestamp>             | <Timestamp>             |
      | Action                | <Observe Action>        | <Add Action>            |
      | Disposition           | <Observe Disposition>   | <Add Disposition>       |
      | GLN                   | <GLN>                   | <GLN>                   |
      | Written to Blockchain | <Written to Blockchain> | <Written to Blockchain> |
    Examples:
      | MessageId                 | GTIN           | LOT       | Add Disposition     | Observe Disposition      | Expiration Date           | PI Count    | GTINBC               | LOTBC                       | Serial Number | Timestamp                   | Add Action | Observe Action | GLN           | Written to Blockchain    |
      | automationtest20757464111 | 00363391180998 | auto68869 | activeobserveobject | in_transit_objectobserve | Expiration Date: 21-11-28 | PI Count: 1 | GTIN: 00363391180998 | Lot/Batch Number: auto68869 | 7578533430    | 2020-02-26T12:39:06.515506Z | Add        | Observe        | 0363391100002 | 2022-11-18T05:56:31.895Z |


  Scenario Outline: USSC_2011_04- Validate user is not allowed to download PDF with more than 100000 records
    And the button labeled Go is clicked
    And get the record count from the table
    And the download pdf button is clicked
    Then the download error message '<Error Message>' is displayed
    Examples:
      | Error Message                                                                |
      | Download size limit is 100000. Use the filter to reduce the number of lines. |

  Scenario Outline: USSC_2011_02- Verify Write Log displays record count of past one week on landing to home screen with default filters applied
    And navigate to home screen
    Then the app header contains '<appName>' and '<header>'
    And get the record count from the app
    And the WriteLog app is clicked in the USSC launcher page
    Then validate the write log record count
    Examples:
      | appName   | header            |
      | Write Log | Uploads Past week |
