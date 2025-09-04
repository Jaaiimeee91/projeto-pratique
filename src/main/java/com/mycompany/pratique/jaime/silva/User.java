package com.mycompany.pratique.jaime.silva;
    public class User {
        private String id;
        private String nome;
        private String email;
        private String cidade;

        public User(String id, String nome, String email, String cidade) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.cidade = cidade;
        }

        public String getId() { return id; }
        public String getNome() { return nome; }
        public String getEmail() { return email; }
        public String getCidade() { return cidade; }
    }