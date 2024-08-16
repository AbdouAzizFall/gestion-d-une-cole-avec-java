package com.example.gesecole.dao;

import com.example.gesecole.model.Inscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDAO {
    private Connection connection;

    public InscriptionDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une inscription
    public void create(Inscription inscription) {
        String sql = "INSERT INTO inscription (etudiant_id, cours_id, date_inscription) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, inscription.getEtudiantId());
            preparedStatement.setInt(2, inscription.getCoursId());
            preparedStatement.setDate(3, new java.sql.Date(inscription.getDateInscription().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout de l'inscription", e);
        }
    }

    // Récupérer une inscription
    public Inscription read(int id) {
        String sql = "SELECT * FROM inscription WHERE id = ?";
        Inscription inscription = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int inscriptionId = resultSet.getInt("id");
                int etudiantId = resultSet.getInt("etudiant_id");
                int coursId = resultSet.getInt("cours_id");
                java.sql.Date dateInscription = resultSet.getDate("date_inscription");

                inscription = new Inscription(inscriptionId, etudiantId, coursId, dateInscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération de l'inscription", e);
        }

        return inscription;
    }

    // Mettre à jour une inscription
    public void update(Inscription inscription) {
        String sql = "UPDATE inscription SET etudiant_id = ?, cours_id = ?, date_inscription = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, inscription.getEtudiantId());
            preparedStatement.setInt(2, inscription.getCoursId());
            preparedStatement.setDate(3, new java.sql.Date(inscription.getDateInscription().getTime()));
            preparedStatement.setInt(4, inscription.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour de l'inscription", e);
        }
    }

    // Supprimer une inscription
    public void delete(int id) {
        String sql = "DELETE FROM inscription WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de l'inscription", e);
        }
    }

    // Récupérer toutes les inscriptions
    public List<Inscription> findAll() {
        List<Inscription> inscriptions = new ArrayList<>();
        String sql = "SELECT * FROM inscription";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int etudiantId = resultSet.getInt("etudiant_id");
                int coursId = resultSet.getInt("cours_id");
                java.sql.Date dateInscription = resultSet.getDate("date_inscription");

                Inscription inscription = new Inscription(id, etudiantId, coursId, dateInscription);
                inscriptions.add(inscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des inscriptions", e);
        }

        return inscriptions;
    }
}
