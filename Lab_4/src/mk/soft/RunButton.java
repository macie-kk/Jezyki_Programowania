package mk.soft;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class RunButton extends JFrame {
	private final Container container = getContentPane();
	private final Random r = new Random();
		
	public RunButton() {
		setSize(400, 340);
		setTitle("Guzik");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
				
		addContent();	
	}
	
	private void addContent() {
		final int buttonW = 90;
		final int buttonH = 30;
		
		JButton clickMe = new JButton("Click me");
			clickMe.setBounds(30, 30, 90, 30);
						
		clickMe.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				container.setBackground(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(e.getX() < 70) {
					final int nextX = r.nextInt(380 - buttonW) + 10;	// = Szerokosc okna - szerokosc menu - szerokosc przycisku - margin 20px
					final int nextY = r.nextInt(290 - buttonH) + 10;	// = Wysokosc okna - wysokosc menu - wysokosc przycisku - margin 20px
					
					clickMe.setLocation(nextX, nextY);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {};
		});
		
		container.add(clickMe);
	}
}
