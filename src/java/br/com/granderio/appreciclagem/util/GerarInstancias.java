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
        /*
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
        */
          
        /*
        Reciclador novoReciclador = new Reciclador();
        TransportadorProprio trans = new TransportadorProprio();       
        Endereco end = new Endereco();
        
        novoReciclador.setCnpj("500000-00");
        novoReciclador.setEmail("rafa@gmail.com");
        novoReciclador.setRazaoSocial("TESTANDO");
        novoReciclador.setSenha("123");
        novoReciclador.setTelefone("21 9999-9999");
            
        end.setBairro("SJM");
        end.setCep("20000-400");
        end.setCidade("RJ");
        end.setComplemento("APARTAMENTO 999");
        end.setNumero(1004);
        end.setLogradouro("AVENIDA TESTANDO ");
        
        novoReciclador.setEndereco(end);
             
        novoReciclador.setTransportadoraPropria(trans);
        trans.setReciclador(novoReciclador);
           
        DAO<Reciclador> acesso = new DAO(novoReciclador);
        acesso.inserir();
    }
 */
         Gerador g1 = new Gerador();
         g1.setCnpj("xxx-xxx-xxx-00");
         g1.setEmail("xxx@gmail.com");
         g1.setRazaoSocial("TESTE");
         g1.setSenha("123");
         g1.setTelefone("(99) 9999-9999");
         
         DAO<Gerador> acesso = new DAO(g1);
         acesso.inserir();
                 
    }
    
}
