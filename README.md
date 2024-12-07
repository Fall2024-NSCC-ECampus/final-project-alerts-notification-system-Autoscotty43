# SafetyNet Alert Notification System

**Created By:** Jared Scott  
**Date:** 12/3/2024

-----

## Project Description

The **SafetyNet Alert Notification System** is a web-based REST API designed to provide alerts for emergency situations. The system leverages various endpoints to provide detailed information for fire, flood, and other emergency scenarios. The API supports querying emergency details such as fire alerts, flood alerts, children living in specific households, and contact information for persons covered by specific fire stations. It also integrates a notification system that can provide real-time updates for safety-related concerns.

This application is built using **Spring Boot**, and it incorporates **JPA** (Java Persistence API) to interact with the underlying database. The application is also structured to allow easy integration with other services and tools that may require emergency alert information.

------

## Key Features

- **Fire Station Alerts:** Provides information about individuals covered by a particular fire station.
- **Child Alerts:** Returns information about children living at a given address.
- **Phone Alerts:** Retrieves phone numbers of individuals associated with a fire station.
- **Fire Alerts:** Provides fire details, including affected persons and fire severity.
- **Flood Alerts:** Returns flood information for a list of fire stations, including flood severity.
- **Community Email Alerts:** Retrieves email addresses for community members in a particular city.

---------

## Technologies Used

- **Java** (Spring Boot)
- **Spring Data JPA** (for database access)
- **SLF4J & Logback** (for logging)
- **MySQL Server** (for database)
- **H2 Database** (for development and testing purposes)

-------

## Setup Instructions

### Prerequisites

Before setting up the project, ensure that the following software is installed on your system:

- **Java 17 or later**
- **Maven** (for building the project)
- **IDE** like IntelliJ IDEA or Eclipse for editing the code
- **Postman** or any other API testing tool for testing endpoints

### Installation Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/Autoscotty43/AlertNotificationSystem.git
    ```

2. Navigate to the project directory:

    ```bash
    cd AlertNotificationSystem
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

   The application will start on `http://localhost:8080`.

5. You can now access the various endpoints by sending HTTP GET requests using a tool like Postman or cURL.

-------

## API Endpoints

### 1. Fire Station Persons

- **URL:** `/safetynet/firestation`
- **Method:** `GET`
- **Query Parameters:**
    - `stationNumber` (int): The fire station number to query.

**Response Example:**

```json
[
  {
    "name": "John Doe",
    "phone": "123-456-7890",
    "address": "123 Main St"
  },
  ...
]




