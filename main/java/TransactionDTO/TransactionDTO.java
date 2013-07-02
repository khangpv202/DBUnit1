package TransactionDTO;

/**
 * Created with IntelliJ IDEA.
 * User: kpv
 * Date: 7/1/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDTO
{
    private  String accountNumber;
    private  double amount;
    private  String description;
    private long timestemp;

    public TransactionDTO(String accountNumber, int amount, String descreption)
    {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = descreption;
    }

    public long getTimestamp()
    {
        return timestemp;
    }
}
