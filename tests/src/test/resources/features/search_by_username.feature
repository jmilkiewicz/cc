@accepted
Feature: Search docs by username

  As a user
  I want to search files uploaded by given user
  So that it allows me to easily found files uploaded by given user

  Background:
    Given "John" is an authenticated user
    And the following documents alread exist in the System
      |Id |  Name    |  DocumentDate   | UploadDate   | File         | UploadPerson |
      |0  | image1   |   01/01/11      |  02/03/14    | foo.jpg      | Lucy         |
      |1  | image2   |   02/05/11      |  03/06/14    | bar.jpd      | Mary         |
      |2  | image4   |   03/04/11      |  11/02/12    | xyxx.co      | John         |
      |3  | image5   |   02/05/13      |  12/03/09    | aaa.doc      | Lucy         |

  Scenario: Documents found
    When "John" goes to files uploaded by "Lucy"
    Then "John" will see following document list entries:
      |Id |  Name      |  DocumentDate   |  UploadPerson |
      |0  | image1     |   01/01/11      |  Lucy         |
      |3  | image5     |   02/05/13      |  Lucy         |

  Scenario: Dispaly success message
    Given "my message" is a pending message
    When "John" goes to files uploaded by "Lucy"
    Then "John" will see the "my message" message