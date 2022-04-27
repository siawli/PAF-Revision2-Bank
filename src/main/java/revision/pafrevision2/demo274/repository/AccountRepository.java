package revision.pafrevision2.demo274.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static revision.pafrevision2.demo274.repository.Queries.*;

import java.util.Optional;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<Float> getBalance(String acct_no) {

        SqlRowSet result = template.queryForRowSet(SQL_GET_BALANCE, acct_no);

        if (!result.next()) {
            return Optional.empty();
        } else {
            return Optional.of(result.getFloat("balance"));
        }
    }
    
    public Optional<Float> deposit(String acct_no, Float amount) {

        Optional<Float> balanceOpt = getBalance(acct_no);

        if (balanceOpt.isPresent()) {
            Float balance = balanceOpt.get();
            balance += amount;
            int updated = template.update(SQL_DEPOSIT_AND_WITHDRAW, balance, acct_no);
            return Optional.of(balance);
        }

        return Optional.empty();

    }

    public Optional<Float> withdraw(String acct_no, Float amount) {

        Optional<Float> balanceOpt = getBalance(acct_no);

        if (balanceOpt.isPresent()) {
            Float balance = balanceOpt.get();
            balance -= amount;
            int updated = template.update(SQL_DEPOSIT_AND_WITHDRAW, balance, acct_no);
            return Optional.of(balance);
        }

        return Optional.empty();

    }

    
}
