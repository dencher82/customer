package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Subscriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4836531318642288040L;
	@Id
	String login;
	String description;
	@ManyToMany // (CHILD)
	@JoinTable(
			name = "SUBSCRIBERS_ACCOUNTS",
			joinColumns = @JoinColumn(name = "SUBSCRIBER_LOGIN"),
			inverseJoinColumns = @JoinColumn(name = "ACCOUNT_LOGIN")
			)
	Set<Account> accounts;
	
	
	
}
