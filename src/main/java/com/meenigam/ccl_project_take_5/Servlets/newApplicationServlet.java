package com.meenigam.ccl_project_take_5.Servlets;

import com.meenigam.ccl_project_take_5.database_interaction.DBManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "newApplicationServlet", urlPatterns = "/add_application")
public class newApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Sorry, I don't know how you got here!!! \nYou shouldn't be here.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employee_name = request.getParameter("employee_name");
        String employee_id = request.getParameter("employee_id");
        String patient_name = request.getParameter("patient_name");
        String relation = request.getParameter("relation");
        String patient_uhid = request.getParameter("patient_uhid");
        String disease_type = request.getParameter("disease_type");
        String treatment_type = request.getParameter("treatment_type");
        String phone_number = request.getParameter("phone_number");

        try {
            DBManagement.create_application(employee_name, employee_id, patient_name, relation, patient_uhid, disease_type, treatment_type, phone_number);
            response.sendRedirect("/listPage.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}