/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.PessoaJuridica;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorGerador")
public class ControladorGerador extends ControladorPrincipal <Gerador> {
    
    private Gerador novoGerador;
    
    public ControladorGerador(){
        super( new Gerador() );
        novoGerador = new Gerador();
    }
    
    public String editarGerador(Gerador gerador){
        DAO<Gerador> acesso = new DAO(gerador);
        acesso.alterar();
        return "geradores?faces-redirect=true";
    }
    
    public String removerGerador(Gerador gerador){
        DAO<Gerador> acesso = new DAO(gerador);
        acesso.excluir();
        return "geradores?faces-redirect=true";
    }
      
    public String voltar(){
        novoGerador.setCnpj("");
        novoGerador.setEmail("");
        novoGerador.setRazaoSocial("");
        novoGerador.setTelefone("");
        return "geradores?faces-redirect=true";
    }
    /**
     * @return the novoGerador
     */
    public Gerador getNovoGerador() {
        return novoGerador;
    }

    /**
     * @param novoGerador the novoGerador to set
     */
    public void setNovoGerador(Gerador novoGerador) {
        this.novoGerador = novoGerador;
    }
    
}
