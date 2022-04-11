package mk.soft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;

enum PersonJob {
	UNKNOWN("-------"), GUEST("Go��"), STUDENT("Student"), TEACHER("Nauczyciel"), MANAGER("Kierownik"),
	DIRECTOR("Dyrektor");

	String jobName;

	private PersonJob(String job_name) {
		jobName = job_name;
	}

	@Override
	public String toString() {
		return jobName;
	}

}

class PersonException extends Exception {

	private static final long serialVersionUID = 1L;

	public PersonException(String message) {
		super(message);
	}

}

public class Person implements Comparable<Person> {

	protected String firstName;
	protected String lastName;
	protected int birthYear;
	protected PersonJob job;

	public Person(String first_name, String last_name) throws PersonException {
		setFirstName(first_name);
		setLastName(last_name);
		job = PersonJob.UNKNOWN;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) throws PersonException {
		if ((first_name == null) || first_name.equals(""))
			throw new PersonException("Pole <Imi�> musi by� wype�nione.");
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) throws PersonException {
		if ((last_name == null) || last_name.equals(""))
			throw new PersonException("Pole <Nazwisko> musi by� wype�nione.");
		this.lastName = last_name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birth_year) throws PersonException {
		if ((birth_year != 0) && (birth_year < 1900 || birth_year > 2030))
			throw new PersonException("Rok urodzenia musi by� w przedziale [1900 - 2030].");
		this.birthYear = birth_year;
	}

	public void setBirthYear(String birth_year) throws PersonException {
		if (birth_year == null || birth_year.equals("")) { // pusty �a�cuch znak�w oznacza rok niezdefiniowany
			setBirthYear(0);
			return;
		}
		try {
			setBirthYear(Integer.parseInt(birth_year));
		} catch (NumberFormatException e) {
			throw new PersonException("Rok urodzenia musi by� liczb� ca�kowit�.");
		}
	}

	public PersonJob getJob() {
		return job;
	}

	public void setJob(PersonJob job) {
		this.job = job;
	}

	public void setJob(String job_name) throws PersonException {
		if (job_name == null || job_name.equals("")) { // pusty �a�cuch znak�w oznacza stanowisko niezdefiniowane
			this.job = PersonJob.UNKNOWN;
			return;
		}
		for (PersonJob job : PersonJob.values()) {
			if (job.jobName.equals(job_name)) {
				this.job = job;
				return;
			}
		}
		throw new PersonException("Nie ma takiego stanowiska.");
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	public static void printToFile(PrintWriter writer, Person person) {
		writer.println(person.firstName + "#" + person.lastName + "#" + person.birthYear + "#" + person.job);
	}

	public static void printToFile(String file_name, Person person) throws PersonException {
		try (PrintWriter writer = new PrintWriter(file_name)) {
			printToFile(writer, person);
		} catch (FileNotFoundException e) {
			throw new PersonException("Nie odnaleziono pliku " + file_name);
		}
	}

	public static Person readFromFile(BufferedReader reader) throws PersonException {
		try {
			String line = reader.readLine();
			String[] txt = line.split("#");
			Person person = new Person(txt[0], txt[1]);
			person.setBirthYear(txt[2]);
			person.setJob(txt[3]);
			return person;
		} catch (IOException e) {
			throw new PersonException("Wyst�pi� b��d podczas odczytu danych z pliku.");
		}
	}

	public static Person readFromFile(String file_name) throws PersonException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(file_name)))) {
			return Person.readFromFile(reader);
		} catch (FileNotFoundException e) {
			throw new PersonException("Nie odnaleziono pliku " + file_name);
		} catch (IOException e) {
			throw new PersonException("Wyst�pi� b��d podczas odczytu danych z pliku.");
		}
	}

	@Override
	public int compareTo(Person o) {
		return getLastName().compareTo(o.getLastName());
	}

}

//Komparator
class FNComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		return p1.getFirstName().compareTo(p2.getFirstName());
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
}
