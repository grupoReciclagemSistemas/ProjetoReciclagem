package br.com.granderio.appreciclagem.util;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Gerador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael
 */
public class GerarInstancias {
    
    public static void main(String[] args){
        Gerador g1 = new Gerador();
        g1.setCnpj("5345345");
        g1.setEmail("rafaelnunes.inf@gmail.com");
        g1.setRazaoSocial("Testando");
        g1.setSenha("teste");
        g1.setTelefone("(99) 9999-9999");
        
        DAO<Gerador> acesso = new DAO(g1);
        acesso.inserir();
    }
    
}
