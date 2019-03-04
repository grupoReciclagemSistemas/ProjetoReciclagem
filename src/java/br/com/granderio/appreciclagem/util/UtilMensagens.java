/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rafael
 */
public class UtilMensagens {
    
    public static void mensagemInfo(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
        FacesContext.getCurrentInstance().addMessage("", msg);
    }
    
    public static void mensagemAdvertencia(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "");
        FacesContext.getCurrentInstance().addMessage("", msg);
    }
    
    public static void mensagemError(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        FacesContext.getCurrentInstance().addMessage("", msg);
    }
    
    public static void mensagemFatal(String mensagem){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, "");
        FacesContext.getCurrentInstance().addMessage("", msg);
    }
    
}
