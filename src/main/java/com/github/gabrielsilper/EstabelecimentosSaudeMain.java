package com.github.gabrielsilper;

import com.github.gabrielsilper.daos.EstabelecimentosSaudeDAO;
import com.github.gabrielsilper.db.DatabaseConnection;
import com.github.gabrielsilper.models.EstbalecimentosSaude;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;

public class EstabelecimentosSaudeMain {
    public static void main(String[] args) {
        // Altere o caminho para o seu arquivo
        String caminho = "C:\\Users\\Elias Dev\\tratamento-dados-DAD\\estabelecimentos_de_saude.csv";
        EstabelecimentosSaudeDAO estabelecimentosSaudeDAO = new EstabelecimentosSaudeDAO();

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            int linhaAtual = 0;

            try (CSVReader reader = new CSVReader(new FileReader(caminho))) {
                String[] linha;
                boolean primeiraLinha = true;

                while ((linha = reader.readNext()) != null) {
                    linhaAtual++;
                    if (primeiraLinha) {
                        primeiraLinha = false;
                        continue;
                    }

                    // Extração e Conversão de Dados
                    // O código do município precisa de limpeza para a Foreign Key funcionar
                    String codigoMunicipio = linha[1];
                    codigoMunicipio = codigoMunicipio.replaceAll("\\.0$", "").trim();

                    String nome = linha[2];
                    String logradouro = linha[3];
                    String bairro = linha[4];
                    String latitude = linha[5];
                    String longitude = linha[6];
                    String cep = linha[7];

                    // Criação do Modelo e Inserção
                    EstbalecimentosSaude estbalecimentosSaude = new EstbalecimentosSaude(
                            codigoMunicipio, nome, logradouro, bairro,
                            latitude, longitude, cep
                    );

                    // O DAO fará o tratamento da Foreign Key (código IBGE)
                    estabelecimentosSaudeDAO.insert(connection, estbalecimentosSaude);
                }

                connection.commit();
                System.out.println("Estabelecimentos de Saúde importados com sucesso.");

            } catch (IOException | CsvValidationException e) {
                System.err.println("Erro ao ler/processar o arquivo CSV: " + e.getMessage());
                connection.rollback();
            } catch (SQLException e) {
                // Este catch agora pegará erros de SQL e de Foreign Key (do DAO)
                System.err.println("Erro de SQL/Conexão na linha " + linhaAtual + ": " + e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Erro de SQL/Conexão: " + e.getMessage());
        }
    }
}
