package com.github.gabrielsilper.daos;

import com.github.gabrielsilper.models.Municipio;
import com.github.gabrielsilper.models.Populacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PopulacaoDAO {
    public void insert(Connection con, Populacao populacao){
        String sql = "INSERT INTO populacao_total " +
                " (municipio, populacao_masc, populacao_fem) " +
                " VALUES (?, ?, ?) ";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, populacao.getMunicipio());
            ps.setString(2, populacao.getPopulacaoMasc());
            ps.setString(3, populacao.getPopulacaoFem());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
