# 🧾 Processamento de Pedidos - Mensageria Assíncrona com Spring Boot + JWT

> Um projeto profissional que simula um sistema de processamento de pedidos assíncronos, utilizando mensageria em memória e autenticação moderna com OAuth2 + JWT.

---

## 📦 Tecnologias Utilizadas

- ✅ **Java 21**
- ✅ **Spring Boot 3**
- ✅ **Spring Security**
- ✅ **Spring Authorization Server**
- ✅ **OAuth2 Client Credentials + JWT**
- ✅ **Jakarta**
- ✅ **Maven**
- ✅ **Postman** (para testes)

---

## 🧠 Objetivo do Projeto

Esse projeto tem como foco a **simulação de um sistema real** de mensageria para pedidos, com as seguintes funcionalidades:

- 📥 Enfileiramento de pedidos via REST
- 🧵 Processamento assíncrono de pedidos
- 🔐 Segurança com OAuth2 (Client Credentials) + JWT
- 🛡️ Proteção de endpoints via `Bearer Token`
- 🧪 Testes reais com Postman
- ✅ Validação de regras de negócio para pedidos

---

## 🧱 Estrutura do Projeto

```bash
src/main/java
└── com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento
    ├── config                         # Configurações de segurança e OAuth2
    │   ├── AuthorizationServerConfig.java
    │   ├── CustomAuthenticationEntryPoint.java
    │   ├── KeyGeneratorUtils.java
    │   └── SecurityConfig.java
    ├── consumer                      # Processo assíncrono de consumo da fila
    │   └── PedidoConsumer.java
    ├── controller                    # Entrada de pedidos via REST
    │   └── PedidoController.java
    ├── core                          # Domínio do negócio
    │   ├── ItemPedido.java
    │   ├── Pedido.java
    │   ├── Validadores.java
    │   └── ValidadorPedido.java
    ├── dto                           # DTOs utilizados nas requisições
    │   ├── ItemDTO.java
    │   └── PedidoDTO.java
    ├── queue                         # Simulação da fila de mensagens (in memory)
    │   └── FilaMensagens.java
    ├── service                       # Lógica de negócios do pedido
    │   └── PedidoService.java
    └── ProjetoProcessamentoApplication.java
```

## 🔁 Fluxo de Funcionamento

```bash
✅ Você envia um pedido via endpoint REST (POST /pedidos)
🔐 Endpoint exige token JWT gerado via fluxo OAuth2 Client Credentials
📥 Pedido é validado e enfileirado (via FilaMensagens)
⏳ Um PedidoConsumer roda em segundo plano e processa os pedidos da fila
🧾 Logs no console mostram a simulação do processamento
```

## 🔐 Segurança: OAuth2 + JWT
Este projeto usa o Spring Authorization Server embutido na mesma aplicação.

## 🎫 Gerando um Token JWT
Faça uma requisição para /oauth2/token:

```bash
curl --location 'http://localhost:8080/oauth2/token' \
--header 'Authorization: Basic bWF0ZXVzLWNsaWVudDpzZW5oYQ==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'scope=read'
```
🟢 Retorno esperado:

```bash
{
  "access_token": "eyJraWQiOiJ...jwt...token...",
  "token_type": "Bearer",
  "expires_in": 3600,
  "scope": "read"
}
```

## 🔒 Endpoint Protegido
Use o token recebido acima para fazer requisições ao endpoint /pedidos:

```bash
curl --location 'http://localhost:8080/pedidos' \
--header 'Authorization: Bearer SEU_TOKEN_AQUI' \
--header 'Content-Type: application/json' \
--data '{
  "cliente": "Teste",
  "itens": [
    { "nome": "Produto Teste", "quantidade": 1 }
  ]
}'
```

## 🧪 Exemplo de Logs
```bash
⏳ Aguardando pedidos...
📥 Pedido enviado para a fila: Teste
✅ Pedido processado para cliente: Teste
```

## 🚀 Como Rodar Localmente

# Clonar o repositório
git clone [https://github.com/seu-usuario/ProjetoProcessamento.git](https://github.com/mateusascacibas/ProjetoProcessamentoMensageria.git)

# Entrar no projeto
cd ProjetoProcessamento

# Rodar a aplicação
./mvnw spring-boot:run

## 🔮 Melhorias Futuras
```bash
Integração com RabbitMQ ou Kafka
Persistência dos pedidos em banco de dados (PostgreSQL ou MySQL)
Documentação OpenAPI (Swagger)
Interface frontend com Vue.js ou React
```

👨‍💻 Autor
Desenvolvido com 💙 por Mateus Ascacibas
📫 LinkedIn (https://www.linkedin.com/in/mateus-ascacibas/)
