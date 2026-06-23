# ADR-001: Adoção do Keycloak para identidade e controle de acesso

- **Status:** Aceito
- **Data:** 2026-06-23
- **Responsáveis:** Equipe do Projeto Fiscal

## Contexto

O Projeto Fiscal precisa autenticar usuários e controlar o acesso às operações da API de acordo com seus perfis. Implementar internamente cadastro, armazenamento de senhas, emissão de tokens, gerenciamento de sessões e recuperação de acesso aumentaria a complexidade da aplicação e a superfície de risco de segurança.

Também é necessário manter a API sem estado, permitir integração com aplicações clientes por meio de padrões abertos e separar as responsabilidades de identidade das regras de negócio fiscal.

## Direcionadores da decisão

- Utilizar padrões consolidados de autenticação e autorização.
- Evitar o armazenamento de credenciais na API.
- Permitir autorização baseada em papéis.
- Manter a API desacoplada do gerenciamento de usuários e sessões.
- Possibilitar execução reproduzível no ambiente local.
- Adotar uma solução compatível com Spring Security.

## Alternativas consideradas

### Autenticação implementada na própria aplicação

Ofereceria controle total sobre o fluxo, mas exigiria implementar e manter funcionalidades sensíveis, como armazenamento seguro de senhas, emissão e renovação de tokens e gerenciamento de usuários.

### Serviço de identidade gerenciado

Reduziria o esforço operacional, porém criaria dependência de um provedor externo, custos potenciais e limitações para execução integral do ambiente local.

### Keycloak

Fornece gerenciamento de identidade e acesso, suporte a OpenID Connect, emissão de tokens JWT e controle baseado em papéis. Pode ser executado localmente em contêiner e integrado ao Spring Security como provedor de identidade.

## Decisão

Adotar o Keycloak como provedor de identidade e autorização do Projeto Fiscal.

A API funcionará como um OAuth 2.0 Resource Server, validando tokens JWT emitidos pelo realm `projeto-fiscal`. Os usuários, as senhas e as sessões serão administrados pelo Keycloak, sem armazenamento de credenciais na aplicação.

As permissões serão representadas pelas roles de realm `ADMIN`, `OPERADOR` e `CONSULTA`. As roles presentes no claim `realm_access.roles` serão convertidas para authorities do Spring Security com o prefixo `ROLE_`.

O ambiente local utilizará o Keycloak em contêiner, com configuração inicial importada de um arquivo de realm versionado no projeto. O banco de dados do provedor de identidade permanecerá separado do banco da aplicação.

## Consequências

### Positivas

- Centralização do gerenciamento de usuários, credenciais, sessões e papéis.
- Uso de OpenID Connect e tokens JWT compatíveis com Spring Security.
- API sem estado e sem responsabilidade direta por credenciais.
- Ambiente local reproduzível por meio do Docker Compose.
- Separação entre identidade, autorização e domínio fiscal.

### Negativas

- Inclusão de um serviço adicional e de seu banco de dados na infraestrutura.
- Dependência da disponibilidade e da configuração correta do Keycloak.
- Necessidade de administrar atualizações, exportações de realm e configurações de segurança.
- Aumento do consumo de recursos no ambiente local.

### Riscos e ações de mitigação

- **Configuração insegura:** manter credenciais de demonstração restritas ao ambiente local e utilizar segredos externos nos demais ambientes.
- **Indisponibilidade do provedor:** monitorar o serviço e definir políticas adequadas de disponibilidade para cada ambiente.
- **Divergência de configuração:** versionar a configuração inicial do realm e revisar alterações antes da implantação.
- **Mapeamento incorreto de permissões:** cobrir as regras de acesso e a conversão de roles com testes automatizados.

## Critérios de validação

- Tokens válidos emitidos pelo Keycloak são aceitos pela API.
- Requisições sem autenticação válida são rejeitadas.
- As roles `ADMIN`, `OPERADOR` e `CONSULTA` restringem corretamente as operações protegidas.
- A API não persiste senhas ou sessões de usuários.
- O ambiente local pode ser iniciado com a configuração de realm prevista pelo projeto.

## Referências internas

- Configuração de segurança da API: `src/main/java/io/github/getulio222/projetofiscal/seguranca/infra/SecurityConfig.java`
- Conversão de roles: `src/main/java/io/github/getulio222/projetofiscal/seguranca/infra/KeycloakRealmRoleConverter.java`
- Configuração do realm: `infra/keycloak/realm-projeto-fiscal.json`
- Infraestrutura local: `compose.yaml`

## Histórico

| Data | Alteração | Responsável |
| --- | --- | --- |
| 2026-06-23 | Registro inicial da decisão. | Equipe do Projeto Fiscal |
