/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOReciclador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorReciclador")
@SessionScoped
public class ControladorReciclador extends ControladorPrincipal<Reciclador> {
         
    private Reciclador recicladorSelecionado;
    
    public ControladorReciclador(){
        super(new Reciclador());
        
    }
   
    public String entrarEmNegociacao(PedidoReciclagem pedido, Reciclador recicladorLogado){
        DAOReciclador dao = new DAOReciclador(recicladorLogado);
//        boolean negociacaoExiste = dao.verificarNegociacaoSeExiste(pedido, recicladorLogado);
//        if(negociacaoExiste){
//            UtilMensagens.mensagemAdvertencia("Você já está em Negociação para este pedido.");
//            return "";
//        }
        Negociacao neg = new Negociacao(pedido, recicladorLogado, pedido.getGerador());
        
        //Adiciona na lista de Reciclador e Gerador a Negociacao criada
        recicladorLogado.adicionarNegociacao(neg);
        pedido.getGerador().adicionarNegociacao(neg);
             
        // Insere a Negociacao
        DAO<Negociacao> daoNegociacao = new DAO(neg);
        daoNegociacao.inserir(); 
        
        // Atualiza Reciclador   
        dao.alterar();
        
        // Atualiza o Gerador 
        DAO<Gerador> daoGerador = new DAO(pedido.getGerador());
        daoGerador.alterar();
        return "minha_conta?faces-redirec=true";
    }
    
      public void onDoubleClick(SelectEvent event){
       Reciclador obj = (Reciclador) event.getObject();
       recicladorSelecionado = obj;
   }
   
   public void onRowSelect(SelectEvent event){
       Reciclador obj = (Reciclador) event.getObject();
       recicladorSelecionado = obj;
   }
   
   public void onRowUnselect(UnselectEvent event){
       recicladorSelecionado = null;
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

    /**
     * @return the recicladorSelecionado
     */
    public Reciclador getRecicladorSelecionado() {
        return recicladorSelecionado;
    }

    /**
     * @param recicladorSelecionado the recicladorLogado to set
     */
    public void setRecicladorSelecionado(Reciclador recicladorSelecionado) {
        this.recicladorSelecionado = recicladorSelecionado;
    }
      
}
