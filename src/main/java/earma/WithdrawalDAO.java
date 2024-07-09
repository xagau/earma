package earma;

import java.util.List;

public interface WithdrawalDAO {
    void save(Withdrawal withdrawal, String accountId);
    List<Withdrawal> findByAccountId(String accountId);
}

