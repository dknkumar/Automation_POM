@USSC @alertConfiguration
Feature: Verify alert configuration App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the AlertConfiguration app is clicked in the USSC launcher page

  Scenario Outline: USSC_2008_1, USSC_2008_2- BASIC UI Test cases
    Then the title of the master page is '<MasterPageTitle>'
    Then the title of the detail page is '<DetailPageTitle>'
    Then verify available tabs on the alert configuration page
    And the Add button is clicked
    Then the title of create recipient pop up title is '<CreateRecipientTitle>'
    Examples:
      | MasterPageTitle   | DetailPageTitle | CreateRecipientTitle |
      | Alert Rule Groups | Review          | Create New Recipient |

  Scenario Outline: USSC_2008_3,USSC_2008_4, USSC_2008_9, USSC_2008_10- Create, search and delete Alert Rule
    And the Add Rule button is clicked
    Then the title of create rule pop up title is '<CreateRuleTitle>'
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    Then the Status field is disabled
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    Then the first rule contains '<name>'
    And search the alert rule '<name>'
    Then the first rule contains '<name>'
    And select the alert rule '<name>'
    And the button labeled Delete is clicked
    Then the warning pop up contains '<header>' and '<DeletePopUpMessage>'
    And the button labeled No is clicked
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | CreateRuleTitle       | name           | description | CreateSuccessMessage      | DeletePopUpMessage              | DeleteSuccessMessage      | header            |
      | Create New Alert Rule | Auto_test_rule | test        | Rule Created Successfully | Are you sure to delete the Rule | Rule Deleted Successfully | Delete Alert Rule |

  Scenario Outline: USSC_2008_5- Cancel the Alert Rule while adding to the Alert rule group list
    And the Add Rule button is clicked
    Then the title of create rule pop up title is '<CreateRuleTitle>'
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Cancel is clicked
    Then the title of the detail page is '<DetailPageTitle>'
    Then the first rule does not contains '<name>'
    Examples:
      | CreateRuleTitle       | name             | description | DetailPageTitle |
      | Create New Alert Rule | Auto_Cancel_test | Auto_desc   | Review          |

  Scenario Outline: USSC_2008_6,USSC_2008_7,USSC_2008_8-Create a new Rule with active status and edit alert rule
    And the Add Rule button is clicked
    Then the title of create rule pop up title is '<CreateRuleTitle>'
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    Then the first rule contains '<name>'
    And select the alert rule '<name>'
    Then the default rule status is '<defaultStatus>'
    And the button labeled Edit is clicked
    And click on Status switch '<flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the alert rule status is '<status>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | CreateRuleTitle       | name      | description | CreateSuccessMessage      | defaultStatus | status | DeleteSuccessMessage      | UpdateSuccessMessage      | flag |
      | Create New Alert Rule | Auto_test | test_desc   | Rule Created Successfully | inactive      | active | Rule Deleted Successfully | Rule Updated Successfully | Off  |

  Scenario Outline: USSC_2008_13, USSC_2008_14, USSC_2008_19, USSC_2008_20-  ADD and DELETE recipients for Notification.
    And the Add Rule button is clicked
    And the rule name is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    And select the alert rule
    Then the rule name in review page
    And the Add button is clicked
    Then the title of create recipient pop up title is '<CreateRecipientTitle>'
    And the button labeled Cancel is clicked
    And the Add button is clicked
    And the recipient name '<Recipient Name>' is entered
    And the recipient email '<Recipient Email>' is entered
    And the button labeled Save is clicked
    Then the toast message '<CreateRecipientSuccessMessage>' appears
    Then recipient table contains
      | <Recipient Name>  |
      | <Recipient Email> |
    Then the recipient count is '<Count>'
    And the Add button is clicked
    And the recipient name '<Recipient Name>' is entered
    And the recipient email '<Recipient Email>' is entered
    And the button labeled Save is clicked
    Then the error message '<DuplicateRecipientMessage>' appears
    And the button labeled Close is clicked
    And the button labeled Cancel is clicked
    And select the recipient from table
    And the Delete button is clicked
    And the warning pop up contains '<header>' and '<DeletePopUpMessage>'
    And the button labeled No is clicked
    And the Delete button is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteRecipientSuccessMessage>' appears
    Then the recipient count is '<NewCount>'
    And select the alert rule
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | CreateRecipientTitle | Rule Name           | description | Recipient Name | Recipient Email | header           | DeletePopUpMessage                   | DeleteSuccessMessage      | Count | CreateRecipientSuccessMessage  | DuplicateRecipientMessage              | DeleteRecipientSuccessMessage  | NewCount |
      | Create New Recipient | Auto_test_Recipient | test        | testName       | test@email.com  | Delete Recipient | Are you sure to delete the Recipient | Rule Deleted Successfully | 1     | Recipient Created Successfully | Email Id already exists for this rule. | Recipient Deleted Successfully | 0        |

  Scenario Outline:USSC_2008_11, USSC_2008_21- Check Notifications and Condition tab is clickable
    And click on conditions menu
    Then title of Condition table header is '<Condition header>'
    Then the table columns header contains
      | Field    |
      | Operator |
      | Value    |
    And click on notification menu
    Then title of Recipient table header is '<Recipient header>'
    Then the table columns header contains
      | Name  |
      | Email |
    Examples:
      | Recipient header | Condition header |
      | Recipients       | Conditions       |


  Scenario Outline: USSC_2008_25,USSC_2008_26- Check operator and value dropdown list for the Verified status field.
    And the Add Rule button is clicked
    And the rule name '<Rule Name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    And select the alert rule '<Rule Name>'
    And click on conditions menu
    Then conditions table contains
      | <Field>    |
      | <Operator> |
      | <Value>    |
    And the VERIFIED STATUS condition is selected from table
    And select the value '<NewValue>' from Values dropdown list
    And the button labeled Save is clicked
    Then the error message '<ErrorMessage>' appears
    And the button labeled Close is clicked
    And the button labeled Cancel is clicked
    And select the alert rule '<Rule Name>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | Rule Name | description | DeleteSuccessMessage      | Field           | Operator     | Value | ErrorMessage                                | NewValue |
      | test      | test        | Rule Deleted Successfully | VERIFIED STATUS | is equals to | false | Additional Info Field must be added to List | true     |

  Scenario Outline: USSC_2008_27,USSC_2008_28,USSC_2008_29,USSC_2008_30,USSC_2008_31,USSC_2008_32,USSC_2008_33- Check operator and value dropdown list for the Additional Info, Verification failure reason, Gtin, Linktype, Requester gln , Responder Gln and Context  field.
    And the Add Rule button is clicked
    And the rule name '<Rule Name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    And select the alert rule '<Rule Name>'
    And the Add button is clicked
    And the recipient name '<Recipient Name>' is entered
    And the recipient email '<Recipient Email>' is entered
    And the button labeled Save is clicked
    And click on conditions menu
    And the Add button is clicked
    Then verify Operator dropdown list for ADDITIONAL INFORMATION field
      | is equals to     |
      | is not equals to |
    Then verify Values dropdown list for ADDITIONAL INFORMATION field
      | Recalled           |
      | Expired            |
      | ExpirationExtended |
      | Suspect            |
      | Illegitimate       |
    Then verify Operator dropdown list for VERIFICATION FAILURE REASON field
      | is equals to     |
      | is not equals to |
    Then verify Values dropdown list for VERIFICATION FAILURE REASON field
      | Manufacturer_policy     |
      | Not_for_re-distribution |
      | No_reason_provided      |
    Then verify Operator dropdown list for LINKTYPE field
      | is equals to     |
      | is not equals to |
    Then verify Values dropdown list for LINKTYPE field
      | verificationService |
    Then verify Operator dropdown list for GTIN field
      | starts with |
    Then verify Operator dropdown list for REQUESTER GLN field
      | is in list     |
      | is not in list |
    Then verify Operator dropdown list for RESPONDER GLN field
      | is in list     |
      | is not in list |
    Then verify Operator dropdown list for CONTEXT field
      | is in list |
    And the button labeled Cancel is clicked
    And select the alert rule '<Rule Name>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | Rule Name         | description           | Recipient Name | Recipient Email | DeleteSuccessMessage      |
      | AutoTestCondition | AutoTestConditionDesc | testName       | test@email.com  | Rule Deleted Successfully |

  Scenario Outline: USSC_2008_22,USSC_2008_23,USSC_2008_24- Add Conditions to the Rule and delete the selected rule
    And the Add Rule button is clicked
    And the rule name is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    And select the alert rule
    And click on conditions menu
    Then the conditions table contains '<Count>' conditions
    And the Add button is clicked
    Then validate field drop down values
    And the button labeled Cancel is clicked
    And the Add button is clicked
    And select the field ADDITIONAL INFORMATION
    And the button labeled Save is clicked
    Then the toast message '<ConditionSaveSuccessMessage>' appears
    Then the conditions table contains '<NewCount>' conditions
    And the ADDITIONAL INFORMATION condition is selected from table
    And the Delete button is clicked
    And the button labeled OK is clicked
    Then the toast message '<DeleteConditionSuccessMessage>' appears
    And select the alert rule
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | description              | DeleteSuccessMessage      | ConditionSaveSuccessMessage | Count | DeleteConditionSuccessMessage     | NewCount |
      | Auto_test_condition_desc | Rule Deleted Successfully | Conditions are saved        | 1     | Condition(s) Deleted Successfully | 2        |

  Scenario Outline: USSC_2102_01 - Check operator and value dropdown list for the HTTP RESPONSE CODE field
    And the Add Rule button is clicked
    And the rule name is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    And select the alert rule
    And click on conditions menu
    And the Add button is clicked
    Then verify Operator dropdown list for HTTP RESPONSE CODE field
      | is in list |
    Then verify Values dropdown list for HTTP RESPONSE CODE field
      | 403 |
      | 500 |
      | 502 |
      | 504 |
    And the button labeled Cancel is clicked
    And the button labeled Cancel is clicked
    And select the alert rule
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | Rule Name          | description           | DeleteSuccessMessage      |
      | AutoTestCondition1 | AutoTestConditionDesc | Rule Deleted Successfully |

  Scenario Outline: USSC_2102_02 - Validate MAH user is not allowed to delete condition with 'HTTP RESPONSE CODE' when rule doesn't has "Verified Status Field".
    And the Add Rule button is clicked
    And the rule name is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    And select the alert rule
    And click on conditions menu
    And the Add button is clicked
    And select the field HTTP RESPONSE CODE
    And select the value '<HTTPResponseCode>' from Values dropdown list
    And the button labeled Save is clicked
    Then the toast message '<ConditionSaveSuccessMessage>' appears
    And the VERIFIED STATUS condition is selected from table
    And the Delete button is clicked
    And the button labeled OK is clicked
    Then the toast message '<DeleteConditionSuccessMessage>' appears
    And the HTTP RESPONSE CODE condition is selected from table
    And the Delete button is clicked
    Then the error message '<ErrorMessage>' appears
    And the button labeled Close is clicked
    And select the alert rule
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | description           | ConditionSaveSuccessMessage | DeleteConditionSuccessMessage     | DeleteSuccessMessage      | ErrorMessage                                                   | HTTPResponseCode |
      | AutoTestConditionDesc | Conditions are saved        | Condition(s) Deleted Successfully | Rule Deleted Successfully | Either VERIFIED STATUS or HTTP RESPONSE CODE should be present | 504              |

  Scenario Outline: USSC_2102_03 -  Validate user is not allowed to add multiple 'HTTP RESPONSE CODE' field in same alert rule.
    And the Add Rule button is clicked
    And the rule name is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    And select the alert rule
    And click on conditions menu
    And the Add button is clicked
    And select the field HTTP RESPONSE CODE
    And select the value '<HTTPResponseCode>' from Values dropdown list
    And the button labeled Save is clicked
    Then the toast message '<ConditionSaveSuccessMessage>' appears
    And the Add button is clicked
    And select the field HTTP RESPONSE CODE
    Then the error message '<ErrorMessage>' appears
    And the button labeled Close is clicked
    And the button labeled Cancel is clicked
    And select the alert rule
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | Rule Name          | description           | ConditionSaveSuccessMessage | DeleteSuccessMessage      | ErrorMessage                          | HTTPResponseCode |
      | AutoTestCondition3 | AutoTestConditionDesc | Conditions are saved        | Rule Deleted Successfully | The Operator is already there in list | 504              |

  Scenario Outline: USSC_2102_29 - Validate user cannot add duplicate alert rules
    And the Add Rule button is clicked
    Then the title of create rule pop up title is '<CreateRuleTitle>'
    And the rule name is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    Then the first rule contains
    And the Add Rule button is clicked
    And the rule name is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    Then validate duplicate error Toast message
    And the button labeled Cancel is clicked
    And select the alert rule
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | CreateRuleTitle       | description | CreateSuccessMessage      | DeleteSuccessMessage      | DuplicateRuleErrorMessage1 | DuplicateRuleErrorMessage2 | DuplicateRuleErrorMessage3 |
      | Create New Alert Rule | test        | Rule Created Successfully | Rule Deleted Successfully | Rule with name:            | Auto_New_test_rule         | already exists             |

  Scenario Outline: USSC_2102_30,USSC_2102_31,USSC_2102_32- Alert in App flag is present on rule creation and validate the flag state    And the Add Rule button is clicked
    And the Add Rule button is clicked
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    Then validate create new alert rule pop up fields
    Then the Alert in App field is disabled
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    And select the alert rule '<name>'
    Then the flag status of alert rule is '<status>' and '<alertInApp>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | name                | description | CreateSuccessMessage      | alertInApp | status   | DeleteSuccessMessage      |
      | AutoTest_flag_state | test        | Rule Created Successfully | No         | inactive | Rule Deleted Successfully |

  Scenario Outline: USSC_2102_33, USSC_2102_37- Modify Alert rule status from inactive to active and 'Alert in App'flag status from No to Yes and delete rule
    And the Add Rule button is clicked
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    And select the alert rule '<name>'
    And the button labeled Edit is clicked
    And click on Status switch '<Off_flag>' button
    And click on Alert switch '<Off_flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the flag status of alert rule is '<actve_status>' and '<alertInApp_yes>'
    And select the alert rule '<name>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | name                         | description | CreateSuccessMessage      | Off_flag | On_flag | alertInApp_yes | alertInApp_no | actve_status | inactve_status | DeleteSuccessMessage      | UpdateSuccessMessage      |
      | AutoTest_flag_status_update1 | test        | Rule Created Successfully | Off      | On      | Yes            | No            | active       | inactive       | Rule Deleted Successfully | Rule Updated Successfully |

  Scenario Outline:  USSC_2102_34,USSC_2102_38- Modify Alert rule status from inactive to active and 'Alert in App' flag status from Yes to No and Delete rule
    And the Add Rule button is clicked
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    And select the alert rule '<name>'
    And the button labeled Edit is clicked
    And click on Alert switch '<Off_flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the flag status of alert rule is '<inactve_status>' and '<alertInApp_yes>'
    And select the alert rule '<name>'
    And the button labeled Edit is clicked
    And click on Status switch '<Off_flag>' button
    And click on Alert switch '<On_flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the flag status of alert rule is '<actve_status>' and '<alertInApp_no>'
    And select the alert rule '<name>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | name                         | description | CreateSuccessMessage      | Off_flag | On_flag | alertInApp_yes | alertInApp_no | actve_status | inactve_status | DeleteSuccessMessage      | UpdateSuccessMessage      |
      | AutoTest_flag_status_update2 | test        | Rule Created Successfully | Off      | On      | Yes            | No            | active       | inactive       | Rule Deleted Successfully | Rule Updated Successfully |

  Scenario Outline: USSC_2102_35,USSC_2102_39- Modify Alert rule status from active to inactive and 'Alert in App'flag status from No to Yes and delete rule
    And the Add Rule button is clicked
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    And select the alert rule '<name>'
    And the button labeled Edit is clicked
    And click on Status switch '<Off_flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the flag status of alert rule is '<actve_status>' and '<alertInApp_no>'
    And select the alert rule '<name>'
    And the button labeled Edit is clicked
    And click on Status switch '<On_flag>' button
    And click on Alert switch '<Off_flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the flag status of alert rule is '<inactve_status>' and '<alertInApp_yes>'
    And select the alert rule '<name>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | name                         | description | CreateSuccessMessage      | Off_flag | On_flag | alertInApp_yes | alertInApp_no | actve_status | inactve_status | DeleteSuccessMessage      | UpdateSuccessMessage      |
      | AutoTest_flag_status_update3 | test        | Rule Created Successfully | Off      | On      | Yes            | No            | active       | inactive       | Rule Deleted Successfully | Rule Updated Successfully |

  Scenario Outline: USSC_2102_36,USSC_2102_40- Modify Alert rule status from active to inactive and 'Alert in App'flag status from Yes to No and delete rule
    And the Add Rule button is clicked
    And the rule name '<name>' is entered
    And the rule description '<description>' is entered
    And the button labeled Save is clicked
    Then the toast message '<CreateSuccessMessage>' appears
    And select the alert rule '<name>'
    And the button labeled Edit is clicked
    And click on Status switch '<Off_flag>' button
    And click on Alert switch '<Off_flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the flag status of alert rule is '<actve_status>' and '<alertInApp_yes>'
    And select the alert rule '<name>'
    And the button labeled Edit is clicked
    And click on Status switch '<On_flag>' button
    And click on Alert switch '<On_flag>' button
    And the button labeled Save is clicked
    Then the toast message '<UpdateSuccessMessage>' appears
    Then the flag status of alert rule is '<inactve_status>' and '<alertInApp_no>'
    And select the alert rule '<name>'
    And the button labeled Delete is clicked
    And the button labeled Yes is clicked in delete pop up
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | name                         | description | CreateSuccessMessage      | Off_flag | On_flag | alertInApp_yes | alertInApp_no | actve_status | inactve_status | DeleteSuccessMessage      | UpdateSuccessMessage      |
      | AutoTest_flag_status_update4 | test        | Rule Created Successfully | Off      | On      | Yes            | No            | active       | inactive       | Rule Deleted Successfully | Rule Updated Successfully |
