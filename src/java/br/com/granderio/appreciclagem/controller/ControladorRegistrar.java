/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.granderio.appreciclagem.dao.DAOGerador;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.dao.DAOTransportador;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.Consulta;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorRegistrar")
@SessionScoped
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
        tipoDePessoa = 0;
    }
    
    public void registrarTipoPessoa(int valor){
        this.setTipoDePessoa(valor);    
    }
    
    public void listenerRecicladorCEP(){  
        try {
            String cep = novoReciclador.getEndereco().getCep();
            Endereco retornoDoWebService = Consulta.consultarCEP(cep);
            novoReciclador.setEndereco(retornoDoWebService);
        } catch (SQLException_Exception ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SigepClienteException ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listenerGeradorCEP(){  
        try {
            String cep = novoGerador.getEndereco().getCep();
            Endereco retornoDoWebService = Consulta.consultarCEP(cep);
            novoGerador.setEndereco(retornoDoWebService);
        } catch (SQLException_Exception ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SigepClienteException ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listenerTransportadorCEP(){  
        try {
            String cep = novoTransportador.getEndereco().getCep();
            Endereco retornoDoWebService = Consulta.consultarCEP(cep);
            novoTransportador.setEndereco(retornoDoWebService);
        } catch (SQLException_Exception ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SigepClienteException ex) {
            Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

     public String registrarTransportador(){
       DAOTransportador acesso = new DAOTransportador(novoTransportador);
       novoTransportador.getEndereco().setPessoa(novoTransportador);
       novoTransportador.setPedidosDeReciclagens(null);
       String email = novoTransportador.getEmail();
       String cnpj = novoTransportador.getCnpj();
       if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            UtilMensagens.mensagemError("Já existe o Transportador no Sistema!");   
            return "";
        }  
       acesso.inserir();
       novoTransportador = new Transportador();
       tipoDePessoa = 0;
        UtilMensagens.mensagemInfo("Transportador cadastrado com sucesso!");
        return "index";
    }
     
    public String registrarGerador(){
        DAOGerador acesso = new DAOGerador(novoGerador);
        novoGerador.getEndereco().setPessoa(novoGerador);
        novoGerador.setEstoques(null);
        String email = novoGerador.getEmail();
        String cnpj = novoGerador.getCnpj();
        if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            UtilMensagens.mensagemError("Já existe o Gerador no Sistema!");
            return "";
        }
        acesso.inserir();
        novoGerador = new Gerador();
        tipoDePessoa = 0;
        UtilMensagens.mensagemInfo("Gerador cadastrado com sucesso!");
        return "index";
    }
    
    public String registrarReciclador(){
        DAOReciclador acesso = new DAOReciclador(novoReciclador);
        novoReciclador.getEndereco().setPessoa(novoReciclador);
        novoReciclador.setPedidosDeReciclagens(null);
        String email = novoReciclador.getEmail();
        String cnpj = novoReciclador.getCnpj();
        if( acesso.verificarEmail(email) || acesso.verificarCNPJ(cnpj) ){
            UtilMensagens.mensagemError("Já existe o Reciclador no Sistema!");
            return "";
        }  
        acesso.inserir();
        novoReciclador = new Reciclador();
        tipoDePessoa = 0;
        UtilMensagens.mensagemInfo("Reciclador cadastrado com sucesso!");
        return "index";
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
