# CRUD Simples

Um CRUD bem simples, experimental e didático, na forma de API REST.

> As classes do aplicativo no repositório estão devidamente comentadas.


## Requisitos

 - IDE [IntelliJ CE](https://www.jetbrains.com/pt-br/idea/download/) ou outra compatível com Java e Spring Boot
 - [Postman](https://www.postman.com/downloads/)
 - [Oracle JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) ou [OpenJDK 21](https://adoptium.net/temurin/releases/?os=any&arch=any&version=21)
 - Navegador Web
 - Acima de 8GB de espaço em disco


## IntelliJ

Crie o projeto Java no IntelliJ CE com os seguintes parâmetros:

 - New Project: `Java`
 - Name: `crud_simples`
 - Location: `~/Documents/java`
 - ☑ Create Git repository
 - Build system: `Mavin`
 - JDK: `temurin-21`
 - GroupId: `br.senac.rj`
 - ArtifactId: `crud`


## Spring Initializr

Crie o projeto Spring Boot no **Spring Initializr**: 

 - https://start.spring.io/ 
 - Project: `Mavin`
 - Language: `Java`
 - Spring Boot: `3.5.0`
 - Project Metadata:
    - Group: `br.senac.rj`
    - Artifact: `crud`
    - Name: `CRUD Simples`
    - Description: `Um CRUD bem simples na forma de PI REST`
    - Package name: `br.senac.rj.crud`
    - Packaging: `Jar`
    - Java: `21`
 - Dependencies:
   - Spring Web
   - Spring Data JPA
   - H2 Database
   - Lombok
   - Validation


## Merge

Baixe e descompacte o arquivo baixado do **Spring Initializr** e cole sobre o projeto do **IntelliJ CE** para gerar o projeto **Java Spring Boot**.


## `application.properties`

Configure o `application.properties` para usar o **H2 Database**:

```yml
spring.application.name=CRUD Simples

# Configurações do H2 Database
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:file:./data/crudsimplesdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

## Postman

Usamos o aplicativo **Postman** para testar as requisições e respostas da API.

Os requests estão em `CRUD Simples.postman_collection.json`.
Baixe o arquivo e importe no Postman.


## Model e Repository

O aplicativo é um CRUD genérico que armazena dados básicos de qualquer "**COISA**".
Vamos modelar a entidade `COISA` com os seguintes atributos:

```sql
id INT PRIMARY KEY AUTO_INCREMENT,
date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
name VARCHAR(255),
description TEXT,
location VARCHAR(255),
imageURL VARCHAR(255),
price DECIMAL(10,2),
status ENUM('ON', 'OFF')
```

O modelo está em `model.Coisa` e já criamos, em seguida, o _repository_ do mesmo em `repository.CoisaRepository`.


## Populando o Banco de Dados

Inicie o aplicativo para fazer o primeiro teste e também popular a tabela "COISA" com alguns dados _fake_ para testes iniciais.

Acesse, configure e se conecte ao **H2 Console**, para acessar o **H2 Database**.

Obtenha o arquivo `doc/crudsimples.sql` e rode o script no **H2 Console**.


## Formatação do response

A classe `tool.ApiResponse`, devidamente comentada, formata os _JSON_ de _response_, o que melhora a padronização.


## Cadastrar novo registro (CREATE)

 - Endpoint: `POST /api/create`
 - Body: `raw JSON` (Exemplo abaixo)
   ```json
   {
       "name": "Meia Falante",
       "description": "Uma meia que, misteriosamente, sussurra segredos antigos quando está seca. Cuidado com o que ela sabe!",
       "location": "Dentro do cesto de roupas sujas da Sra. Doubtfire",
       "imageUrl": "https://picsum.photos/215",
       "price": 123.45
   }
   ```
> Os valores dos campos `id`, `date` e `status` são gerados pelo aplicativo.

Como não enviamos todos os campos, vamos criar o DTO `dto.CoisaRequestDTO` que aproveitamos para validar os dados da requisição usando `Spring Boot Validator`. A classe está devidamente comentada.

O controller para este endpoint está em `controller.CoisaCadastroController`.


## Listar todos os registros (READ All)

Listar todos os registros ordenados pela `date` mais recente e com `status.ON`.

- Endpoint: `GET /api/read`
- Body: `none`

Vamos retornar apenas os campos `id`, `name` e `imageURL`, para isso, vamos criar o _DTO_ que está em `dto.CoisaListagemDTO`.

Na sequência, crie o `controller.CoisaListagemController` que atende aos requisitos.

> Teste este e todos os _endpoint_ usando o **Postman**.


## Listar um registro único (READ)

 - Endpoint: `GET /api/read/{id}`
 - Body: `none`

Este endpoint terá as seguintes características:

 - Receberá um `{id}` como parte da URL
 - Buscará a `COISA` correspondente a este `id`
 - Retorna o registro completo da `COISA`
 - Apenas se `status.ON`

Este controller está em `controller.CoisaDetalheController`.


## Atualizar registro (UPDATE)

Este endpoint receberá um `{id}` como parte da URL, para identificar o registro a ser atualizado.

 - Endpoint: `PUT /api/update/{id}`
 - Body: raw JSON (Exemplo abaixo)
> Campos `id`, `date` e `status` não são atualizados.

Vamos reaproveitar o `dto.CoisaRequestDTO` que já criamos, pois ele já contém os campos que podem ser atualizados (name, description, location, imageUrl, price).

O endpoint está configurado em `controller.`


## Apagar registro (DELETE)

Neste endpoint, não vamos realmente apagar a "COISA", vamos sim marcar o registro como "apagado", trocando `status.ON` para `status.OFF` neste.

 - Endpoint: `DELETE /api/delete/{id}`
 - Body: `none`

O `controller` deste endpoint está em `controller.CoisaDeleteController`.
