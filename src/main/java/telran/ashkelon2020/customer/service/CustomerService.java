package telran.ashkelon2020.customer.service;

import telran.ashkelon2020.customer.dto.AccountCreateDto;
import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerCreateDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;

public interface CustomerService {
	
	boolean addCustomer(CustomerCreateDto customerCreateDto);
	
	CustomerDto getCustomer(Integer id);
	
	CustomerDto updateCustomer(Integer id, CustomerUpdateDto customerUpdateDto);
	
	CustomerDto deleteCustomer(Integer id);
	
	Iterable<AccountDto> findAccountsByCity(String city);
	
	boolean addAccount(Integer id, AccountCreateDto accountDto);

	AccountDto getAccount(String login);

	AccountDto updateAccount(String login, String description);

	AccountDto deleteAccount(String login);

	boolean addSubscriber(String login, SubscriberDto subscriberCreateDto);

	SubscriberDto getSubscriber(String login);

	SubscriberDto updateSubscriber(String login, String description);

	SubscriberDto deleteSubscriber(String login);

	Iterable<SubscriberDto> findSubscribersByLoginPattern(String loginPattern);
	
}
