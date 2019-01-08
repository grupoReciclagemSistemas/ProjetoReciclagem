/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rafael
 */
@Entity
public class PedidoReciclagem implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idPedidoReciclagem;
    
    private int numero;
    
    @Temporal(TemporalType.DATE)
    private Calendar data;
    
    private double valorTotal;
    
    @OneToMany(mappedBy="pedidoDeReciclagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemPedido> itens;
    
    private String localEntrega;
    private String observações;
    
    @ManyToOne
    private Gerador gerador;
    
    @ManyToOne
    private Reciclador reciclador;
    
    @ManyToOne
    private Transportador transportador;
    
    public PedidoReciclagem(){
        idPedidoReciclagem = -1;
        itens = new ArrayList();
        data = Calendar.getInstance();
        gerador = new Gerador();
        reciclador = new Reciclador();
        transportador = new Transportador();
    }

    /**
     * @return the idPedidoReciclagem
     */
    public long getIdPedidoReciclagem() {
        return idPedidoReciclagem;
    }

    /**
     * @param idPedidoReciclagem the idPedidoReciclagem to set
     */
    public void setIdPedidoReciclagem(long idPedidoReciclagem) {
        this.idPedidoReciclagem = idPedidoReciclagem;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    

    /**
     * @return the localEntrega
     */
    public String getLocalEntrega() {
        return localEntrega;
    }

    /**
     * @param localEntrega the localEntrega to set
     */
    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    /**
     * @return the observações
     */
    public String getObservações() {
        return observações;
    }

    /**
     * @param observações the observações to set
     */
    public void setObservações(String observações) {
        this.observações = observações;
    }

    /**
     * @return the gerador
     */
    public Gerador getGerador() {
        return gerador;
    }

    /**
     * @param gerador the gerador to set
     */
    public void setGerador(Gerador gerador) {
        this.gerador = gerador;
    }

    /**
     * @return the reciclador
     */
    public Reciclador getReciclador() {
        return reciclador;
    }

    /**
     * @param reciclador the reciclador to set
     */
    public void setReciclador(Reciclador reciclador) {
        this.reciclador = reciclador;
    }

    /**
     * @return the transportador
     */
    public Transportador getTransportador() {
        return transportador;
    }

    /**
     * @param transportador the transportador to set
     */
    public void setTransportador(Transportador transportador) {
        this.transportador = transportador;
    }

    /**
     * @return the itens
     */
    public List<ItemPedido> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
    
    
}
