package earma;

import java.sql.Date;

public class Deposit {
    private double amount;
    private Date date;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if( amount > 0 ) {
            this.amount = amount;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
