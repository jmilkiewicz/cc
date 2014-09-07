@accepted
Feature: Search docs by dates

  As a user
  I want to search files uploaded by dates
  So that it allows me to easily found files uploaded in a given period

  Background:
    Given "John" is an authenticated user
    And the following documents alread exist in the System
      |Id | FileName    |  DocumentDate   | Content       | UploadPerson |
      |0  | file1.txt   |   01/01/11      | file1Content  | Lucy         |
      |1  | file2.txt   |   02/05/11      | file2Content  | Mary         |
      |2  | file3.txt   |   03/04/11      | file3Content  | John         |
      |3  | file5.txt   |   02/05/13      | file5Content  | Lucy         |

  # NO Scenarios as the test are not needed for this code challenge ?

