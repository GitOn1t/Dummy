<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daily Expenses</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 960px;
            margin: 40px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-size: 28px;
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 6px;
            color: #34495e;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .btn {
            background-color: #3498db;
            color: white;
            padding: 10px 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        .btn:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e2eafc;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Daily Expenses</h2>

        <!-- Set Group ID Form -->
        <h:form>
            <h:panelGrid columns="1" style="margin-bottom: 20px;" columnClasses="form-col">
                <h:outputLabel for="groupId" value="Enter Group ID:" />
                <h:inputText id="groupId" value="#{expenseController.groupId}" required="true" />
                <h:commandButton value="Load Expenses"
                    action="#{expenseController.dailyExpensesListMethod}"
                    styleClass="btn" />
            </h:panelGrid>
        </h:form>

        <!-- Expenses Table -->
        <h:form>
            <h:dataTable value="#{expenseController.dailyExpensesList}" var="expense"
                         styleClass="expense-table" headerClass="header-row" rowClasses="row-style-even,row-style-odd">
                <h:column>
                    <f:facet name="header">Expense ID</f:facet>
                    <h:outputText value="#{expense.expId}" />
                </h:column>
                <h:column>
                    <f:facet name="header">Description</f:facet>
                    <h:outputText value="#{expense.expenseDescription}" />
                </h:column>
                <h:column>
                    <f:facet name="header">Amount</f:facet>
                    <h:outputText value="#{expense.amount}" />
                </h:column>
                <h:column>
                    <f:facet name="header">Date</f:facet>
                    <h:outputText value="#{expense.expenseDate}">
                        <f:convertDateTime pattern="yyyy-MM-dd" />
                    </h:outputText>
                </h:column>
                <h:column>
                    <f:facet name="header">Paid By (User ID)</f:facet>
                    <h:outputText value="#{expense.paidBy}" />
                </h:column>
            </h:dataTable>
        </h:form>
    </div>
</body>
</html>
</f:view>
