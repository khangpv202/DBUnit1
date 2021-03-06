package BankAccountDTO;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/28/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    private double balance;
    private String accountNumber;
    private long timeStamp;
    public BankAccountDTO(String numberOfNumber) {
        this.accountNumber = numberOfNumber;
        this.balance = 0;
        timeStamp= System.currentTimeMillis();
    }

    public BankAccountDTO(String accountNumber,double balance)
    {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public BankAccountDTO(String accountNumber, double balance, long timestamp)
    {
        this.balance       = balance;
        this.timeStamp     = timestamp;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getAccountNumber() {
        return accountNumber;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setBalance(double amount)
    {
        this.balance = amount;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean  check = false;
        if(o instanceof BankAccountDTO){
            if(this.accountNumber==((BankAccountDTO) o).getAccountNumber()
                    && this.timeStamp==((BankAccountDTO) o).getTimeStamp()
                    && this.balance==((BankAccountDTO) o).getBalance())
                check = true;
        }
        return check;
    }

    @Override
    public int hashCode()
    {
       return 1;
    }
    @Override
    public String toString(){
        return accountNumber+"  "+balance+" "+timeStamp;
    }
}
