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
    

    public ControladorTransportador(){
        super(new Transportador());
    }
    
    public String removerTransportador(Transportador trans){
        DAO<Transportador> acesso  = new DAO(trans);
        acesso.excluir();
        return "transportadores?faces-redirect=true";
    }

}
