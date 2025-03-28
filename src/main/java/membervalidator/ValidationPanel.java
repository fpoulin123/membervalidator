package membervalidator;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ValidationPanel extends JPanel {
	private static final int RECT_X = 40;
	private static final int RECT_Y = 40;
	private static final int RECT_WIDTH = 200;
	private static final int RECT_HEIGHT = 100;
	   
	   
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
		
   }

}
