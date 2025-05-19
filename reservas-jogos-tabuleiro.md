# Sistema de Biblioteca de Jogos de Tabuleiro

## Descrição

Este sistema tem como objetivo gerenciar uma biblioteca de jogos de tabuleiro utilizada por uma instituição, clube ou espaço cultural. O sistema deve permitir o cadastro e o gerenciamento de jogos, usuários (membros), empréstimos, devoluções, reservas, categorias de jogos e controle de disponibilidade.

Cada jogo possui informações como nome, editor, quantidade de cópias disponíveis, tempo médio de partida, número mínimo e máximo de jogadores, e categoria (ex: estratégia, cooperativo, infantil). Usuários podem reservar e emprestar jogos, sendo necessário controlar os prazos, a disponibilidade e possíveis penalidades por atraso.

O sistema deve permitir que administradores acompanhem a movimentação dos jogos, visualizem estatísticas de uso e gerem relatórios com filtros diversos, com possibilidade de exportação em PDF ou CSV.

## Requisitos Funcionais

### 1. Gerenciamento de Jogos

- **REQ01**: Permitir o gerenciamento de jogos de tabuleiro, incluindo nome, editor, descrição, número mínimo e máximo de jogadores, tempo estimado de partida, e quantidade de cópias.
- **REQ02**: Associar cada jogo a uma ou mais categorias (ex: estratégia, festa, cooperativo, educativo).

### 2. Gerenciamento de Usuários

- **REQ03**: Permitir o gerenciamento de usuários (membros da biblioteca), com nome, e-mail, telefone e status (ativo/inativo).
- **REQ04**: Controlar o número máximo de jogos que um usuário pode ter emprestado simultaneamente (ex: 3 jogos).

### 3. Empréstimos de Jogos

- **REQ05**: Permitir que usuários realizem empréstimos de jogos, considerando a disponibilidade de cópias.
- **REQ06**: Definir prazo de devolução padrão (ex: 7 dias), com possibilidade de extensão.
- **REQ07**: Impedir novos empréstimos se o usuário estiver com devoluções em atraso.

### 4. Devoluções e Atrasos

- **REQ08**: Registrar a devolução de jogos com a data de retorno.
- **REQ09**: Identificar e registrar devoluções com atraso e aplicar penalidades, como suspensão temporária de novos empréstimos.
- **REQ10**: Permitir o registro de danos ou peças faltando no momento da devolução.

### 5. Reservas de Jogos

- **REQ11**: Permitir que usuários reservem jogos que estão atualmente emprestados.
- **REQ12**: Organizar uma fila de reservas por ordem de solicitação.
- **REQ13**: Notificar (simulado) o usuário quando o jogo reservado estiver disponível para retirada, com prazo para efetivação.

### 6. Consultas e Pesquisa

- **REQ14**: Permitir a busca de jogos por nome, editor, categoria, tempo de partida, número de jogadores ou disponibilidade.
- **REQ15**: Permitir que o usuário veja seu histórico de empréstimos, reservas ativas e penalidades aplicadas.

### 7. Relatórios e Estatísticas

- **REQ16**: Gerar relatórios de empréstimos por período, usuário ou jogo, incluindo frequência de uso e popularidade dos jogos.
- **REQ17**: Gerar relatório de usuários com maior número de empréstimos ou com mais atrasos.
- **REQ18**: Permitir exportação de relatórios em **PDF** e **CSV**, com colunas organizadas, agrupamentos e totais.

### 8. Regras e Restrições

- **REQ19**: Um mesmo jogo não pode ser emprestado a dois usuários ao mesmo tempo (controlar por cópias disponíveis).
- **REQ20**: Um usuário não pode reservar o mesmo jogo mais de uma vez simultaneamente.
- **REQ21**: Jogos com pendências de devolução ou em manutenção não devem estar disponíveis para novos empréstimos.

## Possíveis APIs/Bibliotecas a Serem Usadas

- **JavaFX** – Interface gráfica do sistema.
- **JDBC / Hibernate** – Acesso e persistência de dados.
- **Java Time API** – Cálculo de prazos de devolução e atrasos.
- **iText / JasperReports** – Geração de relatórios em PDF.
- **Apache POI** – Exportação de dados em formato CSV ou Excel.
- **JUnit / Mockito** – Testes de lógica de empréstimos, devoluções e reservas.
