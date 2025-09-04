/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pratique.jaime.silva;
/**
 *
 * @author jaime
 */
import java.util.Scanner;
public class PratiqueJaimeSilva {

    public static void main(String[] args) {    
   
        Scanner sc = new Scanner(System.in);
        EventManager eventManager = new EventManager();

        int opcao;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Criar evento");
            System.out.println("3 - Listar eventos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do usuário: ");
                    String nome = sc.nextLine();
                    User u = new User(nome);
                    System.out.println("Usuário cadastrado: " + u);
                    break;

                case 2:
                    System.out.print("Título do evento: ");
                    String titulo = sc.nextLine();
                    Event e = new Event(titulo);
                    eventManager.addEvent(e);
                    System.out.println("Evento criado!");
                    break;

                case 3:
                    eventManager.listEvents();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}

    

