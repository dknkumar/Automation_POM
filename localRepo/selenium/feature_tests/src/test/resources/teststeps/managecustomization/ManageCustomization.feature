@ManageCustomization
Feature: Manage Customization App

  Background:
    Given the user logs in as 'MC_User' in the login page
    And the ManageCustomizations app is clicked in the USSC launcher page
    And clear data in the customizations app

  Scenario: BR_2202_01	Manage Customizations App	Validate that customer admin or Customer user is able to access 'Manage Customizations' App
    Then App must get open and user must be able to see the customizations table and all the coulmns, add , edit, delete, sort, filter and refresh buttons

  Scenario Outline: BR_2202_02	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option - Declarant for Activation
    When Click on Add button
    Then Add button is clicked
    When Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization with:<Scenario>,<parameter key>,<parameter option>,<parameter value> get from payload
    Examples:
    |Scenario                           |parameter key     |parameter option          |parameter value|
    |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation  |ReadpointGLN   |

  Scenario Outline: BR_2202_03	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration BrazilParameter Key - CNPJ Determination Parameter Option - Declarant for Dispatch
    Then App must get open and user must be able to see the customizations table and all the coulmns, add , edit, delete, sort, filter and refresh buttons
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
  Examples:
    |Scenario                           |parameter key     |parameter option          |parameter value|
    |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Dispatch    |ReadpointGLN   |

  Scenario Outline: BR_2202_04	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination Parameter Option - Declarant for Finalization
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option          |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Finalization|ReadpointGLN   |

  Scenario Outline: BR_2202_05	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination Parameter Option  - Declarant for Receive
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option          |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Receive     |ReadpointGLN   |

  Scenario Outline: BR_2202_06	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination Parameter Option - MbrAgt for Activation
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization with:<Scenario>,<parameter key>,<parameter option>,<parameter value> get from payload
    Examples:
      |Scenario                           |parameter key     |parameter option       |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|MbrAgt for Activation  |ReadpointGLN   |

  Scenario Outline: BR_2202_07	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option - MbrAgt for Dispatch
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option          |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|MbrAgt for Dispatch       |ReadpointGLN   |

  Scenario Outline: BR_2202_08	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option - MbrAgt for Finalization
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option          |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|MbrAgt for Finalization   |ReadpointGLN   |

  Scenario Outline: BR_2202_09	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option - MbrAgt for Receive
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option     |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|MbrAgt for Receive   |ReadpointGLN   |

  Scenario Outline: BR_2202_10	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Partner for Dispatch
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option     |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Partner for Dispatch |DestOPGLN      |

  Scenario Outline: BR_2202_11	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Partner for Receive
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option     |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Partner for Receive  |ReadpointGLN   |

  Scenario Outline: BR_2202_12	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option - Receiver for Activation
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization with:<Scenario>,<parameter key>,<parameter option>,<parameter value> get from payload
    Examples:
      |Scenario                           |parameter key     |parameter option       |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Receiver for Activation|DestOPGLN   |

  Scenario Outline: BR_2202_13	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Receiver for Dispatch
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option     |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Receiver for Dispatch|DestOPGLN   |

  Scenario Outline: BR_2202_14	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Receiver for Finalization
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Receiver for Finalization|DestOPGLN   |

  Scenario Outline: BR_2202_15	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Receiver for Receive
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Receiver for Receive     |ReadpointGLN   |

  Scenario Outline: BR_2202_16	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option - Sender for Activation
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization with:<Scenario>,<parameter key>,<parameter option>,<parameter value> get from payload
    Examples:
      |Scenario                           |parameter key     |parameter option       |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Sender for Activation  |SrcOPGLN       |

  Scenario Outline: BR_2202_17	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Sender for Dispatch
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option     |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Sender for Dispatch  |ReadpointGLN   |

  Scenario Outline: BR_2202_18	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Sender for Finalization
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Sender for Finalization  |ReadpointGLN   |

  Scenario Outline: BR_2202_19	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - CNPJ Determination  Parameter Option -  Sender for Receive
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Sender for Receive       |ReadpointGLN   |

  Scenario Outline: BR_2202_20	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - GLN for Biztransaction Id in EPCIS  Parameter Option -  13 digit numeric GLN
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Input Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key                     |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|GLN for Biztransaction ID in EPCIS|                         |1234567890123  |

  Scenario Outline: BR_2202_21	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - Message Retention Time  Parameter Option -  Error Message
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key         |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|Message Retention Time|Error Message            |30Days         |
  Scenario Outline: BR_2202_22	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - Message Retention Time  Parameter Option -  Successful Message
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key         |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|Message Retention Time|Successful Message       |30Days         |

  Scenario Outline: BR_2202_23	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - Outsource CNPJ for Activation
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
#    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key                |parameter option   |parameter value|
      |Regulatory Collaboration for Brazil|Outsource CNPJ for Activation|                   |ReadpointGLN   |

  Scenario Outline: BR_2202_24	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - SAPQueueMessageSender in EPCIS
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
#    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|SAPQueueMessageSender in EPCIS|       |SenderCNPJ_ReceiverCNPJ         |

