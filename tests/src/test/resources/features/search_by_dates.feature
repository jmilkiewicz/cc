@accepted
Feature: Search docs by dates

  As a user
  I want to search files uploaded by dates
  So that it allows me to easily found files uploaded in a given period

  Background:
    Given "John" is an authenticated user
    And the following documents alread exist in the System
      |Id | Name     |  DocumentDate   | UploadDate   | File         | UploadPerson |
      |0  | image1   |   01/01/11      |  02/03/14    | foo.jpg      | Lucy         |
      |1  | image2   |   02/05/11      |  03/06/14    | bar.jpd      | Mary         |
      |2  | image4   |   03/04/11      |  11/02/12    | xyxx.co      | John         |
      |3  | image5   |   02/05/13      |  12/03/09    | aaa.doc      | Lucy         |

  # NO Scenarios as the test are not needed for this code challenge ?

