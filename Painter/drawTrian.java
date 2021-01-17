package Painter;

import java.awt.*;
import java.io.*;

public class drawTrian extends selecting implements Serializable {

	private static final long serialVersionUID = 1L;
	private int xPoints, yPoints;
	private int xLength, yLength;
	private int thick;
	private Color color;
	private Polygon trian;
	private boolean fillPaint;

	public drawTrian(int x, int y, int xL, int yL, Color color, int thick) {
		super(color, thick);
		this.xPoints = x;
		this.yPoints = y;
		this.xLength = xL;
		this.yLength = yL;
		this.thick = thick;
		this.color = color;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getColor());
		g2d.setStroke(new BasicStroke(getThickH(), BasicStroke.CAP_ROUND, 0));

		int xp[] = new int[] { (xPoints + xLength / 2), xPoints, (xPoints + xLength) };
		int yp[] = new int[] { yPoints, (yPoints + yLength), (yPoints + yLength) };
		trian = new Polygon(xp, yp, 3);
		g2d.draw(trian);

		if (fillPaint == true)
			g2d.fill(trian);

		if (super.getSelect() == true) {
			g.setColor(new Color(0, 128, 255, 128));
			g.drawOval(xPoints - 5, yPoints - 5, 10, 10);
			g.drawOval(xPoints - 5, yPoints + yLength, 10, 10);
			g.drawOval(xPoints + xLength-5, yPoints - 5, 10, 10);
			g.drawOval(xPoints + xLength-5, yPoints + yLength-5, 10, 10);
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
		int a[] = { xPoints, yPoints, xLength, yLength, 3, thick};
		return a;
	}
	
	public Color getSelectColor() {
		return color;
	};

	public void move(int moveX, int moveY) {
		xPoints = xPoints + moveX;
		yPoints = yPoints + moveY;
	}

	public void resize(int moveX, int moveY) {
		xLength = xLength + moveX;
		yLength = yLength + moveY;
	}
}

class getTrian extends action {
	private Point start;

	public void mousePressed(Point p, drawObject Canvas) {
		start = p;
		drawTrian trian = new drawTrian(p.x, p.y, 0, 0, Canvas.getColor(), Canvas.getThick()); 
		Canvas.add(trian);
	}

	public void mouseDragged(Point p, drawObject Canvas) 
	{
		drawTrian trian = new drawTrian(Math.min(p.x, start.x), Math.min(p.y, start.y), Math.abs(p.x - start.x),
				Math.abs(p.y - start.y), Canvas.getColor(), Canvas.getThick());
		Canvas.redoObject(trian); 
	}

	public void mouseDragShift(Point p, drawObject Canvas) 
	{
		drawTrian tri = new drawTrian(Math.min(p.x, start.x), Math.min(p.y, start.y), Math.abs(p.x - start.x),
				Math.abs(p.x - start.x), Canvas.getColor(), Canvas.getThick());
		Canvas.redoObject(tri); 
	}
}