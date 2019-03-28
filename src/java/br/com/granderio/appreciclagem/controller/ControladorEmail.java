/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.util.UtilEmail;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.mail.EmailException;


/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controladorEmail")
@ViewScoped
public class ControladorEmail {
    
    private String remetente;
    private String mensagem;
    private String titulo;
    
    public void enviarEmail(){
        try {
            UtilEmail.enviarEmailSimples(titulo, remetente, mensagem);
            UtilMensagens.mensagemInfo("Email enviado com sucesso, aguarde uma resposta!");
        } catch (EmailException ex) {
            Logger.getLogger(ControladorEmail.class.getName()).log(Level.SEVERE, null, ex);
            UtilMensagens.mensagemInfo("Erro ao enviar o Email!");
        }
    }
    
    public ControladorEmail(){
        
    }

    /**
     * @return the remetente
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     * @param remetente the remetente to set
     */
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
