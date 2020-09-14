package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8105841816420176828L;
	@Id
	Integer id;
	String name;
	LocalDate birthDate;
	String mobilePhone;
	String email;
	Address address;
	@OneToMany(mappedBy = "customer") // (PARENT)
	Set<Account> accounts; //= new HashSet<>(); use 'new' only when we have method 'addAccount'
	
}
