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

@WebServlet(name = "otpAuthServlet", urlPatterns = "/otpAuth")
public class otpAuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String otpSent = (String) request.getParameter("sentotp");
        String otp = (String) request.getParameter("otp");
        String phoneNumber = (String) request.getParameter("phoneNumber");
        System.out.println(otpSent + ' ' + otp + ' ' + phoneNumber);
        ResultSet rows = null;
        if (otpSent.equals(otp)) {
            try {
                Pair<ResultSet, Integer> data = DBManagement.get_applications_otp(phoneNumber);
                System.out.println(data.getSecond() + "<= the number of entries supplied");
                request.setAttribute("table", data.getFirst());
                request.setAttribute("entries", data.getSecond());
                rows = data.getFirst();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (rows != null) {
                try {
                    rows.next();
                    getServletContext().setAttribute("username", rows.getString("employee_name"));
                    getServletContext().setAttribute("user_type", "applicant");
                    getServletContext().setAttribute("userid", rows.getString("employee_id"));
                    request.getRequestDispatcher("/userApplications.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                getServletContext().setAttribute("phoneNumber", phoneNumber);
                getServletContext().setAttribute("otp", otpSent);
                request.setAttribute("message", "Invalid otp");
                request.getRequestDispatcher("/otpLogin.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("otp", otpSent);
            request.setAttribute("message", "Invalid otp");
            request.getRequestDispatcher("/otpLogin.jsp").forward(request, response);
        }
    }
}