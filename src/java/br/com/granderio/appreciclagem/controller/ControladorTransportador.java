/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Transportador;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorTransportador")
public class ControladorTransportador extends ControladorPrincipal<Transportador> {
    
    private Transportador trans = new Transportador();
    
    public ControladorTransportador(){
        super(new Transportador());
    }
    
    public String registrarTransportador(){
        DAO<Transportador> acesso = new DAO(trans);
        acesso.inserir();
        return "transportadores?faces-redirect-true";
    }
    
    public String remover(Transportador trans){
        DAO<Transportador> acesso  = new DAO(trans);
               acesso.excluir();
               return "transportadores?faces-redirect=true";
    }

    /**
     * @return the trans
     */
    public Transportador getTrans() {
        return trans;
    }

    /**
     * @param trans the trans to set
     */
    public void setTrans(Transportador trans) {
        this.trans = trans;
    }
    
}
