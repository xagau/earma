<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Withdrawal</title>
</head>
<body>
<h2>Add Withdrawal</h2>
<%
    String an = request.getParameter("accountName");
    if( an == null ){
        an = "";
    }
%>
<form action="addWithdrawal" method="post">
    Account Name: <input type="text" name="accountName" value="<%=an%>"><br>
    Amount: <input type="number" name="amount" step="0.01"><br>
    <input type="submit" value="Add Withdrawal">
</form>
<a href="index.jsp">Back to Accounts</a>

</body>
</html>
