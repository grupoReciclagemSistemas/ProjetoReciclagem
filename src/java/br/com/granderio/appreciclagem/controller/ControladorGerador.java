/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.controller;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOGerador;
import br.com.granderio.appreciclagem.model.Estoque;
import br.com.granderio.appreciclagem.model.EstoqueGerador;
import br.com.granderio.appreciclagem.model.Gerador;
import br.com.granderio.appreciclagem.model.ItemPedido;
import br.com.granderio.appreciclagem.model.Material;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.util.UtilMensagens;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


/**
 *
 * @author Rafael
 */
@ManagedBean
@SessionScoped
@Named(value="controladorGerador")
public class ControladorGerador extends ControladorPrincipal <Gerador> {
    
    private Gerador novoGerador;
    
    //Atributos
    private EstoqueGerador estoque = null;
    private double quantidadeMais = 0.0;
    
    //Atributos para novo EstoqueGerador
    private Material materialEscolhido;
    private double quantidadeDoMaterialEscolhido = 0.0;
    
    //Gerador selecionado no Painel Admin
    private Gerador geradorSelecionado;
    
    //Atributos para listar Venda
    private double quantidadeTotal;
    private double valorPorKG;
    private boolean vender;
    
    public ControladorGerador(){
        super( new Gerador() );
        novoGerador = new Gerador();
    }
    
     public void onDoubleClick(SelectEvent event){
       Gerador obj = (Gerador) event.getObject();
       geradorSelecionado = obj;
   }
   
   public void onRowSelect(SelectEvent event){
       Gerador obj = (Gerador) event.getObject();
       geradorSelecionado = obj;
   }
   
   public void abrirModalNovoEstoque(){
       RequestContext.getCurrentInstance().execute("PF('varNovoEstoqueModal').show();");
   }
   
   public void abrirModalVender(EstoqueGerador estoque){
       this.estoque = estoque;
       RequestContext.getCurrentInstance().execute("PF('varVender').show();");
   }
   
   public void fecharModalNovoEstoque(){
       materialEscolhido = null;
       quantidadeDoMaterialEscolhido = 0.0;
       RequestContext.getCurrentInstance().execute("PF('varNovoEstoqueModal').hide();");
   }
   
   public void onRowUnselect(UnselectEvent event){
       geradorSelecionado = null;
   }
    
    public List<Gerador> lista(){
        DAO<Gerador> lista = new DAO(new Gerador());
        return lista.obterLista();
    }
    
    public void cancelar(){
        quantidadeMais = 0.0;
        estoque = null;
    }
 
    public String salvarEdicao(Gerador gerador){
        DAO<Gerador> acesso = new DAO(gerador);
        acesso.alterar();
        return "geradores?faces-redirect=true";
    }
       
    public String voltar(){
        novoGerador.setCnpj("");
        novoGerador.setEmail("");
        novoGerador.setRazaoSocial("");
        novoGerador.setTelefone("");
        return "geradores?faces-redirect=true";
    }
    
    public void mudarQuantidadeTotal(){
      EstoqueGerador estoqueGerador = getEstoque();
      double quantidadeAtual = estoqueGerador.getEstoque().getQuantidadeMaterial();
      estoqueGerador.getEstoque().setQuantidadeMaterial(quantidadeAtual + quantidadeMais);
    }
    
    public void setarEstoqueGerador(EstoqueGerador obj){
        this.setEstoque(obj);    
    }
    
    public void venderEstoqueGerador(EstoqueGerador estoque){
        this.estoque = estoque;
        setVender(true);
    }
    
