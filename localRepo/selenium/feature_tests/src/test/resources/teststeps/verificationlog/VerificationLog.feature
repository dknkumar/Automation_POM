@USSC @verificationlog
Feature: Verify Verification Log App

  Scenario Outline: USSC_8.1, USSC_8.2, USSC_8.3, USSC_8.4, USSC_8.5, USSC_8.6, USSC_8.7, USSC_2005.7, USSC_2005.8, USSC_2005.9: "Verification log" -> Filter ->GTIN, LOT,RequestDate, Serial Number, Requestor GLN, Responder GLN, SerialNumber, Verified, Additional Information
    Given the user logs in as 'USSC_Wholesaler' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filter for field
      | <fieldName> |
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And the filters button is clicked in the verification log page
    And filter components are selected
      | <fieldName> |
    And the button labeled OK is clicked
    And set '<fieldName>' filter value in verification logs page
    And the button labeled Go is clicked
    Then the results contains the filtered values in the response
      | <fieldName> |  |
    Examples:
      | fieldName              |
      | Request Time           |
      | GTIN                   |
      | Lot/Batch Number       |
      | Serial Number          |
      | Verified               |
      | UUID                   |
      | Link Type              |
      | Context                |
      | Additional Information |
      | Requester GLN          |
      | Responder GLN          |

    #NA -  PHARMANETWORK-15346 _ As part of UI5 upgrade, feature of variants drop down from filters dialog is removed. Hence These tests are not valid.
  @testNASkip
  Scenario Outline: USSC_8.4.1 - "Verification log" -> Filter -> Select only Verified  ->Populate with 'TRUE' value-> save Filter and set Filter as default and search
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And filter components are selected
      | Verified |
    And the button labeled OK is clicked
    And the verified, '<Verified>', is selected on the verification log page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And '<ViewName>' is set as default view on the verification log page
    And Go button is clicked in filter modal pop up
    Then verify selected filter components are available
      | Verified |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | Verified |
    And verify default value for Verified filter component
      | Verified | <Verified> |
    And Go button is clicked in verification home page
    Then the results contains the Verified filtered values in the response
      | Verified | <Verified> |
    Examples:
      | user            | Verified | ViewName      |
      | USSC_Wholesaler | true     | Auto_Standard |
 #NA -  PHARMANETWORK-15346 _ As part of UI5 upgrade, feature of variants drop down from filters dialog is removed. Hence These tests are not valid.
  @testNASkip
  Scenario Outline: USSC_9.1 - "Verification log" -> Filter -> Select only RequestTime, GTIN, LOT number->  Populate  with values -> save Filter
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filters
    And switch to my view if '<ViewName>' exists
    Then the title of the page is '<Title>'
    Then the title of the Verification log table is '<Title>'
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And filter components are selected
      | Request Time     |
      | GTIN             |
      | Lot/Batch Number |
    And the button labeled OK is clicked
    And set 'Request Date' filter value in verification logs page
    And set 'GTIN' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And '<ViewName>' is set as default view on the verification log page
    And Go button is clicked in filter modal pop up
    #And Show Filter Bar button is clicked
    Then verify selected filter components are available
      | Request Time     |
      | GTIN             |
      | Lot/Batch Number |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | Request Time     |
      | GTIN             |
      | Lot/Batch Number |
    And verify default value for filter component
      | RequestTime |  |
      | GTIN        |  |
      | LOT         |  |
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | RequestTime |  |
      | GTIN        |  |
      | LOT         |  |
    Examples:
      | user              | Title            | RequestTime | GTIN           | LOT        | ViewName      | DefaultRequestTime        |
      | USSC_Wholesaler   | Verification Log | 2020-08-03  | 00363391180998 | AutoDbTest | Auto_Standard | Aug 3, 2020 - Aug 3, 2020 |
      | USSC_Manufacturer | Verification Log | 2020-08-03  | 00363391180998 | AutoDbTest | Auto_Standard | Aug 3, 2020 - Aug 3, 2020 |
 #NA
  @testNASkip
  Scenario Outline: USSC_9.2- "Verification log" -> Filter -> Select only Verified , Serial Number, Additional Info->  Populate  with values -> save Filter-> search
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filters
    And switch to my view if '<ViewName>' exists
    Then the title of the page is '<Title>'
    Then the title of the Verification log table is '<Title>'
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And filter components are selected
      | Verified               |
      | Serial Number          |
      | Additional Information |
    And the button labeled OK is clicked
    And set 'Verified' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And set 'Additional Information' filter value in verification logs page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And '<ViewName>' is set as default view on the verification log page
    And Go button is clicked in filter modal pop up
    Then verify selected filter components are available
      | Verified               |
      | Serial Number          |
      | Additional Information |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | Verified               |
      | Serial Number          |
      | Additional Information |
    And verify default value for filter component
      | Verified               |  |
      | Serial Number          |  |
      | Additional Information |  |
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | Verified               |  |
      | Serial Number          |  |
      | Additional Information |  |
    Examples:
      | user              | Title            | RequestTime | GTIN           | LOT              | ViewName      |
      | USSC_Manufacturer | Verification Log | 2020-04-22  | 00363391180998 | TestvinayQA2023  | Auto_Standard |
      | USSC_Wholesaler   | Verification Log | 2020-05-03  | 00363391180998 | Test1vinayQA2020 | Auto_Standard |
 #NA
  @testNASkip
  Scenario Outline: USSC_10.2 - set default filter component as verified,serial number and additional info search
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filters
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And filter components are selected
      | Verified               |
      | Serial Number          |
      | Additional Information |
    And the button labeled OK is clicked
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And '<ViewName>' is set as default view on the verification log page
    And Go button is clicked in filter modal pop up
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | Verified               |
      | Serial Number          |
      | Additional Information |
    And set 'Verified' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And set 'Additional Information' filter value in verification logs page
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | Verified               |  |
      | Serial Number          |  |
      | Additional Information |  |
    Examples:
      | user            | ViewName      |
      | USSC_Wholesaler | Auto_Standard |
 #NA
  @testNASkip
  Scenario Outline: USSC_10.1 - set default filter component as GTIN,Lot and Request Time search
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filters
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And filter components are selected
      | Request Time     |
      | GTIN             |
      | Lot/Batch Number |
    And the button labeled OK is clicked
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And '<ViewName>' is set as default view on the verification log page
    And Go button is clicked in filter modal pop up
    Then verify selected filter components are available
      | Request Time     |
      | GTIN             |
      | Lot/Batch Number |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | Request Time     |
      | GTIN             |
      | Lot/Batch Number |
    And set 'Request Date' filter value in verification logs page
    And set 'GTIN' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | RequestTime |  |
      | GTIN        |  |
      | LOT         |  |
    Examples:
      | user              | SerialNumber | ViewName      |
      | USSC_Manufacturer | 7578533417   | Auto_Standard |
      | USSC_Wholesaler   | 7578533417   | Auto_Standard |
