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
import java.util.Map;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

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
            s.getTransaction().commit();
        }catch(HibernateException ex){
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao logar: " + mensagem);
            s.getTransaction().rollback();
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
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao incluir registro: " + mensagem);
            s.getTransaction().rollback();
        }
    }
    
    public void alterar() {       
        try {
            s.getTransaction().begin();
            s.update(getObjetoModelo());
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao incluir alterar: " + mensagem);
            s.getTransaction().rollback();
        }
    }
  
    public int quantidadeDeMateriais(){
        List<Material> lista = null;       
        try{     
            s.getTransaction().begin();       
            Criteria cri = s.createCriteria(Material.class);
            lista = cri.list();
            s.getTransaction().commit();
        }catch(HibernateException ex){
            System.err.println("Erro ao buscar lista de Material: " + ex);
            s.getTransaction().rollback();
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
             s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);       
            System.err.println("Erro ao excluir registro: " + mensagem);
            s.getTransaction().rollback();
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
             s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        return list;
    }

    public void buscar(long id) {    
        try {
            s.getTransaction().begin();
            s.load(getObjetoModelo(), id);
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registro: " + mensagem);
            s.getTransaction().rollback();
        }
    }
    
    public List executarConsultaPersonalizada(String sql) {      
        List data = null;
        try {
            s.getTransaction().begin();
            SQLQuery query = s.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            data = query.list();
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros: " + mensagem);
            s.getTransaction().rollback();
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
             s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
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
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        if(lista.size() >0){
            return lista.get(0);
        }
        return null;
    }
    
    public Chat buscarChat(long idChat){
      List<Chat> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(Chat.class);
            criteria.add(Restrictions.eq("idChat", idChat));
            lista = criteria.list();
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        if(lista.size() >0){
            return lista.get(0);
        }
        return null;
    }
    
    public Material buscarMaterial(long id){
      List<Material> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(Material.class);
            criteria.add(Restrictions.eq("idMaterial", id));
            lista = criteria.list();
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        if(lista.size() >0){
            return lista.get(0);
        }
        return null;
    }
    
     public List<ChatAplicacao> buscarMensagensChat(Chat chat){
        List<ChatAplicacao> lista = null;
         try{
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(ChatAplicacao.class);
            criteria.add(Restrictions.eq("chat", chat));
            criteria.addOrder(Order.desc("idChatAplicacao"));
            lista = criteria.list();  
            s.getTransaction().commit();
            return lista;
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             s.getTransaction().rollback();
             return null;
        }
    }
     
     public List<ChatAplicacao> buscarLazyModelMensagens(int first, int max, String sortField, SortOrder sortOrder, Map<String, Object> filters){   
        List<ChatAplicacao> lista = null;
        long idChat = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat");
        Query query = null;
         try{
            s.getTransaction().begin();
//            if(filters != null && filters.size() > 0){
//                idChat = (long) filters.get("idChat");
//                query = s.getNamedQuery("ChatAplica.buscarTodos").setLong("idChat", idChat);
//            }else{
//                query = s.getNamedQuery("ChatAplica.buscarTodos2");
//            } 
            query = s.getNamedQuery("ChatAplica.buscarTodos").setLong("idChat", idChat);
            query.setFirstResult(first);
            query.setMaxResults(max);
            lista = query.list();
            s.getTransaction().commit();
            return lista;
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             s.getTransaction().rollback();
             return null;
        }
    }
     
     public int getChatAplicacaoTotalCount() {
      List<ChatAplicacao> lista = null;
      Query query = null;
      long idChat = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat");
         try{
            s.getTransaction().begin();
//            if(idChat > 0){
//                query = s.getNamedQuery("ChatAplica.buscarTodos").setLong("idChat", idChat);
//            }else{
//                query = s.getNamedQuery("ChatAplica.buscarTodos2");
//            }
            query = s.getNamedQuery("ChatAplica.buscarTodos").setLong("idChat", idChat);
            lista = query.list();
            s.getTransaction().commit();
            return lista.size();
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             s.getTransaction().rollback();
             return 0;
        }
  }
    
    public Negociacao buscarNegociacao(long id){
        List<Negociacao> lista = new ArrayList();
        try {
            s.getTransaction().begin();
            Criteria criteria = s.createCriteria(Negociacao.class);
            criteria.add(Restrictions.eq("idNegociacao", id));
            lista = criteria.list();
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        if(lista.size() >0){
            return lista.get(0);
        }
        return null;
    }
    
    public List<?> listByNamedQuery(String name){
        Query query = s.getNamedQuery(name);
         try {
            s.getTransaction().begin();
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            s.getTransaction().rollback();
        }
        return null;
    }
        
}

