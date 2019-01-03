/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Rafael
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PessoaJuridica implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idPessoaJuridica;
    
    @Column(unique=true, nullable=false)
    private String email;
    
    @Column(nullable = false)
    private String razaoSocial;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(unique=true, nullable=false)
    private String cnpj;
    
    @Column(nullable = false)
    private String telefone;
    
    private boolean comprador;
    
    public PessoaJuridica(){
        
    }

    /**
     * @return the idPessoaJuridica
     */
    public long getIdPessoaJuridica() {
        return idPessoaJuridica;
    }

    /**
     * @param idPessoaJuridica the idPessoaJuridica to set
     */
    public void setIdPessoaJuridica(long idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the comprador
     */
    public boolean isComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(boolean comprador) {
        this.comprador = comprador;
    }
    
    
    
}