#NA
  @testNASkip
  Scenario Outline: USSC_2005.6 -"Verification log" -> Filter -> Select only UUID, LinkType, Context->  Populate  with values -> save Filter->Search
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get UUID, Link Type & Context verification log data for filters
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And filter components are selected
      | UUID      |
      | Link Type |
      | Context   |
    And the button labeled OK is clicked
    And set 'UUID' filter value in verification logs page
    And set 'Linktype' filter value in verification logs page
    And set 'Context' filter value in verification logs page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is click in filter modal pop up
    And Show Filter Bar button is clicked
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | UUID      |
      | Link Type |
      | Context   |
    And verify default value for filter component
      | UUID      |  |
      | Link Type |  |
      | Context   |  |
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | UUID      |  |
      | Link Type |  |
      | Context   |  |
    Examples:
      | user            | UUID                                 | Linktype            | Context             |
      | USSC_Wholesaler | 1bfad4b7-bf04-43c7-a1e9-f635b310e756 | verificationService | dscsaSaleableReturn |

    #NA - As this involves sorting of alpha numeric value in lexicographic order and few data is only numeric
  @testNASkip
  Scenario Outline: USSC_13.2.1 - Sorting of Lot/Batch number column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
     #   And switch to my view if '<ViewName>' exists
 #   And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option LOT is selected
    And the button labeled OK is clicked
    Then the LOT column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.2.2 - Sorting of Lot/Batch number column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
   # And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And the button labeled OK is clicked
 #   And clear filter requestDate value in verification log
    And Go button is click in filter modal pop up
   # And Show Filter Bar button is clicked
    And the view sort setting button is clicked
    And the sort option LOT is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the LOT column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.2.1 - Sorting of GTIN column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
   # And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option GTIN is selected
    And the button labeled OK is clicked
    Then the GTIN column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.2.2 - Sorting of GTIN column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
 #   And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option GTIN is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the GTIN column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.4.1 - Sorting of Serial Number column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option SerialNumber is selected
    And the button labeled OK is clicked
    Then the SerialNumber column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |


  Scenario Outline: USSC_13.4.2 - Sorting of Serial Number column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option SerialNumber is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the SerialNumber column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |


  Scenario Outline: USSC_13.5.1 - Sorting of Verified column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option Verified is selected
    And the button labeled OK is clicked
    Then the Verified column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.5.2 - Sorting of Verified column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option Verified is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the Verified column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.6.1 - Sorting of RequestorGln column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option RequestorGln is selected
    And the button labeled OK is clicked
    Then the RequestorGln column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.6.2 - Sorting of RequestorGln column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option RequestorGln is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the RequestorGln column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.7.1 - Sorting of ResponderGln column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option ResponderGln is selected
    And the button labeled OK is clicked
    Then the ResponderGln column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.7.2 - Sorting of ResponderGln column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option ResponderGln is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the ResponderGln column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.8.1 - Sorting of AdditionalInfo column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option AdditionalInfo is selected
    And the button labeled OK is clicked
    Then the AdditionalInfo column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13.8.2 - Sorting of AdditionalInfo column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
    And the status, '<Status>', is selected on the verification log page
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option AdditionalInfo is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the AdditionalInfo column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13- Sorting of RequestTime column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
    And the status, '<Status>', is selected on the verification log page
  #  And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option RequestTime is selected
    And the button labeled OK is clicked
    Then the RequestTime column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13 - Sorting of RequestTime column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option RequestTime is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the RequestTime column is sorted in descending order
    Examples:
      | user              |
      | USSC_Manufacturer |

  Scenario Outline: USSC_13- Sorting of ExpirationDate column in ascending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
   # And clear filter requestDate value in verification log
    And Go button is click in filter modal pop up
  #  And Show Filter Bar button is clicked
    And the status, '<Status>', is selected on the verification log page
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option ExpirationDate is selected
    And the button labeled OK is clicked
    Then the ExpirationDate column is sorted in ascending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_13 - Sorting of ExpirationDate column in descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Status |
    And the button labeled OK is clicked
  #  And clear filter requestDate value in verification log
    And Go button is click in filter modal pop up
  #  And Show Filter Bar button is clicked
    And the status, '<Status>', is selected on the verification log page
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option ExpirationDate is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the ExpirationDate column is sorted in descending order
    Examples:
      | user              | Status  |
      | USSC_Manufacturer | Success |

  Scenario Outline: USSC_14 - Download pdf document
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filter for field
      | GTIN |
    And the filters button is clicked in the verification log page
    And filter components are selected
      | GTIN |
    And the button labeled OK is clicked
    And set 'GTIN' filter value in verification logs page
    And the button labeled Go is clicked
    And the download pdf button is clicked
    Then verify file '<pdfFileName>' is downloaded
    And the download button is clicked
    Then verify file '<downloadFileName>' is downloaded
    Examples:
      | user              | pdfFileName  | downloadFileName | ViewName      | GTIN           |
      | USSC_Manufacturer | PDFSheet.pdf | Export.xlsx      | Auto_Standard | 00363391200016 |
      | USSC_Wholesaler   | PDFSheet.pdf | Export.xlsx      | Auto_Standard | 00363391200016 |


  Scenario Outline:  USSC_2005.10 , USSC_2008_28 , USSC_2011_25: Verification log-> verify Vr log record count-> Click Verification log details
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filters
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Request Time           |
      | GTIN                   |
      | Lot/Batch Number       |
      | Serial Number          |
      | Verified               |
      | Additional Information |
      | Requester GLN          |
      | Responder GLN          |
      | UUID                   |
      | Link Type              |
      | Context                |
    And the button labeled OK is clicked
    And Go button is clicked in verification home page
   # And Show Filter Bar button is clicked
    And set 'Request Time' filter value in verification logs page
    And set 'GTIN' filter value in verification logs page
    And set 'Request Time' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And set 'Additional Information' filter value in verification logs page
    And set 'Requester GLN' filter value in verification logs page
    And set 'ResponderGln' filter value in verification logs page
    And set 'UUID' filter value in verification logs page
    And set 'Linktype' filter value in verification logs page
    And set 'Context' filter value in verification logs page
    And Go button is clicked in verification home page
    Then the verification log record count is displayed
    Then the results contains the filtered values in the response
      | Verified       | <Verified>       |
      | SerialNumber   | <SerialNumber>   |
      | AdditionalInfo | <AdditionalInfo> |
      | RequestTime    | <RequestTime>    |
      | GTIN           | <GTIN>           |
      | LOT            | <LOT>            |
      | RequestorGln   | <RequestorGln>   |
      | ResponderGln   | <ResponderGln>   |
      | UUID           | <UUID>           |
      | Linktype       | <Linktype>       |
      | Context        | <Context>        |
    And get execution duration of first record
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    Then the request details contains
      | RequestTime       | <RequestTime>    |
      | Verified          | <Verified>       |
      | SerialNumber      | <SerialNumber>   |
      | AdditionalInfo    | <AdditionalInfo> |
      | GTIN              | <GTIN>           |
      | LOT               | <LOT>            |
      | RequesterGln      | <RequestorGln>   |
      | ResponderGln      | <ResponderGln>   |
      | UUID              | <UUID>           |
      | Linktype          | <Linktype>       |
      | Context           | <Context>        |
      | ExecutionDuration |                  |
    Examples:
      | user              | Verified | RequestTime | SerialNumber | AdditionalInfo | ViewName      | GTIN           | LOT       | ResponderGln  | RequestorGln  | UUID                                 | Linktype            | Context          |
      | USSC_Manufacturer | false    | 2022-02-10  | 9282745585   | Suspect        | Auto_Standard | 00363391180998 | auto79834 | 0363391100002 | 0363391100002 | c0ab5338-7204-44fe-968c-bda70425bd44 | verificationService | SAPdscsaSelfTest |

  Scenario Outline: USSC_2005.58, USSC_2005.59,USSC_2005.60,USSC_2005.61,USSC_2005.62,USSC_2005.63 ,USSC_2008_14 , USSC_2008_15, USSC_2008_02- "Verification log"-> personalization of Verification log table
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And the button labeled Go is clicked
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And select columns from the personalisation settings
      | RequestTime |
      | GTIN        |
      | LOT         |
    And the button labeled OK is clicked
    Then the verification log table contains
      | RequestTime |
      | GTIN        |
      | LOT         |
    Then the verification log table does not contains
      | Verified       |
      | SerialNumber   |
      | AdditionalInfo |
      | RequesterGln   |
      | ResponderGln   |
      | UUID           |
      | Linktype       |
      | Context        |
      | ExpirationDate |
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And select columns from the personalisation settings
      | SerialNumber   |
      | Verified       |
      | AdditionalInfo |
    And the button labeled OK is clicked
    Then the verification log table contains
      | SerialNumber   |
      | Verified       |
      | AdditionalInfo |
    Then the verification log table does not contains
      | RequestTime               |
      | GTIN                      |
      | LOT                       |
      | RequestorGln              |
      | ResponderGln              |
      | UUID                      |
      | Linktype                  |
      | Context                   |
      | ExpirationDate            |
      | VerificationFailureReason |
      | ExecutionDuration         |
      | RequesterCompany          |
      | ResponderCompany          |
      | Status                    |
      | ResponseCode              |
      | ResponseCodeDescription   |
      | AlertEmailStatus          |
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And select columns from the personalisation settings
      | RequesterGln |
      | ResponderGln |
      | UUID         |
      | Linktype     |
      | Context      |
    And the button labeled OK is clicked
    Then the verification log table contains
      | RequestorGln |
      | ResponderGln |
      | UUID         |
      | Linktype     |
      | Context      |
    Then the verification log table does not contains
      | RequestTime    |
      | GTIN           |
      | LOT            |
      | Verified       |
      | SerialNumber   |
      | AdditionalInfo |
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    Examples:
      | user            |
      | USSC_Wholesaler |


  Scenario Outline: USSC_2005.64 , USSC_2008_16 - "Verification log"-> click settings/personalization icon -> Column sequencing
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And move RequestTime column by one place
    And the button labeled OK is clicked
    Then the second column in verification log table contains Request Time
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the button labeled Sign in again is clicked
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then the second column in verification log table contains Request Time
    Examples:
      | user              |
      | USSC_Wholesaler   |
      | USSC_Manufacturer |

  Scenario Outline: USSC_2008_8,USSC_2008_9,USSC_2008_10,USSC_2008_11, USSC_2008_01- MAH And WHO User_Verify Execution Duration display in verification log table and filters section and default column display
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Execution Duration (ms) |
    And the button labeled OK is clicked
    Then the filter component contains fields
      | ExecutionDuration |
    Then the filter component does not contains fields
      | RequestTime    |
      | GTIN           |
      | LOT            |
      | Verified       |
      | SerialNumber   |
      | AdditionalInfo |
      | RequestorGln   |
      | ResponderGln   |
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    Then the verification log table contains
      | RequestTime    |
      | GTIN           |
      | LOT            |
      | ExpirationDate |
      | SerialNumber   |
      | Verified       |
      | RequestorGln   |
      | ResponderGln   |
      | AdditionalInfo |
    Examples:
      | user              |
      | USSC_Wholesaler   |
      | USSC_Manufacturer |

  Scenario Outline: USSC_2008_23, USSC_2008_24- MAH/WHO User_ Verify sorting order of execution duration in verification log results when execution duration is selected to sort in ascending and descending order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Execution Duration (ms) |
    And the button labeled OK is clicked
    Then the filter component contains fields
      | ExecutionDuration |
    Then set execution duration range to default value
    And the button labeled Go is clicked
    And the view sort setting button is clicked
    And the sort option ExecutionDuration is selected
    And the button labeled OK is clicked
    Then the ExecutionDuration column is sorted in ascending order
    And the view sort setting button is clicked
    And the sort option ExecutionDuration is selected
    And the radio button descending order is clicked
    And the button labeled OK is clicked
    Then the ExecutionDuration column is sorted in descending order
    Examples:
      | user              |
      | USSC_Manufacturer |
      | USSC_Wholesaler   |

  Scenario Outline: USSC_2008_25 -MAH/Wholesaler User_verify filtering of execution duration with specified range
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Requester GLN           |
      | Execution Duration (ms) |
    And the button labeled OK is clicked
