<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0"
    />
    <link
            rel="stylesheet"
            href="./style/registerPage.css"
    />
    <title>Create Application</title>
</head>
<body>
<div class="box">
    <form
            action="${pageContext.request.contextPath}/add_application"
            method="post"
    >
        <h2 style="text-align: center">Create Application</h2>

        <div class="to_right">
            <label for="employee_name">Enter the name of the Employee:</label>
            <input
                    type="text"
                    name="employee_name"
                    id="employee_name"
            />
        </div>

        <div class="to_right">
            <label for="employee_id">Enter employee id:</label>
            <input
                    type="text"
                    name="employee_id"
                    id="employee_id"
            />
        </div>

        <div class="to_right">
            <label for="patient_name">Enter the name of the patient:</label>
            <input
                    type="text"
                    name="patient_name"
                    id="patient_name"
            />
        </div>

        <div class="to_right">
            <label for="relation">
                Select the relation of patient to the employee:
            </label>
            <select
                    name="relation"
                    id="relation"
            >
                <option value="SELF">SELF</option>
                <option value="SPOUSE">Spouse</option>
                <option value="CHILD1">Child 1</option>
                <option value="CHILD2">Child 2</option>
            </select>
        </div>

        <div class="to_right">
            <label for="patient_uhid">Enter the patient UHID:</label>
            <input
                    type="text"
                    name="patient_uhid"
                    id="patient_uhid"
            />
        </div>

        <div class="to_right">
            <label for="disease_type">Select the disease_type of patient:</label>
            <select
                    name="disease_type"
                    id="disease_type"
            >
                <option value="critical">Critical</option>
                <option value="non_critical">Non Critical</option>
            </select>
        </div>

        <div class="to_right">
            <label for="treatment_type">
                Select the treatment_type of patient:
            </label>
            <select
                    name="treatment_type"
                    id="treatment_type"
            >
                <option value="IPD">IPD</option>
                <option value="OPD">OPD</option>
            </select>
        </div>

        <div class="to_right">
            <label for="phone_number">Enter employee phone number:</label>
            <input
                    type="text"
                    minlength="10"
                    maxlength="10"
                    name="phone_number"
                    id="phone_number"
            />
        </div>

        <%--        <div class="to_right">--%>
        <%--            <label for="cprms_id">Enter CPRMS id:</label>--%>
        <%--            <input--%>
        <%--                    type="text"--%>
        <%--                    name="cprms_id"--%>
        <%--                    id="cprms_id"--%>
        <%--            />--%>
        <%--        </div>--%>

        <button type="submit">Add Application</button>
    </form>
</div>
</body>
</html>

