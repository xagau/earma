package earma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositDAOImpl implements DepositDAO {
    private Connection connection;

    public DepositDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Deposit deposit, String accountId) {
        if( deposit.getAmount() > 0 ) {
            String sql = "INSERT INTO deposits (account_id, amount, date) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, accountId);
                stmt.setDouble(2, deposit.getAmount());
                stmt.setDate(3, deposit.getDate());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Deposit> findByAccountId(String accountId) {
        List<Deposit> deposits = new ArrayList<>();
        String sql = "SELECT * FROM deposits WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Deposit deposit = new Deposit();
                deposit.setAmount(rs.getDouble("amount"));
                deposit.setDate(rs.getDate("date"));
                deposits.add(deposit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deposits;
    }
}
