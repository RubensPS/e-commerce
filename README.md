

# ecommerce-api

API para o projeto de e-commerce


## Arquitetura

* Gradle
* Java 17
* Spring Boot
* Spring Data
* Spring JPA
* Docker
* PostgreSQL

## Execução

### Clone

```console
git clone https://github.com/RubensPS/e-commerce
```



## Requisições
- Todas as requisições estão documentadas no swagger. Após inicialização do projeto acesse o endereço: http://localhost:8080/swagger-ui.html

- Por favor, atentar a todas as observações das requisições.


<details><summary><b>Rotas dos produtos (Clique aqui)</b></summary>

> Utiliza-se o estilo RESTFull portanto todos os caminhos partem do path: **/produtos**

1. Caminho=/ , Metodo=**POST**
   ```
   Body:
   ```	
   ```json
   {
       "nome": "string",
       "descricao": "string",
       "valor": big decimal,
       "codigoBarra": "string",
       "nomeFabricante": "string",
       "peso": 0,
       "pesoUnidadeMedida": "string"
     }
   ```
2. caminho=*/alterar/{codigoBarra}*, Método=**POST**
   ```
   Body:
   ```	
   ```json
     {
       "nome": "string",
       "descricao": "string",
       "valor": big decimal,
       "codigoBarra": "string",
       "nomeFabricante": "string",
       "peso": 0,
       "pesoUnidadeMedida": "string"
     }
   ```
3. caminho=*/consultar/{codigoBarra}*, Método=**GET**
   ```
   Consulta um produto através do seu Código de Barra. 
   Retorna um JSON com as informações do produto.
   ```	
4. Caminho=/{codigoBarra}, Método=**DELETE**
   ```
   Deleta um determinado produto através do código de Barra.
   ```
5. Caminho=/, Método=**GET**
   ```
   Traz todos os produtos cadastrados na base de dados.
   ```
   
</details>

<details><summary><b>Rotas dos usuários (Clique aqui)</b></summary>

> Utiliza-se o estilo RESTFull portanto todos os caminhos partem do path: **/usuarios**

1. Caminho=/, Método=**POST**
    ```
    Body:
    ```
   ```json
   {
   "nomeUsuario" : "string",
   "password" : "string",
   "funcao" : "ADMIN",
   "nome" : "string",
   "dataNascimento" : "1984-04-23"
   }
   ```
2. Caminho=/, Método=**PATCH**
   ```
   Altera um usuario;
   ```
3. Caminho=/{id}, Método=**DELETE**
   ```
   Deleta um determinado usuário na base de dados a partir de seu ID.
   ```
4. Caminho=/consultarNome/{nomeUsuario}, Método=**GET**
   ```
   Consulta um usuário a partir do nome.
   ```
5. Caminho=/consultar/{id}, Método=**GET**
   ```
   Consulta um usuário a partir do id.
   ```
</details>


<details><summary><b>Rotas do carrinho (Clique aqui)</b></summary>

> Utiliza-se o estilo RESTFull portanto todos os caminhos partem do path: **/carrinho**

1. Caminho=/, Método=**POST**
    ```
    Cadastra um carrinho com produto;
    ```
    ```json
      {
        "usuarioId": 1,
        "produtoId": 2,
        "quantidade": 2
      }
    ```
2. Caminho=/usuario/{usuarioId}, Método=**GET**
   ```
   Obtem o carrinho de um usuario baseado no id. NENHUM BODY É NECESSÁRIO.
   ```
</details>

<details><summary><b>Rotas dos fabricantes (Clique aqui)</b></summary>
> Utiliza-se o estilo RESTFull portanto todos os caminhos partem do path: **/fabricantes**







</details>




## Detalhes


1. Para comunicação foi usado a arquitetura REST, baseado no Restful. Os serviços recebem e respondem JSON

2. Para banco de dados foi usado banco em memória h2, tanto para o projeto quanto para os testes.

3. Na documentação foi pensado no swagger por ser uma ferramenta de facil implementação e usabilidade.

4. Para testes foi usado Junit4, fazendo testes diretamente nos endpoints, testes de integração, para tentar simular mais uma chamada e as respostas mockados.

5. Nas responstas foi escolhido o objeto ResponseEntity do spring para gerenciar todo o objeto da resposta.

6. Para testes eu estava usando o INSOMNIA com os endpoints informados acima.

>Obrigado pela oportunidade :)