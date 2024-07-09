package earma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WithdrawalDAOImpl implements WithdrawalDAO {
    private Connection connection;

    public WithdrawalDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Withdrawal withdrawal, String accountId) {
        if( withdrawal.getAmount() > 0 ) {
            String sql = "INSERT INTO withdrawals (account_id, amount, date) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, accountId);
                stmt.setDouble(2, withdrawal.getAmount());
                stmt.setDate(3, withdrawal.getDate());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Withdrawal> findByAccountId(String accountId) {
        List<Withdrawal> withdrawals = new ArrayList<>();
        String sql = "SELECT * FROM withdrawals WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Withdrawal withdrawal = new Withdrawal();
                withdrawal.setAmount(rs.getDouble("amount"));
                withdrawal.setDate(rs.getDate("date"));
                withdrawals.add(withdrawal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdrawals;
    }
}
