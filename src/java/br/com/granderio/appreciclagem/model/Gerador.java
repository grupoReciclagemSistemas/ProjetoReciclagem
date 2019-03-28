/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import br.com.granderio.appreciclagem.dao.DAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Gerador extends PessoaJuridica {
    
    @OneToMany(mappedBy="gerador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EstoqueGerador> estoques;
    
    //Gerador tem uma lista de negociações
    @OneToMany(mappedBy="gerador", fetch = FetchType.LAZY)
    private List<Negociacao> negociacoes;
    
    public Gerador(){
        super();
        estoques = new ArrayList();
        negociacoes = new ArrayList();
    }
    
    public List<Material> listaDeMaterialQuePodeAdicionar(){
        DAO<Material> dao = new DAO(new Material());
        if(estoques != null && estoques.size() > 0){
            List<Material> listaDeMaterialGerador = new ArrayList();
            int count = 0;
            for(EstoqueGerador estoqueGerador : estoques){
                Material obj = (Material) estoques.get(count).getEstoque().getMaterial();     
                listaDeMaterialGerador.add(obj);
                count++;
            }
            List<Material> listaDeMaterialConsolidada = (List<Material>) dao.obterLista();
            listaDeMaterialConsolidada.removeAll(listaDeMaterialGerador);
            return listaDeMaterialConsolidada;
        }
        return (List<Material>) dao.obterLista();
    }
    
    public void adicionarEstoqueGerador(EstoqueGerador obj){
        estoques.add(obj);
    }
    
    public void removerEstoqueGerador(EstoqueGerador obj){
        estoques.remove(obj);
    }
    
    public void adicionarNegociacao(Negociacao neg){
        negociacoes.add(neg);
    }

    /**
     * @return the estoques
     */
    public List<EstoqueGerador> getEstoques() {
        return estoques;
    }

    /**
     * @param estoques the estoques to set
     */
    public void setEstoques(List<EstoqueGerador> estoques) {
        this.estoques = estoques;
    }

    /**
     * @return the negociacoes
     */
    public List<Negociacao> getNegociacoes() {
        return negociacoes;
    }

    /**
     * @param negociacoes the negociacoes to set
     */
    public void setNegociacoes(List<Negociacao> negociacoes) {
        this.negociacoes = negociacoes;
    }

}
