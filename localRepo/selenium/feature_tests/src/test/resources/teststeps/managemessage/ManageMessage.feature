@ManageMessage
Feature: Manage Message App

  Background:
    Given the user logs in as 'MM_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page

  Scenario: BR_MM_2108_01 Validate that BP/Customer who is having access to Message Manager app is able to open the app
    Then the ManageMessages page should be visible

  Scenario: BR_MM_2108_03 Validate that If there are message exchange happens between the BP and Customer,  data should be present in the table
    Then data should be present in the table

  Scenario: BR_MM_2108_04 Validate All the selected filters from 'Adapt filter' should show on UI
    And click on 'Adapt Filters' button
    And select Basic filters
      | GTIN       |
      | Changed On |
      | Lot Number |
      | Type       |
    Then all basic filters should get selected
    And select Addition filters
      | Business Document ID |
      | Correlation ID       |
      | Instance ID          |
      | Message Status       |
      | Receiver             |
      | Receiver Code        |
      | Review Status        |
      | Sender               |
      | Sender Code          |
    Then all additional filters should get selected
    And click on 'OK' button
    Then all the filters show on the UI page

  Scenario: BR_MM_2108_05 Validate only the selected filters from 'Adapt filter' should show on UI
    And click on 'Adapt Filters' button
    And select Basic filters
      | GTIN       |
      | Changed On |
    Then all basic filters should get selected
    And select Addition filters
      | Business Document ID |
      | Correlation ID       |
      | Message Status       |
      | Receiver             |
      | Review Status        |
      | Sender               |
    Then all additional filters should get selected
    And click on 'OK' button
    Then only the selected filters show on the UI page

  Scenario: BR_MM_2108_06 Validate drop-down values of Message Type field
    Then <"Type"> should have following drop-down values
      | Brazil Horizontal Reporting |

  Scenario: BR_MM_2108_07 Validate drop-down values of Message Status field
    Then <"Message Status"> should have following drop-down values
      | Error               |
      | On Hold             |
      | Processing          |
      | Retrigger Scheduled |
      | Retry in Process    |
      | Successful          |

  Scenario: BR_MM_2108_08 Validate drop-down values of Review Status field
    Then <"Review Status"> should have following drop-down values
      | Open              |
      | In Progress       |
      | In Review         |
      | Completed         |
      | To be Re-analyzed |

  Scenario Outline: BR_MM_2108_09 Verify Message Timestemp field with calender option
    And click on calendar in Changed On filter
    Then calendar should open
    And select changed on date <FromDate> and <ToDate>
    And click on 'Go' button
    Then data should display for the selected dates with "Changed On" from <FromDate> and <ToDate>
    Examples:
      | FromDate     | ToDate       |
      | "2022-01-01" | "2023-05-15" |
      | "2023-04-01" | "2023-05-20" |

  Scenario Outline: BR_MM_2108_10 Verify Message Timestamp field by writing th dates
    And input date in "Created On" filter with <CreatedOnFromDate> and <CreatedOnToDate>
    And input date in "Changed On" filter with <ChangedOnFromDate> and <ChangedOToDate>
    And click on 'Go' button
    Then data should display for the selected dates with "Created On" from <CreatedOnFromDate> and <CreatedOnToDate>
    Then data should display for the selected dates with "Changed On" from <ChangedOnFromDate> and <ChangedOToDate>
    Examples:
      | CreatedOnFromDate | CreatedOnToDate | ChangedOnFromDate | ChangedOToDate |
      | "Jan 1, 2022"     | "May 15, 2023"  | "Feb 10, 2022"    | "May 19, 2023" |
      | "Feb 15, 2022"    | "May 10, 2023"  | "Oct 5, 2022"     | "May 20, 2023" |

  Scenario Outline:BR_MM_2108_11	Message Manager app	Verify Lot No field filter
    And Write <lotNumber> in LOT Filter
    Then check user should be able to write LOT in the empty field
    And Click on Go button
    Then LOT Related data must get displayed for the searched <lotNumber>
    Examples:
      | lotNumber |
      | LTBR0154B |

  Scenario Outline:BR_MM_2108_12	Message Manager app	Verify GTIN  field filter
    And Write <GTIN> in GTIN Filter
    Then check user should be able to write GTIN in the empty field
    And Click on Go button
    Then GTIN Related data must get displayed for the searched <GTIN>
    Examples:
      | GTIN           |
      | 04063973000930 |

  Scenario Outline:BR_MM_2108_13	Message Manager app	Verify Search field filter.
    And Write <Value> in SEARCH Filter
    Then check user should be able to write SEARCH in the empty field
    And Click on Go button
    Then SEARCH Related data must get displayed for the searched <Value>
    Examples:
      | Value           |
      | PNTESTREGCOLLAB |
      | TestPartner     |
      | 52434771000190  |
      | 51533666000146  |

  Scenario Outline:BR_MM_2108_14	Message Manager app	Verify Sender field filter.
    And Select <Sender> in SENDER Filter
    Then check user should be able to Select <Sender> in the SENDER Filter
    And Click on Go button
    Then SENDER Related data must get displayed for the searched <Sender>
    Examples:
      | Sender                |
      | TestPartner InvCom001 |

  Scenario Outline:BR_MM_2108_15	Message Manager app	Verify Receiver field filter.
    And Select <Receiver> in RECEIVER Filter
    Then check user should be able to Select <Receiver> in the RECEIVER Filter
    And Click on Go button
    Then RECEIVER Related data must get displayed for the searched <Receiver>
    Examples:
      | Receiver              |
      | TestPartner InvCom001 |

  Scenario Outline:BR_MM_2108_16	Message Manager app	Verify Receiver code field filter
    And Write <ReceiverCode> in RECEIVERCODE Filter
    Then check user should be able to write RECEIVERCODE in the empty field
    And Click on Go button
    Then RECEIVERCODE Related data must get displayed for the searched <ReceiverCode>
    Examples:
      | ReceiverCode   |
      | 52434771000190 |

  Scenario Outline:BR_MM_2108_17	Message Manager app	Verify Sender code field filter
    And Write <SenderCode> in SENDERCODE Filter
    Then check user should be able to write SENDERCODE in the empty field
    And Click on Go button
    Then SENDERCODE Related data must get displayed for the searched <SenderCode>
    Examples:
      | SenderCode     |
      | 52434771000190 |

  Scenario Outline:BR_MM_2108_18	Message Manager app	Verify message status field filter
    And Select <Review> in REVIEW Filter
    Then check user should be able to Select <Review> in the REVIEW Filter
    And Click on Go button
    Then REVIEW Related data must get displayed for the searched <Review>
    Examples:
      | Review |
      | Open   |

  Scenario Outline:BR_MM_2108_19	Message Manager app	Verify Correlation ID field filter
    And Write <Correlation> in CORRELATION Filter
    Then check user should be able to write CORRELATION in the empty field
    And Click on Go button
    Then CORRELATION Related data must get displayed for the searched <Correlation>
    Examples:
      | Correlation                  |
      | AGRwg0Bvij2QWBgBKipjtuj2ijTh |

  Scenario Outline:BR_MM_2108_20	Message Manager app	Verify Business doc ID field filter
    And Write <BusinessID> in BUSINESS Filter
    Then check user should be able to write BUSINESS in the empty field
    And Click on Go button
    Then BUSINESS Related data must get displayed for the searched <BusinessID>
    Examples:
      | BusinessID |
      |            |
    #TODO
  Scenario Outline: BR_MM_2108_21 Verify Instance ID field filter
    And Enter any <Instance ID> in the <columnName> Filter
    Then User should be able to write in the <columnName> field
    And click on 'Go' button
    Then Related data must get displayed for the searched <Instance ID>
    Examples:
      | Instance ID                      | columnName |
      | 005056B63F751EE886F3C65685B5BF33 | InstanceID |

  Scenario Outline: BR_MM_2108_22 Validate Filter Options with various combination of fields.
    And  Select the column <reviewStatusColumn> '<ReviewStatus>'
    And  Enter any <Co-relation id> in the <CorrelationIdColumn> Filter
    And  Select the column <messageStatusColumn> '<MessageStatus>'
    And  Select the column <messageTypeColumn> '<MessageType>'
    And  click on 'Go' button
    Then Only the messages of selected filters <Co-relation id> should be displayed
    Examples:
      | ReviewStatus | Co-relation id               | MessageStatus | MessageType                        | CorrelationIdColumn | reviewStatusColumn | messageStatusColumn | messageTypeColumn |
      | Open         | AGRZ2wKYBOkG0DL0zm_HMW-oeFGx | Successful    | Supply Chain Partner Collaboration | CorrelationID       | ReviewStatus       | MessageStatus       | Scenario          |

  Scenario: BR_MM_2108_23 Validate  Collapse Header icon functionality.
    And  Click on Collapse Header icon
    Then The filter bar should be removed from the displayed screen


  Scenario: BR_MM_2108_24 Validate  Collapse Header icon functionality.
    And  Click on Collapse Header icon
    And  Click on Expand Header icon
    Then The filter bar should be returned to the displayed screen

  Scenario Outline: BR_MM_2108_25 Validate Clear button functionality by adding values to all filters and clicking on clear button
    And Enter '<SenderOrganisationName>' in the Search Filter
    And Select the column <messageTypeColumn> '<MessageType>'
    And Get message count
    And click on 'Go' button
    And click on 'Clear' button
    Then All the applied filters should get removed and it should shows the whole data
    Examples:
      | SenderOrganisationName    | messageTypeColumn | MessageType |
      | Contract Manufacturer One | Scenario          | General     |

  Scenario: BR_MM_2108_26 Validate  Message Count on Home Screen
    And Validate Message Count on Home Screen of Message Manager app
    And Valid count should be present.

  Scenario Outline: BR_MM_2108_27 Verify that BP/Customer is able to download the Message records in .xlsx format
    And Click on download icon
    Then verify xsl format <fileName> is downloaded
    Examples:
      | fileName |
      | Messages |

  Scenario Outline: BR_MM_2108_28 Verify that BP/Customer can download the Messages logs based on selected filters
    And Write any <Co-relation id> in the <CorrelationIdColumn> Filter
    And click on 'Go' button
    And Click on download icon
    Then Downloaded data in the <fileName> excel sheet must be same as showing in the Home page
    Examples:
      | Co-relation id                       | CorrelationIdColumn | fileName      |
      | 0babd376-238f-47cd-bc87-6c014719d086 | CorrelationID       | Messages.xlsx |

  Scenario: BR_MM_2108_29 Validate that all selected columns in the setting field are visible on the home screen
    When Click on Settings icon
    Then View Settings pop up must open
    And Click select all check box
    And click on 'OK' button
    Then It should display the columns on the home display
      | Message Status         |
      | Business Document ID   |
      | Additional Information |
      | Number of Retriggers   |
      | GTINs                  |
      | Type                   |
      | Sub Type               |
      | Sender                 |
      | Receiver               |
      | Review Status          |
      | Changed By             |
      | Created On             |
      | Changed On             |
      | Sender Code            |
      | Receiver Code          |
      | Correlation ID         |
      | Instance ID            |
      | Lot Numbers            |

  Scenario: BR_MM_2108_30 Validate that only selected columns in the setting field are visible on the home screen
    When Click on Settings icon
    Then View Settings pop up must open
    And Select only few fields
      | Type           |
      | Sub Type       |
      | Sender         |
      | Changed By     |
      | Created On     |
      | Changed On     |
      | Sender Code    |
      | Receiver Code  |
      | Correlation ID |
      | Instance ID    |
      | Lot Numbers    |
    And click on 'OK' button
    Then It should display the columns on the home display
      | Message Status       |
      | Business Document ID |
      | GTINs                |
      | Receiver             |
      | Review Status        |


  Scenario Outline: BR_MM_2108_31- Validate the sort functionality for single field in descending order
    When Click on Settings icon
    Then View Settings pop up must open
    And Click select all check box
    And Click on Sort option
    Then Sort view page will open
    And Select <fieldName> option from the list and select Descending order
    Then <fieldName> and Descending order options must get selected
    And Click on OK button
    Then On the home screen data will show up as <fieldName> Descending order
    Examples:
      | fieldName              |
      | Business Document ID   |
      | Additional Information |
      | Number of Retriggers   |
      | GTINs                  |
      | Type                   |
      | Sub Type               |
      | Sender                 |
      | Receiver               |
      | Message Status         |
      | Review Status          |
      | Changed By             |
      | Created On             |
      | Changed On             |
      | Sender Code            |
      | Receiver Code          |
      | Correlation ID         |
      | Instance ID            |
      | Lot Numbers            |

  Scenario Outline: BR_MM_2108_32- Validate the sort functionality for single field in ascending order
    When Click on Settings icon
    Then View Settings pop up must open
    And Click select all check box
    And Click on Sort option
    Then Sort view page will open
    And Select <fieldName> option from the list and select Ascending order
    Then <fieldName> and Ascending order options must get selected
    And Click on OK button
    Then On the home screen data will show up as <fieldName> Ascending order
    Examples:
      | fieldName              |
      | Message Status         |
      | Business Document ID   |
      | Additional Information |
      | Number of Retriggers   |
      | GTINs                  |
      | Type                   |
      | Sub Type               |
      | Sender                 |
      | Receiver               |
      | Review Status          |
      | Changed By             |
      | Created On             |
      | Changed On             |
      | Sender Code            |
      | Receiver Code          |
      | Correlation ID         |
      | Instance ID            |
      | Lot Numbers            |

  Scenario: BR_MM_2108_33- Validate the sort functionality for multiple field
    When Click on Settings icon
    Then View Settings pop up must open
    And Click on Sort option
    Then Sort view page will open
    And Select GTINs option from the list and select Ascending order
    Then GTINs and Ascending order options must get selected
    And Click on Add Sort Criterion button
    Then One more sort field added
    And Select Lot Numbers option from the list and select Descending order
    Then Lot Numbers and Descending order options must get selected
    And Click on OK button
    Then On the home screen data will show up as GTINs Ascending order
    Then On the home screen data will show up as Lot Numbers Descending order
    #TODO Failing at last step

  Scenario:BR_MM_2108_34- Validate Clear Sorting functionality
    When Click on Settings icon
    Then View Settings pop up must open
    And Click on Sort option
    Then Sort view page will open
    And Click on Remove Sort Criterion button
    And Click on OK button
    Then Data will not be in any of the sorting order

  Scenario: BR_MM_2108_35- Validate Adapt filter search functionality
    When Click on Adapt Filters button
    Then Adapt Filters pop up must open
    And Search for GTIN in the search option
    Then GTIN should come as search result

  Scenario: BR_MM_2108_36- Validate in 'All tab' all the messages which has been sent or received both are coming
    When Check message logs in All tab
    Then In 'All tab' all the sent and received message logs are coming

  Scenario: BR_MM_2108_37- Validate in 'Sent tab' all the messages which has been sent only those are coming
    When Check message logs in Sent tab
    Then In 'Sent tab' all the message which has been sent by that customer only those should show

  Scenario: BR_MM_2108_38- Validate in 'Received tab' all the messages which has been Received only those are coming
    When Check message logs in Received tab
    Then In 'Received tab' all the message which has been received by that customer only those should show

  Scenario Outline: BR_MM_2108_39- Validate 'Create message' functionality by uploading Activation Message file from BP account to Customer which result to Successful
    When Click on Create button
    Then New Message pop up must open
    And Select the '<message type>'
    Then <message type> will get selected
    And Attach the message file in xml format
    And Enter the <valid info> in additional information field
    Then 'Accept message' file should get uploaded
    And Click on Upload button
    Then 'File uploaded successfully' message should display
    And Open the CPI tenant and check for the message which has been sent from UI and copy the co-relation id
    Then All the related iflows should get hit with completed status
    And Now Paste the same corelation in the search box and click on GO button.
    Then Result should show for the searched co-relation ID
    And Validate the Message with all the detail.
    Then Sender, Receiver, Lot No., GTIN, Business doc ID information should match with the attached payload information
    Examples:
      | message type                    | valid info                               |
      | Regulatory Reporting for Brazil | Comments for Activation Message Scenario |
      | Dispatch                        | Comments for Activation Message Scenario |
      | Receive                         | Comments for Activation Message Scenario |
      | Finalization                    | Comments for Activation Message Scenario |

  Scenario Outline: BR_MM_2108_40- Validate 'Create message' functionality by uploading Activation Message file BP account to Custome which result to Error
    When Click on Create button
    Then New Message pop up must open
    And Select the '<message type>'
    Then <message type> will get selected
    And Attach the message file BR_MM_2108_40_1.xml in xml format
    And Enter the <valid info> in additional information field
    Then 'Accept message' file should get uploaded
    And Click on Upload button
    Then 'File uploaded successfully' message should display
    And Open the CPI tenant and check for the message which has been sent from UI and copy the co-relation id
    Then All the related iflows should get hit with completed status
    And Now Paste the same corelation in the search box and click on GO button.
    Then  Result should show for the searched co-relation ID
    And Validate the Message with all the detail.
    Then Sender, Receiver, Lot No., GTIN, Business doc ID information should match with the attached payload information
    Examples:
      | message type                    | valid info                               |
      | Regulatory Reporting for Brazil | Comments for Activation Message Scenario |
      | Dispatch                        | Comments for Activation Message Scenario |
      | Receive                         | Comments for Activation Message Scenario |
      | Finalization                    | Comments for Activation Message Scenario |

  Scenario: BR_MM_2108_41- Validate upload button functionality by attaching ZIP attachment
    When Click on Create button
    Then New Message pop up must open
    And Select the 'Regulatory Reporting for Brazil'
    Then Regulatory Reporting for Brazil will get selected
    And Attach the message file in zip format
    Then Accept message file should get uploaded

  Scenario: BR_MM_2108_42- Create new message with file contains upper and lower letters XML,xMl,XmL
    When Click on Create button
    Then New Message pop up must open
    And Select the 'Regulatory Reporting for Brazil'
    Then Regulatory Reporting for Brazil will get selected
    And Attach a xml file with contains upper and lower letters XML,xmL,XmL
    Then Accept message file should get uploaded
    And Click on Upload button
    Then 'File uploaded successfully' message should display

  Scenario: BR_MM_2108_43- Send message with special characters in xml file name
    When Click on Create button
    Then New Message pop up must open
    And Select the 'Regulatory Reporting for Brazil'
    Then Regulatory Reporting for Brazil will get selected
    And Attach a xml file contains with upper and lower letters and spec characters
    Then Accept message file should get uploaded
    And Click on Upload button
    Then 'File uploaded successfully' message should display

  Scenario: BR_MM_2108_44- Validate mandatory field check while creating the message
    When Click on Create button
    And Click on Upload button
    Then 2 red borders should come across the Message type and Message file field and error message pops-up: 'Select Type' and 'Select Message File'

  Scenario: BR_MM_2108_45- Validate a customer can enter only 40 char in 'Additional information' field
    When Click on Create button
    Then New Message pop up must open
    And Select the 'Regulatory Reporting for Brazil'
    Then Regulatory Reporting for Brazil will get selected
    And Attach the message file BR_MM_2108_40_1.xml in xml format
    And Enter 151 char in the Additional field
    Then It should allow only 150 char
    And Click on upload button

  Scenario: BR_MM_2108_46- Validate 'close' button functionality while creating the message
    When Click on Create button
    And Select the 'Regulatory Reporting for Brazil'
    And Attach the message file BR_MM_2108_40_1.xml in xml format
    And Enter the valid info in additional information field
    And Click on 'Cancel' button
    Then user should back to the Home page

  Scenario: BR_MM_2108_47- Verify Message Logs Detail page
    When Select the message from the top
    Then A unique correlation id and timestamp will show
    Then Fields will show in page header facet
    Then Tabs are present
    And Go to Message Details
    Then Fields will show in Message Details
    Then Table will show in Message Content
    And Go to Related Messages
    Then Fields will show in Automatic Retries
    Then Fields will show in Identical Messages
    And Go to Comments
    Then Comment input will show

  Scenario: BR_MM_2108_48- Change the Review Status
    When Select the message from the top
    And Click on Review Status drop down button
    Then Review Status button dropdown must be clickable
    Then User should be able to change the status from the dropdown list to In-progress
    And Change the Status from Open to In-Progress
    Then User must get back to the detail page and status should be now In-progress

  Scenario: BR_MM_2108_50- Validate save as view functionality which creates a new view
    When Click on Select View option
    Then My Views pop up must open
    And clear all customized views
    And Click on Select View option
    And Click on Save As button
    And Enter name in the View name field
    Then User should be enter text in name field
    And Click on Save button
    Then After clicking on save button, view should get save with same name and user will navigate back to home page
    
  Scenario Outline:BR_MM_2108_51	Message Manager app	Validate cancel button functionality while view creation.
    And Click on Settings icon
    Then View Settings pop up must open
    And Select <column> from View Settings
    And Click on OK button
    And Click on My views incon
    Then My Views pop up must open
    And Click on Save As button
    Then Save View pop up must open
    And Write <viewName> in VIEW Filter
    Then check user should be able to write VIEW in the empty field
    And Click on Cancel button
    Then no  change will get save
    Examples:
      | viewName    |column     |
      | TestView    |GTINs      |

  Scenario:BR_MM_2108_52 Message Manager app Validate Manage view functionality.
    And Click on My views incon
    Then My Views pop up must open
    And Click on Manage button
    Then Manage Views pop up must open
    And Select undefault view name from the list and make it default one
    Then undefault view will get selected
    And Click on Save button
    Then After clicking on save button the choosed view will become the default view
    And Select undefault view name from the list and make it default one
    And Click on Save button

  Scenario Outline:BR_MM_2108_53 Message Manager app Vlidate Manage view search functiolity.
    And Click on My views incon
    And Click on Manage button
    And Search for <viewName> view name
    Then It must show only the <viewName> view
    Examples:
    |viewName|
    |Standard|

  Scenario Outline:BR_MM_2108_54 Message Manager app Validate when downloading xml's from message manager, the sender should see the payload he sent in detail page.
    And Click on New Message button
    Then New Message pop up must open
    And Select <Scenario> in New Message Scenario
    And Attach the message file <fileName> in xml format
    And Click on Send button
    And Click on Sent tab
    Then SENDER Related data must get displayed for the searched <Sender>
    And open first message in Manage Message App
    Then Detail page must get open
    And Click on Download Message button
    And Download Sender file
    Then Downloaded payload should contain the <fileName> payload
    Examples:
    |Sender  |Scenario     |fileName|
    |PNTESTREGCOLLAB ORG2305   |Regulatory Collaboration for Brazil      |test54.xml|

  Scenario Outline:BR_MM_2108_55 Message Manager app Validate when downloading xml's from message manager, the sender should see the payload he sent, the receiver should see the transformed payload
    And Click on Received tab
    Then RECEIVER Related data must get displayed for the searched <Receiver>
    And open first message in Manage Message App
    Then Detail page must get open
    And Click on Download Message button
    And Download Receiver file
    Then Downloaded payload should be transformed payload.
    Examples:
    |Receiver|
    |PNTESTREGCOLLAB ORG2305|

  Scenario Outline:BR_MM_2108_56 Message Manager app Validate when downloading xml's from message manager, the sender should see the payload he sent, the receiver should also see the sender payload in case of validation error.
    And Click on New Message button
    Then New Message pop up must open
    And Select <Scenario> in New Message Scenario
    And Attach the message file <fileName> in xml format
    And Click on Send button
    And Click on Received tab
    Then RECEIVER Related data must get displayed for the searched <Receiver>
    And open first message in Manage Message App
    Then Detail page must get open
    And Click on Download Message button
    And Download Sender file
    Then Downloaded payload should contain the sender payload
    And Download Receiver file
    Then  Downloaded Receiver file contains  the decoded file content of the sender <fileName> info
    Examples:
      |Receiver|  fileName|Scenario|
      |PNTESTREGCOLLAB ORG2305|test56.xml|Regulatory Collaboration for Brazil|

  Scenario:BR_MM_2108_57 Message Manager app Validation of History Log page overview
    And Click on Change Log button
    Then Change Log pop up must open
    Then Search field, Action, Field, Applied To, Old Values, New Values, Changed By, Changed On: table columns should be displayed

  Scenario Outline:BR_MM_2108_58 Message Manager app Validation of Upload event when a message is triggered - History Log Page
    And Click on New Message button
    Then New Message pop up must open
    And Select <Scenario> in New Message Scenario
    And Attach the message file <fileName> in xml format
    And Click on Send button
    Then Message should be triggered
    And  Click on Change Log button
    Then Search field, Action, Field, Applied To, Old Values, New Values, Changed By, Changed On: table columns should be displayed
    Then Columns values should be displayed in the table as uploadMessage template
    Examples:
      |Scenario                             |fileName|
      |Regulatory Collaboration for Brazil      |test58.xml|

  Scenario:BR_MM_2108_59 Message Manager app Validation of Update event when review status of a message is changed - History Log Page
    And open first message in Manage Message App
    And Click on Review Status button
    And  Change the status
    And Click on Back button
    And  Click on Change Log button
    Then Search field, Action, Field, Applied To, Old Values, New Values, Changed By, Changed On: table columns should be displayed
    Then Columns values should be displayed in the table as reviewStatus template

  Scenario Outline:BR_MM_2108_60 Message Manager app Validation of No event getting triggered when review comments of a message is changed - History Log Page
    And open first message in Manage Message App
    And Click on Review Comments text box
    And Provide new <comments>
    And Click on Submit button
    And Click on Back button
    And  Click on Change Log button
    Then Search field, Action, Field, Applied To, Old Values, New Values, Changed By, Changed On: table columns should be displayed
    Then No New Log Row should be found for the message with the respective Correlation ID
    Examples:
    |comments|
    |some comments        |

  Scenario Outline: BR_MM_2211_61 Validation of Create/Retrigger Message event when a message is re-triggered - History Log Page
    And Click on Adapt Filters button
    And Select check box in adapt filter
      | Message Retriggerable |
    And click on 'OK' button
    And Select the column <MessageRetriggerableColumn> '<Message Retriggerable>'
    And Select the column <messageStatusColumn> '<MessageStatus>'
    And click on 'Go' button
    And Click on one message
    And Get correlationID
    And click on 'Retrigger Message' button
    Then Message should be retriggered
    And click on 'OK' button
    And Back to home page
    And Click on Change Log button
    Then Change Log pop up must open
    Then Verify field column display
    Then Verify columns value display
      | EventAction |
      | Field       |
      | AppliedTo   |
      | OldValues   |
      | NewValues   |
      | ChangedBy   |
      | ChangedOn   |
    Examples:
      | MessageRetriggerableColumn | Message Retriggerable | messageStatusColumn | MessageStatus |
      | MessageRetriggerable       | true                  | MessageStatus       | Error         |


  Scenario Outline: BR_MM_2111_62 Validate search functionality in History Log Page
    And Click on Change Log button
    Then Change Log pop up must open
    Then Verify field column display
    And Search '<value>' in Change Log
    And Click search button to search
    Then Validate search '<value>' display '<column>'
    Examples:
      | value                                | column      |
      | Retrigger                            | EventAction |
      | Correlation ID                       | Field       |
      | 25aad767-9f7d-4a9c-9bee-f91a5d2f50d5 | AppliedTo   |
      | 25aad767-9f7d-4a9c-9bee-f91a5d2f50d5 | OldValues   |
      | 9fb926af-6af0-4487-9471-2d549c8f5391 | NewValues   |
      | ritu191@gmail.com                    | ChangedBy   |

  Scenario: BR_MM_2108_74 Validate save view functionality for newly view created will save the changes in the same view by not creating a new view
    When Click on Select View option
    Then My Views pop up must open
    And clear all customized views
    And Click on Select View option
    And Click on Save As button
    And Enter name in the View name field
    Then User should be enter text in name field
    And Click on Save button
    Then After clicking on save button, view should get save with same name and user will navigate back to home page


  Scenario Outline: BR_MM_2108_75 Validate Deleting a view in Manage view functionality
    When Click on Select View option
    Then My Views pop up must open
    And Click on Manage button
    And Delete <viewName> view
    And Click on Save button
    Then After clicking on save button, view should get delete with same name and user will navigate back to home page
    Examples:
      | viewName |
      | TestView |

  Scenario Outline: BR_MM_2108_76 Validate cancellation of view deletion in Manage view functionality using Cancel button
    When Click on Select View option
    Then My Views pop up must open
    And clear all customized views
    And Click on Select View option
    And Click on Save As button
    And Enter name in the View name field
    Then User should be enter text in name field
    And Click on Save button
    Then After clicking on save button, view should get save with same name and user will navigate back to home page
    When Click on Select View option
    Then My Views pop up must open
    And Click on Manage button
    And Delete <viewName> view
    And Click on Cancel button
    Then User will navigate back to home page and the view still exist
    Examples:
      | viewName |
      | TestView |

  Scenario: BR_MM_2108_77 Validate Reset filters functionality in Adapt Filter window
    And Click on Adapt Filters button
    And Reset check box in adapt filter
      | Type       |
      | Lot Number |
      | GTIN       |
    And click on 'OK' button
    Then Verify column display in home page
      | Type       |
      | Lot Number |
      | GTIN       |

  Scenario: BR_MM_2108_78 Validate Reset Sorting and Reset Table Settings functionality
    When Click on Settings icon
    Then View Settings pop up must open
    And Click on Sort option
    Then Sort view page will open
    And Click on Add Sort Criterion button
    Then One more sort field added
    And Select Lot Numbers option from the list and select Descending order
    Then Lot Numbers and Descending order options must get selected
    And Click on OK button

  Scenario Outline:BR_MM_2108_87	Verify Number of Retriggers field filter
    And click on 'Adapt Filters' button
    And select Addition filters
      | Number of Retriggers |
    And click on 'OK' button
    And input integer in "Number of Retriggers" filter with <integerCount>
    And click on 'Go' button
    And Click on Settings icon
    And select "Number of Retriggers" in view setting
    Then messages should display with Number of Retriggers of <integerCount>
    Examples:
      | integerCount |
      | 2            |
      | 10           |

  Scenario Outline:BR_MM_2108_88	Verify Message Retriggerable field filter
    And click on 'Adapt Filters' button
    And select Addition filters
      | Message Retriggerable |
    And click on 'OK' button
    And select <selectFilter> in Message Retriggerable filter
    And click on 'Go' button
    Then messages should display with Message Retriggerable of <selectFilter>
    Examples:
      | selectFilter |
      | true         |
      | false        |


  Scenario:BR_MM_2108_89	Verify UI of Message Detail Page is having details regarding Automatic Retries table, Identical Messages table, Retriggerd On and Created On timestamps
    And open first message in Manage Message App
    Then "Automatic Retries" table with column names should be displayed
    Then "Identical Messages" table with column names should be displayed
    Then "Retriggered On" timestamp should be displayed
    Then "Created On" timestamp should be displayed

  Scenario:BR_MM_2108_90	Verify for each message both the Sender and Receiver Payloads are available to download
    And set Message Status filter to 'Successful'
    And click on 'Go' button
    And open first message in Manage Message App
    And click on 'Download Message' button
    Then "sender" payload should be displayed
    Then "receiver" payload should be displayed


  Scenario Outline:BR_MM_2108_92	Verify adding the comments functionality for a message in message detail page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And leave <Comment Message> in comment
    And submit comment
    Then <Comment Message> should display on message detail page
    Examples:
      | Correlation id                       | Comment Message        |
      | ce0ea5a7-72f6-4a18-a6cc-0ddb4b06ff73 | "test comment message" |

  Scenario Outline:BR_MM_2108_94	Verify adding the comments functionality with combination of alphanumeric, special characters and symbols like fractions, copyrights with 255 character length for a message in message detail page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And leave <Comment Message> in comment
    And submit comment
    Then <Comment Message> should display on message detail page
    Examples:
      | Correlation id                       | Comment Message                                                                                                                                                                                                                                                   |
      | ce0ea5a7-72f6-4a18-a6cc-0ddb4b06ff73 | "This comment includes alphanumeric characters, such as abc123 and XYZ456, as well as special characters like @#$%^&*. It also features symbols like fractions (½) and the copyright symbol ©. I'm using it as the comment in ICH automation testing. Thank you!" |


  Scenario Outline:BR_MM_2108_95	Verify adding the comments functionality with combination of alphanumeric, special characters and symbols like fractions, copyrights with 256 character length for a message in message detail page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And leave <Comment Message> in comment
    And submit comment
    Then 'Only 255 characters allowed' warning message should display
    Examples:
      | Correlation id                       | Comment Message                                                                                                                                                                                                                                                    |
      | ce0ea5a7-72f6-4a18-a6cc-0ddb4b06ff73 | "This comment includes alphanumeric characters, such as abc123 and XYZ456, as well as special characters like @#$%^&*. It also features symbols like fractions (½) and the copyright symbol ©. I'm using it as the comments in ICH automation testing. Thank you!" |


  Scenario Outline:BR_MM_2108_96	Verify adding the comments functionality with line breaks between a sentences/words in the comment box for a message in message detail page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And leave <Comment Message> in comment
    And submit comment
    Then <Commented Message> should display on message detail page
    Examples:
      | Correlation id                       | Comment Message             | Commented Message       |
      | ce0ea5a7-72f6-4a18-a6cc-0ddb4b06ff73 | "test comment  \nmessage  " | "test comment\nmessage" |


  Scenario Outline:BR_MM_2108_97	Verify adding the comments functionality with only SPACES in the comment box with no text for a message in message detail page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And leave <Comment Message> in comment
    Then submit commit button should be disabled
    Examples:
      | Correlation id                       | Comment Message |
      | ce0ea5a7-72f6-4a18-a6cc-0ddb4b06ff73 | " "             |

  Scenario Outline:BR_MM_2108_98	Verify Sub Type field filter
    And select a <Sub Type> sub type from Sub Type filter
    And click on 'Go' button
    Then data should display with "Sub Type" of <Sub Type>
    Examples:
      | Sub Type     |
      | "Activation" |
      | "Shipping"   |
      | "Receiving"  |
