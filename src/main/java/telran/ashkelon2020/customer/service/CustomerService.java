package telran.ashkelon2020.customer.service;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDeleteDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;

public interface CustomerService {
	
	boolean addCustomer(CustomerDto customerDto);
	
	CustomerDto getCustomer(Integer id);
	
	CustomerDto updateCustomer(Integer id, CustomerUpdateDto customerUpdateDto);
	
	CustomerDeleteDto deleteCustomer(Integer id);
	
	Iterable<AccountDto> findAccountsByCity(String city);
	
}
