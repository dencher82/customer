package telran.ashkelon2020.customer.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.customer.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
	
	@Query("select s from Subscriber s join s.accounts a where a.login=?1")
	Stream<Subscriber> findSubscribersByAccountsLogin(String login);
	
	@Query("select s from Subscriber s where s.login like %:loginPattern%")
	Stream<Subscriber> findSubscribersByLoginPattern(String loginPattern);

}
