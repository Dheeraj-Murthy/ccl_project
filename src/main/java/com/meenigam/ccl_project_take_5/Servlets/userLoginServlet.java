package com.meenigam.ccl_project_take_5.Servlets;

import com.meenigam.ccl_project_take_5.database_interaction.DBManagement;
import com.meenigam.ccl_project_take_5.utils.Pair;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "userLoginServlet", urlPatterns = "/userLogin")
public class userLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) getServletContext().getAttribute("username");
        String userid = (String) getServletContext().getAttribute("userid");
        System.out.println(username + "<= username : " + userid + "<= userid");

        if (username != null) {
            try {
                Pair<ResultSet, Integer> data = DBManagement.get_applications(username, userid);
                System.out.println(data.getSecond() + "<= the number of entries supplied");
                request.setAttribute("table", data.getFirst());
                request.setAttribute("entries", data.getSecond());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("/userApplications.jsp").forward(request, response);
        } else {
            response.getWriter().println("the username is null");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employee_name = request.getParameter("employee_name");
        String employee_id = request.getParameter("employee_id");
//        DBManagement.get_employee(employee_name, employee_id);
        try {
            Pair<ResultSet, Integer> data = DBManagement.get_applications(employee_name, employee_id);
            System.out.println(data.getSecond() + "<= the number of entries supplied");
            request.setAttribute("table", data.getFirst());
            request.setAttribute("entries", data.getSecond());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getServletContext().setAttribute("username", employee_name);
        getServletContext().setAttribute("user_type", "applicant");
        getServletContext().setAttribute("userid", employee_id);
        request.getRequestDispatcher("/userApplications.jsp").forward(request, response);
    }

}