#    And Show Filter Bar button is clicked
   # And clear filter requestDate value in verification log
    And set execution duration range to default value
    And get execution duration range from filter field
    And the button labeled Go is clicked
    Then the results contains the filtered values in the response
      | ExecutionDuration |  |
    Examples:
      | user              |
      | USSC_Wholesaler   |
      | USSC_Manufacturer |
#NA
  @testNASkip
  Scenario Outline: USSC_10.3, USSC_2008_25 - set default filter component as requestor ,responder gln and execution duration and search
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filters
    And the filters button is clicked in the verification log page
  #  And switch to my view if '<ViewName>' exists
  #  And clear all filter component fields
    And filter components are selected
      | Requester GLN           |
      | Responder GLN           |
      | Execution Duration (ms) |
    And the button labeled OK is clicked
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is click in filter modal pop up
    And Show Filter Bar button is clicked
    Then the filter component contains fields
      | RequestorGln      |
      | ResponderGln      |
      | ExecutionDuration |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then the filter component contains fields
      | RequestorGln      |
      | ResponderGln      |
      | ExecutionDuration |
    Then set execution duration range to default value
    And get execution duration range from filter field
    And set 'Requester GLN' filter value in verification logs page
    And set 'Responder GLN' filter value in verification logs page
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | RequestorGln      | <RequestorGln> |
      | ResponderGln      | <ResponderGln> |
      | ExecutionDuration |                |
    Examples:
      | user              | RequestorGln  | ResponderGln  | ViewName      |
      | USSC_Manufacturer | 0363391100002 | 0363391100002 | Auto_Standard |

  Scenario Outline: USSC_8.1, USSC_8.2, USSC_8.3, USSC_8.4, USSC_8.5, USSC_8.6, USSC_8.7- Verification Log App"-> Filter -> LOT Number, GTIN , Additional Information, requestor gln, responder gln and serial number Field length
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
    And filter components are selected
      | GTIN                   |
      | Lot/Batch Number       |
      | Serial Number          |
      | Additional Information |
      | Requester GLN          |
      | Responder GLN          |
      | UUID                   |
    And the button labeled OK is clicked
    And Go button is clicked in verification home page
    And set filter value gtin '<GTIN>'
    And set filter value lot '<LOT>'
    And set filter value serialNumber '<SerialNumber>'
    #And set filter value Additional Info '<AdditionalInfo>'
    And set filter value requestor Gln '<RequestorGln>'
    And set filter value responder Gln '<ResponderGln>'
    And set filter value UUID '<UUID>'
    Then verify the maximum allowed length in verification log
   #   | AdditionalInfo | <AdditionalInfo> |
      | GTIN           | <GTIN>           |
      | RequestorGln   | <RequestorGln>   |
      | ResponderGln   | <ResponderGln>   |
      | SerialNumber   | <SerialNumber>   |
      | UUID           | <UUID>           |
      | LOT            | <LOT>            |
    Examples:
      | user              | SerialNumber                           | AdditionalInfo                                           | ViewName      | GTIN               | LOT                         | ResponderGln    | RequestorGln         | UUID                                                                                                          |
      | USSC_Manufacturer | 75785334174353464565674686987960980980 | SuspectFDGDGFDGFDBFDBFVBDRDSFWRTREYTRUYTIUYIsdgfdhgfjghj | Auto_Standard | 003633911809988989 | Test1vinayQA202098798797879 | 036339110000898 | 03633911000028989909 | SuspectFDGDGFDGFDBFDBFVBDRDSFWRTREYTRUYTIUYIsdgfdhgfjghjSuspectFDGDGFDGFDBFDBFVBDUYTIUYIsdgfdhgfjghjfsdfsdfsd |

  Scenario Outline: USSC_2008_12,USSC_2008_13,USSC_2102_02- MAH/WHO User_Verify all the columns are displayed in verification log on selecting all columns in personalisation
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And the button labeled Go is clicked
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    Then the verification log table contains
      | RequestTime               |
      | GTIN                      |
      | LOT                       |
      | ExpirationDate            |
      | SerialNumber              |
      | Verified                  |
      | RequestorGln              |
      | ResponderGln              |
      | AdditionalInfo            |
      | UUID                      |
      | Linktype                  |
      | Context                   |
      | VerificationFailureReason |
      | ExecutionDuration         |
      | RequesterCompany          |
      | ResponderCompany          |
      | Status                    |
      | ResponseCode              |
      | ResponseCodeDescription   |
      | AlertEmailStatus          |
    Examples:
      | user              |
      | USSC_Wholesaler   |
      | USSC_Manufacturer |
