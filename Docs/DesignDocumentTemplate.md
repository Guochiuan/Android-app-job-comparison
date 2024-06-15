# Design Document

**Author**: Team 059 <br>

**Versions**: 
1. Initial verison: 6/29/2022 - KK
2. 

## 1 Design Considerations
*The subsections below describe the issues that need to be addressed or resolved prior to or while completing the design, as well as issues that may influence the design process.*

*The following are the key elements that must be defined before the project is completed*
1. The requirement for data persistence: This is required to be defined, on whether data needs to persist across app closing and reopening.
If data needs to persist, at least a simple database is required. Update: Data will need to be stored between sessions.

### 1.1 Assumptions

*Describe any assumption, background, or dependencies of the software, its use, the operational environment, or significant project issues.* <br>

The design is based on the requirements that were provided. The system will have the following characteristics
1. Android app that can run with API 30 and Android 11.0
2. The app will be a multi-screen app that should persist between user sessions
3. There is no requirement to maintain state over multiple devices


### 1.2 Constraints

*Describe any constraints on the system that have a significant impact on the design of the system.*

There are no significant constraints on the design of the system other than the API level mentioned earlier that will restrict the devices the app can be run on..

### 1.3 System Environment

*Describe the hardware and software that the system must operate in and interact with.*
The app should be developed with a minimum API level of 30, and should be tested on three systems
1. Emulator with Pixel 4 XL with API 30
2. Emulator with ***
3. Real device (***)

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

### 2.1 Component Diagram

*This section should provide and describe a diagram that shows the various components and how they are connected. This diagram shows the logical/functional components of the system, where each component represents a cluster of related functionality. In the case of simple systems, where there is a single component, this diagram may be unnecessary; in these cases, simply state so and concisely state why.*
<br>

The app consists of a single monolithic software system for the application. The app runs on the provided mobile device. The only additional component is the database to store
the state of the application, and is handled by Android room libary.

### 2.2 Deployment Diagram

*This section should describe how the different components will be deployed on actual hardware devices. Similar to the previous subsection, this diagram may be unnecessary for simple systems; in these cases, simply state so and concisely state why.*
<br>

The application is deployed on the devices that run Android operating systems It does not require
additional components or secondary databases. All information in the application is stored within the device and does not require any additional support
from external components.

## 3 Low-Level Design

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*
The UML class diagram is shown below.

![UML Class diagram](../images/group_design.png 'UML diagram')

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details,
   (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).

    * The app startup and lifecycle is handled within the main() method in the App class, which responds to any user selections by using the GUI to
      display the appropriate page. The methods used by main() to handle the above options are as follows:
        * Enter or edit current job details: setCurrentJob()/editCurrentJob().
        * Enter job offer: enterJobOffer()
        * Adjust comparison settings - adjustComparisonSettings()
        * Compare jobs: compareJobs() and displayRankedOffers().

   If the 'jobList' field has any elements which have the isCurrentJob field set to false, then the user will be given the option
   to compare job offers; otherwise, the option will be disabled.

2. When choosing to enter current job details, a user will:

   a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:

           i. Title
          ii. Company
         iii. Location (entered as city and state)
          iv. Cost of living in the location (expressed as an index)
           v. Yearly salary
          vi. Yearly bonus
         vii. Retirement benefits (as percentage matched) (given as an integer from 0 to 100 inclusive)
        viii. Relocation stipend
          ix. Training and development fund ($0 to $18000 inclusive annually)

    * The details of the job are stored in the Job class. Each of the fields above are stored in the Job class (and validated to be within range if required).
      The user interface of entering or editing a job is handled by the GUI. When a job is selected for editing, the App class uses the editCurrentJob() method to
      edit the instance of the current Job.

   b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

    * This part is handled by the GUI when exiting and returning to the main menu. If a valid job offer is saved, both the currentJob instance as well as the instance
      of the current job that is stored in the jobList will be updated.

3. When choosing to enter job offers, a user will:

   a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.

    * The main() method works with the GUI to bring up the appropriate user interface.

   b. Be able to either save the job offer details or cancel.

    * If the user chooses to save the job offer details, a Job object will be created and the enterJobOffer() method
      will be called which will add the job to the 'jobList' array field. If the user cancels, the GUI will clear the input fields.

   c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

    * If the user enters another offer, they will be given an option by the GUI to save the details or cancel, as in the previous requirement.
      If the user chooses to return to the main menu, the GUI will display the appropriate main menu screen.
      If the user chooses to compare the offer, the compareJobs() method will be called with the saved offer and the 'currentJob' as parameters;
      however, if the user had not saved an offer or if there is not an existing 'currentJob', then the user will not be allowed to select this option.

4. When adjusting the comparison settings, the user can assign integer weights to:

   a. Yearly salary

   b. Yearly bonus

   c. Retirement benefits

   d. Relocation stipend

   e. Training and development fund

   If no weights are assigned, all factors are considered equal.

    * The comparison settings are stored using the adjustComparisonSettings() method, which edits or sets an instance of the JobComparison class.
      The class contains the different parameters entered by the user. Initially the JobComparison class is initialized to equal weights and is
      used if the user has not entered a comparison setting. In addition, validation of the weights is also done before storing.

5. When choosing to compare job offers, a user will:

   a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.

    * The main() method will call the displayRankedOffers() method which will work with the GUI to display the jobs in appropriate order.
      The jobs will be ordered by their scores, which are calculated by passing the jobs to the calculateScore() method in the JobComparison class,
      and the current job (as tracked by the currentJob field) will be clearly indicated visually.

   b. Select two jobs to compare and trigger the comparison.

    * The compareJobs() method will be called which will be passed the two jobs desired to be compared.

   c. Be shown a table comparing the two jobs, displaying, for each job:

          i. Title
          ii. Company
         iii. Location
          iv. Yearly salary adjusted for cost of living
           v. Yearly bonus adjusted for cost of living
          vi. Retirement benefits 
         vii. Relocation stipend
        viii. Training and development fund

    * The compareJobs() method will work with the GUI to display in a table the details of the jobs passed to it.

   d. Be offered to perform another comparison or go back to the main menu.

    * The GUI will provide the user with options to perform another comparison or return to the main menu, and display the appropriate page as chosen by the user.

6. When ranking jobs, a job’s score is computed as the weighted sum of:

   AYS + AYB + (RBP * AYS / 100) + RS + TDF

   where:
   AYS = yearly salary adjusted for cost of living
   AYB = yearly bonus adjusted for cost of living
   RBP = retirement benefits percentage
   RS = relocation stipend
   TDF = training and development fund

   For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:


    2/7 * AYS + 1/7 * AYB + 1/7 * (RBP * AYS / 100) + 2/7 * RS + 1/7 * TDF
     
    * The calculateScore() method in JobComparison uses the job's values along with the various weights (if assigned) to calculate the score.

7. The user interface must be intuitive and responsive.

    * This will be handled entirely by the GUI implementation.

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

    * This part simplifies the design by not requiring user login, although user state has to be stored between sessions.

### 3.1 Class Diagram

*In the case of an OO design, the internal structure of a software component would typically be expressed as a UML class diagram that represents the static class structure for the component and their relationships.*

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

