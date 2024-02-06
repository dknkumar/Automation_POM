@USSC @transactioninformation
Feature: Verify TransactionInformation  App

  Background:
    Given the user logs in as 'USSC_TI' in the login page
    And the TransactionInformation app is clicked in the USSC launcher page

  Scenario: DISP-002, DISP-004 - To verify the transactions table columns and filters displayed in the overview page
    Then the TI table contains below columns
      | Delivery Number | Shipment Date | SAP Technical Date | Sender GLN | Receiver GLN | Message ID | Downloaded | Message Date | Purchase Order |
    Then the TI overview page contains below filters
      | Delivery Number | Shipment Date |Sender GLN|Receiver GLN|Message ID|Downloaded|Message Date|Purchase Order|GTIN|Batch / Lot Number|Serial Number|National Drug Code|

  Scenario Outline: DISP-001,DISP-009,DISP-010- Verify page title , filter view, select and apply filter
    Then the title of the transaction information table is '<tableHeader>'
    And set filter value delivery number '<DeliveryNumber>'
    And the button labeled Go is clicked
    Then the TI table contains the filtered values in the response
      | DeliveryNumber | <DeliveryNumber> |
    And clear filter deliveryNumber value in transaction information
    And set filter value senderGln '<SenderGln>'
    And the button labeled Go is clicked
    Then the TI table contains the filtered values in the response
      | SenderGln | <SenderGln> |
    And clear filter senderGln value in transaction information
    And set filter value receiverGln '<ReceiverGln>'
    And the button labeled Go is clicked
    Then the TI table contains the filtered values in the response
      | ReceiverGln | <ReceiverGln> |
    And clear filter receiverGln value in transaction information
    And set filter value messageId '<MessageId>'
    And the button labeled Go is clicked
    Then the TI table contains the filtered values in the response
      | MessageId | <MessageId> |
    And clear filter messageId value in transaction information
    And set filter value PurchaseOrder '<PurchaseOrder>'
    And the button labeled Go is clicked
    Then the TI table contains the filtered values in the response
      | PurchaseOrder | <PurchaseOrder> |
    And clear filter purchaseOrder value in transaction information
    Examples:
      | tableHeader                      | DeliveryNumber | SenderGln     | ReceiverGln    | MessageId | PurchaseOrder |
      | Transaction Information Messages | 9010429213     | 0363391100002 | 80809999899987 | 888011111 | 4010429213    |


  Scenario: DISP-003- Adapt Filter
    And the button labeled Adapt Filters is clicked
    Then the adapt filter pop is displayed
    And filter components are selected
      | National Drug Code |
    And the button labeled OK is clicked
    Then verify selected filter components are available in TI app
      | National Drug Code |
    Then verify unselected filter components are not available
      | SenderGln      |
      | ReceiverGln    |
      | MessageId      |
      | PurchaseOrder  |
      | LotNumber      |
      | SerialNumber   |
      | MessageDate    |
      | DeliveryNumber |
      | DeliveryDate   |
      | Downloaded     |
    |SAP Technical Date|


  Scenario Outline: DISP-017 - Download pdf document
    And the download TI TS pdf report button is clicked
    And get the first TI Message Delivery Number
    Then verify TI pdf report '<pdfFileName>' is downloaded
    Examples:
      | pdfFileName      |
      | SAP DSCSA Report |


  Scenario Outline: DISP-018 - Download excel document
    And the download TI TS excel report button is clicked
    Then the toast message '<Message>' appears
    And get the first TI Message Delivery Number
    Then verify file '<FileName>' is downloaded
    Examples:
      | FileName                              |Message|
      | Transaction Information Messages.xlsx | Download to Excel succeeded|


  Scenario Outline: DISP-016 - Download input xml document
    And the download TI TS xml report button is clicked
    And get the first TI Message Delivery Number
    Then verify file '<FileName>' is downloaded
    Examples:
      | FileName                              |
      | SAP DSCSA Report|

  Scenario: DISP-006,DISP-007,DISP-008 -Verify  the display of Download XML,PDF and Excel button.
    Then the download TI TS xml report button is displayed
    Then the download TI TS pdf report button is displayed
    Then the download TI TS excel report button is displayed
