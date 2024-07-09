<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Distribution</title>
</head>
<body>
<h2>Add Distribution</h2>
<form action="addDistribution" method="post">
    <input type="number" name="amount" step="0.01"><br>
    <input type="submit" value="Add Distribution">
</form>
<a href="index.jsp">Back to Accounts</a>

</body>
</html>
