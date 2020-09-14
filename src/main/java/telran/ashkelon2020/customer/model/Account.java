package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"login"})
@Entity
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6779120156297153483L;
	@Id
	String login;
	String description;
	@ManyToMany(mappedBy = "accounts") // (PARENT)
	Set<Subscriber> subscribers;
	@ManyToOne // (CHILD)
	Customer customer;
	
	public Account(String login, String description, Customer customer) {
		this.login = login;
		this.description = description;
		this.customer = customer;
	}
	
}
