# 🏥 EasyHealth API

**Projeto Interdisciplinar - Faculdade de Tecnologia de Cotia**

A **EasyHealth API** é o back-end de uma plataforma de saúde e bem-estar, desenvolvida para conectar **Pacientes** e **Profissionais**. O sistema promove hábitos saudáveis com tarefas personalizadas, agendamento de consultas e autenticação segura.

---

## ✨ Principais Funcionalidades

- 🔐 **Autenticação JWT Segura**  
  Cadastro e login usando JSON Web Token e Spring Security. As senhas são criptografadas.

- 👥 **Perfis com Herança**  
  Estrutura orientada a objetos com uma entidade base `Person`, herdada por `Paciente` e `Profissional`.

- 📚 **Catálogo de Hábitos Dinâmico**  
  Objetivos e modelos de hábitos carregados automaticamente ao iniciar o sistema.

- 📝 **Tarefas Personalizadas**  
  Com base nos objetivos de saúde (ex: *Perder Peso*), a API gera tarefas específicas para o usuário.

- ✅ **CRUD Completo de Tarefas**  
  Criar, listar, atualizar status (`PENDING`, `DONE`) e deletar logicamente tarefas.

- 📅 **Agendamento de Consultas**  
  Pacientes podem agendar consultas com os profissionais disponíveis.

- 🧹 **Código Limpo com Lombok**  
  Redução de *boilerplate* em modelos e DTOs com uso extensivo do Lombok.

---

## 🚀 Tecnologias e Arquitetura

| Categoria         | Tecnologia                  |
|------------------|-----------------------------|
| **Linguagem**     | Java 21                     |
| **Framework**     | Spring Boot 3.4.3           |
| **Segurança**     | Spring Security 6+, JWT     |
| **Persistência**  | Spring Data JPA, Hibernate  |
| **Banco de Dados**| PostgreSQL (via Docker)     |
| **Build Tool**    | Maven                       |
| **Outros**        | Lombok                      |

---

## 📂 Estrutura de Pastas

├── config # Configurações iniciais (ex: DataInitializer)
├── controller # Endpoints REST
├── dto # Data Transfer Objects
├── enums # Enumerações do sistema (ex: Status, Gênero)
├── model # Entidades JPA
├── repository # Interfaces de persistência com Spring Data JPA
├── service # Lógica de negócio
├── security # Autenticação e filtros de segurança JWT


---

## ⚙️ Como Rodar o Projeto

### 🔁 Clonando o repositório

```bash
git clone [https://github.com/seu-usuario/easyhealthapi.git](https://github.com/D-Franca-Almeida/EasyHealthAPI.git)
cd easyhealthapi

📦 Pré-requisitos
Java JDK 21+

Maven 3.6+ (ou usar o wrapper mvnw)

Docker e Docker.compose(Dentro do repositório)

🛠️ Configuração do application.properties
Atenção: este arquivo é apenas para uso acadêmico e não deve ser versionado. Use .gitignore.

# Servidor
server.port=8181

# Banco de Dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/easyhealthdb
spring.datasource.username=postgres
spring.datasource.password=sua_senha

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT
jwt.secret=a4b8c1d9e2f3g4h5i6...
jwt.expiration=86400000 # 24 horas

🐳 Subindo o Banco com Docker
Tenha o Docker instalado.

Na pasta do projeto, execute:
docker-compose up

Acesse o PgAdmin criado e configure o banco conforme o .properties.

▶️ Executando a API
Linux/Mac:
./mvnw spring-boot:run
Windows:
mvnw spring-boot:run

A aplicação estará disponível em:
➡️ http://localhost:8181

📖 Documentação da API
🔓 Endpoints Públicos
| Método | Endpoint                    | Descrição                |
| ------ | --------------------------- | ------------------------ |
| POST   | `/auth/signup-paciente`     | Cadastro de paciente     |
| POST   | `/auth/signup-profissional` | Cadastro de profissional |
| POST   | `/auth/signin`              | Login com retorno de JWT |

Exemplo de corpo da requisição:
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}

🔐 Endpoints Protegidos
Inclua o JWT no cabeçalho de cada requisição:

Authorization: Bearer SEU_TOKEN_AQUI

| Método | Endpoint                                             | Descrição                                |
| ------ | ---------------------------------------------------- | ---------------------------------------- |
| GET    | `/objectives`                                        | Lista de objetivos de saúde              |
| POST   | `/tasks/person/{id}/assign-objective?objectiveId={}` | Cria tarefas com base em objetivo        |
| GET    | `/tasks/person/{id}`                                 | Lista tarefas de um usuário              |
| PUT    | `/tasks/{id}/status`                                 | Atualiza status da tarefa (`DONE`, etc.) |
| DELETE | `/tasks/{id}`                                        | Deleção lógica de tarefa                 |
| POST   | `/consultas/agendar`                                 | Agenda uma nova consulta                 |

Exemplo de atualização de status:

{
  "status": "DONE"
}

📚 Swagger UI
A EasyHealth API já conta com documentação interativa gerada automaticamente pelo Swagger.

Após iniciar a aplicação, acesse:
http://localhost:8181/swagger-ui/index.html


📌 Contribuições
Este projeto é acadêmico e está aberto para melhorias e sugestões.
Sinta-se à vontade para abrir issues e pull requests!


🧑‍🏫 Créditos
## 👥 Equipe do Projeto

Este projeto foi desenvolvido como parte do Projeto Interdisciplinar da **FATEC Cotia**, com foco em soluções tecnológicas voltadas à saúde e bem-estar.

| Nome                          | Função                          |
|-------------------------------|----------------------------------|
| **Andre Luiz De França Junior** | Gerente de Projetos             |
| **Daniel França Almeida**       | Desenvolvedor Back-End          |
| **Giancarlo Sabatini**          | Desenvolvedor Front-End         |
| **Gustavo Pereira Queiroz**     | UX Designer & QA Tester         |
| **Vickybert Pessoa Freire**     | Professor Orientador / Demandante |

---



