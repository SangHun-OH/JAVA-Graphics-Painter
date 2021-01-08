package Painter;

import java.awt.*;

public class drawErase extends selecting {
	private static final long serialVersionUID = 1L;
	private int x1, y1, x2, y2, thick;
	private Color color;

	public drawErase(int x1, int y1, int x2, int y2, Color color, int thick) {
		super(color, thick);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.thick = thick;
		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(this.x1, this.y1, thick, thick);
		g.fillOval(this.x2, this.y2, thick, thick);
	}

	public boolean FillPaint(Point p) {
		return true;
	}

	public boolean containsPoint(Point p) {
		return false;
	}

	public void move(int moveX, int moveY) {
	}

	public void resize(int moveX, int moveY) {
	}

	public int[] getSize() {
		return null;
	}

	public Color getSelectColor() {
		return null;
	};
}

class getErase extends action {
	private int x1, y1;
	private Point LastP;

	public void mousePressed(Point p, drawObject Canvas) {
		x1 = p.x;
		y1 = p.y;
		drawErase eraser = new drawErase(x1, y1, x1, y1, Color.WHITE, Canvas.getThick());
		Canvas.add(eraser);
		LastP = p;
	}

	public void mouseDragged(Point p, drawObject Canvas) {
		drawErase eraser = new drawErase(LastP.x, LastP.y, x1, y1, Color.WHITE, Canvas.getThick());
		LastP = p;
		Canvas.add(eraser);
	}

}