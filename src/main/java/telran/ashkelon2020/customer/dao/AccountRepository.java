package telran.ashkelon2020.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.customer.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
	
//	@Query("update Account a SET a.login=?2 WHERE a.login=?1")
//	long updateAccountLogin(String login, String newLogin);
	
}
