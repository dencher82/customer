package telran.ashkelon2020.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.customer.dto.AccountCreateDto;
import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerCreateDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.service.CustomerService;

@RestController
@RequestMapping
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	@PostMapping("/customer")
	public boolean addCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
		return customerService.addCustomer(customerCreateDto);
	}
	
	@GetMapping("/customer/id/{id}")
	public CustomerDto findCustomer(@PathVariable Integer id) {
		return customerService.getCustomer(id);
	}
	
	@PutMapping("/customer/id/{id}")
	public CustomerDto updateCustomer(@PathVariable Integer id, @RequestBody CustomerUpdateDto customerUpdateDto) {
		return customerService.updateCustomer(id, customerUpdateDto);
	}
	
	@DeleteMapping("/customer/id/{id}")
	public CustomerDto deleteCustomer(@PathVariable Integer id) {
		return customerService.deleteCustomer(id);
	}
	
	@PostMapping("/account/customer/id/{id}")
	public boolean addAccount(@PathVariable Integer id, @RequestBody AccountCreateDto accountCreateDto) {
		return customerService.addAccount(id, accountCreateDto);
	}
	
	@GetMapping("/account/login/{login}")
	public AccountDto findAccount(@PathVariable String login) {
		return customerService.getAccount(login);
	}
	
	@PutMapping("/account/login/{login}/description/{description}")
	public AccountDto updateAccount(@PathVariable String login, @PathVariable String description) {
		return customerService.updateAccount(login, description);
	}
	
	@DeleteMapping("/account/login/{login}")
	public AccountDto deleteAccount(@PathVariable String login) {
		return customerService.deleteAccount(login);
	}
	
	@PostMapping("/subscriber/account/login/{login}")
	public boolean addSubscriber(@PathVariable String login, @RequestBody SubscriberDto subscriberDto) {
		return customerService.addSubscriber(login, subscriberDto);
	}
	
	@GetMapping("/subscriber/login/{login}")
	public SubscriberDto findSubscriber(@PathVariable String login) {
		return customerService.getSubscriber(login);
	}
	
	@PutMapping("/subscriber/login/{login}/description/{description}")
	public SubscriberDto updateSubscriber(@PathVariable String login, @PathVariable String description) {
		return customerService.updateSubscriber(login, description);
	}
	
	@DeleteMapping("/subscriber/login/{login}")
	public SubscriberDto deleteSubscriber(@PathVariable String login) {
		return customerService.deleteSubscriber(login);
	}
	
	@GetMapping("/customer/accounts/city/{city}")
	public Iterable<AccountDto> getAccountsByCity(@PathVariable String city) {
		return customerService.findAccountsByCity(city);
	}
	
	@GetMapping("/subscribers/login/{loginPattern}/pattern")
	public Iterable<SubscriberDto> findSubscribersByLoginPattern(@PathVariable String loginPattern) {
		return customerService.findSubscribersByLoginPattern(loginPattern);
	}
	
}
