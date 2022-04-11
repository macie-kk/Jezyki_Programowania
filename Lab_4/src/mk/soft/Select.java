package mk.soft;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Select extends JFrame {
	private final Container container = getContentPane();

	public Select() {
		setSize(400, 340);
		setTitle("Wybór");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
				
		addContent();	
	}
	
	private void addContent() {
		JButton runBtn = new JButton("Guzik");
			runBtn.setBounds(50, 125, 100, 50);
			runBtn.addActionListener(e -> {
				RunButton rb = new RunButton();
					rb.setVisible(true);
					rb.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			});
			
		JButton canvas = new JButton("Kanwa");
			canvas.setBounds(234, 125, 100, 50);
			canvas.addActionListener(e -> {
				Kanwa cv = new Kanwa();
					cv.setVisible(true);
					cv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			});
			
		container.add(runBtn);
		container.add(canvas);
	}
}
