/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorRegistrar")
public  class ControladorRegistrar implements Serializable {
    
    // 1 --> Reciclador
    // 2 --> Gerador
    // 3 --> Transportador
    private int tipoDePessoa;
    
    private Gerador novoGerador;
    private Reciclador novoReciclador;
    private Transportador novoTransportador;
     
    public ControladorRegistrar(){
        novoGerador = new Gerador();
        novoReciclador = new Reciclador();
        novoTransportador = new Transportador();
        
    }
    
    public void registrarTipoPessoa(int valor){
        this.setTipoDePessoa(valor);    
    }
    

     public String registrarTransportador(){
       DAO<Transportador> acesso = new DAO(novoTransportador);
       acesso.inserir();
       novoTransportador = new Transportador();
       tipoDePessoa = 0;
       return "transportadores?faces-redirect-true";
    }
     
    public String registrarGerador(){
        DAO<Gerador> acesso = new DAO(novoGerador);
        acesso.inserir();
        novoGerador = new Gerador();
        tipoDePessoa = 0;
        return "geradores?faces-redirect=true";
    }
    
    public String registrarReciclador(){
        DAO<Reciclador> acesso = new DAO(novoReciclador);
        String email = novoReciclador.getEmail();
        String cnpj = novoReciclador.getCnpj();
        if( acesso.verificarEmail(email) ){            
            return "index?faces-redirect=true";
        }
        if( acesso.verificarCNPJ(cnpj)){           
            return "index?faces-redirect=true"; 
        }      
        acesso.inserir();
        novoReciclador = new Reciclador();
        tipoDePessoa = 0;
        return "recicladores?faces-redirect=true";
    }
    
    public String voltar(){
        tipoDePessoa = 0;
        novoReciclador = new Reciclador();
        novoGerador = new Gerador();
        novoTransportador = new Transportador();
       return "registrar";
    }
   
    /**
     * @return the tipoDePessoa
     */
    public int getTipoDePessoa() {
        return tipoDePessoa;
    }

    /**
     * @param tipoDePessoa the tipoDePessoa to set
     */
    public void setTipoDePessoa(int tipoDePessoa) {
        this.tipoDePessoa = tipoDePessoa;
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

    /**
     * @return the novoReciclador
     */
    public Reciclador getNovoReciclador() {
        return novoReciclador;
    }

    /**
     * @param novoReciclador the novoReciclador to set
     */
    public void setNovoReciclador(Reciclador novoReciclador) {
        this.novoReciclador = novoReciclador;
    }

    /**
     * @return the novoTransportador
     */
    public Transportador getNovoTransportador() {
        return novoTransportador;
    }

    /**
     * @param novoTransportador the novoTransportador to set
     */
    public void setNovoTransportador(Transportador novoTransportador) {
        this.novoTransportador = novoTransportador;
    }

    

 
}
