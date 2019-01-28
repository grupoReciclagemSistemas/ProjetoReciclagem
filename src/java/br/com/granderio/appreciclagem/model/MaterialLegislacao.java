/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@Entity
public class MaterialLegislacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMaterialLegislacao;
    
    @ManyToOne
    private Material material;
    
    @ManyToOne
    private Legislacao legislacao;
    
    public MaterialLegislacao(){
        
    }

    /**
     * @return the idMaterialLegislacao
     */
    public long getIdMaterialLegislacao() {
        return idMaterialLegislacao;
    }

    /**
     * @param idMaterialLegislacao the idMaterialLegislacao to set
     */
    public void setIdMaterialLegislacao(long idMaterialLegislacao) {
        this.idMaterialLegislacao = idMaterialLegislacao;
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the legislacao
     */
    public Legislacao getLegislacao() {
        return legislacao;
    }

    /**
     * @param legislacao the legislacao to set
     */
    public void setLegislacao(Legislacao legislacao) {
        this.legislacao = legislacao;
    }

}
