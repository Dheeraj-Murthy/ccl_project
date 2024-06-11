package com.meenigam.ccl_project_take_5.Servlets;

import com.meenigam.ccl_project_take_5.database_interaction.DBManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Hello user you are here by GET" + "!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("email_or_username");
        String password = request.getParameter("password");
        try {
            String[] data = Objects.requireNonNull(DBManagement.retrieve_user(username, password));
            String userid = data[0];
            String usertype = data[1];
            getServletContext().setAttribute("userid", userid);
            getServletContext().setAttribute("usertype", usertype);
            System.out.println("are we here");
//            response.getWriter().write("Hello " + username + "!" + "You are logged in!\nYour userId is " + userid + "! \nAnd you are a " + usertype);
//            request.getRequestDispatcher("/listPage").forward(request, response);
            response.sendRedirect("/listPage");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "user not found");
            request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
        }
//        response.getWriter().write("Hello " + username + "!");
    }
}