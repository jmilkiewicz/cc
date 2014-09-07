@accepted
Feature: Search docs by username

  As a user
  I want to search files uploaded by given user
  So that it allows me to easily found files uploaded by given user

  Background:
    Given "John" is an authenticated user
    And the following documents alread exist in the System
      |Id | FileName |  DocumentDate   | UploadDate   | File         | UploadPerson |
      |0  | image1   |   01/01/11      |  02/03/14    | foo.jpg      | Lucy         |
      |1  | image2   |   02/05/11      |  03/06/14    | bar.jpd      | Mary         |
      |2  | image4   |   03/04/11      |  11/02/12    | xyxx.co      | John         |
      |3  | image5   |   02/05/13      |  12/03/09    | aaa.doc      | Lucy         |

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