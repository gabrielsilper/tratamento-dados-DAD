package com.github.gabrielsilper.daos;

import com.github.gabrielsilper.models.PopulacaoTotal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulacaoDAO {
    public void insert(Connection con, PopulacaoTotal populacaoTotal){
        String sql = "INSERT INTO populacao_total " +
                " (municipio, populacao_masc, populacao_fem) " +
                " VALUES (?, ?, ?) ";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, populacaoTotal.getMunicipio());
            ps.setString(2, populacaoTotal.getPopulacaoMasc());
            ps.setString(3, populacaoTotal.getPopulacaoFem());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public PopulacaoTotal findByMunicipio(Connection con, String codigoIbge) throws SQLException {
        String sql = "SELECT * FROM populacao_total WHERE municipio = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoIbge);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PopulacaoTotal pop = new PopulacaoTotal();
                    pop.setMunicipio(rs.getString("municipio"));
                    pop.setPopulacaoMasc(rs.getString("populacao_masc"));
                    pop.setPopulacaoFem(rs.getString("populacao_fem"));
                    return pop;
                }
            }
        }
        return null; // Retorna null se não encontrar
    }
}
