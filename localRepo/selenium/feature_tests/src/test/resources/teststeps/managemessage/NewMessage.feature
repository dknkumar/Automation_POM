Feature: Manage Message App

  Scenario Outline: BR_MM_2108_39- Validate 'Create message' functionality by uploading Activation Message file from BP account to Customer which result to Successful
    Given the user logs in as 'MM_User' in the login page
    And the ManageMessages app is clicked in the USSC launcher page
    When Click on Create button
    Then New Message pop up must open
    And Select the '<message type>'
    Then <message type> will get selected
    And Attach the message file BR_MM_2108_40_1.xml in xml format
    And Enter the '<valid info>' in additional information field
    Then Accept message file should get uploaded
    And Click on Upload button
    Then 'File uploaded successfully' message should display
    And Open the CPI tenant and check for the message which has been sent from UI and copy the co-relation id
    And Now Paste the same corelation in the search box and click on GO button.
    Then Result should show for the searched co-relation ID
    Then All the related iflows should get hit with completed status
    And Validate the Message with all the detail
    Then Sender, Receiver, Lot No., GTIN, Business doc ID information should match with the attached payload information

    Examples:
      | message type                    | valid info                               |
      | Regulatory Reporting for Brazil | Comments for Activation Message Scenario |
      | Dispatch                        | Comments for Activation Message Scenario |
      | Receive                         | Comments for Activation Message Scenario |
      | Finalization                    | Comments for Activation Message Scenario |

  Scenario Outline: BR_MM_2108_40- Validate 'Create message' functionality by uploading Activation Message file BP account to Customer which result to Error
    When Click on Create button
    Then New Message pop up must open
    And Select the '<message type>'
    And Attach the message file <file> in xml format
    And Enter the '<valid info>' in additional information field
    Then <message type> will get selected
    Then Accept message file should get uploaded
    And Click on Upload button
    Then 'File uploaded successfully' message should display
    And Open the CPI tenant and check for the message which has been sent from UI and copy the co-relation id
    Then All the related iflows should get hit with completed status
    And Now Paste the same corelation in the search box and click on GO button.
    Then  Result should show for the searched co-relation ID
    And Validate the Message with all the detail.
    Then Sender, Receiver, Lot No., GTIN, Business doc ID information should match with the attached payload information
    Examples:
      | message type                    | file                                       | valid info                               |
      | Regulatory Reporting for Brazil | BR_MM_2108_40_1.xml                        | Comments for Activation Message Scenario |
      | Dispatch                        | Anvisa_Shipping_New_tags_QA_New_BP (3).xml | Comments for Activation Message Scenario |
      | Receive                         | Receive_New_Tags - New BP (4).xml          | Comments for Activation Message Scenario |
      | Finalization                    | Final_Export - New Tags - New BP (4).xml   | Comments for Activation Message Scenario |