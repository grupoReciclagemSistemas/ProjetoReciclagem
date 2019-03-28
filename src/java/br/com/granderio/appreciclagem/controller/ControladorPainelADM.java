/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controlePainel")
@SessionScoped
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
 
    public void removerGerador(Gerador obj){
        DAO<Gerador> dao = new DAO(obj);
        dao.excluir();
    }
    
    public void removerReciclador(Reciclador obj){
        DAO<Reciclador> dao = new DAO(obj);
        dao.excluir();
    }
    
    public void removerTransportador(Transportador obj){
        DAO<Transportador> dao = new DAO(obj);
        dao.excluir();
    }
    
    public void removerMaterial(Material obj){
        DAO<Material> dao = new DAO(obj);
        dao.excluir();
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
