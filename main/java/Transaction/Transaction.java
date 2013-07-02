package Transaction;

import TransactionDAO.TransactionDAO;
import TransactionDTO.TransactionDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kpv
 * Date: 7/1/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction
{
    private static TransactionDAO transactionDAO;
    public static void setMockTransaction(TransactionDAO transactinDao)
    {
        transactionDAO = transactinDao;
    }

    public static void save(TransactionDTO transactionDTO)
    {
        transactionDAO.save(transactionDTO);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        return transactionDAO.getTransactionsOccurred(accountNumber);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        return transactionDAO.getTransactionsOccurred(accountNumber,startTime,stopTime);
    }

    public static List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        return transactionDAO.getTransactionsOccurred(accountNumber,numberNewestOfTransaction);
    }
}
