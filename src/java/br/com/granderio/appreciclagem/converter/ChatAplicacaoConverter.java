/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.converter;


import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
@FacesConverter(value="chatAplicacaoConverter")
public class ChatAplicacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ChatAplicacao retorno = new ChatAplicacao();
        long id = Long.valueOf(value);
        DAO<ChatAplicacao> acesso = new DAO(retorno);
        retorno = acesso.buscarChatAplicacao(id);
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ChatAplicacao chatAplica = (ChatAplicacao) value;
        return String.valueOf(chatAplica.getIdChatAplicacao());
    }

    

}
