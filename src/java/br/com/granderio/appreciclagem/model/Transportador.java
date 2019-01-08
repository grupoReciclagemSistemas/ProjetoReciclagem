/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Rafael
 */
@Entity
public class Transportador extends PessoaJuridica {
    
    @OneToMany(mappedBy="transportador", fetch = FetchType.LAZY)
    private List<PedidoReciclagem> pedidosDeReciclagens;
    
    public Transportador(){
        super();
        pedidosDeReciclagens = new ArrayList();
    }

    /**
     * @return the pedidosDeReciclagens
     */
    public List<PedidoReciclagem> getPedidosDeReciclagens() {
        return pedidosDeReciclagens;
    }

    /**
     * @param pedidosDeReciclagens the pedidosDeReciclagens to set
     */
    public void setPedidosDeReciclagens(List<PedidoReciclagem> pedidosDeReciclagens) {
        this.pedidosDeReciclagens = pedidosDeReciclagens;
    }
    
    
    
}
