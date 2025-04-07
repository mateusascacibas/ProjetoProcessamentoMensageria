# 📟 Processamento de Pedidos - Mensageria Assíncrona com Spring Boot + JWT + RabbitMQ + PostgreSQL

> Um projeto profissional que simula um sistema completo de processamento de pedidos assíncronos, utilizando mensageria com RabbitMQ, persistência com PostgreSQL e autenticação moderna com OAuth2 + JWT.

---

## 📦 Tecnologias Utilizadas

- ✅ **Java 21**
- ✅ **Spring Boot 3**
- ✅ **Spring Security**
- ✅ **Spring Authorization Server**
- ✅ **OAuth2 Client Credentials + JWT**
- ✅ **RabbitMQ (via Docker)**
- ✅ **PostgreSQL (via Docker)**
- ✅ **JPA / Hibernate**
- ✅ **Swagger / OpenAPI**
- ✅ **Jakarta**
- ✅ **Maven**
- ✅ **Postman** (para testes)

---

## 🧐 Objetivo do Projeto

Esse projeto tem como foco a **simulação de um sistema real** de mensageria para pedidos, com as seguintes funcionalidades:

- 📅 Recebimento de pedidos via API REST
- 🛅 Processamento assíncrono com RabbitMQ
- 🔐 Autenticação segura com OAuth2 (Client Credentials) + JWT
- 🏦 Persistência dos pedidos com PostgreSQL
- 🛡️ Proteção de endpoints via `Bearer Token`
- 🤔 Validação de regras de negócio

---

## 🧱 Estrutura do Projeto

```bash
src/main/java
└── com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento
    ├── config                         # Configurações de segurança, OAuth2 e RabbitMQ
    │   ├── AuthorizationServerConfig.java
    │   ├── KeyGeneratorUtils.java
    │   ├── SecurityConfig.java
    │   └── RabbitMQConfig.java
    ├── controller                    # Endpoints REST
    │   └── PedidoController.java
    ├── consumer                      # Consumo assíncrono dos pedidos via fila
    │   └── PedidoConsumer.java
    ├── service                       # Regras de negócio e publicação na fila
    │   ├── PedidoService.java
    │   └── PedidoPublisher.java
    ├── dto                           # Objetos de Transferência de Dados
    │   ├── PedidoDTO.java
    │   └── ItemDTO.java
    ├── entity                        # Entidades JPA
    │   ├── PedidoEntity.java
    │   └── Repositório
    │       └── PedidoRepository.java
    ├── core                          # Lógica de negócio e validações
    │   ├── Pedido.java
    │   ├── ItemPedido.java
    │   ├── ValidadorPedido.java
    │   └── Validadores.java
    └── ProjetoProcessamentoApplication.java
```

---

## 🔄 Fluxo de Funcionamento

```bash
1. Cliente envia POST /pedidos com token JWT
2. Pedido é validado e persistido no PostgreSQL
3. Pedido é publicado na fila RabbitMQ
4. PedidoConsumer consome da fila e processa o pedido
5. Logs no console confirmam cada etapa
```

---

## 🔐 Autenticação e Segurança: OAuth2 + JWT

O projeto usa **Spring Authorization Server** na própria aplicação.

### 🎟️ Geração do Token

```bash
curl --location 'http://localhost:8080/oauth2/token' \
--header 'Authorization: Basic bWF0ZXVzLWNsaWVudDpzZW5oYQ==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'scope=read'
```

### 🔒 Acesso ao endpoint protegido

```bash
curl --location 'http://localhost:8080/pedidos' \
--header 'Authorization: Bearer SEU_TOKEN_AQUI' \
--header 'Content-Type: application/json' \
--data '{
  "cliente": "Mateus",
  "itens": [
    { "nome": "Mouse", "quantidade": 1 },
    { "nome": "Teclado", "quantidade": 2 }
  ]
}'
```

---

## 📚 Documentação com Swagger

O Swagger está disponível automaticamente ao rodar a aplicação.

- Acesse via navegador: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Toda a API está documentada com schemas de entrada e saída

---

## 📊 Banco de Dados - PostgreSQL (Docker)

### Criar container PostgreSQL:

```bash
docker run --name postgres-processamento \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_DB=processamento \
  -p 5432:5432 \
  -d postgres
```

---

## 🚀 RabbitMQ - Docker

```bash
docker run -d \
  --name rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=guest \
  -e RABBITMQ_DEFAULT_PASS=guest \
  rabbitmq:3-management
```

Acesse: [http://localhost:15672](http://localhost:15672) (usuário: guest / senha: guest)

---

## 🧪 Testes Unitários

O projeto inclui testes unitários com `@WebMvcTest` e `Mockito` para garantir o comportamento correto dos endpoints REST e regras de negócio.

Para executar:

```bash
./mvnw test
```

---

## 🐳 Dockerfile + Docker Compose

Crie a imagem da aplicação com o seguinte `Dockerfile`:

```dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/ProjetoProcessamento-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

E o `docker-compose.yml`:

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - postgres
      - rabbitmq
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/processamento
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: admin
      SPRING_RABBITMQ_HOST: rabbitmq

  postgres:
    image: postgres
    container_name: postgres
    restart: always
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: processamento
      POSTGRES_PASSWORD: admin

  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
```

Para subir tudo:

```bash
docker-compose up --build
```

---

## 📊 Exemplo de Logs

```bash
⏳ Aguardando pedidos...
📥 Pedido enviado para a fila: Mateus
✅ Pedido processado via RabbitMQ: Mateus
```

---

## 🚀 Como Rodar Localmente

```bash
# Clonar o repositório
git clone https://github.com/mateusascacibas/ProjetoProcessamentoMensageria.git

# Entrar no projeto
cd ProjetoProcessamento

# Subir tudo com Docker
docker-compose up --build

# Ou rodar manualmente (se quiser)
./mvnw spring-boot:run
```

---

## 🔮 Melhorias Futuras

- [ ] Interface frontend com Angular ou React
- [ ] Fila de Dead Letter (DLQ)
- [ ] Emails de notificação ao cliente

---

## 👨‍💼 Autor

Desenvolvido por **Mateus Ascacibas**

- 📧 [LinkedIn](https://www.linkedin.com/in/mateus-ascacibas/)
- 🏠 Projeto com foco profissional e escalável
