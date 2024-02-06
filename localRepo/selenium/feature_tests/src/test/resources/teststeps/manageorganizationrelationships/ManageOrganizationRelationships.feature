@ManageOrganizations
Feature: Manage Organization Relationships App

  Background:
    Given the user logs in as 'MOR_User' in the login page
    And the ManageOrganizationRelationships app is clicked in the USSC launcher page

    # TODO: PHN_19662_2  To be added lateron
#  Scenario Outline: PHN_19662_2     To verify the Manage Organization Relationship tab under Relationship section
#    And input <PNID> in search filter
#    And click on 'Go' button
#    Then customer record should be displayed with PNID of <PNID>
#    And open first message
#    Then customer detail page should be displayed
#    Then Manage Organization Relationships link should be displayed under Relationship section
#    Examples:
#      | PNID     |
#      | 12345678 |


  Scenario: PHN_19662_3     To verify the Overview page for  Manage Organization Relationship app
    Then the 'Manage Organization Relationships' list page is displayed on the page
    Then 'various filter' field should be displayed on overview page
    Then 'table columns' field should be displayed on overview page
    Then 'create button' field should be displayed on overview page
    Then 'setting button' field should be displayed on overview page
    Then 'download button' field should be displayed on overview page
    Then 'adapt filter button' field should be displayed on overview page

    #TODO: PHN_19662_4  To be confirmed
  Scenario: PHN_19662_4     To verify Create relationship page field of Organization Relationship app
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Create' button
    Then create page should open
    And click on scenario drop down
    Then scenario drop down should be displayed with respective values
    And select <Scenario> in Scenario drop down in detail page
    Then 'Inviter Organization' should be displayed under details section
    Then 'Invitee Organization' should be displayed under details section
    And select <Inviter Organization> in 'Inviter Organization' in detail page
    And select <Invitee Organization> in 'Invitee Organization' in detail page
    Then 'Inviter GLN' should be displayed under details section
    Then 'Invitee GLN' should be displayed under details section
    And select <Inviter GLN> in 'Inviter GLN' in detail page
    And select <Invitee GLN> in 'Invitee GLN' in detail page
    Then 'Inviter Role' should be displayed under details section
    Then 'Invitee Role' should be displayed under details section
    And select <Inviter Role> in 'Inviter Role' in detail page
    And select <Invitee Role> in 'Invitee Role' in detail page
    Then 'Message Type' should be displayed under details section
