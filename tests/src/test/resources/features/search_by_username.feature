@accepted
Feature: Search docs by username

  As a user
  I want to search files uploaded by given user
  So that it allows me to easily found files uploaded by given user

  Background:
    Given "John" is an authenticated user
    And the following documents alread exist in the System
      |Id | FileName    |  DocumentDate   | Content       | UploadPerson |
      |0  | file1.txt   |   01/01/11      | file1Content  | Lucy         |
      |1  | file2.txt   |   02/05/11      | file2Content  | Mary         |
      |2  | file3.txt   |   03/04/11      | file3Content  | John         |
      |3  | file5.txt   |   02/05/13      | file5Content  | Lucy         |

  Scenario: Documents found
    #TODO czy to dobra nazwa
    When "John" goes to documents of "Lucy"
    #Then "John" will see following documents:
    # |Id | FileName    |  DocumentDate   | Content       | UploadPerson |
    # |1  | file1.txt   |   01/01/11      | file1Content  | Lucy         |
    # |4  | file5.txt   |   02/05/13      | file5Content  | Lucy         |
    Then "John" will see documents with following ids: "0,3"

  Scenario: Dispaly success message
    #TODO ale chujowa name
    Given "my message" message has been appended
    When "John" goes to documents of "Lucy"
    Then "John" will see the "my message" message