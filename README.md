# ShopperStack REST Assured API Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange)
![REST Assured](https://img.shields.io/badge/REST%20Assured-6.0.0-blue)
![TestNG](https://img.shields.io/badge/TestNG-7.10.0-red)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36)
![GitHub Actions](https://img.shields.io/badge/CI-GitHub%20Actions-2088FF)

A Java-based REST API automation framework built with **REST Assured, TestNG and Maven** for testing the [ShopperStack](https://www.shoppersstack.com/) APIs.

The project is designed as an SDET portfolio framework and demonstrates practical API automation concepts such as reusable request specifications, endpoint abstraction, POJO payloads, API chaining, data-driven testing, logging, reporting, assertions and CI execution with GitHub Actions.

---

## 📌 Project Overview

This framework follows a layered design so that API tests remain readable and reusable.

```text
Test Cases
    ↓
Data Providers / Test Data
    ↓
Endpoint Classes
    ↓
Reusable Request Specifications
    ↓
ShopperStack REST APIs
```

The framework separates:

- **Test cases** — business scenarios and assertions
- **Endpoints** — HTTP request implementation
- **Payloads** — request body models
- **Specifications** — common REST Assured configuration
- **Utilities** — reusable helpers and runtime test data
- **Data Providers** — parameterized and Excel-driven test data
- **Listeners** — TestNG lifecycle handling and reporting

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| REST Assured 6.0.0 | REST API automation |
| TestNG 7.10.0 | Test execution and assertions |
| Maven | Build and dependency management |
| Jackson | JSON serialization/deserialization |
| Apache POI | Excel-based test data |
| JavaFaker | Dynamic test data generation |
| Log4j2 | Application/framework logging |
| ExtentReports 5.1.2 | HTML execution reports |
| Git / GitHub | Source control |
| GitHub Actions | CI pipeline |

---

## 🏗️ Framework Architecture

```text
src/
├── main/
│   ├── java/
│   │   └── api/
│   │       ├── endpoints/
│   │       │   ├── AdminEndpoints.java
│   │       │   ├── MerchantEndpoints.java
│   │       │   ├── ShopperEndpoints.java
│   │       │   ├── ProductEndpoints.java
│   │       │   ├── CartOrderEndpoints.java
│   │       │   ├── ReviewEndpoints.java
│   │       │   └── Routes.java
│   │       │
│   │       ├── payload/
│   │       │   ├── AdminPayload.java
│   │       │   ├── ProductPayload.java
│   │       │   ├── ReviewPayload.java
│   │       │   ├── OrderPayload.java
│   │       │   └── ...
│   │       │
│   │       ├── specs/
│   │       │   └── ReusableRequestSpec.java
│   │       │
│   │       └── utils/
│   │           ├── Constants.java
│   │           ├── TestData.java
│   │           ├── FakeDataGenerator.java
│   │           └── ExcelReader.java
│   │
│   ├── resources/
│   │   └── log4j2.xml
│   │
│   └── ...
│
├── test/
│   ├── java/
│   │   └── api/
│   │       ├── testcases/
│   │       │   ├── Admintestcases.java
│   │       │   ├── MerchantTestcases.java
│   │       │   ├── ShopperTestcases.java
│   │       │   ├── ProductTestcases.java
│   │       │   ├── CartOrderTestcases.java
│   │       │   └── ReviewTestcases.java
│   │       │
│   │       ├── dataproviders/
│   │       │   ├── AdminDataProvider.java
│   │       │   ├── ProductDataProvider.java
│   │       │   └── ReviewDataProvider.java
│   │       │
│   │       └── listeners/
│   │           ├── TestListener.java
│   │           ├── ExtentManager.java
│   │           └── ExtentTestManager.java
│   │
│   └── resources/
│       └── ReviewData.xlsx
│
├── .github/
│   └── workflows/
│       └── api-tests.yml
│
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

> The exact package/file list can evolve as additional API modules are automated.

---

## ✨ Key Features

### 1. Reusable Request Specifications

Common REST Assured configuration is centralized in `ReusableRequestSpec`.

The framework supports:

- Base URI
- JSON `Content-Type`
- JSON `Accept`
- Authentication headers
- Role-specific request specifications
- Reusable request configuration

Example:

```java
RequestSpecification spec =
        ReusableRequestSpec.buildRequestSpec();
```

Authenticated requests can use the relevant role token:

```java
RequestSpecification spec =
        ReusableRequestSpec.buildShopperRequestSpec();
```

---

### 2. Endpoint Layer

HTTP request implementation is kept outside the test classes.

Example:

```java
public static Response createReview(
        String productId,
        ReviewPayload payload) {

    return given()
            .spec(ReusableRequestSpec.buildShopperRequestSpec())
            .queryParam("productId", productId)
            .body(payload)
            .when()
            .post(Routes.POST_REVIEW)
            .then()
            .extract()
            .response();
}
```

This keeps the test case focused on **what is being tested**, rather than how the HTTP request is constructed.

---

### 3. Payload / POJO Classes

Request bodies are represented using Java classes instead of building JSON strings directly in tests.

Example:

```java
ReviewPayload payload = new ReviewPayload();

payload.setDateTime("2025-06-02T10:00:00.000Z");
payload.setDescription("The product quality was excellent.");
payload.setHeading("Highly recommended!");
payload.setRating(5);
payload.setShopperId(Integer.parseInt(TestData.shopperId));
payload.setShopperName("Sophia");
```

REST Assured/Jackson handles serialization of the Java object into JSON.

---

### 4. API Chaining

The framework supports passing runtime data from one API to another.

Example:

```text
Create Admin
     ↓
Extract adminId
     ↓
TestData.adminId
     ↓
Get Admin
     ↓
Update Admin
```

Other workflows can follow the same pattern:

```text
Add Product to Cart
        ↓
Extract itemId
        ↓
Create Order
        ↓
Extract orderId
        ↓
Get Invoice / Update Order
```

Runtime values such as IDs and tokens are maintained separately from static constants.

---

### 5. Runtime Test Data

`TestData` is used for values generated or extracted during execution.

Examples:

```java
TestData.adminId
TestData.merchantId
TestData.shopperId
TestData.productId
TestData.itemId
TestData.orderId
TestData.reviewId
```

This allows dependent API tests to reuse values returned by earlier requests.

---

### 6. Dynamic Test Data

`FakeDataGenerator` is used where unique test data is required.

Examples include:

- Unique email addresses
- Names
- Phone numbers
- Passwords

Example:

```java
String email = FakeDataGenerator.getUniqueEmail();
String phone = FakeDataGenerator.getPhoneNumber();
```

This reduces collisions when APIs require unique values.

---

### 7. Data-Driven Testing

TestNG `@DataProvider` is used for multiple datasets.

Example:

```java
@Test(
    dataProvider = "validAdminData",
    dataProviderClass = AdminDataProvider.class
)
public void createAdminDataDrivenTest(
        String firstName,
        String lastName,
        String email,
        String phone) {

    // test implementation
}
```

The framework also supports Excel-based test data using Apache POI.

Current Excel flow:

```text
Excel
  ↓
ExcelReader
  ↓
DataProvider
  ↓
Test Method
  ↓
Payload / API Request
```

The generic `ExcelReader` is intentionally kept independent of individual payload classes.

---

### 8. TestNG Listener

The framework uses a TestNG listener to capture test lifecycle events.

Examples:

```text
TEST STARTED
TEST PASSED
TEST FAILED
TEST SKIPPED
```

Failure information can be captured from `ITestResult`.

This keeps lifecycle handling outside individual test classes.

---

### 9. ExtentReports

ExtentReports provides an HTML execution report containing:

- Test names
- Pass/fail status
- Execution information
- System information
- Failure information

The report is generated after the test execution.

Typical local output:

```text
test-output/
└── ExtentReport.html
```

---

### 10. Log4j2 Logging

Log4j2 is used for framework/test lifecycle logging.

Example:

```text
INFO  TestListener - TEST STARTED: createAdminWithValidDataTest
INFO  TestListener - TEST PASSED: createAdminWithValidDataTest
ERROR TestListener - TEST FAILED: createProductReviewTest
```

Sensitive information such as passwords, JWTs and authorization headers should not be logged.

---

## 🧪 API Areas Automated

The framework is built around the ShopperStack shopping API and includes automation across areas such as:

- Admin
- Merchant
- Shopper profile
- Shopper address
- Shopper bank details
- Shopper cards
- Products
- Wishlist
- Cart
- Orders
- Wallet
- Product reviews

The test suite is continuously being expanded as additional API scenarios are implemented.

---

## 🔄 Example API Workflow

A typical chained workflow can look like:

```text
Authentication
      ↓
Create Entity
      ↓
Extract ID from Response
      ↓
Store Runtime ID in TestData
      ↓
Get Entity by ID
      ↓
Update Entity
      ↓
Validate Response
```

For an order workflow:

```text
Add Product to Cart
      ↓
itemId
      ↓
Create Order
      ↓
orderId
      ↓
Get Invoice
      ↓
Update Order Status
```

---

## 📊 Test Validation

The framework uses TestNG assertions for:

- HTTP status codes
- Response body values
- IDs returned from APIs
- Required fields
- Business validations
- API chaining values

Example:

```java
Assert.assertEquals(
        response.getStatusCode(),
        201,
        "Resource should be created successfully"
);

Assert.assertNotNull(
        TestData.reviewId,
        "Review ID should not be null"
);
```

---

## 🚀 Getting Started

### Prerequisites

Install:

- JDK 17+
- Maven 3.x
- Git
- IntelliJ IDEA / Eclipse / VS Code
- Internet access to the ShopperStack API

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

---

## 📥 Clone the Repository

```bash
git clone https://github.com/LittleLightXD/restassured-api-automation-framework.git
cd restassured-api-automation-framework
```

---

## 📦 Install Dependencies

```bash
mvn clean install
```

Or, when you only want to execute the tests:

```bash
mvn clean test
```

---

## ▶️ Run Tests

### Run the complete TestNG suite

```bash
mvn clean test
```

The Maven Surefire plugin uses the project's `testng.xml` suite configuration.

### Run a specific test class

Example:

```bash
mvn test -Dtest=Admintestcases
```

### Run a specific test method

Example:

```bash
mvn test -Dtest=Admintestcases#createAdminWithValidDataTest
```

---

## 📄 TestNG Suite

The project uses:

```text
testng.xml
```

The suite controls the test classes/listeners used during execution.

---

## 📊 Reports

After local execution, generated test artifacts are available under the project's output directories.

### Extent Report

```text
test-output/ExtentReport.html
```

Open the HTML file in a browser.

### Maven / Surefire Reports

```text
target/surefire-reports/
```

These reports are also useful when diagnosing CI failures.

---

# 🔁 CI/CD – GitHub Actions

The project includes a GitHub Actions workflow:

```text
.github/workflows/api-tests.yml
```

The pipeline automatically runs when changes are pushed to the configured branch and can also run for pull requests/manual execution depending on the workflow configuration.

### CI Flow

```text
Git Push / Pull Request
          ↓
    GitHub Actions
          ↓
     Checkout Code
          ↓
      Java 17 Setup
          ↓
     Maven Dependencies
          ↓
      mvn clean test
          ↓
    TestNG Test Suite
          ↓
 ┌────────┴─────────┐
 PASS               FAIL
  ↓                   ↓
Artifacts           Artifacts
  ↓                   ↓
Reports available for investigation
```

### GitHub Actions Artifacts

The workflow uploads test reports so they remain available after the CI job finishes.

Typical artifacts include:

```text
testng-reports
extent-report
```

This makes it possible to investigate failed tests directly from the GitHub Actions run.

---

## 🔐 Security

Do **not** commit:

- Passwords
- JWT tokens
- API secrets
- Private credentials
- Authorization headers

For CI/CD, sensitive values should be supplied through GitHub Actions Secrets or environment variables rather than hardcoded in Java source.

If a token is accidentally exposed, rotate/revoke it.

---

## 🧩 Design Principles

The framework follows these principles:

### Separation of concerns

```text
Test Case       → Scenario + Assertions
Endpoint        → HTTP Request
Payload         → Request Body
Request Spec    → Common HTTP Configuration
Data Provider   → Test Data
Excel Reader    → Excel Reading
TestData        → Runtime Values
Listener        → Test Lifecycle
ExtentReports   → Test Reporting
Log4j2          → Logging
```

### Reusability

Common HTTP configuration and utility operations are centralized instead of duplicated across tests.

### Maintainability

When an endpoint changes, the endpoint implementation can be updated without rewriting every test that uses it.

### Readability

Test cases are written to describe the API scenario rather than low-level request construction.

---

## 📁 Important Files

| File / Directory | Responsibility |
|---|---|
| `api/endpoints/` | API request implementations |
| `api/payload/` | Request payload POJOs |
| `api/specs/` | Reusable REST Assured specifications |
| `api/utils/Constants.java` | Static constants |
| `api/utils/TestData.java` | Runtime IDs/tokens |
| `api/utils/FakeDataGenerator.java` | Dynamic test data |
| `api/utils/ExcelReader.java` | Generic Excel reader |
| `api/testcases/` | Test scenarios and assertions |
| `api/dataproviders/` | TestNG/Excel data providers |
| `api/listeners/` | TestNG listener and Extent reporting |
| `testng.xml` | Test suite configuration |
| `pom.xml` | Maven dependencies/build configuration |
| `.github/workflows/` | CI workflow |

---

## 🧪 Example: Create Review Test

The project uses Excel data for data-driven review testing.

Example Excel structure:

```text
productId | dateTime | description | heading | rating | shopperName
```

The test receives the Excel values through a TestNG DataProvider and builds a `ReviewPayload`.

```java
@Test(
    dataProvider = "reviewData",
    dataProviderClass = ReviewDataProvider.class
)
public void createProductReviewTest(
        String productId,
        String dateTime,
        String description,
        String heading,
        String rating,
        String shopperName) {

    ReviewPayload payload = new ReviewPayload();

    payload.setDateTime(dateTime);
    payload.setDescription(description);
    payload.setHeading(heading);
    payload.setRating(Integer.parseInt(rating));
    payload.setShopperId(Integer.parseInt(TestData.shopperId));
    payload.setShopperName(shopperName);

    Response response =
            ReviewEndpoints.createReview(productId, payload);

    Assert.assertEquals(response.getStatusCode(), 201);
}
```

---

## 🧠 What This Project Demonstrates

This project demonstrates practical SDET/API automation skills including:

- REST API testing
- REST Assured DSL
- HTTP methods: GET, POST, PUT, PATCH and DELETE
- Query parameters
- Path parameters
- Headers
- Bearer authentication
- JSON request/response handling
- POJO payload modeling
- API chaining
- TestNG
- DataProvider
- Excel data-driven testing
- Dynamic test data
- Assertions
- TestNG listeners
- Log4j2
- ExtentReports
- Maven
- Git/GitHub
- GitHub Actions CI

---

## 🔮 Future Enhancements

Potential future improvements include:

- Environment-specific configuration
- Secure CI/CD secrets integration
- Parallel execution with thread-safe runtime test data
- JSON Schema validation
- More centralized response validation
- Retry strategy for transient failures
- API performance testing
- Dockerized test execution
- Jenkins pipeline integration
- Database validation
- Security testing
- Additional API coverage

---

## 🤝 Development Workflow

Recommended workflow:

```text
Create Feature Branch
        ↓
Implement / Update Tests
        ↓
Run mvn clean test Locally
        ↓
Commit Changes
        ↓
Push Feature Branch
        ↓
Create Pull Request
        ↓
GitHub Actions Executes Tests
        ↓
Review Test Results
        ↓
Merge to Main
```

---

## 📚 References

- REST Assured: https://rest-assured.io/
- REST Assured GitHub: https://github.com/rest-assured/rest-assured
- TestNG: https://testng.org/
- Maven: https://maven.apache.org/
- Apache POI: https://poi.apache.org/
- ExtentReports: https://www.extentreports.com/
- GitHub Actions: https://docs.github.com/en/actions

---

## 👤 Author

**LittleLightXD**

GitHub:  
https://github.com/LittleLightXD

Repository:  
https://github.com/LittleLightXD/restassured-api-automation-framework

---

## 📄 License

This project is intended for learning, portfolio and SDET/API automation practice.

---

⭐ If you find the framework useful, feel free to explore the code, raise issues, or suggest improvements.