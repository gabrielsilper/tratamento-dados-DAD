
package com.github.gabrielsilper.cliente;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.github.gabrielsilper.cliente package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.github.gabrielsilper.cliente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DadosSaudeDTO }
     * 
     */
    public DadosSaudeDTO createDadosSaudeDTO() {
        return new DadosSaudeDTO();
    }

    /**
     * Create an instance of {@link EstbalecimentosSaude }
     * 
     */
    public EstbalecimentosSaude createEstbalecimentosSaude() {
        return new EstbalecimentosSaude();
    }

    /**
     * Create an instance of {@link DadosPopulacaoDTO }
     * 
     */
    public DadosPopulacaoDTO createDadosPopulacaoDTO() {
        return new DadosPopulacaoDTO();
    }

    /**
     * Create an instance of {@link Cep }
     * 
     */
    public Cep createCep() {
        return new Cep();
    }

}
