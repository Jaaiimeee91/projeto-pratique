package com.mycompany.pratique.jaime.silva;

import java.io.*;
import java.util.*;

public class EventManager implements Serializable {
    private List<User> usuarios = new ArrayList<>();
    private List<Event> eventos = new ArrayList<>();

    private static final String FILE_NAME = "events.data";

    public void addUser(User u) {
        usuarios.add(u);
    }

    public void addEvent(Event e) {
        eventos.add(e);
    }

    public void listUsers() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("\n--- Usuários ---");
            for (User u : usuarios) {
                System.out.println("- " + u);
            }
        }
    }

    public void listEvents() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            System.out.println("\n--- Eventos ---");
            for (Event e : eventos) {
                System.out.println("- " + e);
            }
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
            System.out.println("Dados salvos em " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static EventManager loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (EventManager) ois.readObject();
        } catch (Exception e) {
            System.out.println("Nenhum dado anterior encontrado. Criando novo gerenciador.");
            return new EventManager();
        }
    }
}
