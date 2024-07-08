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
<% String user_type = (String) application.getAttribute("usertype");
    ResultSet rs = (ResultSet) request.getAttribute("table1");
    String application_id = (String) request.getAttribute("applicationId");
    System.out.println(application_id + "<= this is the application id");

%>
<body>

<div class="dashboard" style="grid-template-columns: 1fr 2fr 0.5fr 0.5fr;">
<%--    <div class="element cdash" onclick="window.location.reload()"><span id="span">&#8801;</span> Dashboard</div>--%>
<%--    <input name="search" id="search" type="text" placeholder="Search...">--%>

    <% if (user_type != null) { %>
    <div class="element cdash" onclick="window.location.href='/listPage'"><span class="span">&#8801;</span> Dashboard</div>
    <h1 style="margin: auto">Application</h1>
    <button onclick="window.location.href='/listPage'" id="back" class="logout">back</button>
    <button type="button" onclick="window.location.href='/logout'" class="logout">Logout</button>
    <% } else { %>
    <div class="element cdash" onclick="window.location.href='/userLogin'"><span class="span">&#8801;</span> Dashboard</div>
    <h1 style="margin: auto">Application</h1>
    <button onclick="window.location.href='/userLogin'" id="back" class="logout">Back</button>
    <button type="button" onclick="window.location.href='/userlogout'" class="logout">Logout</button>
     <% } %>
</div>
<div class="dash">
    <% if (user_type != null && !user_type.equals("super_user")) {
        System.out.println(user_type+ "<= this is the user type");
    %>
    <div class="ele butt" style="grid-column: 2">
        <button onclick="window.location.href='/approve?id=<%= application_id %>'" class="buttoninbox">approve selected
        </button>
    </div>
    <div class="ele butt" style="grid-column: 3">
        <button onclick="window.location.href='/reject?id=<%= application_id %>'" class="buttoninbox">reject selected
        </button>
    </div>
    <% } %>
</div>

<div class="box"
     style="overflow: scroll; width: 90vw; font-size: 0.8rem; justify-content: start; height: fit-content; max-height: 80vh; min-height: 10vh;">
    <h1><% if (user_type != null) { %><!--You are <%=user_type%> --> <% } else
    {
        String user_name = (String) application.getAttribute("username");
    %> Hello <%=user_name%> <% } %>
    </h1>
    <div id="tablebox">
        <%

            int colCount = rs.getMetaData().getColumnCount();
            ResultSet his = (ResultSet) request.getAttribute("table2");
            int hcols = his.getMetaData().getColumnCount();
        %>
        <table id="myTable" class="t1">
            <caption><h4>Application Details</h4></caption>
            <thead>
            <tr>
                <th>Attribute</th>
                <th>Value</th>
            </tr>
            </thead>
            <tbody>
            <% if (rs.next()) { %>

            <% for (int i = 2; i < colCount; i++) {
                String colValue = rs.getString(i);
                String colName = rs.getMetaData().getColumnName(i);
                switch (colName) {
                    case "application_id": colName = "Bill Id"; break;
                    case "employee_name": colName = "Employee Name"; break;
                    case "employee_id": colName = "Employee Id"; break;
                    case "patient_name": colName = "Patient Name"; break;
                    case "relation_to_employee": colName = "Relation to Employee"; break;
                    case "application_date": colName = "Registration Date"; break;
                    case "application_status": colName = "Application Status"; break;
//                    case "patient_uhid": colName = "Bill Id"; break;
                    case "amount_claimed": colName = "Bill Amount"; break;
                    case "disease_type": colName = "Disease Type"; break;
                    case "treatment_type": colName = "Treatment Type"; break;
                    case "phone_number": colName = "Phone Number"; break;
                    case "cprms_id": colName = "CPRMS Id"; break;
                }
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

        <table id="myTable" class="widthup">
            <caption><h4>Application History</h4></caption>
            <thead>
            <tr>
                <% for (int i = 1; i <= hcols; i++) {
                    String colName = his.getMetaData().getColumnName(i);
                    switch (colName) {
                        case "application_id": colName = "Bill Id"; break;
                        case "Processed_by": colName = "Processed By"; break;
                        case "Date": colName = "Date"; break;
                        case "verdict": colName = "Verdict"; break;
                    }
                %>
                <th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(<%=i%>)')"><%= colName %></th>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <% while (his.next()) { %>
            <tr>

                <% for (int i = 1; i <= hcols; i++) {
                    String colValue = his.getString(i);
                %>
                <td><%= colValue %>
                </td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>

    </div>
</div>

<div class="to_right">
    <%--    <button onclick="window.location.href='/approve?id=<%= rs.getString("application_id") %>'">approve selected</button>--%>
    <%--    <button onclick="window.location.href='/reject?id=<%= rs.getString("application_id") %>'">reject selected</button>--%>
</div>
<%--<div class="to_right">--%>
<%--    <button onclick="window.location.href='/listPage'" id="back">back</button>--%>
<%--</div>--%>
</body>
</html>
