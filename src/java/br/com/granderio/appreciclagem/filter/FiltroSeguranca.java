/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.filter;

import br.com.granderio.appreciclagem.controller.ControladorLogado;
import br.com.granderio.appreciclagem.model.Administrador;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Filtro de Segunraça do Aplicativo de Reciclagem
 * Feito por Rafael Nunes em 21/03/2019
 * rafaelnunes.inf@gmail.com
 * @programador Desenvolvedor Java 
 */
@WebFilter(urlPatterns="/admin/")
public class FiltroSeguranca implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Começou o filtro");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        //URL do Context
        String urlPadrao = httpRequest.getContextPath();      
        //URL Completa
        String urlCompleta = httpRequest.getRequestURL().toString();
      
        ControladorLogado controleLogin = (ControladorLogado) session.getAttribute("controladorLogado");
        
//        if(urlCompleta.contains("/admin/index") || urlCompleta.contains("/admin/legislacao") || urlCompleta.contains("/admin/material") || 
//                urlCompleta.contains("admin/materialLegislacao")){
//            if(controleLogin == null  ){
//                httpResponse.sendRedirect(urlPadrao + "/admin/logar.xhtml");
//            } else if(controleLogin.getAdminLogado() == null){
//                httpResponse.sendRedirect(urlPadrao + "/admin/logar.xhtml");
//            }
//        }
//        
//        if(urlCompleta.contains("/minha_conta.xhtml")){
//            if(controleLogin == null  ){
//                httpResponse.sendRedirect(urlPadrao + "/index.xhtml");
//            } else if(controleLogin.getGeradorLogado() == null && controleLogin.getRecicladorLogado() == null && controleLogin.getTransportadorLogado() == null){
//                    httpResponse.sendRedirect(urlPadrao + "/index.xhtml");
//            }
//        }
//        
//        if(urlCompleta.contains("/registrar.xhtml")){
//            if(controleLogin != null){
//                if(controleLogin.getGeradorLogado() != null || controleLogin.getRecicladorLogado() != null || controleLogin.getTransportadorLogado() != null)
//                    httpResponse.sendRedirect(urlPadrao + "/minha_conta.xhtml");
//            }
//        }
        //Faz o Filtro
//        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Fim do filtro");
    }

}
