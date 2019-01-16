package br.com.granderio.appreciclagem.util;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;


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
        g1.setIdPessoaJuridica(4);
        g1.setEmail("asad@hotmail.com");
        g1.setRazaoSocial("TESTANDO INSTANCIAS");
        g1.setCnpj("213123213-11");
        g1.setTelefone("(33) 1111-2222");
        g1.setSenha("1234");
        
       DAO<Gerador> acesso = new DAO(g1);
       acesso.excluir();
                 
    }
    
}
