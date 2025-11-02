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

public class EnfermeirosMain {
    public static void main(String[] args) {
        String caminho = "/home/gasp/gabriel-dev/java/tratamento-dados-DAD/enfermeiros.csv";
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
                    String qtdEnfermeirosString = linha[1];

                    int qtdEnfermeiros = 0;
                    if (!qtdEnfermeirosString.equals("-")) {
                        qtdEnfermeiros = Integer.parseInt(qtdEnfermeirosString);
                    }

                    ProfissionaisSaude profissionaisSaude = new ProfissionaisSaude(codigoMunicipio, null, qtdEnfermeiros);
                    profissionaisSaudeDAO.atualizarQuantidadeEnfermeiros(connection, profissionaisSaude);
                    connection.commit();
                }
                System.out.println("Quantidade de enfermeiros importada.");
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
