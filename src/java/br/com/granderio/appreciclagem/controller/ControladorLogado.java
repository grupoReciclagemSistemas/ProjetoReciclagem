/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOAdministrador;
import br.com.granderio.appreciclagem.dao.DAOGerador;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.dao.DAOTransportador;
import br.com.granderio.appreciclagem.model.Administrador;
import br.com.granderio.appreciclagem.model.Estoque;
import br.com.granderio.appreciclagem.model.EstoqueGerador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorLogado")
@SessionScoped
public class ControladorLogado implements Serializable {

    private Reciclador recicladorLogado = null;
    private Gerador geradorLogado = null;
    private Transportador transportadorLogado = null;
    private Administrador adminLogado = null;
    
    //Var para editar
    private EstoqueGerador estoque = null;
    private double quantidadeMais = 0.0;
      
    //Atributos para logar Reciclador, Gerador ou Transportador
    private String email;
    private String senha;
    
     //Atributos para logar Administrador
    private String login;
    private String senhaAdmin;

    private int tipoLogin;
    
    //Atributos para novo EstoqueGerador
    private Material materialEscolhido;
    private double quantidadeDoMaterialEscolhido = 0.0;
     
    public ControladorLogado() {
      tipoLogin = 1;
      login = "";
      senhaAdmin="";
      email = "";
      senha = "";
    }
    
    public void listenerIrNegociar(Negociacao negociacao){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idChat", negociacao.getChat().getIdChat());
    }
    
    public void setarEstoqueGerador(EstoqueGerador obj){
        this.setEstoque(obj);    
    }
    
    public void mudarQuantidadeTotal(){
      
    }
    
    public String tipoQueIraLogar(){
         if(tipoLogin == 1){
            return "ENTRAR COMO RECICLADOR";
        }else if(tipoLogin == 2){
            return "ENTRAR COMO GERADOR";
        }else{
            return "ENTRAR COMO TRANSPORTADOR";
        }
    }
    
    public String logarAdmin(){
        if(login.equals("") || senhaAdmin.equals("")){
            UtilMensagens.mensagemAdvertencia("Os campos estão vazios!");
            return "";
        }
        DAOAdministrador dao = new DAOAdministrador(new Administrador());
        try{
            adminLogado = dao.logarAdmin(login, senhaAdmin);
            setAttribute("adminLogado", adminLogado);
            return "index?faces-redirect=true";
        }catch(Exception e){
            UtilMensagens.mensagemError(e.getMessage());
        }
        UtilMensagens.mensagemError("Login/Senha inválidos!");
        return "";
    }
    
    public void voltarInicio(){
        login = null;
        senha = null;
        adminLogado = null;
        try{
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        }catch(IOException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
    
    public void cancelar(){
        quantidadeMais = 0.0;
        estoque = null;
    }
    public String logar(){
        if(tipoLogin == 1){
            return this.logarReciclador();
        }else if(tipoLogin == 2){
            return this.logarGerador();
        }else{
            return this.logarTransportador();
        }
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
        tipoLogin = 1;
        email = "";
        senha = "";
        return "login?faces-redirect=true";
    }

    public String logarReciclador() {
        DAOReciclador acesso = new DAOReciclador(recicladorLogado);
        try{
            recicladorLogado = acesso.logarReciclador(email, senha);
            setAttribute("recicladorLogado", recicladorLogado);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "Email/Senha inválidos!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            RequestContext.getCurrentInstance().update("messagesLogar");
            return "";
        }
        email = "";
        senha = "";
        return "minha_conta?faces-redirect=true";
    }

    public String logarGerador() {
        DAOGerador acesso = new DAOGerador(geradorLogado);
        try{
            geradorLogado = acesso.logarGerador(email, senha);
            setAttribute("geradorLogado", geradorLogado);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "Email/Senha inválidos!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            RequestContext.getCurrentInstance().update("messagesLogar");
            return "";
        }            
        email = "";
        senha = "";
        tipoLogin = 1;
        return "minha_conta?faces-redirect=true";
    }

    public String logarTransportador() {
        DAOTransportador acesso = new DAOTransportador(transportadorLogado);
        try{
            transportadorLogado = acesso.logarTransportador(email, senha);
             setAttribute("transportadorLogado", transportadorLogado);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Email/Senha inválidos!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            RequestContext.getCurrentInstance().update("messagesLogar");
            return ""; 
        }
        email = "";
        senha = "";
        tipoLogin = 1;
        return "minha_conta?faces-redirect=true";
    }

    public String deslogar() {
        recicladorLogado = null;
        geradorLogado = null;
        transportadorLogado = null;
        tipoLogin = 1;
        setAttribute("transportadorLogado", null);
        setAttribute("recicladorLogado", null);
        setAttribute("geradorLogado", null);
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
    
    public void abrirPopupLogar(){
        if(recicladorLogado == null && geradorLogado == null && transportadorLogado == null)
            RequestContext.getCurrentInstance().execute("PF('modalLogar').show();");
    }
    
    public void fecharPopupLogar(){
        email = null;
        senha = null;
        tipoLogin = 1;
        RequestContext.getCurrentInstance().execute("PF('modalLogar').hide();");
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
        email = "";
        senha = "";      
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

    /**
     * @return the adminLogado
     */
    public Administrador getAdminLogado() {
        return adminLogado;
    }

    /**
     * @param adminLogado the adminLogado to set
     */
    public void setAdminLogado(Administrador adminLogado) {
        this.adminLogado = adminLogado;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senhaAdmin
     */
    public String getSenhaAdmin() {
        return senhaAdmin;
    }

    /**
     * @param senhaAdmin the senhaAdmin to set
     */
    public void setSenhaAdmin(String senhaAdmin) {
        this.senhaAdmin = senhaAdmin;
    }
    
    private ExternalContext currentExternalContext(){
         if (FacesContext.getCurrentInstance() == null){
             System.out.println("O contexto atual é nulo!");
             return null;
         }else{
             return FacesContext.getCurrentInstance().getExternalContext();
         }
    }

    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }
    
    public void encerrarSessao(){   
         currentExternalContext().invalidateSession();
    }

}
