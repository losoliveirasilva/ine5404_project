package graphics;

import java.awt.Graphics;


public class Linha implements Drawable {
	Ponto a, b;

	public Linha(Ponto a, Ponto b) {
		this.a = a;
		this.b = b;
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawLine(a.x, a.y, b.x, b.y);

	}
}
