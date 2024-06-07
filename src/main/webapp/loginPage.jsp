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
    <form action="/login" method="post">
        <h2>Login Page</h2>
        <div class="to_right">
            <label for="email_or_username">Please enter your email / username </label>
            <input type="text" id="email_or_username" name="email_or_username">
        </div>

        <div class="to_right">
            <label for="password">Please enter your password: </label>
            <input type="password" id="password" name="password">
        </div>

        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>