package br.com.granderio.appreciclagem.util;

import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.Administrador;
import br.com.granderio.appreciclagem.model.Endereco;
import br.com.granderio.appreciclagem.model.Estoque;
import br.com.granderio.appreciclagem.model.EstoqueGerador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.model.Transportador;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerarInstancias {
    
    public static void main(String[] args) throws InterruptedException{
      
            Administrador adm = new Administrador();
            adm.setEmail("rafaelnunes.inf@gmail.com");
            adm.setLogin("admin");
            adm.setSenha("123");
            adm.setNome("Administrador Geral");
            DAO<Administrador> dao = new DAO(adm);
            dao.inserir();
    }
   
}
