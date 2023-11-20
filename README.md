# Luiza Labs - Desafio Técnico

Projeto desenvolvido para o desafio técnico de desenvolvedor backend da Luiza Labs.


## Stack utilizada:

* Linguagem: [`Java 21`](https://docs.oracle.com/en/java/)
* Compilação: [`Maven`](https://maven.apache.org/guides/)
* Framework: [`Spring Boot`](https://spring.io/projects/spring-boot)
* Cache: [`Spring Cache Redis`](https://github.com/ben-manes/caffeine)
* Banco de dados: [`H2`](h2database.com) / [`MySQL`](https://www.mysql.com) / [`Flyway`](https://flywaydb.org)
* Documentação: [`Swagger`](https://swagger.io)
* Testes:
    * Teste de unidade [`jUnit5`](https://junit.org/junit5/docs/current/user-guide/)
    * Ferramenta para mocks [`Mockk`](https://mockk.io)
    * Ferramenta para cobertura de tests [`Jacoco`](https://www.jacoco.org)
* CI / CD: [`GitHub Actions`](https://github.com/features/actions)

## Arquitetura

A arquitetura do projeto foi baseada no modelo de Clean Architecture, onde a aplicação é dividida em módulos, sendo eles:


* **Configuration** - Configurações do Framework, injeção de dependências, define
  as implementações e realiza a união do projeto.

* **Core** - Regra de negócio, esse módulo é livre de qualquer tipo de framework
  para deixar a regra independente de tecnologia e de fácil manutenção, nessa camada
  temos duas vertentes:
    * **entity** - Entidades, objetos que representam o negócio
    * **use case** - Casos de uso do projeto, como um `Service`, implementa
      funcionalidades e regras especificas de cada funcionalidade.

* **Data Provider** - Camada responsável em fornecer os dados para o `core`
  implementando suas interfaces.

* **Entry Points** - Responsável em fornecer os end-points, essa
  camada trata os dados que o usuário envia processa utilizando os `use cases`
  do `core` e trata seu retorno.

## Executando o projeto

Para executar o projeto é necessário ter o 'Docker' instalado na máquina, após isso basta executar o comando abaixo:

```shell
docker compose up
```

Após a execução do comando acima, o projeto estará disponível no endereço: `http://localhost:8080`

## Documentação

A documentação da API está disponível no endereço: `http://localhost:8080/swagger-ui.html`

No diretório do projeto também é possível encontrar os arquivos da documentação no Postman.