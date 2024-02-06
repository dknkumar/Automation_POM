@USSC @verifyProductPackage
Feature: Verify Product Package App Test Cases

  Scenario: USSC_101, USSC_102, USSC_106, USSC122:  "New Verification Request" -> "Decoded Data" -> Check Field: GTIN -> negative test -> check digit
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And title of verify product pack screen is 'New Verification Request'
    And header of request types are displayed
    And click on verify button
    Then the toast message 'Please fill all the fields correctly' appears
    And the button labeled Close is clicked
    And the error message with red border around the gtin field is displayed
    And the error message with red border around the lot field is displayed
    And the error message with red border around the serial field is displayed
    And the error message with red border around the expireDate field is displayed
    And the GTIN value '4036050500800912 is entered
    And the error message with red border around the gtin field is displayed

  Scenario Outline: USSC_106a "New Verification Request" -> "Decoded Data" -> Check Field: GTIN -> negative test -> field length error
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the error message with red border around the gtin field is displayed
    Examples:
      | user              | GTIN           |
      | USSC_Manufacturer | 10000000000008 |

  Scenario Outline: USSC_110 , USSC_110a, USSC_113 : "New Verification Request" -> "Decoded Data" -> Check Field: Expiration Date  -> positive test -> YYMM
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the Expiration Date value '<ExpirationDate>' is entered
    Then GSl Element contains the decoded value
      | ExpirationDate | 200831 |
    And the Expiration Date value '<ExpirationDateInvalidMonth>' is entered
    And the error message with red border around the expireDate field is displayed
    And the Expiration Date value '<ExpirationDateInvalidMonthWithoutDate>' is entered
    Then GSl Element contains the decoded value
      | ExpirationDate | 201331 |
    And the error message with red border around the expireDate field is displayed
    Examples:
      | user              | ExpirationDate | ExpirationDateInvalidMonth | ExpirationDateInvalidMonthWithoutDate |
      | USSC_Manufacturer | 2008           | 201315                     | 2013                                  |


  Scenario Outline:USSC_107,USSC_108,USSC_109 USSC_111 "New Verification Request" -> "Decoded Data" -> Check Field: Expiration Date -> positive test -> date picker
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    Then GSl Element contains the decoded value
      | GTIN | 12345 |
    And the Batch Number value '<BatchNumber>' is entered
    Then GSl Element contains the decoded value
      | BatchNumber | <BatchNumber> |
    And the Serial Number value '<SerialNumber>' is entered
    Then GSl Element contains the decoded value
      | SerialNumber | <SerialNumber> |
    And the Expiration Date value future '<date>' is selected from date picker calender
    Then GSl Element contains the decoded value
      | DatePickerExpirationDate |
    Examples:
      | user              | date | GTIN      | BatchNumber | SerialNumber |
      | USSC_Manufacturer | 15   | abcd12345 | xyz_123     | test1233     |


  Scenario Outline: USSC_112 "New Verification Request" -> "Decoded Data" -> Check Field: Expiration Date -> negative test -> exp date in the past test -> date picker
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the Expiration Date value past '<date>' is selected from date picker calender
    And there is a warning message with yellow border around the ExpirationDate field is displayed
    Then GSl Element contains the decoded value
      | DatePickerExpirationDate |
    Examples:
      | user              | date |
      | USSC_Manufacturer | 15   |

  Scenario Outline: USSC_115 "New Verification Request"-> "Decoded Data "-> Batch Number
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And Verify the Ghost Text '<batchNumberGhostText>' is present by default in BatchNumber field
    And the Batch Number value '<BatchNumber>' is entered
    Then verify the maximum allowed length for the fields in verify product pack page
      | BatchNumber | <BatchNumber> |
    Examples:
      | user              | BatchNumber                                                                                           | batchNumberGhostText |
      | USSC_Manufacturer | temp1234567890123jkfhdshfeyrASFHJ**())^$#dasjfhjksfhjdshfsdhfsdhfklsdhklfbvncxbvuiwehfidkncmxznbjv^%a | Maxlength 20         |

  Scenario Outline: USSC_116, USSC_117 "New Verification Request"-> "Decoded Data "->Serial Number
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And Verify the Ghost Text '<SerialNumberGhostText>' is present by default in SerialNumber field
    And the Serial Number value '<SerialNumber>' is entered
    Then verify the maximum allowed length for the fields in verify product pack page
      | SerialNumber | <SerialNumber> |
    And the Serial Number value '<SerialNumber1>' is entered
    Then verify the '<SerialNumber1>' field
    Examples:
      | user              | SerialNumber                                                                                          | SerialNumberGhostText | SerialNumber1                           |
      | USSC_Manufacturer | temp1234567890123jkfhdshfeyrAS98_**())^$#dasjfhjksfhjdshfsdhfsdhfklsdhklfbvncxbvuiwehfidkncmxznbjv^%a | Maxlength 20          | test123!@#$%^&*()ABC_?<>:"{}[]\;',./:"' |

  Scenario Outline: USSC_121 , USSC_123: "New Verification Request" -> "GS1 Element String" -> Check decoded data2
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And click on verify button
    Then the toast message 'Please fill all the fields correctly' appears
    And the button labeled Close is clicked
    And the error message with red border around the gs1 field is displayed
    And the gsl element '<GS1Element>' is entered
    Then verify values displayed against each field
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | GS1Element                                                    | ErrorMessage                         |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | (01)00363391180998(21)7578533417(10)TestvinayQA2023(17)231230 | Please fill all the fields correctly |

  Scenario Outline: USSC_118, USSC_125 , USSC 119 : "New Verification Request" -> "Decoded Data" -> "Response" Screen -> Check Data Elements
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    Then GSl Element contains the decoded value
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    And click on verify button
    And click on Verification Tab
    And check correct values are populated on Verification screen
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
      | GS1Element     | <GS1Element>     |
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | GS1Element                                                    |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | (01)00363391180998(21)7578533417(10)TestvinayQA2023(17)231230 |


  Scenario Outline: USSC_127 :"New Verification Request" -> "GS1 Element String" -> Execute Verification -> valid PI
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And check correct values are populated on Verification screen
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
      | GS1Element     | <GS1Element>     |
    And click on verify button
    And click on Verification Tab
    And check correct values are populated on Verification screen
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
      | GS1Element     | <GS1Element>     |
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | GS1Element                                                    |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | (01)00363391180998(21)7578533417(10)TestvinayQA2023(17)231230 |

  Scenario Outline: USSC_128, USSC_2008_19, USSC_2008_33, USSC_2102_18: "New Verification Request" -> "Decoded Data" -> Execute Verification -> valid PI
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>       |
      | Verification Failure Reason |                  |
      | Additional Information      |                  |
      | Verification Timestamp      |                  |
      | Responder GLN               | <ResponderGln>   |
      | Correlation UUID            |                  |
      | HTTP return code            | <HTTPReturnCode> |
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber | GS1Element                                                    | Verified | ResponderGln  | HTTPReturnCode |
      | USSC_Manufacturer | 00363391180998 | auto83297   | 231230         | 7578533429   | (01)00363391180998(21)7578533417(10)TestvinayQA2023(17)231230 | true     | 0363391100002 | 200            |
      | USSC_Wholesaler   | 00363391180998 | auto83297   | 231230         | 7578533429   | (01)00363391180998(21)7578533417(10)TestvinayQA2023(17)231230 | true     | 0363391100002 | 200            |
      | USSC_Wholesaler   | 00363391180998 | auto83297   | 231200         | 7578533429   | (01)00363391180998(21)7578533417(10)TestvinayQA2023(17)231230 | true     | 0363391100002 | 200            |

