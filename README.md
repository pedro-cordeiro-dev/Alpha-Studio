# AlphaStudio

## Visão Geral do Projeto

O AlphaStudio é uma aplicação web desenvolvida para gerenciar agendamentos e preços de serviços para veículos. O projeto é dividido em duas partes principais: um frontend intuitivo para a interação do usuário e um backend robusto que lida com a lógica de negócio e a persistência de dados.

## Tecnologias Utilizadas

### Frontend

O frontend do AlphaStudio foi desenvolvido utilizando tecnologias web padrão:

*   **HTML5:** Para a estrutura e conteúdo das páginas.
*   **CSS3:** Para estilização e design responsivo.
*   **JavaScript:** Para a lógica interativa e comunicação com o backend.

### Backend

O backend do AlphaStudio é construído com um conjunto de tecnologias Java e Spring Boot:

*   **Spring Boot:** Framework Java para construção de aplicações web e RESTful APIs.
*   **Spring Data JPA:** Para facilitar a interação com o banco de dados, abstraindo a complexidade do JDBC e Hibernate.
*   **Spring Security:** Framework para autenticação e autorização (configurado para permitir todas as requisições no momento, mas pronto para melhorias futuras).
*   **MySQL:** Sistema de gerenciamento de banco de dados relacional para persistência de dados.
*   **Maven:** Ferramenta de automação de build e gerenciamento de dependências.

### Banco de Dados

O banco de dados utilizado é o **MySQL**, que armazena todas as informações de agendamentos, preços de serviços e usuários administradores.

## Estrutura do Projeto

O repositório está organizado da seguinte forma:

```
Alpha Studio/
├── FRONT-END/
│   ├── index.html
│   ├── script.js
│   └── style.css
└── Back-End/
    └── Alpha-Studio/
        ├── pom.xml
        ├── src/
        │   ├── main/
        │   │   ├── java/com/example/Alpha_Studio/
        │   │   │   ├── Controller/
        │   │   │   ├── DTO/
        │   │   │   ├── Model/
        │   │   │   ├── Repository/
        │   │   │   ├── Service/
        │   │   │   └── config/
        │   │   └── resources/
        │   │       └── application.properties
        │   └── test/
        │       └── java/com/example/Alpha_Studio/
        └── ... (outros arquivos de build e configuração)
```

## Como Configurar e Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto AlphaStudio em sua máquina local.

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

*   **Java Development Kit (JDK) 17 ou superior**
*   **Maven 3.x ou superior**
*   **MySQL Server**
*   **Um IDE (Integrated Development Environment) como IntelliJ IDEA, Eclipse ou VS Code**

### 1. Configuração do Banco de Dados (MySQL)

1.  Certifique-se de que seu servidor MySQL esteja em execução.
2.  O backend está configurado para criar o banco de dados `alpha_studio_db` automaticamente se ele não existir. No entanto, você pode criar manualmente se preferir:

    ```sql
    CREATE DATABASE alpha_studio_db;
    ```

3.  Verifique as credenciais do banco de dados no arquivo `Back-End/Alpha-Studio/src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/alpha_studio_db?createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=12345678
    ```
    **Importante:** Altere `root` e `12345678` para o seu usuário e senha do MySQL, se forem diferentes.

### 2. Configuração e Execução do Backend

1.  Navegue até o diretório do backend:

    ```bash
    cd Alpha\ Studio/Back-End/Alpha-Studio
    ```

2.  Compile o projeto Maven:

    ```bash
    mvn clean install
    ```

3.  Execute a aplicação Spring Boot:

    ```bash
    mvn spring-boot:run
    ```

    O backend será iniciado na porta `8080` (ou a porta configurada no `application.properties`). Você pode acessar a documentação da API (Swagger UI) em `http://localhost:8080/swagger-ui.html`.

### 3. Execução do Frontend

O frontend é uma aplicação estática e pode ser executado diretamente em um navegador web:

1.  Navegue até o diretório do frontend:

    ```bash
    cd Alpha\ Studio/FRONT-END
    ```

2.  Abra o arquivo `index.html` em seu navegador preferido. Você pode simplesmente arrastar e soltar o arquivo no navegador ou usar um servidor web local (como o Live Server do VS Code) para servi-lo.

    Certifique-se de que o frontend esteja configurado para se comunicar com o backend na porta `8080` (ou a porta que você configurou).

## Contribuições

Este projeto foi desenvolvido com as seguintes contribuições:

*   **Frontend:** João Pedro Gomes Moraes
*   **Backend e Banco de Dados:** Pedro Lucas Saraiva Cordeiro

## Contato

Aberto para Sugestões para melhorar o projeto.



