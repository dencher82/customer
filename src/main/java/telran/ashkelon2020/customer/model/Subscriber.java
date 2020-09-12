package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "subscribers")
public class Subscriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4836531318642288040L;
	@Id
	String id;
	String name;
	LocalDate birthDate;
	String mobilePhone;
	String email;
	Address address;
	
}
