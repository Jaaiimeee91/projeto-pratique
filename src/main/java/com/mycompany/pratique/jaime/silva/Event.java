package com.mycompany.pratique.jaime.silva;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

 // Classe Event
    public class Event { 
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        private final String id;
        private final String nome;
        private final String endereco;
        private final String categoria;
        private final LocalDateTime horario;
        private final String descricao;
        private final Set<String> confirmedUserIds = new HashSet<>();
        private final String cidade; // cidade onde ocorre o evento (pode ser usada para filtros)

        public Event(String id, String nome, String endereco, String categoria, LocalDateTime horario, String descricao, String cidade) {
            this.id = id;
            this.nome = nome;
            this.endereco = endereco;
            this.categoria = categoria;
            this.horario = horario;
            this.descricao = descricao;
            this.cidade = cidade;
        }

        public String getId() { return id; }
        public String getNome() { return nome; }
        public String getEndereco() { return endereco; }
        public String getCategoria() { return categoria; }
        public LocalDateTime getHorario() { return horario; }
        public String getDescricao() { return descricao; }
        public String getCidade() { return cidade; }

        public Set<String> getConfirmedUserIds() { return confirmedUserIds; }

        public void addParticipant(String userId) { confirmedUserIds.add(userId); }
        public boolean removeParticipant(String userId) { return confirmedUserIds.remove(userId); }

        // Serialização simples para CSV
        public String toDataLine() {
            // id|nome|endereco|categoria|horario(yyyy-MM-dd HH:mm)|descricao|cidade|user1,user2,...
            String participants = String.join(",", confirmedUserIds);
            // substituir pipes por outro char para evitar quebra (simples escaping)
            String safeNome = nome.replace("|", " ");
            String safeEndereco = endereco.replace("|", " ");
            String safeCategoria = categoria.replace("|", " ");
            String safeDescricao = descricao.replace("|", " ");
            String safeCidade = cidade.replace("|", " ");
            return String.join("|", id, safeNome, safeEndereco, safeCategoria, horario.format(FORMATTER), safeDescricao, safeCidade, participants);
        }

        public static Event fromDataLine(String line) {
            String[] parts = line.split("\\|", -1);
            if (parts.length < 8) return null;
            try {
                String id = parts[0];
                String nome = parts[1];
                String endereco = parts[2];
                String categoria = parts[3];
                DateTimeFormatter FORMATTER = null;
                LocalDateTime horario = LocalDateTime.parse(parts[4], FORMATTER);
                String descricao = parts[5];
                String cidade = parts[6];
                Event e = new Event(id, nome, endereco, categoria, horario, descricao, cidade);
                String participants = parts[7];
                if (!participants.isEmpty()) {
                    String[] uids = participants.split(",");
                    for (String u : uids) if (!u.trim().isEmpty()) e.confirmedUserIds.add(u.trim());
                }
                return e;
            } catch (Exception ex) {
                return null;
            }
        }
    }
