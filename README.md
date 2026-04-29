📅 Gerenciador de Eventos (Java Console App)
Sistema robusto de gestão de eventos desenvolvido em Java, focado em manipulação de arquivos, lógica de coleções e processamento de datas. O projeto simula uma plataforma de agendamento onde usuários podem criar, listar e participar de eventos locais.

🚀 Funcionalidades Principais
👤 Gestão de Usuários: Cadastro de perfis com Nome, Email e Cidade, utilizando geração de IDs únicos (UUID) para garantir a integridade dos dados.

🎉 Controle de Eventos: Criação de eventos com categorias customizáveis (Festa, Esporte, Show), endereço, descrição e validação de data/hora.

💾 Persistência de Dados (I/O): Implementação de salvamento e carregamento automático via arquivo externo (events.data). Os dados são persistidos em formato estruturado (similar ao CSV), permitindo que as informações sejam mantidas entre diferentes sessões de execução.

🔍 Filtros e Consultas Inteligentes:

Busca filtrada por categoria.

Visualização de Próximos Eventos (ordenados cronologicamente).

Histórico de Eventos Passados (já ocorridos).

🤝 Sistema de Participação: Lógica para confirmação ou cancelamento de presença em eventos específicos, com controle para evitar duplicidade de inscritos.

🛠️ Tecnologias e Conceitos de ADS Aplicados
Este projeto demonstra o domínio de competências fundamentais de Engenharia de Software:

Arquitetura de Software: Divisão clara de responsabilidades utilizando o modelo de classes (App, User, Event, EventManager), facilitando a manutenção e escalabilidade.

Java Streams API: Uso avançado de processamento funcional (.stream(), .filter(), .sorted()) para manipulação eficiente de grandes listas de dados.

Collections Framework: * HashMap: Utilizado para indexação rápida de eventos por ID (busca O(1)).

HashSet: Utilizado para garantir que a lista de participantes de um evento não contenha duplicatas.

Manipulação de Arquivos (NIO): Uso da biblioteca java.nio.file para operações seguras de leitura e escrita em disco.

Tratamento de Exceções: Código resiliente com blocos try-catch para gerenciar erros de entrada de dados e IO.

📋 Como Executar
Certifique-se de ter o JDK 8 ou superior instalado em sua máquina.

Clone este repositório:

Bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
Navegue até a pasta do projeto e compile os arquivos:

Bash
javac com/mycompany/pratique/jaime/silva/*.java
Execute a aplicação:

Bash
java com.mycompany.pratique.jaime.silva.App
📝 Exemplo de Fluxo
O sistema inicia carregando o arquivo events.data.

O usuário seleciona a opção 1 para se identificar.

Através da opção 2, cria um novo evento fornecendo os detalhes.

Outros usuários podem consultar os detalhes (opção 4) e confirmar participação (opção 5).

Ao sair (opção 0), o estado atual é salvo automaticamente.
