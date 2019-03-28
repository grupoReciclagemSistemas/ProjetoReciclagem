/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOGerador;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.datamodel.ChatAplicacaoLazyDataModel;
import br.com.granderio.appreciclagem.model.Chat;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 * 
 * Classe para Controlar o LAZY MODEL DO CHAT
 */
@ManagedBean(name="controleChatLazyModel")
@SessionScoped
public class ControleChatLazy {
    
    private Negociacao neg;
    
    private String novaMensagem;
    
    private final int first = 0;
    private final int pageSize = 15;
    
    private LazyDataModel<ChatAplicacao> model;
    
    private Map<String, Object> parameters;
    
    public ControleChatLazy(){
        parameters = new HashMap<>();
        model = new ChatAplicacaoLazyDataModel();
    }
    
//    @PostConstruct
//    public void init(){
//        long idChat = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat");
//        parameters.put("idChat", idChat);
//        this.model.load(first, pageSize, "", SortOrder.UNSORTED, parameters);
//    }
    
    public void atualizarLista(){
        parameters.put("idChat", (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat"));
        this.model.load(first, pageSize, "", SortOrder.UNSORTED, parameters);
    } 
    
    public void adicionarNovaMensagemGerador(Gerador pessoa){
        DAOGerador daoGerador = new DAOGerador(new Gerador());
        if(getNovaMensagem() == null){
                UtilMensagens.mensagemError("Mensagem nula");
                setNovaMensagem(null);
                return;
            }
        if(getNovaMensagem() != null && getNovaMensagem().equals("")){
            UtilMensagens.mensagemError("Mensagem nula");
            setNovaMensagem(null);
                return;
        }
            ChatAplicacao novo = new ChatAplicacao();
            novo.setDataHora(new Date());
            DAO<Chat> daoChat=  new DAO<Chat>(new Chat());
            long idChat = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat");
            Chat chat = daoChat.buscarChat(idChat);
            novo.setChat(chat);
            novo.setMensagem(getNovaMensagem());
            novo.setGerador(pessoa);
            chat.adicionarChatAplica(novo);
            DAO<ChatAplicacao> dao = new DAO(novo);
            dao.inserir();
            setNovaMensagem(null);
        }
    
    public void adicionarNovaMensagemReciclador(Reciclador pessoa){
        DAOReciclador daoReciclador = new DAOReciclador(new Reciclador());
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
            DAO<Chat> daoChat=  new DAO<Chat>(new Chat());
            long idChat = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat");
            Chat chat = daoChat.buscarChat(idChat);
            novo.setChat(chat);
            novo.setMensagem(novaMensagem);
            novo.setReciclador(pessoa);
            chat.adicionarChatAplica(novo);
            DAO<ChatAplicacao> dao = new DAO(novo);
            dao.inserir();
            novaMensagem = null;
        }

    /**
     * @return the model
     */
    public LazyDataModel<ChatAplicacao> getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(LazyDataModel<ChatAplicacao> model) {
        this.model = model;
    }

    /**
     * @return the neg
     */
    public Negociacao getNeg() {
        return neg;
    }

    /**
     * @param neg the neg to set
     */
    public void setNeg(Negociacao neg) {
        this.neg = neg;
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
     * @return the first
     */
    public int getFirst() {
        return first;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

}
