package com.meenigam.ccl_project_take_5.Servlets;

import com.meenigam.ccl_project_take_5.database_interaction.DBManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet(name = "approveServlet", urlPatterns = "/approve")
public class approveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String type = (String) getServletContext().getAttribute("usertype");
        System.out.println(type + "hello this message is brought to you by the approveServlet");
        if (type.equals("Data_entry_operator")) {
            type = "data_entry_operator";
        }
        try {
            DBManagement.approve(type, id);
            response.sendRedirect("/listPage");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}