# Restify

API REST para gerenciamento de salas e reservas, com autenticação JWT, validação de conflitos de horário, documentação Swagger e suporte a Docker.
Projeto de fins educacionais (Feito em base minha base de estudos)

---

## ✨ Funcionalidades

* CRUD de Salas (`/rooms`)
* CRUD de Reservas (`/reservations`) com validação de conflito de horários
* Autenticação via JWT (`/auth/login`)
* Documentação interativa com Swagger UI
* H2 Console para desenvolvimento local
* Docker + Docker Compose para ambiente isolado com PostgreSQL

---

## 📦 Requisitos

* Java 17+
* Maven (ou Maven Wrapper `./mvnw`)
* Docker & Docker Compose (opcional, para ambiente conteinerizado)

---

## 🚀 Instalação & Execução Local

1. **Clonar repositório**

   ```bash
   git clone https://github.com/seu-usuario/restify.git
   cd restify
   ```

2. **Configurar variáveis**

   Copie o modelo de `application-example.properties` para `src/main/resources/application.properties` e preencha:

   ```properties
   spring.datasource.url=jdbc:h2:mem:restify
   spring.datasource.username=root
   spring.datasource.password=root

   # JWT
   app.jwt.secret=UmaChaveUltraSecreta12345
   app.jwt.expiration-ms=3600000
   ```

3. **Executar com Maven**

   ```bash
   ./mvnw clean spring-boot:run
   ```

4. **Acessar**

   * API: `http://localhost:8080`
   * Swagger UI: `http://localhost:8080/swagger-ui.html`
   * H2 Console: `http://localhost:8080/h2-console` (usuário: `root`; senha: `root`)

---

## 🐳 Com Docker

1. **Construir e subir containers**

   ```bash
   docker-compose up --build
   ```

2. **Variáveis de ambiente**

   O `docker-compose.yml` já define:

   ```yaml
   SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/restify_db
   SPRING_DATASOURCE_USERNAME: restify_user
   SPRING_DATASOURCE_PASSWORD: restify_pwd
   APP_JWT_SECRET: UmaChaveUltraSecreta12345
   APP_JWT_EXPIRATION_MS: 3600000
   ```

3. **Acessar**

   * API: `http://localhost:8080`
   * Swagger UI: `http://localhost:8080/swagger-ui.html`

4. **Parar containers**

   ```bash
   docker-compose down
   ```

---

## 🔐 Autenticação

1. **Criar usuário** (via H2 Console ou script SQL):

   ```sql
   INSERT INTO users(username, password, roles)
   VALUES('admin', '{bcrypt}$2a$10$...hashedPassword...', 'ROLE_USER,ROLE_ADMIN');
   ```

2. **Obter token JWT**:

   ```bash
   curl -X POST http://localhost:8080/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username":"admin","password":"suaSenha"}'
   ```

   ```json
   { "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..." }
   ```

3. **Incluir token nas requisições**:

   ```bash
   curl http://localhost:8080/rooms \
     -H "Authorization: Bearer SEU_TOKEN_AQUI"
   ```

---

## 📚 Endpoints principais

| Método | Rota            | Descrição                           |
| ------ | --------------- | ----------------------------------- |
| POST   | `/auth/login`   | Autentica e retorna token JWT       |
| GET    | `/rooms`        | Lista todas as salas                |
| POST   | `/rooms`        | Cria nova sala                      |
| GET    | `/reservations` | Lista todas as reservas             |
| POST   | `/reservations` | Cria nova reserva (valida conflito) |

---

## 🛠️ Próximos Passos

* Escrever testes automatizados (unit & integração)
* Adicionar monitoramento com Spring Actuator
* Configurar CI/CD (GitHub Actions)
* Implementar frontend com React ou Vue.js
