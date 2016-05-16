package graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener{
	int i;
	public Mouse(){
	};
	
	public void mouseClicked(MouseEvent e) {
		int x,y;
		x = e.getX();
		y = e.getY();
		System.out.println(x + " " + y);
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
