package earma;

import java.sql.Date;

public class Main {
    public static void main(String[] args)
    {
        try {
            AccountManager manager = new AccountManager();
            Deposit dA = new Deposit();
            Deposit dB = new Deposit();
            Deposit dC = new Deposit();
            dA.setDate(new Date(System.currentTimeMillis()));
            dB.setDate(new Date(System.currentTimeMillis()));
            dC.setDate(new Date(System.currentTimeMillis()));

            dA.setAmount(30000);
            dB.setAmount(50000);
            dC.setAmount(20000);



            StateManager stateManager = new StateManager(); //jdbcUrl, user, password);

            Account accountA = new Account();
            accountA.setName("John Doe");
            accountA.add(dA);

            Account accountB = new Account();
            accountB.setName("Jill Doe");
            accountB.add(dB);

            Account accountC = new Account();
            accountC.setName("Max Doe");
            accountC.add(dC);

            manager.add(accountA);
            manager.add(accountB);
            manager.add(accountC);

            manager.distribute(1150);

            stateManager.saveAccount(accountA);
            stateManager.saveAccount(accountB);
            stateManager.saveAccount(accountC);


            System.out.println(accountA.getBalance());
            System.out.println(accountB.getBalance());
            System.out.println(accountC.getBalance());

            // Load account
            Account loadedAccount = stateManager.loadAccount("John Doe");
            System.out.println("Loaded account: " + loadedAccount.getName() + ", Balance: " + loadedAccount.getBalance());

            stateManager.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }


    }
}
