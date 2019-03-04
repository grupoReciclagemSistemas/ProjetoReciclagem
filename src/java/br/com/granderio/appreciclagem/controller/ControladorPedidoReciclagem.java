/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorPedidoReciclagem")
public class ControladorPedidoReciclagem extends ControladorPrincipal<PedidoReciclagem> {
    
    public ControladorPedidoReciclagem(){
        super (new PedidoReciclagem());
    }
    
    public List<PedidoReciclagem> listagemVendas(){
        DAO<PedidoReciclagem> dao = new DAO(new PedidoReciclagem());
        return (List<PedidoReciclagem>) dao.listaDeMateriaisVendendo();
    }
    
    public List<PedidoReciclagem> geradorItensVendendo(Integer id){
        String query = "SELECT * FROM pedidoreciclagem as P WHERE P.reciclador_idPessoaJuridica IS NULL AND P.transportador_idPessoaJuridica IS NULL"
                + "AND P.gerador_idPessoaJuridica = " + id;
        DAO<PedidoReciclagem> dao = new DAO(new PedidoReciclagem());
        return (List<PedidoReciclagem>) dao.executarConsultaPersonalizada(query);
    }
    
}
