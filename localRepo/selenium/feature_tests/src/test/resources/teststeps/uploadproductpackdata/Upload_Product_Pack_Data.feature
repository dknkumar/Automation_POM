@USSC @uploadproductpackdata
Feature: Verify upload product pack App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page

  Scenario Outline: USSC_27, USSC_27.1 : Upload Product pack data with valid information
    And the xml file, '<filename>' is updated
    And the ManageSerializationEvents app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
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
    Examples:
      | customer        | senderGln     | product | batchId | messageType | filename         | receiverGln   | msgId                                    |
      | ichblockchainQA | 0363391100002 | testQA  | 12345   | EPCIS_1.0   | BC_TEST_BULK.xml | 1100001002641 | 115056CA6ACA1ED5B0E9C0665D4027TestDBQA01 |

  Scenario Outline: Upload product pack data with invalid file
    And the ManageSerializationEvents app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
    And the new message button is clicked in the SEM create page
    When the customer '<customer>' is selected in the SEM create page
    And the sender GLN '<senderGln>' is selected from the sender GLN list in the SEM create page
    And the product '<product>' is selected in the SEM create page
    And the Batch ID, '<batchId>', is entered on the SEM Create Page
    And the message type, '<messageType>', is selected on the SEM Create Page
    And the file, '<filename>', is attached in the SEM create page
    Then the toast message '<message>' appears
    And the send button is clicked in the SEM create page
    Then the toast message '<mandatoryErrorMessage>' appears
    Examples:
      | customer        | senderGln     | product | batchId | messageType | filename          | message                                          | mandatoryErrorMessage                                         |
      | ichblockchainQA | 0363391100002 | testQA  | 12345   | EPCIS_1.0   | WrongFileType.pdf | Cannot upload. Only XML and ZIP files permitted. | Cannot Send. Enter valid information in all mandatory fields. |


  Scenario Outline: USSC_2008_01-1 Object Event in EPCIS file.Incorrect GCP maintained in config file.GTIN doesn’t match with GCP maintained.
    And the ManageSerializationEvents app is clicked in the USSC launcher page
    And user switches to the child window
    And click on sign in link
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
    And the refresh button is clicked on the SEM master page
    Then the first SEM message status is 'Error'
    Examples:
      | customer        | senderGln     | product | batchId | messageType | filename                            | receiverGln   |
      | ichblockchainQA | 0363391100002 | testQA  | 12345   | EPCIS_1.0   | N_Decomissioning_1ObjectEvent.xml   | 1100001002641 |
      | ichblockchainQA | 0363391100002 | testQA  | 12345   | EPCIS_1.0   | N_Decommsion_Without_GTINandLOT.xml | 1100001002641 |