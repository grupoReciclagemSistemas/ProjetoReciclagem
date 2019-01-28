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
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;


public class DAOPessoaJuridica extends DAO<PessoaJuridica>{
    
    public DAOPessoaJuridica(PessoaJuridica pessoa) {
        super(pessoa);
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

