# 🚀 RestAssured + TestNG API Testing Suite

A Java-based TestNG automation framework using **RestAssured** to validate RESTful API endpoints.

## 📋 Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Running Tests](#running-tests)
- [Reporting & Logs](#reporting--logs)
- [Contributing](#contributing)

---

## 🧠 Introduction

This project leverages [RestAssured](https://rest-assured.io/) — a Java DSL simplifying REST API testing — in combination with TestNG. Write clean, declarative tests like:

```java
given()
  .param("key", "value")
.when()
  .get("/endpoint")
.then()
  .assertThat().statusCode(200)
  .body("data.id", equalTo(123));
```

It’s perfect for automated validation of REST services with readable syntax.

---

## 🗂 Project Structure

```
src/
└── test/
    └── java/
        └── com/yourorg/tests/
            ├── BaseTest.java         # Initializes RestAssured and common configs
            ├── AuthenticationTests.java
            ├── FunctionalTests.java
            └── DataDrivenTests.java
pom.xml
```

- **BaseTest**: Defines shared configurations (base URI/port).
- **Test Class**: Contains API calls and assertions.
- **TestNG** is used as the test runner.

---

## ✅ Prerequisites

Ensure your environment includes:
- **Java 8** or higher
- **Maven 3.6+**
- TestNG and RestAssured dependencies (see `pom.xml`)
- Optionally: **Mock server / real API endpoint**

---

## 🛠 Setup & Installation

1. **Clone the repo**
    ```bash
    git clone https://github.com/sujata1992/RestAssured.git
    cd RestAssured
    ```
2. **Ensure API availability**  
   Make sure your API is running locally or change configuration in `Config.properties`.
3. **Build and download dependencies**
    ```bash
    mvn clean compile
    ```

---

## ▶️ Running Tests

Run tests via Maven or your IDE.

### Using Maven:
```bash
mvn test
```

### Through TestNG XML (parallel execution supported):
```bash
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

### Targeted test execution:
```bash
mvn test -Dtest=AuthenticationTests
```

---

## 📊 Reporting & Logs

- **Console output**: Displays pass/fail for each test.
- **Custom logging**: Enhance with Log4j/Sl4j or TestNG reports.
- **(Reporter)** Integrated ExtentReports for rich HTML reporting.

---

## 🤝 Contributing

1. Fork the repo  
2. Create a feature branch (`git checkout -b feature/new-tests`)  
3. Add tests or fix bugs  
4. Ensure all tests pass  
5. Raise a pull request  

---


## 📚 Learn More

- [RestAssured Wiki](https://github.com/rest-assured/rest-assured/wiki/usage)
- [RestAssured with TestNG Example](https://github.com/kamalgirdher/restassured_testNG)
- [Advanced RestAssured Examples](https://github.com/eliasnogueira/restassured-complete-basic-example)

---

### 🧪 Final Tips

- Keep tests **independent** and **idempotent**
- Use **Data Providers** for parameterized scenarios
- Externalize config (e.g., base URI, credentials)
- Incorporate **parallel execution** carefully to avoid shared state issues
