/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.util;

/**
 *
 * @author Rafael
 */
public class UtilError {
    
    public static String getMensagemErro(Exception e) {
      while( e.getCause() != null) {
	  e = (Exception) e.getCause();
	}
        
	String mensagem = e.getMessage();
	return mensagem;	
    
	}
    
}
