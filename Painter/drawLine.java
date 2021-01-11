package Painter;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public class drawLine extends selecting implements Serializable {
	private static final long serialVersionUID = 1L;
	private int x1, y1, x2, y2;
	private int thick;
	private Color color;

	public drawLine(int x1, int y1, int x2, int y2, Color color, int thick) {
		super(color, thick);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.thick = thick;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(getThickH(), BasicStroke.CAP_ROUND, 0));
		g2d.setColor(getColor());
		g2d.draw(new Line2D.Double(x1, y1, x2, y2));

		if (super.getSelect() == true) {
			g.setColor(new Color(0, 128, 255, 128));
			g.drawOval(x1, y1, 10, 10);
			g.drawOval(x1, y2, 10, 10);
			g.drawOval(x2, y2, 10, 10);
			g.drawOval(x2, y1, 10, 10);
		}
	}

	public boolean containsPoint(Point p) {
		int left = Math.min(x1, x2);
		int top = Math.min(y1, y2);
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);

		if (p.x >= left && p.x <= left + width && p.y >= top && p.y <= top + height)
			return true;
		else
			return false;
	}

	public void move(int moveX, int moveY) {
		x1 = x1 + moveX;
		y1 = y1 + moveY;
		x2 = x2 + moveX;
		y2 = y2 + moveY;
	}

	public void resize(int moveX, int moveY) {
		x2 = x2 + moveX;
		y2 = y2 + moveY;
	}

	public int[] getSize() {
		int a[] = { x1, y1, x2, y2, 0, thick};
		return a;
	}

	public boolean FillPaint(Point p) {
		return true;
	}

	public Color getSelectColor() {
		return color;
	};
}

class getLine extends action {
	private int x1, y1;

	public void mousePressed(Point p, drawObject Canvas) {
		x1 = p.x;
		y1 = p.y;

		drawLine line = new drawLine(x1, y1, x1, y1, Canvas.getColor(), Canvas.getThick());
		Canvas.add(line);
	}

	public void mouseDragged(Point p, drawObject Canvas) {
		drawLine line = new drawLine(x1, y1, p.x, p.y, Canvas.getColor(), Canvas.getThick());

		Canvas.redoObject(line);
	}

}

class getPen extends action{
	private int x1, y1;
	private Point LastP;

	public void mousePressed(Point p, drawObject Canvas) {
		x1 = p.x;
		y1 = p.y;

		drawLine pen = new drawLine(x1, y1, x1, y1, Canvas.getColor(), Canvas.getThick());
		Canvas.add(pen);
		LastP = p;
	}

	public void mouseDragged(Point p, drawObject Canvas) {
		drawLine pen = new drawLine(LastP.x, LastP.y, p.x, p.y, Canvas.getColor(), Canvas.getThick());
		LastP = p;
		Canvas.add(pen);
	}

}
