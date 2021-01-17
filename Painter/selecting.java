package Painter;

import java.awt.*;
import java.io.Serializable;

public abstract class selecting implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private Color color;
	private int thickH;
	private boolean Selected;

	public selecting(Color initColor, int thick) {
		color = initColor;
		thickH = thick;
	}

	public abstract void draw(Graphics g);

	public abstract boolean containsPoint(Point p);

	public abstract void move(int addX, int addY);

	public abstract void resize(int addX, int addY);

	public abstract boolean FillPaint(Point p);

	public abstract int[] getSize();

	public abstract Color getSelectColor();

	public Color getColor() {
		return color;
	}

	public void setColor(Color newColor) {
		color = newColor;
	}

	public int getThickH() {
		return thickH;
	}

	public void setThickH(int a) {
		thickH = a;
	}

	public boolean getSelect() {
		return Selected;
	}

	public void setSelect(boolean b) {
		Selected = b;
	}

	public void mouseMove(Point p, drawObject canvas) {
	}

	public void mouseClicked(Point p, drawObject canvas) {
	}

	public void mousePressed(Point p, drawObject canvas) {
	}

	public void mouseDragged(Point p, drawObject canvas) {
	}

	public void mouseDragShift(Point p, drawObject canvas) {
	}

	public void mouseReleased(Point p, drawObject canvas) {
	}

}
