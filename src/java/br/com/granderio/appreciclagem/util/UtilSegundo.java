/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.util;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class UtilSegundo {
      
    public static boolean contarSegundos(int quantidade) throws InterruptedException{
        //Var de Inicio
        int inicio = 0;
        
        if(quantidade <= 0){
            System.out.println("A quantidade pedida é igual ou menor que 0, o método não será executado!");
            return false;
        }
        
        while(quantidade >= inicio){
            inicio++;
            System.out.println("Var contadora = " + inicio);
            Thread.sleep(1000);
        }
        System.out.println("O contador chegou ao Segundo desejado, ambos = " + quantidade);
        return true;
    }
}
