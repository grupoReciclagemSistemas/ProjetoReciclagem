/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.ChatAplicacao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controladorChatAplicacao")
@SessionScoped
public class ControladorChatAplicacao extends ControladorPrincipal<ChatAplicacao>{
    
    public ControladorChatAplicacao(){
        super(new ChatAplicacao() );
    }

}
