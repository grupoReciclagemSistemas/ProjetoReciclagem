package br.com.granderio.appreciclagem.util;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Estoque;
import br.com.granderio.appreciclagem.model.EstoqueGerador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;

public class GerarInstancias {
    
    public static void main(String[] args) throws InterruptedException{
            
//            Gerador g1 = new Gerador();
//            Endereco ed = new Endereco();
//            Material m1 = new Material();
//            Estoque es1 = new Estoque();
//            EstoqueGerador eg1 = new EstoqueGerador();
//            
//            //Adicionando Material
//            m1.setDescricao("TESTANDO MATERIAL");
//            m1.setNome("Aluminio");
//            m1.setPrecoMax(0.25);
//            m1.setPrecoMin(0.21);
//            
//            //Inserindo o Material no Banco
//            DAO<Material> dao1 = new DAO(m1);
//            dao1.inserir();
//            
//            //Editando o Endereço
//            ed.setBairro("SJM");
//            ed.setCep("21000-222");
//            ed.setCidade("RIO DE JANEIRO");
//            ed.setComplemento("");
//            ed.setLogradouro("AVENIDA TESTANDO GERADOR ");
//            ed.setNumero(1002);
//            //Colocando a PessoaJuridica que pertence a esse endereço, que é o g1, criado nesta Main
//            ed.setPessoa(g1);
//            ed.setUf("RJ");
//            
//            //Editando o Gerador
//            g1.setCnpj("111.222.333-44");
//            g1.setEmail("gerador1@teste.com");
//            g1.setRazaoSocial("Reciclagem APP");
//            g1.setSenha("123");
//            g1.setTelefone("(24) 2121-3434");
//            //Colocando o Endereço de Gerador que é o ED instanciado nessa Main
//            g1.setEndereco(ed);
//            
//            //Editando estoque do Material 1 com 3.65 toneladas
//            es1.setMaterial(m1);
//            es1.setQuantidadeMaterial(3.65);
//            es1.setEstoqueGerador(eg1);
//      
//            //Editando EstoqueGerador, que contém 1 estoque e o Gerador 
//            eg1.setEstoque(es1);
//            eg1.setGerador(g1);
//            
//            //Adicionando na Lista do Gerador o EstoqueGerador criado aqui
//            g1.adicionarEstoqueGerador(eg1);
//            
//            DAO<Gerador> acesso = new DAO(g1);
//            acesso.inserir();
            
            
    }
   
}
