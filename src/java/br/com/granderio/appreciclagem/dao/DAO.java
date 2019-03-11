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

import br.com.granderio.appreciclagem.model.Chat;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.model.Estoque;
import br.com.granderio.appreciclagem.model.EstoqueGerador;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.PessoaJuridica;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Transportador;
import br.com.granderio.appreciclagem.util.HibernateUtil;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
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
        s = HibernateUtil.pegarSession();
    }
    
     /*
    public long ultimoIdEstoque(){
        List<Estoque> listagem = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(Estoque.class);
            cri.addOrder(Order.desc("idEstoque"));
            listagem = cri.list();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao excluir Listagem: " + mensagem);
            s.getTransaction().rollback();
        }finally{
        s.getTransaction().commit();
        s.flush();
        }
        return listagem.get(0).getIdEstoque();
    }
 
    public long ultimoIdEstoqueGerador(){
        List<EstoqueGerador> listagem = null;
        try{
            s.getTransaction().begin();
            Criteria cri = s.createCriteria(EstoqueGerador.class);
            cri.addOrder(Order.desc("idEstoqueGerador"));
            listagem = cri.list();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao excluir Listagem: " + mensagem);
            s.getTransaction().rollback();
        }finally{
        s.getTransaction().commit();
        s.flush();  
        }
        return listagem.get(0).getIdEstoqueGerador();
    }
    
    public void updateGeradorSQL(double qtdMat, long idMaterial, long idGerador){
        long id1 = (this.ultimoIdEstoque() + 1);
        long id2 = (this.ultimoIdEstoqueGerador() + 1);
        try{
        s.getTransaction().begin();
        s.createSQLQuery("INSERT INTO Estoque VALUES ( " + id1 + ", " + qtdMat + ", " + null + ", " + idMaterial + ")").executeUpdate();
        s.createSQLQuery("INSERT INTO EstoqueGerador VALUES ( " + id2 + ", " + null + ", " + idGerador + ")").executeUpdate();
        s.createSQLQuery("UPDATE Estoque SET estoqueGerador_idEstoqueGerador = " + id2 + " WHERE idEstoque = " + id1).executeUpdate();
        s.createSQLQuery("UPDATE EstoqueGerador SET estoque_idEstoque = " + id1 + " WHERE idEstoqueGerador = " + id2).executeUpdate();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao excluir Gerador --> " + mensagem);
            s.getTransaction().rollback();
        }
        s.getTransaction().commit();
        s.flush();  
    }
    */
    
    public int quantidade(String email, String senha){  
        int quantidade = 0;
        try{
            s.getTransaction().begin();
            
            Criteria cri = s.createCriteria(Transportador.class);
            cri.add(Restrictions.eq("email", email));
            cri.add(Restrictions.eq("senha", senha));
            quantidade = cri.list().size();          
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            s.getTransaction().rollback();
        }finally{
            s.getTransaction().commit();
            s.flush();
        }
        return quantidade;
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
  
    public int quantidadeDeMateriais(){
        List<Material> lista = null;       
        try{     
            s.getTransaction().begin();       
            Criteria cri = s.createCriteria(Material.class);
            lista = cri.list();
        }catch(HibernateException ex){
            System.err.println("Erro ao buscar lista de Material: " + ex);
            s.getTransaction().rollback();
        }finally{
           s.getTransaction().commit();
           s.flush();
        }
        return lista.size();
    }
    
    /*
    public PessoaJuridica logar(String email, String senha){
        List<PessoaJuridica> lista = null;
        try{     
            s.getTransaction().begin();       
            Criteria cri = s.createCriteria(PessoaJuridica.class);
            cri.add(Restrictions.eq("email", email));
            cri.add(Restrictions.eq("senha", senha));
            lista = cri.list();
        }catch(HibernateException ex){
            System.err.println("Erro ao buscar lista de Material: " + ex);
            s.getTransaction().rollback();
        }finally{
           s.getTransaction().commit();
           s.flush();
        }
        return lista.get(0);
    }
  */
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
    
    /*
    public void excluirGeradorPorSQL(long id){
        try{
        s.getTransaction().begin();
        int a = s.createSQLQuery("DELETE FROM Estoque WHERE gerador_idPessoaJuridica = " + id).executeUpdate();
        int b = s.createSQLQuery("DELETE FROM Gerador WHERE idPessoaJuridica = " + id).executeUpdate();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao excluir Gerador --> " + mensagem);
            s.getTransaction().rollback();
        }
        s.getTransaction().commit();
        s.flush();
    }
    
    public void excluirTranposrtadorPorSQL(long id){
        try{
        s.getTransaction().begin();
        int a = s.createSQLQuery("DELETE FROM PedidoReciclagem WHERE transportador_idPessoaJuridica = " + id).executeUpdate();
        int b = s.createSQLQuery("DELETE FROM Transportador WHERE idPessoaJuridica = " + id).executeUpdate();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao excluir Gerador --> " + mensagem);
            s.getTransaction().rollback();
        }
        s.getTransaction().commit();
        s.flush();  
    }
    
    public void excluirRecicladorPorSQL(long id){
        try{
        s.getTransaction().begin();
        int a = s.createSQLQuery("DELETE FROM PedidoReciclagem WHERE reciclador_idPessoaJuridica = " + id).executeUpdate();
        int b = s.createSQLQuery("DELETE FROM Reciclador WHERE idPessoaJuridica = " + id).executeUpdate();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao excluir Gerador --> " + mensagem);
            s.getTransaction().rollback();
        }
        s.getTransaction().commit();
        s.flush();
    }
    */
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
    
    public List<PedidoReciclagem> listaDeMateriaisVendendo(){
        List<PedidoReciclagem> list = null;
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(PedidoReciclagem.class);
            criteria.add(Restrictions.isNull("transportador"));
            criteria.add(Restrictions.isNull("reciclador"));
            list = criteria.list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }

        return (List<PedidoReciclagem>) list;
    }
    
    public ChatAplicacao buscarChatAplicacao(long id){
      List<ChatAplicacao> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(ChatAplicacao.class);
            criteria.add(Restrictions.eq("idChatAplicacao", id));
            lista = criteria.list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        if(lista.size() >0){
            return lista.get(0);
        }
        return null;
    }
    public Negociacao buscarNegociacao(long id){
        List<Negociacao> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(Negociacao.class);
            criteria.add(Restrictions.eq("idNegociacao", id));
            lista = criteria.list();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        } finally {
            s.getTransaction().commit();
            s.flush();
        }
        if(lista.size() >0){
            return lista.get(0);
        }
        return null;
    }
        
}

