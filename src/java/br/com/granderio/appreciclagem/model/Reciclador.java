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


@Entity
public class Reciclador extends PessoaJuridica {

    private boolean transportadoraPropria;
    
    @OneToMany(mappedBy="reciclador", fetch = FetchType.LAZY)
    private List<PedidoReciclagem> pedidosDeReciclagens;
          
    public Reciclador(){
        super();
        transportadoraPropria = false;
        pedidosDeReciclagens = new ArrayList();
    }

    public boolean isPossuiTransportadora() {
        return transportadoraPropria;
    }

    
    public void setPossuiTransportadora(boolean transportadoraPropria) {
        this.transportadoraPropria= transportadoraPropria;
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
