@accepted
Feature: Get body of a file

  As a user
  I want to get a body of a file
  So i can download it

  Background:
    Given "John" is an authenticated user
    And the following documents alread exist in the System
      |Id | Name     |  DocumentDate   | UploadDate   | File         | UploadPerson |
      |0  | image1   |   01/05/14      |  02/03/14    | foo.jpg      | Lucy         |
      |1  | image2   |   01/03/10      |  02/03/14    | aaas.usg     | Adam         |
      |2  | image3   |   01/06/12      |  02/03/14    | foo.jpg      | Mary         |

  Scenario: Success
    When "John" gets content of file "1"
    Then "John" will get "aaas.usg" file


  Scenario: Not Found
    When "John" gets content of file "6"
    Then "John" will receive feedback that file does not exists