    public void listarVenda(Gerador geradorLogado){
        if(estoque.getEstoque().getQuantidadeMaterial() < quantidadeTotal){
            UtilMensagens.mensagemError("A quantidade que você quer vender é maior que o seu Estoque!");
            return;
        }
        if(valorPorKG > estoque.getEstoque().getMaterial().getPrecoMax()){
           UtilMensagens.mensagemError("O preço que você colocou é maior que o preço máximo permitido por KG para esse Material!");
            return; 
        }
         if(valorPorKG < estoque.getEstoque().getMaterial().getPrecoMin()){
           UtilMensagens.mensagemError("O preço que você colocou é menor que o preço mínimo permitido por KG para esse Material!");
            return; 
        }
        ItemPedido itemPedido = new ItemPedido();
        PedidoReciclagem pedido = new PedidoReciclagem();
        itemPedido.setMaterial(estoque.getEstoque().getMaterial());
        itemPedido.setPreco(valorPorKG);
        itemPedido.setQuantidade(quantidadeTotal);
        this.retirarQuantidadeDoEstoque();
        itemPedido.setPedidoDeReciclagem(pedido);
        pedido.setItem(itemPedido);
        Double valorTotal = this.calcularValorTotalPedido(quantidadeTotal, valorPorKG);
        pedido.setData(null);
        pedido.setTransportador(null);
        pedido.setReciclador(null);
        pedido.setGerador(geradorLogado);
        pedido.setValorTotal(valorTotal);
        DAO<PedidoReciclagem> acesso = new DAO(pedido);
        acesso.inserir();
        DAO<Estoque> dao = new DAO(estoque.getEstoque());
        dao.alterar();
        this.cancelarVenda();       
    }
    
    public Double calcularValorTotalPedido(double quantidade, double valorPorKg){
        String valorAtualStr = String.valueOf(quantidade);
        String valorTotalStr = String.valueOf(valorPorKg); 
        
        BigDecimal valor1 = new BigDecimal(valorAtualStr);
        BigDecimal valor2 = new BigDecimal(valorTotalStr);
        
        return (valor1.multiply(valor2)).doubleValue();
    }
    
    public void retirarQuantidadeDoEstoque(){
        //Passa os valores de Double para STRING
        String valorAtualStr = String.valueOf(estoque.getEstoque().getQuantidadeMaterial());
        String valorTotalStr = String.valueOf(quantidadeTotal);
        
        //Instancia os BIG DECIMAL PASSANDO AS STRINGS
        BigDecimal totalAtual = new BigDecimal(valorAtualStr);
        BigDecimal totalVenda = new BigDecimal(valorTotalStr);
        
        //Subtrai o que ele tem, para o que vai vender
        BigDecimal retorno = totalAtual.subtract(totalVenda);  
        //Altera na instancia de estoque
        estoque.getEstoque().setQuantidadeMaterial(retorno.doubleValue());
    }
    
    public EstoqueGerador adicionarQuantidadeDoEstoque(PedidoReciclagem pedido, EstoqueGerador estoqueGer){
        String valorDoPedido = String.valueOf(pedido.getItem().getQuantidade());
        String valorTotal = String.valueOf(estoqueGer.getEstoque().getQuantidadeMaterial());
        
        BigDecimal quantidadeAtual = new BigDecimal(valorTotal);
         BigDecimal quantidadePedido = new BigDecimal(valorDoPedido);
         
         BigDecimal retorno = quantidadeAtual.add(quantidadePedido);
         estoqueGer.getEstoque().setQuantidadeMaterial(retorno.doubleValue());
         return estoqueGer;
    }
    
    public void excluirPedidoReciclagem(PedidoReciclagem pedido, Gerador geradorLogado){
        EstoqueGerador estoqueGeradorCorreto = null;
        for(EstoqueGerador estoqueGer : geradorLogado.getEstoques()){
            if(estoqueGer.getEstoque().getMaterial().equals(pedido.getItem().getMaterial())){
                estoqueGeradorCorreto = estoqueGer;
            }
        }
        estoqueGeradorCorreto = this.adicionarQuantidadeDoEstoque(pedido, estoqueGeradorCorreto);
        DAO<PedidoReciclagem> daoPedido = new DAO<PedidoReciclagem>(pedido);
        daoPedido.excluir();
        DAOGerador daoGer = new DAOGerador(geradorLogado);
        daoGer.alterar();
    }
    
    public List<PedidoReciclagem> pedidosGerador(Gerador geradorLogado){
        DAOGerador dao = new DAOGerador(geradorLogado);
        return dao.materiaisVendendo(geradorLogado);
    }
      
    public void cancelarVenda(){
        estoque = null;
        valorPorKG = 0.0;
        quantidadeTotal = 0.0;
        vender = false;
        RequestContext.getCurrentInstance().execute("PF('varVender').hide();");
    }
    
