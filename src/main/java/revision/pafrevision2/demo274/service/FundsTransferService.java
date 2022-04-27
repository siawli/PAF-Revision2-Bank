package revision.pafrevision2.demo274.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import revision.pafrevision2.demo274.repository.AccountRepository;

@Service
public class FundsTransferService {

    @Autowired
    private AccountRepository acctRepo;

    @Transactional
    public void fundsTransfer(String fromAcct, String toAcct, Float amount) {

        Optional<Float> balanceFromAcctOpt = acctRepo.withdraw(fromAcct, amount);
        Optional<Float> balanceToAcctOpt = acctRepo.deposit(toAcct, amount);

        if (balanceFromAcctOpt.isEmpty() || balanceToAcctOpt.isEmpty()) {
            throw new IllegalArgumentException("No such bank account!");
        }

        if (balanceFromAcctOpt.get() < 0) {
            throw new IllegalArgumentException("Insufficient balance in first account!");
        }

    }
}
