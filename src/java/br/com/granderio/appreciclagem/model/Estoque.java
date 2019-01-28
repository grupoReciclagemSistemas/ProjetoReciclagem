
package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Estoque implements Serializable {
    
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long idEstoque;
     
    @OneToOne
    private Material material;
    
    @OneToOne
    private EstoqueGerador estoqueGerador;
    
    //Qtd em Toneladas
    private double quantidadeMaterial;
    
    public Estoque(){
        idEstoque = -1;
        material = null;
        estoqueGerador = null;
    }

    /**
     * @return the idEstoque
     */
    public long getIdEstoque() {
        return idEstoque;
    }

    /**
     * @param idEstoque the idEstoque to set
     */
    public void setIdEstoque(long idEstoque) {
        this.idEstoque = idEstoque;
    }


    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the quantidadeMaterial
     */
    public double getQuantidadeMaterial() {
        return quantidadeMaterial;
    }

    /**
     * @param quantidadeMaterial the quantidadeMaterial to set
     */
    public void setQuantidadeMaterial(double quantidadeMaterial) {
        this.quantidadeMaterial = quantidadeMaterial;
    }

    /**
     * @return the estoqueGerador
     */
    public EstoqueGerador getEstoqueGerador() {
        return estoqueGerador;
    }

    /**
     * @param estoqueGerador the estoqueGerador to set
     */
    public void setEstoqueGerador(EstoqueGerador estoqueGerador) {
        this.estoqueGerador = estoqueGerador;
    }
    
}
