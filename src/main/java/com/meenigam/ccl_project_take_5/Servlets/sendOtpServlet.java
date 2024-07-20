package com.meenigam.ccl_project_take_5.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.meenigam.ccl_project_take_5.OtpService.OtpService.sendOtp;

@WebServlet("/sendOtp")
public class sendOtpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = (String) request.getParameter("phone_number");
        phoneNumber = "+91" + phoneNumber;
        System.out.println(phoneNumber);
        String otp = sendOtp(phoneNumber);
        System.out.println();
        request.setAttribute("otp", otp);
        request.setAttribute("phoneNumber", phoneNumber);
        request.getRequestDispatcher("otpLogin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
