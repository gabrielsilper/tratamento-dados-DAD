
package com.github.gabrielsilper.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de dadosPopulacaoDTO complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="dadosPopulacaoDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="faixa0a10" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="faixa11a20" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="faixa21a30" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="faixa31a40" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="faixa41Mais" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="mensagemErro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="populacaoFeminina" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="populacaoGeral" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="populacaoMasculina" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosPopulacaoDTO", propOrder = {
    "faixa0A10",
    "faixa11A20",
    "faixa21A30",
    "faixa31A40",
    "faixa41Mais",
    "mensagemErro",
    "populacaoFeminina",
    "populacaoGeral",
    "populacaoMasculina"
})
public class DadosPopulacaoDTO {

    @XmlElement(name = "faixa0a10")
    protected long faixa0A10;
    @XmlElement(name = "faixa11a20")
    protected long faixa11A20;
    @XmlElement(name = "faixa21a30")
    protected long faixa21A30;
    @XmlElement(name = "faixa31a40")
    protected long faixa31A40;
    protected long faixa41Mais;
    protected String mensagemErro;
    protected long populacaoFeminina;
    protected long populacaoGeral;
    protected long populacaoMasculina;

    /**
     * Obtém o valor da propriedade faixa0A10.
     * 
     */
    public long getFaixa0A10() {
        return faixa0A10;
    }

    /**
     * Define o valor da propriedade faixa0A10.
     * 
     */
    public void setFaixa0A10(long value) {
        this.faixa0A10 = value;
    }

    /**
     * Obtém o valor da propriedade faixa11A20.
     * 
     */
    public long getFaixa11A20() {
        return faixa11A20;
    }

    /**
     * Define o valor da propriedade faixa11A20.
     * 
     */
    public void setFaixa11A20(long value) {
        this.faixa11A20 = value;
    }

    /**
     * Obtém o valor da propriedade faixa21A30.
     * 
     */
    public long getFaixa21A30() {
        return faixa21A30;
    }

    /**
     * Define o valor da propriedade faixa21A30.
     * 
     */
    public void setFaixa21A30(long value) {
        this.faixa21A30 = value;
    }

    /**
     * Obtém o valor da propriedade faixa31A40.
     * 
     */
    public long getFaixa31A40() {
        return faixa31A40;
    }

    /**
     * Define o valor da propriedade faixa31A40.
     * 
     */
    public void setFaixa31A40(long value) {
        this.faixa31A40 = value;
    }

    /**
     * Obtém o valor da propriedade faixa41Mais.
     * 
     */
    public long getFaixa41Mais() {
        return faixa41Mais;
    }

    /**
     * Define o valor da propriedade faixa41Mais.
     * 
     */
    public void setFaixa41Mais(long value) {
        this.faixa41Mais = value;
    }

    /**
     * Obtém o valor da propriedade mensagemErro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensagemErro() {
        return mensagemErro;
    }

    /**
     * Define o valor da propriedade mensagemErro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensagemErro(String value) {
        this.mensagemErro = value;
    }

    /**
     * Obtém o valor da propriedade populacaoFeminina.
     * 
     */
    public long getPopulacaoFeminina() {
        return populacaoFeminina;
    }

    /**
     * Define o valor da propriedade populacaoFeminina.
     * 
     */
    public void setPopulacaoFeminina(long value) {
        this.populacaoFeminina = value;
    }

    /**
     * Obtém o valor da propriedade populacaoGeral.
     * 
     */
    public long getPopulacaoGeral() {
        return populacaoGeral;
    }

    /**
     * Define o valor da propriedade populacaoGeral.
     * 
     */
    public void setPopulacaoGeral(long value) {
        this.populacaoGeral = value;
    }

    /**
     * Obtém o valor da propriedade populacaoMasculina.
     * 
     */
    public long getPopulacaoMasculina() {
        return populacaoMasculina;
    }

    /**
     * Define o valor da propriedade populacaoMasculina.
     * 
     */
    public void setPopulacaoMasculina(long value) {
        this.populacaoMasculina = value;
    }

}
