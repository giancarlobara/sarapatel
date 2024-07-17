# Worker de Mensageria

## Como Rodar a Aplicação

Para executar esta aplicação, é necessário criar um arquivo `.env` na raiz do projeto seguindo a estrutura do arquivo `.env.example` também presente na raiz. Este arquivo `.env` deve conter os dados de conexão com o AWS SQS e o serviço de mensageria.

**Observação:** O arquivo `.env` é essencial para executar o projeto tanto manualmente quanto pelo Docker.

### Rodar Manualmente

#### Recursos Necessários:
- Node.js
- npm ou yarn (recomendado)

#### Comando para Executar no Terminal:

Para iniciar a aplicação em modo de desenvolvimento:

- Usando npm:
``npm run start``
- Usando yarn:
``yarn start``

### Rodar Usando Docker

#### Recursos Necessários:
- Docker

#### Comando para Executar no Terminal:

Para iniciar a aplicação usando Docker, certifique-se de ter o arquivo `.env` na raiz do projeto e execute o seguinte comando:

``docker-compose --env-file .env up``
