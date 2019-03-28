/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
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
public class DAOReciclador extends DAOPessoaJuridica {

    public DAOReciclador(Reciclador reciclador) {
        super(reciclador);
    }
    
     public Reciclador logarReciclador(String email, String senha){
        Reciclador reci = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(Reciclador.class);
            cri.add(Restrictions.eq("email", email));
            cri.add(Restrictions.eq("senha", senha));
            reci = (Reciclador) cri.list().get(0);
            s.getTransaction().commit();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            s.getTransaction().rollback();
        }
        return reci;
    }
     
     //Método que verifica se a Negociação com o mesmo Pedido e o Reciclador já existe, para não criar 2.
     public boolean verificarNegociacaoSeExiste(PedidoReciclagem pedido, Reciclador reciclador){
         List<Negociacao> list = null;
         try{
             Query query = s.getNamedQuery("Negociacao.listarPorPedidoReciclador").setLong("id", pedido.getIdPedidoReciclagem())
                     .setLong("idReciclador", reciclador.getIdPessoaJuridica());
             list = query.list();
             s.getTransaction().commit();
         }catch(Exception ex){
             String mensagem = UtilError.getMensagemErro(ex);
             System.err.println("Erro ao logar: " + mensagem);
             s.getTransaction().rollback();
         }
         if(list.size() > 0 )
             return true;
         
         return false;
     }
     
     public Reciclador buscarReciclador(long id){
         List<Reciclador> list = null;
         try{
             s.getTransaction().begin();
             Criteria criteria = s.createCriteria(Reciclador.class);
             criteria.add(Restrictions.eq("idPessoaJuridica", id));
             list = criteria.list();
             s.getTransaction().commit();
         }catch(HibernateException ex){
             String mensagem = UtilError.getMensagemErro(ex);
             System.err.println("Erro ao logar: " + mensagem);
             s.getTransaction().rollback();
         }   
         if(list.size() > 0 )
             return list.get(0);
         
         return null;
     }

}
