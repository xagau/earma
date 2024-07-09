import earma.Account;
import earma.StateManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/accountHistory")
public class AccountHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("accountName");

        if (accountName != null && !accountName.isEmpty()) {
            StateManager stateManager = new StateManager();
            Account account = stateManager.loadAccount(accountName);

            if (account != null) {
                request.setAttribute("account", account);
            }

            stateManager.close();
        }

        request.getRequestDispatcher("history.jsp").forward(request, response);
    }
}

