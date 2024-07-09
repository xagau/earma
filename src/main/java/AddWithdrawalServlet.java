import earma.*;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addWithdrawal")
public class AddWithdrawalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("accountName");
        double amount = Double.parseDouble(request.getParameter("amount"));

        StateManager stateManager = new StateManager();
        Account account = stateManager.loadAccount(accountName);

        System.out.println("Add Withdrawal");
        if (account != null) {
            Withdrawal withdrawal = new Withdrawal();
            withdrawal.setAmount(amount);
            withdrawal.setDate(new Date(System.currentTimeMillis()));
            account.add(withdrawal);
            WithdrawalDAO dao = new WithdrawalDAOImpl(stateManager.getConnection());
            System.out.println("Add Withdrawal [" + amount + "]");
            dao.save(withdrawal,account.getName());
            System.out.println("Withdrawal [" + amount + "]");
            stateManager.saveAccount(account);
        }

        stateManager.close();
        response.sendRedirect("history?accountName=" + accountName);
    }
}