# 📅 Gerenciador de Eventos (Java Console App)

Sistema robusto de gestão de eventos desenvolvido em **Java**, focado em manipulação de arquivos, lógica de coleções e processamento de datas.

---

## 🚀 Funcionalidades Principais

* **👤 Gestão de Usuários:** Cadastro de perfis com Nome, Email e Cidade (UUID).
* **🎉 Controle de Eventos:** Criação de eventos com categorias, endereço e validação de data/hora.
* **💾 Persistência de Dados (I/O):** Salvamento e carregamento automático via arquivo `.data`.
* **🔍 Filtros e Consultas:** Busca por categoria, próximos eventos e histórico.
* **🤝 Sistema de Participação:** Lógica para confirmação ou cancelamento de presença.

---

## 🛠️ Tecnologias e Conceitos de ADS Aplicados

* **Arquitetura de Software:** Divisão em classes (`App`, `User`, `Event`, `EventManager`).
* **Java Streams API:** Uso avançado de `.stream()`, `.filter()` e `.sorted()`.
* **Collections Framework:** `HashMap` para buscas rápidas e `HashSet` para evitar duplicatas.
* **Manipulação de Arquivos (NIO):** Uso da biblioteca `java.nio.file`.
* **Tratamento de Exceções:** Blocos `try-catch` para gerenciar erros de entrada e IO.

---

## 📋 Como Executar

1. Compile os arquivos:
   `javac com/mycompany/pratique/jaime/silva/*.java`
2. Execute a aplicação:
   `java com.mycompany.pratique.jaime.silva.App`

---

## 📝 Exemplo de Fluxo
- O sistema carrega o arquivo `events.data`.
- Usuário seleciona a opção **1** para se identificar.
- Opção **2** para criar um evento.
- Ao sair (**0**), o estado é salvo automaticamente.
