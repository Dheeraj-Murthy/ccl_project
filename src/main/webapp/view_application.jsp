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
     style="overflow: scroll; width: 90vw; font-size: 0.8rem; justify-content: start; height: fit-content; max-height: 80vh; min-height: 10vh;">
    <h1>You are <%=user_type%>
    </h1>
    <%
        ResultSet rs = (ResultSet) request.getAttribute("table1");
        int colCount = rs.getMetaData().getColumnCount();
    %>
    <table id="myTable">
        <thead>
        <tr>
            <th>Attribute</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <% if (rs.next()) { %>

        <% for (int i = 1; i <= colCount; i++) {
            String colValue = rs.getString(i);
            String colName = rs.getMetaData().getColumnName(i);
        %>
        <tr>
            <td><%=colName%>
            </td>
            <td><%= colValue %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>No data available. You have no new entries. WEll DONE ig...</p>
    <% } %>
</div>

<div class="to_right">
    <button onclick="window.location.href='/approve?id=<%= rs.getString("application_id") %>'">approve selected</button>
    <button onclick="window.location.href='/reject?id=<%= rs.getString("application_id") %>'">reject selected</button>
</div>
</body>
</html>
