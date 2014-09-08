@accepted
Feature: Get metadata of a single file

  As a user
  I want to get a metadata of a single file
  So that i can reason about it

  Background:
    Given "John" is an authenticated user
    And the following documents alread exist in the System
      |Id | Name     |  DocumentDate   | UploadDate   | File         | UploadPerson |
      |0  | image1   |   01/05/14      |  02/03/14    | foo.jpg      | Lucy         |
      |2  | image2   |   01/03/10      |  02/03/14    | fff.jpg      | John         |
      |3  | image3   |   01/06/12      |  02/03/14    | foo.jpg      | Mary         |

  Scenario: Success
    When "John" comes to details of file "0"
    Then "John" will see the following file meta data
      |Id | Name     |  DocumentDate   | UploadDate   | UploadPerson |
      |0  | image1   |   01/05/14      |  02/03/14    | Lucy         |


  Scenario: Not Found
    When "John" comes to details of file "6"
    Then "John" will receive feedback that file does not exists
