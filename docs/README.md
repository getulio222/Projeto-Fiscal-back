# Documentação do Projeto Fiscal

Esta pasta centraliza a documentação técnica e o histórico de evolução do Projeto Fiscal. O objetivo é tornar decisões, arquitetura, aprendizados e progresso verificáveis, facilitando a manutenção do sistema e a apresentação do projeto em portfólio.

## Estrutura

```text
docs/
├── README.md
├── arquitetura/
├── adr/
├── diario-dev/
├── estudos/
├── roadmap/
├── diagramas/
└── postmortems/
```

| Pasta | Finalidade |
| --- | --- |
| [`arquitetura/`](arquitetura/README.md) | Registrar a visão arquitetural, os componentes, as integrações, os fluxos e as restrições técnicas do sistema. |
| [`adr/`](adr/README.md) | Manter os Registros de Decisão Arquitetural (ADRs), incluindo contexto, alternativas, decisão e consequências. |
| [`diario-dev/`](diario-dev/README.md) | Documentar a evolução do desenvolvimento, com atividades realizadas, problemas, decisões e próximos passos. |
| [`estudos/`](estudos/README.md) | Consolidar estudos técnicos, provas de conceito e análises que apoiem decisões do projeto. |
| [`roadmap/`](roadmap/README.md) | Apresentar objetivos, prioridades, marcos e evolução planejada do produto. |
| [`diagramas/`](diagramas/README.md) | Centralizar representações visuais da arquitetura, dos fluxos e das integrações. |
| [`postmortems/`](postmortems/README.md) | Registrar incidentes e falhas relevantes, suas causas, impactos, aprendizados e ações preventivas. |

## Diretrizes de documentação

- Escrever em linguagem objetiva, profissional e compreensível para pessoas que não participaram do desenvolvimento.
- Registrar fatos e decisões relevantes, evitando duplicar informações que já estejam claras no código.
- Informar fontes, premissas e limitações sempre que aplicável.
- Atualizar documentos afetados por mudanças significativas no sistema.
- Preservar o histórico: decisões substituídas devem ser marcadas como tal, não apagadas.
- Não incluir credenciais, segredos, dados pessoais ou informações sensíveis.

## Convenção de nomes

- Arquitetura: nomes descritivos em `kebab-case`, por exemplo `visao-geral.md`.
- ADR: `ADR-NNN-titulo-da-decisao.md`, por exemplo `ADR-003-adotar-cache.md`.
- Diário de desenvolvimento: `AAAA-MM-DD.md`; se necessário, acrescentar um tema, como `2026-06-23-autenticacao.md`.
- Estudos: `AAAA-MM-DD-tema.md`, por exemplo `2026-06-23-oauth2.md`.
- Roadmap: nomes associados ao período ou objetivo, como `2026-segundo-semestre.md`.
- Diagramas: nomes descritivos em `kebab-case`, como `contexto-do-sistema.md`.
- Postmortems: `AAAA-MM-DD-descricao.md`, como `2026-06-23-indisponibilidade-autenticacao.md`.

Os arquivos de template devem ser copiados para um novo documento e permanecer inalterados como referência.
