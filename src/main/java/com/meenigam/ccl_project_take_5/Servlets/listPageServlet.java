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
import java.sql.SQLException;

@WebServlet(name = "listPageServlet", urlPatterns = "/listPage")
public class listPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("you have reached the list page Servlet");
        String type = (String) getServletContext().getAttribute("usertype");
        String username = (String) getServletContext().getAttribute("username");
        String q = null;
        ResultSet rs = null;
        if (type == null) {
            String userid = (String) getServletContext().getAttribute("userid");
            if (userid == null) {
                response.sendRedirect("loginPage.jsp");
                return;
            } else {
                throw new RuntimeException("usertype is null but userid is not null here is the userid: " + userid);
            }
        } else if (type.equals("reception")) {
            System.out.println("reception user beign sent to reception");
            try {
                request.getRequestDispatcher("/receptionPage.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        } else if (type.equals("super_user")) {
            try {
                Pair<ResultSet, Integer> data = DBManagement.retrieve_rows("super_user");
                Pair<Integer, Integer> cunt = DBManagement.get_count(username);
                System.out.println(username + " <= this is the user name ");
                request.setAttribute("table", data.getFirst());
                int rows = data.getSecond();
                request.setAttribute("entries", rows);
                request.setAttribute("accepted", cunt.getFirst());
                request.setAttribute("rejected", cunt.getSecond());
                System.out.println(cunt.getFirst() + ' ' + cunt.getSecond());
                System.out.println(rows);
                request.getRequestDispatcher("/super_userPage.jsp").forward(request, response);
                return;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("unknown type");
        }
        try {
            Pair<ResultSet, Integer> data = DBManagement.retrieve_rows(q);
            Pair<Integer, Integer> cunt = DBManagement.get_count(username);
            System.out.println(username + " <= this is the user name ");
            request.setAttribute("table", data.getFirst());
            int rows = data.getSecond();
            request.setAttribute("entries", rows);
            request.setAttribute("accepted", cunt.getFirst());
            request.setAttribute("rejected", cunt.getSecond());
            System.out.println(cunt.getFirst() + ' ' + cunt.getSecond());
            System.out.println(rows);
            if (!type.equals("reception")) {
                request.getRequestDispatcher("/listPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String type = (String) getServletContext().getAttribute("usertype");
            String username = (String) getServletContext().getAttribute("username");
            String q = request.getParameter("type");
            ResultSet rs = null;
            Pair<ResultSet, Integer> data = DBManagement.retrieve_rows(q);
            Pair<Integer, Integer> cunt = DBManagement.get_count(username);
            System.out.println(username + " <= this is the user name ");
            request.setAttribute("table", data.getFirst());
            int rows = data.getSecond();
            request.setAttribute("entries", rows);
            request.setAttribute("accepted", cunt.getFirst());
            request.setAttribute("rejected", cunt.getSecond());
            System.out.println(cunt.getFirst() + ' ' + cunt.getSecond());
            System.out.println(rows);
            if (!type.equals("reception")) {
                request.getRequestDispatcher("/super_userPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}