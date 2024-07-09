package earma;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAOImpl implements AccountDAO {
    private Connection connection;
    private WithdrawalDAO withdrawalDAO;
    private DepositDAO depositDAO;

    public AccountDAOImpl(Connection connection) {
        this.connection = connection;
        this.withdrawalDAO = new WithdrawalDAOImpl(connection);
        this.depositDAO = new DepositDAOImpl(connection);
    }

    public boolean exists(String name)
    {
        StateManager sm = new StateManager();
        if(sm.loadAccount(name)!= null){
            return true;
        }
        return false;
    }

    @Override
    public void save(Account account) {
        if( !exists(account.getName())) {
            String sql = "INSERT INTO accounts (name) VALUES (?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, account.getName());
                //stmt.setDouble(2, compute(account).getBalance());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Account findByName(String name) {
        String sql = "SELECT * FROM accounts WHERE name = ?";
        Account account = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setName(rs.getString("name"));
                //account.setBalance(rs.getDouble("balance"));
                String accountId = rs.getString("name");
                account.withdrawalArrayList = (ArrayList<Withdrawal>) withdrawalDAO.findByAccountId(accountId);
                account.depositArrayList = (ArrayList<Deposit>) depositDAO.findByAccountId(accountId);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
