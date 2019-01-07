/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Reciclador;
import java.io.Serializable;
import javax.annotation.ManagedBean;
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
    // 3 transportador
    private int tipoDePessoa;
    
    private Gerador novoGerador;
    
    
    private Reciclador novoReciclador;
    private boolean transportadoraReciclador = false;
         
    public ControladorRegistrar(){
        novoGerador = new Gerador();
        novoReciclador = new Reciclador();
    }
    
    public void teste(int x ){
        this.setTipoDePessoa(x);    
    }
    
    public String transporte(boolean valor){
        transportadoraReciclador = true;
        return "registrar";
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
        acesso.inserir();
        novoReciclador = new Reciclador();
        tipoDePessoa = 0;
        return "recicladores?faces-redirect=true";
    }
    
    public String voltar(){
        tipoDePessoa = 0;
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
     * @return the transportadoraReciclador
     */
    public boolean isTransportadoraReciclador() {
        return transportadoraReciclador;
    }

    /**
     * @param transportadoraReciclador the transportadoraReciclador to set
     */
    public void setTransportadoraReciclador(boolean transportadoraReciclador) {
        this.transportadoraReciclador = transportadoraReciclador;
    }

     
}
