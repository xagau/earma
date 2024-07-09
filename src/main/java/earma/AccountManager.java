package earma;

import java.sql.*;
import java.util.ArrayList;

public class AccountManager {
    ArrayList<Account> accountArrayList = new ArrayList<Account>();

    public ArrayList<String> loadAllNames()  {
        try {
            ArrayList<String> names = new ArrayList<>();
            StateManager sm = new StateManager();
            Connection c = sm.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from accounts");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                String name = r.getString("name");
                names.add(name);
            }
            return names;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public void loadAll(){
        accountArrayList = new ArrayList<>();
        StateManager sm = new StateManager();
        ArrayList<String> names = loadAllNames();
        for(int i =0; i < names.size(); i++ ) {
            Account a = sm.loadAccount(names.get(i));
            accountArrayList.add(a);
        }

    }
    public AccountManager(){
        loadAll();
    }

    public void add(Account account)
    {
        accountArrayList.add(account);
    }

    public ArrayList<Account> getAccountArrayList(){
        return accountArrayList;
    }

    public double compute(Account aa)
    {
        if( aa == null ){
            return 0;
        }
        StateManager sm = new StateManager();
        Account a = sm.loadAccount(aa.getName());

        ArrayList<Deposit> depositArrayList = a.getDepositArrayList();
        ArrayList<Withdrawal> withdrawalArrayList = a.getWithdrawalArrayList();
        double dt = 0;
        double wt = 0;
        for(int i = 0; i < depositArrayList.size(); i++ ){
            dt += depositArrayList.get(i).getAmount();
        }
        for(int i = 0; i < withdrawalArrayList.size(); i++ ){
            wt += withdrawalArrayList.get(i).getAmount();
        }
        return dt - wt;
    }
    public void distribute(double funds){
        double totalBalance = 0.0;
        loadAll();
        accountArrayList = getAccountArrayList();
        for (Account account : accountArrayList) {
            totalBalance += compute(account); //.getBalance();
        }

        StateManager sm = new StateManager();
        Connection connection = sm.getConnection();

        // Distribute funds based on the balance of each account
        for (Account account : accountArrayList) {
            double accountBalance = compute(account); //.getBalance();
            double proportion = accountBalance / totalBalance;
            double amountToDistribute = funds * proportion;

            Deposit deposit = new Deposit();
            deposit.setAmount(amountToDistribute);
            deposit.setDate(new Date(System.currentTimeMillis()));
            account.add(deposit);

            DepositDAOImpl impl = new DepositDAOImpl(connection);
            impl.save(deposit, account.getName());

        }
    }
}
