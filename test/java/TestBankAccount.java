import BankAccount.BankAccount;
import BankAccountDAO.BankAccountDAO;
import BankAccountDTO.BankAccountDTO;
import Transaction.Transaction;
import TransactionDAO.TransactionDAO;
import TransactionDTO.TransactionDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * User: khangpv
 * Date: 6/28/13
 * Time: 1:34 PM
 */
public class TestBankAccount  {
    private BankAccountDAO mockBankAccountDAO = mock(BankAccountDAO.class);
    private TransactionDAO mockTransactinDAO = mock(TransactionDAO.class);
    private final String accountNumber = "123456789";

    @Before
    public void initialForTest(){
        reset(mockBankAccountDAO);
        BankAccount.setMockBankAccount(mockBankAccountDAO);
        reset(mockTransactinDAO);
        Transaction.setMockTransaction(mockTransactinDAO);
    }

    @Test
    public void testOpenAccount() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        ArgumentCaptor<BankAccountDTO> saveAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBankAccountDAO).save(saveAccount.capture());
        assertEquals(saveAccount.getValue().getBalance(),0,0.001);
        assertEquals(saveAccount.getValue().getAccountNumber(),accountNumber);
        assertEquals(saveAccount.getValue().getTimeStamp(),initialAccount.getTimeStamp());
    }
    @Test
    public void testGetAccountNumber() throws SQLException
    {
        BankAccount.open(accountNumber);
        BankAccount.getAccountNumber(accountNumber);
        verify(mockBankAccountDAO).getAccountNumber(accountNumber);
    }
    @Test
    public void testDeposit() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockBankAccountDAO.getAccountNumber(accountNumber)).thenReturn(initialAccount);
        BankAccount.deposit(accountNumber,10,"deposit");
        ArgumentCaptor<BankAccountDTO> capturedAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBankAccountDAO,times(2)).save(capturedAccount.capture());
        assertEquals(capturedAccount.getValue().getBalance(),initialAccount.getBalance(),0.001);
    }
    @Test
    public void testDepositForSaveInToDB() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockBankAccountDAO.getAccountNumber(accountNumber)).thenReturn(initialAccount);
        TransactionDTO transactionDTO = BankAccount.deposit(accountNumber,10,"first deposit");
        ArgumentCaptor<TransactionDTO> savedTransaction = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactinDAO).save(savedTransaction.capture());
        assertEquals(transactionDTO.getTimestamp(),savedTransaction.getValue().getTimestamp());
    }
    @Test
    public void testWithdraw() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockBankAccountDAO.getAccountNumber(accountNumber)).thenReturn(initialAccount);
        BankAccount.deposit(accountNumber,10,"deposit");
        BankAccount.withdraw(accountNumber,-10,"deposit");
        ArgumentCaptor<BankAccountDTO> capturedAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBankAccountDAO,times(3)).save(capturedAccount.capture());
        assertEquals(capturedAccount.getValue().getBalance(),initialAccount.getBalance(),0.001);
        assertEquals(capturedAccount.getValue().getBalance(),0,0.001);
    }
    @Test
    public void testWithdrawhasTimestampSavedIntoDB() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockBankAccountDAO.getAccountNumber(accountNumber)).thenReturn(initialAccount);
        BankAccount.deposit(accountNumber,10,"first deposit");
        TransactionDTO transactionDTO =BankAccount.withdraw(accountNumber,-10,"first withdraw");
        ArgumentCaptor<TransactionDTO> savedTransaction = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactinDAO,times(1)).save(savedTransaction.capture());
        assertEquals(transactionDTO.getTimestamp(),savedTransaction.getValue().getTimestamp());
    }
    @Test
    public void testGetTransactionsOccurred() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        List<TransactionDTO>listTransaction ;

        when(mockBankAccountDAO.getAccountNumber(accountNumber)).thenReturn(initialAccount);

        BankAccount.deposit(accountNumber,10,"first deposit");
        BankAccount.deposit(accountNumber,10,"second deposit");

        ArgumentCaptor<TransactionDTO> listCapturedTransaction = ArgumentCaptor.forClass(TransactionDTO.class);
        listTransaction = listCapturedTransaction.getAllValues();
        when(mockTransactinDAO.getTransactionsOccurred(accountNumber)).thenReturn(listTransaction);

        List<TransactionDTO> transactionList = BankAccount.getTransactionsOccurred(accountNumber);

        assertEquals(listTransaction,transactionList);
    }
    @Test
    public void testGetTransactionHasStartandStopTime() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockBankAccountDAO.getAccountNumber(accountNumber)).thenReturn(initialAccount);

        BankAccount.deposit(accountNumber,10,"first deposit");
        BankAccount.deposit(accountNumber,10,"second deposit");
        long startTime = 0l, stopTime = 1000L;

        BankAccount.getTransactionsOccurred(accountNumber, startTime, stopTime);
        verify(mockTransactinDAO).getTransactionsOccurred(accountNumber,startTime,stopTime);
    }
    @Test
    public void testGetNewestTransaction() throws SQLException
    {
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockBankAccountDAO.getAccountNumber(accountNumber)).thenReturn(initialAccount);

        BankAccount.deposit(accountNumber,10,"first deposit");
        BankAccount.deposit(accountNumber,10,"second deposit");

        BankAccount.getTransactionsOccurred(accountNumber,2);
        verify(mockTransactinDAO).getTransactionsOccurred(accountNumber,2);
    }

}
