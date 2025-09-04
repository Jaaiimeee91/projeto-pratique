package com.mycompany.pratique.jaime.silva;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PratiqueJaimeSilva {
// Formato para exibir e parsear datas
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Arquivo onde os eventos serão salvos
    private static final String EVENTS_FILE = "events.data";

    // Dados em memória
    private static Scanner scanner = new Scanner(System.in);
    private static EventManager eventManager = new EventManager();
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("==== Sistema de Eventos (Console) ====");
        // Carrega eventos do arquivo
        try {
            eventManager.loadFromFile(EVENTS_FILE);
            System.out.println("Eventos carregados do arquivo.");
        } catch (IOException e) {
            System.out.println("Nenhum arquivo de eventos encontrado — iniciando vazio.");
        }

        mainLoop();

        // Ao sair, salva
        try {
            eventManager.saveToFile(EVENTS_FILE);
            System.out.println("Eventos salvos em " + EVENTS_FILE);
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }

        System.out.println("Até mais!");
    }

    private static void mainLoop() {
        boolean running = true;
        while (running) {
            System.out.println("\nMenu principal:");
            System.out.println("1) Cadastrar/Selecionar usuário");
            System.out.println("2) Criar evento");
            System.out.println("3) Listar eventos");
            System.out.println("4) Consultar evento (detalhes)");
            System.out.println("5) Participar de evento");
            System.out.println("6) Cancelar participação");
            System.out.println("7) Próximos eventos");
            System.out.println("8) Eventos já ocorridos");
            System.out.println("9) Salvar agora");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1": userMenu(); break;
                case "2": createEvent(); break;
                case "3": listEventsMenu(); break;
                case "4": showEventDetails(); break;
                case "5": participateEvent(); break;
                case "6": cancelParticipation(); break;
                case "7": showNextEvents(); break;
                case "8": showPastEvents(); break;
                case "9":
                    try {
                        eventManager.saveToFile(EVENTS_FILE);
                        System.out.println("Salvo com sucesso.");
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar: " + e.getMessage());
                    }
                    break;
                case "0": running = false; break;
                default: System.out.println("Opção inválida."); break;
            }
        }
    }
        
