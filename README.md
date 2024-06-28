Thank you for the detailed information. Based on what you've provided, here's a structured approach to create your README file:

### README for CPRMS System by CCL

#### Overview
The CPRMS (Claims Processing and Reimbursement Management System) is a web-based application developed for CCL to manage health insurance claims for present and past employees.

#### Features
- **User Management**: Maintain user profiles and credentials.
- **Application Processing**: Handle applications from reception to final approval.
- **Role-based Access**: Different roles (pharmacists, accountants, CMO, CMS, data entry operators, super user) with specific permissions.
- **OTP Authentication**: Secure access using one-time passwords.
- **SMS Integration**: Twilio integration for sending SMS notifications.
- **Database Management**: MySQL used for storing application data and user information.

#### Technologies Used
- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java, Servlets
- **SMS Messaging**: Twilio API
- **Database**: MySQL

#### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo.git
   cd your-repo
   ```
2. Set up MySQL database and configure `application.properties`.

#### Usage
1. Build and run the project using your preferred IDE or build tool.
2. Access the application via the provided URL.
3. Use different roles to navigate through the application:
    - **Pharmacist**: Handle assigned applications.
    - **Accountant**: Review applications post-pharmacist approval.
    - **CMO, CMS**: Review and approve applications.
    - **Data Entry Operator**: Manage application data.
    - **Super User**: Access and manage all application types.

#### Deployment
- Deploy the application on a web server capable of running Java Servlets and MySQL.

#### Contributing
- Contributions are welcome! Fork the repository, make your changes, and submit a pull request.

#### License
- There is no license, i made it and i don't know how to file copyright yet, soo.
- If you are able to make it work o your system and host the website. Use it!

#### Additional Information
- For troubleshooting or additional information, contact dcompany2004@gmail.com