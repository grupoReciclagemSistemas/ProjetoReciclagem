/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorMaterial")
@SessionScoped
public class ControladorMaterial extends ControladorPrincipal<Material> {
    
    private Material novoMaterial;
    private Material materialSelecionado;
    
    public ControladorMaterial(){
        super ( new Material() );
        novoMaterial = new Material();
        
    }
    
   public void onDoubleClick(SelectEvent event){
       Material mat = (Material) event.getObject();
       materialSelecionado = mat;
   }
   
   public void onRowSelect(SelectEvent event){
       Material mat = (Material) event.getObject();
       materialSelecionado = mat;
   }
   
   public void onRowUnselect(UnselectEvent event){
       materialSelecionado = null;
   }
   
   public void abrirPopUp(){
       RequestContext.getCurrentInstance().execute("PF('modalMaterial').show();");
       RequestContext.getCurrentInstance().update("idMaterialModal");
   }
   
   public void fecharPopUp(){
       materialSelecionado = null;
       RequestContext.getCurrentInstance().execute("PF('modalMaterial').hide();");
   }
   
    public String adicionarMaterial(){
        if(novoMaterial.getPrecoMin() == 0 ){
            UtilMensagens.mensagemError("O preço mínimo não pode ser igual a 0");
            return "";
        }
        if(novoMaterial.getPrecoMax() == 0 ){
            UtilMensagens.mensagemError("O preço máximo não pode ser igual a 0");
            return "";
        }
        if(novoMaterial.getPrecoMin() > novoMaterial.getPrecoMax()){
            UtilMensagens.mensagemError("O preço mínimo é maior que o preço máximo!");
            novoMaterial = new Material();
            return "";
        } 
        if(novoMaterial.getPrecoMin() == novoMaterial.getPrecoMax()){
            UtilMensagens.mensagemError("O preço mínimo é igual ao preço máximo!");
            novoMaterial = new Material();
            return "";
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

    /**
     * @return the materialSelecionado
     */
    public Material getMaterialSelecionado() {
        return materialSelecionado;
    }

    /**
     * @param materialSelecionado the materialSelecionado to set
     */
    public void setMaterialSelecionado(Material materialSelecionado) {
        this.materialSelecionado = materialSelecionado;
    }


}
