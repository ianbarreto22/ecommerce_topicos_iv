# Ecommerce_topicos_iv

Apresentação: https://www.youtube.com/watch?v=QcNNuUJioLg

Este projeto foi feito para a disciplina Tópicos de Engenharia de Software IV - Tolerância a falhas em sistemas de sistema de software. Para aplicar os conceitos aprendidos na disciplina, desenvolvemos uma aplicação com arquitetura orientada a microsserviços em que foram implementados conceitos de retry, replicas, circuit breaker, entre outros. Abaixo está um diagrama da aplicação.

![Diagrama do Sistema](images/diagrama.png)

O sistema foi estruturado com o ecommerce como o ponto de comunicação entre a aplicação e o usuário. Os containers com os serviços foram dispostos por trás de
proxy reverso com nginx e também foi utilizado a aplicação de mensageria o RabbitMQ para registrar no log as requisições feitas para serem depois processadas, em caso de falha.

### Como executar

Para executar a aplicação, basta utilizar o seguinte comando:

`$ docker-compose up --build`

A aplicação Ecommerce ficará disponível em http://localhost:9090
Os outros serviços ficarão disponíveis em http://localhost:8080
