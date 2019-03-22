/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.Administrador;
import br.com.granderio.appreciclagem.model.Chat;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean
@SessionScoped
@Named(value="controladorAdmin")
public class ControladorAdmin extends ControladorPrincipal<Administrador> {
    
    public ControladorAdmin(){
        super(new Administrador());
    }

}
