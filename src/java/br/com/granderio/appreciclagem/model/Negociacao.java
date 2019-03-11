/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@Entity
public class Negociacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNegociacao;
    
    @ManyToOne
    private Gerador gerador;
    
    @ManyToOne
    private Reciclador reciclador;
    
    @OneToOne
    private Chat chat;
    
    @OneToOne
    private PedidoReciclagem pedido;
    
    public Negociacao(){
        
    }
    
    public Negociacao(PedidoReciclagem pedido, Reciclador reciclador, Gerador gerador){
        this.gerador = gerador;
        this.pedido = pedido;
        this.reciclador = reciclador;
        chat = new Chat();
    }

    /**
     * @return the idNegociacao
     */
    public long getIdNegociacao() {
        return idNegociacao;
    }

    /**
     * @param idNegociacao the idNegociacao to set
     */
    public void setIdNegociacao(long idNegociacao) {
        this.idNegociacao = idNegociacao;
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
     * @return the chat
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @param chat the chat to set
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    /**
     * @return the pedido
     */
    public PedidoReciclagem getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(PedidoReciclagem pedido) {
        this.pedido = pedido;
    }

}
