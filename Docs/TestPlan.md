# Test Plan

**Author**: Team 059

## 1 Testing Strategy

### 1.1 Overall strategy

The test strategy consists of multiple test levels. <br>
1. When possible, certain units are tested with unit testing. These are self-contained blocks. An example if the function
to evaluate the score of an offer. Junit4 is used for unit level tests
2. Integration tests: There are a set of simple integration tests that are run, for every integration. These are simple tests that validate that base functionality is preserved between integrations
3. System level tests: These are the bulk of the testing done for the app. The system level tests is run in three different modes.
   1. Emulator android tests on Pixel 4 XL at API level 30
   2. Emulator Android tests on Pixel 4a at API level 30
   3. Tests on real device (Samsung Galaxy A51)

The following is the split of work for the test plan development
1. The test cases for black box testing is developed by Kamal Koshy 
2. White box testing and coverage - Joshua Carpenter and Edson Philippe

### 1.2 Test Selection

The primary method of testing is with black box testing to test the different features in the requirements.
The selection of the test cases is primarily driven by the use cases that are described in the Use case documents.
For the different use cases and requirements, different test cases are developed using Android Espresso. The tests are developed to
test the different states of the application, and to test the key interactions. In some cases, visual and manual testing is done, but the primary
mechanism of testing was using Android Espresso and automating the test cases.

### 1.3 Adequacy Criterion

We tested the application thoroughly by making sure that we tested all of our use cases, and we made use of unit and system tests to ensure that our code pieces were performing as expected.

### 1.4 Bug Tracking

Bug tracking is done using the capabilities of github to track issues. The issues are also assigned to developer
for fixes. In addition, the developer runs the test before release to validate that the new changes have not made any new bugs.
The bugs were also discussed periodically with the developer and Teams communication was used to keep track of bugs and evaluate bug fixes.

### 1.5 Technology

Two different technologies are used for testing. For unit testing Junit4 is used to build the test cases. For system level tests, Android espresso library is used.
Using Espresso, the test cases are automated and tested using specific IDs within the app.

## 2 Test Cases

<table>
  <tr>
   <td>Test Case
   </td>
   <td>Purpose
   </td>
   <td>Test details
   </td>
   <td>Expected Result
   </td>
   <td>Current Status
   </td>
  </tr>
  <tr>
   <td>1. Validate fields in application screen to enter current job details and save to database works
   </td>
   <td>Test that all the fields are present in the job offer screen. Check that save button works
   </td>
   <td>
<ol>

<li>Start the app in initial state.

<li>Add a job offer

<li>Click save

<li>Check whether fields are present in database.
</li>
</ol>
   </td>
   <td>
Job is visible in database after saving
   </td>
   <td>Pass
   </td>
  </tr>
  <tr>
   <td>2. Check whether current job offer cancel button works
   </td>
   <td>Check that cancel button works
   </td>
   <td>
<ol>

<li>Start the app in initial state.
<li>Add a job offer
<li>Click cancel
<li>Check that Job is not present in database.
</ol>
   </td>
   <td>
Check that no job offer is saved in database
   </td>
   <td>Pass
   </td>
  </tr>
  <tr>
   <td>2a. Validate field error checks are present for current job details
   </td>
   <td>Test for fields that are out of range
   </td>
   <td>
<ol>

<li>Start the app in initial state.
<li>Add a job offer with invalid fields (example, index, salary, bonus, benefits etc)
<li>Click save
<li>Check that error conditions are shown
</ol>
   </td>
   <td>
Error condition is shown in app
   </td>
   <td>Pass (manual check)
   </td>
  </tr>
  <tr>
   <td>3. Check current job offer with empty fields and saving does not exit the app
   </td>
   <td>Check that app prevents crash with empty fields
   </td>
   <td>
<ol>

<li>Start the app in initial state.
<li>Put in empty fields
<li>Click save
</ol>
   </td>
   <td>
Check that app does not crash
   </td>
   <td>Pass
   </td>
  </tr>
  <tr>
   <td>4. Validate fields in application screen to enter new job offer details and save to database works
   </td>
   <td>Test that all the fields are present in the job offer screen. Check that save button works
   </td>
   <td>
<ol>

<li>Start the app in initial state.
<li>Add a job offer
<li>Click save
<li>Check whether fields are present in database.
</ol>
   </td>
   <td>
