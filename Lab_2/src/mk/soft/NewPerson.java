package mk.soft;

import java.util.Objects;

public class NewPerson extends Person {

	public NewPerson(String first_name, String last_name) throws PersonException {
		super(first_name, last_name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthYear, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || getClass() != obj.getClass())
			return false;
		

		Person tmp = (Person) obj;
		
		return birthYear == tmp.getBirthYear() && Objects.equals(firstName, tmp.getFirstName())
				&& Objects.equals(lastName, tmp.getLastName());
	}

}
