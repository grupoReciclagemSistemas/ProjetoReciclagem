/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorEndereco")
public class ControladorEndereco extends ControladorPrincipal<Endereco> {
    
    private List<String> uf = new ArrayList();
    
    public ControladorEndereco() {
        super(new Endereco() );
        uf.add("RJ");
        uf.add("SP");
        uf.add("MG");
        uf.add("RS");
    }

    /**
     * @return the uf
     */
    public List<String> getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(List<String> uf) {
        this.uf = uf;
    }
    
}
