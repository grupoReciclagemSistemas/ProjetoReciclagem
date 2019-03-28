/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class UtilEmail {
    
    public static void enviarEmailSimples(String tituloDoEmail, String remetente, String mensagem) throws EmailException{
        SimpleEmail email = new SimpleEmail();
        //Falta o HOST
        email.setFrom(remetente);
        email.addTo("rafaelnunes.inf@gmail.com");
        email.setMsg(mensagem);
        email.setSubject(tituloDoEmail);
        email.send();
    }

}
