package com.example.gesecole.dao;

import com.example.gesecole.model.Classe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasseDAO {
    private Connection connection;

    public ClasseDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une classe
    public void create(Classe classe) {
        String sql = "INSERT INTO classe (nom, niveau) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, classe.getNom());
            preparedStatement.setString(2, classe.getNiveau());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout de la classe", e);
        }
    }

    // Récupérer une classe
    public Classe read(int id) {
        String sql = "SELECT * FROM classe WHERE id = ?";
        Classe classe = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int classeId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String niveau = resultSet.getString("niveau");

                classe = new Classe(classeId, nom, niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération de la classe", e);
        }

        return classe;
    }

    // Mettre à jour une classe
    public void update(Classe classe) {
        String sql = "UPDATE classe SET nom = ?, niveau = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, classe.getNom());
            preparedStatement.setString(2, classe.getNiveau());
            preparedStatement.setInt(3, classe.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour de la classe", e);
        }
    }

    // Supprimer une classe
    public void delete(int id) {
        String sql = "DELETE FROM classe WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de la classe", e);
        }
    }

    // Récupérer toutes les classes
    public List<Classe> findAll() {
        List<Classe> classes = new ArrayList<>();
        String sql = "SELECT * FROM classe";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String niveau = resultSet.getString("niveau");

                Classe classe = new Classe(id, nom, niveau);
                classes.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des classes", e);
        }

        return classes;
    }
}
