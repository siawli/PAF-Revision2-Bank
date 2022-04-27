package revision.pafrevision2.demo274.repository;

public class Queries {

    public static final String SQL_DEPOSIT_AND_WITHDRAW = 
        "update account set balance = ? where acct_no = ?";

    public static final String SQL_GET_BALANCE = 
        "select balance from account where acct_no = ?";
        
    
}
