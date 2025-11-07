package com.github.gabrielsilper.service;

import com.github.gabrielsilper.daos.*;
import com.github.gabrielsilper.db.DatabaseConnection;
import com.github.gabrielsilper.models.*;
import com.github.gabrielsilper.models.dto.DadosPopulacaoDTO;
import com.github.gabrielsilper.models.dto.DadosSaudeDTO;
import com.google.gson.Gson;
import javax.jws.WebService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "com.github.gabrielsilper.service.IConsultaDadosService")
public class ConsultaDadosServiceImpl implements IConsultaDadosService {

    private final MunicipioDAO municipioDAO;
    private final PopulacaoDAO populacaoDAO;
    private final PopulacaoIdadeDAO populacaoIdadeDAO;
    private final ProfissionaisSaudeDAO profissionaisSaudeDAO;
    private final EstabelecimentosSaudeDAO estabelecimentosSaudeDAO;
    private final CepDAO cepDAO;

    public ConsultaDadosServiceImpl() {
        this.municipioDAO = new MunicipioDAO();
        this.populacaoDAO = new PopulacaoDAO();
        this.populacaoIdadeDAO = new PopulacaoIdadeDAO();
        this.profissionaisSaudeDAO = new ProfissionaisSaudeDAO();
        this.estabelecimentosSaudeDAO = new EstabelecimentosSaudeDAO();
        this.cepDAO = new CepDAO();
    }

    /**
     * TAREFA 1: Retorna dados de população.
     */
    @Override
    public DadosPopulacaoDTO getDadosPopulacao(String uf, String municipio) {
        DadosPopulacaoDTO resposta = new DadosPopulacaoDTO();

        try (Connection con = DatabaseConnection.getConnection()) {
            // 1. Achar o código IBGE
            String codigoIbge = municipioDAO.findCodigoByUfAndNome(con, uf, municipio);

            if (codigoIbge == null) {
                resposta.setMensagemErro("Município não encontrado: " + municipio + "/" + uf);
                return resposta;
            }

            // 2. Buscar População Total (Masc/Fem)
            PopulacaoTotal popTotal = populacaoDAO.findByMunicipio(con, codigoIbge);
            if (popTotal != null) {
                long masc = safeParseLong(popTotal.getPopulacaoMasc());
                long fem = safeParseLong(popTotal.getPopulacaoFem());
                resposta.setPopulacaoMasculina(masc);
                resposta.setPopulacaoFeminina(fem);
                resposta.setPopulacaoGeral(masc + fem); // Conforme OBS do professor [cite: 29]
            }

            // 3. Buscar População por Faixa Etária
            PopulacaoPorFaixaEtaria popFaixa = populacaoIdadeDAO.findByMunicipio(con, codigoIbge);
            if (popFaixa != null) {
                resposta.setFaixa0a10(safeParseLong(popFaixa.getPopulacao0A10()));
                resposta.setFaixa11a20(safeParseLong(popFaixa.getPopulacao11A20()));
                resposta.setFaixa21a30(safeParseLong(popFaixa.getPopulacao21A30()));
                resposta.setFaixa31a40(safeParseLong(popFaixa.getPopulacao31A40()));
                resposta.setFaixa41Mais(safeParseLong(popFaixa.getPopulacao41Mais()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resposta.setMensagemErro("Erro de banco de dados: " + e.getMessage());
        }
        return resposta;
    }

    /**
     * TAREFA 2: Retorna dados de saúde.
     */
    @Override
    public DadosSaudeDTO getDadosSaude(String uf, String municipio) {
        DadosSaudeDTO resposta = new DadosSaudeDTO();

        try (Connection con = DatabaseConnection.getConnection()) {
            // 1. Achar o código IBGE
            String codigoIbge = municipioDAO.findCodigoByUfAndNome(con, uf, municipio);

            if (codigoIbge == null) {
                resposta.setMensagemErro("Município não encontrado: " + municipio + "/" + uf);
                return resposta;
            }

            // 2. Buscar Quantidade de Profissionais
            ProfissionaisSaude prof = profissionaisSaudeDAO.findByMunicipio(con, codigoIbge);
            if (prof != null) {
                resposta.setQuantidadeMedicos(prof.getQuantidadeMedicos());
                resposta.setQuantidadeEnfermeiros(prof.getQuantidadeEnfermeiros());
            }

            // 3. Buscar Lista de UBS (Estabelecimentos)
            List<EstbalecimentosSaude> ubsList = estabelecimentosSaudeDAO.findByMunicipio(con, codigoIbge);
            resposta.setListaUBS(ubsList);
            resposta.setQuantidadeUBS(ubsList.size());

        } catch (SQLException e) {
            e.printStackTrace();
            resposta.setMensagemErro("Erro de banco de dados: " + e.getMessage());
        }
        return resposta;
    }

    /**
     * TAREFA 3: Valida/Atualiza CEP usando API REST ViaCEP.
     */
    @Override
    public CEP validarEnderecoCEP(String cep) {
        // Limpa o CEP para conter apenas dígitos
        String cepLimpo = cep.replaceAll("[^0-9]", "");
        if (cepLimpo.length() != 8) {
            return null; // Ou um CEP com erro
        }

        // 1. Chama a API REST do ViaCEP (conforme permitido pelo professor) [cite: 17]
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cepLimpo + "/json/"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonBody = response.body();

                // 2. Usa GSON (que está no pom.xml) para converter o JSON no seu Model
                Gson gson = new Gson();
                CEP cepInfo = gson.fromJson(jsonBody, CEP.class);

                // Se não for um CEP inválido (ex: "erro": "true" do ViaCEP)
                if (cepInfo != null && cepInfo.getCep() != null) {

                    // 3. Atualiza nosso banco de dados local com essa informação
                    try (Connection con = DatabaseConnection.getConnection()) {
                        // O método addCEP do seu colega já faz "ON DUPLICATE KEY UPDATE"
                        cepDAO.addCEP(con, cepInfo);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Falha no DB, mas o CEP foi encontrado, então retorna mesmo assim
                    }
                }

                return cepInfo; // Retorna o CEP encontrado
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se falhar
    }

    // Função auxiliar para converter os números que estão como String no banco
    private long safeParseLong(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0L;
        }
        try {
            // Remove vírgulas ou pontos de milhar, se houver
            return Long.parseLong(s.replaceAll("[.,]", ""));
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}