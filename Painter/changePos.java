package Painter;

import java.awt.*;

public class changePos extends action {
	private Point lastPoint;
	private selecting s;

	public void mousePressed(Point p, drawObject Canvas) {
		s = Canvas.getSelObject(p);
		if (s != null) {
			lastPoint = p;
		}
	}

	public void mouseDragged(Point p, drawObject Canvas) {
		if (s != null && Canvas.getEditIndex() == Canvas.getCurrIndex(s)) {
			s.move(p.x - lastPoint.x, p.y - lastPoint.y);
			lastPoint = p;
		}
	}
}
