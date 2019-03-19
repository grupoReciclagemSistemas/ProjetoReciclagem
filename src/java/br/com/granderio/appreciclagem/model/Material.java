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
 */ // SELECT a FROM Material a WHERE 
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
    
    private double precoMin;
    
    private double precoMax;
    
    @OneToMany(mappedBy="material", fetch = FetchType.LAZY)
    private List<MaterialLegislacao> listaMatLegi;
  
    public Material(){
        idMaterial = -1;
        dataCadastro = new Date();
        listaMatLegi = new ArrayList();
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
    public double getPrecoMin() {
        return precoMin;
    }

    /**
     * @param precoMin the precoMin to set
     */
    public void setPrecoMin(double precoMin) {
        this.precoMin = precoMin;
    }

    /**
     * @return the precoMax
     */
    public double getPrecoMax() {
        return precoMax;
    }

    /**
     * @param precoMax the precoMax to set
     */
    public void setPrecoMax(double precoMax) {
        this.precoMax = precoMax;
    }

    /**
     * @return the listaMatLegi
     */
    public List<MaterialLegislacao> getListaMatLegi() {
        return listaMatLegi;
    }

    /**
     * @param listaMatLegi the listaMatLegi to set
     */
    public void setListaMatLegi(List<MaterialLegislacao> listaMatLegi) {
        this.listaMatLegi = listaMatLegi;
    }
 
}