// Usuário
    private static void userMenu() {
        System.out.println("\n== Cadastro de Usuário ==");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Cidade onde reside: ");
        String cidade = scanner.nextLine().trim();
        currentUser = new User(UUID.randomUUID().toString(), nome, email, cidade);
        System.out.println("Usuário '" + currentUser.getNome() + "' cadastrado/selecionado.");
    }

    // Criar evento
    private static void createEvent() {
        if (currentUser == null) {
            System.out.println("Cadastre ou selecione um usuário primeiro (opção 1).");
            return;
        }
        System.out.println("\n== Criar Evento ==");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();
        System.out.print("Categoria (ex: festa, esporte, show): ");
        String categoria = scanner.nextLine().trim();
        System.out.print("Data e hora (formato: yyyy-MM-dd HH:mm) -> ex: 2025-09-07 19:30: ");
        String dt = scanner.nextLine().trim();
        LocalDateTime horario;
        try {
            horario = LocalDateTime.parse(dt, FORMATTER);
        } catch (Exception e) {
            System.out.println("Formato de data inválido. Evento não criado.");
            return;
        }
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine().trim();

        Event ev = new Event(UUID.randomUUID().toString(), nome, endereco, categoria, horario, descricao, currentUser.getCidade());
        eventManager.addEvent(ev);
        System.out.println("Evento criado com ID: " + ev.getId());
        // auto-save
        try {
            eventManager.saveToFile(EVENTS_FILE);
        } catch (IOException e) {
            System.err.println("Falha ao salvar após criar evento: " + e.getMessage());
        }
    }

    // Listar eventos (todos / por categoria)
    private static void listEventsMenu() {
        System.out.println("\n== Listar Eventos ==");
        System.out.println("1) Listar todos");
        System.out.println("2) Listar por categoria");
        System.out.print("Escolha: ");
        String op = scanner.nextLine().trim();
        List<Event> list;
        if ("2".equals(op)) {
            System.out.print("Digite a categoria: ");
            String cat = scanner.nextLine().trim();
            list = eventManager.getEventsByCategory(cat);
        } else {
            list = eventManager.getAllEvents();
        }
        if (list.isEmpty()) {
            System.out.println("Nenhum evento encontrado.");
            return;
        }
        System.out.println("\nEventos:");
        for (Event e : list) {
            System.out.printf("ID: %s | %s | %s | %s\n", e.getId(), e.getNome(), e.getHorario().format(FORMATTER), e.getCategoria());
        }
    }

    // Mostrar detalhes
    private static void showEventDetails() {
        System.out.print("Digite o ID do evento: ");
        String id = scanner.nextLine().trim();
        Event e = eventManager.getEventById(id);
        if (e == null) {
            System.out.println("Evento não encontrado.");
            return;
        }
        System.out.println("\n== Detalhes do Evento ==");
        System.out.println("ID: " + e.getId());
        System.out.println("Nome: " + e.getNome());
        System.out.println("Endereço: " + e.getEndereco());
        System.out.println("Categoria: " + e.getCategoria());
        System.out.println("Data/Hora: " + e.getHorario().format(FORMATTER));
        System.out.println("Descrição: " + e.getDescricao());
        System.out.println("Cidade do evento: " + e.getCidade());
        System.out.println("Participantes confirmados: ");
        for (String u : e.getConfirmedUserIds()) {
            System.out.println(" - " + u);
        }
        System.out.println("Status: " + (e.getHorario().isBefore(LocalDateTime.now()) ? "Já ocorreu" : "Agendado"));
    }

    // Participar
    private static void participateEvent() {
        if (currentUser == null) {
            System.out.println("Cadastre um usuário primeiro (opção 1).");
            return;
        }
        System.out.print("Digite o ID do evento para participar: ");
        String id = scanner.nextLine().trim();
        Event e = eventManager.getEventById(id);
        if (e == null) {
            System.out.println("Evento não encontrado.");
            return;
        }
        boolean ok = eventManager.addParticipant(e.getId(), currentUser.getId());
        if (ok) {
            System.out.println("Participação confirmada para o usuário: " + currentUser.getNome());
            try { eventManager.saveToFile(EVENTS_FILE); } catch (IOException ex) { /*ignore*/ }
        } else {
            System.out.println("Usuário já estava participando.");
        }
    }

    // Cancelar participação
    private static void cancelParticipation() {
        if (currentUser == null) {
            System.out.println("Cadastre um usuário primeiro (opção 1).");
            return;
        }
        System.out.print("Digite o ID do evento para cancelar participação: ");
        String id = scanner.nextLine().trim();
        Event e = eventManager.getEventById(id);
        if (e == null) {
            System.out.println("Evento não encontrado.");
            return;
        }
        boolean ok = eventManager.removeParticipant(e.getId(), currentUser.getId());
        if (ok) {
            System.out.println("Participação cancelada.");
            try { eventManager.saveToFile(EVENTS_FILE); } catch (IOException ex) { /*ignore*/ }
        } else {
            System.out.println("Você não estava participando desse evento.");
        }
    }

    // Próximos eventos ordenados
    private static void showNextEvents() {
        System.out.print("Quantos próximos eventos mostrar? (enter para 5): ");
        String s = scanner.nextLine().trim();
        int n = 5;
        if (!s.isEmpty()) {
            try { n = Integer.parseInt(s); } catch (NumberFormatException ex) { n = 5; }
        }
        List<Event> next = eventManager.getNextEvents(n);
        if (next.isEmpty()) {
            System.out.println("Nenhum próximo evento.");
            return;
        }
        System.out.println("\nPróximos eventos:");
        for (Event e : next) {
            System.out.printf("%s | %s | %s\n", e.getId(), e.getNome(), e.getHorario().format(FORMATTER));
        }
    }

    // Mostrar eventos já ocorridos
    private static void showPastEvents() {
        List<Event> past = eventManager.getPastEvents();
        if (past.isEmpty()) {
            System.out.println("Nenhum evento ocorrido ainda.");
            return;
        }
        System.out.println("\nEventos já ocorridos:");
        for (Event e : past) {
            System.out.printf("%s | %s | %s\n", e.getId(), e.getNome(), e.getHorario().format(FORMATTER));
        }
    }
}
