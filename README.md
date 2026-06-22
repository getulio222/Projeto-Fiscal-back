# Projeto Fiscal

API backend para estudos de arquitetura fiscal e emissão de NF-e.

## Stack
- Java 21
- Spring Boot
- PostgreSQL
- Keycloak 26.6.3 (OpenID Connect)
- Docker

## Objetivo
Construir uma API fiscal modular, developer-first, com foco em arquitetura limpa e clareza.

## Autenticação local

1. Inicie PostgreSQL e Keycloak:

   ```bash
   docker compose up -d
   ```

2. Acesse o painel administrativo em `http://localhost:8180/admin` com `admin` / `admin`.
3. O realm `projeto-fiscal`, o cliente `projeto-fiscal-front` e as roles `ADMIN`, `OPERADOR` e `CONSULTA` são importados automaticamente.
4. Para testar a aplicação, use `fiscal` / `fiscal`. No primeiro acesso, o Keycloak solicitará uma nova senha.

As credenciais acima existem somente para desenvolvimento local. Usuários, senhas e sessões são administrados pelo Keycloak; a API não armazena credenciais.
