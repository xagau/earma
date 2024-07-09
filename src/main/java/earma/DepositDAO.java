package earma;

import java.util.List;

public interface DepositDAO {
    void save(Deposit deposit, String accountId);
    List<Deposit> findByAccountId(String accountId);
}

