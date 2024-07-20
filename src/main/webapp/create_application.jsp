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
        <h2 style="text-align: center; margin-bottom: 20px">Create Application</h2>

        <div class="to_right">
            <label for="employee_name">Enter the name of the Employee:</label>
            <input
                    type="text"
                    name="employee_name"
                    id="employee_name"
                    required
            />
        </div>

        <div class="to_right">
            <label for="employee_id">Enter Employee id:</label>
            <input
                    type="text"
                    name="employee_id"
                    id="employee_id"
                    required
            />
        </div>

        <div class="to_right">
            <label for="patient_name">Enter the name of the patient:</label>
            <input
                    type="text"
                    name="patient_name"
                    id="patient_name"
                    required
            />
        </div>

        <div class="to_right">
            <label for="relation">
                Select the relation of patient to the Employee:
            </label>
            <select
                    name="relation"
                    id="relation"
                    style="height: 1.5rem; padding: 0 10px;"
            >
                <option value="SELF">SELF</option>
                <option value="SPOUSE">Spouse</option>
                <option value="CHILD1">Child 1</option>
                <option value="CHILD2">Child 2</option>
            </select>
        </div>

        <div class="to_right">
            <label for="patient_uhid">Enter the patient CPRMS Id:</label>
            <input
                    type="text"
                    name="patient_uhid"
                    id="patient_uhid"
                    required
            />
        </div>

        <div class="to_right">
            <label for="disease_type">Select the disease type of patient:</label>
            <select
                    name="disease_type"
                    id="disease_type"
                    style="height: 1.5rem; padding: 0 10px;"
            >
                <option value="critical">Critical</option>
                <option value="non_critical">Non Critical</option>
            </select>
        </div>

        <div class="to_right">
            <label for="treatment_type">
                Select the treatment type of patient:
            </label>
            <select
                    name="treatment_type"
                    id="treatment_type"
                    style="height: 1.5rem; padding: 0 10px;"
            >
                <option value="IPD">IPD</option>
                <option value="OPD">OPD</option>
            </select>
        </div>

        <div class="to_right">
            <label for="amount_claimed">Enter the amount to be claimed:</label>
            <input
                    type="number"
                    minlength="10"
                    maxlength="10"
                    name="amount_claimed"
                    id="amount_claimed"
                    required
            />
        </div>

        <div class="to_right">
            <label for="phone_number">Enter Employee phone number:</label>
            <input
                    type="text"
                    minlength="10"
                    maxlength="10"
                    name="phone_number"
                    id="phone_number"
                    required
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

        <button type="submit" style="height: 2rem">Add Application</button>
    </form>

    <script>
        function disableEmptyInputs(form) {
            var controls = form.elements;
            for (var i=0, iLen=controls.length; i<iLen; i++) {
                if (controls[i] === '') {
                    alert("Please make sure to fill all fields");
                }
            }
        }
    </script>
</div>
</body>
</html>

