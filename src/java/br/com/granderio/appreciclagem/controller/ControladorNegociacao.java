/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean(name="controladorNegociacao")
@SessionScoped
public class ControladorNegociacao extends ControladorPrincipal<Negociacao> {
    
    private Negociacao negociacao;
    private long idNegociacao;
    
    private String novaMensagem;
    
    private ChatAplicacao chatAplicacaoSelecionado;
    
    public ControladorNegociacao(){
        super(new Negociacao() );
        novaMensagem = null;
    }
    
    public void updateNegociacao(){
        try{
        DAO<ChatAplicacao> acesso = new DAO(new ChatAplicacao());
        negociacao.getChat().setChatAplicacao(acesso.buscarMensagensChat(negociacao.getChat()));
        }catch(Exception e){
            UtilMensagens.mensagemError(e.getMessage());
        }
     }
    
    public String quemEnviouMsg(ChatAplicacao chatAplica){
        if(chatAplica.getGerador() != null){
            return chatAplica.getGerador().getRazaoSocial();
        }
        return chatAplica.getReciclador().getRazaoSocial();
    }
    /**
     * @return the negociacao
     */
    public Negociacao getNegociacao() {
        return negociacao;
    }
    
    public void adicionarNovaMensagemGerador(Gerador pessoa){
        if(novaMensagem == null){
                UtilMensagens.mensagemError("Mensagem nula");
                novaMensagem = null;
                return;
            }
        if(novaMensagem != null && novaMensagem.equals("")){
            UtilMensagens.mensagemError("Mensagem nula");
            novaMensagem = null;
                return;
        }
            ChatAplicacao novo = new ChatAplicacao();
            novo.setDataHora(new Date());
            novo.setChat(negociacao.getChat());
            novo.setMensagem(novaMensagem);
            novo.setGerador(pessoa);
            negociacao.getChat().adicionarChatAplica(novo);
            DAO<ChatAplicacao> dao = new DAO(novo);
            dao.inserir();
            novaMensagem = null;
        }
    
     public void adicionarNovaMensagemReciclador(Reciclador pessoa){
        if(novaMensagem == null){
                UtilMensagens.mensagemError("Mensagem nula");
                novaMensagem = null;
                return;
            }
        if(novaMensagem != null && novaMensagem.equals("")){
            UtilMensagens.mensagemError("Mensagem nula");
            novaMensagem = null;
                return;
        }
            ChatAplicacao novo = new ChatAplicacao();
            novo.setDataHora(new Date());
            novo.setChat(negociacao.getChat());
            novo.setMensagem(novaMensagem);
            novo.setReciclador(pessoa);
            negociacao.getChat().adicionarChatAplica(novo);
            DAO<ChatAplicacao> dao = new DAO(novo);
            dao.inserir();
            novaMensagem = null;
        }
    
    /**
     * @param negociacao the negociacao to set
     */
    public void setNegociacao(Negociacao negociacao) {
        this.negociacao = negociacao;
    }
    
    public void listenerIrNegociar(Negociacao negociacao){
        this.setNegociacao(negociacao);
        this.setIdNegociacao(negociacao.getIdNegociacao());     
    }

    /**
     * @return the novaMensagem
     */
    public String getNovaMensagem() {
        return novaMensagem;
    }

    /**
     * @param novaMensagem the novaMensagem to set
     */
    public void setNovaMensagem(String novaMensagem) {
        this.novaMensagem = novaMensagem;
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
     * @return the chatAplicacaoSelecionado
     */
    public ChatAplicacao getChatAplicacaoSelecionado() {
        return chatAplicacaoSelecionado;
    }

    /**
     * @param chatAplicacaoSelecionado the chatAplicacaoSelecionado to set
     */
    public void setChatAplicacaoSelecionado(ChatAplicacao chatAplicacaoSelecionado) {
        this.chatAplicacaoSelecionado = chatAplicacaoSelecionado;
    }
    
    public void excluirNegociacao(Negociacao neg){
        DAO<Negociacao> dao = new DAO<Negociacao>(neg);
        dao.excluir();
    }
    
    

}
