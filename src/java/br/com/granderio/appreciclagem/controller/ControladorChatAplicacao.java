/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.ChatAplicacao;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean
@SessionScoped
@Named(value="controladorChatAplicacao")
public class ControladorChatAplicacao extends ControladorPrincipal<ChatAplicacao>{
    
    public ControladorChatAplicacao(){
        super(new ChatAplicacao() );
    }

}
