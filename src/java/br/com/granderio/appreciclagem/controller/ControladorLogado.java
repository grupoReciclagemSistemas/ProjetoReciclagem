/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOGerador;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.dao.DAOTransportador;
import br.com.granderio.appreciclagem.model.Estoque;
import br.com.granderio.appreciclagem.model.EstoqueGerador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value = "controladorLogado")
public class ControladorLogado implements Serializable {

    private Reciclador recicladorLogado = null;
    private Gerador geradorLogado = null;
    private Transportador transportadorLogado = null;
    
    //Var para editar
    private EstoqueGerador estoque = null;
    private double quantidadeMais = 0.0;
      
    private String email;
    private String senha;

    private int tipoLogin;
    
    //Atributos para novo EstoqueGerador
    private Material materialEscolhido;
    private double quantidadeDoMaterialEscolhido = 0.0;
     
    public ControladorLogado() {
      
    }
    
    public void setarEstoqueGerador(EstoqueGerador obj){
        this.setEstoque(obj);    
    }
    
    public void mudarQuantidadeTotal(){
      
    }
    
    public void cancelar(){
        quantidadeMais = 0.0;
        estoque = null;
    }
    
    public String adicionarNoEstoque(){
        EstoqueGerador estoqueGerador = new EstoqueGerador();
        Estoque estoque = new Estoque();
        estoque.setEstoqueGerador(estoqueGerador);
        estoqueGerador.setEstoque(estoque);
        estoque.setMaterial(materialEscolhido);
        estoque.setQuantidadeMaterial(quantidadeDoMaterialEscolhido);
        estoqueGerador.setGerador(geradorLogado);
        geradorLogado.adicionarEstoqueGerador(estoqueGerador);  
        DAO<Gerador> dao = new DAO(geradorLogado);
        dao.alterar();     
        materialEscolhido = new Material();
        quantidadeDoMaterialEscolhido = 0.0;
        return "minha_conta?faces-redirect=true";
    }
    
    public void removerEstoqueGerador(EstoqueGerador obj){ 
        obj.getEstoque().setMaterial(null);
        obj.getEstoque().setEstoqueGerador(null); 
        obj.setGerador(null);
        obj.setEstoque(null);
        geradorLogado.getEstoques().remove(obj);
        DAO<EstoqueGerador> acesso = new DAO(obj);
        acesso.excluir();   
    }
    
    public String setTipoLogin() {
        tipoLogin = 0;
        email = "";
        senha = "";
        return "login?faces-redirect=true";
    }

    public String logarReciclador() {
        DAOReciclador acesso = new DAOReciclador(recicladorLogado);
        recicladorLogado = acesso.logarReciclador(email, senha);
  
        if(recicladorLogado == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "RECICLADOR NÃO ENCONTRADO!", "Usuário não encontrado no Sistema!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        }  
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "LOGADO COM SUCESSO!", "Bem-vindo ao Sistema, " + recicladorLogado.getRazaoSocial());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        email = "";
        senha = "";
        tipoLogin = 0;
        return "minha_conta?faces-redirect=true";
    }

    public String logarGerador() {
        DAOGerador acesso = new DAOGerador(geradorLogado);
        geradorLogado = acesso.logarGerador(email, senha);
        
        if(geradorLogado == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "GERADOR NÃO ENCONTRADO!", "Usuário não encontrado no Sistema!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        }
        
        email = "";
        senha = "";
        tipoLogin = 0;
        return "minha_conta?faces-redirect=true";
    }

    public String logarTransportador() {
        DAOTransportador acesso = new DAOTransportador(transportadorLogado);
        transportadorLogado = acesso.logarTransportador(email, senha);
        email = "";
         senha ="";
        tipoLogin = 0;
        return "minha_conta?faces-redirect=true";
    }

    public String deslogar() {
        recicladorLogado = null;
        geradorLogado = null;
        transportadorLogado = null;
        tipoLogin = 0;
        return "index?faces-redirect=true";
    }

    public boolean veriRecicladorLogado() {
        if(recicladorLogado == null) {
            return false;
        }
        return true;
    }

    public boolean veriTransLogado() {
        if (transportadorLogado == null) {
            return false;
        }
        return true;
    }

    public boolean veriGeradorLogado() {
        if (geradorLogado == null) {
            return false;
        }
        return true;
    }

    public boolean verificarLogado() {
        if (geradorLogado == null && recicladorLogado == null && transportadorLogado == null) {
            return false;
        }
        return true;
    }

    /**
     * @return the recicladorLogado
     */
    public Reciclador getRecicladorLogado() {
        return recicladorLogado;
    }

    /**
     * @param recicladorLogado the recicladorLogado to set
     */
    public void setRecicladorLogado(Reciclador recicladorLogado) {
        this.recicladorLogado = recicladorLogado;
    }

    /**
     * @return the geradorLogado
     */
    public Gerador getGeradorLogado() {
        return geradorLogado;
    }

    /**
     * @param geradorLogado the geradorLogado to set
     */
    public void setGeradorLogado(Gerador geradorLogado) {
        this.geradorLogado = geradorLogado;
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
     * @return the transportadorLogado
     */
    public Transportador getTransportadorLogado() {
        return transportadorLogado;
    }

    /**
     * @param transportadorLogado the transportadorLogado to set
     */
    public void setTransportadorLogado(Transportador transportadorLogado) {
        this.transportadorLogado = transportadorLogado;
    }

    /**
     * @return the tipoLogin
     */
    public int getTipoLogin() {
        return tipoLogin;
    }

    /**
     * @param tipoLogin the tipoLogin to set
     */
    public void setTipoLogin(int tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

    /**
     * @return the materialEscolhido
     */
    public Material getMaterialEscolhido() {
        return materialEscolhido;
    }

    /**
     * @param materialEscolhido the materialEscolhido to set
     */
    public void setMaterialEscolhido(Material materialEscolhido) {
        this.materialEscolhido = materialEscolhido;
    }

    /**
     * @return the quantidadeDoMaterialEscolhido
     */
    public double getQuantidadeDoMaterialEscolhido() {
        return quantidadeDoMaterialEscolhido;
    }

    /**
     * @param quantidadeDoMaterialEscolhido the quantidadeDoMaterialEscolhido to set
     */
    public void setQuantidadeDoMaterialEscolhido(double quantidadeDoMaterialEscolhido) {
        this.quantidadeDoMaterialEscolhido = quantidadeDoMaterialEscolhido;
    }

    /**
     * @return the estoque
     */
    public EstoqueGerador getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(EstoqueGerador estoque) {
        this.estoque = estoque;
    }

    /**
     * @return the quantidadeMais
     */
    public double getQuantidadeMais() {
        return quantidadeMais;
    }

    /**
     * @param quantidadeMais the quantidadeMais to set
     */
    public void setQuantidadeMais(double quantidadeMais) {
        this.quantidadeMais = quantidadeMais;
    }

}
