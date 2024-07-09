<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="earma.*" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account History</title>
</head>
<body>
<h2>Account History</h2>
<form action="history" method="get">
    Account Name:
    <input type="text" name="accountName"><br>
    <input type="submit" value="View History">
</form>

<%
    String accountName = request.getParameter("accountName");
    if (accountName != null && !accountName.isEmpty()) {
        StateManager stateManager = new StateManager();

        Account account = stateManager.loadAccount(accountName);
        AccountManager am = new AccountManager();

        if (account != null) {
            double total = 0;
            DecimalFormat df = new DecimalFormat("0.00");

            out.println("<h3>Account: " + account.getName() + "</h3>");

            out.println("<h4>Deposits:</h4>");
            out.println("<ul>");
            for (Deposit deposit : account.getDepositArrayList()) {
                out.println("<li>Date: " + deposit.getDate() + " Amount: " + df.format(deposit.getAmount()) + "</li>");
            }
            out.println("</ul>");

            out.println("<h4>Withdrawals:</h4>");
            out.println("<ul>");
            for (Withdrawal withdrawal : account.getWithdrawalArrayList()) {
                out.println("<li>Date: " + withdrawal.getDate() + " Amount: " + df.format(withdrawal.getAmount()) + "</li>");
            }
            out.println("</ul>");
            total = am.compute(account);
            out.println("<h4>Balance:</h4>");
            out.println("<p> " + df.format(total) + "</p>");

            stateManager.close();
        } else {
            out.println("<p>Account not found.</p>");
        }
    }
%>
<a href="index.jsp">Back to Accounts</a>

</body>
</html>
