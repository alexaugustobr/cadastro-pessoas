# API para Cadastro de Pessoas

Para rodar a aplicação é necessário ter o Docker instalado.

A imagem Docker referente a essa API pode ser obtida através do nome williammachado/api-pessoas.

Na raíz do projeto, é possível ter acesso a arquivos que facilitam a construção/execução da aplicação.

O arquivo Dockerfile é o que dá origem a imagem hospedada no Dockerhub pelo nome citado acima.

O arquivo docker-compose.yml irá construir a API com a dependência do MySQL. Ou seja, a aplicação estará pronta para uso apenas com o comando:

`docker-compose up`

## Makefile

O Makefile possibilita a construção, execução e teste da aplicação de forma mais simplificada.

### Banco para testes

Para executar os testes (integração e unitário), você precisará de uma instância MySQL também.

Para construir essa instância com o Makefile, utilize o comando:

`make test-db`

Isso irá construir a instância MySQL necessária para o seu cenário de testes.

### Rodar os testes

Uma vez que o banco está de pé, você pode utilizar o comando abaixo para executar a suite de testes:

`make tests`

### Executando a aplicação

Para rodar a aplicação, basta utilizar o comando:

`make run`

Isso irá rodar o docker-compose.

## Utilizando a aplicação

### Rotas

A aplicação é divida entre duas versões V1 e V2.

Para acessar a versão 1 da rota de pessoas:

`/v1/pessoas`

Para acessar a versão 2:

`/v2/pessoas`

Para acessar o source:

`{version}/source`

Para acessar a documentação Swagger:

`/swagger-ui/index.html`

### Autenticação

A autenticação utilizada é do tipo Basic e os dados para acesso são:

```
usuario=pessoa
senha=123
```
