package com.github.gabrielsilper.daos;

import com.github.gabrielsilper.models.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipioDAO {
    public void insert(Connection con, Municipio municipio){
        String sql = "INSERT INTO municipios " +
                " (codigo_ibge, uf, municipio) " +
                " VALUES (?, ?, ?) " +
                " ON DUPLICATE KEY UPDATE " +
                " uf = VALUES(uf), " +
                " municipio = VALUES(municipio);";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, municipio.getCodigoIbge());
            ps.setString(2, municipio.getUf());
            ps.setString(3, municipio.getMunicipio());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String findCodigoByUfAndNome(Connection con, String uf, String municipio) throws SQLException {
        String sql = "SELECT codigo_ibge FROM municipios WHERE uf = ? AND municipio = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uf);
            ps.setString(2, municipio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("codigo_ibge");
                }
            }
        }
        return null;
    }
}
