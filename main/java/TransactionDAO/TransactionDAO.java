package TransactionDAO;

import BankAccountDTO.BankAccountDTO;
import TransactionDTO.TransactionDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private Connection dbConnection;
    public TransactionDAO(DataSource dataSource) throws SQLException
    {
        this.dbConnection= dataSource.getConnection();
    }

    public void save(TransactionDTO transaction) throws SQLException
    {
        String query = "INSERT INTO TRANSACTIONS(Account_Number,Timestamps ,Amount,Description) VALUES ('"+transaction.getAccountNumber()+"',"+"'"
                +transaction.getTimeStamp()+"',"+"'"+transaction.amount()+"'"+",'"+transaction.getDescription()+"')";
        //ResultSet resultSet = dbConnection.createStatement().executeQuery(query);
        Statement stmt = dbConnection.createStatement();
        stmt.execute(query);
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

    public TransactionDTO getTransaction(TransactionDTO transaction)
    {
        /*String queryString = "SELECT balance,timestamps FROM SAVINGS_ACCOUNT WHERE Account_Number='" + accountNumber + "'";
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        if(resultSet.next())
            return new BankAccountDTO(accountNumber,resultSet.getDouble("balance"),resultSet.getLong("timestamps"));
        else
            return null;*/
        //just make for right return.:)
        return transaction;
    }
}
