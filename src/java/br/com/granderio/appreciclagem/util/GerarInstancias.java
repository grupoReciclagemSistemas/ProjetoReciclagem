package br.com.granderio.appreciclagem.util;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Reciclador;

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
        Reciclador r1 = new Reciclador();
        r1.setCnpj("500000-00");
        r1.setEmail("teste@gmail.com");
        r1.setRazaoSocial("TESTANDO");
        r1.setSenha("123");
        r1.setTelefone("21 9999-9999");
        
        Endereco end = new Endereco();
        end.setBairro("SJM");
        end.setCep("20000-400");
        end.setCidade("RJ");
        end.setComplemento("APARTAMENTO 999");
        end.setNumero(1004);
        end.setLogradouro("AVENIDA TESTANDO ");
        
        r1.setEndereco(end);
        end.setPessoaJuridica(r1);
        
        DAO<Reciclador> acesso = new DAO(r1);
        acesso.inserir();
        
    }
    
}