    public String adicionarNoEstoque(Gerador gerador){
        if(materialEscolhido == null){
            UtilMensagens.mensagemError("Material escolhido é nulo!");
            return "";
        }
        if(quantidadeDoMaterialEscolhido <= 0){
            UtilMensagens.mensagemError("Quantidade do Material escolhido é igual ou menor que 0!");
            return "";
        }
        EstoqueGerador estoqueGerador = new EstoqueGerador();
        Estoque estoque = new Estoque();
        estoque.setEstoqueGerador(estoqueGerador);
        estoqueGerador.setEstoque(estoque);
        estoque.setMaterial(getMaterialEscolhido());
        estoque.setQuantidadeMaterial(getQuantidadeDoMaterialEscolhido());
        estoqueGerador.setGerador(gerador);
        gerador.adicionarEstoqueGerador(estoqueGerador);  
        DAO<Gerador> dao = new DAO(gerador);
        dao.alterar();     
        setMaterialEscolhido(new Material());
        setQuantidadeDoMaterialEscolhido(0.0);
        UtilMensagens.mensagemInfo("Material incluído com sucesso!" );
        this.fecharModalNovoEstoque();
        return "minha_conta";
    }
    
    public void removerEstoqueGerador(EstoqueGerador obj, Gerador gerador){ 
        obj.getEstoque().setMaterial(null);
        obj.getEstoque().setEstoqueGerador(null); 
        obj.setGerador(null);
        obj.setEstoque(null);
        gerador.getEstoques().remove(obj);
        DAO<EstoqueGerador> acesso = new DAO(obj);
        acesso.excluir();   
    }
    
    /**
     * @return the novoGerador
     */
    public Gerador getNovoGerador() {
        return novoGerador;
    }

    /**
     * @param novoGerador the novoGerador to set
     */
    public void setNovoGerador(Gerador novoGerador) {
        this.novoGerador = novoGerador;
    }

    /**
     * @return the estoque
     */
    public EstoqueGerador getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(EstoqueGerador estoque) {
        this.estoque = estoque;
    }

    /**
     * @return the quantidadeMais
     */
    public double getQuantidadeMais() {
        return quantidadeMais;
    }

    /**
     * @param quantidadeMais the quantidadeMais to set
     */
    public void setQuantidadeMais(double quantidadeMais) {
        this.quantidadeMais = quantidadeMais;
    }

    /**
     * @return the materialEscolhido
     */
    public Material getMaterialEscolhido() {
        return materialEscolhido;
    }

    /**
     * @param materialEscolhido the materialEscolhido to set
     */
    public void setMaterialEscolhido(Material materialEscolhido) {
        this.materialEscolhido = materialEscolhido;
    }

    /**
     * @return the quantidadeDoMaterialEscolhido
     */
    public double getQuantidadeDoMaterialEscolhido() {
        return quantidadeDoMaterialEscolhido;
    }

    /**
     * @param quantidadeDoMaterialEscolhido the quantidadeDoMaterialEscolhido to set
     */
    public void setQuantidadeDoMaterialEscolhido(double quantidadeDoMaterialEscolhido) {
        this.quantidadeDoMaterialEscolhido = quantidadeDoMaterialEscolhido;
    }

    /**
     * @return the quantidadeTotal
     */
    public double getQuantidadeTotal() {
        return quantidadeTotal;
    }

    /**
     * @param quantidadeTotal the quantidadeTotal to set
     */
    public void setQuantidadeTotal(double quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    /**
     * @return the valorPorKG
     */
    public double getValorPorKG() {
        return valorPorKG;
    }

    /**
     * @param valorPorKG the valorPorKG to set
     */
    public void setValorPorKG(double valorPorKG) {
        this.valorPorKG = valorPorKG;
    }

    /**
     * @return the vender
     */
    public boolean isVender() {
        return vender;
    }

    /**
     * @param vender the vender to set
     */
    public void setVender(boolean vender) {
        this.vender = vender;
    }

    /**
     * @return the geradorSelecionado
     */
    public Gerador getGeradorSelecionado() {
        return geradorSelecionado;
    }

    /**
     * @param geradorSelecionado the geradorSelecionado to set
     */
    public void setGeradorSelecionado(Gerador geradorSelecionado) {
        this.geradorSelecionado = geradorSelecionado;
    }
    
}
