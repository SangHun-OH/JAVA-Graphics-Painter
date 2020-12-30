package Painter;

import java.awt.*;

public class changeFill extends action {
	public void mouseClicked(Point p, drawObject Canvas) {
		selecting s = Canvas.getSelObject(p);
		if (s != null) {
			if (s.containsPoint(p)) {
				s.setColor(Canvas.getColor());
				s.FillPaint(p);
			}
		}
	}
}