Job is visible in database after saving
   </td>
   <td>Pass
   </td>
  </tr>
  <tr>
   <td>5. Check whether New job offer cancel button works</td>
   <td>Check that cancel button works</td>
   <td>
      <ol>
         <li>Start the app in initial state.
         <li>Add a job offer
         <li>Click cancel
         <li>Check that job offer is not saved
      </ol>
   </td>
   <td>
      Check that no job offer is saved.
      Check that next screen is main menu.
   </td>
   <td>Pass
   </td>
  </tr>
  <tr>
   <td>6. Check new job offer with empty fields does not crash application</td>
   <td>Check that app is stable from crashes</td>
   <td>
      <ol>
         <li>Get to job offer menu
         <li>Add no fields
         <li>Click “Save”
      </ol>
   </td>
   <td>Check that application does not crash</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>7. Check that new job offer with empty fields and clicking “Enter Another” does not crash application</td>
   <td>Check that app is stable from crashes</td>
   <td>
      <ol>
         <li>Get to job offer menu
         <li>Add no fields
         <li>Click “Enter Another”
      </ol>
   </td>
   <td>Check that application does not crash</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>8. Check that new job offer with empty fields and clicking “Save and Compare” does not crash application</td>
   <td>Check that app is stable from crashes</td>
   <td>
      <ol>
         <li>Get to job offer menu
         <li>Add no fields
         <li>Click “Save and Another”
      </ol>
   </td>
   <td>Check that application does not crash</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>9. Check that current job saved is visible when clicked again</td>
   <td>Check that the application pulls the current job from database</td>
   <td>
      <ol>
         <li>Get to current job offer
         <li>Add fields
         <li>Click “Save”
         <li>Go back to edit current job
      </ol>
   </td>
   <td>Check that current job details are visible</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>10. Check that current job saved is editable when clicked again</td>
   <td>Check that the application pulls the current job from database</td>
   <td>
      <ol>
         <li>Get to current job offer
         <li>Add fields
         <li>Click “Save”
         <li>Go back to edit current job
         <li>Enter new details and “Save”
      </ol>
   </td>
   <td>Check that current job details are saved in database</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>11. Check “Compare Settings” save without any inputs does not crash application</td>
   <td>Check that application is stable from crashes</td>
   <td>
      <ol>
         <li>Get to compare settings menu
         <li>Click save without any inputs
      </ol>
   </td>
   <td>Check that application does not crash</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>12. Check that the default compare settings are equal and greater than 0</td>
   <td>Check that the default compare is equal</td>
   <td>
      <ol>
         <li>Get to compare settings menu
         <li>Check that compare settings are equal
      </ol>
   </td>
   <td>Check default compare settings are equal.</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>13. Check that compare settings are saved</td>
   <td>Check that compare savings are saved correctly</td>
   <td>
      <ol>
         <li>Select compare settings from main menu
         <li>Change compare settings
         <li>Save compare settings
         <li>Read compare settings and make sure correct settings are stored
      </ol>
   </td>
   <td>Compare settings reflect correct value</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>14. Check compare settings when negative settings is given is not saved</td>
   <td>Check that compare settings is not saved with negative settings</td>
   <td>
      <ol>
         <li>Select compare settings from main menu in initial state
         <li>Check that if any negative value is there it is not saved
      </ol>
   </td>
   <td>Settings value is database is not changed when original value is given</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>15. Check that compare settings when all values are 0 is not saved.</td>
   <td>Check that right validation is done on compare settings</td>
   <td>
      <ol>
         <li>Select compare settings from main menu in initial state
         <li>Check that if all values are 0 it is not saved.
      </ol>
   </td>
   <td>Settings value in the database is not changed when zero values is given.</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>16. Check calculation of adjusted bonus</td>
   <td>Validate the calculation of adjusted bonus</td>
   <td>
      <ol>
         <li>Set the values of Job and compare settings
         <li>Check the value of adjusted bonus to manually calculated value
      </ol>
   </td>
   <td>The value of adjusted bonus matches manually calculated value</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>17. Check calculation of adjusted salary</td>
   <td>Validate the calculation of adjusted salary</td>
   <td>
      <ol>
         <li>Set the values of Job and compare settings
         <li>Check the value of adjusted salary to manually calculated value
      </ol>
   </td>
   <td>The value of adjusted salary matches manually calculated value</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>18. Check calculation of score</td>
   <td>Validate calculation of score</td>
   <td>
      <ol>
         <li>Set the values of Job and compare settings
         <li>Check the value of score to alternate implementation.
      </ol>
   </td>
   <td>Check for score matches alternate implementation</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>19. Check selection and display of job comparison</td>
   <td>Validate end to end flow</td>
   <td>
      <ol>
         <li>Set the values of Jobs and compare settings
         <li>Select two jobs to compare
      </ol>
   </td>
   <td>Check the job fields and sorting matches expected sorting and values</td>
   <td>Pass</td>
  </tr>
  <tr>
   <td>20. Check sorting of job offers works correctly</td>
   <td>Check that job offers are sorted correctly</td>
   <td>
      <ol>
         <li>Add jobs including current job
         <li>Set compare values
         <li>Check that jobs are sorted correctly
      </ol>
   </td>
   <td>Check sorting works correctly (Manual)</td>
   <td>Pass</td>
  </tr>
