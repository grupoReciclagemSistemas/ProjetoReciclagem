/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import java.io.Serializable;
import java.util.List;
import br.com.granderio.appreciclagem.dao.DAO;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Rafael
 * @param <T>
 */
public abstract class ControladorPrincipal <T> implements Serializable {
    
    private T modelo;
    
    public ControladorPrincipal(T modelo) {
        this.modelo = modelo;
    }
    
    protected void inserir (){
        DAO <T> acesso = new DAO(modelo);
        acesso.inserir();
    }
      
    protected void alterar (){
        DAO <T> acesso = new DAO(modelo);
        acesso.alterar();
    }
    
    public void excluir (){
        DAO <T> acesso = new DAO(modelo);
        acesso.excluir();
    }
    
    public void buscar (int id){
        DAO <T> acesso = new DAO(modelo);
        acesso.buscar(id);
    }
    
    public T getModelo() {
        return modelo;
    }

    public void setModelo(T modelo){
        if (modelo == null){
            try {
                modelo = (T)this.modelo.getClass().newInstance();
            } catch (IllegalAccessException | InstantiationException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.modelo = modelo;
    }
    
public void salvar(int id){
        if (id == -1){
            inserir();
        }
        else{
            alterar();
        }
    }   
}
