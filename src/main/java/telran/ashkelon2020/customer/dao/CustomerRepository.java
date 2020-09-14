package telran.ashkelon2020.customer.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query("select c.accounts from Customer c where c.address.city=?1")
	Stream<Account> findAccountsByAddressCity(String city);
	
}
