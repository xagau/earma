<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Account</title>
</head>
<body>
<h2>Add Account</h2>
<form action="addAccount" method="post">
    Account Name: <input type="text" name="name" required><br>
    Initial Balance: <input type="number" name="initialBalance" step="0.01" required><br>
    <input type="submit" value="Add Account">
</form>
<a href="index.jsp">Back to Accounts</a>
</body>
</html>