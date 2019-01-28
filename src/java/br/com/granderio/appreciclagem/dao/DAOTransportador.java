/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.UtilError;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class DAOTransportador extends DAOPessoaJuridica {

    public DAOTransportador(Transportador transportador) {
        super(transportador);
    }
    
    public Transportador logarTransportador(String email, String senha){
        Transportador trans = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(Transportador.class);
            cri.add(Restrictions.eq("email", email));
            cri.add(Restrictions.eq("senha", senha));
            trans = (Transportador) cri.list().get(0);
            if(cri.list().isEmpty()){
                System.out.println("Vazio");
            }
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            s.getTransaction().rollback();
        }finally{
            s.getTransaction().commit();
            s.flush();
        }
        return trans;
    }

}
