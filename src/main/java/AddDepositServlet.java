import earma.*;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addDeposit")
public class AddDepositServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("accountName");
        double amount = Double.parseDouble(request.getParameter("amount"));

        StateManager stateManager = new StateManager();
        Account account = stateManager.loadAccount(accountName);

        System.out.println("Add Deposit");
        if (account != null) {
            System.out.println("Add Deposit [" + amount + "]" );
            Deposit deposit = new Deposit();
            deposit.setAmount(amount);
            deposit.setDate(new Date(System.currentTimeMillis()));
            account.add(deposit);
            DepositDAO dao = new DepositDAOImpl(stateManager.getConnection());
            dao.save(deposit, account.getName());
            stateManager.saveAccount(account);
            System.out.println("Deposit [" + amount + "] Added to " + account.getName() );
        }

        stateManager.close();
        response.sendRedirect("history?accountName=" + accountName);
    }
}

