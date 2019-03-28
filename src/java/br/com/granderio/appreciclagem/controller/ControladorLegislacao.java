/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Legislacao;
import br.com.granderio.appreciclagem.model.MaterialLegislacao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controladorLegislacao")
@SessionScoped
public class ControladorLegislacao extends ControladorPrincipal<Legislacao> {
    
    private Legislacao modelo2;
    private Legislacao novaLesgilacao;
    
    public ControladorLegislacao(){
        super(new Legislacao());
        novaLesgilacao = new Legislacao();
    }
    
    public void setar(Legislacao a){
        setModelo2(a);
    }
    
    public String adicionarLegislacao(){
        DAO<Legislacao> dao = new DAO(novaLesgilacao);
        dao.inserir();
        novaLesgilacao = new Legislacao();
        return "index?faces-redirect=true";
    }
    
    public List<Legislacao> lista(){
        DAO<Legislacao> dao = new DAO(new Legislacao());
        return dao.obterLista();
    }
    /**
     * @return the novaLesgilacao
     */
    public Legislacao getNovaLesgilacao() {
        return novaLesgilacao;
    }

    /**
     * @param novaLesgilacao the novaLesgilacao to set
     */
    public void setNovaLesgilacao(Legislacao novaLesgilacao) {
        this.novaLesgilacao = novaLesgilacao;
    }

    /**
     * @return the modelo2
     */
    public Legislacao getModelo2() {
        return modelo2;
    }

    /**
     * @param modelo2 the modelo2 to set
     */
    public void setModelo2(Legislacao modelo2) {
        this.modelo2 = modelo2;
    }

}
