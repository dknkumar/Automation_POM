@USSC @uploadLD
Feature: Verify Upload product data to look up directory  App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the UploadProductPackData app is clicked in the USSC launcher page

  Scenario Outline: USSC_27, USSC_27.2, USSC_27.3, USSC_27.7, USSC_27.8 ,USSC_27.11 - Upload Product data App -> New Upload -> New Upload -> Review file Contents -> Upload to Look up directory
    Then the title of the page is '<Title>'
    Then the title of the History table is '<HistoryHeader>'
    Then the title of the upload product data table is '<tableHeader>'
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date '<startDate>' is entered
    And the comments '<comments>' is entered
    And the end date '<endDate>' is entered
    And the button labeled Upload is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | endDate        | <endDate>   |
      | comments       | <comments>  |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus1> |
    And Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | endDate        | <endDate>   |
      | comments       | <comments>  |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus2> |
    And the button labeled Close is clicked
    Then first record in history contains LD Upload details
      | fileName  | <filename>  |
      | comment   | <comments>  |
      | gtinCount | <gtinCount> |
      | status    | <status>    |
    And click on first record in history table
    Then the detail file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | endDate        | <endDate>   |
      | comments       | <comments>  |
    Examples:
      | Title               | tableHeader                     | HistoryHeader | GTIN           | Description              | startDate    | endDate      | comments           | filename                      | gtinCount | status | uploadStatus1 | uploadStatus2 |
      | Upload Product Data | Lookup Directory Uploaded Files | History       | 00363391200030 | Special ¼ of ½ char © in | Apr 22, 2021 | Apr 24, 2021 | Auto_text_comment_ | gtin_LD_Upload_Automation.csv | 0/2       | Fail   | Pending       | Failed        |

  @test3
  Scenario Outline: USSC_28.1, USSC_28_4 , USSC_27.10, USSC_27.5, USSC_27_4, USSC_2005_37- Upload Product data App -> New Upload -> Cancel
    And the button labeled New Upload is clicked
    Then the note section contains text
    And the button labeled Cancel is clicked
    Then the title of the upload product data table is '<tableHeader>'
    And the button labeled New Upload is clicked
    And the button labeled Upload is clicked
    Then the error message contains '<ErrorMessage>'
    And the file, '<filename>', is attached in the upload product data page
    And the start date is selected from calender
    And the comments '<comments>' is entered
    And the end date is selected from the calender
    And the button labeled Upload is clicked
    Then detail page title '<detailPageTitle>' is displayed
    And the cancel button is clicked
    Then the title of the upload product data table is '<tableHeader>'
    And the button labeled New Upload is clicked
    And the file, '<filename1>', is attached in the upload product data page
    And the start date '<startDate>' is entered
    And the button labeled Upload is clicked
    Then the error message '<duplicateGTINError>' is Displayed
    Examples:
      | tableHeader                     | startDate    | ErrorMessage                         | detailPageTitle      | comments           | filename                      | filename1          | duplicateGTINError           |
      | Lookup Directory Uploaded Files | Apr 22, 2021 | Please fill all the mandatory values | Review File Contents | Auto_text_comment_ | gtin_LD_Upload_Automation.csv | Duplicate_GTIN.csv | Duplicate GTINs in CSV File. |

  Scenario Outline: USSC_28.5 - Upload Product data App-> New Upload-> Upload
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And th]e start date '<startDate>' is entered
    And the comments '<comments>' is entered
    And the button labeled Upload is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | comments       | <comments>  |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus1> |
    And the button labeled Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | comments       | <comments>  |
      | status         |             |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus2> |
    And the button labeled Close is clicked
    Then first record in history contains LD Upload details
      | fileName  | <filename>  |
      | comment   | <comments>  |
      | gtinCount | <gtinCount> |
      | status    | <status>    |
    Examples:
      | GTIN           | Description              | startDate    | comments           | filename                      | gtinCount | status | uploadStatus1 | uploadStatus2 |
      | 00363391200030 | Special ¼ of ½ char © in | Apr 22, 2021 | Auto_text_comment_ | gtin_LD_Upload_Automation.csv | 0/2       | Fail   | Pending       | Failed        |

  Scenario Outline: USSC_2005_29,USSC_2005_43 - Upload invalid GTINs
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date '<startDate>' is entered
    And the comments '<comments>' is entered
    And the button labeled Upload is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | comments       | <comments>  |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus1> |
    And the button labeled Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | comments       | <comments>  |
      | status         |             |
    Then the first record of review file items contains
      | GTIN           | <GTIN>           |
      | Description    | <Description>    |
      | uploadStatus   | <uploadStatus2>  |
      | responseCode   | <responseCode>   |
      | responseDetail | <responseDetail> |
    And the button labeled Close is clicked
    Then first record in history contains LD Upload details
      | fileName  | <filename>  |
      | comment   | <comments>  |
      | gtinCount | <gtinCount> |
      | status    | <status>    |
    Examples:
      | GTIN            | Description              | startDate    | comments           | filename                   | gtinCount | status | uploadStatus1 | uploadStatus2 | responseCode | responseDetail                                     |
      | 003633912000302 | Special ¼ of ½ char © in | Apr 22, 2021 | Auto_text_comment_ | Invalid_GTIN_LD_Upload.csv | 0/2       | Fail   | Pending       | Failed        | 400          | GTIN length must be 14 and check digit must match. |


  Scenario Outline: USSC_2005_30 - Upload duplicate GTINs
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date Today - 20 Years is entered
    And the comments '<comments>' is entered
    And the button labeled Upload is clicked
    Then the review file details contains
      | fileNameHeader | <filename> |
      | comments       | <comments> |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus1> |
    And the button labeled Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename> |
      | comments       | <comments> |
      | status         |            |
    Then the first record of review file items contains
      | GTIN           | <GTIN>           |
      | Description    | <Description>    |
      | uploadStatus   | <uploadStatus2>  |
      | responseCode   | <responseCode>   |
      | responseDetail | <responseDetail> |
    And the button labeled Close is clicked
    Then first record in history contains LD Upload details
      | fileName  | <filename>  |
      | comment   | <comments>  |
      | gtinCount | <gtinCount> |
      | status    | <status>    |
    Examples:
      | GTIN           | Description              | comments           | filename                      | gtinCount | status | uploadStatus1 | uploadStatus2 | responseCode | responseDetail                          |
      | 00363391200030 | Special ¼ of ½ char © in | Auto_text_comment_ | gtin_LD_Upload_Automation.csv | 0/2       | Fail   | Pending       | Failed        | 409          | The GTIN provided already exists in LD. |

  Scenario Outline: USSC_2005_31 - Upload invalid GTINs with prefix mismatch
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date Today - 20 Years is entered
    And the comments '<comments>' is entered
    And the button labeled Upload is clicked
    Then the review file details contains
      | fileNameHeader | <filename> |
      | comments       | <comments> |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus1> |
    And the button labeled Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename> |
      | comments       | <comments> |
      | status         |            |
    Then the first record of review file items contains
      | GTIN           | <GTIN>           |
      | Description    | <Description>    |
      | uploadStatus   | <uploadStatus2>  |
      | responseCode   | <responseCode>   |
      | responseDetail | <responseDetail> |
    Then the second record of review file items contains
      | GTIN           | <GTIN1>           |
      | Description    | <Description1>    |
      | uploadStatus   | <uploadStatus2>   |
      | responseCode   | <responseCode1>   |
      | responseDetail | <responseDetail1> |
    And the button labeled Close is clicked
    Then first record in history contains LD Upload details
      | fileName  | <filename>  |
      | comment   | <comments>  |
      | gtinCount | <gtinCount> |
      | status    | <status>    |
    Examples:
      | GTIN           | GTIN1          | Description              | Description1           | comments           | filename                                   | gtinCount | status | uploadStatus1 | uploadStatus2 | responseCode | responseCode1 | responseDetail                                                          | responseDetail1                         |
      | 00373391100803 | 00363391200030 | Special ¼ of ½ char © in | Special ¾ of ½ char µ® | Auto_text_comment_ | Invalid_Gtin_prefix_mismatch_LD_Upload.csv | 0/2       | Fail   | Pending       | Failed        | 400          | 409           | The GTIN provided does not match the company prefix of the organization | The GTIN provided already exists in LD. |

  Scenario Outline: USSC_2005_32 -  Mass Upload app should support characters from the UTF-8 Latin-1 Supplement.
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date Today - 20 Years is entered
    And the button labeled Upload is clicked
    Then the first record of review file items contains
      | GTIN        | <GTIN>        |
      | Description | <Description> |
    Then the second record of review file items contains
      | GTIN        | <GTIN1>        |
      | Description | <Description1> |
    Then the third record of review file items contains
      | GTIN        | <GTIN2>        |
      | Description | <Description2> |
    Then the fourth record of review file items contains
      | GTIN        | <GTIN3>        |
      | Description | <Description3> |
    Then the fifth record of review file items contains
      | GTIN        | <GTIN4>        |
      | Description | <Description4> |
    Then the sixth record of review file items contains
      | GTIN        | <GTIN5>        |
      | Description | <Description5> |
    And the button labeled Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename> |
    Then the first record of review file items contains
      | GTIN        | <GTIN>        |
      | Description | <Description> |
    Then the second record of review file items contains
      | GTIN        | <GTIN1>        |
      | Description | <Description1> |
    Then the third record of review file items contains
      | GTIN        | <GTIN2>        |
      | Description | <Description2> |
    Then the fourth record of review file items contains
      | GTIN        | <GTIN3>        |
      | Description | <Description3> |
    Then the fifth record of review file items contains
      | GTIN        | <GTIN4>        |
      | Description | <Description4> |
    Then the sixth record of review file items contains
      | GTIN        | <GTIN5>        |
      | Description | <Description5> |
    And the button labeled Close is clicked
    Examples:
      | filename                              | GTIN           | GTIN1          | GTIN2          | GTIN3          | GTIN4          | GTIN5          | Description                                   | Description1                                   | Description2                                   | Description3                                   | Description4                                   | Description5                                   |
      | Gtin_Latin_Suppliment_Description.csv | 00363391100132 | 00363391100125 | 00363391100118 | 00363391100293 | 00363391100286 | 00363391100279 | --¡--¢--£--¤--¥--¦--§--¨--©--ª--«--¬--­--®--¯ | °--±--²--³--´--µ--¶--·--¸--¹--º--»--¼--½--¾--¿ | À--Á--Â--Ã--Ä--Å--Æ--Ç--È--É--Ê--Ë--Ì--Í--Î--Ï | Ð--Ñ--Ò--Ó--Ô--Õ--Ö--×--Ø--Ù--Ú--Û--Ü--Ý--Þ--ß | à--á--â--ã--ä--å--æ--ç--è--é--ê--ë--ì--í--î--ï | ð--ñ--ò--ó--ô--õ--ö--÷--ø--ù--ú--û--ü--ý--þ--ÿ |

  Scenario Outline: USSC_2005_33 -  Mass Upload app should support characters from the UTF-8 Latin-1 Supplement.
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date '<startDate>' is entered
    And the button labeled Upload is clicked
    Then the first record of review file items contains
      | GTIN        | <GTIN>        |
      | Description | <Description> |
    Then the second record of review file items contains
      | GTIN        | <GTIN1>        |
      | Description | <Description1> |
    Then the third record of review file items contains
      | GTIN        | <GTIN2>        |
      | Description | <Description2> |
    Then the fourth record of review file items contains
      | GTIN        | <GTIN3>        |
      | Description | <Description3> |
    Then the fifth record of review file items contains
      | GTIN        | <GTIN4>        |
      | Description | <Description4> |
    Then the sixth record of review file items contains
      | GTIN        | <GTIN5>        |
      | Description | <Description5> |
    And the button labeled Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
    Then the first record of review file items contains
      | GTIN        | <GTIN>        |
      | Description | <Description> |
    Then the second record of review file items contains
      | GTIN        | <GTIN1>        |
      | Description | <Description1> |
    Then the third record of review file items contains
      | GTIN        | <GTIN2>        |
      | Description | <Description2> |
    Then the fourth record of review file items contains
      | GTIN        | <GTIN3>        |
      | Description | <Description3> |
    Then the fifth record of review file items contains
      | GTIN        | <GTIN4>        |
      | Description | <Description4> |
    Then the sixth record of review file items contains
      | GTIN        | <GTIN5>        |
      | Description | <Description5> |
    And the button labeled Close is clicked
    Examples:
      | filename                                | startDate    | GTIN           | GTIN1          | GTIN2          | GTIN3          | GTIN4          | GTIN5          | Description              | Description1           | Description2     | Description3   | Description4       | Description5    |
      | Gtin_Latin_Suppliment_2_Description.csv | Apr 22, 2021 | 14298300000018 | 14298300000019 | 14298300000020 | 14298300000021 | 14298300000022 | 14298300000023 | Special ¼ of ½ char © in | Special ¾ of ½ char µ® | Special char ¹ ° | Special char ± | Special char X² 5º | Special char X³ |

  Scenario Outline: USSC_2005_34- Upload Product data App-> New Upload-> Upload(future end date)
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date '<startDate>' is entered
    And the comments '<comments>' is entered
    And the end date '<endDate>' is entered
    And the button labeled Upload is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | comments       | <comments>  |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus1> |
    And the button labeled Upload to Lookup Directory is clicked
    Then the review file details contains
      | fileNameHeader | <filename>  |
      | startDate      | <startDate> |
      | comments       | <comments>  |
      | status         |             |
    Then the first record of review file items contains
      | GTIN         | <GTIN>          |
      | Description  | <Description>   |
      | uploadStatus | <uploadStatus2> |
    And the button labeled Close is clicked
    Then first record in history contains LD Upload details
      | fileName  | <filename>  |
      | comment   | <comments>  |
      | gtinCount | <gtinCount> |
      | status    | <status>    |
    Examples:
      | GTIN           | Description              | startDate    | endDate      | comments           | filename                      | gtinCount | status | uploadStatus1 | uploadStatus2 |
      | 00363391200030 | Special ¼ of ½ char © in | Dec 31, 2021 | Dec 12, 2100 | Auto_text_comment_ | gtin_LD_Upload_Automation.csv | 0/2       | Fail   | Pending       | Failed        |

  Scenario Outline: USSC_2005_38 - Upload Product Data - New Upload Page - field checks and Validations
    And the button labeled New Upload is clicked
    And the file, '<filename>', is attached in the upload product data page
    And the start date Today - 20 Years is entered
    And the button labeled Upload is clicked
    Then the error message '<duplicateGTINError>' is Displayed
    Examples:
      | startDate    | filename                     | duplicateGTINError           |
      | Dec 22, 2002 | Duplicate_And_Valid_GTIN.csv | Duplicate GTINs in CSV File. |
