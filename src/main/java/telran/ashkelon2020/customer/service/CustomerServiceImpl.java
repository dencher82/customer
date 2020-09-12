package telran.ashkelon2020.customer.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.customer.dao.AccountRepository;
import telran.ashkelon2020.customer.dao.CustomerRepository;
import telran.ashkelon2020.customer.dao.SubscriberRepository;
import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDeleteDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.dto.exceptions.EntityNotFoundException;
import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	SubscriberRepository subscriberRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	@Transactional
	public boolean addCustomer(CustomerDto customerDto) {
		if (customerRepository.existsById(customerDto.getId())) {
			return false;
		}
		Set<Account> accounts = customerDto.getAccounts().stream()
				.map(a -> accountRepository.findById(a.getLogin())
						.orElseGet(() -> accountRepository.save(new Account(a.getLogin(), a.getPassword()))))
				.collect(Collectors.toSet());
		Customer customer = new Customer(customerDto.getId(), customerDto.getName(), customerDto.getBirthDate(),
				customerDto.getMobilePhone(), customerDto.getEmail(), customerDto.getAddress(), accounts);
		customerRepository.save(customer);		
		return true;
	}

	@Override
	public CustomerDto getCustomer(Integer id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public CustomerDto updateCustomer(Integer id, CustomerUpdateDto customerUpdateDto) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		if (customerUpdateDto.getName() != null) {
			customer.setName(customerUpdateDto.getName());
		}
		if (customerUpdateDto.getBirthDate() != null) {
			customer.setBirthDate(customerUpdateDto.getBirthDate());
		}
		if (customerUpdateDto.getMobilePhone() != null) {
			customer.setMobilePhone(customerUpdateDto.getMobilePhone());
		}
		if (customerUpdateDto.getEmail() != null) {
			customer.setEmail(customerUpdateDto.getEmail());
		}
		if (customerUpdateDto.getAddress() != null) {
			customer.setAddress(customerUpdateDto.getAddress());
		}
		customerRepository.save(customer);
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public CustomerDeleteDto deleteCustomer(Integer id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		customerRepository.deleteById(id);
		customer.getAccounts().stream()
				.forEach(a -> accountRepository.delete(a));
		return modelMapper.map(customer, CustomerDeleteDto.class); // FIX ME
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<AccountDto> findAccountsByCity(String city) {
		return customerRepository.findCustomersByAddressCity(city)
				.map(a -> modelMapper.map(a, AccountDto.class))
				.collect(Collectors.toSet());
	}

}
