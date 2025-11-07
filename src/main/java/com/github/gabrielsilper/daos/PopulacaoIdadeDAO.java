package com.github.gabrielsilper.daos;

import com.github.gabrielsilper.models.PopulacaoPorFaixaEtaria;
import com.github.gabrielsilper.models.PopulacaoTotal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulacaoIdadeDAO {
    public void insert(Connection con, PopulacaoPorFaixaEtaria populacaoIdade){
        String sql = "INSERT INTO populacao_faixa_etaria " +
                " (municipio, populacao_0_10, populacao_11_20, populacao_21_30, populacao_31_40, populacao_41_mais) " +
                " VALUES (?, ?, ?, ?, ?, ?) ";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, populacaoIdade.getMunicipio());
            ps.setString(2, populacaoIdade.getPopulacao0A10());
            ps.setString(3, populacaoIdade.getPopulacao11A20());
            ps.setString(4, populacaoIdade.getPopulacao21A30());
            ps.setString(5, populacaoIdade.getPopulacao31A40());
            ps.setString(6, populacaoIdade.getPopulacao41Mais());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public PopulacaoPorFaixaEtaria findByMunicipio(Connection con, String codigoIbge) throws SQLException {
        String sql = "SELECT * FROM populacao_faixa_etaria WHERE municipio = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoIbge);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PopulacaoPorFaixaEtaria popIdade = new PopulacaoPorFaixaEtaria();
                    popIdade.setMunicipio(rs.getString("municipio"));
                    popIdade.setPopulacao0A10(rs.getString("populacao_0_10"));
                    popIdade.setPopulacao11A20(rs.getString("populacao_11_20"));
                    popIdade.setPopulacao21A30(rs.getString("populacao_21_30"));
                    popIdade.setPopulacao31A40(rs.getString("populacao_31_40"));
                    popIdade.setPopulacao41Mais(rs.getString("populacao_41_mais"));
                    return popIdade;
                }
            }
        }
        return null; // Retorna null se não encontrar
    }
}
