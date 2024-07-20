<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="./style/registerPage.css">
</head>
<body>
<%
    String sentotp = (String) request.getAttribute("otp");
    String phone_number = (String) request.getAttribute("phoneNumber");
%>

<div class="box">
    <form action="/otpAuth" method="post">
        <input type="hidden" name="sentotp" id="sentotp" value=<%=sentotp%>>
        <input type="hidden" name="phoneNumber" id="phoneNumber" value=<%=phone_number%>>
        <h1 style="text-align: center; padding: 20px;">Find my Application!</h1>
        <div class="to_right">
            <p>Please enter the otp sent to the phone number: <%= phone_number%></p>
        </div>
        <% String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <p style="align-self: center; text-align: center; color: red"><%=message%></p>
        <% } %>
        <div class="to_right">
            <label for="otp">Please enter your otp</label>
            <input type="text" id="otp" name="otp">
        </div>
        <p style="align-self: center; text-align: center; color: red"></p>
        <div style="flex-direction: row; grid-template-columns: 1fr 1fr 1fr">
            <button type="button" onclick="window.location.href='index.jsp'">Back</button>
            <button type="button" onclick="window.location.href='/'">use Credentials</button>
            <button type="submit">Find</button>
        </div>
    </form>
</div>
</body>
</html>