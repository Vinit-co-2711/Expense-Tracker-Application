# Expense Tracker Application

A sleek, enterprise-grade personal finance and transaction logging web application built using **Java Spring Boot**, **Hibernate (Spring Data JPA)**, and **MySQL**, with a modern single-page frontend rendered via **Thymeleaf** and styled using **Tailwind CSS**. 

This application functions as a highly optimized single-user platform to log daily financial transactions, categorize systemic cash flows, and generate real-time analytical reports through asynchronous REST endpoints.

---

## 🏗️ MVC System Architecture & Code Structure

Following the robust **Model-View-Controller (MVC)** architectural design pattern, the application decouples computing layers to maintain high clean-code standards and micro-service compatibility:

* **Presentation Layer (Web UI):** A premium, minimalist UI styled with a light beige palette (`#FDFBF7`) and high-contrast charcoal elements. Built using **Thymeleaf + Tailwind CSS**, it communicates asynchronously via JavaScript Fetch API to render changes instantly without reloading the page.
* **Controller Layer (REST API):** Exposed under the `@RequestMapping("/api/expenses")` endpoints. It handles JSON data payloads, maps web requests, and decouples the UI from core processing layers.
* **Service Layer (Core Analytics Engine):** Contains backend calculation algorithms. It processes custom analytics, grouping entries by categorical structures and aggregating overall financial summaries.
* **Repository Layer (Data Access via Hibernate):** Built upon **Spring Data JPA**, this layer abstracts low-level SQL mechanics, executing streamlined database persistence calls.
* **Database Layer:** Relational storage powered by a local **MySQL instance** running a structured transactional entity schema.

---

## ⚡ Core Features

* **Asynchronous RESTful CRUD:** Log new financial metrics, read data histories, update legacy inputs, and delete records seamlessly with dynamic JSON data interchanges.
* **Smart Categorization:** Tag transactions under distinct domains (e.g., Food, Transport, Rent, Entertainment) utilizing responsive, pastel-tinted status chips for optimal classification.
* **Real-time Analytics Reporting:** Automated service layer modules aggregate records on-the-fly to return overall KPI balances, total income (emerald indicators), total expenses (muted coral indicators), and itemized category graphs.
* **Lightweight Setup:** Stripped of heavy Spring Security configuration protocols for rapid local deployments and clean developer analysis.

---

## 🗄️ Database Schema & Entity Layout (ERD)

The system maps a single data entity model directly to the MySQL relational layer:

| Field | Data Type | Database Type | Description |
| :--- | :--- | :--- | :--- |
| `id` | `Long` | `BIGINT (PK, AI)` | Unique identifier configured for Auto-Increment |
| `description` | `String` | `VARCHAR(255)` | Simple text overview describing the transaction |
| `amount` | `double` | `DOUBLE / DECIMAL` | Monetary valuation of the transaction item |
| `category` | `String` | `VARCHAR(100)` | Grouping tag (e.g., Food, Rent, Utilities) |
| `date` | `LocalDate` | `DATE` | Timestamp detailing when the expense occurred |
| `transactionType`| `Enum / String`| `VARCHAR(50)` | Specifies balance flow: `INCOME` or `EXPENSE` |

---

## 🛠️ Technical Stack

* **Backend Framework:** Java 17+, Spring Boot (Spring Web, Spring Data JPA)
* **ORM Framework:** Hibernate
* **Frontend UI Engine:** Thymeleaf, JavaScript (Fetch API), Tailwind CSS (via CDN)
* **Database Engine:** MySQL
* **Build Automation:** Maven

---

## 🚀 Local Installation & Getting Started

### Prerequisites
* Java Development Kit (JDK 17 or higher)
* Apache Maven installed
* Local MySQL Server instance running

### 1. Initialize the Relational Database
Access your MySQL CLI or Workbench and run the following command to initialize the schema:
```sql
CREATE DATABASE expense_db;
