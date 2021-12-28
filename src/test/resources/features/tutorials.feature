Feature: Manage tutorials
  As an user
  I want to do operations with tutorials like creation, deletion, etc
  To expose the tutorials


  Scenario: Search a tutorial by id OK

    When I search a tutorial with id 1
    Then I should see the status code is OK with status code 200
    And I should see the tutorial information


  Scenario: Create tutorial OK
    When I create a tutorial
      | title                  | description                      | published |
      | Post desde Screen Rest | This is a automation api testing | true      |
    Then I should see the status code is OK with status code 201
    And I should see the tutorial information