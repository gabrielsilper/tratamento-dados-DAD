package com.github.gabrielsilper.service;

import com.github.gabrielsilper.models.CEP;
import com.github.gabrielsilper.models.dto.DadosPopulacaoDTO;
import com.github.gabrielsilper.models.dto.DadosSaudeDTO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IConsultaDadosService {

    @WebMethod
    DadosPopulacaoDTO getDadosPopulacao(
            @WebParam(name = "uf") String uf,
            @WebParam(name = "municipio") String municipio);

    @WebMethod
    DadosSaudeDTO getDadosSaude(
            @WebParam(name = "uf") String uf,
            @WebParam(name = "municipio") String municipio);

    @WebMethod
    CEP validarEnderecoCEP(@WebParam(name = "cep") String cep);
}