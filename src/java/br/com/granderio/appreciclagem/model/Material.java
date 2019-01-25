/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rafael
 */
@Entity
public class Material implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idMaterial;
    
    private String nome;
    
    @Lob
    private String descricao;
    
    @Temporal(value = TemporalType.DATE)
    private Date dataCadastro;
    
    private long precoMin;
    
    private long precoMax;
  
    public Material(){
        idMaterial = -1;
        dataCadastro = new Date();
    }

    /**
     * @return the idMaterial
     */
    public long getIdMaterial() {
        return idMaterial;
    }

    /**
     * @param idMaterial the idMaterial to set
     */
    public void setIdMaterial(long idMaterial) {
        this.idMaterial = idMaterial;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the precoMin
     */
    public long getPrecoMin() {
        return precoMin;
    }

    /**
     * @param precoMin the precoMin to set
     */
    public void setPrecoMin(long precoMin) {
        this.precoMin = precoMin;
    }

    /**
     * @return the precoMax
     */
    public long getPrecoMax() {
        return precoMax;
    }

    /**
     * @param precoMax the precoMax to set
     */
    public void setPrecoMax(long precoMax) {
        this.precoMax = precoMax;
    }
}
