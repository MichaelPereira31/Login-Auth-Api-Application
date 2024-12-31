# API RESTful de Autenticação

## Descrição
Esta é uma API RESTful de autenticação desenvolvida em Java. A API permite:

- Registro de usuários.
- Login de usuários.
- Renovação de tokens de acesso.
- Gerenciamento de usuários autenticados.

A API utiliza JWT (JSON Web Tokens) para autenticação e Spring Boot como framework principal.

---

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento da API.
- **Spring Security**: Implementação de segurança.
- **JWT**: Para autenticação baseada em tokens.
- **Maven**: Gerenciamento de dependências.
- **H2**: Banco de dados em memória (para ambiente de desenvolvimento).
- **PostgreSQL**: Banco de dados para ambiente de produção.

---

## Requisitos

- Java 11 ou superior.
- Maven 3.6 ou superior.
- Banco de dados PostgreSQL (opcional para ambiente de desenvolvimento, se preferir usar outro banco de dados).

---

## Configuração

### 1. Clonar o Repositório

```bash
$ git clone https://github.com/usuario/repo-api-auth-java.git
$ cd repo-api-auth-java
```

### 2. Configurar o Arquivo `application.properties`

No diretório `src/main/resources`, edite o arquivo `application.properties` para incluir as configurações do banco de dados e a chave secreta do JWT:

```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configuração JWT
jwt.secret=chave-secreta-super-segura
jwt.expiration=3600000  # Em milissegundos (1 hora)
```

### 3. Compilar e Executar

Para compilar o projeto:

```bash
$ mvn clean install
```

Para executar a aplicação:

```bash
$ mvn spring-boot:run
```

---
