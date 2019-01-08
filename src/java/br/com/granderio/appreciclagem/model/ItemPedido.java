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

/**
 *
 * @author Rafael
 */
@Entity
public class ItemPedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idItemPedido;
    private int seq_item;
    private Double preco;
    private Double quantidade;
    
    @ManyToOne
    private PedidoReciclagem pedidoDeReciclagem;
    
    @ManyToOne
    private Material material;
    
    public ItemPedido(){
        idItemPedido = -1;
        material = new Material();
    }

    /**
     * @return the idItemPedido
     */
    public long getIdItemPedido() {
        return idItemPedido;
    }

    /**
     * @param idItemPedido the idItemPedido to set
     */
    public void setIdItemPedido(long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    /**
     * @return the seq_item
     */
    public int getSeq_item() {
        return seq_item;
    }

    /**
     * @param seq_item the seq_item to set
     */
    public void setSeq_item(int seq_item) {
        this.seq_item = seq_item;
    }

    /**
     * @return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * @return the quantidade
     */
    public Double getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the pedidoDeReciclagem
     */
    public PedidoReciclagem getPedidoDeReciclagem() {
        return pedidoDeReciclagem;
    }

    /**
     * @param pedidoDeReciclagem the pedidoDeReciclagem to set
     */
    public void setPedidoDeReciclagem(PedidoReciclagem pedidoDeReciclagem) {
        this.pedidoDeReciclagem = pedidoDeReciclagem;
    }
    
    
}
