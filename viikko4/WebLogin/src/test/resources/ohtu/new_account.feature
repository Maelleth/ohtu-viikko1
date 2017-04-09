Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation successful with correct username and password
    Given new user is selected
    When new username "geralt" and password "witcher1" are given
    Then user is created

  Scenario: creation fails with too short username and valid passord
    Given new user is selected
    When new username "ger" and password "witcher1" are given
    Then user is not created and error "username should have at least 3 characters" is reported

  Scenario: creation fails with correct username and too short password
    Given new user is selected
    When new username "geralt" and password "witch" are given
    Then user is not created and error "password should have at least 8 characters" is reported

  Scenario: creation fails with correct username and password consisting of letters
    Given new user is selected
    When new username "geralt" and password "lululul" are given
    Then user is not created and error "password can not contain only letters" is reported

  Scenario: creation fails with already taken username and valid pasword
    Given new user is selected
    When new username "alistair" and password "dragonage123" are given
    Then user is not created and error "username is already taken" is reported

  Scenario: creation fails when password and password confirmation do not match
    Given new user is selected
    When new username "geralt" and password "witcher1" and passord confirmation "witcher1234" are given
    Then user is not created and error "password and password confirmation do not match" is reported

  Scenario: user can login with successfully generated account
    Given new user is selected
    And user with username "geralt" with password "witcher1" is successfully created
    And login is selected
    When correct username "geralt" and password "witcher1" are given
    Then user is not logged in and error message is given

  Scenario: user can not login with account that is not successfully created
    Given new user is selected
    And user with username "lol" and password "wut" is unsuccessfully created
    And login is selected
    When correct username "lol" and incorrect password "wut" are given
    Then user is not logged in and error message is given
