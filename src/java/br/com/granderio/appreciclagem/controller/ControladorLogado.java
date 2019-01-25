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
@Named(value = "controladorLogado")
public class ControladorLogado implements Serializable {

    private Reciclador recicladorLogado = null;
    private Gerador geradorLogado = null;
    private Transportador transportadorLogado = null;

    private String email;
    private String senha;

    private int tipoLogin;

    public ControladorLogado() {

    }

    public String setTipoLogin() {
        tipoLogin = 0;
        email = "";
        senha = "";
        return "login?faces-redirect=true";
    }

    public void logarReciclador() {
        DAO<Reciclador> acesso = new DAO(recicladorLogado);
        recicladorLogado = acesso.logarReciclador(email, senha);
  
        if(recicladorLogado == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "RECICLADOR NÃO ENCONTRADO!", "Usuário não encontrado no Sistema!");
            FacesContext.getCurrentInstance().addMessage("growl", msg);
        }  
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LOGADO COM SUCESSO!", "Bem-vindo ao Sistema, " + recicladorLogado.getRazaoSocial());
            FacesContext.getCurrentInstance().addMessage("growl", msg);
    }

    public String logarGerador() {
        DAO<Gerador> acesso = new DAO(geradorLogado);
        geradorLogado = acesso.logarGerador(email, senha);
         email = "";
         senha ="";
        tipoLogin = 0;
        return "minha_conta?faces-redirect=true";
    }

    public String logarTransportador() {
        DAO<Transportador> acesso = new DAO(transportadorLogado);
        transportadorLogado = acesso.logarTransportador(email, senha);
        email = "";
         senha ="";
        tipoLogin = 0;
        return "minha_conta?faces-redirect=true";
    }

    public String deslogar() {
        recicladorLogado = null;
        geradorLogado = null;
        transportadorLogado = null;
        tipoLogin = 0;
        return "index?faces-redirect=true";
    }

    public boolean veriRecicladorLogado() {
        if(recicladorLogado == null) {
            return false;
        }
        return true;
    }

    public boolean veriTransLogado() {
        if (transportadorLogado == null) {
            return false;
        }
        return true;
    }

    public boolean veriGeradorLogado() {
        if (geradorLogado == null) {
            return false;
        }
        return true;
    }

    public boolean verificarLogado() {
        if (geradorLogado == null && recicladorLogado == null && transportadorLogado == null) {
            return false;
        }
        return true;
    }

    /**
     * @return the recicladorLogado
     */
    public Reciclador getRecicladorLogado() {
        return recicladorLogado;
    }

    /**
     * @param recicladorLogado the recicladorLogado to set
     */
    public void setRecicladorLogado(Reciclador recicladorLogado) {
        this.recicladorLogado = recicladorLogado;
    }

    /**
     * @return the geradorLogado
     */
    public Gerador getGeradorLogado() {
        return geradorLogado;
    }

    /**
     * @param geradorLogado the geradorLogado to set
     */
    public void setGeradorLogado(Gerador geradorLogado) {
        this.geradorLogado = geradorLogado;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the transportadorLogado
     */
    public Transportador getTransportadorLogado() {
        return transportadorLogado;
    }

    /**
     * @param transportadorLogado the transportadorLogado to set
     */
    public void setTransportadorLogado(Transportador transportadorLogado) {
        this.transportadorLogado = transportadorLogado;
    }

    /**
     * @return the tipoLogin
     */
    public int getTipoLogin() {
        return tipoLogin;
    }

    /**
     * @param tipoLogin the tipoLogin to set
     */
    public void setTipoLogin(int tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

}