#to be fixed

  Scenario Outline: USSC_129 ,USSC_2008_21,2008_35: "New Verification Request" -> "Decoded Data" -> Execute Verification -> invalid PI (PI not found)
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value past '<date>' is selected from date picker calender
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      |                             |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |

    Examples:
      | user              | GTIN           | BatchNumber | date | SerialNumber | Verified | ResponderGln  | HTTPReturnCode | VerificationFailureReason |
      | USSC_Manufacturer | 00363391180998 | NT5QA2029   | 20   | 2197857644   | false    | 0363391100002 | 200            | No_reason_provided        |
      | USSC_Wholesaler   | 00363391180998 | NT5QA2029   | 20   | 2197857644   | false    | 0363391100002 | 200            | No_reason_provided        |


  Scenario Outline: USSC_130 : "New Verification Request" -> "Decoded Data" -> Execute Verification -> adjust request -> change date
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify header of verification response screen
    And the button labeled Adjust Request is clicked
    Then verify header of adjust request screen
    And the Expiration Date value '<NewExpirationDate>' is entered
    And click on verify button
    Then verify header of verification response screen
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | NewExpirationDate |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | 261230            |

  Scenario Outline: USSC_131 : "New Verification Request" -> "Decoded Data" -> Execute Verification -> adjust request -> change batch
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify header of verification response screen
    And the button labeled Adjust Request is clicked
    Then verify header of adjust request screen
    And the Batch Number value '<NewBatchNumber>' is entered
    And click on verify button
    Then verify header of verification response screen
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | NewBatchNumber |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | NT4QA2023      |

  Scenario Outline: USSC_132 : "New Verification Request" -> "Decoded Data" -> Execute Verification -> adjust request -> change GTIN / SN
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify header of verification response screen
    And the button labeled Adjust Request is clicked
    Then verify header of adjust request screen
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify header of verification response screen
    And the button labeled Adjust Request is clicked
    Then verify header of adjust request screen
    And the GTIN value '<NewGTIN>' is entered
    And click on verify button
    Then verify header of verification response screen
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | NewGTIN        |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | 00363391180998 |

  Scenario Outline: USSC_133 : "New Verification Request" -> "Decoded Data" -> Execute Verification -> new request
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify header of verification response screen
    And the button labeled Adjust Request is clicked
    Then verify header of adjust request screen
    And the GTIN value '<NewGTIN>' is entered
    And the Batch Number value '<NewBatchNumber>' is entered
    And the Expiration Date value '<NewExpirationDate>' is entered
    And the Serial Number value '<NewSerialNumber>' is entered
    And click on verify button
    Then verify header of verification response screen
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | NewGTIN        | NewBatchNumber  | NewExpirationDate | NewSerialNumber |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | 00363391180998 | TestvinayQA2023 | 231230            | 7578533417      |


  Scenario Outline: USSC_134 , USSC_2102_01: "New Verification Request" -> "Decoded Data" -> change settings
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    And click on setting button in verify screen
    Then verify header of select GLN screen
    Then verify sender GLN '<SenderGln>','<SenderGln1>' change for the verification
    And header of request types are displayed
    And click on setting button
    And change the sender GLN to '<SenderGln>'
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | SenderGln     | SenderGln1    |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | 0363391100002 | 0363391100019 |

  Scenario Outline: USSC_135,USSC_136: "New Verification Request" -> "Decoded Data" -> Home
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And the button labeled Cancel is clicked
    Then the title of the page is '<Title>'
    And switches to default content
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And the button labeled Cancel is clicked
    Then the title of the page is '<Title>'
    Examples:
      | user              | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | Title | GS1Element                                                    |
      | USSC_Manufacturer | 00363391180998 | TestvinayQA2023 | 231230         | 7578533417   | Home  | (01)00363391180998(21)7578533417(10)TestvinayQA2023(17)231230 |

  Scenario Outline: USSC_2005_3 : Verification:User Type CMO
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then the toast message 'User unauthorized.' appears
    And the button labeled Close is clicked
    Then title of verify product pack screen is '<title>'
    Examples:
      | user     | title          | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | ErrorMessage       |
      | USSC_CMO | Adjust Request | 00361958090117 | TestvinayQA2023 | 231230         | 7578533417   | User unauthorized. |

  Scenario Outline: USSC_2005_4 : User Type WHO and GTIN exists in Mediledger
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    Then GSl Element contains the decoded value
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    And click on verify button
    And click on Verification Tab
    And check correct values are populated on Verification screen
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
      | GS1Element     | <GS1Element>     |
    Examples:
      | user            | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | GS1Element                                                    |
      | USSC_Wholesaler | 00363391180998 | TestvinayQA2023 | 211230         | 7578533418   | (01)00363391180998(21)7578533418(10)TestvinayQA2023(17)211230 |


  Scenario Outline: USSC_2008_20, USSC_2008_41:  Valid PI Scenario _ Datex (DD=blank)
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
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
    Examples:
      | user              | GTIN           | BatchNumber       | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Manufacturer | 00363391180998 | AutoFalseRecalled | 2112           | auto52345678911 | false    | 0363391100002 | 200            | Recalled              | Manufacturer_policy       |
      | USSC_Wholesaler   | 00363391180998 | AutoFalseRecalled | 2112           | auto52345678911 | false    | 0363391100002 | 200            | Recalled              | Manufacturer_policy       |

  Scenario Outline: USSC_2008_27, USSC_2008_34:  Valid PI Scenario _ Datex (DD=blank)
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    Then verify values displayed against each field
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user              | GS1Element                                                        | GTIN           | BatchNumber       | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Manufacturer | (01)00363391180998(21)auto13435435341(10)AutoFalseSuspect(17)2312  | 00363391180998 | AutoFalseSuspect  | 231231         | auto13435435341 | false    | 0363391100002 | 200            | Suspect               | Not_for_re-distribution   |
      | USSC_Wholesaler   | (01)00363391180998(21)auto52345678911(10)AutoFalseRecalled(17)2112 | 00363391180998 | AutoFalseRecalled | 211231         | auto52345678911 | false    | 0363391100002 | 200            | Recalled              | Manufacturer_policy       |

  Scenario Outline: USSC_129b,USSC_2008_23,USSC_2008_24,USSC_2008_39,USSC_2008_40: "New Verification Request" -> "Decoded Data" -> Execute Verification -> valid PI (MAH?WHO User B1,B2,C1,C2 Rules)
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
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
    Examples:
      | user              | GTIN           | BatchNumber       | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Manufacturer | 00363391180998 | AutoFalseSuspect  | 231231         | auto13435435341 | false    | 0363391100002 | 200            | Suspect               | Not_for_re-distribution   |
      | USSC_Manufacturer | 00363391180998 | AutoTrueRecalled  | 221231         | auto12345678911 | true     | 0363391100002 | 200            | Recalled              |                           |
      | USSC_Wholesaler   | 00363391180998 | AutoFalseRecalled | 211231         | auto52345678911 | false    | 0363391100002 | 200            | Recalled              | Manufacturer_policy       |
      | USSC_Wholesaler   | 00363391180998 | AutoFalse         | 220815         | auto72345678911 | false    | 0363391100002 | 200            |               | Manufacturer_policy       |

  Scenario Outline: USSC_2008_25,USSC_2008_26,USSC_2008_37,USSC_2008_38: "New Verification Request" -> "Decoded Data" -> Execute Verification -> valid PI (MAH?WHO User B1,B2,C1,C2 Rules)
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    Then verify values displayed against each field
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user              | GS1Element                                                          | GTIN           | BatchNumber       | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Manufacturer | (01)00363391180998(21)auto52345678911(10)AutoFalseRecalled(17)211231 | 00363391180998 | AutoFalseRecalled | 211231         | auto52345678911 | false    | 0363391100002 | 200            | Recalled              | Manufacturer_policy       |
      | USSC_Manufacturer | (01)00363391180998(21)auto72345678911(10)AutoFalse(17)220815         | 00363391180998 | AutoFalse         | 220815         | auto72345678911 | false    | 0363391100002 | 200            |               | Manufacturer_policy       |
      | USSC_Wholesaler   | (01)00363391180998(21)auto13435435341(10)AutoFalseSuspect(17)231231  | 00363391180998 | AutoFalseSuspect  | 231231         | auto13435435341 | false    | 0363391100002 | 200            | Suspect               | Not_for_re-distribution   |
      | USSC_Wholesaler   | (01)00363391180998(21)auto12345678911(10)AutoTrueRecalled(17)211120  | 00363391180998 | AutoTrueRecalled  | 221231         | auto12345678911 | true     | 0363391100002 | 200            | Recalled              |                           |


  Scenario Outline: USSC_2008_20,USSC_2008_34: Valid PI Scenario _ Datex (DD=blank)
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    Then verify values displayed against each field
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>       |
      | Verification Failure Reason |                  |
      | Additional Information      |                  |
      | Verification Timestamp      |                  |
      | Responder GLN               | <ResponderGln>   |
      | Correlation UUID            |                  |
      | HTTP return code            | <HTTPReturnCode> |
    Examples:
      | user              | GS1Element                                            | GTIN           | BatchNumber | ExpirationDate | SerialNumber | Verified | ResponderGln  | HTTPReturnCode |
      | USSC_Manufacturer | (01)00363391180998(21)7578533429(10)auto83297(17)2312 | 00363391180998 | auto83297   | 231231         | 7578533429   | true     | 0363391100002 | 200            |

  Scenario Outline: USSC_2008_22,USSC_2008_36: "New Verification Request" -> "Decoded Data" -> Execute Verification -> invalid PI (PI not found)
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    Then verify values displayed against each field
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      |                             |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user              | GS1Element                                            | ExpirationDate | GTIN           | BatchNumber | SerialNumber | Verified | ResponderGln  | HTTPReturnCode | VerificationFailureReason |
      | USSC_Manufacturer | (01)00363391180998(21)2197857644(10)NT5QA2029(17)2205 | 220531         | 00363391180998 | NT5QA2029   | 2197857644   | false    | 0363391100002 | 200            | No_reason_provided        |
      | USSC_Wholesaler   | (01)00363391180998(21)2197857644(10)NT5QA2029(17)2205 | 220531         | 00363391180998 | NT5QA2029   | 2197857644   | false    | 0363391100002 | 200            | No_reason_provided        |

  Scenario Outline: USSC_2008_28,USSC_2008_50: Expired PI found in BC and Default(No rule)  configuration selected
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And click on expiry rules tab
    And select '<Expiry Rule>' rule for expired PI
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>       |
      | Verification Failure Reason |                  |
      | Additional Information      |                  |
      | Verification Timestamp      |                  |
      | Responder GLN               | <ResponderGln>   |
      | Correlation UUID            |                  |
      | HTTP return code            | <HTTPReturnCode> |
    Examples:
      | user              | GS1Element                                                     | Expiry Rule | Verified | ResponderGln  | HTTPReturnCode |
      | USSC_Manufacturer | (01)00363391180998(21)auto5456788999(10)AutoTestTrue(17)211215 | Default     | true     | 0363391100002 | 200            |

  Scenario Outline:  USSC_2008_42: Expired PI found in BC and Default(No rule)  configuration selected
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>       |
      | Verification Failure Reason |                  |
      | Additional Information      |                  |
      | Verification Timestamp      |                  |
      | Responder GLN               | <ResponderGln>   |
      | Correlation UUID            |                  |
      | HTTP return code            | <HTTPReturnCode> |
    Examples:
      | user            | GS1Element                                                     | Verified | ResponderGln  | HTTPReturnCode |
      | USSC_Wholesaler | (01)00363391180998(21)auto5456788999(10)AutoTestTrue(17)211215 | true     | 0363391100002 | 200            |


  Scenario Outline: USSC_2008_29, USSC_2008_49: Expired PI found in BC ,  should not be resold - MAH selected to respond with verified = True , additional info = Expired
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And click on expiry rules tab
    And select '<Expiry Rule>' rule for expired PI
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user              | GS1Element                                                     | Expiry Rule | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Manufacturer | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)220131 | B1 exp      | true     | 0363391100002 | 200            | Expired               | -                         |


  Scenario Outline: USSC_2008_43: Expired PI found in BC ,  should not be resold - MAH selected to respond with verified = True , additional info = Expired
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user            | GS1Element                                                     | Expiry Rule | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Wholesaler | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)220131 | B1 exp      | true     | 0363391100002 | 200            | Expired               | -                         |


  Scenario Outline: USSC_2008_30, USSC_2008_31, USSC_2008_49: Expired PI found in BC ,  should not be resold - MAH selected to respond with verified = False , additional info = Expired  and Verification Failure Reason : Manufacturer_policy
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And click on expiry rules tab
    And select '<Expiry Rule>' rule for expired PI
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user              | GS1Element                                                     | Expiry Rule | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Manufacturer | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)220131 | B2 exp      | false    | 0363391100002 | 200            | Expired               | Manufacturer_policy       |
      | USSC_Manufacturer | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)2201   | B2 exp      | false    | 0363391100002 | 200            | Expired               | Manufacturer_policy       |

  Scenario Outline: USSC_2008_44, USSC_2008_45: Expired PI found in BC ,  should not be resold - MAH selected to respond with verified = False , additional info = Expired  and Verification Failure Reason : Manufacturer_policy
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user            | GS1Element                                                     | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Wholesaler | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)220131 | false    | 0363391100002 | 200            | Expired               | Manufacturer_policy       |
      | USSC_Wholesaler | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)2201   | false    | 0363391100002 | 200            | Expired               | Manufacturer_policy       |

  Scenario Outline: USSC_2008_32: Expired PI found in BC with Disposition configured to respond with verified=TRUE and additionalInfo=RECALLED but the Expired PI response configuration is set to B1exp rule .
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
#    And click on Add Rule button
#    And the first Disposition name '<DispName>' is entered
#    And the disposition rule B1 is selected for first record
#    And the button labeled Save is clicked
#    And the button labeled OK is clicked
    And click on expiry rules tab
    And select '<Expiry Rule>' rule for expired PI
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
#    And navigate to home screen
#    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
#    And select the disposition '<DispName>'
#    And click on Delete Icon
#    And the button labeled OK is clicked
    Examples:
      | user              | GS1Element                                                      | Expiry Rule | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason | DispName              |
      | USSC_Manufacturer | (01)00363391180998(21)auto76567889981(10)auto_expired(17)210221 | B2 exp      | true     | 0363391100002 | 200            | Recalled              | -                         | auto_expired_recalled |

  Scenario Outline: USSC_2008_46: Expired PI found in BC with Disposition configured to respond with verified=TRUE and additionalInfo=RECALLED but the Expired PI response configuration is set to B1exp rule .
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    Examples:
      | user            | GS1Element                                                      | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Wholesaler | (01)00363391180998(21)auto76567889981(10)auto_expired(17)210221 | true     | 0363391100002 | 200            | Recalled              | -                         |

  Scenario Outline: USSC_2008_1,USSC_2008_2: New Verification Request -> "Decoded Data" -> Execute Verification -> valid PI -Verified-True -> MAH and WHO user
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>       |
      | Verification Failure Reason |                  |
      | Additional Information      |                  |
      | Verification Timestamp      |                  |
      | Responder GLN               | <ResponderGln>   |
      | Correlation UUID            |                  |
      | HTTP return code            | <HTTPReturnCode> |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then validate the execution duration captured
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode |
      | USSC_Manufacturer | 00363391180998 | AutoBatch1  | 231231         | auto2343543534 | true     | 0363391100002 | 200            |
      | USSC_Wholesaler   | 00363391180998 | AutoBatch1  | 231231         | auto2343543534 | true     | 0363391100002 | 200            |


  Scenario Outline: USSC_2008_3,USSC_2008_4: New Verification Request -> "Decoded Data" -> Execute Verification -> valid PI ->Verified-False-> MAH and WHO user
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      |                             |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then validate the execution duration captured
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | VerificationFailureReason |
      | USSC_Manufacturer | 00363391180998 | AutoBatch20 | 220228         | auto5576567892 | false    | 0363391100002 | 200            | No_reason_provided        |
      | USSC_Wholesaler   | 00363391180998 | AutoBatch20 | 220228         | auto5576567892 | false    | 0363391100002 | 200            | No_reason_provided        |

