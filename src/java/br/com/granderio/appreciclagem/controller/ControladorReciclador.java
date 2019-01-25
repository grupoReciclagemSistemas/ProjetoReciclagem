/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Reciclador;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorReciclador")
public class ControladorReciclador extends ControladorPrincipal<Reciclador> {
    
       
    public ControladorReciclador(){
        super(new Reciclador());
        
    }
    
    public List<Reciclador> lista(){
        DAO<Reciclador> lista = new DAO(new Reciclador());
        return lista.obterLista();
    }
 
    public String testarTrans(boolean valor){
        if(valor){
            return "Sim";
        }
        return "Não";              
    }
    
    public String novoReciclador(){
        return "registrar?faces-redirect=true";
    }
      
}
