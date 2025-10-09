# Smart Home Tech 🚀

---

## 🛠 Tech Stack
- **Backend:** Java 21, Spring Boot 3 (Web, Data JPA, Actuator, Spring Cloud)  
- **Messaging & Serialization:** Apache Kafka, Avro, Protobuf, gRPC  
- **Database:** PostgreSQL (Database per Service, Spring Data JPA/Hibernate)  
- **API & Contracts:** REST, OpenFeign, OpenAPI/Swagger  
- **Infrastructure:** Docker, Docker Compose, API Gateway, Eureka, Spring Cloud Config  
- **Monitoring:** Micrometer, Prometheus, Grafana  
- **Testing:** JUnit5, Mockito, Testcontainers  
- **Utilities:** Lombok, Logging, Exception Handling  
- **Architecture:** Event-driven microservices, CQRS-style aggregation, async processing  

---

## 📡 Project Overview
**Smart Home Tech** is a modular event-driven platform that combines **smart home telemetry** with an integrated **e-commerce system**.  
- **Telemetry domain:** Collector, Aggregator, Analyzer — processing sensor events, aggregating hub snapshots, and executing user-defined automation scenarios via gRPC.  
- **Commerce domain:** Shopping Store, Shopping Cart, Warehouse, Order, Payment, Delivery — microservices integrated through REST + Feign with Spring Cloud Discovery and Config.  

All services communicate via **Kafka** (Avro events) or **gRPC**, persist their state in **PostgreSQL**, and are containerized with **Docker** for local and production deployments.

---

## ✨ Features
- ✅ Event-driven architecture with Kafka + Avro/Protobuf  
- ✅ gRPC client-server communication between services  
- ✅ Telemetry snapshot aggregation and custom automation rules  
- ✅ E-commerce microservices (store, cart, warehouse, order, payment, delivery)  
- ✅ Service discovery & externalized config (Eureka, Spring Cloud Config)  
- ✅ API Gateway + Load Balancer for unified entry point  
- ✅ Unit & integration testing with Testcontainers  
- ✅ Monitoring & metrics with Prometheus/Grafana  

---

## 🚀 Getting Started

```bash
# clone repo
git clone https://github.com/oxSwight/smart-home-tech.git
cd smart-home-tech

# run with Docker Compose
docker-compose up --build
