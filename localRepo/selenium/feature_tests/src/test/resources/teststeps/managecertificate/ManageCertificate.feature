@ManageCertificate
Feature: Manage Certificate App

  Background:
    Given the user logs in as 'MCert_User' in the login page
    And the ManageCertificates app is clicked in the USSC launcher page

  Scenario: MCert_Reg_TC_01- Verify uploading new TLS Certificate
    When Navigate to TLS Certificate type
    And Click upload button if the cert not exist
    And Upload new cert
    Then Certificate should be successfully uploaded in app with same Serial number, Status(New), Expiry date

  Scenario: MCert_Reg_TC_02- Verify Activating new TLS Certificate
    When Navigate to TLS Certificate type
    And Inside that Select the New TLS certificate
    And Click Activate button
    Then New certificate should be successfully Activated in app
    Then same entry of Certificate should be updated in the main page with given serial number, Status (Active), In Use checkbox (Checked), Activation Date (Current Date), Expiry Date etc.

 Scenario: MCert_Reg_TC_03- Verify Activating Inactive TLS Certificate
    When Navigate to TLS Certificate type
    And Inside that Select the Inactive certificate
    And Click Activate button
    Then Inactive Certificate should be successfully Activated in app
    Then same entry of Certificate should be updated in the main page with given serial number, Status (Active), In Use checkbox (Checked), Activation Date (Current Date), Expiry Date

  Scenario: MCert_Reg_TC_04- Verify Deleting New/Inactive TLS Certificate
    When Navigate to TLS Certificate type
    And Inside that Select the New or Inactive certificate
    And Click Delete button
    Then Certificate should be successfully Deleted from app

  Scenario: MCert_Reg_TC_05- Verify uploading new MLS Certificate
    When Navigate to MLS Certificate type
    And Click upload if the cert not exist
    And Upload new cert
    Then MLS Certificate should be successfully uploaded in app with same Serial number, Status(New), Expiry date

  Scenario: MCert_Reg_TC_06- Verify Activating new MLS Certificate
    When Navigate to MLS Certificate type
    And Inside that Select the New MLS certificate
    And Click Activate button
    Then same entry of MLS Certificate should be updated in the main page with given serial number, Status (Active), In Use checkbox (Checked), Activation Date (Current Date), Expiry Date etc.
    When the user logs in CPI for MC
    And Search for the alias as updated in the manage certificate app
    Then Activated Certificate entry updated in CPI Keystore with same Alias

  Scenario: MCert_Reg_TC_07- Verify Activating Inactive MLS Certificate
    When Navigate to MLS Certificate type
    And Inside that Select the Inactive MLS certificate
    And Click Activate button
    Then Inactive Certificate should be successfully Activated in app
    Then same entry of MLS Certificate should be updated in the main page with given serial number, Status (Active), In Use checkbox (Checked), Activation Date (Current Date), Expiry Date etc.
    When the user logs in CPI for MC
    And Search for the alias as updated in the manage certificate app
    Then Activated Certificate entry updated in CPI Keystore with same Alias

  Scenario: MCert_Reg_TC_08- Verify Deleting New/Inactive MLS Certificate
    When Navigate to MLS Certificate type
    And Inside that Select the New or Inactive MLS certificate
    And Click Delete button
    Then Certificate should be successfully Deleted from app

  Scenario: MCert_Reg_TC_09- Verify uploading more than 5 certificate in any of the type of certificates
    When Navigate to MLS Certificate type
    And Inside that try to upload more than 5 certificates
    Then Certificate should NOT get uploaded & error message "Maximum five certificates permitted" should be displayed

  Scenario: MCert_Reg_TC_10- Verify Downloading TLS Certificate from app
    When click on download button of the TLS certificate
    Then '.cer' file should get downloaded
    And Navigate to TLS Certificate type
    And click on download button of the certificate
    Then '.cer' file should get downloaded

  Scenario: MCert_Reg_TC_11- Verify Downloading MLS Certificate from app
    And click on download button of the MLS certificate
    Then '.cer' file should get downloaded
    And Navigate to MLS Certificate type
    And click on download button of the certificate
    Then '.cer' file should get downloaded

  Scenario Outline: MCert_Reg_TC_12-1- Verify uploading Duplicate TLS certificate in  SAME environment
    And Navigate to TLS Certificate type
    And click on 'Upload' button
    And Upload cert in 'TLS' with <cert name>
    Then 'Duplicate certificate not permitted' error message should be displayed
    Examples:
      | cert name               |
      | TLS_duplicated_cert.cer |

  Scenario Outline: MCert_Reg_TC_12-2- Verify uploading Duplicate MLS certificate in  SAME environment
    And Navigate to MLS Certificate type
    And click on 'Upload' button
    And Upload cert in 'MLS' with <cert name>
    Then 'Duplicate certificate not permitted' error message should be displayed
    Examples:
      | cert name               |
      | MLS_duplicated_cert.cer |

  Scenario: MCert_Reg_TC_13- Verify Certificate Details page
    And Navigate to MLS Certificate type
    Then the certificate details should display columns
      | Certificate Filename |
      | Serial Number        |
      | Subject DN           |
      | Issuer DN            |
      | In Use               |
      | Activation Date      |
      | Status               |
      | Expires In (Days)    |
      | Expiry Date          |


  Scenario Outline: MCert_Reg_TC_14- Verify uploading Expired certificate
    When Navigate to MLS Certificate type
    And click on 'Upload' button
    And Upload cert in 'MLS' with <cert name>
    Then 'Expired certificate not permitted' error message should be displayed
    Examples:
      | cert name            |
      | MLS_expired_cert.cer |

