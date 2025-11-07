package com.github.gabrielsilper.service;

import javax.xml.ws.Endpoint;

public class ServidorMain {
    public static void main(String[] args) {
        String url = "http://localhost:8080/servico/consultas";

        // Publica o serviço
        Endpoint.publish(url, new ConsultaDadosServiceImpl());

        System.out.println("==================================================");
        System.out.println("🚀 SERVIÇO SOAP NO AR! 🚀");
        System.out.println("Ouvindo em: " + url);
        System.out.println("WSDL (Contrato) disponível em: " + url + "?wsdl");
        System.out.println("Pressione CTRL+C para parar o servidor.");
        System.out.println("==================================================");
    }
}
