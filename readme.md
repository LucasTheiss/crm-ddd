# CRM Conversacional Inteligente (Hub Omnichannel)

Este repositório contém o código-fonte de um SaaS de gestão de relacionamento e atendimento ao cliente em tempo real, construído com arquitetura de microsserviços poliglotas, Domain-Driven Design (DDD) e Arquitetura Hexagonal.

## Objetivo
Automatizar a triagem de mensagens multicanal (WhatsApp via Evolution API), gerenciar funis de vendas/suporte e utilizar Inteligência Artificial (LLMs) para classificar intenções e gerar sugestões de respostas (Copiloto) para operadores humanos.

## Como Executar (Ambiente Local)
O projeto é 100% containerizado. Para subir toda a infraestrutura (PostgreSQL, RabbitMQ, Evolution API) e as aplicações (.NET, Java e Angular):

1. Certifique-se de ter o Docker e o Docker Compose instalados.
2. Na raiz do projeto, execute:
   ```bash
   docker-compose up -d --build

Acessos:

Frontend: http://localhost:4200

Backend Java (Core): http://localhost:8081

Gateway .NET: http://localhost:5000

RabbitMQ Admin: http://localhost:15672 (admin/admin123)