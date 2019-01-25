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
import br.com.granderio.appreciclagem.util.UtilSegundo;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    
    public String trans(){
       boolean possui = novoReciclador.isPossuiTransportadora();
       if(possui){
           return "Quando você for comprar algum produto no Sistema, sua empresa que efetuará o Transporte!";
       }
       return "Quando você for comprar algum produto no Sistema, poderá alugar um Transportador!";
    }
     
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
       novoTransportador.getEndereco().setPessoa(novoTransportador);
       novoTransportador.setPedidosDeReciclagens(null);
       String email = novoTransportador.getEmail();
       String cnpj = novoTransportador.getCnpj();
       if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Transportador já existe no Sistema!", "Este Reciclador já existe no Sistema!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            try{
                Thread.sleep(5000);
            }catch(InterruptedException ex){
                System.out.println("Error na Thread:" + ex.getMessage());
            }
            return "index";
        }  
       acesso.inserir();
       novoTransportador = new Transportador();
       tipoDePessoa = 0;
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transportador cadastrado com Sucesso!", "");
       FacesContext.getCurrentInstance().addMessage(null, msg);
       try{
                Thread.sleep(5000);
            }catch(InterruptedException ex){
                System.out.println("Error na Thread:" + ex.getMessage());
            }
       return "login";
    }
     

    public String registrarGerador(){
        DAO<Gerador> acesso = new DAO(novoGerador);
        novoGerador.getEndereco().setPessoa(novoGerador);
        novoGerador.setEstoques(null);
        String email = novoGerador.getEmail();
        String cnpj = novoGerador.getCnpj();
        if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Gerador já existe no Sistema!", "Este Reciclador já existe no Sistema!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "index";
        }
        acesso.inserir();
        novoGerador = new Gerador();
        tipoDePessoa = 0;
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerador cadastrado com Sucesso!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "login";
    }
    
    public void registrarReciclador(){
        DAO<Reciclador> acesso = new DAO(novoReciclador);
        novoReciclador.getEndereco().setPessoa(novoReciclador);
        novoReciclador.setPedidosDeReciclagens(null);
        String email = novoReciclador.getEmail();
        String cnpj = novoReciclador.getCnpj();
        if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Reciclador já existe no Sistema!", "Este Reciclador já existe no Sistema!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }  
        acesso.inserir();
        novoReciclador = new Reciclador();
        tipoDePessoa = 0;
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reciclador cadastrado com Sucesso!", "Bem-vindo ao Sistema, já pode efetuar Login!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      
    public void voltar(){
        tipoDePessoa = 0;
        novoReciclador = new Reciclador();
        novoGerador = new Gerador();
        novoTransportador = new Transportador();   
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
