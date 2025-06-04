<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Menu</title>
<style>
    body {
        font-family: Arial, sans-serif;
    }
    .logout {
        margin-bottom: 20px;
    }
    .menu-bar {
        display: flex;
        background-color: #ff11ff;
        padding: 10px;
        gap: 20px;
    }
    .menu-item {
        background-color: #ffff00;
        padding: 10px 20px;
        text-align: center;
        border-radius: 5px;
    }
    .menu-item a {
        text-decoration: none;
        color: black;
        font-weight: bold;
    }
</style>
</head>
<body>

<%
  String user = (String)session.getAttribute("user");
  out.println("Welcome <b>" + user + "</b>");
%>

<div class="logout">
    <input type="button" name="logout" value="Logout" onclick="window.location='index.html'">
</div>

<div class="menu-bar">
    <div class="menu-item"><a href="AccountDetails.jsp">Account Details</a></div>
    <div class="menu-item"><a href="Search.jsp">Search / Issue</a></div>
    <div class="menu-item"><a href="Return.jsp">Return</a></div>
    <div class="menu-item"><a href="History.jsp">History</a></div>
</div>

</body>
</html>
