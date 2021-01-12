package Painter;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class drawOval extends selecting implements Serializable {
	private static final long serialVersionUID = 1L;
	private int xPoints, yPoints;
	private int xLength, yLength;
	private int thick;
	private Color color;
	private Ellipse2D.Double ellipse;
	private boolean fillPaint;

	public drawOval(int x, int y, int xL, int yL, Color color, int thick) {
		super(color, thick);
		this.xPoints = x;
		this.yPoints = y;
		this.xLength = xL;
		this.yLength = yL;
		this.color = color;
		this.thick = thick;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(getThickH(), BasicStroke.CAP_ROUND, 0));
		g2d.setColor(getColor());
		ellipse = new Ellipse2D.Double(xPoints, yPoints, xLength, yLength);
		g2d.draw(ellipse);

		if (fillPaint == true)
			g2d.fill(ellipse);

		if (super.getSelect() == true) {
			g.setColor(new Color(0, 128, 255, 128));
			g.drawOval(xPoints - 5, yPoints - 5, 10, 10);
			g.drawOval(xPoints - 5, yPoints + yLength, 10, 10);
			g.drawOval(xPoints + xLength - 5, yPoints - 5, 10, 10);
			g.drawOval(xPoints + xLength - 5, yPoints + yLength - 5, 10, 10);
		}
	}

	public boolean FillPaint(Point p) {
		if (p.x >= xPoints && p.x <= xPoints + xLength && p.y >= yPoints && p.y <= yPoints + yLength)
			return this.fillPaint = true;
		else
			return this.fillPaint = false;
	}

	public boolean containsPoint(Point p) {
		if (p.x >= xPoints && p.x <= xPoints + xLength && p.y >= yPoints && p.y <= yPoints + yLength)
			return true;
		else
			return false;
	}

	public int[] getSize() {
		int a[] = { xPoints, yPoints, xLength, yLength, 2, thick };
		return a;
	}

	public void move(int moveX, int moveY) {
		xPoints = xPoints + moveX;
		yPoints = yPoints + moveY;
	}

	public void resize(int moveX, int moveY) {
		xLength = xLength + moveX;
		yLength = yLength + moveY;
	}

	public Color getSelectColor() {
		return color;
	};
}

class getOval extends action {
	private Point start;

	public void mousePressed(Point p, drawObject Canvas) {
		start = p;
		drawOval oval = new drawOval(p.x, p.y, 0, 0, Canvas.getColor(), Canvas.getThick());
		Canvas.add(oval);
	}

	public void mouseDragged(Point p, drawObject Canvas) {
		drawOval oval = new drawOval(Math.min(p.x, start.x), Math.min(p.y, start.y), Math.abs(p.x - start.x),
				Math.abs(p.y - start.y), Canvas.getColor(), Canvas.getThick());
		Canvas.redoObject(oval);
	}

	public void mouseDragShift(Point p, drawObject Canvas) {
		drawOval oval = new drawOval(Math.min(p.x, start.x), Math.min(p.y, start.y), Math.abs(p.x - start.x),
				Math.abs(p.x - start.x), Canvas.getColor(), Canvas.getThick());
		Canvas.redoObject(oval);
	}
}