#no value in Suppress Aggregation in receive notification
  Scenario Outline: BR_2202_25	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - Suppress Aggregation in Receive Notification
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
#    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|Suppress Aggregation in Receive Notification|          |TestPartner InvCom001    |
  Scenario Outline: BR_2202_26	Manage Customizations App	Add the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - Suppress Aggregation in Receive Notification
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
#    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Cancel button
    Then Warning Pop Up must open
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|Suppress Aggregation in Receive Notification|          |TestPartner InvCom001   |
  Scenario Outline: BR_2202_27	Validate the browser back button functionality while Adding customization through manage customization app
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
#    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And back to home page
    Then unsaved window pop up
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|Suppress Aggregation in Receive Notification|          |TestPartner InvCom001   |

  Scenario Outline: BR_2202_28	Validate the Error message when Mandatory fields are empty while adding the customization.
    And Click on Add button
    Then Add button is clicked
    And Click on Save button
    Then warning message Scenario display
    And Select Scenario: value <Scenario>
    And Click on Save button
    Then warning message Parameter Key display
    And Select Parameter Key: value <parameter key>
    And Click on Save button
    Then warning message Parameter Option display
    And Select Parameter Option: value <parameter option>
    And Click on Save button
    Then warning message Parameter Value display
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |

  Scenario Outline: BR_2202_29	Validate the Edit field Functionality
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Click on Edit button
    And Modify Parameter Value: value <changedValue>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<changedValue>
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|changedValue|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |180Days      |

  Scenario Outline: BR_2202_30	Verify that multiple editing is possible
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Select record <Scenario2>:<parameter key2>:<parameter option2>
    And Click on Edit button
    And Modify Parameter Value: value <changedValue2>
    And ModifySecond Parameter Value: value <changedValue>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<changedValue>
    Then Newly added customization record is available with:<Scenario2>,<parameter key2>,<parameter option2>,<changedValue2>
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|changedValue |Scenario2                           |parameter key2     |parameter option2         |parameter value2|changedValue2|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation       |SrcOPGLN   |SrcPPGLN    |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Dispatch           |SrcLocGLN       |ReadpointGLN     |

  Scenario Outline: BR_2202_31	Validate the Delete field Functionality
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Delete record <Scenario>:<parameter key>:<parameter option>
    And Click on Ok button
    Then Newly added customization record is not available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |

  Scenario Outline: BR_2202_32	Verify that multiple deletion is possible
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Select record <Scenario2>:<parameter key2>:<parameter option2>
    And Click on Delete button
    And Click on OK button
    Then Newly added customization record is not available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Then Newly added customization record is not available with:<Scenario>,<parameter key>,<parameter option>,<parameter value2>
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2                           |parameter key2     |parameter option2         |parameter value2|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation       |SrcOPGLN   |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Dispatch    |SrcLocGLN       |

  Scenario Outline: BR_2202_33	Validate the sort functionality for Scenario field in descending order
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario3>
    And Select Parameter Key: value <parameter key3>
    And Select Parameter Option: value <parameter option3>
    And Select Parameter Value: value <parameter value3>
    And Click on Save button
    When Sort order Ascending by Scenario
    Then Order is Ascending sorted by Scenario
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Select record <Scenario2>:<parameter key2>:<parameter option2>
    And Select record <Scenario3>:<parameter key3>:<parameter option3>
    And Click on Delete button
    And Click on OK button
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2|Scenario3                          |parameter key3 |parameter option3                   |parameter value3|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |Supply Chain Partner Collaboration |ICH Adapter    |Is ICH Adapter used in communication|True            |

  Scenario Outline: BR_2202_34	Validate the sort functionality for Scenario field in descending order
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario3>
    And Select Parameter Key: value <parameter key3>
    And Select Parameter Option: value <parameter option3>
    And Select Parameter Value: value <parameter value3>
    And Click on Save button
    When Sort order Descending by Scenario
    Then Order is Descending sorted by Scenario
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Select record <Scenario2>:<parameter key2>:<parameter option2>
    And Select record <Scenario3>:<parameter key3>:<parameter option3>
    And Click on Delete button
    And Click on OK button
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2|Scenario3                          |parameter key3 |parameter option3                   |parameter value3|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |Supply Chain Partner Collaboration |ICH Adapter    |Is ICH Adapter used in communication|True            |

  Scenario Outline: BR_2202_35	Validate 'Changed' column timestamp
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    When Select record <Scenario>:<parameter key>:<parameter option>
    And Click on Edit button
    And Modify Parameter Value: value <changedValue>
    And Click on Save button
    Then Newly added customization record is available with timeStamp:<Scenario>,<parameter key>,<parameter option>,<changedValue>
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|changedValue|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |180Days      |

  Scenario Outline: BR_2202_36	Customers cannot add duplicate values for scenario, parameter key and option combination
    When Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Changes will not save and throw warning Customization already exists
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Click on Delete button
    And Click on OK button
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN   |

  Scenario Outline: BR_2202_37	Verify Filter functionality for Scenario
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario3>
    And Select Parameter Key: value <parameter key3>
    And Select Parameter Option: value <parameter option3>
    And Select Parameter Value: value <parameter value3>
    And Click on Save button
    When Filter By Scenario :<Scenario3>
    Then Only Scenario :<Scenario3> customizations display
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2|Scenario3                          |parameter key3 |parameter option3                   |parameter value3|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |Supply Chain Partner Collaboration |ICH Adapter    |Is ICH Adapter used in communication|True            |

  Scenario Outline: BR_2202_38	Verify Filter functionality for Parameter key
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario3>
    And Select Parameter Key: value <parameter key3>
    And Select Parameter Option: value <parameter option3>
    And Select Parameter Value: value <parameter value3>
    And Click on Save button
    When Filter By Parameter Key :<parameter key>
    Then Only Parameter Key :<parameter key> customizations display
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2|Scenario3                          |parameter key3 |parameter option3                   |parameter value3|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |Supply Chain Partner Collaboration |ICH Adapter    |Is ICH Adapter used in communication|True            |

  Scenario Outline: BR_2202_39	Verify Filter functionality for Parameter Option
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario3>
    And Select Parameter Key: value <parameter key3>
    And Select Parameter Option: value <parameter option3>
    And Select Parameter Value: value <parameter value3>
    And Click on Save button
    When Filter By Parameter Option :<parameter option>
    Then Only Parameter Option :<parameter option> customizations display
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2|Scenario3                          |parameter key3 |parameter option3                   |parameter value3|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |Supply Chain Partner Collaboration |ICH Adapter    |Is ICH Adapter used in communication|True            |

  Scenario Outline: BR_2202_40	Verify Filter functionality for with the combination of filters
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario3>
    And Select Parameter Key: value <parameter key3>
    And Select Parameter Option: value <parameter option3>
    And Select Parameter Value: value <parameter value3>
    And Click on Save button
    When Filter the combination of filters with <Scenario2>:<parameter key2>:<parameter option2>
    Then Only cunstomizations match to the combinations:<Scenario2>:<parameter key2>:<parameter option2> display
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2|Scenario3                          |parameter key3 |parameter option3                   |parameter value3|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |Supply Chain Partner Collaboration |ICH Adapter    |Is ICH Adapter used in communication|True            |

  Scenario Outline: BR_2202_41	Validate Change Log page overview
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Click on Edit button
    And Modify Parameter Value: value <changedValue>
    And Click on Save button
    When Click on Change Log button
    Then ChangeLog colume display well
      | Action |
      | Field       |
      | Item   |
      | Previous   |
      | New  |
      | Who   |
      | When   |
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|changedValue|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |180Days      |

  Scenario Outline: BR_2202_42	Validate Filter Reset functionality on Filter Window
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario3>
    And Select Parameter Key: value <parameter key3>
    And Select Parameter Option: value <parameter option3>
    And Select Parameter Value: value <parameter value3>
    And Click on Save button
    When Filter the combination of filters with <Scenario2>:<parameter key2>:<parameter option2>
    And Filter Reset
    Then App should not display the results based on the filter values selected:<Scenario2>:<parameter key2>:<parameter option2>
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2|Scenario3                          |parameter key3 |parameter option3                   |parameter value3|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |Supply Chain Partner Collaboration |ICH Adapter    |Is ICH Adapter used in communication|True            |

  Scenario Outline: BR_2202_43	Validate Create Action is triggered when a new customization is added - change Log page
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    When Click on Change Log button
    Then Check Create record <Scenario>:<parameter key>:<parameter option> :value <parameter value> and timeStamp
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|changedValue|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |180Days      |



  Scenario Outline: BR_2202_44	Validate Update Action is triggered when a customization is edited based on parameter value - change Log page
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    When Select record <Scenario>:<parameter key>:<parameter option>
    And Click on Edit button
    And Modify Parameter Value: value <changedValue>
    And Click on Save button
    When Click on Change Log button
    Then Check Update record <Scenario>:<parameter key>:<parameter option> :value <changedValue> and timeStamp
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|changedValue|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |180Days      |

  Scenario Outline: BR_2202_45	Validate multiple Update Actions are triggered when 2 or more Customizations are edited - Change Log page
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    And Select record <Scenario>:<parameter key>:<parameter option>
    And Select record <Scenario2>:<parameter key2>:<parameter option2>
    And Click on Edit button
    And Modify Parameter Value: value <changedValue2>
    And ModifySecond Parameter Value: value <changedValue1>
    And Click on Save button
    And Click on Change Log button
    Then Check Update record <Scenario>:<parameter key>:<parameter option> :value <changedValue1> and timeStamp
    Then Check Update record <Scenario2>:<parameter key2>:<parameter option2> :value <changedValue2> and timeStamp
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2 |changedValue1|changedValue2|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |SrcLocGLN           |False  |


  Scenario Outline: BR_2202_46	Validate Delete Action is triggered when a Customization is deleted from the table - change Log page
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Delete record <Scenario>:<parameter key>:<parameter option>
    And Click on Ok button
    When Click on Change Log button
    Then Check Delete record <Scenario>:<parameter key>:<parameter option> :value <valueChanged> and timeStamp
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|valueChanged|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         ||

  Scenario Outline: BR_2202_47	Validate the error message for Adding the customization for the Scenario - Regulatory Collaboration Brazil Parameter Key - GLN for Biztransaction Id in EPCIS Parameter Option - >13 digit numeric GLN value or alpha numeric value
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Input Parameter Value: value <parameter value>
    And Click on Save button
    Then Changes will not save and throw warning Invalid Parameter Value
    Examples:
      |Scenario                           |parameter key                     |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|GLN for Biztransaction ID in EPCIS|                         |123456789012345  |

  Scenario Outline: BR_2202_48	Manage Customizations App	Add the customization for the Scenario - Regulatory Reporting Brazil Parameter Key - Outsource CNPJ for Activation
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
#    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    Then Newly added customization record is available with:<Scenario>,<parameter key>,<parameter option>,<parameter value>
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Reporting for Brazil|Outsource CNPJ for Activation|          |ReadpointGLN    |

  Scenario Outline: BR_2202_49	Manage Customizations App	Validate Search functionality - Change log page
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    When Click on Change Log button
    Then ChangeLog display only:
      |Create|
      |Customization|
      |SrcOPGLN|
      |Regulatory Collaboration for Brazil|
      |CNPJ Determination|

    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Collaboration for Brazil|CNPJ Determination           |Declarant for Activation |SrcOPGLN       |


  Scenario Outline: BR_2202_50	Manage Customizations App	Validate No Action is logged when a adding a new Customization is cancelled using Cancel button - Change Log page
    And Click on Add button
    Then Add button is clicked
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
#    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Cancel button
    And Click on OK button
    When Click on Change Log button
    Then CheckNo Create record <Scenario>:<parameter key>:<parameter option> :value <valueChanged> and timeStamp
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|
      |Regulatory Reporting for Brazil|Outsource CNPJ for Activation|          |ReadpointGLN    |


  Scenario Outline: BR_2202_51	Manage Customizations App	Validate No Action is logged when a editing a new Customization is cancelled using Cancel button - Change Log page
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    When Select record <Scenario>:<parameter key>:<parameter option>
    And Click on Edit button
    And Modify Parameter Value: value <changedValue>
    And Click on Cancel button
    And Click on OK button
    And Click on Change Log button
    Then CheckNo Update record <Scenario>:<parameter key>:<parameter option> :value <changedValue> and timeStamp
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|changedValue|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |30Days         |180Days      |

  Scenario Outline: BR_2202_52	Manage Customizations App	Validate No Action is logged when a deleting a new Customization is cancelled using Cancel button - Change Log page
    And Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Delete record <Scenario>:<parameter key>:<parameter option>
    And Click on Cancel button
    When Click on Change Log button
    Then CheckNo Delete record <Scenario>:<parameter key>:<parameter option> :value <valueChanged> and timeStamp
    Examples:
      |Scenario                           |parameter key                |parameter option         |parameter value|valueChanged|
      |Regulatory Collaboration for Brazil|Message Retention Time       |Successful Message       |180Days         ||

  Scenario Outline: BR_2202_53 Manage Customizations App	Validate Delete Actions are triggered when a 2 or more Customizations are deleted from the table - change Log page
    Given Click on Add button
    And Select Scenario: value <Scenario>
    And Select Parameter Key: value <parameter key>
    And Select Parameter Option: value <parameter option>
    And Select Parameter Value: value <parameter value>
    And Click on Save button
    And Click on Add button
    And Select Scenario: value <Scenario2>
    And Select Parameter Key: value <parameter key2>
    And Select Parameter Option: value <parameter option2>
    And Select Parameter Value: value <parameter value2>
    And Click on Save button
    When Select record <Scenario>:<parameter key>:<parameter option>
    And Select record <Scenario2>:<parameter key2>:<parameter option2>
    And Click on Delete button
    And Click on Cancel button
    And Click on Change Log button
    Then CheckNo Delete record <Scenario>:<parameter key>:<parameter option> :value <changedValue1> and timeStamp
    Then CheckNo Delete record <Scenario2>:<parameter key2>:<parameter option2> :value <changedValue2> and timeStamp
    Examples:
      |Scenario                           |parameter key     |parameter option         |parameter value|Scenario2        |parameter key2     |parameter option2         |parameter value2 |changedValue1|changedValue2|
      |Regulatory Collaboration for Brazil|CNPJ Determination|Declarant for Activation |SrcOPGLN       |Generic Reporting|Has Cloud Connector|                          |True            |SrcLocGLN           |False  |
