package Painter;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class drawRect extends selecting implements Serializable {

	private static final long serialVersionUID = 1L;
	private int xPoints, yPoints;
	private int xLength, yLength;
	private int thick;
	private Color color;
	private Rectangle2D.Double rect;
	private boolean fillPaint;

	public drawRect(int x, int y, int xL, int yL, Color color, int thick) {
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

		rect = new Rectangle2D.Double(xPoints, yPoints, xLength, yLength); 
		g2d.draw(rect); 

		if (fillPaint == true)
			g2d.fill(rect);

		if (super.getSelect() == true)
		{ 
			g.setColor(new Color(0, 128, 255, 128));
			g.drawOval(xPoints - 5, yPoints -5, 10, 10);
			g.drawOval(xPoints - 5, yPoints + yLength-5, 10, 10); 
			g.drawOval(xPoints + xLength-5, yPoints-5, 10, 10); 
			g.drawOval(xPoints + xLength-5, yPoints+yLength-5, 10, 10); 
		} 
	} 
	public boolean FillPaint(Point p) 
	{
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

	
	public void move(int moveX, int moveY) {
		xPoints = xPoints + moveX; 
		yPoints = yPoints + moveY; 
	}

	public void resize(int moveX, int moveY) {
		xLength = xLength + moveX; 
		yLength = yLength + moveY;
	}

	public int[] getSize() {
		int a[] = { xPoints, yPoints, xLength, yLength, 1, thick };
		return a;
	}
	
	public Color getSelectColor() {
		return color;
	};
}

class getRect extends action {
	private Point start;

	public void mousePressed(Point p, drawObject Canvas) {
		if (Main.points) {
			selecting s = Canvas.getSelObject(p);
			if (s == null) {
				System.out.println("비었습니다");
			}
			int i[] = s.getSize();
			Color color = s.getSelectColor();
			if (i[4] == 0) {
				drawLine line = new drawLine(i[0] + 30, i[1] + 30, i[2] + 30, i[3] + 30, color, i[5]);
				Canvas.add(line);
				Main.points = false;
			} else if (i[4] == 2) {
				drawOval oval = new drawOval(i[0] + 30, i[1] + 30, i[2], i[3], color, i[5]);
				Canvas.add(oval);
				Main.points = false;
			} else if (i[4] == 3) {
				drawTrian trian = new drawTrian(i[0] + 30, i[1] + 30, i[2], i[3], color, i[5]);
				Canvas.add(trian);
				Main.points = false;
			} else {
				drawRect rect = new drawRect(i[0] + 30, i[1] + 30, i[2], i[3], color, i[5]);
				Canvas.add(rect);

				Main.points = false;
			}
		} else {
			start = p; 
			drawRect rect = new drawRect(p.x, p.y, 0, 0, Canvas.getColor(), Canvas.getThick()); 
			Canvas.add(rect);
		}
	}

	public void mouseDragged(Point p, drawObject Canvas) 
	{
		drawRect rect = new drawRect(Math.min(p.x, start.x), Math.min(p.y, start.y), Math.abs(p.x - start.x),
				Math.abs(p.y - start.y), Canvas.getColor(), Canvas.getThick());
		Canvas.redoObject(rect); 
	}

	public void mouseDragShift(Point p, drawObject Canvas) 
	{
		drawRect rect = new drawRect(Math.min(p.x, start.x), Math.min(p.y, start.y), Math.abs(p.x - start.x),
				Math.abs(p.x - start.x), Canvas.getColor(), Canvas.getThick());
		Canvas.redoObject(rect); 
	}
}
