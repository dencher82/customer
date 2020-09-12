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
import telran.ashkelon2020.customer.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired AccountService accountService;
	
	@PostMapping("/id/{id}")
	public boolean addAccount(@PathVariable Integer id, @RequestBody AccountDto accountDto) {
		return accountService.addAccount(id, accountDto);
	}
	
	@GetMapping("/login/{login}")
	public AccountDto findAccount(@PathVariable String login) {
		return accountService.getAccount(login);
	}
	
	@PutMapping("/id/{id}/login/{login}/newlogin/{newlogin}")
	public AccountDto updateAccount(@PathVariable Integer id, @PathVariable String login, @PathVariable String newlogin) {
		return accountService.updateAccount(id, login, newlogin);
	}
	
	@DeleteMapping("/id/{id}/login/{login}")
	public AccountDto deleteAccount(@PathVariable Integer id, @PathVariable String login) {
		return accountService.deleteAccount(id, login);
	}
	
	
}
