<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="./style/registerPage.css">
    <link rel="stylesheet" type="text/css" href="./style/table.css">
</head>
<body>
<script>0</script>
<div class="box">
    What Action do you wish to perform:
    <div class="actions">
        <button class="logout" onclick="window.location.href='/loginPage.jsp'">Login as User</button>
        <button class="logout" style="height: 2rem" onclick="window.location.href='/userLogin.jsp'">Login as Applicant</button>
    </div>

</div>
</body>
</html>