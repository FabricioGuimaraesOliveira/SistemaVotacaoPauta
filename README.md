# SistemaVotacaoPauta

## Regras de Negócio

- Cadastrar uma nota pauta;
- Abrir sessão de votação em uma pauta(a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos de associados em pauta com votos SIM/NÃO. Cada Associado é identificado por um id único e pode votar apenas uma vez por pauta
- Disponibilizar os votos e dar o resultado da votação na pauta

## Documentação de API AWS

A documentação do swagger com os padrões de comunicação está disponível em:

http://ec2-3-144-104-121.us-east-2.compute.amazonaws.com/swagger-ui/index.html

## Tecnologias Utilizadas no Projeto
Para construção do sistema foram utilizadas as tecnologias:
- Java 17;
- Spring Boot Framework 3.0.5;
- Maven 3.8.2
- Aws Cloud;


## Arquitetura Hexagonal utilizada no desenvolvimento do projeto

![img_1.png](img_1.png)

Uma Arquitetura Hexagonal divide as classes de um sistema em dois grupos principais:
- Classes de domínio, isto é, diretamente relacionadas com o negócio do sistema.
- Classes relacionadas com infraestrutura, tecnologias e responsáveis pela integração com sistemas externos (tais como bancos de dados).

Neste projeto foi aplicado o conceito de arquitetura hexagonal devido os diversos benefícios que podemos obter como:
- Testabilidade : Desacoplar as regras de negócios de preocupações externas, como banco de dados, estrutura, interface do usuário e outras dependências, permite testar as regras de negócios de forma independente.
- Flexibilidade : É possível alterar as implementações concretas sem precisar modificar as regras de negócios.
- Foco no modelo do negócio : A principal objetivo foi separar a tecnologia utilizada do modelo de negócio.
- Fácil manutenção e escalabilidade
- Favorece a reusabilidade de código, alta coesão, baixo acoplamento, independência de tecnologia

## Testes Unitários
Foram realizados testes na camada web(controlles), no futuro será implementado a cobertura total do codigo a utilizando o JACOCO.


## Versionamento da Api

Foi utilizado o versionamento da api por URL, ex : /v1/pauta/criar

O modelo por URL ou por path (URI) é um dos modelos mais utilizados. É muito mais claro e simples para o usuário qual API ele está acessando, permitindo que o usuário consulte a documentação correta.
Muitos frameworks oferecem suporte ao controle de versão definido por rota(URI) ,e quando isso não ocorre, você pode simplesmente estruturar sua API, para que, permita o controle de versão de rota. Por isso, é extremamente fácil de implementar; entretanto, ele adiciona comprimento à sua rota da API REST, o que é a desvantagem.

Os metodo de versionamento por cabeçalhos personalizados seria uma boa opção — eles especificam a versão da API, e o servidor pode responder apropriadamente com os dados de resposta corretos.

Isso pode soar como uma solução fácil; porém, foi considerado que outros desenvolvedores podem extrair os dados por meio da  API de código aberto, sem fornecer cabeçalhos personalizados.

## Consultas Externas

Foi implementado um RestTemplateService para validar o cpf do associado , contudo a url externa url=https://user-info.herokuapp.com/users/{cpf} nao está mais disponível. 
Logo, essa consulta nao esta sendo realizada no projeto.


## Integração Kafka
Foi implementado um Kafka Producer Template para simular o envio do resultado para um topico, com a  contagem dos votos da pauta. Esses dados podem ser utilizados por um sistema de notificação por exemplo.

Para rodar o kafka local por favor utilize o comando 
- docker-compose -f kafka-docker-compose.yml up -d dentro da pasta /docker

Para produzir a mensagem use o recurso /v1/pauta/{pautaId}/notificarResultado


## Performance(Em Desenvolvimento)
Na busca pela melhora de desempenho da API desenvolvida foi utilizado o cache em memória para possibilitar o aumento da velocidade de consumo a dados frequentemente acessados, a fim de evitar consultas regulares no banco de dados.
- No futuro, será implementado o teste de performance utilizando a ferramenta de teste de carga de  codigo aberto K6.