@test55
  Scenario Outline: USSC_2008_5: New Verification Request -> "Decoded Data" -> Execute Verification -> valid expired PI -> MAH User
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And click on expiry rules tab
    And select '<Expiry Rule>' rule for expired PI
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
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
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then validate the execution duration captured
    Examples:
      | user              | GS1Element                                                     | Expiry Rule | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Manufacturer | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)220131 | B2 exp      | false    | 0363391100002 | 200            | Expired               | Manufacturer_policy       |


  Scenario Outline: USSC_2008_6: New Verification Request -> "Decoded Data" -> Execute Verification -> valid expired PI -> WHO User
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
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
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then validate the execution duration captured
    Examples:
      | user            | GS1Element                                                     | Expiry Rule | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason |
      | USSC_Wholesaler | (01)00363391180998(21)auto84567889991(10)AutoTestTrue(17)220131 | B2 exp      | false    | 0363391100002 | 200            | Expired               | Manufacturer_policy       |
@test11
  Scenario Outline: USSC_2008_15: Portal Verification-Company Names-3 same GCP's with different company name and end date
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Responder Company           | <ResponderCompany>          |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then the results contains the filtered values in the response
      | RequesterCompany | <RequesterCompany> |
    Examples:
      | user            | GS1Element                                                         | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason | ResponderCompany        | RequesterCompany |
      | USSC_Wholesaler | (01)00363391180998(21)auto13435435341(10)AutoFalseSuspect(17)231231 | false    | 0363391100002 | 200            | Suspect               | Not_for_re-distribution   | SAP Manufacturer Future | SAP Wholesaler Present  |


  Scenario Outline: USSC_2008_15,USSC_2011_02: Portal Verification_Company Names_3 same GCP's with different company name and end date
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Responder Company           | <ResponderCompany>          |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then the results contains the filtered values in the response
      | RequesterCompany | <RequesterCompany> |
    Examples:
      | user              | GTIN           | BatchNumber      | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | ResponderCompany        | RequesterCompany        | AdditionalInformation | VerificationFailureReason |
      | USSC_Wholesaler   | 00363391180998 | AutoFalseSuspect | 231231         | auto13435435341 | false    | 0363391100002 | 200            | SAP Manufacturer Future | SAP Wholesaler          | Suspect               | Not_for_re-distribution   |
      | USSC_Manufacturer | 00363391180998 | AutoFalseSuspect | 231231         | auto13435435341 | false    | 0363391100002 | 200            | SAP Manufacturer Future | SAP Manufacturer Future | Suspect               | Not_for_re-distribution   |

  Scenario Outline: USSC_2008_8: Portal Verification_Company Names_Requester and Responder GCP and associated company name are maintained in tables
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified               | <Verified>         |
      | Verification Timestamp |                    |
      | Responder GLN          | <ResponderGln>     |
      | Responder Company      | <ResponderCompany> |
      | Correlation UUID       |                    |
      | HTTP return code       | <HTTPReturnCode>   |
    And get UUID value from verification response screen
    And navigate to home screen
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then the results contains the filtered values in the response
      | RequesterCompany | <RequesterCompany> |
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | ResponderCompany        | RequesterCompany        |
      | USSC_Manufacturer | 00363391180998 | AutoBatch1  | 231231         | auto2343543534 | true     | 0363391100002 | 200            | SAP Manufacturer Future | SAP Manufacturer Future |

  Scenario Outline: USSC_2008_10: Portal Verification_Company Names_Only Responder GCP and associated company  is maintained in  GCP content table
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on setting button
    Then verify header of select GLN screen
    Then verify sender GLN '<SenderGln>','<SenderGln1>' change for the verification
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>         |
      | Verification Failure Reason |                    |
      | Additional Information      |                    |
      | Verification Timestamp      |                    |
      | Responder GLN               | <ResponderGln>     |
      | Responder Company           | <ResponderCompany> |
      | Correlation UUID            |                    |
      | HTTP return code            | <HTTPReturnCode>   |
    And get UUID value from verification response screen
    And click on setting button in verify screen
    And change the sender GLN to '<SenderGln>'
    And the button labeled Cancel is clicked
    And switches to default content
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | UUID |
    And the button labeled OK is clicked
    And set UUID value
    And Go button is clicked in filter modal pop up
    Then the results contains the filtered values in the response
      | RequesterCompany | <RequesterCompany> |
    Examples:
      | user            | GTIN           | BatchNumber | ExpirationDate | SerialNumber   | Verified | ResponderGln  | HTTPReturnCode | ResponderCompany        | RequesterCompany | SenderGln     | SenderGln1    |
      | USSC_Wholesaler | 00363391180998 | AutoBatch1  | 231231         | auto2343543534 | true     | 0363391100002 | 200            | SAP Manufacturer Future | Unknown          | 0363392100056 | 0363392100049 |

  Scenario Outline: USSC_2011_09, USSC_2011_10 - BatchNumber and serial Number Field is not allowed to contain Blank/space char in the Decoded data of new verification request form
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the Batch Number value '<BatchNumber>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    Then verify the field for blank value validation
      | BatchNumber  | <BatchNumber>  |
      | SerialNumber | <SerialNumber> |
    Examples:
      | user              | BatchNumber      | SerialNumber      |
      | USSC_Manufacturer | test batch blank | test serial blank |

  Scenario Outline: USSC_2011_11,USSC_2011_12,USSC_2011_13 :Batch Number and serial number Field blank value validation and empty date validation in GS1  string tab
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And check correct values are populated on Verification screen
      | GTIN           | <GTIN>             |
      | BatchNumber    | <BatchNumber>      |
      | ExpirationDate | <ExpirationDate>   |
      | SerialNumber   | <SerialNumber>     |
      | GS1Element     | <ActualGS1Element> |
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber | GS1Element                                              | ActualGS1Element                                      |
      | USSC_Manufacturer | 00363391180998 | TestBlank   | 230131         | 7578533417   | (01)00363391180998(21)75785 33417(10)Test Blank(17)2301 | (01)00363391180998(21)7578533417(10)TestBlank(17)2301 |

  Scenario Outline: USSC_2011_08: MAH VR where requester GLN is added to blocklist  by MAH with Active Switch = ON
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And click on blocked requester gln tab
    And the gln '<GLN>' is added to blocklist
    And the gln '<GLN2>' is added to blocklist
    And the gln '<GLN3>' is added to blocklist
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then the toast message '<Error Message>' appears
    And the button labeled Close is clicked
    And the button labeled Cancel is clicked
    And switches to default content
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | GTIN             |
      | Lot/Batch Number |
      | Serial Number    |
    And the button labeled OK is clicked
    And set filter value gtin '<GTIN>'
    And set filter value lot '<LOT>'
    And set filter value serialNumber '<SerialNumber>'
    And Go button is clicked in filter modal pop up
    Then the results contains the filtered values in the response
      | SerialNumber            | <SerialNumber>            |
      | GTIN                    | <GTIN>                    |
      | LOT                     | <LOT>                     |
      | Status                  | <Status>                  |
      | ResponseCode            | <ResponseCode>            |
      | ResponseCodeDescription | <ResponseCodeDescription> |
