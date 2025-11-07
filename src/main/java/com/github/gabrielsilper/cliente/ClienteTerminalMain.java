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


        System.out.println("==================================================");
        System.out.println("Cliente conectado com sucesso!");
        System.out.println("==================================================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a UF (ex: AM): ");
        String uf = scanner.nextLine().toUpperCase();
        System.out.print("Digite o Município (ex: Manaus): ");
        String municipio = scanner.nextLine();

        // 3. Chamar a TAREFA 1
        System.out.println("\nBuscando dados da Tarefa 1 (População)...");
        DadosPopulacaoDTO pop = servico.getDadosPopulacao(uf, municipio);

        if (pop.getMensagemErro() != null) {
            System.out.println("Erro: " + pop.getMensagemErro());
        } else {
            System.out.println("População Geral: " + pop.getPopulacaoGeral());
            System.out.println("Homens: " + pop.getPopulacaoMasculina());
            System.out.println("Mulheres: " + pop.getPopulacaoFeminina());
            // Métodos corrigidos (getFaixa0A10, etc.)
            System.out.println("Faixa 0-10: " + pop.getFaixa0A10());
            System.out.println("Faixa 11-20: " + pop.getFaixa11A20());
            System.out.println("Faixa 21-30: " + pop.getFaixa21A30());
            System.out.println("Faixa 31-40: " + pop.getFaixa31A40());
            System.out.println("Faixa 41+: " + pop.getFaixa41Mais());
        }

        // 4. Chamar a TAREFA 2
        System.out.println("\nBuscando dados da Tarefa 2 (Saúde)...");
        DadosSaudeDTO saude = servico.getDadosSaude(uf, municipio);

        if (saude.getMensagemErro() != null) {
            System.out.println("Erro: " + saude.getMensagemErro());
        } else {
            System.out.println("Total de UBS: " + saude.getQuantidadeUBS());
            System.out.println("Médicos: " + saude.getQuantidadeMedicos());
            System.out.println("Enfermeiros: " + saude.getQuantidadeEnfermeiros());

            System.out.println("--- Lista de UBS ---");
            if (saude.getListaUBS() != null) {
                for (EstbalecimentosSaude ubs : saude.getListaUBS()) {
                    System.out.println("Nome: " + ubs.getNome() + ", End: " + ubs.getLogradouro() + ", CEP: " + ubs.getCep());
                }
            } else {
                System.out.println("Nenhuma UBS encontrada.");
            }
        }

        // 5. Chamar a TAREFA 3
        System.out.println("\nTestando Tarefa 3 (ViaCEP)...");
        System.out.print("Digite um CEP para validar (ex: 01001-000): ");
        String cep = scanner.nextLine();


        Cep cepInfo = servico.validarEnderecoCEP(cep);


        if (cepInfo != null && cepInfo.getCep() != null) {
            System.out.println("CEP Encontrado: " + cepInfo.getCep() + " - " + cepInfo.getLogradouro() + ", " + cepInfo.getBairro() + ", " + cepInfo.getLocalidade() + "/" + cepInfo.getUf());
        } else {
            System.out.println("CEP não encontrado.");
        }

        scanner.close();
    }
}