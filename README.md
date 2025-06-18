# EasyHealth
Projeto interdisciplinar para Faculdade de Tecnologia de Cotia. 

A EasyHealth API fornece o back-end para um aplicativo focado em promover um estilo de vida mais saudável. A plataforma permite que usuários (pacientes e profissionais) se cadastrem, interajam e gerenciem atividades de saúde. A principal funcionalidade é a geração de tarefas (hábitos) personalizadas para os usuários com base em seus objetivos de saúde pré-definidos, como "Perder Peso" ou "Reduzir Estresse".

✨ Principais Funcionalidades
Autenticação de Usuários: Sistema de cadastro e login para diferentes perfis de usuário (Paciente e Profissional).
Herança de Perfis: Utiliza uma estrutura de herança com uma entidade Person base, da qual Paciente e Profissional herdam atributos comuns.
Catálogo de Hábitos: O sistema possui um catálogo pré-definido e expansível de Objetivos e Hábitos saudáveis.
Geração de Tarefas Personalizadas: Um usuário pode selecionar um objetivo e o sistema automaticamente cria uma lista de tarefas (hábitos) correspondentes para ele.
CRUD de Tarefas: Ciclo completo de criação, leitura, atualização de status e deleção (lógica) das tarefas de um usuário.
Agendamento de Consultas: Funcionalidade para que pacientes agendem consultas com profissionais.
Outros Módulos: Inclui a base para funcionalidades de Chat, Pagamentos e gerenciamento de Tokens.
🚀 Arquitetura e Tecnologias
Este projeto foi construído utilizando as seguintes tecnologias:

Java 17+
Spring Boot 3+
Spring Data JPA / Hibernate: Para persistência de dados e mapeamento objeto-relacional.
Maven/Gradle: Como gerenciador de dependências e build.
Banco de Dados: Compatível com bancos relacionais como PostgreSQL, MySQL ou H2 (para ambiente de desenvolvimento).
📂 Estrutura do Projeto
O código-fonte está organizado nos seguintes pacotes principais:

config: Classes de configuração, como o DataInitializer que popula o banco de dados na inicialização.
controller: Camada de API REST, responsável por expor os endpoints e receber as requisições HTTP.
dto: (Data Transfer Objects) Objetos simples para transferir dados entre o cliente e o servidor.
enums: Enumerações utilizadas no projeto (Status, Gênero, etc.).
model: As entidades JPA que representam o modelo de dados e as tabelas do banco.
repository: Interfaces do Spring Data JPA para acesso e manipulação dos dados no banco.
service: Camada de serviço, onde reside a lógica de negócio da aplicação.
⚙️ Configuração do Ambiente
Para executar este projeto localmente, siga os passos abaixo:

Clone o Repositório:

Bash

git clone [URL_DO_SEU_REPOSITORIO]
cd easyhealthapi
Pré-requisitos:

Java JDK 17 ou superior instalado.
Maven ou Gradle instalado.
Configure o Banco de Dados:

Abra o arquivo src/main/resources/application.properties.
Adicione as configurações para o seu banco de dados. Abaixo um exemplo para PostgreSQL:
<!-- end list -->

Properties

# Configuração do Banco de Dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/easyhealthdb
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuração do Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
Importante: A propriedade spring.jpa.hibernate.ddl-auto=update fará com que o Hibernate atualize o schema do banco com base nas suas entidades. Na primeira execução, ele criará todas as tabelas.
▶️ Executando a Aplicação
Após configurar o ambiente, você pode iniciar a aplicação com o seguinte comando Maven:

Bash

./mvnw spring-boot:run
Ou, se preferir, execute a classe principal EasyhealthapiApplication.java a partir da sua IDE. A API estará disponível em http://localhost:8181.

📖 Documentação da API
Abaixo estão os principais endpoints implementados.

Autenticação (/auth)
Método	| Endpoint |	Descrição	| Parâmetros/Headers |	Corpo da Requisição (Exemplo)
POST	/auth/signup-paciente	Cadastra um novo paciente.	(nenhum)	{"nome": "...", "email": "...", "senha": "...", ...}
POST	/auth/signup-profissional	Cadastra um novo profissional.	(nenhum)	{"nome": "...", "email": "...", "senha": "...", ...}
POST	/auth/signin	Realiza o login e retorna um token.	(nenhum)	{"email": "...", "senha": "..."}
POST	/auth/check	Verifica se um token de autenticação ainda é válido.	Header: token: SEU_TOKEN_AQUI	(nenhum)
POST	/auth/signout	Invalida um token, efetivamente realizando o logout do usuário.	Header: token: SEU_TOKEN_AQUI	(nenhum)

Exportar para as Planilhas


Exportar para as Planilhas
Tarefas e Objetivos
Método	Endpoint	Descrição	Corpo da Requisição
GET	/objectives	Lista todos os objetivos de saúde disponíveis no catálogo.	(nenhum)
POST	/tasks/person/{id}/assign-objective?objectiveId={id}	Cria as tarefas de um objetivo para uma pessoa específica.	(nenhum)
GET	/tasks/person/{id}	Lista todas as tarefas de uma pessoa específica.	(nenhum)
PUT	/tasks/{id}/status	Atualiza o status de uma tarefa específica.	{"status": "DONE"}
DELETE	/tasks/{id}	Realiza a deleção lógica de uma tarefa.	(nenhum)

Exportar para as Planilhas
Consultas (/consultas)
Método	Endpoint	Descrição	Corpo da Requisição
POST	/consultas/agendar	Agenda uma nova consulta entre um paciente e um profissional.	{"pacienteId": 1, "profissionalId": 2, ...}


