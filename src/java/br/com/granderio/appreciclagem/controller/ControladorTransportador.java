/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Transportador;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorTransportador")
@SessionScoped
public class ControladorTransportador extends ControladorPrincipal<Transportador> {
    
    private Transportador transportadorSelecionado;

    public ControladorTransportador(){
        super(new Transportador());
    }
    
    public List<Transportador> lista(){
        DAO<Transportador> lista = new DAO(new Transportador());
        return lista.obterLista();
    }
    
     public void onDoubleClick(SelectEvent event){
       Transportador obj = (Transportador) event.getObject();
       transportadorSelecionado = obj;
   }
   
   public void onRowSelect(SelectEvent event){
       Transportador obj = (Transportador) event.getObject();
       transportadorSelecionado = obj;
   }
   
   public void onRowUnselect(UnselectEvent event){
       transportadorSelecionado = null;
   }

    /**
     * @return the transportadorSelecionado
     */
    public Transportador getTransportadorSelecionado() {
        return transportadorSelecionado;
    }

    /**
     * @param transportadorSelecionado the transportadorSelecionado to set
     */
    public void setTransportadorSelecionado(Transportador transportadorSelecionado) {
        this.transportadorSelecionado = transportadorSelecionado;
    }

}
