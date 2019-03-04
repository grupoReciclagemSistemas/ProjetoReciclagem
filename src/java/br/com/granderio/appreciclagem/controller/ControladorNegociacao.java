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
    
    private String novaMensagem;
    
    public ControladorNegociacao(){
        super(new Negociacao() );
    }

    /**
     * @return the negociacao
     */
    public Negociacao getNegociacao() {
        return negociacao;
    }
    
    public void adicionarNovaMensagem(PessoaJuridica pessoa){
        if(novaMensagem != null){
            ChatAplicacao novo = new ChatAplicacao();
            novo.setDataHora(new Date());
            novo.setChat(negociacao.getChat());
            novo.setMensagem(novaMensagem);
            novo.setPessoa(pessoa);
            negociacao.getChat().adicionarChatAplica(novo);
            DAO<Chat> dao = new DAO(negociacao.getChat());
            dao.alterar();
            novaMensagem = null;
        }
    }

    /**
     * @param negociacao the negociacao to set
     */
    public void setNegociacao(Negociacao negociacao) {
        this.negociacao = negociacao;
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
    
    

}
