<%@ page import="java.util.ArrayList" %>
<%@ page import="earma.AccountManager" %>
<%@ page import="earma.Account" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: xagau
  Date: 2024-06-19
  Time: 11:16 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Account List</title>
  </head>
  <body>
  <a href="distribution.jsp?">Distribution Payment</a><br>
  <a href="addAccount.jsp?">Add Account</a><br>

  <table>
  <%
    AccountManager am = new AccountManager();
    ArrayList<Account> accountArrayList = am.getAccountArrayList();
    double runningTotal = 0;
    DecimalFormat df = new DecimalFormat("0.00");
    for(int i = 0; i < accountArrayList.size(); i++ ){
      double balance = am.compute(accountArrayList.get(i));
      runningTotal += balance;
  %>
  <tr>

  <td>
   <a href="history.jsp?accountName=<%=accountArrayList.get(i).getName()%>">History <%=accountArrayList.get(i).getName()%></a>
  </td>
  <td>
   <a href="addDeposit.jsp?accountName=<%=accountArrayList.get(i).getName()%>">Deposit to <%=accountArrayList.get(i).getName()%></a>
  </td>
    <td><a href="addWithdrawal.jsp?accountName=<%=accountArrayList.get(i).getName()%>">Withdrawal from <%=accountArrayList.get(i).getName()%></a></td><td align="right"> <%=df.format(balance)%> </td><td align="right"><%=df.format(runningTotal)%></td>
  </tr>
<%
  }
%>
  </table>
  </body>
</html>
