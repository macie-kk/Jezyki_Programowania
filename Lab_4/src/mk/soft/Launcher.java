package mk.soft;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Select().setVisible(true);
		});
	}
}
