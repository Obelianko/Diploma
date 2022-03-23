##Подготовка и запуск теста

Предварительно должен быть скачен и запущен Docker. Скачаны образы для докера Node.js, mysql, postgresql.

###Инструкция по запуску с поддержкой MySQL
1. Клонировать репозиторий, выполнить команду:git clone https://github.com/Obelianko/Diploma.git
2. Запуск контейнеров Docker, выполнить команду: docker-compose up
3. Запуск SUT с поддержкой MySQL: выполнить команду: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
4. Запуск тестов с MySQL, выполнить команду: ./gradlew "-Ddb.url=jdbc:mysql://localhost:3306/app" clean test
5. Отчёт Allure, выполнить команду: ./gradlew allureReport 
6. Окончание тестов и остановка контейнеров

###Инструкция по запуску с поддержкой PostgreSQL
1. Клонировать репозиторий, выполнить команду: git clone https://github.com/Obelianko/Diploma.git
2. Запуск контейнеров Docker, выполнить команду: docker-compose up
3. Запуск SUT с поддержкой Postgres, выполнить команду java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
4. Запуск тестов с Postgres, выполнить команду: ./gradlew "-Ddb.url=jdbc:postgresql://localhost:5432/app" clean test
5. Отчёт Allure, выполнить команду ./gradlew allureReport 
6. Окончание тестов и остановка контейнеров


