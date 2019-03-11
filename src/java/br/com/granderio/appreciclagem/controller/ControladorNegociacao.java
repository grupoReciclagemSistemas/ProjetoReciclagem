/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Chat;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PessoaJuridica;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@ManagedBean
@javax.enterprise.context.SessionScoped
@Named(value="controladorNegociacao")
public class ControladorNegociacao extends ControladorPrincipal<Negociacao> {
    
    private Negociacao negociacao;
    private long idNegociacao;
    
    private String novaMensagem;
    
    public ControladorNegociacao(){
        super(new Negociacao() );
    }
    
    public Negociacao updateNegociacao(){
        DAO<Negociacao> acesso = new DAO(new Negociacao());
        return acesso.buscarNegociacao(idNegociacao);
     }
    /**
     * @return the negociacao
     */
    public Negociacao getNegociacao() {
        return negociacao;
    }
    
    public void adicionarNovaMensagem(PessoaJuridica pessoa){
        if(novaMensagem != null){
            if(novaMensagem.equals("")){
                UtilMensagens.mensagemAdvertencia("Não pode enviar uma mensagem em branca!");
                return;
            }
            ChatAplicacao novo = new ChatAplicacao();
            novo.setDataHora(new Date());
            novo.setChat(negociacao.getChat());
            novo.setMensagem(novaMensagem);
            novo.setPessoa(pessoa);
            negociacao.getChat().adicionarChatAplica(novo);
            DAO<Chat> dao = new DAO(negociacao.getChat());
            dao.alterar();
            novaMensagem = "";
        }
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
    
    

}
