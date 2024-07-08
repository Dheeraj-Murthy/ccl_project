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
<script>0</script>
<div class="box">
    <form action="/userLogin" method="post">
        <h1 style="text-align: center; padding: 20px;">Find my Application!</h1>
        <div class="to_right">
            <label for="employee_name">Please enter your name </label>
            <input type="text" id="employee_name" name="employee_name">
        </div>

        <div class="to_right">
            <label for="employee_id">Please enter your employee id </label>
            <input type="text" id="employee_id" name="employee_id">
        </div>
        <p style="align-self: center; text-align: center; color: red">${error}</p>

        <div style="flex-direction: row; grid-template-columns: 1fr 1fr">
            <button type="button" style="width: 30%; height: 2rem;" onclick="window.location.href='index.jsp'">Back</button>
            <button type="submit" style="width: 30%; height: 2rem;">Find</button>
        </div>
    </form>
</div>
</body>
</html>