#NA
  @testNASkip
  Scenario Outline: USSC_9.3 ,USSC_2008_26 ,USSC_2008_27 -"Verification log" -> Filter -> Select only Requestor GLN, Responder GLN, execution Duration->  Populate  with values -> save Filter->Search
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And get verification log data for filters
    And the filters button is clicked in the verification log page
  #  And switch to my view if '<ViewName>' exists
 #   And clear all filter component fields
    And filter components are selected
      | Requester GLN           |
      | Responder GLN           |
      | Execution Duration (ms) |
    And the button labeled OK is clicked
    And set 'Requester GLN' filter value in verification logs page
    And set 'Responder GLN' filter value in verification logs page
    And set execution duration range to default value
    And get execution duration range from filter field
  #  And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is click in filter modal pop up
#    And Show Filter Bar button is clicked
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then the filter component contains fields
      | RequestorGln      |
      | ResponderGln      |
      | ExecutionDuration |
    And verify default value for filter component
      | RequestorGln      | <RequestorGln> |
      | ResponderGln      | <ResponderGln> |
      | ExecutionDuration |                |
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | RequestorGln      | <RequestorGln> |
      | ResponderGln      | <ResponderGln> |
      | ExecutionDuration |                |
    And the filters button is clicked in the verification log page
    And filter components are selected
      | ExecutionDuration |
    And set execution duration range to default value
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is click in filter modal pop up
    Examples:
      | user              | RequestorGln  | ResponderGln  | ViewName      |
      | USSC_Wholesaler   | 0363392100056 | 0363391100002 | Auto_Standard |
      | USSC_Manufacturer | 0363391100002 | 0363391100002 | Auto_Standard |

  Scenario Outline: USSC_2008_29,USSC_2008_30,USSC_2008_33:  verify the header details,Serial number field value in blockchain explorer page
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
    And clear all filter component fields
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And Go button is clicked in verification home page
    And get verification log data for filters
    And clear filter requestDate value in verification log
    And set 'GTIN' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    And click on repository explorer link
    Then verify default serial number field '<SerialNumber>' on navigation from verification log page
    Then Navigation menu title is '<BC Navigation Menu title>'
    And click on back navigation button
    Then Navigation menu title is '<VR Log Details Navigation Menu title>'
    Examples:
      | user              | GTIN           | LOT    | SerialNumber | ViewName      | BC Navigation Menu title | VR Log Details Navigation Menu title |
      | USSC_Manufacturer | 00363391180998 | lot004 | test123      | Auto_Standard | Repository Explorer      | Request Details                      |

  Scenario Outline: USSC_2008_32,USSC_2008_35:  PI count value is shown with "--" when the data written to blockchain has failed/not completed or when data is not found
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And get verification log data for filter for field
      | GTIN |
    And clear filter requestDate value in verification log
    And set 'GTIN' filter value in verification logs page
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    And click on repository explorer link
    Then verify default recorded event table from write log page navigation
    Then No Data Found Error message is displayed
    Examples:
      | user              | GTIN           |
      | USSC_Manufacturer | 00363391200016 |


  Scenario Outline: USSC_2008_31,USSC_2008_34,USSC_2011_11,USSC_2011_15:  Verify the BC section display and Recorded events data for the PI found VR entry
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And get verification log data for filters
    And the filters button is clicked in the verification log page
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And set 'GTIN' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And clear filter requestDate value in verification log
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    Then verify repository explorer link is visible
    And click on repository explorer link
    And Validate the GS1 elements on Blockchain Explorer screen
      | GTIN            | <GTINBC>          |
      | Lot             | <LOTBC>           |
      | Expiration Date | <Expiration Date> |
      | PI Count        | <PI Count>        |
    Then Validate the recorded event
      | Timestamp             | <Timestamp>             |
      | Action                | <Action>                |
      | Disposition           | <Disposition>           |
      | GLN                   | <GLN>                   |
      | Written to Blockchain | <Written to Blockchain> |
    Examples:
      | user              | GTIN           | LOT          | Disposition  | Expiration Date         | PI Count    | GTINBC               | LOTBC                          | SerialNumber    | Timestamp                   | Action | GLN           | Written to Blockchain    | ViewName      |
      | USSC_Manufacturer | 00363391180998 | AutoTestTrue | auto_expired | Expiration Date: 220131 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoTestTrue | auto84567889991 | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T09:36:50.218Z | Auto_Standard |


  Scenario Outline: USSC_2008_36,USSC_2008_37,USSC_2008_38,USSC_2008_39 USSC_2011_12, USSC_2011_13,USSC_2011_17:  Verify the BC section display and Recorded events data for the PI found but configured to respond with B1/B2/C1/C2 rule VR entry
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And get verification log data for filters
    And the filters button is clicked in the verification log page
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And set 'GTIN' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And clear filter requestDate value in verification log
    And the verified, '<verified>', is selected on the verification log page
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    And click on repository explorer link
    And Validate the GS1 elements on Blockchain Explorer screen
      | GTIN            | <GTINBC>          |
      | Lot             | <LOTBC>           |
      | Expiration Date | <Expiration Date> |
      | PI Count        | <PI Count>        |
    Then Validate the recorded event
      | Timestamp             | <Timestamp>             |
      | Action                | <Action>                |
      | Disposition           | <Disposition>           |
      | GLN                   | <GLN>                   |
      | Written to Blockchain | <Written to Blockchain> |
    Examples:
      | user              | GTIN           | LOT               | Disposition         | Expiration Date         | PI Count    | GTINBC               | LOTBC                               | SerialNumber    | Timestamp                   | Action | GLN           | Written to Blockchain    | verified |
      | USSC_Manufacturer | 00363391180998 | AutoFalseSuspect  | auto_false_suspect  | Expiration Date: 231231 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoFalseSuspect  | auto13435435341 | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T10:17:43.171Z | All      |
      | USSC_Manufacturer | 00363391180998 | AutoTrueRecalled  | auto_true_recalled  | Expiration Date: 221231 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoTrueRecalled  | auto12345678911 | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T10:27:51.881Z | All      |
      | USSC_Manufacturer | 00363391180998 | AutoFalseRecalled | auto_false_recalled | Expiration Date: 211231 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoFalseRecalled | auto52345678911 | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T10:40:56.438Z | All      |
      | USSC_Manufacturer | 00363391180998 | AutoFalse         | auto_false          | Expiration Date: 220815 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoFalse         | auto72345678911 | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T10:47:57.188Z | All      |


  Scenario Outline: USSC_2008_41,USSC_2008_42,USSC_2008_43,USSC_2011_2, USSC_2011_14, USSC_2011_18:  Verify the BC section display and Recorded events data for the expired PI found but configured to respond with B1 exp/B2 exp/Expired PI with B2 Rule
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set filter value UUID '<UUID>'
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    Then verify repository explorer link is visible
    And click on repository explorer link
    And Validate the GS1 elements on Blockchain Explorer screen
      | GTIN            | <GTINBC>          |
      | Lot             | <LOTBC>           |
      | Expiration Date | <Expiration Date> |
      | PI Count        | <PI Count>        |
    Then Validate the recorded event
      | Timestamp             | <Timestamp>             |
      | Action                | <Action>                |
      | Disposition           | <Disposition>           |
      | GLN                   | <GLN>                   |
      | Written to Blockchain | <Written to Blockchain> |

    Examples:
      | user              | GTIN           | LOT              | Disposition         | Expiration Date         | PI Count    | GTINBC               | LOTBC                               | SerialNumber    | Timestamp                   | Action | GLN           | Written to Blockchain    | AdditionalInfo | verified | UUID                                 |
      | USSC_Manufacturer | 00363391180998 | AutoTestTrue     | auto_expired        | Expiration Date: 220131 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoTestTrue      | auto456788999   | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T09:36:50.218Z | Expired        | true     | c4753bf7-be04-4d51-b350-e43b96b689d4 |
      | USSC_Manufacturer | 00363391180998 | AutoTestTrue     | auto_expired        | Expiration Date: 220131 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoTestTrue      | auto456788999   | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T09:36:50.218Z | Expired        | false    | fddec4fe-3bfa-44ed-8d53-18b4406fd665 |
      | USSC_Manufacturer | 00363391180998 | AutoTrueRecalled | auto_false_recalled | Expiration Date: 211231 | PI Count: 3 | GTIN: 00363391180998 | Lot/Batch Number: AutoFalseRecalled | auto84567889991 | 2021-03-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-11-17T10:40:56.438Z | Recalled       | true     | 049c820a-834b-4cb7-af92-80b663e3bf2b |

  Scenario Outline: USSC_2008_03- Sorting of Requester company Name column in ascending and descending  order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the view sort setting button is clicked
    And the sort option RequesterCompany is selected
    And the button labeled OK is clicked
    Then the RequesterCompany column is sorted in ascending order
    Examples:
      | user              |
      | USSC_Manufacturer |

  Scenario Outline: USSC_2008_03- Sorting of Responder company Name column in ascending and descending  order
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And clear filter requestDate value in verification log
    And the button labeled Go is clicked
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the view sort setting button is clicked
    And the sort option ResponderCompany is selected
    And the button labeled OK is clicked
    Then the ResponderCompany column is sorted in ascending order
    Examples:
      | user              |
      | USSC_Manufacturer |
