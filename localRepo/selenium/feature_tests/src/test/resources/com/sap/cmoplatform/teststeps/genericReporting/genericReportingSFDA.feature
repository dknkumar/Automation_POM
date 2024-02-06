@USSC @genericReportingSFDA
Feature: Generic Reporting SFDA Scenarios

  Background:
    Given the user logs in CPI
    And Monitor Message Processing app is displayed

  Scenario Outline: SFDA_PHN_17396_TC_05 Accept Notification Success
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status  |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | acceptSuccess.xml | SFDA     | acceptSuccessInput.xml | 5       | Success |

  @Sanity @Regression
  Scenario Outline: SFDA_PHN_17396_TC_11 Accept Notification without ReceiverID
    When enable trace logs for <Iflowname> I-flow
    Then Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    Examples:
      | Iflowname                             | inputFileName              | scenario | updatedInputFileName            |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | acceptwoReceiverID.xml     | SFDA     | acceptwoReceiverIDInput.xml     |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | deactivatewoReceiverID.xml | SFDA     | deactivatewoReceiverIDInput.xml |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | dispatchwoReceiverID.xml   | SFDA     | dispatchwoReceiverIDInput.xml   |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | importwoReceiverID.xml     | SFDA     | importwoReceiverIDInput.xml     |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | supplywoReceiverID.xml     | SFDA     | supplywoReceiverIDInput.xml     |

  Scenario Outline: Export Success
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status  |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | exportSuccess.xml | SFDA     | exportSuccessInput.xml | 5       | Success |

  Scenario Outline: SFDA_PHN_17395_TC_03 Import Notification Success
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status              |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | importSuccess.xml | SFDA     | importSuccessInput.xml | 1       | importFailure_21017 |

  Scenario Outline: PTS Upload Success
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                               | stepName      | inputFileName        | scenario | updatedInputFileName      | segment | status  |
      | ICH_DataExchange_GR_PTS_Upload_2305_0_0 | ProcessDirect | PTSuploadSuccess.xml | SFDA     | PTSuploadSuccessInput.xml | 5       | Success |

  Scenario Outline: SFDA_PHN_17394_TC_01_Supply Notification Success
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status        |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | supplySuccess.xml | SFDA     | supplySuccessInput.xml | 5       | supplySuccess |

  Scenario Outline: SFDA_PHN_17397_TC_07_Deactivate Notification Success
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName         | scenario | updatedInputFileName       | segment | status            |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | deactivateSuccess.xml | SFDA     | deactivateSuccessInput.xml | 5       | deactivateFailure |

  Scenario Outline: SFDA_PHN_17397_TC_08_Deactivate Notification Failure
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName         | scenario | updatedInputFileName       | segment | status            |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | deactivateFailure.xml | SFDA     | deactivateFailureInput.xml | 5       | deactivateFailure |

  Scenario Outline: SFDA_PHN_17398_TC_09_Dispatch_Notification_Success
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName       | scenario | updatedInputFileName     | segment | status  |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | dispatchSuccess.xml | SFDA     | dispatchSuccessInput.xml | 5       | Success |

  Scenario Outline: SFDA_PHN_17396_TC_06 Accept Notification Failure
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | acceptFailure.xml | SFDA     | acceptFailureInput.xml | 5       | 11032  |

  Scenario Outline: Export Failure
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status              |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | exportFailure.xml | SFDA     | exportFailureInput.xml | 1       | exportFailure_11813 |

  Scenario Outline: SFDA_PHN_17395_TC_04 Import Notification Failure
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status              |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | importFailure.xml | SFDA     | importFailureInput.xml | 1       | importFailure_21017 |

  Scenario Outline: SFDA_PHN_17394_TC_02 Supply Notification Failure
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName     | scenario | updatedInputFileName   | segment | status |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | supplyFailure.xml | SFDA     | supplyFailureInput.xml | 1       | 11004  |

  Scenario Outline: SFDA_PHN_17398_TC_10 Dispatch Notification Failure
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName       | scenario | updatedInputFileName     | segment | status |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | dispatchFailure.xml | SFDA     | dispatchFailureInput.xml | 1       | 11032  |

  Scenario Outline: SFDA_TC_12_AcceptMulti
    When enable trace logs for <Iflowname> I-flow
    And Trigger <scenario> SOAP req and Validate status for <inputFileName> <updatedInputFileName>
    And Validate payload from <Iflowname> and <stepName> <segment> for <status>
    Examples:
      | Iflowname                             | stepName      | inputFileName          | scenario | updatedInputFileName        | segment | status  |
      | ICH_DataExchange_SFDA_GR_JMS_2305_0_0 | ProcessDirect | acceptSuccessmulti.xml | SFDA     | acceptSuccessmultiInput.xml | 5       | Success |
