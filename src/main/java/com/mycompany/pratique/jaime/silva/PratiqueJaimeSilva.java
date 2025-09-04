package com.mycompany.pratique.jaime.silva;
import com.mycompany.pratique.jaime.silva.EventManager;
import java.util.Scanner;

public class PratiqueJaimeSilva {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventManager eventManager = EventManager.loadFromFile();

        int opcao;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Criar evento");
            System.out.println("3 - Listar usuários");
            System.out.println("4 - Listar eventos");
            System.out.println("5 - Salvar dados");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do usuário: ");
                    String nome = sc.nextLine();
                    System.out.print("Email do usuário: ");
                    String email = sc.nextLine();
                    User u = new User(nome, email);
                    eventManager.addUser(u);
                    System.out.println("Usuário cadastrado!");
                    break;

                case 2:
                    if (eventManager == null) break;
                    System.out.print("Título do evento: ");
                    String titulo = sc.nextLine();
                    System.out.print("Data do evento: ");
                    String data = sc.nextLine();
                    System.out.print("Local do evento: ");
                    String local = sc.nextLine();
                    System.out.print("Nome do organizador: ");
                    String nomeOrg = sc.nextLine();
                    System.out.print("Email do organizador: ");
                    String emailOrg = sc.nextLine();
                    User organizador = new User(nomeOrg, emailOrg);
                    Event e = new Event(titulo, data, local, organizador);
                    eventManager.addEvent(e);
                    System.out.println("Evento criado!");
                    break;

                case 3:
                    eventManager.listUsers();
                    break;

                case 4:
                    eventManager.listEvents();
                    break;

                case 5:
                    eventManager.saveToFile();
                    break;

                case 0:
                    System.out.println("Saindo... (dados salvos automaticamente)");
                    eventManager.saveToFile();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}

