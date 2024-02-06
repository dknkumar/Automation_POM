@USSC1
Feature: Verify End to end flow of USSC application

@test15
  Scenario Outline: USSC_E2E_01 - Upload product pack data-> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And set filter value disposition '<Disposition>' in write logs page
    And the button labeled Go is clicked
    Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on first record of write log
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded event
      | Timestamp   | <EventTime>   |
      | Action      | <Add Action>  |
      | Disposition | <Disposition> |
      | GLN         | <senderGln>   |
    And navigate to home screen
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And add disposition rule if rule does not exist
      | Disposition | <Disposition> |
      | Verified    | <Verified>    |
      | Rule        | <rule>        |
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>              |
      | Verification Failure Reason |                         |
      | Additional Information      | <AdditionalInformation> |
      | Verification Timestamp      |                         |
      | Responder GLN               | <ResponderGln>          |
      | Correlation UUID            |                         |
      | HTTP return code            | <HTTPReturnCode>        |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
   # And switch to my view if '<ViewName>' exists
    And clear all filter component fields
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
  #  And all the fields are selected as filter component
  #  And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is clicked in filter modal pop up
#    And set UUID value
#    And the button labeled Go is clicked
    Then the response contains the filtered values
      | GTIN         | <GTIN>         |
      | Verified     | <Verified>     |
      | SerialNumber | <SerialNumber> |
      | ResponderGln | <ResponderGln> |
    And select default columns to display under personalisation
    And the button labeled OK is clicked
    And click on first record of verification log
    And click on repository explorer link
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    Then Validate the recorded event
      | Timestamp   | <EventTime>   |
      | Action      | <Add Action>  |
      | Disposition | <Disposition> |
      | GLN         | <senderGln>   |
    Examples:
      | customer        | senderGln     | product | batchId        | messageType | filename    | receiverGln   | GTIN           | Add Action | Disposition             | EventTime                   | Verified | ResponderGln  | HTTPReturnCode | ExpirationDate | SerialNumber | ViewName | NumberOfRecords | rule | AdditionalInformation | PI Count |
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2022 | EPCIS_1.0   | USSC_01.xml | 1100001002641 | 00363391180998 | Add        | commissioning_auto_test | 2021-02-26T12:39:06.515506Z | true     | 0363391100002 | 200            | 22-07-15       | 9282745585   | Auto_Std | 1               | B1   | Recalled              | 3        |


  Scenario Outline: USSC_02 - Upload product pack data_ multiple expiry year-> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And set filter value disposition '<Disposition>' in write logs page
    And the button labeled Go is clicked
    Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on record of write log
      | ExpirationDate | <ExpirationDate> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded event
      | Timestamp   | <EventTime>   |
      | Action      | <Add Action>  |
      | Disposition | <Disposition> |
      | GLN         | <senderGln>   |
    And navigate to home screen
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And set filter value disposition '<Disposition>' in write logs page
    And the button labeled Go is clicked
    And click on record of write log
      | ExpirationDate | <ExpirationDate1> |
    And click on repository explorer link
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>            |
      | Lot             |                   |
      | Expiration Date | <ExpirationDate1> |
      | PI Count        | <PI Count>        |
    And Search the '<SerialNumber>'
    Then Validate the recorded event
      | Timestamp   | <EventTime>   |
      | Action      | <Add Action>  |
      | Disposition | <Disposition> |
      | GLN         | <senderGln>   |
    And navigate to home screen
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And add disposition rule if rule does not exist
      | Disposition | <Disposition> |
      | Verified    | <Verified>    |
      | Rule        | <rule>        |
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>              |
      | Verification Failure Reason |                         |
      | Additional Information      | <AdditionalInformation> |
      | Verification Timestamp      |                         |
      | Responder GLN               | <ResponderGln>          |
      | Correlation UUID            |                         |
      | HTTP return code            | <HTTPReturnCode>        |
    And the button labeled Adjust Request is clicked
    And the Expiration Date value '<ExpirationDate1>' is entered
    And click on verify button
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    #And switch to my view if '<ViewName>' exists
    And clear all filter component fields
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    #And all the fields are selected as filter component
   # And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is clicked in filter modal pop up
   # And set UUID value
  #  And the button labeled Go is clicked
    Then the response contains the filtered values
      | GTIN         | <GTIN>         |
      | Verified     | <Verified>     |
      | SerialNumber | <SerialNumber> |
      | ResponderGln | <ResponderGln> |

    Examples:
      | customer        | senderGln     | product | batchId             | messageType | filename    | receiverGln   | GTIN           | Add Action | Disposition             | EventTime                   | Verified | ResponderGln  | HTTPReturnCode | ExpirationDate1 | ExpirationDate | SerialNumber | ViewName | NumberOfRecords | PI Count | rule | AdditionalInformation |
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2020_2021 | EPCIS_1.0   | USSC_02.xml | 1100001002641 | 00363391180998 | Add        | commissioning_auto_test | 2021-02-26T12:39:06.515506Z | true     | 0363391100002 | 200            | 22-12-30        | 21-12-31       | 6753011225   | Auto_Std | 2               | 3        | B1   | Recalled              |

  Scenario Outline: USSC_2005_15 - Upload product pack data- expiry date less than previous year -> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    #Then Error message '<error Message>'is displayed
   # And the button labeled OK is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Error'
    Examples:
      | customer        | senderGln     | product | batchId        | messageType | filename         | receiverGln   | error Message                               |
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2018 | EPCIS_1.0   | USSC_2005_14.xml | 1100001002641 | Your message is saved with an Error status. |


