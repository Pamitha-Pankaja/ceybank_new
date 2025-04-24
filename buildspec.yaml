version: 0.2

phases:
  install:
    runtime-versions:
      java: corretto17
  build:
    commands:
      - echo Building Spring Boot application...
      - ./mvnw clean package -DskipTests
artifacts:
  files:
    - target/*.jar
    - appspec.yml
    - scripts/**