#    Then 'Inviter Company Prefix' should be disabled under 'Relationship' section for <Scenario>

  Scenario: PHN_19662_5     Validate All the selected filters from 'Adapt filter' should show on UI
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Adapt Filters' button
    And select filter options
      | Inviter PNID           |
      | Inviter GLN            |
      | Invitee PNID           |
      | Invitee GLN            |
      | Scenario               |
      | Connection Status      |
      | Send / Receive Message |
      | Message Type           |
      | Changed on             |
      | Changed by             |
      | Created on             |
      | Created by             |
    And click on 'OK' button
    And click on 'Go' button
    Then it will show all the filters on the overview page
      | Inviter PNID           |
      | Inviter GLN            |
      | Invitee PNID           |
      | Invitee GLN            |
      | Scenario               |
      | Connection Status      |
      | Send / Receive Message |
      | Message Type           |
      | Changed on             |
      | Changed by             |
      | Created on             |
      | Created by             |

    #TODO:  PHN_19662_6 To be confirmed
  Scenario: PHN_19662_6     Validate All the selected filters from 'Adapt filter' should show on UI.
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Adapt Filters' button
    And select filter options
      | Inviter Organization   |
      | Inviter GLN            |
      | Invitee Organization   |
      | Connection Status      |
      | Send / Receive Message |
      | Message Type           |
      | Created on             |
    And click on 'OK' button
    And click on 'Go' button
    Then it will show all the filters on the overview page
      | Inviter Organization   |
      | Inviter GLN            |
      | Invitee Organization   |
      | Connection Status      |
      | Send / Receive Message |
      | Message Type           |
      | Created on             |

  Scenario: PHN_19662_7     Validate Adapt filter search functionality.
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Adapt Filters' button
    And search for 'Message Type' in Adapt Filters
    And click on search button in Adapt Filters
    Then search result should be displayed with 'Message Type' in Adapt Filters

  Scenario: PHN_19662_8     Validate drop-down values of Message Type field
    And click on 'Message Type' drop-down
    Then 'Message Type' drop-down should have  following values
      | SNR          |
      | EPCIS        |
      | CLOSED_ENV   |
      | SSCC         |
      | PML          |
      | RegReporting |

  Scenario: PHN_19662_9     Validate drop-down values of Send/Receive field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Send/Receive Message' drop-down
    Then 'Send/Receive Message' drop-down should have  following values
      | Send    |
      | Receive |

  Scenario Outline: PHN_19662_10     Verify Changed On Timestamp field with calender option
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select last update time <FromDate> and <ToDate> for 'Changed on' field
    And click on 'Go' button
    Then data should be displayed with 'Changed on' between <FromDate> and <ToDate>
    Examples:
      | FromDate     | ToDate       |
      | "2022-01-01" | "2023-07-07" |
      | "2022-04-01" | "2023-07-07" |

  Scenario Outline: PHN_19662_11     Verify  Changed On field by writing th dates
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And input date in "Changed on" field with <ChangedOnFromDate> and <ChangedOnToDate>
    And click on 'Go' button
    Then data should be displayed with 'Changed on' between <ChangedOnFromDate> and <ChangedOnToDate>
    Examples:
    Examples:
      | ChangedOnFromDate | ChangedOnToDate |
      | "Feb 10, 2022"    | "Jul 07, 2023"  |
      | "Oct 5, 2022"     | "Jul 07, 2023"  |

  Scenario Outline: PHN_19662_12     Verify Created On Timestemp field with calender option
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select last update time <FromDate> and <ToDate> for 'Created on' field
    And click on 'Go' button
    Then data should be displayed with 'Created on' between <FromDate> and <ToDate>
    Examples:
      | FromDate     | ToDate       |
      | "2022-01-01" | "2023-07-07" |
      | "2023-04-01" | "2023-07-07"  |

  Scenario Outline: PHN_19662_13     Verify  Created On field by writing th dates
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And input date in "Created on" field with <CreatedOnFromDate> and <CreatedOnToDate>
    And click on 'Go' button
    Then data should be displayed with 'Created on' between <CreatedOnFromDate> and <CreatedOnToDate>
    Examples:
    Examples:
      | CreatedOnFromDate | CreatedOnToDate |
      | "Feb 10, 2022"    | "Jul 07, 2023"  |
      | "Oct 5, 2022"     | "Jul 07, 2023"  |

  Scenario Outline: PHN_19662_14-1     Verify  Searched field filter
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And input <Inviter GLN> in 'Inviter GLN' filter
    And click on 'Go' button
    Then data should be displayed with 'Inviter GLN' of <Inviter GLN>
    Examples:
      | Inviter GLN   |
      | 2183374593717 |

  Scenario Outline: PHN_19662_14-2     Verify  Searched field filter
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And input <Invitee GLN> in 'Invitee GLN' filter
    And click on 'Go' button
    Then data should be displayed with 'Invitee GLN' of <Invitee GLN>
    Examples:
      | Invitee GLN    |
      | 51533666000146 |

  Scenario Outline: PHN_19662_14-3     Verify  Searched field filter
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And input <Inviter PNID> in 'Inviter Organization' filter
    And click on 'Go' button
    Then data should be displayed with 'Inviter Organization' of <Inviter PNID>
    Examples:
      | Inviter PNID          |
      | testna_20230615023211 |

  Scenario Outline: PHN_19662_14-4     Verify  Searched field filter
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And input <Invitee PNID> in 'Invitee Organization' filter
    And click on 'Go' button
    Then data should be displayed with 'Invitee Organization' of <Invitee PNID>
    Examples:
      | Invitee PNID           |
      | PNTESTREGCOLLABORG2305 |

  Scenario Outline: PHN_19662_15-1     Verify Inviter Organization field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Inviter PNID> in 'Inviter Organization'
    And click on 'Go' button
    Then data should be displayed with 'Inviter Organization' of <Inviter PNID>
    Examples:
      | Inviter PNID          |
      | testna_20230615023211 |

  Scenario Outline: PHN_19662_15-2     Verify Invitee Organization field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Invitee PNID> in 'Invitee Organization'
    And click on 'Go' button
    Then data should be displayed with 'Invitee Organization' of <Invitee PNID>
    Examples:
      | Invitee PNID           |
      | PNTESTREGCOLLABORG2305 |

  Scenario Outline: PHN_19662_15-3     Verify Inviter GLN field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Inviter GLN> in 'Inviter GLN'
    And click on 'Go' button
    Then data should be displayed with 'Inviter GLN' of <Inviter GLN>
    Examples:
      | Inviter GLN   |
      | 2183374593717 |

  Scenario Outline: PHN_19662_15-4     Verify Invitee GLN field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Invitee GLN> in 'Invitee GLN'
    And click on 'Go' button
    Then data should be displayed with 'Invitee GLN' of <Invitee GLN>
    Examples:
      | Invitee GLN    |
      | 51533666000146 |

  Scenario Outline: PHN_19662_15-5     Verify Scenario field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Scenario> in 'Scenario' drop down
    And click on 'Go' button
    Then data should be displayed with 'Scenario' of <Scenario>
    Examples:
      | Scenario                        |
      | Regulatory Reporting for Brazil |

  Scenario Outline: PHN_19662_15-6     Verify Send/Receive Message field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Send/Receive Message> in 'Send/Receive Message' drop down
    And click on 'Go' button
    Then data should be displayed with 'Send/Receive Message' of <Send/Receive Message>
    Examples:
      | Send/Receive Message                     |
      | Receive, Regulatory Reporting for Brazil |

  Scenario Outline: PHN_19662_15-7     Verify Message Type field
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Message Type> in 'Message Type' drop down
    And click on 'Go' button
    Then data should be displayed with 'Message Type' of <Message Type>
    Examples:
      | Message Type                                  |
      | RegReporting, Regulatory Reporting for Brazil |

  Scenario Outline: PHN_19662_16     Validate Filter Options with various combination of fileds
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Inviter PNID> in 'Inviter Organization'
    And select <Inviter GLN> in 'Inviter GLN'
    And select <Message Type> in 'Message Type' drop down
    And click on 'Go' button
    Then data should be displayed with 'Inviter Organization' of <Inviter PNID>
    Then data should be displayed with 'Inviter GLN' of <Inviter GLN>
    Then data should be displayed with 'Message Type' of <Message Type>
    Examples:
      | Inviter PNID          | Inviter GLN   | Message Type                                  |
      | testna_20230615023211 | 2183374593717 | RegReporting, Regulatory Reporting for Brazil |

  Scenario Outline: PHN_19662_17     Validate Clear button functionality by adding values to all filters and clicking on clear button
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And get the message count
    And select <Message Type> in 'Message Type' drop down
    And click on 'Go' button
    Then data should be displayed with 'Message Type' of <Message Type>
    And click on Adapt Filters
    And reset the Adapt Filters
    And click on 'Go' button
    Then all the filters should get removed and it should display the whole data
    Examples:
      | Message Type                                  |
      | RegReporting, Regulatory Reporting for Brazil |

  Scenario: PHN_19662_18     Validate  Message Count on Home Screen
    Then the 'Manage Organization Relationships' list page is displayed on the page
    Then message count in list report should match

  Scenario Outline: PHN_19662_19     Verify that Obt team can can download the  data based on selected filters
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And select <Invitee PNID> in 'Invitee Organization'
    And click on 'Go' button
    And click on export to spreadsheet icon
    Then downloaded <downloadFileName> in the spreadsheet should be same as showing in the list page
    Examples:
      | Invitee PNID           | downloadFileName   |
      | PNTESTREGCOLLABORG2305 | Relationships.xlsx |

  Scenario: PHN_19662_20     Verify that Obt team can can download the  data based in excel format
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on export to spreadsheet icon
    Then user should be able to download the result in '.xlsx' format

    #TODO: PHN_19662_21   To be confirmed
  Scenario: PHN_19662_21     Validate that all selected columns in the setting field are visible on the home screen
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on setting icon
    And select all fields in setting popover
    And click on 'OK' button
    Then selected columns will be shown up in the list report page
      | Inviter PNID / GLN     |
      | Invitee PNID / GLN     |
      | Scenario               |
      | Send / Receive Message |
      | Message Type           |
      | Created on             |
      | Connection Status      |

  Scenario: PHN_19662_22     Validate that only selected columns in the setting field are visible on the home screen
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on setting icon
    And select fields in setting popover
      | Inviter PNID / GLN |
      | Invitee PNID / GLN |
      | Scenario           |
      | Message Type       |
      | Connection Status  |
    And click on 'OK' button
    Then selected columns will be shown up in the list report page
      | Inviter PNID / GLN |
      | Invitee PNID / GLN |
      | Scenario           |
      | Message Type       |
      | Connection Status  |

    #TODO: PHN_19662_23  Bug to be fixed
  Scenario: PHN_19662_23     Validate error message for all the mandatory fields
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Create' button
    Then create page should open
    And click on 'Create' button
#    Then 'No change to save' error message should display


  Scenario Outline: PHN_19662_24     Validate user creates duplicate relationship between same inviter and invitee
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Create' button
    Then create page should open
    And click on scenario drop down
    And select <Scenario> in Scenario drop down in detail page
    And select <Inviter Organization> in 'Inviter Organization' in detail page
    And select <Invitee Organization> in 'Invitee Organization' in detail page
    And select <Inviter GLN> in 'Inviter GLN' in detail page
    And select <Invitee GLN> in 'Invitee GLN' in detail page
    And select <Inviter Role> in 'Inviter Role' in detail page
    And select <Invitee Role> in 'Invitee Role' in detail page
    And save the relationship
    Then 'Invitation already exists for the business partner' error message should be displayed in detail page
    Examples:
      | Scenario                           | Inviter Organization                     | Invitee Organization                              | Inviter GLN   | Invitee GLN   | Inviter Role | Invitee Role |
      | Supply Chain Partner Collaboration | MAH_ATTP_PORTAL1 (ATTP MAH Organisation) | PNCONTRACTMAN20160811 (Contract Manufacturer One) | 4063973003030 | 9010102000007 | MAH          | CMO          |

