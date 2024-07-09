package earma;

public interface AccountDAO {
    void save(Account account);
    Account findByName(String name);
}
