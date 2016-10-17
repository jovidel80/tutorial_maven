package com.joseoliveros;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//@WebServlet(urlPatterns = "/test")
public class SimpleServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("name");
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        if (userName != "" && userName != null) {
            session.setAttribute("savedUserName", userName);
            context.setAttribute("savedUserName", userName);
        }
        
        out.println("<br/>request parameter has username as " + userName);
        out.println("<br/>session parameter has username as " + session.getAttribute("savedUserName"));
        out.println("<br/>context parameter has username as " + context.getAttribute("savedUserName"));

        ServletConfig servletConfig = getServletConfig();
        out.println("<br/>el valor de parametros de init es, email: " + servletConfig.getInitParameter("email"));
        out.println("<br/>el valor de parametros de init es, hijos: " + servletConfig.getInitParameter("hijos"));
        Enumeration enumeration = servletConfig.getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            out.println("<br/>valor del enumerado: " + enumeration.nextElement());
        }
        out.println("<br/>el nombre del servlet es: " + servletConfig.getServletName());
        ServletContext context1 = servletConfig.getServletContext();
        out.println("<br/>volvi a recuperar el servletcontext pero a traves del servletConfig: " + context1.getAttribute("savedUserName"));
    }

    
}
