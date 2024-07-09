package earma;

import java.util.ArrayList;

public class Account {
    private String name;
    private double balance;
    ArrayList<Withdrawal> withdrawalArrayList = new ArrayList<Withdrawal>();
    ArrayList<Deposit> depositArrayList = new ArrayList<Deposit>();

    public ArrayList<Withdrawal> getWithdrawalArrayList()
    {
        return withdrawalArrayList;
    }

    public ArrayList<Deposit> getDepositArrayList()
    {
        return depositArrayList;
    }

    public void add(Withdrawal w)
    {
        setBalance(getBalance() - w.getAmount());
        withdrawalArrayList.add(w);
    }

    public void add(Deposit d)
    {
        setBalance(getBalance() + d.getAmount());
        depositArrayList.add(d);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
