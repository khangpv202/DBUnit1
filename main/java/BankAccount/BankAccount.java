package BankAccount;

import BankAccountDAO.BankAccountDAO;
import BankAccountDTO.BankAccountDTO;
import TransactionDTO.TransactionDTO;
import Transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/28/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount{
    private static BankAccountDAO bankAccountDAO ;

    public static void setMockBankAccount(BankAccountDAO bankAccountDao) {
        bankAccountDAO=bankAccountDao;
    }

    public static BankAccountDTO open(String accountNumber) {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDAO.save(account);
        return account;
    }

    public static BankAccountDTO getAccountNumber(String accountNumber) throws SQLException
    {
        return bankAccountDAO.getAccountNumber(accountNumber);
    }

    public static TransactionDTO deposit(String accountNumber, int amount, String descreption) throws SQLException
    {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccountNumber(accountNumber);
        bankAccountDTO.setBalance (bankAccountDTO.getBalance()+amount);
        bankAccountDAO.save(bankAccountDTO);
        TransactionDTO transaction = new TransactionDTO(accountNumber,amount,descreption);
        Transaction.save(transaction);
        return transaction;
    }

    public static TransactionDTO withdraw(String accountNumber, int amount, String description) throws SQLException
    {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccountNumber(accountNumber);
        bankAccountDTO.setBalance (bankAccountDTO.getBalance()+amount);
        bankAccountDAO.save(bankAccountDTO);
        TransactionDTO transactionDTO = new TransactionDTO(accountNumber,amount,description);
        Transaction.save(transactionDTO);
        return  transactionDTO;
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        return Transaction.getTransactionsOccurred(accountNumber);
    }

    public static List<TransactionDTO>  getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        return Transaction.getTransactionsOccurred(accountNumber,startTime,stopTime);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        return Transaction.getTransactionsOccurred(accountNumber,numberNewestOfTransaction);
    }
}
