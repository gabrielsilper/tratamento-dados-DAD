package com.github.gabrielsilper.daos;

import com.github.gabrielsilper.models.ProfissionaisSaude;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfissionaisSaudeDAO {
    public void insertQuantidadeMedicos(Connection con, ProfissionaisSaude profissionaisSaude) {
        String sql = "INSERT INTO profissionais_saude " +
                " (municipio, quantidade_medicos, quantidade_enfermeiros) " +
                " VALUES (?, ?, null) ";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, profissionaisSaude.getMunicipio());
            ps.setInt(2, profissionaisSaude.getQuantidadeMedicos());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void atualizarQuantidadeEnfermeiros(Connection con, ProfissionaisSaude profissionaisSaude) {
        String sql = "UPDATE profissionais_saude " +
                " SET quantidade_enfermeiros = ? " +
                " WHERE municipio = ? ";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, profissionaisSaude.getQuantidadeEnfermeiros());
            ps.setString(2, profissionaisSaude.getMunicipio());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
