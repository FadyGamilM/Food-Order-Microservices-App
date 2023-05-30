# Food-Order-Microservices-App
Food-Ordering-System using microservices architecture with java, Spring, Spring boot, PostgreSQL, Docker, and K8s,.

## The Architecture of the `order-service`:
➜ `order-service` <br/>
- the base-project for the order microservice.
- contains all different layers of the order microservice. <br/>
  ➜ `order-core` <br/>
  - The core layer is the project that consists of 2 projects:
    - the `Domain` project which contains the domain entities with the domain services.
      - containers the `entites`, `value objects`, and `domain services`.
    - the `Application` project which contains the business-logic.
      - exposes the domain services to the outside world.
    
  ➜ `order-messaging` <br/>
    - contains the logic to integrate the kafka message queueing system.

  ➜ `order-data-access` <br/>
    - contains the logic to interact with database management system (in our scenario it will be postgreSQL).
  
  ➜ `order-container` <br/>
    - creates a runnable jar file for the order microservice.
    - this module project will have a dependency on all other modules.
    - after generating the jar file for this microservice, we will build a docker image from it.
  
  