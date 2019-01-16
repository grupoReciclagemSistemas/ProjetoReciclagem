/*
 * Copyright (C) 2018 Pablo Rangel <pablorangel@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.PessoaJuridica;
import br.com.granderio.appreciclagem.util.HibernateUtil;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Esta classe implementa um DAO (Data Access Object - Genérico) para conexão ao
 * Banco de Dados.
 *
 * @since 20/05/2016
 * @author Pablo Rangel <pablorangel at gmail.com>
 * @param <T> tipo genérico para as classes de modelo a serem persistidas.
 */
public class DAO<T> {
    
    private T objetoModelo;
    public Session s;
   
   
    public DAO(T objetoModelo) {
        this.objetoModelo = objetoModelo;
        
        if(s == null){
            s = HibernateUtil.getSessionFactory().openSession();
        }
 
    }

    public T getObjetoModelo() {
        return objetoModelo;
    }

    public void setObjetoModelo(T objetoModelo) {
        this.objetoModelo = objetoModelo;
    }

    public void inserir() {      
        try {      
            s.getTransaction().begin();
            s.save(getObjetoModelo());
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao incluir registro: " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();          
        }
    }

    public void alterar() {       
        try {
            s.getTransaction().begin();
            s.update(getObjetoModelo());
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao incluir alterar: " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();         
        }
    }

    public void excluir() {      
        try {      
            s.getTransaction().begin();
            s.delete(getObjetoModelo());
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);       
            System.err.println("Erro ao excluir registro: " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
    }

    public List<T> obterLista() {     
        List<T> list = null;
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(getObjetoModelo().getClass());
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            list = criteria.list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();          
        }

        return list;
    }

    public void buscar(long id) {    
        try {
            s.getTransaction().begin();
            s.load(getObjetoModelo(), id);
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registro: " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();      
        }
    }
    
    public List executarConsultaPersonalizada(String sql) {      
        List data = null;
        try {
            s.getTransaction().begin();
            SQLQuery query = s.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            data = query.list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros: " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();     
        }
        return data;
    }
    
    public boolean verificarEmail(String email){
        List<PessoaJuridica> lista = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(PessoaJuridica.class);
            cri.add(Restrictions.eq("email", email));         
            lista = cri.list();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros: " + mensagem);
            s.getTransaction().rollback();
        }finally{
            s.getTransaction().commit();
            s.flush();    
        }
        
        if(lista.size() > 0 ){
            return true;
        }
         return false;   
    }
    
     public boolean verificarCNPJ(String cnpj){
        List<PessoaJuridica> lista = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(PessoaJuridica.class);
            cri.add(Restrictions.eq("cnpj", cnpj));         
            lista = cri.list();
        }catch(HibernateException ex){
            System.err.println("Erro ao buscar registros: " + ex);
            s.getTransaction().rollback();
        }finally{
            s.getTransaction().commit();
            s.flush();    
        }
        if(lista.size() > 0 ){
            return true;
        }
         return false;   
    }
    
  
}

