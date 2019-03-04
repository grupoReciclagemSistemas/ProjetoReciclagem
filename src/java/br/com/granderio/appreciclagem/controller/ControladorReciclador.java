/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.model.Chat;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorReciclador")
public class ControladorReciclador extends ControladorPrincipal<Reciclador> {
         
    public ControladorReciclador(){
        super(new Reciclador());
        
    }
   
    public void entrarEmNegociacao(PedidoReciclagem pedido, Reciclador recicladorLogado){
        DAOReciclador dao = new DAOReciclador(recicladorLogado);
//        boolean negociacaoExiste = dao.verificarNegociacaoSeExiste(pedido, recicladorLogado);
//        if(negociacaoExiste){
//            UtilMensagens.mensagemAdvertencia("Você já está em Negociação para este pedido.");
//            return;
//        }
        Negociacao neg = new Negociacao(pedido, recicladorLogado, pedido.getGerador());
        
        //Adiciona na lista de Reciclador e Gerador a Negociacao criada
        recicladorLogado.adicionarNegociacao(neg);
        pedido.getGerador().adicionarNegociacao(neg);
        
        // Insere o Chat
        DAO<Chat> daoChat = new DAO(neg.getChat());
        daoChat.inserir();
        
        // Insere o Negociacao
        DAO<Negociacao> daoNegociacao = new DAO(neg);
        daoNegociacao.inserir(); 
        
        // Atualiza Reciclador   
        dao.alterar();
        
        // Atualiza o Gerador 
        DAO<Gerador> daoGerador = new DAO(pedido.getGerador());
        daoGerador.alterar();
    }
    
    public List<Reciclador> lista(){
        DAO<Reciclador> lista = new DAO(new Reciclador());
        return lista.obterLista();
    }
 
    public String testarTrans(boolean valor){
        if(valor){
            return "Sim";
        }
        return "Não";              
    }
    
    public String novoReciclador(){
        return "registrar?faces-redirect=true";
    }
      
}