#TODO:  PHARMANETWORK-23552
  Scenario Outline: MCert_Reg_TC_15- Verify that system allows to upload only .cer types of certificates for MLS/TLS
    When Navigate to MLS Certificate type
    And click on 'Upload' button
    And Upload cert in 'MLS' with <cert name>
    Then 'File format must be .cer' error message should be displayed
    Examples:
      | cert name              |
      | MLS_diff_type_cert.crt |
      | MLS_diff_type_cert.rar |
      | MLS_diff_type_cert.xml |


#No Adapt Filters, skip MCert_Reg_TC_16

  Scenario Outline: MCert_Reg_TC_17-1- Verify Search functionality for Name
    And search for <information> in search field
    Then result should be displayed with 'Name' of <information>
    Examples:
      | information |
      | QA_MLS      |

  Scenario Outline: MCert_Reg_TC_17-2- Verify Search functionality for Type
    And search for <information> in search field
    Then result should be displayed with 'Type' of <information>
    Examples:
      | information |
      | MLS         |

  Scenario Outline: MCert_Reg_TC_17-3- Verify Search functionality for Environment
    And search for <information> in search field
    Then result should be displayed with 'Environment' of <information>
    Examples:
      | information |
      | QA          |

  Scenario Outline: MCert_Reg_TC_17-4- Verify Search functionality for Serial Number
    And search for <information> in search field
    Then result should be displayed with 'Serial Number' of <information>
    Examples:
      | information                      |
      | 1e92944e946ef8e4e72ea06e97365663 |

  Scenario Outline: MCert_Reg_TC_17-5- Verify Search functionality for Alias Name
    And search for <information> in search field
    Then result should be displayed with 'Alias Name' of <information>
    Examples:
      | information   |
      | cf_qa_mlstest |

  Scenario Outline: MCert_Reg_TC_17-6- Verify Search functionality for Status
    And search for <information> in search field
    Then result should be displayed with 'Status' of <information>
    Examples:
      | information |
      | Active      |

  Scenario Outline: MCert_Reg_TC_18- Verify Group functionality
    And click on group button in main page
    And select <Group Order> in group order section
    And select <Group By> in group by section
    And click on 'OK' button
    Then result should be displayed with <Group Order> order of group by <Group By>
    Examples:
      | Group Order | Group By |
      | Ascending   | Type     |

  Scenario Outline: MCert_Reg_TC_19- Verify Sort functionality
    And click on sort button in main page
    And select <Sort Order> in sort order section
    And select <Sort By> in sort by section
    And click on 'OK' button
    Then result should be displayed with <Sort Order> order of sort by <Sort By>
    Examples:
      | Sort Order | Sort By |
      | Ascending  | Name    |

  Scenario Outline: MCert_Reg_TC_20- Verify Export to Excel functionality
    And click on export to excel button in main page
    Then <file name> should be downloaded with correct value
    Examples:
      | file name        |
      | Certificates.csv |

  Scenario: MCert_Reg_TC_21- Verify Personalization functionality
    And click on Personalization button
    And select columns from the Personalisation
      | Name          |
      | Type          |
      | Environment   |
      | Serial Number |
      | Alias Name    |
      | Expiry Date   |
    And click on 'OK' button
    Then columns should be displayed on main page
      | Name          |
      | Type          |
      | Environment   |
      | Serial Number |
      | Alias Name    |
      | Expiry Date   |

  Scenario Outline: MCert_Reg_TC_22- Verify Change log functionality
    And Navigate to TLS Certificate type
    And activate certificate <Cert Name>
    And activate certificate <Previous Cert Name>
    And Navigate to main page
    And click on 'Change Log' button
    Then changelog should display with columns of
      | Activate                                             |
      | TLS / QA                                             |
      | Test_TLS_04.cer                                      |
      | Cert_02.cer (097dbd5b8d9b6ce7bd93cd28f16822ce)       |
      | Test_TLS_04.cer (008b63e7bd35cded08f5a3649cf6d19c75) |
      | testcertuser@mailnesia.com                           |
    Examples:
      | Cert Name   | Previous Cert Name |
      | Cert_02.cer | Test_TLS_04.cer    |

#TODO
  Scenario: MCert_Reg_TC_23- Verify creating New user & assigning roles for Manage Cert app via Manage User app

  Scenario: MCert_Reg_TC_24- Verifying Manage Cert app access after removing roles  via Manage User app
    And navigate to home screen
    And the ManageUsers app is clicked in the USSC launcher page
    And click on 'test cert' user
    And click on 'Edit' button
    And remove 'Manage Certificate' application
    And click on 'Save' button
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MCert_User' in the login page
    Then the 'ManageCertificates' app should not be displayed
    And the ManageUsers app is clicked in the USSC launcher page
    And click on 'test cert' user
    And click on 'Edit' button
    And add 'Manage Certificate' application
    And click on 'Save' button
    And the user logs out from current account
    And click on 'OK' button
    And click on 'Sign in again' button
    And the user logs in as 'MCert_User' in the login page
    Then the 'ManageCertificates' app should be displayed
