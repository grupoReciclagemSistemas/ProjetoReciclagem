/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.util;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.granderio.appreciclagem.model.Endereco;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 * https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl
 */
public class Consulta {

    public static Endereco consultarCEP(String cep) throws SQLException_Exception, SigepClienteException{
         Endereco retorno = new Endereco();
         br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService service = new br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService();
         br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente port = service.getAtendeClientePort();
         br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP result = port.consultaCEP(cep);
         
         retorno.setBairro(result.getBairro());
         retorno.setCep(cep);
         retorno.setCidade(result.getCidade());
         retorno.setLogradouro(result.getEnd());
         retorno.setUf(result.getUf());
         return retorno;
    }
}
