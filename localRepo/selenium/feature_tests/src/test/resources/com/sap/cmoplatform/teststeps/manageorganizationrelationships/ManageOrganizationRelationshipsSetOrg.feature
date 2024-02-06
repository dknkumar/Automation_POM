@ManageOrganizations
Feature: Manage Organization Relationships App

  Background:
    Given the user logs in as 'MOR_User' in the login page
    And the ManageOrganizations app is clicked in the USSC launcher page

    # TODO: PHN_19662_1  To be added lateron
#  Scenario Outline:PHN_19662_1     To create customer organization from Manage Organization app
#    And click on 'Create' button
#    And input 'Name' field with <Name>
#    And select 'Type' field with <Type>
#    And select 'Role' field with <Role>
#    And select 'Status' field with <Status>
#    Then 'OrganizationPNID' should be generated
#    And click on 'Create' button

  Scenario Outline: PHN_19662_25     Validate inactive org should not be displayed while creating relationship
    Then the 'Manage Organizations' list page is displayed on the page
    And search for <PNID> in search field
    And click on 'Go' button
    And set the organization status to 'Inactive'
    And click on 'Save' button
    Then organization status should be 'Inactive'
    And navigate to home screen
    And the ManageOrganizationRelationships app is clicked in the USSC launcher page
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And click on 'Create' button
    And click on scenario drop down
    And select <Scenario> in Scenario drop down in detail page
    Then <Inviter Organization> should not be displayed in Inviter Organization in detail page
    And cancel the creation of new relationship
    And navigate to home screen
    And the ManageOrganizations app is clicked in the USSC launcher page
    Then the 'Manage Organizations' list page is displayed on the page
    And search for <PNID> in search field
    And click on 'Go' button
    And set the organization status to 'Active'
    And click on 'Save' button
    Then organization status should be 'Active'
    Examples:
      | PNID                  | Scenario                        | Inviter Organization  |
      | testna_20230615023211 | Regulatory Reporting for Brazil | testna_20230615023211 |

    #TODO: PHN_19662_26  To be confirmed
 Scenario Outline: PHN_19662_26     Validate if user marked organization as inactive then by default all the maintained relationship  also become inactive
    Then the 'Manage Organizations' list page is displayed on the page
    And search for <PNID> in search field
    And click on 'Go' button
    And set the organization status to 'Inactive'
    And click on 'Save' button
    Then organization status should be 'Inactive'
    And navigate to home screen
    And the ManageOrganizationRelationships app is clicked in the USSC launcher page
    Then the 'Manage Organization Relationships' list page is displayed on the page
    And input <PNID> in 'Inviter Organization' filter
    And click on 'Go' button
    Then the relationship should all be inactive
   Examples:
     | PNID                  |
     | testna_20230615023211 |




