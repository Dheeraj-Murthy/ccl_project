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

<div class="box">
    <form action="${pageContext.request.contextPath}/sendOtp" method="post">
        <h1 style="text-align: center; padding: 20px;">Find my Application!</h1>
        <div class="to_right">
            <label for="phone_number">Please enter your phone number: </label>
            <input type="text" maxlength="10" minlength="10" id="phone_number" name="phone_number">
        </div>
        <div style="flex-direction: row; grid-template-columns: 1fr 1fr 1fr">
            <button type="button" onclick="window.location.href='index.jsp'">Back</button>
            <button type="button" onclick="window.location.href='/'">use Credentials</button>
            <button type="submit">Send OTP</button>
        </div>
    </form>
</div>
</body>
</html>