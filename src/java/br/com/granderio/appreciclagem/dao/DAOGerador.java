/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class DAOGerador extends DAOPessoaJuridica {
    
    public DAOGerador(Gerador gerador) {
        super(gerador);
    }
    
    public Gerador logarGerador(String email, String senha){
        Gerador ger = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(Gerador.class);
            cri.add(Restrictions.eq("email", email));
            cri.add(Restrictions.eq("senha", senha));
            ger = (Gerador) cri.list().get(0);
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            s.getTransaction().rollback();
        }finally{
            s.getTransaction().commit();
            s.flush();
        }
        return ger;    
    }
    
    public List<PedidoReciclagem> materiaisVendendo(Gerador ger){
        List<PedidoReciclagem> list = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(PedidoReciclagem.class);
            cri.add(Restrictions.eq("gerador", ger));
            cri.add(Restrictions.isNull("reciclador"));
            cri.add(Restrictions.isNull("transportador"));
            list = (List<PedidoReciclagem>) cri.list();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            s.getTransaction().rollback();
        }finally{
            s.getTransaction().commit();
            s.flush();
        }
        return list;
    }
    
    public Gerador buscarGerador(long id){
         List<Gerador> list = null;
         try{
             s.getTransaction().begin();
             Criteria criteria = s.createCriteria(Gerador.class);
             criteria.add(Restrictions.eq("idPessoaJuridica", id));
             list = criteria.list();
             s.getTransaction().commit();
         }catch(HibernateException ex){
             String mensagem = UtilError.getMensagemErro(ex);
             System.err.println("Erro ao logar: " + mensagem);
             s.getTransaction().rollback();
         }finally{
             s.flush(); 
         }       
         if(list.size() > 0 )
             return list.get(0);
         
         return null;
     }
   
}
