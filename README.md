# 🏥 Hospital API

[![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green?logo=springboot)](https://spring.io/projects/spring-boot)
[![JWT](https://img.shields.io/badge/JWT-Security-orange?logo=jsonwebtokens)](https://jwt.io)
[![Maven](https://img.shields.io/badge/Maven-Build-red?logo=apachemaven)](https://maven.apache.org/)

---

## 📖 Sobre o Projeto

Esse projeto é uma aplicação RESTful em Java com Spring Boot para gestão de médicos, pacientes e consultas. Ela permite o cadastro e atualização de usuários, a inativação lógica de registros e o agendamento ou cancelamento de consultas com regras de negócio que garantem consistência, como prevenção de conflitos de horários e seleção automática de médicos disponíveis.

O sistema utiliza autenticação JWT para segurança, documentação interativa com Swagger/OpenAPI e uma arquitetura organizada em camadas que separa controladores, serviços, entidades, DTOs e infraestrutura. Com tratamento global de erros e consultas otimizadas via Spring Data JPA, a API é robusta, escalável e pronta para servir como base de sistemas médicos confiáveis e bem estruturados.

---

## 🚀 Tecnologias Utilizadas
- **Java 21+**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security (JWT)**
- **Hibernate**
- **MySQL**
- **Lombok**
- **SpringDoc OpenAPI**

---

## 📌 Funcionalidades

- **Usuários**: autenticação com JWT.
- **Médicos**:
  - Cadastrar, listar (somente ativos), atualizar e excluir (soft delete).
- **Pacientes**:
  - Cadastrar, listar (somente ativos), atualizar e excluir (soft delete).
- **Consultas**:
  - Agendamento com regras de negócio (validações customizadas).
  - Cancelamento com validação de motivo.
- **Documentação**: disponível via Swagger/OpenAPI.

---

## 📂 Estrutura do Projeto

```src/main/java
src/main/java/com/med/gus/api
│
├── appointment                       # Lógica de agendamento/cancelamento (serviços + regras)
│   ├── AppointmentSchedule.java      # Service principal do agendamento
│   ├── validators                    # Regras de negócio do agendamento
│   └── cancelValidators              # Regras de negócio do cancelamento
│
├── controller                        # Endpoints REST (Medics, Patients, Appointments, Authentication)
│
├── domain
│   ├── appointment
│   │   ├── Appointment.java          # Entidade JPA
│   │   ├── AppointmentSchedule.java  # Service principal do agendamento
│   │   ├── dto                       # DTOs de entrada/saída (ex: DataSchedule, DataCancel, DataDetail)
│   │   └── validations               # Regras de negócio do agendamento
│   │
│   ├── medic
│   │   ├── Medic.java
│   │   └── dto                       # DTOs do médico (cadastro, atualização, listagem, detail)
│   │
│   ├── patient
│   │   ├── Patient.java
│   │   └── dto                       # DTOs do paciente
│   │
│   ├── user
│   │   ├── User.java
│   │   └── dto                       # DTOs de autenticação/login
│   │
│   └── endereco
│       ├── Endereco.java
│       └── dto                       # DTOs de endereço
│
├── infra
│   ├── exception                     # Tratamento global de erros (ErrorManager)
│   ├── security                      # Configuração JWT e autenticação
│   └── springdoc                     # Configuração Swagger/OpenAPI
│
└── Application.java (main)

```

---

## 🔑 Autenticação

A API utiliza **JWT** para proteger os endpoints.  

### Fluxo:
1. Crie um usuário no banco (tabela `users`).
2. Faça login via endpoint `/login`.
3. O token JWT retornado deve ser enviado no header:

Authorization: Bearer <seu_token>

---

## 📖 Endpoints Principais

### 👤 Usuário (Autenticação)
- POST /auth/login → retorna token JWT.

### 🩺 Médicos
- POST /medics → cadastrar.
- GET /medics → listar ativos (paginações).
- PUT /medics → atualizar.
- DELETE /medics/{id} → inativar.

### 👨‍🦱 Pacientes
- POST /patients → cadastrar.
- GET /patients → listar ativos (paginações).
- PUT /patients → atualizar.
- DELETE /patients/{id} → inativar.

### 📅 Consultas
- POST /appointments → agendar consulta.
- DELETE /appointments → cancelar consulta.

---

## 📦 Exemplos de Requisições

### 🔑 Login (Usuário)
**POST /login**

Request:
```json
{
  "login": "admin@gusmed.com",
  "senha": "123456"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 🩺 Cadastrar Médico
**POST /medics**

Request:
```json
{
  "nome": "Dr. João",
  "email": "joao@gusmed.com",
  "crm": "123456",
  "telefone": "11999999999",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua A",
    "bairro": "Centro",
    "cep": "00000-000",
    "cidade": "São Paulo",
    "uf": "SP",
    "numero": "3325",
    "complemento": "Apto 10"
  }
}
```

>[!Important]
>- Conferir [ENUM](src/main/java/com/med/gus/api/domain/medic/Especialidade.java) de especialidade.
>- O `numero` e o `complemento` são opcionais.

---

### 👨‍🦱 Cadastrar Paciente
**POST /patients**

Request:
```json
{
  "nome": "Maria",
  "email": "maria@gusmed.com",
  "telefone": "11888888888",
  "cpf": "12345678900",
  "endereco": {
    "logradouro": "Rua B",
    "bairro": "Bela Vista",
    "cep": "11111-111",
    "cidade": "São Paulo",
    "uf": "SP"
  }
}
```

>[!Important]
>- O `numero` e o `complemento` são opcionais.

---

### 📅 Agendar Consulta
**POST /appointments**

Request:
```json
{
  "idPatient": 2,
  "idMedic": 1,
  "data": "2025-08-05T16:00"
}
```

Para selecionar um médico aleatório, basta enviar a especialidade ao invés do id do médico:

```json
{
	"idPatient": 1,
	"especialidade": "CARDIOLOGIA",
	"data": "2025-08-05T16:00"
}
```

---

### 📅 Cancelar Consulta
**DELETE /appointments**

Request:
```json
{
  "idConsulta": 10,
  "motivo": "PACIENTE_DESISTIU" 
}
```

>[!Important]
>Conferir [ENUM](src/main/java/com/med/gus/api/domain/appointment/MotivoCancelamento.java) de motivos

---

## 🧩 Regras de Negócio (Validações)

Durante o **agendamento**, são aplicadas regras automáticas, por exemplo:
- Médico deve estar ativo.
- Paciente não pode ter outra consulta no mesmo dia.
- Médico não pode ter conflito de horário.
- Consulta não pode ser agendada em horários inválidos.

Durante o **cancelamento**, são verificadas:
- Existência da consulta.
- Motivo obrigatório para cancelamento.

---

## 📑 Documentação

A documentação interativa (Swagger) é gerada automaticamente em:

http://localhost:8080/swagger-ui.html

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos
- **Java 21+**
- **Maven**
- **MySQL** rodando

### Passos
1. Clone o repositório:
   git clone https://github.com/SEU-USUARIO/gusmed-api.git

2. Configure o banco no arquivo `application.properties`:
   spring.datasource.url=jdbc:mysql://localhost/seu_db
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha

3. Rode as migrations (Flyway gera tabelas automaticamente).
4. Execute o projeto:
   mvn spring-boot:run

---

## 📬 Contato

👤 **Augusto**  
✉️ augustocesaaaar@gmail.com  

