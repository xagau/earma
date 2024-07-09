<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Deposit</title>
</head>
<body>
<h2>Add Deposit</h2>
<%
    String an = request.getParameter("accountName");
    if( an == null ){
        an = "";
    }
%>
<form action="addDeposit" method="post">
    Account Name:
    <input type="text" name="accountName" value="<%=an%>"><br>
    Amount:
    <input type="number" name="amount" step="0.01"><br>
    <input type="submit" value="Add Deposit">
</form>
<a href="index.jsp">Back to Accounts</a>

</body>
</html>
