# Университет ScaleApps

Небольшое Spring Boot приложение на Java 17 для управления студентами и группами. Использует Maven, Flyway/DB migrations и шаблоны в `src/main/resources/templates` (`groups.html`, `students.html`).

## Стек
- Java 17
- Spring Boot
- Maven
- MySQL
- Thymeleaf/templates (в `src/main/resources/templates`)

## Структура проекта (ключевые файлы)
- `src/main/java` — исходники приложения (контроллеры, сервисы, репозитории, DTO, entity)
- `src/main/resources/application.yml` — конфигурация Spring
- `src/main/resources/db/migration` — миграции базы данных
- `src/main/resources/templates` — HTML-шаблоны (`groups.html`, `students.html`)
- `Dockerfile` — образ приложения (если нет — создайте по примеру ниже)
- `docker-compose.yml` — образец для запуска приложения и MySQL (см. секцию ниже)

## Запуск проекта
Проект включает в себя Dockerfile и docker-compose.yml для упрощения процесса развертывания, поэтому для запуска проекта следует иметь установленный Docker и Docker Compose Для запуска проекта выполните следующие шаги:

1. Склонируйте репозиторий:
   ```bash
    git clone https://github.com/moootvey/studUniversity.git
    cd studUniversity
   ```
2. Соберите и запустите проект с помощью Docker Compose:
   ```bash
    docker compose up --build
   ```
3. После успешного запуска, вы сможете получить доступ к проекту по адресу: `http://localhost:8080/`.
