package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "accounts")
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6779120156297153483L;
	@Id
	String login;
	String password;
	@ManyToMany
	Set<Subscriber> subscribers;
	
	public Account(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
}
