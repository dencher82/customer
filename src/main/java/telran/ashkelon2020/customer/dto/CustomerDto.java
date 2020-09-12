package telran.ashkelon2020.customer.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {
	Integer id;
	String name;
	LocalDate birthDate;
	String mobilePhone;
	String email;
	Address address;
	Set<Account> accounts;
	
}
