package Painter;

import java.awt.Point;
import java.awt.event.*;

public class actionPerforming implements ActionListener {
	public action actions;
	
	public void actionPerformed(ActionEvent event) {
		int i = Integer.parseInt(event.getActionCommand());
		switch (i) {
		case 1:
			actions = new getPen();
			break;
		case 2:
			actions = new getLine();
			break;
		case 3:
			actions = new getOval();
			break;
		case 4:
			actions = new getRect();
			break;
		case 5:
			actions = new getTrian();
			break;
		case 6:
			actions = new changeFill();
			break;
		case 7:
			actions = new getErase();
			break;
		case 8:
			actions = new changeSelect();
			break;
		case 9:
			actions = new changePos();
			break;
		case 10:
			actions = new changeSize();
			break;
		case 11:
			actions = new getRect();
			break;
		default:
			break;
		}
	}
}

class action {
	public void buttonClicked(Point P, drawObject Canvas) {
	};

	public void mouseMove(Point p, drawObject Canvas) {
	};

	public void mouseClicked(Point p, drawObject Canvas) {
	};

	public void mousePressed(Point p, drawObject Canvas) {
	};

	public void mouseDragged(Point p, drawObject Canvas) {
	};

	public void mouseDragShift(Point p, drawObject Canvas) {
	};

	public void mouseReleased(Point p, drawObject Canvas) {
	};
}

