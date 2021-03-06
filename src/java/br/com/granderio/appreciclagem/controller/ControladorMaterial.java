/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Estoque;
import br.com.granderio.appreciclagem.model.EstoqueGerador;
import br.com.granderio.appreciclagem.model.Material;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Named(value="controladorMaterial")
public class ControladorMaterial extends ControladorPrincipal<Material> {
    
    private Material novoMaterial;
    
    public ControladorMaterial(){
        super ( new Material() );
        novoMaterial = new Material();
        
    }
   
    public String adicionarMaterial(){
        if(novoMaterial.getPrecoMin() > novoMaterial.getPrecoMax()){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "O preço mínimo é maior que o máximo!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            novoMaterial = new Material();
            return "index";
        } 
        if(novoMaterial.getPrecoMin() == novoMaterial.getPrecoMax()){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O preço mínimo é igual ao máximo!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            novoMaterial = new Material();
            return "index";
        }
        DAO<Material> acesso = new DAO(novoMaterial);
        acesso.inserir();
        novoMaterial = new Material();
        return "index?faces-redirect=true";
    }
    
    
    public List<Material> lista(){
        DAO<Material> lista = new DAO(new Material());
        return lista.obterLista();
    }
    
    public String retornaDataCadastro(Date data){
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         return sdf.format(data);
    }

    /**
     * @return the novoMaterial
     */
    public Material getNovoMaterial() {
        return novoMaterial;
    }

    /**
     * @param novoMaterial the novoMaterial to set
     */
    public void setNovoMaterial(Material novoMaterial) {
        this.novoMaterial = novoMaterial;
    }


}
