@accepted
Feature: Add asset for a brand

  As a user
  I want to add a new file
  So that it will be stored in system for future use

  Background:
    Given "John" is an authenticated user

  Scenario: Success
    Given now is "01/01/14"
    When "John" adds an asset:
      | FileName    |  DocumentDate   | File           |
      | Croatia2014 |   2011-01-01    |  sunset.jpg    |
    Then the following documents will exist in the System
      |Id | Name        |  DocumentDate   | UploadDate   |  File        | UploadPerson |
      |0  | Croatia2014 |   01/01/11      | 01/01/14     |sunset.jpg    | John         |
    And "John" will go to files uploaded by "John"
    And will receive a feedback that his operation was succesfull
