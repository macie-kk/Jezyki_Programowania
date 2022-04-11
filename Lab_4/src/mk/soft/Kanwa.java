package mk.soft;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Kanwa extends JFrame {
	
	/*
	 * 	Wcisnij 'K' aby narysowac Kolo
	 * 	Wcisnij 'P' aby narysowac Prostokat
	 * 
	 */
	
	private final Random r = new Random();
	private String currentShape = null;
	
	public Kanwa() {
		setSize(400, 340);
		setTitle("Kanwa");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {		
				switch(e.getKeyChar()) {
					case 'k':
						currentShape = "kolo";
						repaint();
						break;
					case 'p':
						currentShape = "prostokat";
						repaint();
						break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		if(currentShape == null) return;
		
		Graphics2D g2d = (Graphics2D) getGraphics();
		
		Point p = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(p, this);
		
		final int x = (int) p.getX();
		final int y = (int) p.getY();
		
		final int size = r.nextInt(50)+10;

    	switch(currentShape) {
        	case "kolo":
        		g2d.drawOval(x, y, size, size);
        		break;
        	case "prostokat":
        		g2d.drawRect(x, y, size, size);
        		break;
    	}
    }

}
