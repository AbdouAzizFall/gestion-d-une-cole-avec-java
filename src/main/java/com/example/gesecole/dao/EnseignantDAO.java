package com.example.gesecole.dao;

import com.example.gesecole.model.Enseignant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDAO {
    private Connection connection;

    public EnseignantDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajout d'un enseignant
    public void create(Enseignant enseignant) {
        String sql = "INSERT INTO enseignant (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, enseignant.getNom());
            preparedStatement.setString(2, enseignant.getPrenom());
            preparedStatement.setString(3, enseignant.getEmail());
            preparedStatement.setString(4, enseignant.getTelephone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout de l'enseignant", e);
        }
    }

    // Afficher les infos d'un enseignant
    public Enseignant read(int id) {
        String sql = "SELECT * FROM enseignant WHERE id = ?";
        Enseignant enseignant = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int enseignantId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String telephone = resultSet.getString("telephone");

                enseignant = new Enseignant(enseignantId, nom, prenom, email, telephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération de l'enseignant", e);
        }

        return enseignant;
    }

    // Mise à jour d'un enseignant
    public void update(Enseignant enseignant) {
        String sql = "UPDATE enseignant SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enseignant.getNom());
            preparedStatement.setString(2, enseignant.getPrenom());
            preparedStatement.setString(3, enseignant.getEmail());
            preparedStatement.setString(4, enseignant.getTelephone());
            preparedStatement.setInt(5, enseignant.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'enseignant", e);
        }
    }

    // Suppression d'un enseignant
    public void delete(int id) {
        String sql = "DELETE FROM enseignant WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de l'enseignant", e);
        }
    }

    // Récupérer tous les enseignants
    public List<Enseignant> findAll() {
        List<Enseignant> enseignants = new ArrayList<>();
        String sql = "SELECT * FROM enseignant";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String telephone = resultSet.getString("telephone");

                Enseignant enseignant = new Enseignant(id, nom, prenom, email, telephone);
                enseignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des enseignants", e);
        }

        return enseignants;
    }
}
