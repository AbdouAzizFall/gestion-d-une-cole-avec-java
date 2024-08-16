package com.example.gesecole.dao;

import com.example.gesecole.model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EtudiantDAO {
    private Connection connection;

    public EtudiantDAO(Connection connection) {
        this.connection = connection;
    }

    //Ajout d'un etudiant

    public void create(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant (nom, prenom, date_naissance, adresse, email) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getPrenom());
            preparedStatement.setDate(3, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            preparedStatement.setString(4, etudiant.getAdresse());
            preparedStatement.setString(5, etudiant.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout de l'étudiant", e);
        }
    }

    //Affficher toutes les infos d'un etudiant
    public Etudiant read(int id) {
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        Etudiant etudiant = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                etudiant = new Etudiant(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date_naissance"),
                        resultSet.getString("adresse"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiant;
    }


    // Mise à jour d'un étudiant
    public void update(Etudiant etudiant) {
        String sql = "UPDATE etudiant SET nom = ?, prenom = ?, date_naissance = ?, adresse = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getPrenom());
            preparedStatement.setDate(3, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            preparedStatement.setString(4, etudiant.getAdresse());
            preparedStatement.setString(5, etudiant.getEmail());
            preparedStatement.setInt(6, etudiant.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Suppression d'un étudiant
    public void delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                java.sql.Date dateNaissance = resultSet.getDate("date_naissance");
                String adresse = resultSet.getString("adresse");
                String email = resultSet.getString("email");

                Etudiant etudiant = new Etudiant(id, nom, prenom, dateNaissance, adresse, email);
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des étudiants", e);
        }

        return etudiants;
    }



}




