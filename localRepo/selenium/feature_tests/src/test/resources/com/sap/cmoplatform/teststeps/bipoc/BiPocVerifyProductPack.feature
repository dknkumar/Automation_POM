@BiPoc
Feature: Bi-POC Verify Product Package App Test Cases


  Scenario Outline: USSC_BiPoc_1,USSC_BiPoc_3: Check "Verification of Product Pack"(Bi Poc Verification Service)
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    And title of verify product pack screen is '<title>'
    And header of request types are displayed
    Examples:
      | user              | title                    |
      | USSC_Manufacturer | New Verification Request |

  Scenario Outline: USSC_BiPoc_2: Check "Verification of Product Pack"(other than Boehringer Ingelheim)
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is not visible in the USSC launcher page
    Examples:
      | user     |
      | USSC_MAH |

  Scenario Outline: USSC_BiPoc_4: Check Request Type,GTIN,Batch Number, Expiration Date, Serial Number, GLN Number,
  GS1 Element fields are available.
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    Then verify available fields visibility in Decoded data section
    Examples:
      | user              |
      | USSC_Manufacturer |

  Scenario Outline: USSC_BiPoc_5: "New Verification Request" -> "Decoded Data" -> Check Fields are input enabled
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    Then verify decoded data input fields
    Examples:
      | user              |
      | USSC_Manufacturer |

  Scenario Outline: USSC_BiPoc_6: "New Verification Request" ->  "GS1 Element String " -> Check Fields are input disabled except for GS1 element
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    Then verify GSl element String input fields
    Examples:
      | user              |
      | USSC_Manufacturer |

  Scenario Outline: USSC_BiPoc_7: "New Verification Request" -> "Decoded Data" -> Check Field: GS1 Element
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    Then GSl Element contains the decoded value
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber |
      | USSC_Manufacturer | 00363391180998 | AutoTest    | 231230         | 7578533417   |

  Scenario Outline: USSC_BiPoc_8: "New Verification Request" -> "Decoded Data" -> Check Action: Verify
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    And the GTIN value '<GTIN>' is entered
    And the Batch Number value '<BatchNumber>' is entered
    And the Expiration Date value '<ExpirationDate>' is entered
    And the Serial Number value '<SerialNumber>' is entered
    Then GLN Number is auto populated with '<RequesterGLN>'
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
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber | GS1Element                                             | RequesterGLN  |
      | USSC_Manufacturer | 00363391180998 | AutoTest    | 231230         | 7578533417   | (01)00363391180998(21)7578533417(10)AutoTest(17)231230 | 0363391100002 |

  Scenario Outline: USSC_BiPoc_9,USSC_BiPoc_11 : "New Verification Request" -> "GS1 Element String" -> Check decoded data
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And check correct values are populated on Verification screen
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
      | GS1Element     | <GS1Element>     |
    Then GLN Number is auto populated with '<RequesterGLN>'
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber | GS1Element                                             | RequesterGLN  |
      | USSC_Manufacturer | 00363391180998 | AutoTest    | 231230         | 7578533417   | (01)00363391180998(21)7578533417(10)AutoTest(17)231230 | 0363391100002 |

  Scenario Outline: USSC_BiPoc_10 : "New Verification Request" -> "Decoded Data" -> "Response" Screen -> Visual Check
    Given the user logs in as '<user>' in the login page
    And the BiPocVerifyProductPack app is clicked in the USSC launcher page
    And click on GslElementString tab
    And the gsl element '<GS1Element>' is entered
    And check correct values are populated on Verification screen
      | GTIN           | <GTIN>           |
      | BatchNumber    | <BatchNumber>    |
      | ExpirationDate | <ExpirationDate> |
      | SerialNumber   | <SerialNumber>   |
      | GS1Element     | <GS1Element>     |
    Then GLN Number is auto populated with '<RequesterGLN>'
    Examples:
      | user              | GTIN           | BatchNumber | ExpirationDate | SerialNumber | GS1Element                                             | RequesterGLN  |
      | USSC_Manufacturer | 00363391180998 | AutoTest    | 231230         | 7578533417   | (01)00363391180998(21)7578533417(10)AutoTest(17)231230 | 0363391100002 |

