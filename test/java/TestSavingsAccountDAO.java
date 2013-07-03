import BankAccount.BankAccount;
import BankAccountDAO.BankAccountDAO;
import BankAccountDTO.BankAccountDTO;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSavingsAccountDAO
{

    //using H2 so that we can create in-memory database for testing
    // without having to install any DBMS software
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private final String accountNumber= "12345";

    // create the db table
    @BeforeClass
    public static void createSchema() throws Exception {
        String schemaFileName = System.class.getResource("/schema.sql").toString().substring(6);
        RunScript.execute(JDBC_URL, USER, PASSWORD, schemaFileName, Charset.forName("UTF8"), false);
    }

    // populate the table with test data
    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();  // read data from xml file
        cleanlyInsert(dataSet);  // empty the db and insert data
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void testFindByAccountNumber() throws Exception {
        BankAccountDAO savingsAccountDAO = new BankAccountDAO(dataSource());
        BankAccountDTO account = savingsAccountDAO.getAccountNumber("0123456789");
        BankAccountDTO account2 = savingsAccountDAO.getAccountNumber("987");
        assertEquals("0123456789", account.getAccountNumber());
        assertEquals("987",account2.getAccountNumber());
    }
    @Test
    public void testOpenNewAccount() throws SQLException
    {
        BankAccountDAO savingsAccountDAO = new BankAccountDAO(dataSource());
        BankAccount.setMockBankAccount(savingsAccountDAO);
        BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        BankAccountDTO accountDTO = savingsAccountDAO.getAccountNumber(accountNumber);

        assertEquals(accountDTO.getAccountNumber(),accountNumber);
        assertEquals(initialAccount.getAccountNumber(),accountDTO.getAccountNumber());
        assertEquals(initialAccount.getBalance(),accountDTO.getBalance(),0.001);
        assertTrue(initialAccount.equals(accountDTO));
    }

    private DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}

