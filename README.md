TEAM:
https://github.com/DungLe2911
https://github.com/qma16443
https://github.com/OhMyHiep

*The project is incomplete, and largely focuses on the backend so there is currently no functional UI.

# HRProject Requirements
---
## For Employee
1. Login
    - Employee can login with their username or email
    - Employee can only register an account wit the registration token which is created by HR (See HR section for more detail)
2. Onboarding
    - Force user to set up the username and password after user register their account by using the registration token
    - After setting up the username and password, user will be redirected to onboarding page
      - Application Form: Users need to fill out the application form with all following information (note: field with * means it is a mandatory field)
        1. First Name(\*), Last Name(\*), Middle Name, Preferred Name
        2. Current Address(\*)
        3. Cellphone, WorkPhone
        4. Email(prefilled, since user already registered the account with this email address)
        5. SSN (*)
        6. DOB (*)
        7. Gender (Drop down: Male, Female, I don't want to answer)
        8. "Are you a citizen or permanent resident of the U.S?" (\*)
           - If yes, choose either “Green Card” or “Citizen” (*)
           - If no, “What is your work authorization?” (*, Dropdown: H1-B, L2, F1(CPT/OPT), H4, Other)
           - If others, input box for specifying the work authorization, start date and expiration date
           - Otherwise, start date and end date.
        9. Do you have a drive license? (\*)
           - If yes, drive license number, expiration date and upload a copy of drive license
        10. Reference (Who recommends you come here; the reference can only be one person with following filed)
            - First name, Last name, Middle name, Phone, Email, Relationship
    - After user completes the application form, the user will be redirected to the documentation page, which lists all documents in DigitalDocument table.
      - For each document, provide a download link so that user can download the file after
        clicking on.
      - User should also be able to upload the document on this page after they completed and
        signed the document on this page.
    - After user submit the documents, user should be able to submit the entire onboarding application.
      - Once submitted, user should be present a page with “Please wait for HR to review your
        application”
      - User can go to their home page if and only if the HR approved his/her application.
      - If the application is rejected, user should be able to receive an email. Users should be
        able login to the system to check which field is wrong or if there is any missing
        document.
3. Home Page
   - Once user’s onboarding application has been approved, user should be able to see the
     home page after they login
4. Personal Information
   - User should be able to view and update their own personal information on this page.
5. Visa Status Management
   - User should be able to manage their work authorization on this page. Currently, only need to provide this visa status management for the users on OPT status.
     - International students have to use OPT/OPT STEM to work in United States. During the
        onboarding process, it is required to provide at least the OPT Receipt. The OPT status
        changes are listed below:
        1. OPT Receipt (Applied, but don’t receive the OPT EAD yet)
        2. OPT EAD (Received the OPT EAD)
        3. I-983 (Need to be filled for OPT STEM)
        4. I-20 (After submitting the I-983 to the school, the student will receive a new I-20)
           OPT STEM Receipt (Applied for OPT STEM, but haven’t received the OPT STEM
           EAD)
        5. OPT STEM EAD (Received the OPT EAD)
   - Status Notification
     - When user go to this page, the user should be able to see a message about what they
       need to do next if their status meets any of following rules:
        1. If the user currently holds OPT Receipt or OPT STEM Receipt, they should be able to
           see next step as “Please upload a copy of your OPT EAD” or “Please upload a copy
           of your OPT STEM EAD” respectively. 
           - After the message, there should be a button for user to upload the document. 
        2. If the user currently holds OPT EAD and there are less than 100 days before the expiration date of OPT EAD, they will see next step as “Please download and fill your I-983 form”.
        3. If the user is currently waiting for HR to sign the I-983, they will see next step as “Waiting for HR to approve and sign I-983”. If the HR upload the signed I-983, they should be able to see next step as “Please send the I-983 with all necessary documents to your school and upload the new I-20”.
           - After the message, the user should be able to see a button to upload the new I-20
        4. If the user has uploaded the new I-20, they will see the next step as “Please upload your OPT STEM Receipt”
           - After the message, the user should be able to see a button to upload the OPT STEM Receipt
        5. If the user has uploaded the OPT STEM Receipt, they should be able to see the next step as “Please upload your OPT STEM EAD”
           - After the message, the user should be able to see a button to upload the OPT STEM EAD
   - A List of Documents the user has uploaded shall be displayed.
     - User should be able to view and download all documents he/she has uploaded
     - When user click on it, they should be able to download the file.
6. Housing
   - Employees can only view the details about the house but they cannot change the house
     has been assigned to them.
   - House Detail Page
     - Employees should be able to view the following house detail
        1. Address
        2. List of employees who lives in the house with following details
           - Name (Preferred Name, if it is empty, then the First Name)
           - Phone
   - Facility Reporting Page
     - Employees should be able to report a facility issue in the house and see all comments
       by employees or HR
       1. This page will show a list of Facility Reports with following details
          - Title
          - Report Date
          - Status (There are 3 statuses: Open, In Progress, Closed)
       2. A single facility report contains with following details
          - Title
          - Description
          - Created By (Who report this issue)
          - Report Date
          - Status (There are 3 statuses: Open, In Progress, Closed)
          - A list of comment with following details
          
              . Description
              
              . Created By
              
              . Comment Date (If the last modification date is empty, display the created date;
            otherwise, display the last modification date)

       3. For each report, employees can add comments or update comments which is
          created by the employee.
--- 
## For HR
1. Login
    - Both employee and HR should have the same login portal. The system should be able to
      check whether the login user is a HR or an employee
    - Please hard code at least one account with Role as HR
    - Note: An HR is also an employee. The difference is that HR has one more role called HR
2. HomePage
    - After HR login, they will be redirected to the home page shown a Status Tracking Table:
      - The table should display all active visa status of each employee with pagination.
      - This table should have following details
        1. Name (Legal full name)
        2. Work Authorization (F1/OPT, F1/OPT STEM)
        3. Expiration Date
        4. Day Left
3. Employee Profile
    - HR should be able to view all employee profiles
    - Pagination is required
    - HR can filter employee profile base on:
      - First Name
      - Email
4. Hire
    - The HR should be able to create the registration token and review onboarding application
      or other application such as OPT STEM application
    - Registration Token Generation
      - By entering the new hire’s email, the HR should have a button “Generate Token and
        Send Email” to send the token to the email address provided.
        1. The default valid duration should be set through the property file and the value should
           be 3 hours
    - Application Review
      - HR should be able to review employee’s onboarding application or other applications
        (Just consider the onboarding application and OPT STEM application for now):
        1. Form Application
           - HR should be able to view the same form for the onboarding application with
             following rules
           - All Fields are not editable
           - All fields are populated with user entered data
           - HR should be able to add comment for entire application
        2. Documentation Application
        3. Each Employee can only have one ongoing application. (The application status is not
           completed)
           - Application Status: Open, Pending, Rejected, Completed 
        4. HR should be able to approve or reject the application
           - Once approved, the application status should be changed to Completed.
           - If rejected, there should be comments about what is wrong. Then the employee
             has to fix those errors or upload other documents
           - Either approved or reject, the employee should be able to receive an email about
             the application.
        5. When HR go to this page, she/he should be able to view all ongoing applications.
           While clicking on each application, it should show the application details listed above.
5. House Management
   - HR should be able to view, add, delete the house property belonging to the company.
   - View
     - HR should be able to view all house are currently under management by the company
       with following details
       1. Address 
       2. Landlord (Legal Full Name)
       3. Phone 
       4. Email
       5. Max Occupant
     - HR should be able to click on each house record to view the details as follow
       1. House Information
          - Address
          - Landlord, Phone, Email
          - Number of employee currently live in this house
            1. Facility Information
               - Number of Beds
               - Number of Mattress
               - Number of Tables
               - Number of Chairs
               - Facility report (List View)
            
                 • Display all facility reports with (Title + Date + Status) format in a table
      
                 • Each page should only display 3 - 5 reports
          
                 • All reports are sorted by the created date in descending order
     
                 • Once clicked, display a facility detailed page with all following details:
                   - Title
                   - Description
                   - Created By (i.e.,who reported this issue)
                   - Report Date
                   - Status
                   - A list of FacilityReportDetail
                 
                 • For each report, HR can add comments or update comments which is created
                 by the HR.
       2. Employee Information (List View)
          - Name (Preferred Name, if null, then First Name)
          - Phone
          - Email
          - If clicked on each row, redirect to Employee profile page of the selected employee.
