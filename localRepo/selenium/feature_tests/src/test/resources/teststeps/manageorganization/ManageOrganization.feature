Feature: Manage Organization App

  Background:
    Given the user logs in as 'MO_User' in the login page
    And the ManageOrganizations app is clicked in the USSC launcher page

  Scenario: BR_2202_01 Validate that BP/Customer Admin who is having access to Manage Organization app is able to open the app.
    Then the ManageOrganizations page should be visible

  Scenario: BR_2202_03 Validate All the selected filters from 'Adapt filter' should show on UI.
    When click on button 'Adapt Filters'
    And Select check box in adapt filter
      | Status            |
      | Type              |
      | Organization PNID |
      | Role              |
      | Onboarding Type   |
      | Interface Type    |
    And Unselect checkbox in adapt filter
      | Changed On |
    Then All filters should get selected
      | Status            |
      | Type              |
      | Organization PNID |
      | Role              |
      | Onboarding Type   |
      | Interface Type    |
    When click on button 'OK'
    Then Once OK button is pressed, it must show all the filters on the UI overview page
      | Status            |
      | Type              |
      | Organization PNID |
      | Role              |
      | Onboarding Type   |
      | Interface Type    |

  Scenario: BR_2202_04 Validate only the selected filters from 'Adapt filter' should show on UI.
    When click on button 'Adapt Filters'
    And Select check box in adapt filter
      | Status            |
      | Type              |
      | Organization PNID |
    And Unselect checkbox in adapt filter
      | Role            |
      | Onboarding Type |
      | Interface Type  |
      | Changed On      |
    Then All filters should get selected
      | Status            |
      | Type              |
      | Organization PNID |
    When click on button 'OK'
    Then Once OK button is pressed, it must show only the selected filters on the UI overview page.

  Scenario: BR_2202_05 Validate Expand and Collapse Header functionality
    When Click on Collapse arrow
    Then Header section containing the search field and filters should be hidden
    When Click on Expand arrow
    Then Header section should be visible

  Scenario Outline: BR_2202_06 Validate Status filter functionality
    And Select the column <column> '<value>' value
    When click on button 'Go'
    Then Results should be displayed according to the Filter '<value>' selected
    Examples:
      | column | value  |
      | Status | Active |

  Scenario Outline: BR_2202_07 Validate Type filter functionality
    And Select the column <column> '<value>' value
    When click on button 'Go'
    Then Results should be displayed according to the Filter '<value>' selected
    Examples:
      | column | value    |
      | Type   | Customer |

  Scenario Outline: BR_2202_08 Validate Type filter functionality
    And Select filter item column <column> '<value>' value
    Then Results should be displayed according to the '<value>' selected
    When click on button 'OK'
    And click on button 'Go'
    Then Results should be displayed according to the '<value>' selected
    Examples:
      | column           | value |
      | OrganizationPNID | AUTO  |

  Scenario Outline: BR_2202_09 Validate Type filter functionality
    And Select the column <column> '<value>' value
    When click on button 'Go'
    Then Results should be displayed according to the Filter '<value>' selected
    Examples:
      | column | value |
      | Role   | CMO   |

  Scenario Outline: BR_2202_10 Validate Onboarding Type filter functionality
    And Select the column <column> '<value>' value
    When click on button 'Go'
    Then Results should be displayed according to the Filter '<value>' selected
    Examples:
      | column         | value |
      | OnboardingType | B2B   |

  Scenario Outline: BR_2202_11  Validate Interface Type filter functionality
    When Select the Type on '<InterfaceType>' filter
    And click on button 'OK'
    And click on button 'Go'
    Then Results should be displayed according to the '<value>' selected
    Examples:
      | value | InterfaceType  |
      | SOAP  | Interface Type |

  Scenario Outline:  BR_2202_12 Verify Filters functionality with the combination of two or more filters
    When Select the column <columnStatus> '<valueStatus>' value
    And Select the column <columnOnboardingType> '<valueOnboardingType>' value
    And click on button 'Go'
    Then Results should be displayed according to the Filter '<valueStatus>' selected
    Then Results should be displayed according to the Filter '<valueOnboardingType>' selected
    Examples:
      | columnStatus | valueStatus | columnOnboardingType | valueOnboardingType |
      | Status       | Active      | OnboardingType       | B2B                 |

  Scenario Outline: BR_2202_13 Verify Reset button functionality in Adapt Filters window
    When Select the column <columnStatus> '<valueStatus>' value
    And Select the column <columnOnboardingType> '<valueOnboardingType>' value
    And click on button 'Go'
    Then Results should be displayed according to the Filter '<valueStatus>' selected
    Then Results should be displayed according to the Filter '<valueOnboardingType>' selected
    When click on button 'Adapt Filters'
    And click on button 'Reset'
    And click on button 'Reset OK'
    And click on button 'OK'
    And click on button 'Go'
    Then Filters to be displayed and Filter values should be reset to default and the same should be visible on the UI
    Examples:
      | columnStatus | valueStatus | columnOnboardingType | valueOnboardingType |
      | Status       | Active      | OnboardingType       | B2B                 |

  Scenario Outline:  BR_2202_14 Verify Clear button functionality of the Filters
    When Select the column <columnStatus> '<valueStatus>' value
    And Select the column <columnOnboardingType> '<valueOnboardingType>' value
    And click on button 'Go'
    And click on button 'Adapt Filters'
    And click on button 'Reset'
    And click on button 'Reset OK'
    And click on button 'OK'
    And click on button 'Go'
    Then Filters to be displayed and Filter values should be reset to default and the same should be visible on the UI
    Examples:
      | columnStatus | valueStatus | columnOnboardingType | valueOnboardingType |
      | Status       | Active      | OnboardingType       | B2B                 |

  Scenario:  BR_2202_15 Verify Show Details button functionality
    #TODO
  Scenario: BR_2202_16 Verify Hide Details button functionality
    #TODO

  Scenario: BR_2202_17 Validate Organizations Count on Home Screen.
    Then Verify the Organizations Count displayed on above the table

  Scenario: BR_2202_18 Verify that the Download functionality of the Organizations data in .xslx format via excel sheet
    When click on button 'Download'
    Then User should be able to download the results in .xlsx format in an excel sheet

  Scenario: BR_2202_19 Verify the Export Button functionality to download the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet

  Scenario: BR_2202_20 Verify the Export As Button functionality to download the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Select checkbox in Export As page
      | Include filter settings          |
      | Split cells with multiple values |
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet

  Scenario: BR_2202_21 Verify the Export As Button functionality to download the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Select checkbox in Export As page
      | Split cells with multiple values |
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet

  Scenario: BR_2202_22 Verify the Export As Button functionality to download the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Select checkbox in Export As page
      | Include filter settings |
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet

  Scenario: BR_2202_23 Verify the Export As Button functionality to download the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet

  Scenario Outline:  BR_2202_24 Verify the Error Message when no File Name is provided and trying to download the excel sheet using Export As Button to download the Organizations data in .xslx formatby clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Enter empty file name
    And Select checkbox in Export As page
      | Include filter settings          |
      | Split cells with multiple values |
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet with <name> file
    Examples:
      | name   |
      | export |

  Scenario Outline: BR_2202_25
    When Select the column <columnStatus> '<valueStatus>' value
    And Select the column <columnOnboardingType> '<valueOnboardingType>' value
    And click on button 'Go'
    And click on button 'Download'
    Then Downloaded data in the excel sheet must be same as showing in the Home page.
    Examples:
      | columnStatus | valueStatus | columnOnboardingType | valueOnboardingType |
      | Status       | Active      | OnboardingType       | B2B                 |

  Scenario Outline:  BR_2202_26 Verify the Export As Button functionality to download the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Input file name <filename> in Export As
    And Select checkbox in Export As page
      | Include filter settings          |
      | Split cells with multiple values |
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet
    Examples:
      | filename |
      |          |

  Scenario Outline:  BR_2202_27 Verify the Export As Button functionality to download the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Input file name <filename> in Export As
    And Select checkbox in Export As page
      | Include filter settings          |
      | Split cells with multiple values |
    And click on button 'Export'
    Then User should be able to download the results in .xlsx format in an excel sheet
    Examples:
      | filename                                                                                             |
      | 1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 |

  Scenario Outline:  BR_2202_28 Verify the Error Message when we give file name with special character while downloading the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon by Export As Button functionality
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Input file name <filename> in Export As
    And Select checkbox in Export As page
      | Include filter settings          |
      | Split cells with multiple values |
    Then File name error show
    Examples:
      | filename |
      | ******   |

  Scenario Outline: BR_2202_29 Verify the Error Message when we give file name with exceeding the character limit for File Name text box while downloading the Organizations data in .xslx format via excel sheet by clicking the down arrow button next to download icon by Export As Button functionality
    When click on button 'Download Arrow'
    And click on button 'Export As'
    And Input file name <filename> in Export As
    And Select checkbox in Export As page
      | Include filter settings          |
      | Split cells with multiple values |
    Then Warning message should be displayed
    Examples:
      | filename                                                                                              |
      | 11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 |

  Scenario Outline: BR_2202_30 Validate Adapt Filters search functionality.
    When click on button 'Adapt Filters'
    And Search <fieldName> in Adapt Filters
    Then <fieldName> should come as searched result
    Examples:
      | fieldName |
      | Role      |

  Scenario Outline: BR_2202_31 Validate Search functionality on Home screen
    When Search '<content>' in search field
    And click on button 'Go'
    Then Results should be displayed according to the '<content>' searched data
    Examples:
      | content               |
      | TESTPA_20230627122617 |
      | Customer              |
      | CMO                   |
      | B2B                   |
      | SOAP                  |
      | Active                |

  Scenario: BR_2202_32 Validate cancel button functionality while view creation
    When click on button 'Adapt Filters'
    And Unselect checkbox in adapt filter
      | Status |
      | Type   |
    And click on button 'OK'
    And click on button 'Go'
    Then UI should reflect without Filters
      | Status |
      | Type   |
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    When click on button 'Cancel'
    Then After clicking on cancel button, view should not get save with same name and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, new view should not be present

  Scenario:  BR_2202_33 Validate save view functionality
    When click on button 'Adapt Filters'
    And Unselect checkbox in adapt filter
      | Status |
      | Type   |
    And click on button 'OK'
    And click on button 'Go'
    Then UI should reflect without Filters
      | Status |
      | Type   |
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    When click on button 'Save'
    Then After clicking on save button, view should  get save with same name and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, new view should be present
    And clear all customized views

  Scenario: BR_2202_34 Validate save view functionality by setting view as default
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    When Select 'Set as Default' in view
    And click on button 'Save'
    Then After clicking on save button, view should  get save with same name and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, new view should be present
    And clear all customized views

  Scenario: BR_2202_35 Validate save view functionality by Apply automatically option
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    When Select 'Apply Automatically' in view
    And click on button 'Save'
    Then After clicking on save button, view should  get save with same name and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, new view should be present
    And clear all customized views

  Scenario: BR_2202_36 Validate save view functionality by selecting Set as Default and Apply automatically options
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    When Select 'Set as Default' in view
    And Select 'Apply Automatically' in view
    And click on button 'Save'
    Then After clicking on save button, view should  get save with same name and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, new view should be present
    And clear all customized views

  Scenario Outline: BR_2202_37 Validate save view functionality by providing View Name of maximum character limit of length 100 characters
    When Click on Select View option
    And click on button 'Save As'
    And Enter <viewName> in the View
    And click on button 'Save'
    Then After clicking on save button, view should get save with <name> and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, new view should be present
    And clear all customized views
    Examples:
      | viewName                                                                                             | name                                                                                                 |
      | 1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 | 1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 |

  Scenario Outline: BR_2202_38 Validate the error message by execeeding maximum character limit of length 100 characters in the View Name text box while checking save view functionality
    When Click on Select View option
    And click on button 'Save As'
    And Enter <viewName> in the View
    Then View name error message should be displayed
    Examples:
      | viewName                                                                                              |
      | 11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 |

  Scenario Outline: BR_2202_39 Validate the error message by keeping the View Name as empty while checking save view functionality
    When Click on Select View option
    And click on button 'Save As'
    And Enter <viewName> in the View
    Then View name error message should be displayed
    Examples:
      | viewName |
      |          |

  Scenario Outline: BR_2202_40 Validate Manage view functionality by changing the default view
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    And click on button 'Save'
    When Click on Select View option
    And click on button 'Manage'
    And Select the newly created view <viewName> from the list and make it default one
    And click on button 'Save'
    Then After clicking on save button, view should  get save with same name and user will navigate back to home page
    When Click on Select View option
    And clear all customized views
    Examples:
      | viewName |
      | TestView |

  Scenario Outline:  BR_2202_41 Validate Cancel button functionality by deleting the custom view created which was set as default while creation in Manage window
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    And Select 'Set as Default' in view
    And click on button 'Save'
    When Click on Select View option
    And delete <viewName> customized views
    And click on button 'Cancel'
    Then After clicking on cancel button, view should not get save with same name and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, new view should be present
    And clear all customized views
    Examples:
      | viewName |
      | TestView |

  Scenario Outline: BR_2202_42 Validate Manage view functionality by deleting the custom view created which was set as default while creation
    When Click on Select View option
    And click on button 'Save As'
    And Enter name in the View name field
    Then User should be enter text in name field
    And Select 'Set as Default' in view
    And click on button 'Save'
    When Click on Select View option
    And delete <viewName> customized views
    And click on button 'Save'
    Then After clicking on save button, view should get save with <name> and user will navigate back to home page
    Examples:
      | viewName | name     |
      | TestView | Standard |

  Scenario Outline:  BR_2202_43 Validate Manage view functionality by deleting the custom view2 created which was set as default and by setting up the custom view1 as default before saving the changes
    When Click on Select View option
    And click on button 'Save As'
    And create a custom view
      | View1 |
    And click on button 'Save'
    When Click on Select View option
    And click on button 'Save As'
    And create a custom view
      | View2 |
    And Select 'Set as Default' in view
    And click on button 'Save'
    When Click on Select View option
    And delete <viewName1> customized views
    And Select the newly created view <viewName2> from the list and make it default one
    And click on button 'Save'
    Then After clicking on save button, view should get save with <name> and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, 'View1' should be present
    And clear all customized views
    Examples:
      | viewName1 | viewName2 | name     |
      | View2     | View1     | Standard |

  Scenario Outline:  BR_2202_44 Validate Manage view functionality by deleting the Custom View1 created where Custom View2 was set as default while creation before saving the changes in the window
    When Click on Select View option
    And click on button 'Save As'
    And create a custom view
      | View1 |
    And click on button 'Save'
    When Click on Select View option
    And click on button 'Save As'
    And create a custom view
      | View2 |
    And Select 'Set as Default' in view
    And click on button 'Save'
    When Click on Select View option
    And delete <viewName1> customized views
    And click on button 'Save'
    Then After clicking on save button, view should get save with <name> and user will navigate back to home page
    When Click on Select View option
    Then In List of Views, 'View2' should be present
    And clear all customized views
    Examples:
      | viewName1 | name  |
      | View1     | View2 |

  Scenario:  BR_2202_45 Validate Manage view search functionality.
    When Click on Select View option
    And click on button 'Manage'
    And Search 'Standard' in view Search Bar
    Then It must show only the 'Standard' searched view

  Scenario Outline:  BR_2202_46 Validate the sort functionality for single field in ascending order.
    When click on settings
    And click on button 'Sort'
    And Enter '<column>' in sort
    And Select 'Ascending' in '<column>' sort order
    And click on button 'OK'
    Then Data will show up as organization Ascending order with '<column>' in home page
    Examples:
      | column          |
      | Organization    |
      | Status          |
      | Type            |
      | Onboarding Type |

  Scenario Outline: BR_2202_47 Validate the sort functionality for single field in descending order.
    When click on settings
    And click on button 'Sort'
    And Enter '<column>' in sort
    And Select 'Descending' in '<column>' sort order
    And click on button 'OK'
    Then Data will show up as organization Descending order with '<column>' in home page
    Examples:
      | column          |
      | Organization    |
      | Status          |
      | Type            |
      | Onboarding Type |


  Scenario Outline: BR_2202_48 Validate the sort functionality for multiple field.
    When click on settings
    And click on button 'Sort'
    And Enter '<column1>' in sort
    And Select 'Ascending' in '<column1>' sort order
    And click on button 'OK'
    Then Data will show up as organization Ascending order with '<column1>' in home page
    When click on settings
    And click on button 'Sort'
    And Enter '<column2>' in sort
    And Select 'Descending' in '<column2>' sort order
    And click on button 'OK'
    Then Data will show up as organization Ascending order with '<column2>' in home page
    Examples:
      | column1      | column2 |
      | Organization | Status  |

  Scenario Outline:  BR_2202_49 Validate Clear Sorting functionality.
    When click on settings
    And click on button 'Sort'
    And Enter '<column>' in sort
    And Select 'Descending' in '<column>' sort order
    And click on button 'OK'
    Then Data will show up as organization Descending order with '<column>' in home page
    When click on settings
    And click on button 'Sort'
    And Remove '<column>' sorting
    And click on button 'OK'
    Then Organizations Data '<column>' will not be in any of the sorting order
    Examples:
      | column       |
      | Organization |

  Scenario Outline: BR_2202_50 Validate Create Organization functionality by providing organization details same as an existing organization from the table
    When click on button 'Create'
    And Input column '<columnPNID>' '<valuePNID>' in create page
    And Input column '<columnName>' '<valueName>' in create page
    And Provide column '<columnType>' '<valueType>' value in create page
    And Provide column '<columnStatus>' '<valueStatus>' value in create page
    And Provide column '<columnRole>' '<valueRole>' value in create page
    And Provide column '<columnOnboardingType>' '<valueOnboardingType>' value in create page
    And Provide the '<valueInterfaceType>' on '<InterfaceType>' filter
    And click on button 'CreateOrg'
    Then Verify error message "Organization already exists with Name -"
    Examples:
      | columnType | valueType | columnStatus | valueStatus | columnRole | valueRole | columnOnboardingType | valueOnboardingType | valueInterfaceType | InterfaceType  | columnPNID        | valuePNID       | columnName | valueName               |
      | Type       | Customer  | Status       | Active      | Role       | MAH       | Onboarding Type      | B2B                 | AS2                | Interface Type | Organization PNID | Test07172023lxy | Name       | TestUSPartner WHOCom001 |

  Scenario Outline: BR_2202_51 Validate error messages when mandatory fields are missing while creating an organization
    When click on button 'Create'
    And Input column '<columnName>' '<valueName>' in create page
    And Provide column '<columnType>' '<valueType>' value in create page
    And Provide column '<columnStatus>' '<valueStatus>' value in create page
    And Provide column '<columnRole>' '<valueRole>' value in create page
    And Provide column '<columnOnboardingType>' '<valueOnboardingType>' value in create page
    And Provide the '<valueInterfaceType>' on '<InterfaceType>' filter
    And Clear '<column>' column
    And click on button 'CreateOrg'
    Then Verify error message "Invalid Organization PNID. Please fill Organization PNID"
    Examples:
      | columnType | valueType | columnStatus | valueStatus | columnRole | valueRole | columnOnboardingType | valueOnboardingType | valueInterfaceType | InterfaceType  | columnName | valueName             | column            |
      | Type       | Customer  | Status       | Active      | Role       | MAH       | Onboarding Type      | B2B                 | AS2                | Interface Type | Name       | Test07172023Mario LXY | Organization PNID |


  Scenario Outline: BR_2202_52 Validate Edit Organization functionality by providing organization details same as an existing organization from the table
    When click on <OrgName> one organization
    And click on button 'Edit'
    And Provide column '<columnRole>' '<valueRole>' value in create page
    And click on button 'Save'
    Then Verify toast message "Your changes have been saved."
    And click on button 'Edit'
    And Remove the '<valueRole>' in the organization
    And click on button 'Save'
    Then Verify toast message "Your changes have been saved."
    Examples:
      | OrgName               | columnRole | valueRole |
      | testsc_20230721084311 | Role       | MAH       |

  Scenario Outline: BR_2202_53 Validate error messages when mandatory fields are missing while editing an organization
    When click on <OrgName> one organization
    And click on button 'Edit'
    And Remove the '<valueRole>' in the organization
    And click on button 'Save'
    And click the button to show error message
    Then Verify error message "Invalid Role.Please select value from the list"
    Examples:
      | OrgName               | valueRole |
      | testsc_20230721084311 | CMO       |

  Scenario Outline: BR_2202_54 Validate Scenario adding functionality to an organization
    When click on <OrgName> one organization
    And click on button 'AddScenario'
    And check '<value>' in Scenario section
    And click on button 'ScenarioAdd'
    Then Verify toast message "Scenario Added Successfully"
    And Remove '<value>' in the scenario section
    And click on button 'Delete'
    Examples:
      | OrgName               | value                               |
      | testsc_20230721084311 | Regulatory Collaboration for Brazil |

  Scenario Outline: BR_2202_55 Validate Scenario adding functionality by adding the same Scenario that was added to other organization
    When click on <OrgName> one organization
    And click on button 'AddScenario'
    And check '<value>' in Scenario section
    And click on button 'ScenarioAdd'
    Then Verify toast message "Scenario Added Successfully"
    And Remove '<value>' in the scenario section
    And click on button 'Delete'
    Examples:
      | OrgName               | value                               |
      | testsc_20230721084311 | Regulatory Collaboration for Brazil |

  Scenario Outline: BR_2202_56 Validate Scenario deleting functionality to an organization
    When click on <OrgName> one organization
    And click on button 'AddScenario'
    And check '<value>' in Scenario section
    And click on button 'ScenarioAdd'
    Then Verify toast message "Scenario Added Successfully"
    And Remove '<value>' in the scenario section
    And click on button 'Delete'
    Then Verify toast message "Scenario Added Successfully"
    Examples:
      | OrgName               | value                               |
      | testsc_20230721084311 | Regulatory Collaboration for Brazil |

  Scenario Outline: BR_2202_57 Validate Search functionality in Scenario section
    When click on <OrgName> one organization
    And click on button 'AddScenario'
    And search '<value>' in Scenario search bar
    And click on search button in scenario search bar
    And check '<value>' in Scenario section
    And click on button 'ScenarioAdd'
    Then Verify toast message "Scenario Added Successfully"
    And Remove '<value>' in the scenario section
    And click on button 'Delete'
    Then Verify toast message "Scenario Added Successfully"
    Examples:
      | OrgName               | value                               |
      | testsc_20230721084311 | Regulatory Collaboration for Brazil |

  Scenario: BR_2202_58 Validate creating Admin User functionality
    #TODO Skip 58~68, because all of these are related to email verification

  Scenario Outline: BR_2202_70 Verify Cancel/Back Button functionality while Creating an organization
    When click on button 'Create'
    And Input column '<columnPNID>' '<valuePNID>' in create page
    And Input column '<columnName>' '<valueName>' in create page
    And Provide column '<columnType>' '<valueType>' value in create page
    And Provide column '<columnStatus>' '<valueStatus>' value in create page
    And Provide column '<columnRole>' '<valueRole>' value in create page
    And Provide column '<columnOnboardingType>' '<valueOnboardingType>' value in create page
    And Provide the '<valueInterfaceType>' on '<InterfaceType>' filter
    And click on button 'Cancel'
    And click on button 'Ok'
    Then Verify organization '<valueName>' is not created
    Examples:
      | columnType | valueType | columnStatus | valueStatus | columnRole | valueRole | columnOnboardingType | valueOnboardingType | valueInterfaceType | InterfaceType  | columnPNID        | valuePNID         | columnName | valueName     |
      | Type       | Customer  | Status       | Active      | Role       | MAH       | Onboarding Type      | B2B                 | AS2                | Interface Type | Organization PNID | Test0727mario1024 | Name       | Test0727mario |


  Scenario Outline: BR_2202_71 Verify Cancel/Back Button functionality while Editing an organization
    When click on <OrgName> one organization
    And click on button 'Edit'
    And Provide column '<columnRole>' '<valueRole>' value in create page
    And click on button 'Cancel'
    Then Validate the organization '<columnRole>' value '<existValue>'
    Examples:
      | OrgName               | columnRole | valueRole | existValue |
      | testsc_20230721084311 | Role       | MAH       | CMO        |

  Scenario Outline:  BR_2202_72 Validate Organization Id field in Organization Detail Page by providing Alphanumeric with underscores with maximum character limit
    When click on button 'Create'
    And Input a random PNID in create page with length '30'
    And Input a random Organization name in create page with length '20'
    And Provide column '<columnType>' '<valueType>' value in create page
    And Provide column '<columnStatus>' '<valueStatus>' value in create page
    And Provide column '<columnRole>' '<valueRole>' value in create page
    And Provide column '<columnOnboardingType>' '<valueOnboardingType>' value in create page
    And Provide the '<valueInterfaceType>' on '<InterfaceType>' filter
    And click on button 'CreateOrg'
    And Verify toast message "Organization created successfully"
    Examples:
      | columnType | valueType | columnStatus | valueStatus | columnRole | valueRole | columnOnboardingType | valueOnboardingType | valueInterfaceType | InterfaceType  |
      | Type       | Customer  | Status       | Active      | Role       | MAH       | Onboarding Type      | B2B                 | AS2                | Interface Type |
