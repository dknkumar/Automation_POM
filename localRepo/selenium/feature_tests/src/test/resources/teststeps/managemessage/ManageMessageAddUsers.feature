@ManageMessageAddUsers
Feature: Manage Message App

  Scenario: BR_MM_2108_02 Validate that If there is no message exchange happens between the BP and Customer, no data should be present in the table
    Given the user logs in as 'MM_AddUser' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    Then no data should be present in the table

#    Test 81~86: message status is Error when create a new message with uploading xml file
  Scenario Outline:BR_MM_2108_81	Validate 'Create message' functionality by uploading Shipping Message file from BP account to Customer which result to Successful
    And the user logs in as 'MM_Partner_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And click on 'New Message' button
    And select 'Regulatory Collaboration for Brazil' in Scenario drop-down
    And Attach the message file 'Shipping Message' in xml format
    And Enter the <comment message> in additional information field
    Then 'Shipping Message' file should get uploaded
    And click on 'Send' button
    Then 'File uploaded successfully' message should display
    And get first row correlation ID
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input in the Correlation ID filter
    And click on 'Go' button
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs in CPI
    And Monitor Message Processing app is displayed
    And search for iflows with message correlation ID
    Then result should show for the searched co-relation ID
    Then all the related iflows should get hit with 'Completed' status
    And open validation artifact
    Then <Partner_PNID> information should match in CPI "partner PNID"
    Then <Customer_PNID> information should match in CPI "customer PNID"
    And open text view
    Then intermediateError should be <intermediateError>
    Examples:
      | comment message | Type                                  | Sub Type   | LotNo        | Sender Code      | Receiver Code    | Message Status | Review Status | Business document ID                           | GTIN             | Changed By | Changed On | Partner_PNID           | Customer_PNID            | intermediateError |
      | "test comment"  | "Regulatory Collaboration for Brazil" | "Shipping" | "NKGRBR1802" | "52434771000190" | "51533666000146" | "Successful"   | "Open"        | "90723641642768274750907236416427682747501234" | "04063973000930,04063973000947" | ""         | ""         | "TESTPA_20230524095605" | "PNTESTREGCOLLABORG2305" | false             |


  Scenario Outline:BR_MM_2108_82	Validate 'Create message' functionality by uploading Receive Message file from BP account to Customer which result to Successful.
    And the user logs in as 'MM_Partner_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And click on 'New Message' button
    And select 'Regulatory Collaboration for Brazil' in Scenario drop-down
    And Attach the message file 'Receive Message' in xml format
    And Enter the <comment message> in additional information field
    Then 'Receive Message' file should get uploaded
    And click on 'Send' button
    Then 'File uploaded successfully' message should display
    And get first row correlation ID
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input in the Correlation ID filter
    And click on 'Go' button
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs in CPI
    And Monitor Message Processing app is displayed
    And search for iflows with message correlation ID
    Then result should show for the searched co-relation ID
    Then all the related iflows should get hit with 'Completed' status
    And open validation artifact
    Then <Partner_PNID> information should match in CPI "partner PNID"
    Then <Customer_PNID> information should match in CPI "customer PNID"
    And open text view
    Then intermediateError should be <intermediateError>
    Examples:
      | comment message | Type                                  | Sub Type    | LotNo    | Sender Code      | Receiver Code    | Message Status | Review Status | Business document ID                           | GTIN             | Changed By | Changed On | Partner_PNID           | Customer_PNID            | intermediateError |
      | "test comment"  | "Regulatory Collaboration for Brazil" | "Receiving" | "LOT001" | "52434771000190" | "51533666000146" | "Successful"   | "Open"        | "90723641642768274750907236416427682747501234" | "01234567891019,01234567891026" | ""         | ""         | "TESTPA_20230524095605" | "PNTESTREGCOLLABORG2305" | false             |

  Scenario Outline:BR_MM_2108_83	Validate 'Create message' functionality by uploading Finalization Message file from BP account to Customer which result to Successful
    And the user logs in as 'MM_Partner_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And click on 'New Message' button
    And select 'Regulatory Collaboration for Brazil' in Scenario drop-down
    And Attach the message file 'Finalization Message' in xml format
    And Enter the <comment message> in additional information field
    Then 'Finalization Message' file should get uploaded
    And click on 'Send' button
    Then 'File uploaded successfully' message should display
    And get first row correlation ID
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input in the Correlation ID filter
    And click on 'Go' button
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs in CPI
    And Monitor Message Processing app is displayed
    And search for iflows with message correlation ID
    Then result should show for the searched co-relation ID
    Then all the related iflows should get hit with 'Completed' status
    And open validation artifact
    Then <Partner_PNID> information should match in CPI "partner PNID"
    Then <Customer_PNID> information should match in CPI "customer PNID"
    And open text view
    Then intermediateError should be <intermediateError>
    Examples:
      | comment message | Type                                  | Sub Type       | LotNo    | Sender Code      | Receiver Code    | Message Status | Review Status | Business document ID                           | GTIN             | Changed By | Changed On | Partner_PNID            | Customer_PNID            | intermediateError |
      | "test comment"  | "Regulatory Collaboration for Brazil" | "Finalization" | "NKGRBR1902" | "52434771000190" | "51533666000146" | "Successful"   | "Open"        | "90723641642768274750907236416427682747501234" | "04063973000930,04063973000947" | ""         | ""         | "TESTPA_20230524095605" | "PNTESTREGCOLLABORG2305" | false             |


  Scenario Outline:BR_MM_2108_84	Validate 'Create message' functionality by uploading Shipping Message file BP account to Customer which result to Error
    And the user logs in as 'MM_Negative_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And click on 'New Message' button
    And select 'Regulatory Collaboration for Brazil' in Scenario drop-down
    And Attach the message file 'Shipping Message' in xml format
    And Enter the <comment message> in additional information field
    Then 'Shipping Message' file should get uploaded
    And click on 'Send' button
    Then 'File uploaded successfully' message should display
    And get first row correlation ID
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input in the Correlation ID filter
    And click on 'Go' button
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs in CPI
    And Monitor Message Processing app is displayed
    And search for iflows with message correlation ID
    Then result should show for the searched co-relation ID
    Then all the related iflows should get hit with 'Completed' status
    And open validation artifact
    Then <Partner_PNID> information should match in CPI "partner PNID"
    Then <Customer_PNID> information should match in CPI "customer PNID"
    And open text view
    Then intermediateError should be <intermediateError>
    Examples:
      | comment message | Type                                  | Sub Type   | LotNo        | Sender Code      | Receiver Code    | Message Status | Review Status | Business document ID                           | GTIN             | Changed By | Changed On | Partner_PNID            | Customer_PNID            | intermediateError |
      | "test comment"  | "Regulatory Collaboration for Brazil" | "Shipping" | "NKGRBR1802" | "52434771000190" | "51533666000146" | "Error"        | "Open"        | "90723641642768274750907236416427682747501234" | "04063973000930,04063973000947" | ""         | ""         | "TESTPA_20230524095605" | "PNTESTREGCOLLABORG2305" | false             |


  Scenario Outline:BR_MM_2108_85	Validate 'Create message' functionality by uploading Receive Message file BP account to Customer which result to Error
    And the user logs in as 'MM_Negative_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And click on 'New Message' button
    And select 'Regulatory Collaboration for Brazil' in Scenario drop-down
    And Attach the message file 'Receive Message' in xml format
    And Enter the <comment message> in additional information field
    Then 'Receive Message' file should get uploaded
    And click on 'Send' button
    Then 'File uploaded successfully' message should display
    And get first row correlation ID
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input in the Correlation ID filter
    And click on 'Go' button
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs in CPI
    And Monitor Message Processing app is displayed
    And search for iflows with message correlation ID
    Then result should show for the searched co-relation ID
    Then all the related iflows should get hit with 'Completed' status
    And open validation artifact
    Then <Partner_PNID> information should match in CPI "partner PNID"
    Then <Customer_PNID> information should match in CPI "customer PNID"
    And open text view
    Then intermediateError should be <intermediateError>
    Examples:
      | comment message | Type                                  | Sub Type    | LotNo    | Sender Code      | Receiver Code    | Message Status | Review Status | Business document ID                           | GTIN             | Changed By | Changed On | Partner_PNID           | Customer_PNID            | intermediateError |
      | "test comment"  | "Regulatory Collaboration for Brazil" | "Receiving" | "LOT001" | "52434771000190" | "51533666000146" | "Error"   | "Open"        | "90723641642768274750907236416427682747501234" | "01234567891019,01234567891026" | ""         | ""         | "TESTPA_20230524095605" | "PNTESTREGCOLLABORG2305" | false             |


  Scenario Outline:BR_MM_2108_86	Validate 'Create message' functionality by uploading Finalization Message file BP account to Customer which result to Error
    And the user logs in as 'MM_Negative_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And click on 'New Message' button
    And select 'Regulatory Collaboration for Brazil' in Scenario drop-down
    And Attach the message file 'Finalization Message' in xml format
    And Enter the <comment message> in additional information field
    Then 'Finalization Message' file should get uploaded
    And click on 'Send' button
    Then 'File uploaded successfully' message should display
    And get first row correlation ID
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input in the Correlation ID filter
    And click on 'Go' button
    Then first row should display with "Sender Code" of <Sender Code>
    Then first row should display with "Receiver Code" of <Receiver Code>
    Then first row should display with "Sub Type" of <Sub Type>
    Then first row should display with "Type" of <Type>
    Then first row should display with "LotNo" of <LotNo>
    Then first row should display with "Message Status" of <Message Status>
    Then first row should display with "Review Status" of <Review Status>
    Then first row should display with "Business document ID" of <Business document ID>
    Then first row should display with "GTIN" of <GTIN>
    Then first row should display with "Changed By" of <Changed By>
    Then first row should display with "Changed On" of <Changed On>
    And the user logs in CPI
    And Monitor Message Processing app is displayed
    And search for iflows with message correlation ID
    Then result should show for the searched co-relation ID
    Then all the related iflows should get hit with 'Completed' status
    And open validation artifact
    Then <Partner_PNID> information should match in CPI "partner PNID"
    Then <Customer_PNID> information should match in CPI "customer PNID"
    And open text view
    Then intermediateError should be <intermediateError>
    Examples:
      | comment message | Type                                  | Sub Type       | LotNo        | Sender Code      | Receiver Code    | Message Status | Review Status | Business document ID                           | GTIN             | Changed By | Changed On | Partner_PNID           | Customer_PNID            | intermediateError |
      | "test comment"  | "Regulatory Collaboration for Brazil" | "Finalization" | "NKGRBR1902" | "52434771000190" | "51533666000146" | "Error"        | "Open"        | "90723641642768274750907236416427682747501234" | "04063973000930,04063973000947" | ""         | ""         | "TESTPA_20230524095605" | "PNTESTREGCOLLABORG2305" | false             |


  Scenario Outline:BR_MM_2108_91	Verify for each message both the Sender and Receiver Payloads downloaded at Sender end should be the same when the payloads are downloaded at the Receiver end
    Given the user logs in as 'MM_Partner_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And click on 'Download Message' button
    And download 'sender' payload
    And click on 'Download Message' button
    And download 'receiver' payload
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And click on 'Download Message' button
    And download 'sender' payload
    And click on 'Download Message' button
    And download 'receiver' payload
    Then <Sender payload file name> should match from both end
    Then <Receiver payload file name> should match from both end
    Examples:
      | Correlation id                       | Sender payload file name                                                    | Receiver payload file name                                                    |
      | ce0ea5a7-72f6-4a18-a6cc-0ddb4b06ff73 | Sender_90Days_154fd4d9-0b39-44fc-afcf-c6964f4e2b18_2023-06-05T09_51_17.576Z | Receiver_60Days_9889f130-ac8f-4cd1-b494-962285a27b93_2023-06-05T09_51_17.576Z |


  Scenario Outline:BR_MM_2108_93	Verify the comments added at one user end for a particular message should be visible for the user at the other end when the same message has exchanged between the users
    Given the user logs in as 'MM_Partner_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    And leave <Comment Message> in comment
    And submit comment
    Then <Comment Message> should display on message detail page
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MM_Customer_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    And input <Correlation id> into the Correlation ID filter
    And click on 'Go' button
    And open first message in Manage Message App
    Then <Comment Message> should display on message detail page
    Examples:
      | Correlation id                       | Comment Message        |
      | ce0ea5a7-72f6-4a18-a6cc-0ddb4b06ff73 | "test comment message" |