</table>

### **Unit Tests**

<table>
   <tr>
      <td>Test Case</td>
      <td>Purpose</td>
      <td>Test details</td>
      <td>Expected Result</td>
      <td>Current Status</td>
   </tr>
   <tr>
      <td>1. Test Job getters</td>
      <td>Check whether Job getter methods are working correctly</td>
      <td>
         <ol>
            <li>Create a Job
            <li>Use Job getter methods and validate they return correct values
         </ol>
      </td>
      <td>All getter methods return correct values.</td>
      <td>Pass</td>
   </tr>
   <tr>
      <td>2. Test Job setters</td>
      <td>Check whether Job setter methods are working correctly</td>
      <td>
         <ol>
            <li>Create a Job
            <li>Use Job setter methods to change Job fields and validate the fields have been changed
         </ol>
      </td>
      <td>All setter methods appropriately change the Job fields</td>
      <td>Pass</td>
   </tr>
   <tr>
      <td>3. Test validate benefits</td>
      <td>Check whether the benefits validation works correctly</td>
      <td>
         <ol>
            <li>Create a Job
            <li>Set benefits field to 0. Verify.
            <li>Set benefits field to 50. Verify.
            <li>Set benefits field to 100. Verify.
            <li>Set benefits field to -1. Verify.
            <li>Set benefits field to 101. Verify.
         </ol>
      </td>
      <td>The benefits field is validate for values of 0, 50, and 100, but not -1 or 101</td>
      <td>Pass</td>
   </tr>
   <tr>
      <td>4. Test validate fund</td>
      <td>Check whether the fund validation works correctly</td>
      <td>
         <ol>
            <li>Create a Job
            <li>Set fund field to 0.00. Verify
            <li>Set fund field to 9000.00. Verify.
            <li>Set fund field to 18000.00. Verify.
            <li>Set fund field to -0.01. Verify.
            <li>Set fund field to 18000.01. Verify.
         </ol>
      </td>
      <td>The fund field is validated for values of 0.00, 9000.00, and 18000.00, but not -0.01 and 18000.01</td>
      <td>Pass</td>
   </tr>
   <tr>
      <td>5. Test validate cost of living</td>
      <td>Check whether the cost of living validation works correctly</td>
      <td>
         <ol>
            <li>Create a Job
            <li>Set costOfLiving field to -1. Verify.
            <li>Set costOfLiving field to 0. Verify.
            <li>Set costOfLiving field to 1. Verify.
         </ol>
      </td>
      <td>The costOfLiving field is validated for a value of 1 but not 0 or -1</td>
      <td>Pass</td>
   </tr>
   <tr>
      <td>6. Test Setting getters</td>
      <td>Check whether Setting getter methods are working correctly</td>
      <td>
         <ol>
            <li>Create a Setting
            <li>Use Setting getter methods and validate they return correct values
         </ol>
      </td>
      <td>All getter methods return correct values.</td>
      <td>Pass</td>
   </tr>
   <tr>
      <td>7. Test Setting setters</td>
      <td>Check whether Setting setter methods are working correctly</td>
      <td>
         <ol>
            <li>Create a Setting
            <li>Use Setting setter methods to change Setting fields and validate the fields have been changed
         </ol>
      </td>
      <td>All setter methods appropriately change the Setting fields</td>
      <td>Pass</td>
   </tr>
</table>

### **Test Results**
![UML Class diagram](../images/passing_tests.png 'Main UI Page')