/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@Entity
public class Chat implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long idChat;
   
   @OneToMany(mappedBy="chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private List<ChatAplicacao> chatAplicacao;
   
   //Chat ficará vinculado a negociação entre gerador e reciclador
   @OneToOne
   private Negociacao negociacao;
   
   public Chat(){
       chatAplicacao = new ArrayList<>();
   }
   
    public void adicionarChatAplica(ChatAplicacao e){
        chatAplicacao.add(e);
    }
    /**
     * @return the idChat
     */
    public long getIdChat() {
        return idChat;
    }

    /**
     * @param idChat the idChat to set
     */
    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }

    /**
     * @return the chatAplicacao
     */
    public List<ChatAplicacao> getChatAplicacao() {
        return chatAplicacao;
    }

    /**
     * @param chatAplicacao the chatAplicacao to set
     */
    public void setChatAplicacao(List<ChatAplicacao> chatAplicacao) {
        this.chatAplicacao = chatAplicacao;
    }

    /**
     * @return the negociacao
     */
    public Negociacao getNegociacao() {
        return negociacao;
    }

    /**
     * @param negociacao the negociacao to set
     */
    public void setNegociacao(Negociacao negociacao) {
        this.negociacao = negociacao;
    }
   
}
