@USSC @cvr
Feature: Configure Verification Responses App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the ConfigureVerificationResponses app is clicked in the USSC launcher page

    Scenario Outline: USSC_2008_01,USSC_2008_19- To validate ghost text and all the GS1 scenarios available for Response configuration
    And click on Disposition rules tab
    Then the panel header is '<Gs1 Scenario Header>'
    And click on view gsl scenarios button
    Then verify the disposition content for gsl scenarios
    And verify the navigation menu in configure verification rules page
    And click on Add Rule button
    Then ghost text '<GhostText>' is displayed for disposition name
    And click on expiry rules tab
    And click on view gsl scenarios button
    Then verify gsl scenarios for expired PI response configuration
    Examples:
      | Gs1 Scenario Header                                | GhostText     |
      | GS1 Scenarios for PI Status Response Configuration | MaxLength 100 |

   Scenario Outline: USSC_2008_03 - Configure Verification Responses App -> Disposition Name field check
    And click on Disposition rules tab
    And click on Add Rule button
     And the first Disposition name <DispName1> is entered
     And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    Then the toast message '<InvalidDispNameErrorMessage>' appears
    And the button labeled Cancel is clicked
     And click on Add Rule button
     And the first Disposition name <DispName2> is entered
     And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    Then the toast message '<InvalidDispNameErrorMessage>' appears
    And the button labeled Cancel is clicked
    And click on Add Rule button
     And the disposition rule B1 is selected for first record
     And the first Disposition name <DispName3> is entered
     And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    Then the toast message '<InvalidDispNameErrorMessage>' appears
    And the button labeled Cancel is clicked
     And click on Add Rule button
     And the Disposition name with more than 100 char is entered
     Then verify the maximum allowed length for disposition name field
     Examples:
       | DispName1        | DispName2                    | DispName3 | InvalidDispNameErrorMessage                                                                                      |
       | Auto_Test1237676 | Auto!@#$%^^&*&*@#*(#*)$(#)$( | Auto-test | Only lowercase standard characters, numbers and underscores are permitted. Special characters are not permitted. |


  Scenario Outline: USSC_2008_04 , USSC_2008_10-  ADD and Delete Disposition associated with B1 Rule
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                    | <Create action>               |
      | StatusVal                 | <StatusVal>                   |
      | AdditionalInfo            | <Additional Info>             |
      | VerificationFailureReason | <Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | save message                               | delete message  | Create action | StatusVal | Additional Info | Verification Failure Reason |
      | Response configuration successfully saved. | Rule(s) deleted | CREATE        | true      | Recalled        | -                           |


  Scenario Outline: USSC_2008_05 -  ADD and Delete Disposition associated with B2 Rule
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule B2 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                    | <Create action>               |
      | StatusVal                 | <StatusVal>                   |
      | AdditionalInfo            | <Additional Info>             |
      | VerificationFailureReason | <Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | save message                               | delete message  | Create action | StatusVal | Additional Info | Verification Failure Reason |
      | Response configuration successfully saved. | Rule(s) deleted | CREATE        | false     | Recalled        | Manufacturer_policy         |

  #Updated test case from C1 to C1.1
  Scenario Outline: USSC_2008_06.1 -  ADD and Delete Disposition associated with C1.1 Rule
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule C1.1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                    | <Create action>               |
      | StatusVal                 | <StatusVal>                   |
      | AdditionalInfo            | <Additional Info>             |
      | VerificationFailureReason | <Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | save message                               | delete message  | Create action | StatusVal | Additional Info | Verification Failure Reason |
      | Response configuration successfully saved. | Rule(s) deleted | CREATE        | false     | Suspect         | Not_for_re-distribution     |

    #Added new test case for C1.2
  Scenario Outline: USSC_2008_06.2 -  ADD and Delete Disposition associated with C1.2 Rule
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule C1.2 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                    | <Create action>               |
      | StatusVal                 | <StatusVal>                   |
      | AdditionalInfo            | <Additional Info>             |
      | VerificationFailureReason | <Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | save message                               | delete message  | Create action | StatusVal | Additional Info | Verification Failure Reason |
      | Response configuration successfully saved. | Rule(s) deleted | CREATE        | false     | Illegitimate                | Not_for_re-distribution     |

  Scenario Outline: USSC_2008_07,USSC_2008_15 -  ADD and Delete Disposition associated with C2 Rule
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule C2 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                    | <Create action>               |
      | StatusVal                 | <StatusVal>                   |
      | AdditionalInfo            | <Additional Info>             |
      | VerificationFailureReason | <Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | save message                               | delete message  | Create action | StatusVal | Additional Info | Verification Failure Reason |
      | Response configuration successfully saved. | Rule(s) deleted | CREATE        | false     | -               | Manufacturer_policy         |


  Scenario Outline: USSC_2008_08 -UPDATE Disposition assosiated with B1 Rule to B2 Rule.
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And select the disposition created
    And the disposition rule is updated to B2
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                       | <Update action>                   |
      | StatusVal                    | <StatusVal>                       |
      | AdditionalInfo               | <Additional Info>                 |
      | VerificationFailureReason    | <Verification Failure Reason>     |
      | NewStatusVal                 | <NewStatusVal>                    |
      | NewAdditionalInfo            | <NewAdditional Info>              |
      | NewVerificationFailureReason | <New Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | StatusVal | Additional Info | NewStatusVal | NewAdditional Info | Update action | Verification Failure Reason | New Verification Failure Reason | save message                               | delete message  |
      | true      | Recalled        | false        | Recalled           | UPDATE        | -                           | Manufacturer_policy             | Response configuration successfully saved. | Rule(s) deleted |

  Scenario Outline: USSC_2008_09 - UPDATE Disposition associated with C1.1 Rule to C2 Rule.
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule C1.1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And select the disposition created
    And the disposition rule is updated to C2
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                       | <Update action>                   |
      | StatusVal                    | <StatusVal>                       |
      | AdditionalInfo               | <Additional Info>                 |
      | VerificationFailureReason    | <Verification Failure Reason>     |
      | NewStatusVal                 | <NewStatusVal>                    |
      | NewAdditionalInfo            | <NewAdditional Info>              |
      | NewVerificationFailureReason | <New Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | StatusVal | Additional Info | NewStatusVal | NewAdditional Info | Update action | Verification Failure Reason | New Verification Failure Reason | save message                               | delete message  |
      | false     | Suspect         | false        | -                  | UPDATE        | Not_for_re-distribution     | Manufacturer_policy             | Response configuration successfully saved. | Rule(s) deleted |


  Scenario Outline: USSC_2008_11,USSC_2008_12,USSC_2008_20 - Create,Update and Delete rules for multiple dispositions at once.
    And click on Disposition rules tab
    And click on Add Rule button
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule B1 is selected for first record
    And the second Disposition name is entered
    And the disposition rule B1 is selected for second record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And select the first Disposition entered
    And the first disposition rule is updated to C1.1
    And select the second Disposition entered
    And the second disposition rule is updated to C1.2
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And select the first disposition created
    And select the second disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | DispName         | DispName1        | save message                               | delete message  |
      | multi_create_one | multi_create_two | Response configuration successfully saved. | Rule(s) deleted |


  Scenario Outline: USSC_2008_14 ,USSC_2008_16, USSC_2008_17-  SORTING disposition table entries based on  Ascending, Descending and  sequence of the server.
    And click on Disposition rules tab
    Then the disposition rule records are disabled
    And click on Add Rule button
    And the button labeled Cancel is clicked
    Then the disposition rule records are disabled
    And get disposition rule name records
    And click on sort rules button
    Then the toast message '<AscOrderSortMessage>' appears
    Then the disposition rule records are disabled
    Then disposition column is sorted by ascending order
    And click on sort rules button
    Then the toast message '<DescOrderSortMessage>' appears
    Then disposition column is sorted by descending order
    And click on sort rules button
    Then the toast message '<NoOrderSortMessage>' appears
    Then disposition column is sorted by server sequence order
    Examples:
      | AscOrderSortMessage                    | DescOrderSortMessage                    | NoOrderSortMessage       |
      | Rules sorted by disposition, ascending | Rules sorted by disposition, descending | Rules sorted by no order |


  Scenario Outline: USSC_2008_21 - Dispositions should be unique
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And click on Add Rule button
    Then the toast message contains 'Response configuration successfully saved.'
    And the first Disposition name is entered
    And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    Then the toast message '<DuplicateDispositionError>' appears
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | DuplicateDispositionError     | delete message  |
      | Dispositions should be unique | Rule(s) deleted |


  Scenario Outline: USSC_2008_22- View all the GS1 scenarios available for Expired PI Response config
    And click on expiry rules tab
    Then the panel header is '<Expired PI Gs1 Scenario Header>'
    And click on view gsl scenarios button
    Then verify gsl scenarios for expired PI response configuration
    Then verify button label '<expirdePIText>' '<defaultRule>' '<B1Rule>' '<B2Rule>' and history links
    Examples:
      | Expired PI Gs1 Scenario Header                      | expirdePIText | defaultRule | B1Rule        | B2Rule |
      | GS1 Scenarios for Expired PI Response Configuration | Expired PI    | Default     | B1 Expiration | B1 Expiration |


  Scenario Outline: USSC_2008_24 -  Default rule can be reselected from B1 exp/B2 exp rule without restrictions
    And click on expiry rules tab
    And select '<Expiry Rule>' rule for expired PI
    And click on Disposition rules tab
    And click on expiry rules tab
    Then verify expiry rule '<Expiry Rule>' is set for the user
    #Again Set back to Default
    And select '<Expiry Rule Default>' rule for expired PI
    Examples:
      | Expiry Rule   | Expiry Rule Default |
      | B1 Expiration | Default             |

  Scenario Outline: USSC_2008_25 -  To successfully configure the response for expired PI to B1 exp rule
    And click on expiry rules tab
    And select '<Expiry Rule Default>' rule for expired PI
    And select '<Expiry Rule>' rule for expired PI
    And click on Disposition rules tab
    And click on expiry rules tab
    Then verify expiry rule '<Expiry Rule>' is set for the user
    And click on Change Log button
    Then expiry rule logs in history contains
      | action                       | <Update action>                   |
      | StatusVal                    | <StatusVal>                       |
      | AdditionalInfo               | <Additional Info>                 |
      | VerificationFailureReason    | <Verification Failure Reason>     |
      | NewStatusVal                 | <NewStatusVal>                    |
      | NewAdditionalInfo            | <NewAdditional Info>              |
      | NewVerificationFailureReason | <New Verification Failure Reason> |
    And the button labeled Cancel is clicked
      #Again Set back to Default
    And select '<Expiry Rule Default>' rule for expired PI
    Examples:
      | Expiry Rule   | Update action | StatusVal | NewStatusVal | Additional Info | NewAdditional Info | Verification Failure Reason | New Verification Failure Reason | Expiry Rule Default |
      | B1 Expiration | UPDATE        | true      | true         | -               | Expired            | -                           | -                               | Default              |

  Scenario Outline: USSC_2008_26 -  To successfully configure the response for expired PI to B2 exp rule
    And click on expiry rules tab
    And select '<Expiry Rule>' rule for expired PI
    And click on Disposition rules tab
    And click on expiry rules tab
    Then verify expiry rule '<Expiry Rule>' is set for the user
    And click on Change Log button
    Then expiry rule logs in history contains
      | action                       | <Update action>                   |
      | StatusVal                    | <StatusVal>                       |
      | AdditionalInfo               | <Additional Info>                 |
      | VerificationFailureReason    | <Verification Failure Reason>     |
      | NewStatusVal                 | <NewStatusVal>                    |
      | NewAdditionalInfo            | <NewAdditional Info>              |
      | NewVerificationFailureReason | <New Verification Failure Reason> |
    And the button labeled Cancel is clicked
      #Again Set back to Default
    And select '<Expiry Rule Default>' rule for expired PI
    Examples:
      | Expiry Rule   | Update action | StatusVal | NewStatusVal | Additional Info | NewAdditional Info | Verification Failure Reason | New Verification Failure Reason | Expiry Rule Default |
      | B2 Expiration | UPDATE        | true      | false        | Expired         | Expired            | -                           | Manufacturer_policy             | Default             |

  @Test_MH_02
  Scenario Outline: USSC_2008_27 - User should not be allowed to ADD disposition rule without assigning a specific GS1 scenario to it
    And click on Disposition rules tab
    And click on Add Rule button
    And the button labeled Save is clicked
    And the toast message contains '<error message>'
    And the first Disposition name is entered
    And the button labeled Save is clicked
    And the toast message contains '<error message>'
    Examples:
      | DispName  | error message                  |
      | auto_test | GS1 Scenarios can not be empty |

  Scenario Outline: USSC_2008_18 - Verify old entries being refreshed on creating a deleted entry.
    And click on Disposition rules tab
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                    | <Create action>               |
      | dispositionName           | <DispName>                    |
      | StatusVal                 | <StatusVal>                   |
      | AdditionalInfo            | <Additional Info>             |
      | VerificationFailureReason | <Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And the disposition rule is updated to B2
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                       | <Update action>                   |
      | dispositionName              | <DispName>                        |
      | StatusVal                    | <StatusVal>                       |
      | AdditionalInfo               | <Additional Info>                 |
      | VerificationFailureReason    | <Verification Failure Reason>     |
      | NewStatusVal                 | <NewStatusVal>                    |
      | NewAdditionalInfo            | <NewAdditional Info>              |
      | NewVerificationFailureReason | <New Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    And click on Change Log button
    Then first record in Change Log contains
      | action                       | <Delete action>                   |
      | dispositionName              | <DispName>                        |
      | NewStatusVal                 | <NewStatusVal>                    |
      | NewAdditionalInfo            | <NewAdditional Info>              |
      | NewVerificationFailureReason | <New Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And click on Add Rule button
    And the first Disposition name is entered
    And the disposition rule B1 is selected for first record
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    And the toast message contains '<save message>'
    And click on Change Log button
    Then first record in Change Log contains
      | action                    | <Create action>               |
      | dispositionName           | <DispName>                    |
      | StatusVal                 | <StatusVal>                   |
      | AdditionalInfo            | <Additional Info>             |
      | VerificationFailureReason | <Verification Failure Reason> |
    And the button labeled Cancel is clicked
    And select the disposition created
    And click on Delete Icon
    And the button labeled OK is clicked
    And the toast message '<delete message>' appears
    Examples:
      | DispName         | save message                               | Create action | Update action | Delete action | StatusVal | NewStatusVal | Additional Info | NewAdditional Info | Verification Failure Reason | New Verification Failure Reason | delete message  |
      | autonewtestqaone | Response configuration successfully saved. | CREATE        | UPDATE        | DELETE        | true      | false        | Recalled        | Recalled           | -                           | Manufacturer_policy             | Rule(s) deleted |

  Scenario Outline: USSC_2011_01, USSC_2011_02, USSC_2011_03, USSC_2011_04- To validate default values on clicking Add button, Company name Auto population for the GLN
    And click on blocked requester gln tab
    Then the panel header is '<Blocked Requester GLNs Header>'
    And click on Add GLN BlockList button
    Then validate the default fields in blocked requester gln table
    And the GLN '<GLN>' value is entered
    Then the auto populated value of company Name is '<Unknown Company Name>'
    And the GLN '<GLN1>' value is entered
    Then the auto populated value of company Name is '<Unknown Company Name>'
    Examples:
      | Blocked Requester GLNs Header | GLN           | Company Name            | GLN1          | Unknown Company Name |
      | Blocked Requester GLNs        | 0363391100002 | SAP Manufacturer Future | 0363398100050 | Unknown             |

  Scenario: USSC_2011_05- Validate user can sort entries based on GLN, Active Switch Status in ascending and descending order
    And click on blocked requester gln tab
    And the sort gln block list button is clicked
    Then sort by pop up is displayed
    And the GLN option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then the GLN column is sorted in Ascending order
    And the sort gln block list button is clicked
    And the GLN option is selected to sort in Descending Order
    And the button labeled OK is clicked
    Then the GLN column is sorted in Descending order
    And the sort gln block list button is clicked
    And the Active/Inactive option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then the Active/Inactive column is sorted in Ascending order
    And the sort gln block list button is clicked
    And the Active/Inactive option is selected to sort in Descending Order
    And the button labeled OK is clicked
    Then the Active/Inactive column is sorted in Descending order

  Scenario: USSC_2011_05,USSC_2011_06- Validate user can sort entries based on Active Switch Status in ascending and descending order and Valid user can clear sorting
    And click on blocked requester gln tab
    And get GLN and Active switch column values
    And the sort gln block list button is clicked
    And the Active/Inactive option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then the Active/Inactive column is sorted in Ascending order
    And the sort gln block list button is clicked
    And the Active/Inactive option is selected to sort in Descending Order
    And the button labeled OK is clicked
    Then the Active/Inactive column is sorted in Descending order
    And the sort gln block list button is clicked
    And the None option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then GLN and Active column is sorted by server sequence order

  Scenario Outline:USSC_2011_07, USSC_2011_10- Validate Blocked GLN title displays count of GLN's correctly and validate Errors for GLN field
    And click on blocked requester gln tab
    And click on Add GLN BlockList button
    Then validate the GLN count detail
    And the GLN '<GLN>' value is entered
    And the Reason '<Reason>' value is entered
    Then verify the maximum allowed length for the field
      | GLN | <GLN> |
    And the button labeled Save is clicked
    Then the toast message '<CheckDigitErrorMessage>' appears
    And the GLN '<GLN1>' value is entered
    And the button labeled Save is clicked
    Then the toast message '<ValidGlnError>' appears
    And the GLN value is Cleared
    And the button labeled Save is clicked
    Then the toast message '<ValidGlnError>' appears
    Examples:
      | GLN            | Reason       | CheckDigitErrorMessage | ValidGlnError     | GLN1 |
      | 12345678910112 | Auto_Comment | Incorrect check digit  | Enter a valid GLN | 123  |

  Scenario Outline:USSC_2011_11 - Errors for Reason/Comment box
    And click on blocked requester gln tab
    And click on Add GLN BlockList button
    Then validate the GLN count detail
    And the GLN '<GLN>' value is entered
    And the button labeled Save is clicked
    Then the toast message '<ValidReasonComment>' appears
    And the Reason '<Reason>' value is entered
    Then verify the maximum allowed length for the field
      | Reason | <Reason> |
    Examples:
      | GLN           | Reason                                                                                                                                                                                                       | ValidReasonComment           | ValidGlnError     | GLN1 |
      | 0363391999805 | sdfksdfgklsfghsfgklfsgsdfjsdklfjsdkhfsdfbmcnvmxcnvjkhsfduighfkhgjsdfhgklfljsdfjsdkljfljsdghfjkhgjkdfhgsdfksdfgklsfghsfgklfsgsdfjsdklfjsdkhfsdfbmcnvmxcnvjkhsfduighfkhgjsdfhgklfljsdfjsdkljfljsdghfjkhgjkdfhg | Enter a valid reason/comment | Enter a valid GLN | 123  |

  Scenario Outline:USSC_2011_12 - Errors for "Provide a reason" pop-up
    And click on blocked requester gln tab
    And select the first blocked GLN from the table
    And click on Active switch
    Then the provide reason pop up is displayed
    And the button labeled Add is clicked
    Then the error message is displayed
    And the button labeled Cancel is clicked
    And click on Active switch
    And the reason '<Reason>' is entered in pop up window
    And the button labeled Add is clicked
    Then verify the maximum allowed length for the field
      | Reason | <Reason> |
    And click on Active switch
    And the reason '<Reason_New>' is entered in pop up window
    And the button labeled Add is clicked
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    Examples:
      | Reason                                                                                                                                                                                                       | Reason_New    | SaveSuccessMessage                             |
      | sdfksdfgklsfghsfgklfsgsdfjsdklfjsdkhfsdfbmcnvmxcnvjkhsfduighfkhgjsdfhgklfljsdfjsdkljfljsdghfjkhgjkdfhgsdfksdfgklsfghsfgklfsgsdfjsdklfjsdkhfsdfbmcnvmxcnvjkhsfduighfkhgjsdfhgklfljsdfjsdkljfljsdghfjkhgjkdfhg | Auto_Test@123 | Blocklist GLN configuration succesfully saved. |

  Scenario Outline:USSC_2011_13, USSC_2102_02- Duplicate GLN validation
    And click on blocked requester gln tab
    And click on Add GLN BlockList button
    And the GLN '<GLN>' value is entered
    And the Reason '<Reason>' value is entered
    And the button labeled Save is clicked
    Then the toast message '<DuplicateErrorMessage>' appears
    And the GLN '<GLN1>' value is entered
    Then warning message is not displayed
    Examples:
      | GLN           | GLN1          | Reason       | DuplicateErrorMessage |
      | 0363391100002 | 0363391527717 | Auto_Comment | Duplicate GLN         |

  Scenario Outline:USSC_2011_15- Validate History Change - UPDATE event
    And click on blocked requester gln tab
    And select the gln '<GLN>' record
    And get reason and active switch value from the selected '<GLN>' record
    And click on Active switch for selected the gln '<GLN>'
    And the reason '<Reason>' is entered in pop up window
    And the button labeled Add is clicked
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    And click on Change Log button
    Then first blocked gln logs in history contains
      | action | <Update action> |
      | GLN    | <GLN>           |
      | Reason | <Reason>        |
    And the button labeled Cancel is clicked
    Examples:
      | GLN           | Reason       | Update action | SaveSuccessMessage                             |
      | 7654321000107 | Auto_Comment | UPDATE        | Blocklist GLN configuration succesfully saved. |

  Scenario Outline:USSC_2011_17- Validate History Change - UPDATE event (Multiple GLN update)
    And click on blocked requester gln tab
    And select the gln '<GLN1>' record
    And select the gln '<GLN2>' record
    And get reason and active switch value from the selected '<GLN2>' record
    And get reason and active switch value from the selected '<GLN1>' record
    And click on Active switch for selected the gln '<GLN1>'
    And the reason '<Reason1>' is entered in pop up window
    And the button labeled Add is clicked
    And click on Active switch for selected the gln '<GLN2>'
    And the reason '<Reason2>' is entered in pop up window
    And the button labeled Add is clicked
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    And click on Change Log button
    Then first blocked gln logs in history contains
      | action | <Update action> |
      | GLN    | <GLN2>          |
      | Reason | <Reason2>       |
    Then second blocked gln logs in history contains
      | action | <Update action> |
      | GLN    | <GLN1>          |
      | Reason | <Reason1>       |
    And the button labeled Cancel is clicked
    Examples:
      | GLN1          | GLN2          | Reason1        | Reason2        | Update action | SaveSuccessMessage                             |
      | 7654321000053 | 7654321000107 | Auto_Comment_1 | Auto_Comment_2 | UPDATE        | Blocklist GLN configuration succesfully saved. |

  Scenario Outline:USSC_2011_18, USSC_2011_19- GLN Search -History Change screen , Verify Ghost text message for GLN Search
    And click on blocked requester gln tab
    And click on Change Log button
    Then the title of blocked list history pop up is '<HistoryTitle>'
    Then the ghost text of GLN search field is '<Ghost Text>'
    And the search by GLN '<GLN>' value
    Then the logs contains entries of GLN '<GLN>'
    And the search by GLN '<GLN2>' value
    Then the logs contains entries of GLN '<GLN2>'

    Examples:
      | HistoryTitle   | GLN           | GLN2  | Ghost Text    |
      | Change History | 0363391999811 | 03633 | Search by GLN |

  Scenario Outline:USSC_2011_20,USSC_2011_21- Validate updates are reflected on home screen when reason/comment and active switch value is updated for single entry
    And click on blocked requester gln tab
    And select the gln '<GLN>' record
    And the Reason '<Reason>' value is entered for selected gln '<GLN>'
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    Then the selected GLN '<GLN>' contains
      | Reason | <Reason> |
    And select the gln '<GLN>' record
    And get reason and active switch value from the selected '<GLN>' record
    And click on Active switch for selected the gln '<GLN>'
    And the reason '<Reason>' is entered in pop up window
    And the button labeled Add is clicked
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    Then the selected GLN '<GLN>' contains
      | ActiveSwitch |  |
    Examples:
      | GLN           | Reason           | SaveSuccessMessage                             |
      | 7654321000053 | Auto_TestComment | Blocklist GLN configuration succesfully saved. |

  Scenario Outline:USSC_2011_22- Validate updates are reflected on home screen when reason/comment value is updated for multiple entries
    And click on blocked requester gln tab
    And select the gln '<GLN1>' record
    And select the gln '<GLN2>' record
    And the Reason '<Reason1>' value is entered for selected gln '<GLN1>'
    And the Reason '<Reason2>' value is entered for selected gln '<GLN2>'
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    Then the selected GLN '<GLN1>' contains
      | Reason | <Reason1> |
    Then the selected GLN '<GLN2>' contains
      | Reason | <Reason2> |
    Examples:
      | GLN1          | GLN2          | Reason1              | Reason2              | SaveSuccessMessage                             |
      | 7654321000107 | 7654321000053 | Auto_TestComment_One | Auto_TestComment_Two | Blocklist GLN configuration succesfully saved. |

  Scenario Outline:USSC_2011_23- Validate updates are reflected on home screen when active switch and  reason/comment value is updated for multiple entries
    And click on blocked requester gln tab
    And select the gln '<GLN1>' record
    And select the gln '<GLN2>' record
    And get reason and active switch value from the selected '<GLN1>' record
    And get reason and active switch value from the selected '<GLN2>' record
    And click on Active switch for selected the gln '<GLN1>'
    And the reason '<Reason1>' is entered in pop up window
    And the button labeled Add is clicked
    And click on Active switch for selected the gln '<GLN2>'
    And the reason '<Reason2>' is entered in pop up window
    And the button labeled Add is clicked
    And the button labeled Save is clicked
    And the button labeled OK is clicked
    Then the toast message '<SaveSuccessMessage>' appears
    Then the selected GLN '<GLN1>' contains
      | ActiveSwitch |  |
    Examples:
      | GLN1          | GLN2          | Reason1              | Reason2              | SaveSuccessMessage                             |
      | 7654321000107 | 7654321000053 | Auto_TestComment_One | Auto_TestComment_Two | Blocklist GLN configuration succesfully saved. |

  Scenario Outline: USSC_2011_25 - Validate PDF has all the entries with same values as displayed on UI
    And click on blocked requester gln tab
    And the download pdf button is clicked
    Then verify file '<Blocked Gln File Name>' is downloaded
    Examples:
      | Blocked Gln File Name      |
      | Blocked Requester GLNs.pdf |

  Scenario Outline: USSC_2011_26 - Validate PDF has all the entries with same values as displayed on UI
    And click on Disposition rules tab
    And the download pdf button is clicked
    Then verify file '<Disposition Rules File Name>' is downloaded
    Examples:
      | Disposition Rules File Name |
      | Disposition Rules.pdf       |

  Scenario Outline:USSC_2011_24- Validate changes doesn’t get saved if user cancels the updates
    And click on blocked requester gln tab
    And select the gln '<GLN>' record
    And get reason and active switch value from the selected '<GLN>' record
    And the Reason '<New_Reason>' value is entered for selected gln '<GLN>'
    And the button labeled Cancel is clicked
    And the button labeled Cancel is clicked
    Then the selected GLN '<GLN>' contains old values
    Examples:
      | GLN           | New_Reason       |
      | 7654321000107 | Auto_TestComment |