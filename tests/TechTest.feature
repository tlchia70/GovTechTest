@TechTest
Feature: Technical Test

  @TechTest
  @TestCaseId:TC_1
  @TagGroup:TC_Group_1
  Scenario Outline: Technical Test

    # Launch Website
    Given I Launch Website "${env.url}"
    Then Validate Webpage Loaded

    # Input First Name
    Then Fill in First Name : "<First_Name>"
    Then Validate First Name

    # Input Last Name
    Then Fill in Last Name : "<Last_Name>"
    Then Validate Last Name

    # Input Email
    Then Fill in Email : "<Email>"
    Then Validate Email

    # Input Gender
    Then I select the radio button with label "<Gender>" under "4. Gender"
    Then Web: Business Verification: I verify "Gender Selection Success"

    # Input Mobile Number
    Then Fill in Mobile Number : "<Mobile_Number>"
    Then Validate Mobile No

    # Input Date of Birth
    Then Fill in Date of Birth : "<Date_of_Birth>"
    Then Validate Date of Birth : "<Date_of_Birth>"

    # Input Hobbies
    Then Fill in Hobbies : "<Hobbies>"
    Then Web: Business Verification: I verify "Hobbies Success"

    # Upload support files
    Then I upload the file "<SupportingDoc_filename>"
    Then Web: Business Verification: I verify "File Upload Success"

    # Input Location
    Then Select Location : "<Location>"
    Then Web: Business Verification: I verify "Location Success"

    # Input Address
    Then Fill in Address : "<Address>"
    Then Web: Business Verification: I verify "Address Success"


    Then I click button with label "Submit now"
    Then I wait for "5" seconds
    Then I assert text present in page "Thank you for filling out the form."

    Then Web: Wait-For-Seconds Value:"3"
    Then I Close Web Browser

    Examples: {'datafile':'resources/data/TechTest_TestData.xlsx','sheetName':'TechTest'}
    #Examples: {'datafile':'resources/data/TechTest_TestData.xlsx','sheetName':'Positive'}


