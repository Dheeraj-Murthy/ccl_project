package com.meenigam.ccl_project_take_5.Servlets;

import com.meenigam.ccl_project_take_5.database_interaction.DBManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registerPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String username = request.getParameter("username");
        String employeeId = request.getParameter("employee_id");
        String user_type = request.getParameter("user_type");


        if (email == null || password == null || confirmPassword == null || username == null || employeeId == null || user_type == null) {
            request.setAttribute("error", "Please fill all the required fields");
            request.getRequestDispatcher("/registerPage.jsp").forward(request, response);
        } else if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/registerPage.jsp").forward(request, response);
        } else {
            try {
                DBManagement.add_user(username, password, user_type, employeeId, email);
                request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                if (Objects.equals(e.getMessage(), "java.lang.Exception: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'dcompany2004@gmail.com' for key 'user_table.email_id'"))
                    request.setAttribute("error", "User already exists");
                else request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/registerPage.jsp").forward(request, response);
            }

        }

    }
}