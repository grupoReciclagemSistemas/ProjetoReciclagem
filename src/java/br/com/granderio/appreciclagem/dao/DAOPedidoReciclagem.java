/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class DAOPedidoReciclagem extends DAO<PedidoReciclagem> {

    public DAOPedidoReciclagem(PedidoReciclagem pedido) {
        super(pedido);
    }
    
    public List<PedidoReciclagem> listarTodos(){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarTodos");
         try {
            s.getTransaction().begin();  
            retorno = query.list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    public List<PedidoReciclagem> listarPorCidade(String cidade){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarPedidosPorCidade");
         try {
            s.getTransaction().begin();  
            retorno = query.setString("cidade", cidade).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    public List<PedidoReciclagem> listarPorMaterial(long idMaterial){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarPedidosPorMaterial");
         try {
            s.getTransaction().begin();  
            retorno = query.setLong("idmaterial", idMaterial).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    
    
    public List<PedidoReciclagem> listarPorCidadeMaterial(String cidade, long idMaterial){
          List<PedidoReciclagem> retorno = null;
          Query query = s.getNamedQuery("Pedido.buscarPedidosPorMaterialCidade");
         try {
            s.getTransaction().begin();  
            retorno = query.setString("cidade", cidade).setLong("idmaterial", idMaterial).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        return retorno;
    }
    
    public boolean existeNegociacoes(PedidoReciclagem pedido){
        List<Negociacao> retorno = null;
          Query query = s.getNamedQuery("Negociacao.listarPorIdPedidoReciclagem");
         try {
            s.getTransaction().begin();  
            retorno = query.setLong("id", pedido.getIdPedidoReciclagem()).list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        if(retorno.size() > 0)
            return true;
       
        return false;
    }
      
}

