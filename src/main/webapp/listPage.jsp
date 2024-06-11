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
        ResultSet rs = (ResultSet) request.getAttribute("table");
        if (rs != null) {
            int colCount = rs.getMetaData().getColumnCount();
    %>
    <table id="myTable">
        <thead>
        <tr>
            <% for (int i = 1; i <= colCount; i++) {
                String colName = rs.getMetaData().getColumnName(i);
            %>
            <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(<%=i%>)')"><%= colName %> &#8597;</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <% while (rs.next()) { %>
        <tr class="item" onclick="window.location.href='view_application?id=<%= rs.getString("application_id") %>'">

            <% for (int i = 1; i <= colCount; i++) {
                String colValue = rs.getString(i);
            %>
            <td><%= colValue %>
            </td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>No data available. You have no new entries. WEll DONE ig...</p>
    <% } %>
</div>

<div class="to_right">
    <%
        System.out.println(user_type);
        if (user_type == null)
            ;
        else if (user_type.equals("Data_entry_operator")) { %>
    <%--    <button>approve selected</button>--%>
    <%--    <button>reject selected</button>--%>
    <% } else if (user_type.equals("observation_desk")) { %>
    <button type="button" onclick="create_application()">add application</button>
    <script type="text/javascript">
        function create_application() {
            window.location.href = "create_application.jsp";
        }
    </script>
    <% } else if (user_type.equals("pharmacist")) { %>
    <%--    <button>approve selected</button>--%>
    <%--    <button>reject selected</button>--%>
    <% } else if (user_type.equals("accountant")) { %>
    <%--    <button>approve selected</button>--%>
    <%--    <button>reject selected</button>--%>
    <% } else if (user_type.equals("CMO")) { %>
    <%--    <button>approve selected</button>--%>
    <%--    <button>reject selected</button>--%>
    <% } else if (user_type.equals("CMS")) { %>
    <%--    <button>approve selected</button>--%>
    <%--    <button>reject selected</button>--%>
    <% } %>
</div>
</body>
</html>
