package com.mycompany.pratique.jaime.silva;
import java.io.Serializable;

public class Event implements Serializable {
    private String titulo;
    private String data;
    private String local;
    private User organizador;

    public Event(String titulo, String data, String local, User organizador) {
        this.titulo = titulo;
        this.data = data;
        this.local = local;
        this.organizador = organizador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public User getOrganizador() {
        return organizador;
    }

    @Override
    public String toString() {
        return titulo + " | " + data + " | " + local + " | Organizador: " + organizador.getNome();
    }
}
