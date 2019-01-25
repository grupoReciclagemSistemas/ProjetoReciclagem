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
    
    @OneToMany(mappedBy="gerador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Estoque> estoques;
    
    public Gerador(){
        super();
        estoques = new ArrayList();
    }
    
    public void adicionarEstoque(Estoque estoque){
        DAO<Estoque> acesso = new DAO(estoque);
        int quantidadeMax = acesso.quantidadeDeMateriais();
        int quantidade = this.estoques.size();
        if(quantidade == quantidadeMax){
            System.out.println("Quantidade Máxima!!!");
            return;
        }
        estoques.add(estoque);
    }
    
    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

}
