<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="./style/registerPage.css">
</head>
<body>
<div class="box">
    <h1>Register Page</h1> <p style="align-self: center; text-align: center; color: red">${error}</p>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <div class="to_right">
            <label for="email">Please enter your email: </label>
            <input type="email" id="email" name="email">
        </div>
        <hr style="border-style: none; opacity: 0%">
        <div class="to_right">
            <label for="username">Please enter your username: </label>
            <input type="text" id="username" name="username">
        </div>
        <hr style="border-style: none; opacity: 0">
        <div>
            <label for="employee_id">Please enter your employee id: </label>
            <input type="text" id="employee_id" name="employee_id">
        </div>
        <hr style="border-style: none; opacity: 0%">
        <div class="to_right">
            <label for="user_type">Please enter your user_type: </label>
            <select id="user_type" name="user_type" class="select">
                <option value="reception">Reception</option>
                <option value="observation_desk">Observation Desk</option>
                <option value="pharmacist">Pharmacist</option> <option value="accountant">Accountant</option>
                <option value="CMO">CMO</option> <option value="CMS">CMS</option>
                <option value="data_entry_operator">data_entry_operator</option>
            </select>
        </div>
        <hr style="border-style: none; opacity: 0">
        <div class="to_right">
            <label for="password">Please enter your password: </label>
            <input type="password" id="password" name="password">
        </div>
        <hr style="border-style: none; opacity: 0%">
        <div class="to_right">
            <label for="confirmPassword">Please re-enter your password: </label>
            <input type="password" id="confirmPassword" name="confirmPassword">
        </div>
        <hr style="border-style: none; opacity: 0%">
        <div style="flex-direction: row;">
            <button type="button" style="width: 60%; height: 2rem;" onclick="window.location.href='/listPage'">go Back</button>
            <button type="submit" style="width: 60%; height: 2rem;">Create Account</button>
        </div>
        <script>
            function register() {
                window.location.href = "loginPage.jsp";
            }
        </script>
    </form>
</div></body></html>