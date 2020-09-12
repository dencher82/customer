package telran.ashkelon2020.customer.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2020.customer.model.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerUpdateDto {
	String name;
	LocalDate birthDate;
	String mobilePhone;
	String email;
	Address address;
}
