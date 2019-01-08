/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorPedidoReciclagem")
public class ControladorPedidoReciclagem extends ControladorPrincipal<PedidoReciclagem> {
    
    public ControladorPedidoReciclagem(){
        super (new PedidoReciclagem());
    }
    
}