@test16
  Scenario Outline: USSC_22 - Upload product pack data_Decommissioning event-> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And set filter value disposition '<Disposition>' in write logs page
    And the button labeled Go is clicked
    Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on first record of write log
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded event
      | Timestamp   | <EventTime>     |
      | Action      | <Delete Action> |
      | Disposition | <Disposition>   |
      | GLN         | <senderGln>     |
    And navigate to home screen
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And add disposition rule if rule does not exist
      | Disposition | <Disposition> |
      | Verified    | <Verified>    |
      | Rule        | <rule>        |
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
   # And switch to my view if '<ViewName>' exists
    And clear all filter component fields
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
 #   And all the fields are selected as filter component
  #  And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is clicked in filter modal pop up
#    And set UUID value
  #  And the button labeled Go is clicked
    Then the response contains the filtered values
      | GTIN         | <GTIN>         |
      | Verified     | <Verified>     |
      | SerialNumber | <SerialNumber> |
      | ResponderGln | <ResponderGln> |

    Examples:
      | customer        | senderGln     | product | batchId        | messageType | filename    | receiverGln   | GTIN           | Delete Action | Disposition  | EventTime                   | Verified | ResponderGln  | HTTPReturnCode | ExpirationDate | SerialNumber | ViewName | NumberOfRecords | AdditionalInformation | VerificationFailureReason | PI Count | rule |
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2019 | EPCIS_1.0   | USSC_22.xml | 1100001002641 | 00363391180998 | Delete        | autoinactive | 2020-02-26T12:39:06.515506Z | false    | 0363391100002 | 200            | 21-07-15       | 9282745585   | Auto_Std | 1               | Suspect               | Not_for_re-distribution   | 3        | C1   |

  Scenario Outline: USSC_25 - Upload product pack data_Empty EPC List-> Write Logs -> Verify event details not updated in blockchain
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
    Then No records found for the filtered values

    Examples:
      | customer        | senderGln     | product | batchId        | messageType | filename    | receiverGln   | GTIN           |
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2023 | EPCIS_1.0   | USSC_25.xml | 1100001002641 | 00363391180998 |
@test14
  Scenario Outline: USSC_30 - Upload product pack data_Commissioning object and observe event with extension for object event only-> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
    Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on record of write log
      | Disposition | <Add Disposition> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded events
      | Timestamp   | <EventTime>       | <EventTime>           |
      | Action      | <Add Action>      | <Observe Action>      |
      | Disposition | <Add Disposition> | <Observe Disposition> |
      | GLN         | <senderGln>       | <senderGln>           |
    And navigate to home screen
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
    And click on record of write log
      | Disposition | <Observe Disposition> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded events
      | Timestamp   | <EventTime>       | <EventTime>           |
      | Action      | <Add Action>      | <Observe Action>      |
      | Disposition | <Add Disposition> | <Observe Disposition> |
      | GLN         | <senderGln>       | <senderGln>           |
    Examples:
      | customer        | senderGln     | product | batchId             | messageType | filename    | receiverGln   | GTIN           | Add Disposition     | Observe Disposition      | EventTime                   | ExpirationDate | SerialNumber | NumberOfRecords | Add Action | Observe Action | PI Count |
      | ichblockchainQA | 0363391100002 | testQA  | activeobserveobject | EPCIS_1.0   | USSC_30.xml | 1100001002641 | 00363391180998 | activeobserveobject | in_transit_objectobserve | 2020-02-26T12:39:06.515506Z | 21-11-28       | 7578533430   | 2               | Add        | Observe        | 1        |

   Scenario Outline: USSC_31 - Upload product pack data_RTC + Extension generation-> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
     And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
    Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on record of write log
      | Disposition | <Disposition> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded events
      | Timestamp   | <EventTime>      | <EventTime>   |
      | Action      | <Observe Action> | <Add Action>  |
      | Disposition | <Disposition1>   | <Disposition> |
      | GLN         | <senderGln>      | <senderGln>   |
    And navigate to home screen
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
    And click on record of write log
      | Disposition | <Disposition1> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded events
      | Timestamp   | <EventTime>      | <EventTime>   |
      | Action      | <Observe Action> | <Add Action>  |
      | Disposition | <Disposition1>   | <Disposition> |
      | GLN         | <senderGln>      | <senderGln>   |
    And navigate to home screen
