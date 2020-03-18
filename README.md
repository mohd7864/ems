# ems

This is an Employee Management Service which will add and search for the employees
It uses the below tesch stack
  - Java 8
  - Spring Boot
  - PostGres
  - Docker

## Running the project

The Service is dockerized and pushed to dockerHub

Just run the docker compose file to install and run the containers locally

## Few Other Points

- The addEmployeeBulk was not implemented as a similar service has been implemented in my previous project named `inventory-servce` which is available in my gitRepo.
  As for handling large number of data it is advisable to use a Queue which i have used the same in my service which will help to push large number of products in bulk.
  I have used Rabbitmq.