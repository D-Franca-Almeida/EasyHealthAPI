EasyHealth API
Projeto Interdisciplinar - Faculdade de Tecnologia de Cotia
A EasyHealth API é o back-end para uma plataforma de saúde e bem-estar, projetada para conectar Pacientes e Profissionais. O sistema permite o gerenciamento de hábitos saudáveis através de tarefas personalizadas, agendamento de consultas e um ambiente seguro de autenticação.

✨ Principais Funcionalidades
Autenticação Segura com JWT: Sistema de cadastro e login baseado em JSON Web Tokens (JWT) e integrado com Spring Security. As senhas são armazenadas de forma segura usando criptografia.
Perfis de Usuário com Herança: Utiliza uma estrutura de herança com uma entidade Person base, da qual Paciente e Profissional herdam atributos comuns, promovendo a reutilização de código.
Catálogo de Hábitos Dinâmico: O sistema possui um catálogo de Objetivos e Modelos de Hábito que é carregado na inicialização, permitindo que a lista de atividades saudáveis seja facilmente expandida no futuro.
Geração de Tarefas Personalizadas: Usuários podem selecionar um objetivo de saúde (ex: "Perder Peso"), e a API automaticamente cria uma lista de tarefas (hábitos) correspondentes para eles.
CRUD Completo de Tarefas: Ciclo completo de criação (assign), leitura, atualização de status (PENDING, DONE) e deleção lógica (DELETED) das tarefas de um usuário.
Agendamento de Consultas: Funcionalidade para que pacientes agendem consultas com profissionais.
Código Limpo com Lombok: Utilização extensiva do Lombok para reduzir código boilerplate e manter as classes de modelo e DTOs limpas e legíveis.
🚀 Arquitetura e Tecnologias
Linguagem: Java 21
Framework Principal: Spring Boot 3.4.3
Segurança: Spring Security 6+
Tokens: JSON Web Token (JWT) com a biblioteca JJWT
Persistência: Spring Data JPA / Hibernate
Banco de Dados: PostgreSQL (configurado para desenvolvimento)
Build e Dependências: Maven
Redução de Boilerplate: Lombok
📂 Estrutura do Projeto
config: Classes de configuração, como o DataInitializer que popula o banco.
controller: Camada REST que expõe os endpoints da API.
dto: (Data Transfer Objects) Classes para transferência de dados entre cliente e servidor.
enums: Enumerações utilizadas no projeto (Status, Gênero, etc.).
model: Entidades JPA que representam o modelo de dados.
repository: Interfaces Spring Data JPA para acesso ao banco de dados.
service: Camada de serviço contendo a lógica de negócio.
security: Pacote dedicado à configuração do Spring Security, filtro JWT e lógica de autenticação.
⚙️ Configuração do Ambiente
Clone o Repositório:

Bash

git clone [URL_DO_SEU_REPOSITORIO]
cd easyhealthapi
Pré-requisitos:

Java JDK 21 ou superior.
Maven 3.6+ (ou use o Maven Wrapper incluído).
Configure o application.properties:

Abra o arquivo src/main/resources/application.properties.
Garanta que as configurações do banco de dados e do JWT estejam corretas.
<!-- end list -->

Properties

# Configuração do Servidor
server.port=8181

# Configuração do Banco de Dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/easyhealthdb
spring.datasource.username=postgres
spring.datasource.password=sua_senha_do_banco

# Configuração do Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuração do JWT (obrigatória para a aplicação iniciar)
jwt.secret=a4b8c1d9e2f3g4h5i6j7k8l9m0n1o2p3q4r5s6t7u8v9w0x1y2z3a4b5c6d7
jwt.expiration=86400000 # 24 horas em milissegundos
▶️ Executando a Aplicação
Abra um terminal na raiz do projeto.
Execute o comando usando o Maven Wrapper (recomendado):
Bash

./mvnw spring-boot:run
Ou, no Windows:
Bash

mvnw spring-boot:run
A API estará disponível em http://localhost:8181.

📖 Documentação da API
O fluxo de uso da API é:

Cadastre um usuário via /auth/signup-*.
Faça login via /auth/signin para obter um token JWT.
Use esse token JWT no cabeçalho Authorization de todas as requisições para endpoints protegidos.
Endpoints Públicos (Autenticação)
Método	Endpoint	Descrição	Corpo da Requisição (Exemplo)
POST	/auth/signup-paciente	Cadastra um novo paciente. A senha enviada será criptografada.	{"nome": "...", "email": "...", "senha": "plain_text", ...}
POST	/auth/signup-profissional	Cadastra um novo profissional. A senha enviada será criptografada.	{"nome": "...", "email": "...", "senha": "plain_text", ...}
POST	/auth/signin	Realiza o login e retorna um token JWT válido por 24 horas.	{"email": "...", "senha": "..."}

Exportar para as Planilhas
Endpoints Protegidos (Exigem Autenticação)
Para acessar os endpoints abaixo, inclua o token JWT no cabeçalho de cada requisição:
Header: Authorization
Valor: Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQi...

Método	Endpoint	Descrição	Corpo da Requisição (Exemplo)
GET	/objectives	Lista todos os objetivos de saúde disponíveis no catálogo.	(nenhum)
POST	/tasks/person/{id}/assign-objective?objectiveId={id}	Cria as tarefas de um objetivo para uma pessoa específica.	(nenhum)
GET	/tasks/person/{id}	Lista todas as tarefas de uma pessoa específica.	(nenhum)
PUT	/tasks/{id}/status	Atualiza o status de uma tarefa específica.	{"status": "DONE"}
DELETE	/tasks/{id}	Realiza a deleção lógica de uma tarefa.	(nenhum)
POST	/consultas/agendar	Agenda uma nova consulta.	{"pacienteId": 1, "profissionalId": 2, ...}
