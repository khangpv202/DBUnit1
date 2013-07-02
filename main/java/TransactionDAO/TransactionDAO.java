package TransactionDAO;

import TransactionDTO.TransactionDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kpv
 * Date: 7/1/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDAO
{
    public void save(TransactionDTO capture)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public List<TransactionDTO> getTransactionsOccurred(String accountNumber)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<TransactionDTO> getTransactionsOccurred(String accountNumber, long startTime, long stopTime)
    {
        return null;
    }

    public List<TransactionDTO> getTransactionsOccurred(String accountNumber, int numberNewestOfTransaction)
    {
        return null;
    }
}
