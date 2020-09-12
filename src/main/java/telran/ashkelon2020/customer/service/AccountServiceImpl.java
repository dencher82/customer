package telran.ashkelon2020.customer.service;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.customer.dao.AccountRepository;
import telran.ashkelon2020.customer.dao.CustomerRepository;
import telran.ashkelon2020.customer.dao.SubscriberRepository;
import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.exceptions.EntityNotFoundException;
import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;

@Service
public class AccountServiceImpl implements AccountService {
	
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
	public boolean addAccount(Integer id, AccountDto accountDto) {
		if (accountRepository.existsById(accountDto.getLogin())) {
			return false;
		}
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		Account account = modelMapper.map(accountDto, Account.class);
		accountRepository.save(account);
		customer.addAccount(account);
		customerRepository.save(customer);
		return true;
	}

	@Override
	public AccountDto getAccount(String login) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto updateAccount(Integer id, String login, String newLogin) {
//		FIX ME
		Account account = accountRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		Account newAccount = new Account(newLogin, account.getPassword());
		customer.removeAccount(account);
		customer.addAccount(newAccount);
		account.setLogin(newLogin);
		customerRepository.save(customer);
		accountRepository.save(newAccount);
		return  modelMapper.map(newAccount, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto deleteAccount(Integer id, String login) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new EntityNotFoundException());
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		customer.removeAccount(account);
		customerRepository.save(customer);
		accountRepository.delete(account);		
		return modelMapper.map(account, AccountDto.class);
	}

}
