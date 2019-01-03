/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
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
@Named(value="controladorLogado")
public class ControladorLogado implements Serializable {
    
    private Reciclador recicladorLogado;
    private Gerador geradorLogado;
    private Transportador transportadorLogado;
    
    private String email;
    private String senha;
   
   public ControladorLogado(){
       recicladorLogado = null;
       geradorLogado = null;
       transportadorLogado = null;
   }
   
   public boolean veriRecicladorLogado(){
       if(recicladorLogado == null){
           return false;
       }
       return true;
   }
   
    public boolean veriGeradorLogado(){
       if(geradorLogado == null){
           return false;
       }
       return true;
   }
    
    public boolean verificarLogado(){
        if(geradorLogado == null && recicladorLogado == null){
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
   
   
    
    
}
