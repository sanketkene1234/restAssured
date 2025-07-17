# 📚 - BookServe QA Framework

This project is an API automation framework built to validate the core functionalities of a FastAPI-based BookStore application. It ensures comprehensive test coverage across CRUD operations, error handling, and authentication flows, with detailed reporting and CI integration.

---

## 🔧 Tools & Framework Components

- **Java (17)**: Core programming language for framework logic  
- **RestAssured**: RESTful API automation and validation  
- **TestNG**: Test execution and configuration management  
- **ExtentReports**: Generation of interactive HTML test reports  
- **Maven**: Project build, dependency, and lifecycle management  
- **GitHub Actions**: CI/CD pipeline for automated testing and deployment  

---

## 📊 Validation Scope

The framework covers the following test scenarios for Health check, User, and Book APIs:

### ✅ Expected Behavior Cases
- Check service health (If server is running or not)
- User sign-up and login with valid credentials
- Create a new book with valid data
- Retrieve all books
- Retrieve a book by ID
- Update a book
- Delete a book

### ❌ Adverse Condition Validations
- Access APIs with invalid or missing tokens
- Attempt to get non-existent books
- Sign-up/login with invalid credentials


## 🔗 Request Chaining

The access token from the login API is dynamically injected into subsequent requests (e.g., create/update/delete book), enabling seamless request chaining.

---

## 📘 Quality Assurance Strategy

### 🧪 1. API Test Case Architecture
- Modular test classes for each resource: User, Book, and Health
- Used `dependsOnGroups` to manage cross-class dependencies (e.g., Book tests depend on successful login)
- Implemented request chaining for token-based authentication
- Used data.json for data testing for cleaner code

### 🧰 2. System Robustness & Serviceability
- Configurable base URL and tokens using `config.properties`
- Clear separation of concerns: logic, request specs, data, reporting
- Assertions for status codes, response structure, and error messages
- Positive and negative test cases for all endpoints
- Common utility methods for validation
- Used `BaseTest` and `Listeners` to manage test lifecycle and reports

### 🧠 3. Challenges & Solutions

| Challenge | Solution |
|----------|----------|
| APIs returned 500 instead of 422 for invalid input | Validated payloads and added assertions to catch backend issues |
| APIs returned 400 but not mentioned in Swagger | Improve API documentation |
| No Delete API for user | Suggest adding delete user API or managing user reusability |
| Cross-class test dependencies | Switched to `dependsOnGroups` for better control |
| No validation on empty email/book name | Suggested fixes on the backend side |

---

## ⚙️ CI/CD Pipeline

### 🔁 Trigger:
- Runs on every push and pull request to the `main` branch

### 🚀 Steps in CI/CD Process:
1. Checkout code
2. Setup Java (Temurin 17)
3. Build and run tests via Maven
4. Upload test reports:
   - **Surefire Reports (TestNG)**
   - **ExtentReports (HTML)**
   - **JaCoCo Code Coverage Reports**

---

## 🧱 Framework Structure

```
📦src
 ┣ 📂main
 ┃ ┗ 📂java (utils, config)
 ┣ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┣ 📂tests
 ┃ ┃ ┣ 📂utils
 ┃ ┃ ┗ 📜BaseTest.java
 ┃ ┗ 📜testng.xml
┣ 📜pom.xml
┣ 📜config.properties
┣ 📜README.md
```

---

## ▶️ How to Run the Tests

### 🔧 Prerequisites
- Java 17+
- Maven 3.6+
- Git

### 💻 Run Locally
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

## 📈 Sample Report

The framework generates a detailed **Extent Report** after each run with:

- Test name and description
- Pass/Fail/Skip status
- Request/response logs
- Assertion results

---

## 👁️ How to View GitHub Actions Reports

1. Go to: [https://github.com/sujata1992/RestAssured/actions](https://github.com/sujata1992/RestAssured/actions)
2. Click on the latest workflow run
3. Scroll to **Artifacts** section

### Download:
- ✅ **Extent Report** (`ExtentReport.html`)
- ✅ **Surefire Report**
- ✅ **JaCoCo Code Coverage Report**

---

## 🤝 Contributing

- Fork the repo
- Create your feature branch:
  ```bash
  git checkout -b feature/your-feature-name
  ```
- Commit and push
- Submit a pull request

---

## ✍️ Author

**Sujata Sihag**