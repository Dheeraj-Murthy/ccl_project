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
    <form action="${pageContext.request.contextPath}/register" method="post">
        <h2>Register Page</h2>
        <div class="to_right">
            <label for="email">Please enter your email: </label>
            <input type="email" id="email" name="email">
        </div>
        <%--@declare id="username"--%>

        <div class="to_right">
            <label for="username">Please enter your username: </label>
            <input type="text" id="username" name="username">
        </div>
        <div class="to_right">
            <label for="age">Please enter your age: </label>
            <input type="number" id="age" name="age">
        </div>
        <div class="to_right">
            <label for="password">Please enter your password: </label>
            <input type="password" id="password" name="password">
        </div>
        <div class="to_right">
            <label for="confirmPassword">Please re-enter your password: </label>
            <input type="password" id="confirmPassword" name="confirmPassword">
        </div>

        <button type="submit">Create Account</button>
    </form>
</div>
</body>
</html>