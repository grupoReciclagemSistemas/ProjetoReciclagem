/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.ItemPedido;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorItemPedido")
@SessionScoped
public class ControladorItemPedido extends ControladorPrincipal<ItemPedido> {
    
    public ControladorItemPedido() {
        super(new ItemPedido());
    }
    
}
