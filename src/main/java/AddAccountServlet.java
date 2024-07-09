
import earma.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/addAccount")
public class AddAccountServlet extends HttpServlet {
    private AccountDAO accountDAO;

    StateManager stateManager = new StateManager();
    @Override
    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));

        Account account = new Account();
        account.setName(name);


        System.out.println("Add Account");

        Deposit d = new Deposit();
        d.setAmount(initialBalance);
        d.setDate(new Date(System.currentTimeMillis()));
        DepositDAO dao = new DepositDAOImpl(stateManager.getConnection());
        dao.save(d,name);
        stateManager.saveAccount(account);

        response.sendRedirect("history.jsp");
    }
}
