<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="./style/registerPage.css">
    <link rel="stylesheet" type="text/css" href="./style/table.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://www.w3schools.com/lib/w3.js"></script>
    <script type="text/javascript">
        function create_application() {
            window.location.href = "create_application.jsp";
        }

        function logout() {
            window.location.href = "/logout";
        }
    </script>

</head>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

    String user_name = (String) application.getAttribute("username");
    String user_type = (String) application.getAttribute("usertype");
    String user_id = (String) application.getAttribute("userid");
    if (user_type == null || user_id == null) {
        System.out.println("user id or type is null");
        response.sendRedirect("/loginPage.jsp");
        return;
    }
    System.out.println("given below is the user id of the individual");
    System.out.println(user_id);
%>
<body>

<div class="dashboard">
    <div class="element cdash" onclick="window.location.reload()"><span class="span">&#8801;</span> Dashboard</div>
    <input name="search" id="search" type="text" placeholder="Search...">
    <button type="button" onclick="logout()" class="logout">Logout</button>
</div>
<div class="dash">
    <div class="ele" style="grid-column: 1">
        <span>Pending applications</span>
        <span>${entries}</span>
    </div>
    <div class="elesup">
        <form method="post" action="${pageContext.request.contextPath}/listPage">
            <label for="type">application status:</label>
            <select id="type" name="type" class="select2">
                <option value="super_user">All entries</option>
                <option value="REGISTERED">Under Scrutiny</option>
                <option value="PHARMACY">Pharmacy</option>
                <option value="ACCOUNTS">Accounts</option>
                <option value="CMO">CMO</option>
                <option value="CMS">CMS</option>
                <option value="data_entry_operator">Data entry Operator</option>
                <option value="APPROVED">Passed</option>
            </select>
            <button type="submit">submit</button>
        </form>
    </div>

    <div class="ele" style="grid-column: 4">
        <span>Register User</span>
        <button name="register" class="logout" type="button" onclick="window.location.href='/registerPage.jsp'">Register
            Users
        </button>
    </div>
    <%--    <div class="ele" style="grid-column: 4">--%>
    <%--        <span>Applications rejected</span>--%>
    <%--        <span>${rejected}</span>--%>
    <%--    </div>--%>
</div>

<div class="box"
     style="overflow: scroll; width: 90vw; font-size: 0.8rem; justify-content: start; height: fit-content; max-height: 80vh; min-height: 20vh;">
    <h1 style="margin: 0 0 10px 0;">Hello, <%=user_name%>!
    </h1>
    <%
        ResultSet rs = (ResultSet) request.getAttribute("table");
        if (rs != null) {
            int colCount = rs.getMetaData().getColumnCount();
    %>
    <table id="myTable">
        <thead>
        <tr>
            <%
                for (int i = 2; i < colCount; i++) {
                    String colName = rs.getMetaData().getColumnName(i);
                    switch (colName) {
                        case "bill_number":
                            ;
                        case "application_id":
                            colName = "Bill Id";
                            break;
                        case "employee_name":
                            colName = "Employee Name";
                            break;
                        case "employee_id":
                            colName = "Employee Id";
                            break;
                        case "patient_name":
                            colName = "Patient Name";
                            break;
                        case "relation_to_employee":
                            colName = "Relation to Employee";
                            break;
                        case "application_date":
                            colName = "Registration Date";
                            break;
                        case "application_status":
                            colName = "Application Status";
                            break;
                        case "amount_claimed":
                            colName = "Bill Amount";
                            break;
                        case "disease_type":
                            colName = "Disease Type";
                            break;
                        case "treatment_type":
                            colName = "Treatment Type";
                            break;
                        case "phone_number":
                            colName = "Phone Number";
                            break;
                        case "cprms_id":
                            colName = "CPRMS Id";
                            break;
                        case "rejected_from":
                            colName = "Shortfell from";
                            break;
                    }
            %>
            <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(<%=i%>)')"><%= colName %> &#8597;</th>
            <% } %>
        </tr>
        </thead>
        <tbody id="tbody">
        <% while (rs.next()) { %>
        <tr class="item" onclick="window.location.href='view_application?id=<%= rs.getString("application_id") %>'">

            <%
                for (int i = 2; i < colCount; i++) {
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

<%--<div class="to_right">--%>
<%--    <%--%>
<%--        System.out.println(user_type);--%>
<%--        if (user_type == null)--%>
<%--            ;--%>
<%--        else if (user_type.equals("Data_entry_operator")) { %>--%>
<%--    &lt;%&ndash;    <button>approve selected</button>&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <button>reject selected</button>&ndash;%&gt;--%>
<%--    <% } else if (user_type.equals("reception")) {%>--%>
<%--    <button type="button" onclick="create_application()">add application</button>--%>
<%--    <% } %>--%>
<%--    &lt;%&ndash;    <button type="button" onclick="logout()">logout</button>&ndash;%&gt;--%>
<%--</div>--%>

<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $("#search").on("keyup", function () {--%>
<%--            let entireValue = $("#search").val().toLowerCase();--%>

<%--            $("#tbody tr").each(function () {--%>
<%--                let rowText = $(this).text().toLowerCase();--%>
<%--                // Show all rows if entire search is empty--%>
<%--                let matchEntire = entireValue === "";--%>

<%--                if (entireValue !== "") {--%>
<%--                    matchEntire = rowText.indexOf(entireValue) > -1;--%>
<%--                }--%>
<%--                let matchColumn = true;--%>
<%--                $(this).toggle(matchEntire && matchColumn);--%>
<%--            });--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>
