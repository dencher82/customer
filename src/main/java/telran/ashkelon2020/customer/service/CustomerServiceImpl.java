package telran.ashkelon2020.customer.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.customer.dao.AccountRepository;
import telran.ashkelon2020.customer.dao.CustomerRepository;
import telran.ashkelon2020.customer.dao.SubscriberRepository;
import telran.ashkelon2020.customer.dto.AccountCreateDto;
import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerCreateDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.dto.exceptions.EntityNotFoundException;
import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;
import telran.ashkelon2020.customer.model.Subscriber;

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
	public boolean addCustomer(CustomerCreateDto customerCreateDto) {
		if (customerRepository.existsById(customerCreateDto.getId())) {
			return false;
		}
		Customer customer = modelMapper.map(customerCreateDto, Customer.class);
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
	public CustomerDto deleteCustomer(Integer id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		// delete all rows from child table
		customer.getAccounts().forEach(a -> deleteAccount(a.getLogin()));
		// delete row in parent table
		customerRepository.delete(customer);
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public boolean addAccount(Integer id, AccountCreateDto accountCreateDto) {
		if (accountRepository.existsById(accountCreateDto.getLogin())) {
			return false;
		}
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		Account account = new Account(accountCreateDto.getLogin(), accountCreateDto.getDescription(), customer);
		accountRepository.save(account);
		return true;
	}

	@Override
	public AccountDto getAccount(String login) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto updateAccount(String login, String description) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		if (description != null) {
			account.setDescription(description);
		}
		accountRepository.save(account);
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto deleteAccount(String login) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		// delete all rows from child table
		subscriberRepository.findSubscribersByAccountsLogin(login) 
			.forEach(s -> s.getAccounts().remove(account));
		// delete row in parent table
		accountRepository.delete(account);
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public boolean addSubscriber(String login, SubscriberDto subscriberDto) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		// check if subscriber already exists in subscribers of given account login
		Set<Subscriber> subscribers = account.getSubscribers();
		for (Subscriber s : subscribers) {
			if (s.getLogin().equals(subscriberDto.getLogin())) {
				return false;
			}
		}
		// find existing or create new subscriber
		Subscriber subscriber = subscriberRepository.findById(subscriberDto.getLogin())
				.orElseGet(() -> new Subscriber(subscriberDto.getLogin(), subscriberDto.getDescription(), new HashSet<>()));
		subscriber.getAccounts().add(account);
		subscriberRepository.save(subscriber);
		return true;
	}

	@Override
	public SubscriberDto getSubscriber(String login) {
		Subscriber subscriber = subscriberRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(subscriber, SubscriberDto.class);
	}

	@Override
	@Transactional
	public SubscriberDto updateSubscriber(String login, String description) {
		Subscriber subscriber = subscriberRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		if (description != null) {
			subscriber.setDescription(description);
		}
		subscriberRepository.save(subscriber);
		return modelMapper.map(subscriber, SubscriberDto.class);
	}

	@Override
	@Transactional
	public SubscriberDto deleteSubscriber(String login) {
		Subscriber subscriber = subscriberRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		subscriberRepository.delete(subscriber);
		return modelMapper.map(subscriber, SubscriberDto.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<AccountDto> findAccountsByCity(String city) {
		return customerRepository.findAccountsByAddressCity(city)
				.map(a -> modelMapper.map(a, AccountDto.class))
				.collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<SubscriberDto> findSubscribersByLoginPattern(String loginPattern) {
		return subscriberRepository.findSubscribersByLoginPattern(loginPattern)
				.map(s -> modelMapper.map(s, SubscriberDto.class))
				.collect(Collectors.toSet());
	}

}
