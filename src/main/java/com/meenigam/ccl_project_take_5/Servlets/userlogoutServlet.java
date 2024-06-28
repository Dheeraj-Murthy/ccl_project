package com.meenigam.ccl_project_take_5.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet(name = "userlogoutServlet", urlPatterns = "/userlogout")
public class userlogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().setAttribute("username", null);
        getServletContext().setAttribute("userid", null);
        getServletContext().setAttribute("usertype", null);
        getServletContext().getRequestDispatcher("/userLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}