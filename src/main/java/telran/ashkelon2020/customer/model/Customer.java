package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "customers")
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
	@OneToMany
	@JsonIgnore
	Set<Account> accounts;
	
	public boolean addAccount(Account account) {
		return accounts.add(account);
	}
	
	public boolean removeAccount(Account account) {
		return accounts.remove(account);
	}
	
}
