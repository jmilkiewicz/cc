@accepted
Feature: Add asset for a brand

  As a user
  I want to add a new file
  So that it will be stored in system for future use

  Background:
    Given "John" is an authenticated user

  Scenario: Success
    When "John" adds an asset:
      | FileName    |  DocumentDate   | Content        |
      | file.txt    |   2011-01-01    |  someContent   |
    Then the following documents will exist in the System
      | FileName    |  DocumentDate   | Content       | UploadPerson |
      | file.txt    |   01/01/11      | someContent   | John         |
    And "John" will see his files
    And "John" will see a message indicating success
