<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="./style/registerPage.css">
    <link rel="stylesheet" type="text/css" href="./style/table.css">
    <script src="https://www.w3schools.com/lib/w3.js"></script>
</head>
<% String user_type = (String) application.getAttribute("usertype"); %>
<body>
<div class="box"
     style="height: 20vh; width: 60vw">
    <h1>You are <%=user_type%>
    </h1>

    <button type="button" onclick="create_application()" style="margin: auto; padding: 10px">add application</button>
    <script type="text/javascript">
        function create_application() {
            window.location.href = "create_application.jsp";
        }
    </script>


</div>

</body>
</html>
