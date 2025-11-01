package com.github.gabrielsilper;

import com.github.gabrielsilper.daos.PopulacaoDAO;
import com.github.gabrielsilper.db.DatabaseConnection;
import com.github.gabrielsilper.models.PopulacaoTotal;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PopulacaoMain {
    public static void main(String[] args) {
        String caminho = "/home/gasp/gabriel-dev/java/json-cep-reader/populacao.csv";
        PopulacaoDAO populacaoDAO = new PopulacaoDAO();

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            try (CSVReader reader = new CSVReader(new FileReader(caminho))) {
                String[] linha;
                boolean primeiraLinha = true;
                while ((linha = reader.readNext()) != null) {
                    if (primeiraLinha) {
                        primeiraLinha = false;
                        continue;
                    }
                    String codigoMunicipio = linha[0];
                    String QtdMasc = linha[3];
                    String QtdFem = linha[4];

                    PopulacaoTotal populacaoTotal = new PopulacaoTotal(codigoMunicipio, QtdMasc, QtdFem);
                    populacaoDAO.insert(connection, populacaoTotal);
                    connection.commit();
                }
                System.out.println("Populações importadas.");
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            } finally {
                connection.rollback();
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
