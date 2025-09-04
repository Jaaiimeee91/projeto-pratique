package com.mycompany.pratique.jaime.silva;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

// Gerenciador de eventos
    public class EventManager {
        private Map<String, Event> events = new HashMap<>();

        public void addEvent(Event e) {
            events.put(e.getId(), e);
        }

        public List<Event> getAllEvents() {
            return events.values().stream()
                    .sorted(Comparator.comparing(Event::getHorario))
                    .collect(Collectors.toList());
        }

        public Event getEventById(String id) {
            return events.get(id);
        }

        public List<Event> getEventsByCategory(String cat) {
            return events.values().stream()
                    .filter(ev -> ev.getCategoria().equalsIgnoreCase(cat))
                    .sorted(Comparator.comparing(Event::getHorario))
                    .collect(Collectors.toList());
        }

        public boolean addParticipant(String eventId, String userId) {
            Event e = events.get(eventId);
            if (e == null) return false;
            if (e.getConfirmedUserIds().contains(userId)) return false;
            e.addParticipant(userId);
            return true;
        }

        public boolean removeParticipant(String eventId, String userId) {
            Event e = events.get(eventId);
            if (e == null) return false;
            return e.removeParticipant(userId);
        }

        public List<Event> getNextEvents(int n) {
            LocalDateTime now = LocalDateTime.now();
            return events.values().stream()
                    .filter(ev -> !ev.getHorario().isBefore(now))
                    .sorted(Comparator.comparing(Event::getHorario))
                    .limit(n)
                    .collect(Collectors.toList());
        }

        public List<Event> getPastEvents() {
            LocalDateTime now = LocalDateTime.now();
            return events.values().stream()
                    .filter(ev -> ev.getHorario().isBefore(now))
                    .sorted(Comparator.comparing(Event::getHorario).reversed())
                    .collect(Collectors.toList());
        }

        // Persistência simples em arquivo texto
        public void saveToFile(String filename) throws IOException {
            List<String> lines = new ArrayList<>();
            for (Event e : getAllEvents()) {
                lines.add(e.toDataLine());
            }
            Files.write(Paths.get(filename), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

        public void loadFromFile(String filename) throws IOException {
            events.clear();
            Path p = Paths.get(filename);
            if (!Files.exists(p)) throw new IOException("Arquivo não existe.");
            List<String> lines = Files.readAllLines(p);
            for (String l : lines) {
                Event e = Event.fromDataLine(l);
                if (e != null) events.put(e.getId(), e);
            }
        }
    }
