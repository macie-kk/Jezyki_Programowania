package mk.soft;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import java.awt.Color;

public class Window extends JFrame {
	private final HashMap<String, char[]> USERS = new HashMap<String, char[]>();
	private final Container container = getContentPane();
	
	public static void main(String[] args) {
		Window window = new Window();
		window.setVisible(true);
	}
	
	public Window() {
		setSize(400, 340);
		setTitle("Logowanie");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		createUsers();
		addContent();	
	}
	
	private void addContent() {
		JTextField login = new JTextField();
		JPasswordField password = new JPasswordField();
		
	    JLabel passwordLabel = new JLabel("PASSWORD");
       		passwordLabel.setBounds(75,120,100,30);
       	
       	JLabel loginLabel = new JLabel("LOGIN");
       		loginLabel.setBounds(75,70,100,30);
		
		JButton loginButton = new JButton("LOGIN");
			loginButton.setBounds(210, 180, 100, 30);
			loginButton.setEnabled(false);
			loginButton.addActionListener(e -> {
				final boolean isCorrect = verifyUser(login.getText(), password.getPassword());
				container.setBackground(isCorrect ? new Color(179, 232, 125) : new Color(232, 88, 88));		// zmiana koloru w zaleznosci od weryfikacji uzytkownika
			});
					
		login.setBounds(160,70,150,30);
		login.addCaretListener(e -> {
			loginButton.setEnabled(login.getText().length() != 0 && password.getPassword().length != 0);	// wylaczanie guzika jezeli haslo lub login sa puste
		});
			
		password.setBounds(160,120,150,30);
		password.addCaretListener(e -> {
			loginButton.setEnabled(login.getText().length() != 0 && password.getPassword().length != 0);	// wylaczanie guzika jezeli haslo lub login sa puste
		});
		
		JButton resetButton = new JButton("RESET");
		resetButton.setBounds(75, 180, 100, 30);
		resetButton.addActionListener(e -> {
			container.setBackground(new Color(238, 238, 238));
			password.setText("");
			login.setText("");
		});

		container.add(login);
		container.add(password);
		container.add(passwordLabel);
		container.add(loginLabel);
		container.add(resetButton);
		container.add(loginButton);
        
		/* Tworzenie menu */
        JMenuItem userList = new JMenu("Users");
        USERS.keySet().forEach(user -> {
        	JMenuItem userItem = new JMenuItem(user);
	    		userItem.addActionListener(e -> {	// event na wpisanie nazwy uzytkownika do pola po kliknieciu
	    			login.setText(user);
	    		});
        	userList.add(userItem);
        });
        
        JMenuBar menuBar = new JMenuBar();
        	menuBar.add(userList);
        setJMenuBar(menuBar);
	}
	
	private boolean verifyUser(String login, char[] password) {
		return Arrays.equals(password, USERS.get(login));
	}
	
	private void createUsers() {
		USERS.put("foo", "bar".toCharArray());
		USERS.put("root", "toor123".toCharArray());
		USERS.put("Malik", "Montana".toCharArray());
		USERS.put("zaq1", "@WSX".toCharArray());
		USERS.put("Administrator", "asdfghjkl".toCharArray());
	}
}
