/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.Negociacao;
import javax.annotation.ManagedBean;
import javax.inject.Named;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean
@javax.enterprise.context.SessionScoped
@Named(value="controladorNegociacao")
public class ControladorNegociacao extends ControladorPrincipal<Negociacao> {
    
    public ControladorNegociacao(){
        super(new Negociacao() );
    }

}
