# Автоматизация тестирования формы "Путешествие дня -Марракэш"

## Документация

- [План автоматизации тестирования](https://github.com/Obelianko/Diploma/blob/2f8259bbe51eb98842de9f08e02e8f6f6690ae41/Plan.md)
- [Отчет по тестированию](https://github.com/Obelianko/Diploma/blob/2f8259bbe51eb98842de9f08e02e8f6f6690ae41/Report/Report.md)
- [Отчет по автоматизации](https://github.com/Obelianko/Diploma/blob/2f8259bbe51eb98842de9f08e02e8f6f6690ae41/Report/Summary.md)

## Подготовка и запуск теста

Предварительно должен быть скачен и запущен Docker. Скачаны образы для докера Node.js, my
sql, postgresql.

### Инструкция по запуску с поддержкой MySQL
1. Клонировать репозиторий, выполнить команду:git clone https://github.com/Obelianko/Diploma.git
2. Запуск контейнеров Docker, выполнить команду: docker-compose up
3. Запуск SUT с поддержкой MySQL: выполнить команду: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar ./artifacts/aqa-shop.jar
4. Запуск тестов с MySQL, выполнить команду: ./gradlew "-Ddb.url=jdbc:mysql://localhost:3306/app" clean test
5. Отчёт Allure, выполнить команду: ./gradlew allureReport 
6. Выполнить команду: ./gradlew allureServe
7. Окончание тестов и остановка контейнеров

### Инструкция по запуску с поддержкой PostgreSQL
1. Клонировать репозиторий, выполнить команду: git clone https://github.com/Obelianko/Diploma.git
2. Запуск контейнеров Docker, выполнить команду: docker-compose up
3. Запуск SUT с поддержкой Postgres, выполнить команду java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar
4. Запуск тестов с Postgres, выполнить команду: ./gradlew "-Ddb.url=jdbc:postgresql://localhost:5432/app" clean test
5. Отчёт Allure, выполнить команду ./gradlew allureReport 
6. Выполнить команду: ./gradlew allureServe
7. Окончание тестов и остановка контейнеров


