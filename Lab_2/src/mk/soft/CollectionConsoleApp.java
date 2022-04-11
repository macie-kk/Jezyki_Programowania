package mk.soft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class CollectionConsoleApp {
	private static final String MENU = 
			"    M E N U   G L O W N E  		\n" +
			"1 - Podaj dane nowej osoby 		\n" + 
			"2 - Usun dane wybranej osoby   	\n" + 
			"3 - Modyfikuj dane osoby   		\n" + 
			"4 - Zapisz osobe do list 			\n" + 
			"5 - Usun dane z list       		\n" + 
			"6 - Wypisz dane z list     		\n" +
			"7 - Comparable vs Comparator		\n" +
			"8 - Dodaj dane do newPerson 	\n" +
			"0 - Zakoncz program        		\n";
	
	private static final String DELETE_DATA_MENU = 
			"    U S U N   D A N E		\n" +
			"1 - Usun dane z HashSet 	\n" + 
			"2 - Usun dane z TreeSet 	\n" + 
			"3 - Usun dane z ArrayList 	\n" + 
			"4 - Usun dane z LinkedList \n" +
			"5 - Usun dane z HashMap 	\n" + 
			"6 - Usun dane z TreeMap 	\n" + 
			"0 - Powrot do menu glownego\n";
	
	private static final String COMPARABLE_VS_COMPARATOR =
			"    C O M P A R A B L E 		\n" + 
			"            V S                \n" +
			"    C O M P A R A T O R        \n\n" +
			"1 - Posortuj z comparable 		\n" + 
			"2 - Posortuj z comparator		\n" + 
			"0 - Powrot do menu glownego	\n";
	

	private static final String CHANGE_MENU = 
			"   Co zmienic?     \n" + 
			"1 - Imie           \n" + 
			"2 - Nazwisko       \n" + 
			"3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" + 
			"0 - Powrot do menu glownego\n";
	
	private static final ConsoleUserDialog UI = new ConsoleUserDialog();

	public static void main(String[] args) {
		CollectionConsoleApp application = new CollectionConsoleApp();
		application.runMainLoop();
	}

	private Person currentPerson = null;
	private NewPerson currentNewPerson = null;
	// Tworzenie zbiorow z klasy NewPerson
	private HashSet<Person> newPeopleHashSet = new HashSet<>();
	private TreeSet<Person> newPeopleTreeSet = new TreeSet<>();

	// Tworzenie list z klasy person
	private HashSet<Person> peopleHashSet = new HashSet<>();
	private TreeSet<Person> peopleTreeSet = new TreeSet<>();
	private ArrayList<Person> peopleArrayList = new ArrayList<>();
	private LinkedList<Person> peopleLinkedList = new LinkedList<>();
	private Map<Integer, Person> peopleHashMap = new HashMap<>();
	private TreeMap<Integer, Person> peopleTreeMap = new TreeMap<>();
	private int key = 0;

	public void runMainLoop() {

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "\n> ")) {
				case 1:
					currentPerson = createPerson();

					break;
				case 2:
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zosta�y usuni�te");
					break;
				case 3:
					if (currentPerson == null)
						throw new PersonException("�adna osoba nie zosta�a utworzona.");
					changePersonData(currentPerson);
					break;
				case 4: {
					if (currentPerson == null)
						throw new PersonException("�adna osoba nie zosta�a utworzona.");
					peopleHashSet.add(currentPerson);
					peopleTreeSet.add(currentPerson);
					peopleArrayList.add(currentPerson);
					peopleLinkedList.add(currentPerson);
					peopleHashMap.put(++key, currentPerson);
					peopleTreeMap.put(key, currentPerson);
					break;
				}
				case 5: {
					deleteDataFromList();
					break;
				}
				case 6: {
					showLists();
					break;
				}
				case 7: {
					comparableVsComparator();
					break;
				}
				case 8: {
					currentNewPerson = createNewPerson();
					newPeopleHashSet.add(currentNewPerson);
					newPeopleTreeSet.add(currentNewPerson);
					break;
				}
				case 0:
					UI.printInfoMessage("\nProgram zako�czy� dzia�anie!");
					System.exit(0);
				}
			} catch (PersonException e) {

				UI.printErrorMessage(e.getMessage());
			}
		}
	}

	void showCurrentPerson() {
		showPerson(currentPerson);
	}

	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();

		if (person != null) {
			sb.append("Aktualna osoba: \n").append("      Imi�: ").append(person.getFirstName()).append("\n")
					.append("  Nazwisko: ").append(person.getLastName()).append("\n").append("   Rok ur.: ")
					.append(person.getBirthYear()).append("\n").append("Stanowisko: ").append(person.getJob())
					.append("\n");
		} else
			sb.append("Brak danych osoby\n");
		UI.printMessage(sb.toString());
	}

	void comparableVsComparator() {
		while (true) {
			UI.clearConsole();
			switch (UI.enterInt(COMPARABLE_VS_COMPARATOR + "> ")) {
			case 1:
				Collections.sort(peopleArrayList);
				return;
			case 2:
				FNComparator fncomparator = new FNComparator();
				Collections.sort(peopleArrayList, fncomparator);
				return;
			case 0:
				return;
			}
		}
	}

	void showLists() {
		String str = "";

		str += "HashSet:  	";
		for (Person person : peopleHashSet) {
			str += person + "; ";
		}

		str += "\nTreeSet: 	";
		System.out.println();
		for (Person person : peopleTreeSet) {
			str += person + "; ";
		}

		str += "\nArrayList:  	";
		System.out.println();
		for (Person person : peopleArrayList) {
			str += person + "; ";
		}
		
		str += "\nLinkedList:	";
		for (Person person : peopleLinkedList) {
			str += person + "; ";
		}
		
		str += "\nHashHashMap:	";
		for (Map.Entry<Integer, Person> people : peopleHashMap.entrySet()) {
			str += people.getKey() + "=" + people.getValue() + ";";
		}
		
		str += "\nTreeMap:   	";
		for (Map.Entry<Integer, Person> people : peopleTreeMap.entrySet()) {
			str += people.getKey() + "=" + people.getValue() + ";";
		}
		
		str += "\nNew HashSet:  	";
		for (Person person : newPeopleHashSet) {
			str += person + "; ";
		}
		
		str += "\nNew TreeSet: 	";
		System.out.println();
		for (Person person : newPeopleTreeSet) {
			str += person + "; ";
		}
		str += '\n';
		UI.printInfoMessage(str);
	}

	static NewPerson createNewPerson() {
		String first_name = UI.enterString("Podaj imi�: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		NewPerson person;
		try {
			person = new NewPerson(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	static Person createPerson() {
		String first_name = UI.enterString("Podaj imi�: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try {
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}

	void deleteDataFromList() {
		while (true) {
			UI.clearConsole();
			switch (UI.enterInt(DELETE_DATA_MENU + "\n> ")) {
			case 1:
				peopleHashSet.remove(createPerson());
				return;
			case 2:
				peopleTreeSet.remove(createPerson());
				return;
			case 3:
				peopleArrayList.remove(UI.enterInt("Podaj index: "));
				return;
			case 4:
				peopleLinkedList.remove(UI.enterInt("Podaj index: "));
				return;
			case 5:
				peopleHashMap.remove(UI.enterInt("Podaj index: "));
				return;
			case 6:
				peopleTreeMap.remove(UI.enterInt("Podaj index: "));
				return;
			case 7:
				newPeopleHashSet.remove(createNewPerson());
				return;
			case 8:
				newPeopleTreeSet.remove(createNewPerson());
				return;
			case 0:
				return;
			}
		}
	}

	static void changePersonData(Person person) {
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {
				switch (UI.enterInt(CHANGE_MENU + "\n> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imi�: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0:
					return;
				}
			} catch (PersonException e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}

}