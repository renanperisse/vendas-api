# Requisições HTTP
### [POST] Cadastrar Vendedor
/vendedores
```sh
{
    "nome": "Renan"
}
```
#
### [POST] Gerar Venda
/venda
```sh
{
    "idVendedor": 6,
    "valor": 6.0
}
```
#
### [GET] Listar vendas e vendedores através das datas estipuladas nos parâmetros
/vendedores/total-vendas?dataInicio=12/04/2024&dataFim=15/04/2024
