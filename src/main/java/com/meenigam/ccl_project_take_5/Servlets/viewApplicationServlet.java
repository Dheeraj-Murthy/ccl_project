package com.meenigam.ccl_project_take_5.Servlets;

import com.meenigam.ccl_project_take_5.database_interaction.DBManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "viewApplicationServlet", urlPatterns = "/view_application")
public class viewApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationId = request.getParameter("id");
        try {
            ResultSet rs = DBManagement.get_row(applicationId);
            request.setAttribute("table1", rs);
            request.getRequestDispatcher("/view_application.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}