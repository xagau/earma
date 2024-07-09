package earma;

import earma.Account;
import earma.AccountDAO;
import earma.AccountDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StateManager {
    private Connection connection;
    private AccountDAO accountDAO;

    public StateManager(){
        this("jdbc:mariadb://localhost:3306/edb", "root", "PASSWORD");
    }
    private StateManager(String jdbcUrl, String user, String password) {
        try {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            accountDAO = new AccountDAOImpl(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection(){
        return connection;
    }

    public void saveAccount(Account account) {
        accountDAO.save(account);
    }

    public Account loadAccount(String name) {
        return accountDAO.findByName(name);
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

