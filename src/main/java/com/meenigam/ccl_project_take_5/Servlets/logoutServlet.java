package com.meenigam.ccl_project_take_5.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class logoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().setAttribute("userid", null);
        getServletContext().setAttribute("usertype", null);
        getServletContext().setAttribute("username", null);
        getServletContext().getRequestDispatcher("/loginPage.jsp").forward(request, response);
//        response.sendRedirect("/login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}