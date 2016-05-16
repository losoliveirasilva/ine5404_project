package graphics;

import java.awt.Graphics;

public class Ponto implements Drawable {
	public int x, y;

	public Ponto(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawOval(this.x, this.y, 10, 10);
	}
}
