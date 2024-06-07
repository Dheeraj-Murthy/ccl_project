package com.meenigam.ccl_project_take_5;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet(name = "nameServlet", urlPatterns = "/name")
public class nameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");

        StringBuilder error = new StringBuilder();

        if (firstName == null) {
            error.append("firstName was not given");
        } else if (lastName == null) {
            error.append("last name was not given");
        } else if (age == null) {
            error.append("age was not given");
        }

        if (error.length() > 0) {
            request.setAttribute("error", error);
        } else {
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
        }

        request.getRequestDispatcher("/namePage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().append("your response has been recieved.");
        System.out.println("Servlet doPOST method is called.");
    }
}