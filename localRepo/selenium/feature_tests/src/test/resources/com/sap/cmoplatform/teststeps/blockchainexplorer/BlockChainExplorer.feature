Feature: Verify Block chain explorer App

  Background:
    Given the user logs in as 'USSC_Manufacturer' in the login page
    And the BlockchainExplorer app is clicked in the USSC launcher page

  Scenario Outline: USSC_19,USSC_19.1, USSC_19.3 - Verify title of blockchain explorer app and Recorded lot and event details
    Then the title of the page is '<Title>'
    Then the available network header '<AvailableNetworkHeader>' is displayed
    Then the number of available network is '<AvailableNetworkCount>'
    And click on available network '<ushcVerification>'
    Then the recorded lots header '<RecordedLotsHeader>' is displayed
    Then the number of epc count '<RecordedLot>' of is '<NumberOfEpcs>'
    And click on recorded LOT '<RecordedLot>'
    Then the recorded epc header '<RecordedEpcsHeader>' is displayed
    And click on recorded Epcs
    Then the recorded events header '<RecordedEventsHeader>' is displayed
    Then the recorded events contains event details
      | SenderGln   | <SenderGln>   |
      | Disposition | <Disposition> |
      | EventTime   | <EventTime>   |
      | ActionEvent | <ActionEvent> |
    Examples:
      | Title               | AvailableNetworkHeader | RecordedLotsHeader | RecordedEpcsHeader | RecordedEventsHeader | ushcVerification                | RecordedLot                                | NumberOfEpcs | ActionEvent | SenderGln     | Disposition     | EventTime                   | AvailableNetworkCount |
      | Blockchain Explorer | Available Networks     | Recorded Lots      | Recorded EPCs      | Recorded Events      | ushc-verification-2022 (native) | Lot Hash: 65b228f82d7f42b522912dfe73986e12 | 3            | 0           | 0363391100002 | inactivetesttwo | 2020-02-26T12:39:06.515506Z | 6                     |

