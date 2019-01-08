/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import javax.persistence.Entity;


@Entity
public class Reciclador extends PessoaJuridica {
     
    private boolean transportadoraPropria;
          
    public Reciclador(){
        super();
        transportadoraPropria = false;
    }

    public boolean isPossuiTransportadora() {
        return transportadoraPropria;
    }

    
    public void setPossuiTransportadora(boolean transportadoraPropria) {
        this.transportadoraPropria= transportadoraPropria;
    }
     
}
