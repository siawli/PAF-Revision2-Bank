package revision.pafrevision2.demo274;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import revision.pafrevision2.demo274.repository.AccountRepository;
import revision.pafrevision2.demo274.service.FundsTransferService;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private FundsTransferService fundTransferSvc;

	@Autowired
	private AccountRepository acctRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void fundsShouldTransfer() {

		fundTransferSvc.fundsTransfer("siaw", "like", 10f);

		Optional<Float> balanceFromAcctOpt = acctRepo.getBalance("siaw");
		assertEquals(40f, balanceFromAcctOpt.get());

		Optional<Float> balanceToAcctOpt = acctRepo.getBalance("like");
		assertEquals(60f, balanceToAcctOpt.get());

	}


	@BeforeEach
	void setup() {
		int added = template.update("insert into account (acct_no, balance) values (?, ?)", "siaw", 50f);
		int added1 = template.update("insert into account (acct_no, balance) values (?, ?)", "like", 50f);
	}

	@AfterEach
	void delete() {
		int added = template.update("delete from account where acct_no = ?", "siaw");
		int added1 = template.update("delete from account where acct_no = ?", "like");
	}

}
