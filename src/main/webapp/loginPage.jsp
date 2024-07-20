<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

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
    <form action="/login" method="post">
        <h1 style="text-align: center; padding: 20px;">Login Page</h1>
        <div class="to_right">
            <label for="email_or_username">Please enter your email / username </label>
            <input type="text" id="email_or_username" name="email_or_username">
        </div>

        <div class="to_right">
            <label for="password">Please enter your password: </label>
            <input type="password" id="password" name="password">
        </div>
        <%--        <c:choose>--%>
        <%--            <c:when test="${empty user}">--%>
        <%--                I see!  You don't have a name.. well.. Hello no name--%>
        <%--            </c:when>--%>
        <%--            <c:otherwise>--%>
        <%--                <%@ include file="response.jsp" %>--%>
        <%--            </c:otherwise>--%>
        <%--        </c:choose>--%>
        <p style="align-self: center; text-align: center; color: red">${error}</p>

        <div style="flex-direction: row; grid-template-columns: 1fr 1fr">
            <button type="button"  onclick="window.location.href='index.jsp'">Back</button>
            <button type="submit" >Login</button>
        </div>
        <script>
            function register() {
                window.location.href = "registerPage.jsp";
            }
        </script>
    </form>
</div>
</body>
</html>