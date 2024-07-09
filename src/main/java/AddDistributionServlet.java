import earma.Account;
import earma.AccountManager;
import earma.Deposit;
import earma.StateManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/addDistribution")
public class AddDistributionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("accountName");
        double amount = Double.parseDouble(request.getParameter("amount"));

        AccountManager am = new AccountManager();
        System.out.println("Add Distribution");
        if (am != null) {
            am.distribute(amount);
        }

        response.sendRedirect("index.jsp");
    }
}

