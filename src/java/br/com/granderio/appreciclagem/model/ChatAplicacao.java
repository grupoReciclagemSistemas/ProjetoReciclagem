/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@Entity
public class ChatAplicacao implements Serializable {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idChatAplicacao;
  
  
  //Mensagem que irá ser enviada
  private String mensagem;
  
  //Associação bidirecional
  @ManyToOne
  private Chat chat;
  
  //Quem está enviando a mensagem (Reciclador ou Gerador)
  private Gerador gerador;
  private Reciclador reciclador;
 
  //Data e Hora que a mensagem irá ser enviada
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHora;

    /**
     * @return the idChatAplicacao
     */
    public long getIdChatAplicacao() {
        return idChatAplicacao;
    }

    /**
     * @param idChatAplicacao the idChatAplicacao to set
     */
    public void setIdChatAplicacao(long idChatAplicacao) {
        this.idChatAplicacao = idChatAplicacao;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
     * @return the dataHora
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * @param dataHora the dataHora to set
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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
          
}
