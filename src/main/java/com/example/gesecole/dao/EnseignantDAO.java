package com.example.gesecole.dao;

import com.example.gesecole.model.Enseignant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnseignantDAO {
    private Connection connection;

    public EnseignantDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Enseignant enseignant) {
        String sql = "INSERT INTO enseignant (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, enseignant.getNom());
            preparedStatement.setString(2, enseignant.getPrenom());
            preparedStatement.setString(3, enseignant.getEmail());
            preparedStatement.setString(4, enseignant.getTelephone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Bien gérer les exceptions
            throw new RuntimeException("Erreur lors de l'ajout de l'enseignant", e);
        }
    }
    //Affficher toutes les infos d'un enseignant

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
            e.printStackTrace(); // Ajoute une gestion plus détaillée des exceptions
            throw new RuntimeException("Erreur lors de la récupération de l'enseignant", e);
        }

        return enseignant;
    }

    // Mise à jour d'un étudiant
    public void update(Enseignant enseignant){
        String sql = "UPDATE enseignant set nom = ?, prenom = ?, email = ?, telephone = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enseignant.getNom());
            preparedStatement.setString(2, enseignant.getPrenom());
            preparedStatement.setString(3, enseignant.getEmail());
            preparedStatement.setString(4, enseignant.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
