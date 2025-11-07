
package com.github.gabrielsilper.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de dadosSaudeDTO complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="dadosSaudeDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaUBS" type="{http://service.gabrielsilper.github.com/}estbalecimentosSaude" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="mensagemErro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quantidadeEnfermeiros" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="quantidadeMedicos" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="quantidadeUBS" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosSaudeDTO", propOrder = {
    "listaUBS",
    "mensagemErro",
    "quantidadeEnfermeiros",
    "quantidadeMedicos",
    "quantidadeUBS"
})
public class DadosSaudeDTO {

    @XmlElement(nillable = true)
    protected List<EstbalecimentosSaude> listaUBS;
    protected String mensagemErro;
    protected int quantidadeEnfermeiros;
    protected int quantidadeMedicos;
    protected int quantidadeUBS;

    /**
     * Gets the value of the listaUBS property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaUBS property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaUBS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EstbalecimentosSaude }
     * 
     * 
     */
    public List<EstbalecimentosSaude> getListaUBS() {
        if (listaUBS == null) {
            listaUBS = new ArrayList<EstbalecimentosSaude>();
        }
        return this.listaUBS;
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
     * Obtém o valor da propriedade quantidadeEnfermeiros.
     * 
     */
    public int getQuantidadeEnfermeiros() {
        return quantidadeEnfermeiros;
    }

    /**
     * Define o valor da propriedade quantidadeEnfermeiros.
     * 
     */
    public void setQuantidadeEnfermeiros(int value) {
        this.quantidadeEnfermeiros = value;
    }

    /**
     * Obtém o valor da propriedade quantidadeMedicos.
     * 
     */
    public int getQuantidadeMedicos() {
        return quantidadeMedicos;
    }

    /**
     * Define o valor da propriedade quantidadeMedicos.
     * 
     */
    public void setQuantidadeMedicos(int value) {
        this.quantidadeMedicos = value;
    }

    /**
     * Obtém o valor da propriedade quantidadeUBS.
     * 
     */
    public int getQuantidadeUBS() {
        return quantidadeUBS;
    }

    /**
     * Define o valor da propriedade quantidadeUBS.
     * 
     */
    public void setQuantidadeUBS(int value) {
        this.quantidadeUBS = value;
    }

}