#NA
  @testNASkip
  Scenario Outline: USSC_2008_04-Verify user is able to save filter with Requester and Responder Company name
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    #And switch to my view if '<ViewName>' exists
 #   And clear all filter component fields
    And filter components are selected
      | Requester Company |
    And the button labeled OK is clicked
    And the RequesterCompany, '<RequesterCompany>', is selected on the verification log page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is click in filter modal pop up
    And Show Filter Bar button is clicked
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then the filter component contains fields
      | RequesterCompany |
    And verify default value for filter component
      | RequesterCompany | <RequesterCompany> |
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | RequesterCompany | <RequesterCompany> |
    Examples:
      | user              | RequesterCompany             | ViewName      |
      | USSC_Wholesaler   | SAP Advanced Track and Trace | Auto_Standard |
      | USSC_Manufacturer | SAP Manufacturer             | Auto_Standard |
#NA
  @testNASkip
  Scenario Outline: USSC_2008_04-Verify user is able to save filter with Requester and Responder Company name
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the verification log page
  #  And clear all filter component fields
    And filter components are selected
      | Responder Company |
    And the button labeled OK is clicked
    And the ResponderCompany, '<ResponderCompany>', is selected on the verification log page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is click in filter modal pop up
    And Show Filter Bar button is clicked
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then the filter component contains fields
      | ResponderCompany |
    And verify default value for filter component
      | ResponderCompany | <ResponderCompany> |
    And Go button is clicked in verification home page
    Then the results contains the filtered values in the response
      | ResponderCompany | <ResponderCompany> |
    Examples:
      | user            | ResponderCompany | ViewName      |
      | USSC_Wholesaler | SAP Manufacturer | Auto_Standard |


  Scenario Outline: USSC_11_1- "Verification log"-> Select and Save Default Views -> Standard
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And switch to my view if '<ViewName>' exists
    And the filters button is clicked in the verification log page
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And '<ViewName>' is set as default view on the verification log page
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | RequestTime    |
      | GTIN           |
      | LOT            |
      | SerialNumber   |
      | Verified       |
      | AdditionalInfo |
      | RequestorGln   |
      | ResponderGln   |
    And the user clicks on sign out button
    And the button labeled OK is clicked
    And the user clicks log in again
    And the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    Then verify selected filter components are available
      | RequestTime    |
      | GTIN           |
      | LOT            |
      | SerialNumber   |
      | Verified       |
      | AdditionalInfo |
      | RequestorGln   |
      | ResponderGln   |
    Examples:
      | user              | ViewName      |
      | USSC_Manufacturer | Auto_Standard |
  #Fixed
  Scenario Outline: USSC_2011_3,USSC_2011_4,USSC_2011_5,USSC_2011_6:  WHO Portal Verification_B1 Response_B1 exp  Response_FALSE_NoReasonProvided_True response_BC section not visble in VR log details
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And get verification log data for filters
    And the filters button is clicked in the verification log page
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And set 'GTIN' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And clear filter requestDate value in verification log
    And the verified, '<Verified>', is selected on the verification log page
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    Then verify repository explorer link is not visible
    Examples:
      | user            | GTIN           | LOT              | SerialNumber   | Verified |
      | USSC_Wholesaler | 00363391180998 | AutoTestTrue     | auto9234567891 | All      |
      | USSC_Wholesaler | 00363391180998 | AutoTrueRecalled | auto1234567891 | All      |
      | USSC_Wholesaler | 00363391180998 | AutoTestTrue     | auto456788999  | All      |
      | USSC_Wholesaler | 00363391180998 | AutoBatch20      | auto5576567892 | All      |
