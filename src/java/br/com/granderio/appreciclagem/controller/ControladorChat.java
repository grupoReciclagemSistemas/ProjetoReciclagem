/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.Chat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controladorChat")
@SessionScoped
public class ControladorChat extends ControladorPrincipal<Chat> {
    
    public ControladorChat(){
        super(new Chat());
    }

}
