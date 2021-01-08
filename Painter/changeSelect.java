package Painter;

import java.awt.*;

public class changeSelect extends action {
	private selecting s;

	public void mouseClicked(Point p, drawObject Canvas) {
		s = Canvas.getSelObject(p);
		if (Canvas.getModifying() == true) {
			((selecting) Canvas.getDrawing().get(Canvas.getEditIndex())).setSelect(false);
		}
		if (s != null && Canvas.getModifying() == false) {
			s.setSelect(true);
		}
	}
}
