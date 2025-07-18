## Rest Assured Book API  Framework

This project is a robust API automation framework designed to verify the key functionalities of a FastAPI-powered BookStore application. It offers end-to-end test coverage for:

🔁 CRUD operations (Create, Read, Update, Delete)

⚠️ Error and edge case handling

🔐 Authentication and security validations

With comprehensive reporting and smooth CI/CD integration, this framework ensures consistent, high-quality feedback throughout the development lifecycle.

---

###  Technologies & Framework Stack

Java (17): Primary language used to implement framework logic

RestAssured: For automating and validating RESTful API endpoints

TestNG: Manages test execution flow and configurations

ExtentReports: Produces rich, interactive HTML reports for test results

Maven: Handles project builds, dependencies, and lifecycle phases

GitHub Actions: Enables CI/CD for automated testing and deployments 

---

###  Scope of Test Coverage

The framework covers the following test scenarios for Health check, User, and Book APIs:

 ###  Expected Functional Outcomes
- Verify if the server is up and running

- Register a new user and log in with valid credentials

- Add a new book using valid input data

- Fetch the complete list of books

- Retrieve details of a specific book by its ID

- Modify the details of an existing book

- Remove a book from the system

### Negative Validations
- Access APIs with invalid or missing tokens
- Attempt to get non-existent books
- Sign-up/login with invalid credentials


## Request Chaining

The access token obtained from the login API is dynamically applied to subsequent requests—such as create, update, and delete book operations—facilitating smooth request chaining.

---

##  Test Strategy

 1. API Test Case Architecture
- Designed modular test classes for each core resource: User, Book, and Health

- Leveraged dependsOnGroups to handle inter-class dependencies (e.g., Book tests require a successful login)

- Implemented request chaining to support token-based authentication flows

- Utilized a centralized data.json file for input data, promoting cleaner and more maintainable test code



###  2.Scalability
- Configurable base URL and tokens using `config.properties`
- Clear separation of concerns: logic, request specs, data, reporting
- Assertions for status codes, response structure, and error messages
- Positive and negative test cases for all endpoints
- Common utility methods for validation
- Used `BaseTest` and `Listeners` to manage test lifecycle and reports

 ##  3. Challenges & Resolutions

| **Challenge**                                          | **Resolution**                                                                      |
| ------------------------------------------------------ | ----------------------------------------------------------------------------------- |
| API returned `500` instead of `422` for invalid inputs | Implemented payload validation and added assertions to identify backend misbehavior |
| API returned `400` for undocumented cases              | Recommended enhancing Swagger documentation for accuracy                            |
| Missing Delete API for User resource                   | Proposed adding a Delete endpoint or implementing user reuse strategies in tests    |
| Interdependent test classes causing execution issues   | Adopted `dependsOnGroups` to manage test flow more effectively                      |
| No backend validation for empty email or book name     | Highlighted the issue and suggested server-side input validation                    |


---

##  CI/CD Pipeline

###  Trigger:
- Runs on every push and pull request to the `main` branch

###  Steps in CI/CD Process:
1. Checkout code
2. Setup Java (Temurin 17)
3. Build and run tests via Maven
4. Upload test reports:
   - **Surefire Reports (TestNG)**
   - **ExtentReports (HTML)**
   - **JaCoCo Code Coverage Reports**

---

##  How to Run the Tests

###  Prerequisites
- Java 17+
- Maven 3.6+
- Git

###  Run Locally
```bash
git clone https://github.com/sujata1992/RestAssured.git
cd RestAssured
```

- Update `config.properties` with your base URL (e.g., `url=http://localhost:8000`)
- Run the test suite:
  ```bash
  mvn clean test
  ```
- View the report:
  Open the generated report at: `test-output/testReport.html`

---

---

##  How to View GitHub Actions Reports

1. Go to: [https://github.com/sujata1992/RestAssured/actions](https://github.com/sujata1992/RestAssured/actions)
2. Click on the latest workflow run
3. Scroll to **Artifacts** section

### Download:
- ✅ **Extent Report** (`ExtentReport.html`)
- ✅ **Surefire Report**
- ✅ **JaCoCo Code Coverage Report**

---

##  Contributing

--Fork the repo

--Create your feature branch (git checkout -b feature/add-new-test)

--Commit and push

--Submit a pull request

---

##  Author

**Sujata Sihag**
