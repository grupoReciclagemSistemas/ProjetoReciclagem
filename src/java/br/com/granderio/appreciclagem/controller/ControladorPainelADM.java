/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Gerador;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean
@SessionScoped
@Named(value="controlePainel")
public class ControladorPainelADM implements Serializable {
    
    /*
    0 --> Lista Gerador
    1 --> Lista Transportador
    2 --> Lista Reciclador
    3 --> Lista Material
    */
    private int listagem = 0;
    
    public ControladorPainelADM(){
        
    }
    
    public void removerGeradorSQL(long id){
        DAO<Gerador> dao = new DAO(new Gerador() );
        dao.excluirGeradorPorSQL(id);
    }
    
    public void removerTransportadorSQL(long id){
        DAO<Gerador> dao = new DAO(new Gerador() );
        dao.excluirTranposrtadorPorSQL(id);
    }
    
    public void removerRecicladorSQL(long id){
        DAO<Gerador> dao = new DAO(new Gerador() );
        dao.excluirRecicladorPorSQL(id);
    }
    
    public void mudarListagem(int novaLista){
        listagem = novaLista;
    }

    /**
     * @return the listagem
     */
    public int getListagem() {
        return listagem;
    }

    /**
     * @param listagem the listagem to set
     */
    public void setListagem(int listagem) {
        this.listagem = listagem;
    }

}
