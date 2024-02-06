@USSC @configurealert
Feature: Configure Alert Resolution App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the ConfigureAlertResolutions app is clicked in the USSC launcher page

  Scenario Outline: USSC_2102_01,USSC_2102_02- Verify User is able to add new Issue and delete an Issue which is not assigned to any Alert
    And get the issues item list count from the List
    And click on add issues button
    Then the add issue pop up title is '<Add Issue Title>'
    Then the add input ghost text is '<Add Input ghost Text>'
    And enter input value '<issue>'
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then the issue list contains '<issue>'
    Then the issues item List is incremented
    And get the issues item list count from the List
    And select an item '<issue>' from the list
    And the delete issue button is clicked
    Then the delete pop up message is '<Delete Issue Title>'
    And the button labeled OK is clicked
    Then the toast message '<DeleteSuccessMessage>' appears
    And the issues item List is decremented
    Examples:
      | Add Issue Title | Add Input ghost Text | issue     | successMessage  | DeleteSuccessMessage | Delete Issue Title                     |
      | Add Issue       | Enter an issue       | AutoIssue | New issue added | Issue deleted        | Do you want to delete selected issue? |


  Scenario Outline: USSC_2102_04- Verify max length for Issue text box is 30 characters
    And click on add issues button
    And enter input value '<issue>'
    Then verify the maximum allowed length for the issue field
    Examples:
      | issue                               |
      | sdjfdfdgjdfjkbgdjknjsofnosdnofsdnkl |


  Scenario Outline: USSC_2102_05, USSC_2102_06-  Verify Sort pop-up for "Issues" and Sort Issues based on Description in Ascending
    And click on sort issue button
    Then the sort pop up is displayed
    And the Description option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then the issue Description column is sorted in Ascending order
    Examples:
      | successMessage   |
      | Items are sorted |


  Scenario Outline: USSC_2102_06 -Sort Issues based on Description in Descending order and None order
    And get issue description from the list
    And click on sort issue button
    And the Description option is selected to sort in Descending Order
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then the issue Description column is sorted in Descending order
    And click on sort issue button
    And the None option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then issue description column is sorted by server sequence order
    Examples:
      | successMessage   |
      | Items are sorted |

  Scenario: USSC_2102_07 - Verify Issues list doesn’t get sorted if user clicks on Cancel button
    And get issue description from the list
    And click on sort issue button
    And the Description option is selected to sort in Descending Order
    And the button labeled Cancel is clicked
    Then issue description column is sorted by server sequence order

  Scenario Outline: USSC_2102_08, USSC_2102_09- Verify on Clicking Cancel button of  Add Issue pop-up , the issue doesn’t get added and Verify error messages for Issues
    And get the issues item list count from the List
    And click on add issues button
    And enter input value '<issue>'
    And the button labeled Cancel is clicked
    Then the issues item List is not incremented
    And the delete issue button is clicked
    Then the toast message '<DeleteErrorMessage>' appears
    Examples:
      | issue     | DeleteErrorMessage    |
      | AutoIssue | Please select an Item |

  Scenario Outline: USSC_2102_10, USSC_2102_11- Verify user is not allowed to create duplicate issue and error message for add issue
    And verify an item '<issue>' already exists in the list
    And click on add issues button
    And the button labeled OK is clicked
    Then the error message '<EmptyInputErrorMessage>' is displayed for add issue input
    And enter input value '<issue>'
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    And click on add issues button
    And enter input value '<issue>'
    And the button labeled OK is clicked
    Then the toast message '<DuplicateErrorMessage>' appears
    And select an item '<issue>' from the list
    And the delete issue button is clicked
    And the button labeled OK is clicked
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | issue       | DuplicateErrorMessage | successMessage  | DeleteSuccessMessage | EmptyInputErrorMessage |
      | AutoIssue 1 | Duplicate Entry.       | New issue added | Issue deleted        | Entry cannot be blank  |

  Scenario Outline: USSC_2102_12- Verify user can add Issue with only Alphanumeric values & space
    And click on add issues button
    And enter input value '<issue>'
    And the button labeled OK is clicked
    Then the error message '<InputValidationErrorMessage>' is displayed for add issue input
    Examples:
      | issue                         | InputValidationErrorMessage          |
      | aD342 &#(%&^$Q#%^$%^$*&Q@)&)@ | Only alphanumeric characters allowed |

  Scenario Outline: USSC_2102_13,USSC_2102_14- Verify User is able to add new resolution and delete resolution which is not assigned to any Alert
    And click on resolution menu list
    Then the resolutions page title is '<Page Title>'
    Then the resolution page title is '<Resolution Page Title>'
    And get the resolutions item list count from the List
    And click on add resolutions button
    Then the add resolution pop up title is '<Add Resolution Title>'
    Then the add input ghost text is '<Add Input ghost Text>'
    And enter input value '<Resolution>'
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then the resolution list contains '<Resolution>'
    Then the resolutions item List is incremented
    And get the resolutions item list count from the List
    And select an item '<Resolution>' from the list
    And the delete resolution button is clicked
    Then the delete pop up message is '<Delete Resolution Title>'
    And the button labeled OK is clicked
    Then the toast message '<DeleteSuccessMessage>' appears
    And the resolutions item List is decremented
    Examples:
      | Page Title | Resolution Page Title | Add Resolution Title | Add Input ghost Text | Resolution     | successMessage       | DeleteSuccessMessage | Delete Resolution Title                     |
      | Overview   | Resolutions           | Add Resolution       | Enter a resolution   | AutoResolution | New resolution added | Resolution deleted   | Do you want to delete selected resolution? |

  Scenario Outline: USSC_2102_16- Verify max length for Resolution text box is 30 characters
    And click on resolution menu list
    And click on add resolutions button
    And enter input value '<Resolution>'
    Then verify the maximum allowed length for the issue field
    Examples:
      | Resolution                          |
      | sdjfdfdgjdfjkbgdjknjsofnosdnofsdnkl |

  Scenario Outline: USSC_2102_17, USSC_2102_18-  Verify Sort pop-up for "Resolution" and Sort Resolution based on Description in Ascending
    And click on resolution menu list
    And click on sort resolution button
    Then the sort pop up is displayed
    And the Description option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then the resolution Description column is sorted in Ascending order
    Examples:
      | successMessage   |
      | Items are sorted |

  Scenario Outline: USSC_2102_18 -Sort Resolution based on Description in Descending order and None order
    And click on resolution menu list
    And get resolution description from the list
    And click on sort resolution button
    And the Description option is selected to sort in Descending Order
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then the resolution Description column is sorted in Descending order
    And click on sort resolution button
    And the None option is selected to sort in Ascending Order
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    Then resolution description column is sorted by server sequence order
    Examples:
      | successMessage   |
      | Items are sorted |

  Scenario: USSC_2102_19 - Verify Resolutions list doesnot get sorted if user clicks on Cancel button
    And click on resolution menu list
    And get resolution description from the list
    And click on sort resolution button
    And the Description option is selected to sort in Descending Order
    And the button labeled Cancel is clicked
    Then resolution description column is sorted by server sequence order

  Scenario Outline: USSC_2102_20,USSC_2102_21- Verify on Clicking Cancel button of  Add Resolution pop-up , the Resolution doesn’t get added.
    And click on resolution menu list
    And get the resolutions item list count from the List
    And click on add resolutions button
    And enter input value '<resolution>'
    And the button labeled Cancel is clicked
    Then the resolutions item List is not incremented
    And the delete resolution button is clicked
    Then the toast message '<DeleteErrorMessage>' appears
    Examples:
      | resolution     | DeleteErrorMessage    |
      | AutoResolution | Please select an Item |

  Scenario Outline: USSC_2102_22, USSC_2102_23- Verify user is not allowed to create duplicate resolution and error message for add resolution
    And click on resolution menu list
    And verify an item '<resolution>' already exists in the list
    And click on add resolutions button
    And the button labeled OK is clicked
    Then the error message '<EmptyInputErrorMessage>' is displayed for add issue input
    And enter input value '<resolution>'
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    And click on add resolutions button
    And enter input value '<resolution>'
    And the button labeled OK is clicked
    Then the toast message '<DuplicateErrorMessage>' appears
    And select an item '<resolution>' from the list
    And the delete resolution button is clicked
    And the button labeled OK is clicked
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | resolution      | DuplicateErrorMessage | successMessage       | DeleteSuccessMessage | EmptyInputErrorMessage |
      | AutoResolution2 | Duplicate Entry.       | New resolution added | Resolution deleted   | Entry cannot be blank  |

  Scenario Outline: USSC_2102_24- Verify user can add resolution with only Alphanumeric values & space
    And click on resolution menu list
    And click on add resolutions button
    And enter input value '<resolution>'
    And the button labeled OK is clicked
    Then the error message '<InputValidationErrorMessage>' is displayed for add issue input
    Examples:
      | resolution                    | InputValidationErrorMessage          |
      | aD342 &#(%&^$Q#%^$%^$*&Q@)&)@ | Only alphanumeric characters allowed |


  Scenario Outline: USSC_2102_25- Verify User is allowed to delete and add same Issues/Resolution
    And click on add issues button
    And enter issue value
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    And select issue from the list
    And the delete issue button is clicked
    And the button labeled OK is clicked
    Then the toast message '<DeleteSuccessMessage>' appears
    And click on add issues button
    And enter issue value
    And the button labeled OK is clicked
    Then the toast message '<successMessage>' appears
    And select issue from the list
    And the delete issue button is clicked
    And the button labeled OK is clicked
    Then the toast message '<DeleteSuccessMessage>' appears
    Examples:
      | successMessage  | DeleteSuccessMessage |
      | New issue added | Issue deleted        |

