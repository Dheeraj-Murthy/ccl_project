package com.meenigam.ccl_project_take_5.Servlets;

import com.meenigam.ccl_project_take_5.database_interaction.DBManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "listPageServlet", urlPatterns = "/listPage")
public class listPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("you have reached the list page Servlet");
        String type = (String) getServletContext().getAttribute("usertype");
        String q = null;
        ResultSet rs = null;
        if (type == null) {
            throw new RuntimeException("type is null");
        } else if (type.equals("observation_desk")) {
            q = "REGISTERED";
        } else if (type.equals("pharmacist")) {
            q = "PHARMACY";
        } else if (type.equals("accountant")) {
            q = "ACCOUNTS";
        } else if (type.equals("CMO")) {
            q = "CMO";
        } else if (type.equals("CMS")) {
            q = "CMS";
        } else if (type.equals("Data_entry_operator")) {
            q = "data_entry_operator";
        } else {
            throw new RuntimeException("unknown type");
        }
        try {
            rs = DBManagement.retrieve_rows(q);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("table", rs);
        request.getRequestDispatcher("/listPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}