package com.github.gabrielsilper;

import com.github.gabrielsilper.daos.ProfissionaisSaudeDAO;
import com.github.gabrielsilper.db.DatabaseConnection;
import com.github.gabrielsilper.models.ProfissionaisSaude;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MedicosMain {
    public static void main(String[] args) {
        String caminho = "/home/gasp/gabriel-dev/java/tratamento-dados-DAD/medicos.csv";
        ProfissionaisSaudeDAO profissionaisSaudeDAO = new ProfissionaisSaudeDAO();

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
                    int qtdMedicos = Integer.parseInt(linha[2]);

                    ProfissionaisSaude profissionaisSaude = new ProfissionaisSaude(codigoMunicipio, qtdMedicos, null);
                    profissionaisSaudeDAO.insertQuantidadeMedicos(connection, profissionaisSaude);
                    connection.commit();
                }
                System.out.println("Quantidade de médicos importada.");
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
