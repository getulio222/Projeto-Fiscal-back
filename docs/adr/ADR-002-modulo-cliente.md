# ADR-002: Organização do módulo de clientes por contexto funcional

- **Status:** Aceito
- **Data:** 2026-06-23
- **Responsáveis:** Equipe do Projeto Fiscal

## Contexto

O Projeto Fiscal precisa manter seus componentes organizados à medida que novos domínios e funcionalidades forem incorporados. Uma estrutura global por camada, com todos os controllers, serviços, entidades e repositórios agrupados separadamente, tende a dispersar os elementos relacionados a uma mesma capacidade de negócio.

Essa dispersão aumenta o acoplamento entre contextos, dificulta a navegação no código e torna menos explícitos os limites funcionais da aplicação.

## Decisão

Criar um módulo `cliente` contendo a seguinte estrutura interna:

```text
cliente/
├── api/
├── application/
├── domain/
└── infra/
```

Cada diretório possui uma responsabilidade definida:

- `api/`: pontos de entrada do módulo, contratos HTTP e objetos de transferência de dados.
- `application/`: coordenação dos casos de uso e do fluxo de aplicação.
- `domain/`: entidades, regras e conceitos centrais do domínio de clientes.
- `infra/`: mecanismos de persistência e demais detalhes técnicos do módulo.

Essa organização será utilizada em vez de uma estrutura global por camada. Os componentes relacionados ao contexto de clientes permanecerão próximos e subordinados ao módulo `cliente`.

## Motivo

A organização por módulo oferece maior clareza estrutural, isolamento de contexto e facilidade de evolução para uma arquitetura modular. Ela explicita a relação entre os componentes de uma mesma capacidade de negócio e reduz a necessidade de navegar por diferentes pacotes globais para compreender uma funcionalidade.

## Alternativas consideradas

### Estrutura global por camada

Organizar toda a aplicação em pacotes como `controller`, `service`, `domain` e `repository`. Embora simples em projetos pequenos, essa abordagem distribui os componentes de cada funcionalidade por toda a base de código e pode aumentar o acoplamento entre contextos.

### Estrutura por módulo funcional

Agrupar os componentes pelo contexto de negócio e aplicar separação interna de responsabilidades. Essa alternativa favorece coesão, encapsulamento e evolução independente dos módulos.

## Consequências

### Positivas

- Maior coesão entre os componentes do contexto de clientes.
- Limites funcionais mais claros na estrutura do projeto.
- Navegação e manutenção simplificadas.
- Menor tendência ao acoplamento acidental entre contextos.
- Base mais adequada para a criação de novos módulos funcionais.
- Facilidade de evolução futura para uma arquitetura modular mais rigorosa.

### Negativas

- Repetição dos nomes das camadas internas em diferentes módulos.
- Necessidade de definir e fiscalizar regras de dependência entre as camadas internas.
- Risco de dependências diretas entre módulos caso seus limites não sejam respeitados.
- Exigência de critérios consistentes para decidir a qual módulo cada componente pertence.

## Regras de dependência

- A camada `api` deve delegar a execução dos casos de uso à camada `application`.
- A camada `application` deve coordenar o fluxo sem concentrar regras próprias do domínio.
- A camada `domain` deve representar as regras e os conceitos centrais do módulo.
- A camada `infra` deve conter detalhes técnicos e implementar os mecanismos necessários ao módulo.
- Dependências entre módulos devem ocorrer por contratos explícitos, evitando acesso indiscriminado a detalhes internos.

## Critérios de validação

- Todos os componentes relacionados a clientes estão contidos no módulo `cliente`.
- Cada componente está localizado na camada correspondente à sua responsabilidade.
- Não existem pacotes globais por camada para os componentes do módulo.
- As regras de negócio permanecem separadas dos detalhes de API e infraestrutura.
- Novas funcionalidades do contexto de clientes podem ser incorporadas sem romper os limites definidos.

## Referências internas

- Módulo de clientes: `src/main/java/io/github/getulio222/projetofiscal/cliente/`
- Decisão de identidade e acesso: `docs/adr/ADR-001-keycloak.md`

## Histórico

| Data | Alteração | Responsável |
| --- | --- | --- |
| 2026-06-23 | Registro inicial da decisão. | Equipe do Projeto Fiscal |
