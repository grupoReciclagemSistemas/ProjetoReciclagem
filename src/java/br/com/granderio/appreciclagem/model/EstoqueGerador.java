/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@Entity
public class EstoqueGerador implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idEstoqueGerador;
    
    @ManyToOne
    private Gerador gerador;
    
    @OneToOne (cascade = CascadeType.ALL)
    private Estoque estoque;
   
    public EstoqueGerador(){
        idEstoqueGerador = -1;
    }

    /**
     * @return the idEstoqueGerador
     */
    public long getIdEstoqueGerador() {
        return idEstoqueGerador;
    }

    /**
     * @param idEstoqueGerador the idEstoqueGerador to set
     */
    public void setIdEstoqueGerador(long idEstoqueGerador) {
        this.idEstoqueGerador = idEstoqueGerador;
    }

    /**
     * @return the gerador
     */
    public Gerador getGerador() {
        return gerador;
    }

    /**
     * @param gerador the gerador to set
     */
    public void setGerador(Gerador gerador) {
        this.gerador = gerador;
    }

    /**
     * @return the estoque
     */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
