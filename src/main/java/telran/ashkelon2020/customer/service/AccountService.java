package telran.ashkelon2020.customer.service;

import telran.ashkelon2020.customer.dto.AccountDto;

public interface AccountService {

	boolean addAccount(Integer id, AccountDto accountDto);

	AccountDto getAccount(String login);

	AccountDto updateAccount(Integer id, String login, String newLogin);

	AccountDto deleteAccount(Integer id, String login);

}
