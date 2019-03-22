/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Administrador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.UtilError;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class DAOAdministrador extends DAO<Administrador> {

    public DAOAdministrador(Administrador admin) {
        super(admin);
    }
    
    public Administrador logarAdmin(String login, String senha){
        Administrador adm = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(Administrador.class);
            cri.add(Restrictions.eq("login", login));
            cri.add(Restrictions.eq("senha", senha));
            adm = (Administrador) cri.list().get(0);
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            s.getTransaction().rollback();
        }finally{
            s.getTransaction().commit();
            s.flush();
        }
        return adm;  
    }
    
    

}
