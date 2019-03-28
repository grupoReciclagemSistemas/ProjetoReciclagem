/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.model.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rafael
 */
@ManagedBean(name="controladorEndereco")
@RequestScoped
public class ControladorEndereco extends ControladorPrincipal<Endereco> {
    
    private List<String> uf = new ArrayList();
    private List<String> cidadesRJ = new ArrayList();
    
    public ControladorEndereco() {
        super(new Endereco() );
//        uf.add("RJ");   
//        cidadesRJ.add("Angra dos Reis");
//        cidadesRJ.add("Aperibe");
//        cidadesRJ.add("Araruama");
//        cidadesRJ.add("Areal");
//        cidadesRJ.add("Armação de Buzios");
//        cidadesRJ.add("Arraial do Cabo");
//        cidadesRJ.add("Barra Mansa");
//        cidadesRJ.add("Barra do Pirai");
//        cidadesRJ.add("Belford Roxo");
//        cidadesRJ.add("Bom Jardim");
//        cidadesRJ.add("Bom Jesus do Itabapoana");
//        cidadesRJ.add("Cabo Frio");
//        cidadesRJ.add("Cachoeiras de Macacu");
//        cidadesRJ.add("Cambuci");
//        cidadesRJ.add("Campos dos Goytacazes");
//        cidadesRJ.add("Cantagalo");
//        cidadesRJ.add("Carapebus");
//        cidadesRJ.add("Cardoso Moreira");
//        cidadesRJ.add("Carmo");
//        cidadesRJ.add("Casimiro de Abreu");
//        cidadesRJ.add("Comendador Levy Gasparian");
//        cidadesRJ.add("Conceicao de Macabu");
//        cidadesRJ.add("Cordeiro");
//        cidadesRJ.add("Duas Barras");
//        cidadesRJ.add("Duque de Caxias");
//        cidadesRJ.add("Engenheiro Paulo de Frontin");
//        cidadesRJ.add("Guapimirim");
//        cidadesRJ.add("Iguaba Grande");
//        cidadesRJ.add("Itaborai");
//        cidadesRJ.add("Itaguai");
//        cidadesRJ.add("Japeri");
//        cidadesRJ.add("Laje do Muriae");
//        cidadesRJ.add("Macaé");
//        cidadesRJ.add("Macuco");
//        cidadesRJ.add("Mage");
//        cidadesRJ.add("Mangaratiba");
//        cidadesRJ.add("Maricá");
//        cidadesRJ.add("Mendes");
//        cidadesRJ.add("Miguel Pereira");
//        cidadesRJ.add("Miracema");
//        cidadesRJ.add("Natividade");
//        cidadesRJ.add("Nilopólis");
//        cidadesRJ.add("Niterói");
//        cidadesRJ.add("Nova Friburgo");
//        cidadesRJ.add("Nova Iguaçu");
//        cidadesRJ.add("Paracambi");
//        cidadesRJ.add("Paraiba do Sul");
//        cidadesRJ.add("Parati");
//        cidadesRJ.add("Paty do Alferes");
//        cidadesRJ.add("Petropólis");
//        cidadesRJ.add("Pinheiral");
//        cidadesRJ.add("Pirai");
//        cidadesRJ.add("Porciuncula");
//        cidadesRJ.add("Porto Real");
//        cidadesRJ.add("Quatis");
//        cidadesRJ.add("Queimados");
//        cidadesRJ.add("Quissama");
//        cidadesRJ.add("Resende");
//        cidadesRJ.add("Rio Bonito");
//        cidadesRJ.add("Rio Claro");
//        cidadesRJ.add("Rio das Flores");
//        cidadesRJ.add("Rio das Ostras");
//        cidadesRJ.add("Rio de Janeiro");
//        cidadesRJ.add("Santa Maria Madalena");
//        cidadesRJ.add("Santo Antônio de Padua");
//        cidadesRJ.add("São Fidelis");
//        cidadesRJ.add("São Francisco de Itabapoana");
//        cidadesRJ.add("São Gonçalo");
//        cidadesRJ.add("São João da Barra");
//        cidadesRJ.add("São João de Meriti");
//        cidadesRJ.add("São José de Uba");
//        cidadesRJ.add("São José do Vale do Rio Preto");
//        cidadesRJ.add("São Pedro da Aldeia");
//        cidadesRJ.add("São Sebastião do Alto");
//        cidadesRJ.add("Sapucaia");
//        cidadesRJ.add("Saquarema");
//        cidadesRJ.add("Seropédica");
//        cidadesRJ.add("Silva Jardim");
//        cidadesRJ.add("Sumidouro");
//        cidadesRJ.add("Tangua");
//        cidadesRJ.add("Teresopólis");
//        cidadesRJ.add("Trajano de Morais");
//        cidadesRJ.add("Três Rios");
//        cidadesRJ.add("Valença");
//        cidadesRJ.add("Varre-Sai");
//        cidadesRJ.add("Vassouras");
//        cidadesRJ.add("Volta Redonda");
    }

    /**
     * @return the uf
     */
    public List<String> getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(List<String> uf) {
        this.uf = uf;
    }

    /**
     * @return the cidadesRJ
     */
    public List<String> getCidadesRJ() {
        return cidadesRJ;
    }

    /**
     * @param cidadesRJ the cidadesRJ to set
     */
    public void setCidadesRJ(List<String> cidadesRJ) {
        this.cidadesRJ = cidadesRJ;
    }
    
}
