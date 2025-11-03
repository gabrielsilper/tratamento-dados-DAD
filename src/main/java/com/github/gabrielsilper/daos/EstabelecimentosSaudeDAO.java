package com.github.gabrielsilper.daos;

import com.github.gabrielsilper.models.EstbalecimentosSaude;

import java.sql.*;

public class EstabelecimentosSaudeDAO {
    //Consulta para encontrar o código de 7 dígitos que comece com o código de 6 dígitos
    private static final String SQL_FIND_IBGE_7 =
            "SELECT codigo_ibge FROM municipios WHERE codigo_ibge LIKE ? LIMIT 1";

    private static final String SQL_INSERT =
            "INSERT INTO estabelecimentos_de_saude " +
                    " (municipio, nome, logradouro, bairro, latitude, longitude, cep) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?) ";

    // Retorna o código IBGE de 7 ou 8 dígitos
    private String findValidMunicipioCode(Connection con, String code6Digits) throws SQLException {
        // Assume que o código da tabela municipios é maior ou igual
        String searchPattern = code6Digits + "%";
        try (PreparedStatement ps = con.prepareStatement(SQL_FIND_IBGE_7)) {
            ps.setString(1, searchPattern);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("codigo_ibge"); // Retorna o código ibge
                }
            }
        }
        throw new SQLException("Código IBGE " + code6Digits + " não encontrado na tabela municipios (FK fail).");
    }

    // Lançamento (throws) SQLException, delegando o tratamento de erro para o Main.
    public void insert(Connection con, EstbalecimentosSaude estabelecimentos) throws SQLException {

        //Extrai o código RAW de 6 dígitos
        String ibgeRawCode = estabelecimentos.getMunicipio();

        //Busca o código válido de mais dígitos no banco de dados.
        // Se o códigoRaw for nulo ou vazio, findValidMunicipioCode lançará exceção.
        String ibgeValid = findValidMunicipioCode(con, ibgeRawCode);

        //Insere, tratando os NULLS para Latitude e Longitude
        try(PreparedStatement ps = con.prepareStatement(SQL_INSERT)){

            ps.setString(1, ibgeValid); // Código de 7 dígitos
            ps.setString(2, estabelecimentos.getNome());
            ps.setString(3, estabelecimentos.getLogradouro());
            ps.setString(4, estabelecimentos.getBairro());
            ps.setString(5, estabelecimentos.getLatitude());
            ps.setString(6, estabelecimentos.getLongitude());
            ps.setString(7, estabelecimentos.getCep());

            ps.executeUpdate();
        }
    }
}
