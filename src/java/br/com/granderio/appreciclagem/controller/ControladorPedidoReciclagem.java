/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOPedidoReciclagem;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import java.util.ArrayList;
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
    
    private  DAOPedidoReciclagem dao = new DAOPedidoReciclagem(new PedidoReciclagem());
    private List<PedidoReciclagem> pedidosVendendo = new ArrayList<PedidoReciclagem>();
    
    private Material materialFiltro;
    private boolean listarPorCidadeEndereco = false;
    
    public ControladorPedidoReciclagem(){
        super (new PedidoReciclagem());
        this.updateList("");
    }
    
    public List<PedidoReciclagem> listagemVendas(){
        return (List<PedidoReciclagem>) getDao().listaDeMateriaisVendendo();
    }
    
    public List<PedidoReciclagem> geradorItensVendendo(Integer id){
        String query = "SELECT * FROM pedidoreciclagem as P WHERE P.reciclador_idPessoaJuridica IS NULL AND P.transportador_idPessoaJuridica IS NULL"
                + "AND P.gerador_idPessoaJuridica = " + id;
        DAO<PedidoReciclagem> dao = new DAO(new PedidoReciclagem());
        return (List<PedidoReciclagem>) dao.executarConsultaPersonalizada(query);
    }
    
    public void updateList(String cidade){
        if(materialFiltro != null && listarPorCidadeEndereco){
            pedidosVendendo = getDao().listarPorCidadeMaterial(cidade, materialFiltro.getIdMaterial());
        }else if(materialFiltro != null && (!listarPorCidadeEndereco)){
            pedidosVendendo = getDao().listarPorMaterial(materialFiltro.getIdMaterial());
        }else if(materialFiltro == null && listarPorCidadeEndereco){
            pedidosVendendo = getDao().listarPorCidade(cidade);
        }else{
            pedidosVendendo = getDao().listarTodos();
        }
    }

    /**
     * @return the pedidosVendendo
     */
    public List<PedidoReciclagem> getPedidosVendendo() {
        return pedidosVendendo;
    }

    /**
     * @param pedidosVendendo the pedidosVendendo to set
     */
    public void setPedidosVendendo(List<PedidoReciclagem> pedidosVendendo) {
        this.pedidosVendendo = pedidosVendendo;
    }

    /**
     * @return the materialFiltro
     */
    public Material getMaterialFiltro() {
        return materialFiltro;
    }

    /**
     * @param materialFiltro the materialFiltro to set
     */
    public void setMaterialFiltro(Material materialFiltro) {
        this.materialFiltro = materialFiltro;
    }

 
    /**
     * @return the listarPorCidadeEndereco
     */
    public boolean isListarPorCidadeEndereco() {
        return listarPorCidadeEndereco;
    }

    /**
     * @param listarPorCidadeEndereco the listarPorCidadeEndereco to set
     */
    public void setListarPorCidadeEndereco(boolean listarPorCidadeEndereco) {
        this.listarPorCidadeEndereco = listarPorCidadeEndereco;
    }

    /**
     * @return the dao
     */
    public DAOPedidoReciclagem getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DAOPedidoReciclagem dao) {
        this.dao = dao;
    }
    
}
