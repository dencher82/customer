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

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDeleteDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.CustomerUpdateDto;
import telran.ashkelon2020.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	@PostMapping
	public boolean addCustomer(@RequestBody CustomerDto customerDto) {
		return customerService.addCustomer(customerDto);
	}
	
	@GetMapping("/id/{id}")
	public CustomerDto findCustomer(@PathVariable Integer id) {
		return customerService.getCustomer(id);
	}
	
	@PutMapping("/id/{id}")
	public CustomerDto updateCustomer(@PathVariable Integer id, @RequestBody CustomerUpdateDto customerUpdateDto) {
		return customerService.updateCustomer(id, customerUpdateDto);
	}
	
	@DeleteMapping("/id/{id}")
	public CustomerDeleteDto deleteCustomer(@PathVariable Integer id) {
		return customerService.deleteCustomer(id);
	}
	
	@GetMapping("/accounts/city/{city}")
	public Iterable<AccountDto> getAccountsByCity(@PathVariable String city) {
		return customerService.findAccountsByCity(city);
	}
	
}
