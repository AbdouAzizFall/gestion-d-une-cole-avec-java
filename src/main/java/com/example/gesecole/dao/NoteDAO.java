package com.example.gesecole.dao;

import com.example.gesecole.model.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private Connection connection;

    public NoteDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une note
    public void create(Note note) {
        String sql = "INSERT INTO note (etudiant_id, cours_id, valeur) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, note.getEtudiantId());
            preparedStatement.setInt(2, note.getCoursId());
            preparedStatement.setDouble(3, note.getValeur());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout de la note", e);
        }
    }

    // Récupérer une note
    public Note read(int id) {
        String sql = "SELECT * FROM note WHERE id = ?";
        Note note = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int noteId = resultSet.getInt("id");
                int etudiantId = resultSet.getInt("etudiant_id");
                int coursId = resultSet.getInt("cours_id");
                double valeur = resultSet.getDouble("valeur");

                note = new Note(noteId, etudiantId, coursId, valeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération de la note", e);
        }

        return note;
    }

    // Mettre à jour une note
    public void update(Note note) {
        String sql = "UPDATE note SET etudiant_id = ?, cours_id = ?, valeur = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, note.getEtudiantId());
            preparedStatement.setInt(2, note.getCoursId());
            preparedStatement.setDouble(3, note.getValeur());
            preparedStatement.setInt(4, note.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour de la note", e);
        }
    }

    // Supprimer une note
    public void delete(int id) {
        String sql = "DELETE FROM note WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de la note", e);
        }
    }

    // Récupérer toutes les notes
    public List<Note> findAll() {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM note";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int etudiantId = resultSet.getInt("etudiant_id");
                int coursId = resultSet.getInt("cours_id");
                double valeur = resultSet.getDouble("valeur");

                Note note = new Note(id, etudiantId, coursId, valeur);
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des notes", e);
        }

        return notes;
    }
}
