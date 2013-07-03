package BankAccountDAO;

import BankAccountDTO.BankAccountDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/28/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDAO {
    private Connection dbConnection;

    public BankAccountDAO(DataSource dataSource) throws SQLException
    {
        this.dbConnection = dataSource.getConnection();
    }

    public void save(BankAccountDTO account) throws SQLException
    {
        String query = "INSERT INTO SAVINGS_ACCOUNT(Account_Number,Timestamps ,Balance) values ('"+account.getAccountNumber()+"',"+"'"
                +account.getTimeStamp()+"',"+"'"+account.getBalance()+"')";
        //ResultSet resultSet = dbConnection.createStatement().executeQuery(query);
        Statement stmt = dbConnection.createStatement();
        stmt.execute(query);
    }

    public BankAccountDTO getAccountNumber(String accountNumber) throws SQLException
    {
        //String queryString = "SELECT * FROM SAVINGS_ACCOUNT WHERE ACCOUNT_NUMBER='" + accountNumber + "'";
        String queryString = "SELECT balance,timestamps FROM SAVINGS_ACCOUNT WHERE Account_Number='" + accountNumber + "'";
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        if(resultSet.next())
            return new BankAccountDTO(accountNumber,resultSet.getDouble("balance"),resultSet.getLong("timestamps"));
        else
            return null;
    }
}
