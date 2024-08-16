package com.example.gesecole.dao;

import com.example.gesecole.model.Cours;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {
    private Connection connection;

    public CoursDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un cours
    public void create(Cours cours) {
        String sql = "INSERT INTO cours (nom, description, enseignant_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cours.getNom());
            preparedStatement.setString(2, cours.getDescription());
            preparedStatement.setInt(3, cours.getEnseignantId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout du cours", e);
        }
    }

    // Récupérer un cours
    public Cours read(int id) {
        String sql = "SELECT * FROM cours WHERE id = ?";
        Cours cours = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int coursId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                int enseignantId = resultSet.getInt("enseignant_id");

                cours = new Cours(coursId, nom, description, enseignantId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération du cours", e);
        }

        return cours;
    }

    // Mettre à jour un cours
    public void update(Cours cours) {
        String sql = "UPDATE cours SET nom = ?, description = ?, enseignant_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cours.getNom());
            preparedStatement.setString(2, cours.getDescription());
            preparedStatement.setInt(3, cours.getEnseignantId());
            preparedStatement.setInt(4, cours.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour du cours", e);
        }
    }

    // Supprimer un cours
    public void delete(int id) {
        String sql = "DELETE FROM cours WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression du cours", e);
        }
    }

    // Récupérer tous les cours
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        String sql = "SELECT * FROM cours";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                int enseignantId = resultSet.getInt("enseignant_id");

                Cours cours = new Cours(id, nom, description, enseignantId);
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des cours", e);
        }

        return coursList;
    }
}
