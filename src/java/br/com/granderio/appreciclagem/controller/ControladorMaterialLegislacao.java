/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.MaterialLegislacao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controladorMaterialLegi")
@SessionScoped
public class ControladorMaterialLegislacao extends ControladorPrincipal<MaterialLegislacao> {
    
    public ControladorMaterialLegislacao(){
        super(new MaterialLegislacao());
    }

}