#Fixed
  Scenario Outline: USSC_2011_1, USSC_2011_24: Verify Blockchain section is not visible for all the existing verification records in VR logs and Vr log count
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
    And select all filter fields
    And clear all filter fields in verification logs page
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    Then the verification log record count is displayed
    And click on first record of verification log
    Then verify repository explorer link is not visible
    Examples:
      | user            |
      | USSC_Wholesaler |


  Scenario Outline: USSC_2011_16:  MAH B2B Verification_FALSE_NoReasonProvided_ Response_BC section  visble in VR log details
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And get verification log data for filters
    And the filters button is clicked in the verification log page
    And select all filter fields
    And the button labeled OK is clicked
    And clear all filter fields in verification logs page
    And set 'GTIN' filter value in verification logs page
    And set 'lot' filter value in verification logs page
    And set 'Serial Number' filter value in verification logs page
    And clear filter requestDate value in verification log
    And Go button is clicked in verification home page
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    Then verify repository explorer link is visible
    Examples:
      | user              | GTIN           | LOT    | SerialNumber |
      | USSC_Manufacturer | 00363391180998 | lot004 | test123      |

  @test111
    @MH_01
  Scenario Outline: USSC_2011_35,USSC_2011_36,USSC_2011_37,USSC_2011_38,USSC_2011_39:  MAH Portal Verification_VR with special characters
    Given the user logs in as '<user>' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And clear filter requestDate value in verification log
    And Go button is clicked in verification home page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    Then the results contains the filtered values in the response
      | LOT           | <LOT>          |
      | Serial Number | <SerialNumber> |
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    Then the request details contains
      | SerialNumber | <SerialNumber> |
      | LOT          | <LOT>          |
    And click on repository explorer link
    And Validate the GS1 elements on Blockchain Explorer screen
      | GTIN            | <GTINBC>          |
      | Lot             | <LOTBC>           |
      | Expiration Date | <Expiration Date> |
      | PI Count        | <PI Count>        |
    Then Validate the recorded event
      | Timestamp             | <Timestamp>             |
      | Action                | <Action>                |
      | Disposition           | <Disposition>           |
      | GLN                   | <GLN>                   |
      | Written to Blockchain | <Written to Blockchain> |
    Examples:
      | user              | GTIN           | LOT         | SerialNumber | Verified | Disposition | Expiration Date         | PI Count    | GTINBC               | LOTBC                         | Timestamp                   | Action | GLN           | Written to Blockchain    |
      | USSC_Manufacturer | 00363391180998 | AutoLot"001 | test.123     | All      | qatest      | Expiration Date: 231231 | PI Count: 5 | GTIN: 00363391180998 | Lot/Batch Number: AutoLot"001 | 2021-02-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-10-30T00:21:44.318Z |
      #| USSC_Manufacturer | 00363391180998 | AutoLot-001 | test-123     | All      | qatest      | Expiration Date: 23-12-31 | PI Count: 4 | GTIN: 00363391180998 | Lot/Batch Number: AutoLot-001 | 2021-02-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-10-30T00:21:43.371Z |
      #| USSC_Manufacturer | 00363391180998 | AutoLot_001 | test_123     | All      | qatest      | Expiration Date: 23-12-31 | PI Count: 4 | GTIN: 00363391180998 | Lot/Batch Number: AutoLot_001 | 2021-02-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-10-30T00:21:43.832Z |
      #| USSC_Manufacturer | 00363391180998 | AutoLot/001 | test/123     | All      | qatest      | Expiration Date: 23-12-31 | PI Count: 4 | GTIN: 00363391180998 | Lot/Batch Number: AutoLot/001 | 2021-02-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-10-30T00:21:42.38Z  |
      #| USSC_Manufacturer | 00363391180998 | AutoLot.001 | test"123     | All      | qatest      | Expiration Date: 23-12-31 | PI Count: 4 | GTIN: 00363391180998 | Lot/Batch Number: AutoLot.001 | 2021-02-26T12:39:06.515506Z | Add    | 0363391100002 | 2022-10-30T00:21:42.89Z  |

  Scenario: USSC_2011_33 - Validate status code filter dropdown values
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
    And filter components are selected
      | Response Code |
    And the button labeled OK is clicked
    And clear filter Response Code value in verification log
    Then verify Response Code filter dropdown values
      | 200 |
      | 400 |
      | 403 |
      | 404 |
      | 408 |
      | 500 |
      | 502 |
      | 504 |
      | All |