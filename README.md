P# Movies AI Microservice

Microserviço Spring Boot que utiliza IA (OpenAI) para buscar filmes baseado em uma lista de atores fornecida.

## 🚀 Funcionalidades

- **Busca de Filmes por Atores**: Endpoint que recebe uma lista de atores e retorna filmes que contenham pelo menos um desses atores
- **Documentação Swagger**: Interface interativa para testar a API
- **Validação de Dados**: Validação automática dos dados de entrada
- **Tratamento de Erros**: Tratamento global de exceções com respostas padronizadas
- **Testes Unitários**: Cobertura completa de testes para controllers e services

## 🛠️ Tecnologias Utilizadas

- **Spring Boot 3.4.8**
- **Spring AI 1.0.0**
- **OpenAPI/Swagger 3**
- **Java 17**
- **Maven**
- **JUnit 5**
- **Mockito**

## ⚙️ Configuração

### 1. Variáveis de Ambiente

Defina a chave da API OpenAI como variável de ambiente:

```bash
export OPENAI_API_KEY=sua-chave-da-api-aqui
```

### 2. Configuração Customizada

O microserviço está configurado para usar o endpoint customizado da OpenAI:
- **Base URL**: `https://api.wallacewebs.com/v1`
- **Modelo**: `gpt-3.5-turbo`
- **Temperature**: `0.7`

## 🚀 Como Executar

### 1. Compilar e Executar

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### 2. Verificar se está funcionando

```bash
curl http://localhost:8080/api/movies/health
```

## 📖 Documentação da API

### Swagger UI
Acesse a documentação interativa em: `http://localhost:8080/swagger-ui.html`

### OpenAPI Docs
Documentação em JSON: `http://localhost:8080/api-docs`

## 🎯 Endpoints

### POST /api/movies/by-actors

Busca filmes baseado em uma lista de atores.

**Request Body:**
```json
{
  "actors": ["Leonardo DiCaprio", "Brad Pitt", "Margot Robbie"]
}
```

**Response (200 OK):**
```json
[
  {
    "title": "Once Upon a Time in Hollywood",
    "year": "2019",
    "genre": "Drama",
    "actors": ["Leonardo DiCaprio", "Brad Pitt", "Margot Robbie"],
    "plot": "Um ator de TV em declínio e seu dublê enfrentam um mundo em mudança em Hollywood."
  }
]
```

**Exemplo de uso com curl:**
```bash
curl -X POST http://localhost:8080/api/movies/by-actors \
  -H "Content-Type: application/json" \
  -d '{"actors": ["Leonardo DiCaprio", "Brad Pitt"]}'
```

### GET /api/movies/health

Endpoint de health check.

**Response:**
```
Movie service is running
```

## 🧪 Executar Testes

### Todos os testes
```bash
./mvnw test
```

### Apenas testes unitários
```bash
./mvnw test -Dtest="*Test"
```

### Apenas testes de integração
```bash
./mvnw test -Dtest="*IntegrationTest"
```

## 📋 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/wallacewebs/springai/
│   │   ├── SpringaiApplication.java          # Classe principal
│   │   ├── config/
│   │   │   └── SwaggerConfig.java            # Configuração do Swagger
│   │   ├── controller/
│   │   │   └── MovieController.java          # Controller da API
│   │   ├── dto/
│   │   │   ├── ActorRequest.java             # DTO de request
│   │   │   └── MovieResponse.java            # DTO de response
│   │   ├── exception/
│   │   │   └── GlobalExceptionHandler.java   # Tratamento global de erros
│   │   └── service/
│   │       └── MovieService.java             # Lógica de negócio
│   └── resources/
│       └── application.properties            # Configurações da aplicação
└── test/
    └── java/com/wallacewebs/springai/
        ├── SpringaiApplicationIntegrationTest.java
        ├── controller/
        │   └── MovieControllerTest.java
        └── service/
            └── MovieServiceTest.java
```

## 🔧 Configurações Importantes

### application.properties
```properties
# API OpenAI customizada
spring.ai.openai.base-url=https://api.wallacewebs.com/v1
spring.ai.openai.api-key=${OPENAI_API_KEY:your-api-key-here}
spring.ai.openai.chat.options.model=gpt-3.5-turbo
spring.ai.openai.chat.options.temperature=0.7

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs
```

## 🚨 Tratamento de Erros

A API retorna erros padronizados:

### Erro de Validação (400)
```json
{
  "title": "Erro de validação",
  "detail": "Os dados fornecidos são inválidos",
  "status": 400,
  "timestamp": "2025-01-29T10:30:00",
  "fieldErrors": {
    "actors": "A lista de atores não pode estar vazia"
  }
}
```

### Erro Interno (500)
```json
{
  "title": "Erro interno",
  "detail": "Erro ao processar resposta da IA",
  "status": 500,
  "timestamp": "2025-01-29T10:30:00"
}
```

## 📝 Exemplos de Teste

Use o arquivo `example-request.json` para testar:

```bash
curl -X POST http://localhost:8080/api/movies/by-actors \
  -H "Content-Type: application/json" \
  -d @example-request.json
```

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Contato

Wallace Webs - [wallacewebs.com](https://wallacewebs.com)