#    And navigate to home screen
#    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
#    And click on blocked requester gln tab
#    And select the gln '<GLN>' record
#    And click on Active switch for selected the gln '<GLN>'
#    And the reason '<Reason>' is entered in pop up window
#    And the button labeled Add is clicked
#    And the button labeled Save is clicked
#    And the button labeled OK is clicked
#    Then the toast message '<SaveSuccessMessage>' appears
    Examples:
      | user              | GLN           | GLN2          | GLN3          | Reason        | SaveSuccessMessage                             | GTIN           | SerialNumber  | LOT          | GS1Element                                                    | Error Message         | Status | ResponseCode | ResponseCodeDescription |
      | USSC_Manufacturer | 0363391100002 | 0363392100056 | 0363392100049 | auto_test_mah | Blocklist GLN configuration succesfully saved. | 00363391180998 | auto456788999 | AutoTestTrue | (01)00363391180998(21)auto456788999(10)AutoTestTrue(17)200115 | Requester GLN Blocked | Error  | 403          | Requester GLN Blocked   |

  Scenario Outline: USSC_2011_01: WHO VR where requester GLN is added to blocklist  by MAH with Active Switch = ON
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then the toast message '<Error Message>' appears
    And the button labeled Close is clicked
    And the button labeled Cancel is clicked
    And switches to default content
    And the VerificationLog app is clicked in the USSC launcher page
    And select all columns to display under personalisation
    And the button labeled OK is clicked
    And the filters button is clicked in the verification log page
    And filter components are selected
      | GTIN             |
      | Lot/Batch Number |
      | Serial Number    |
    And the button labeled OK is clicked
    And set filter value gtin '<GTIN>'
    And set filter value lot '<LOT>'
    And set filter value serialNumber '<SerialNumber>'
    And Go button is clicked in filter modal pop up
    Then the results contains the filtered values in the response
      | SerialNumber            | <SerialNumber>            |
      | GTIN                    | <GTIN>                    |
      | LOT                     | <LOT>                     |
      | Status                  | <Status>                  |
      | ResponseCode            | <ResponseCode>            |
      | ResponseCodeDescription | <ResponseCodeDescription> |
    Examples:
      | user            | GTIN           | SerialNumber   | LOT        | GS1Element                                                   | Error Message          | Status | ResponseCode | ResponseCodeDescription |
      | USSC_Wholesaler | 00363391180998 | auto2343543534 | AutoBatch1 | (01)00363391180998(21)auto2343543534(10)AutoBatch1(17)231231 | Requester GLN Blocked. | Error  | 403          | Requester GLN Blocked   |

  Scenario Outline: USSC_2011_05: Multisender GLN-WHO performs VR  with the requester GLN that is added to blocklist with Switch = ON
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on setting button
    Then verify header of select GLN screen
    Then verify sender GLN '<GLN1>','<GLN2>' change for the verification
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then the toast message '<Error Message>' appears
    And the button labeled Close is clicked
    And click on setting button
    And change the sender GLN to '<GLN1>'
    And the button labeled Cancel is clicked
    Examples:
      | user            | GLN1          | GLN2          | Reason        | SaveSuccessMessage                             | GS1Element                                                    | Error Message          | GTIN           | BatchNumber | ExpirationDate | SerialNumber   |
      | USSC_Wholesaler | 0363392100056 | 0363392100049 | auto_test_who | Blocklist GLN configuration succesfully saved. | (01)00363391180998(21)auto456788999(10)AutoTestTrue(17)200115 | Requester GLN Blocked. | 00363391180998 | AutoBatch1  | 220228         | auto5576567892 |


  Scenario Outline: USSC_2011_08: MAH VR where requester GLN is added to blocklist  by MAH with Active Switch = ON
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And click on blocked requester gln tab
    And select the gln '<GLN1>' record
    And select the gln '<GLN2>' record
    And select the gln '<GLN3>' record
    And click on Active switch for selected the gln '<GLN1>'
    And the reason '<Reason>' is entered in pop up window
    And the button labeled Add is clicked
    And click on Active switch for selected the gln '<GLN2>'
    And the reason '<Reason>' is entered in pop up window
    And the button labeled Add is clicked
    And click on Active switch for selected the gln '<GLN3>'
    And the reason '<Reason>' is entered in pop up window
    And the button labeled Add is clicked
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    Examples:
      | user              | GLN1          | GLN2          | GLN3          | Reason        | SaveSuccessMessage                             |
      | USSC_Manufacturer | 0363391100002 | 0363392100056 | 0363392100049 | auto_test_mah | Blocklist GLN configuration succesfully saved. |


  Scenario Outline: USSC_2102_08 ,USSC_2102_09:Expiration date with today's value should not be shown with any warning msg in the Decoded data and GS1 element of new verification request form
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the Expiration Date value current '<date>' is selected from date picker calender
    And the GTIN value '<GTIN>' is entered
    Then warning message is not displayed for the ExpirationDate field
    And get the gsl element value from the field
    And click on GslElementString tab
    And the retrieved gsl element is entered
    Then warning message is not displayed for the ExpirationDate field
    Examples:
      | user            | date | GTIN          |
      | USSC_Wholesaler | 1    | 0363391100002 |

  Scenario Outline: USSC_2102_14,USSC_2102_15:  Verify the VR response with datex outside GTIN validity range
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then the toast message '<ErrorMessage>' appears
    Examples:
      | user              | GS1Element                                                        | ErrorMessage                                                                       |
      | USSC_Wholesaler   | (01)00363391180998(21)auto323456789(10)AutoFalseSuspect(17)121231 | The GTIN was not found in the Lookup Directory. A verification cannot be executed. |
      | USSC_Manufacturer | (01)00363391180998(21)auto323456789(10)AutoFalseSuspect(17)121231 | The GTIN was not found in the Lookup Directory. A verification cannot be executed. |


  Scenario Outline: USSC_2008_47: MAH performs verification request after updating Disposition rule from B1 to B2 rule
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And verify Disposition name '<Disposition>' already exists in the list
    And click on Add Rule button
    And the first Disposition name '<Disposition>' is entered
    And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And select the disposition '<Disposition>'
    And the disposition rule is updated to B2 for '<Disposition>'
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      | <AdditionalInformation>     |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    And navigate to home screen
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And select the disposition '<Disposition>'
    And click on Delete Icon
    And the button labeled OK is clicked
    Examples:
      | user              | GS1Element                                                | Disposition     | rule | Verified | ResponderGln  | HTTPReturnCode | AdditionalInformation | VerificationFailureReason | delete message  |
      | USSC_Manufacturer | (01)00363391180998(21)auto8965754(10)AutoBatch4(17)211230 | autodisprefresh | B1   | false    | 0363391100002 | 200            | Recalled              | Manufacturer_policy       | Rule(s) deleted |


  Scenario Outline: USSC_2008_48: MAH performs verification request after updating Disposition rule from C1 to C2 rule
    Given the user logs in as '<user>' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And verify Disposition name '<Disposition>' already exists in the list
    And click on Add Rule button
    And the first Disposition name '<Disposition>' is entered
    And the disposition rule C1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And select the disposition '<Disposition>'
    And the disposition rule is updated to C2 for '<Disposition>'
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And navigate to home screen
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Verified                    | <Verified>                  |
      | Verification Failure Reason | <VerificationFailureReason> |
      | Additional Information      |                             |
      | Verification Timestamp      |                             |
      | Responder GLN               | <ResponderGln>              |
      | Correlation UUID            |                             |
      | HTTP return code            | <HTTPReturnCode>            |
    And navigate to home screen
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page
    And select the disposition '<Disposition>'
    And click on Delete Icon
    And the button labeled OK is clicked
    Examples:
      | user              | GS1Element                                                | Disposition     | rule | Verified | ResponderGln  | HTTPReturnCode | VerificationFailureReason | delete message  |
      | USSC_Manufacturer | (01)00363391180998(21)auto8965754(10)AutoBatch4(17)211230 | autodisprefresh | C1   | false    | 0363391100002 | 200            | Manufacturer_policy       | Rule(s) deleted |

  Scenario Outline:  USSC_2102_19,USSC_2102_20,USSC_2102_21,USSC_2102_22,USSC_2102_23: MAH_User Verification with special characters
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And click on verify button
    Then verify details on Verification response screen
      | Responder GLN    | <ResponderGln>   |
      | Correlation UUID |                  |
      | HTTP return code | <HTTPReturnCode> |
    Examples:
      | user              | GS1Element                                              | Verified | ResponderGln  | HTTPReturnCode |
      | USSC_Manufacturer | (01)00363391180998(21)test.123(10)AutoLot"001(17)231231 | true     | 0363391100002 | 200            |
      | USSC_Manufacturer | (01)00363391180998(21)test/123(10)AutoLot/001(17)231231 | true     | 0363391100002 | 200            |
      | USSC_Manufacturer | (01)00363391180998(21)test-123(10)AutoLot-001(17)231231 | true     | 0363391100002 | 200            |
      | USSC_Manufacturer | (01)00363391180998(21)test"123(10)AutoLot.001(17)231231 | true     | 0363391100002 | 200            |
      | USSC_Manufacturer | (01)00363391180998(21)test_123(10)AutoLot_001(17)231231 | true     | 0363391100002 | 200            |

  Scenario Outline: USSC_2005_5, PHN_10802_TC3 : Verification: User Type WHO and GTIN doesn't exists in Mediledger
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then the toast message '<ErrorMessage>' appears
    And the button labeled Close is clicked
    Then title of verify product pack screen is '<title>'
    Then GSl Element contains the decoded value
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | SerialNumber   | <SerialNumber>   |
      | ExpirationDate | <ExpirationDate> |
    Examples:
      | user            | title          | GTIN           | BatchNumber | ExpirationDate | SerialNumber | ErrorMessage                                                                       |
      | USSC_Wholesaler | Adjust Request | 00090031234567 | TestQA2023  | 231230         | 7578533417   | The GTIN was not found in the Lookup Directory. A verification cannot be executed. |


  Scenario Outline: USSC_2005_2, PHN_10802_TC1 : User Type MAH and not the owner of GTIN
    Given the user logs in as '<user>' in the login page
    And the VerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    And click on verify button
    Then the toast message '<ErrorMessage>' appears
    And the button labeled Close is clicked
    Then title of verify product pack screen is '<title>'
    Then GSl Element contains the decoded value
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | SerialNumber   | <SerialNumber>   |
      | ExpirationDate | <ExpirationDate> |
    Examples:
      | user              | title          | GTIN           | BatchNumber     | ExpirationDate | SerialNumber | ErrorMessage                                                                                                                                                                        |
      | USSC_Manufacturer | Adjust Request | 00361958090117 | TestvinayQA2023 | 231230         | 7578533417   | Requester is not the owner of the GTIN (00361958090117). As a MAH you can only verify product identifiers for GTINs that you own, and which are maintained in the lookup directory. |
