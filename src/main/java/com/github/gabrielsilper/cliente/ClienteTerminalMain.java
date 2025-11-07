package com.github.gabrielsilper.cliente;

import java.util.Scanner;
import java.net.URL;
import javax.xml.namespace.QName;

public class ClienteTerminalMain {
    public static void main(String[] args) throws Exception {

        System.out.println("Conectando ao serviço SOAP em http://localhost:8080/servico/consultas?wsdl ...");

        URL wsdlURL = new URL("http://localhost:8080/servico/consultas?wsdl");
        QName serviceQName = new QName("http://service.gabrielsilper.github.com/", "ConsultaDadosServiceImplService");
        QName portQName = new QName("http://service.gabrielsilper.github.com/", "ConsultaDadosServiceImplPort");
        ConsultaDadosServiceImplService serviceFactory = new ConsultaDadosServiceImplService(wsdlURL, serviceQName);
        IConsultaDadosService servico = serviceFactory.getPort(portQName, IConsultaDadosService.class);


        // --- INTERAÇÃO COM O USUÁRIO ---

        System.out.println("==================================================");
        System.out.println("Cliente conectado com sucesso!");
        System.out.println("==================================================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a UF (ex: AM): ");
        String uf = scanner.nextLine().toUpperCase();
        System.out.print("Digite o Município (ex: Manaus): ");
        String municipio = scanner.nextLine();

        // --- TAREFA 1 ---
        System.out.println("\nBuscando dados da Tarefa 1 (População)...");
        DadosPopulacaoDTO pop = servico.getDadosPopulacao(uf, municipio);

        if (pop.getMensagemErro() != null) {
            System.out.println("Erro: " + pop.getMensagemErro());
        } else {
            System.out.println("População Geral: " + pop.getPopulacaoGeral());
            System.out.println("Homens: " + pop.getPopulacaoMasculina());
            System.out.println("Mulheres: " + pop.getPopulacaoFeminina());
            System.out.println("Faixa 0-10: " + pop.getFaixa0A10());
            System.out.println("Faixa 11-20: " + pop.getFaixa11A20());
            System.out.println("Faixa 21-30: " + pop.getFaixa21A30());
            System.out.println("Faixa 31-40: " + pop.getFaixa31A40());
            System.out.println("Faixa 41+: " + pop.getFaixa41Mais());
        }

        // --- TAREFA 2 ---
        System.out.println("\nBuscando dados da Tarefa 2 (Saúde)...");
        DadosSaudeDTO saude = servico.getDadosSaude(uf, municipio);

        if (saude.getMensagemErro() != null) {
            System.out.println("Erro: " + saude.getMensagemErro());
        } else {
            System.out.println("Total de UBS: " + saude.getQuantidadeUBS());
            System.out.println("Médicos: " + saude.getQuantidadeMedicos());
            System.out.println("Enfermeiros: " + saude.getQuantidadeEnfermeiros());

            // --- TAREFA 3 (INTEGRADA COM TAREFA 2) ---

            System.out.println("\n--- Iniciando Validação de Endereços das UBS (Tarefa 3) ---");

            if (saude.getListaUBS() != null && !saude.getListaUBS().isEmpty()) {
                // loop em cada UBS encontrada na Tarefa 2
                for (EstbalecimentosSaude ubs : saude.getListaUBS()) {
                    System.out.println("\n========================================");
                    System.out.println("UBS: " + ubs.getNome());
                    System.out.println("Endereço (do Banco): " + ubs.getLogradouro() + ", " + ubs.getBairro() + " (CEP: " + ubs.getCep() + ")");

                    // Pega o CEP da UBS atual
                    String cepParaValidar = ubs.getCep();

                    // Validar  CEP
                    if (cepParaValidar == null || cepParaValidar.trim().isEmpty()) {
                        System.out.println("Validação (ViaCEP): CEP não cadastrado no banco para esta UBS.");
                        continue; // Pula para a próxima UBS
                    }

                    // Chama a Tarefa 3 para este CEP
                    System.out.println("...Validando CEP " + cepParaValidar + " no ViaCEP...");
                    Cep cepInfo = servico.validarEnderecoCEP(cepParaValidar);

                    // resultado da validação
                    if (cepInfo != null && cepInfo.getCep() != null) {
                        System.out.println("Endereço (ViaCEP): " + cepInfo.getLogradouro() + ", " + cepInfo.getBairro() + ", " + cepInfo.getLocalidade() + "/" + cepInfo.getUf());

                        //Compara o logradouro do banco com o do ViaCEP
                        if (ubs.getLogradouro() != null && !ubs.getLogradouro().equalsIgnoreCase(cepInfo.getLogradouro()) && !cepInfo.getLogradouro().isEmpty()) {
                            System.out.println("!! ALERTA: Logradouro do banco de dados (" + ubs.getLogradouro() + ") é diferente do ViaCEP (" + cepInfo.getLogradouro() + ").");
                        } else {
                            System.out.println("Info: Endereço parece consistente.");
                        }
                    } else {
                        System.out.println("Validação (ViaCEP): CEP " + cepParaValidar + " não foi encontrado na base do ViaCEP.");
                    }
                }
                System.out.println("========================================");
                System.out.println("Fim da validação de UBSs.");

            } else {
                System.out.println("Nenhuma UBS encontrada neste município para validar.");
            }
        }
        scanner.close();
    }
}