#    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
#    And add disposition rule if rule does not exist
#      | Disposition | <Disposition> |
#      | Verified    | <Verified>    |
  #  And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>       |
      | Verification Timestamp      |                  |
      | Responder GLN               | <ResponderGln>   |
      | Correlation UUID            |                  |
      | HTTP return code            | <HTTPReturnCode> |
    And the button labeled Adjust Request is clicked
    And the Expiration Date value '<ExpirationDate>' is entered
    And click on verify button
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
  #  And switch to my view if '<ViewName>' exists
    And clear all filter component fields
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
  #  And all the fields are selected as filter component
  #  And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is clicked in filter modal pop up
  #  And set UUID value
  #  And the button labeled Go is clicked
    Then the response contains the filtered values
      | GTIN         | <GTIN>         |
      | Verified     | <Verified>     |
      | SerialNumber | <SerialNumber> |
      | ResponderGln | <ResponderGln> |

    Examples:
      | customer        | senderGln     | product | batchId        | messageType | filename    | receiverGln   | GTIN           | Disposition | Disposition1 | EventTime                   | Verified | Verified1 | ResponderGln  | HTTPReturnCode | ExpirationDate | SerialNumber | ViewName | NumberOfRecords | ushcVerification                | PI Count | Add Action | Observe Action |AdditionalInformation|
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2022 | EPCIS_1.0   | USSC_31.xml | 1100001002641 | 00363391180998 | active      | in_transit   | 2020-02-26T12:44:06.515506Z | true     | false     | 0363391100002 | 200            | 23-06-30       | 7578533428   | Auto_Std | 2               | ushc-verification-2022 (native) | 10       | Add        | Observe        |Recalled             |

  Scenario Outline: USSC_29.2 - Upload product pack data_Real-Time Commissioning Mapping-> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
   # Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on record of write log
      | Disposition | <Disposition> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded events
      | Timestamp   | <EventTime>   | <EventTime>     |
      | Action      | <Add Action>  | <Delete Action> |
      | Disposition | <Disposition> | <Disposition1>  |
      | GLN         | <senderGln>   | <senderGln>     |
    And navigate to home screen
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
    And click on record of write log
      | Disposition | <Disposition1> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded events
      | Timestamp   | <EventTime>   | <EventTime>     |
      | Action      | <Add Action>  | <Delete Action> |
      | Disposition | <Disposition> | <Disposition1>  |
      | GLN         | <senderGln>   | <senderGln>     |
#    And navigate to home screen
#    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
#    And add disposition rule if rule does not exist
#      | Disposition | <Disposition1> |
#      | Verified    | <Verified1>    |
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>              |
      | Verification Failure Reason |                         |
      | Verification Timestamp      |                         |
      | Responder GLN               | <ResponderGln>          |
      | Correlation UUID            |                         |
      | HTTP return code            | <HTTPReturnCode>        |
    And the button labeled Adjust Request is clicked
    And the Expiration Date value '<ExpirationDate>' is entered
    And click on verify button
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
   And filter components are selected
      | UUID |
    And the button labeled OK is clicked
   # And switch to my view if '<ViewName>' exists
   # And all the fields are selected as filter component
    #And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
  #  And Go button is clicked in filter modal pop up
    And set UUID value
    And the button labeled Go is clicked
    Then the response contains the filtered values
      | GTIN         | <GTIN>         |
      | Verified     | <Verified>     |
      | SerialNumber | <SerialNumber> |
      | ResponderGln | <ResponderGln> |

    Examples:
      | customer        | senderGln     | product | batchId        | messageType | filename      | receiverGln   | GTIN           | Verified  |  Disposition1 | EventTime                   | Disposition | Verified1 | ResponderGln  | HTTPReturnCode | ExpirationDate | SerialNumber | ViewName | NumberOfRecords | ushcVerification                | NumberOfEpcs | Add Action | Delete Action | PI Count |
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2023 | EPCIS_1.0   | USSC_29_2.xml | 1100001002641 | 00363391180998 | true      |               destroyed    | 2021-02-26T12:39:06.515506Z | active      | false     | 0363391100002 | 200            | 23-08-09       | 7578533428   | Auto_Std | 2               | ushc-verification-2023 (native) | 10           | Add        | Delete        | 10       |


  Scenario Outline: USSC_32 - Upload product pack data_Commissioning object and observe event with extension for object event only-> Write Logs -> Block Chain explorer ->configure Verification response -> verify product pack
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
 #   And the button labeled Go is clicked
  #  Then the results contains '<TotalNumberOfRecords>' records
    And set filter value disposition '<Disposition>' in write logs page
    And the button labeled Go is clicked
  #  Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on record of write log
      | Disposition | <Disposition> |
    And click on repository explorer link in Write Log
    And Search the '<SerialNumber>'
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    Then Validate the recorded events
      | Timestamp   | <EventTime>   | <EventTime>     |
      | Action      | <Add Action>  | <Delete Action> |
      | Disposition | <Disposition> | <Disposition1>  |
      | GLN         | <senderGln>   | <senderGln>     |
    And navigate to home screen
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
   # And the button labeled Go is clicked
    And set filter value disposition '<Disposition1>' in write logs page
    And the button labeled Go is clicked
   # Then the results contains '<NumberOfRecords>' records
    And click on record of write log
      | Disposition | <Disposition1> |
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded events
      | Timestamp   | <EventTime>   | <EventTime>     |
      | Action      | <Add Action>  | <Delete Action> |
      | Disposition | <Disposition> | <Disposition1>  |
      | GLN         | <senderGln>   | <senderGln>     |
    Examples:
      | customer        | senderGln     | product | batchId         | messageType | filename    | receiverGln   | GTIN           | Add Action | Delete Action | Disposition | EventTime               | Disposition1 | PI Count | ExpirationDate | SerialNumber | TotalNumberOfRecords | NumberOfRecords |
      | ichblockchainQA | 0363391100002 | testQA  | activedestroyed | EPCIS_1.0   | USSC_32.xml | 1100001002641 | 00363391180998 | Add        | Delete        | active      | 2017-03-22T19:08:33.41Z | destroyed    | 6        | 21-10-31       | 100000705826 | 4                    | 2               |


  Scenario Outline: USSC_29 - Real-Time Commissioning Mapping - 10 commissioning events are compacted into 1 event
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the xml file, '<filename>' is updated
    And the UploadProductPackData app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the SEM app is clicked in the USSC launcher page
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    And the send button is clicked in the SEM create page
    And the confirm window contains the SEM details
      | customer    | <customer>    |
      | senderGln   | <senderGln>   |
      | receiverGln | <receiverGln> |
      | product     | <product>     |
      | batchId     | <batchId>     |
      | messageType | <messageType> |
      | filename    | <filename>    |
    And the button labeled Send is clicked
    And the button labeled Go is clicked
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Sent'
    And user switches to the parent window
    And the WriteLog app is clicked in the USSC launcher page
    And the filters button is clicked in the write log page
    And all the fields are selected as filter component
    And set filter value messageId in write logs page
    And set filter value lot in write logs page
    And set filter value gtin '<GTIN>' in write logs page
    And the button labeled Go is clicked
    Then the results contains '<NumberOfRecords>' records
    Then the results contains the filtered values in the write log response
      | MessageId |
      | LOT       |
    And click on first record of write log
    And click on repository explorer link in Write Log
    And Validate the PI details on Blockchain Explorer screen
      | GTIN            | <GTIN>           |
      | Lot             |                  |
      | Expiration Date | <ExpirationDate> |
      | PI Count        | <PI Count>       |
    And Search the '<SerialNumber>'
    Then Validate the recorded event
      | Timestamp   | <EventTime>   |
      | Action      | <Add Action>  |
      | Disposition | <Disposition> |
      | GLN         | <senderGln>   |
    And navigate to home screen
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And add disposition rule if rule does not exist
      | Disposition    | <Disposition>           |
      | Verified       | <Verified>              |
      | Rule           |    <Rule>               |
      | AdditionalInfo | <AdditionalInformation> |
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>              |
      | Verification Failure Reason |                         |
      | Additional Information      | <AdditionalInformation> |
      | Verification Timestamp      |                         |
      | Responder GLN               | <ResponderGln>          |
      | Correlation UUID            |                         |
      | HTTP return code            | <HTTPReturnCode>        |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
  And the button labeled OK is clicked
   # And switch to my view if '<ViewName>' exists
  #  And all the fields are selected as filter component
    And clear all filter component fields
    And set UUID value
#   And Save view '<ViewName>' for filter and Save button is clicked in filter modal pop up
    And Go button is clicked in filter modal pop up
   # And set UUID value
   # And the button labeled Go is clicked
    Then the response contains the filtered values
      | GTIN         | <GTIN>         |
      | Verified     | <Verified>     |
      | SerialNumber | <SerialNumber> |
      | ResponderGln | <ResponderGln> |

    Examples:
      | customer        | senderGln     | product | batchId        | messageType | filename    | receiverGln   | GTIN           | ActionEvent | EventTime                   | Disposition        | Rule | ResponderGln  | HTTPReturnCode | ExpirationDate | SerialNumber | ViewName | NumberOfRecords | Verified | AdditionalInformation | Add Action | PI Count |
      | ichblockchainQA | 0363391100002 | testQA  | AutoQaTest2023 | EPCIS_1.0   | USSC_29.xml | 1100001002641 | 00363391180998 | 1           | 2020-02-26T12:44:06.515506Z | auto_true_recalled | B1     | 0363391100002 | 200            | 23-05-30       | 7578533420   | Auto_Std | 1               | true     | Recalled              | Add        | 10       |

