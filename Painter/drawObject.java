package Painter;

import java.util.*;
import java.awt.*;
import java.io.*;

public class drawObject implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	public static Vector<selecting> saveObject;
	private Vector<selecting> temp = new Vector<selecting>();
	private Color curColor;
	private int thick;
	private selecting s;
	public static int i = 0;

	public drawObject(Color color, int firstThick) {
		saveObject = new Vector<selecting>();
		curColor = color;
		thick = firstThick;
	}

	public void delete() {
		saveObject.remove(getEditIndex());
	}

	public void add(selecting object) {
		saveObject.add(object);
	}

	public void remove(selecting object) {
		saveObject.remove(saveObject.indexOf(object));
	}

	public void newObject() {
		saveObject.removeAllElements();
	}

	public void undo() {
		if (saveObject.size() > 0) {
			temp.add(saveObject.lastElement());
			saveObject.remove(saveObject.lastElement());
		}
	}

	public void redo() {
		if (temp.size() > 0) {
			saveObject.add(temp.lastElement());
			temp.remove(temp.lastElement());
		}
	}

	public void setColor(Color color) {
		curColor = color;
	}

	public Color getColor() {
		return curColor;
	}

	public void setThick(int tThick) {
		thick = tThick;
	}

	public int getThick() {
		return thick;
	}

	public Vector<selecting> getDrawing() {
		return saveObject;
	}

	public void setDrawing(Vector<selecting> save) {
		saveObject = save;
	}

	public void redoObject(selecting object) {
		saveObject.remove(saveObject.lastElement());
		saveObject.add(object);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < saveObject.size(); i++)
			(saveObject.get(i)).draw(g);
	}

	public static int getEditIndex() {
		for (int i = 0; i <= saveObject.size() - 1; i++)
			if ((saveObject.get(i)).getSelect() == true)
				return i;
		return 0;
	}

	public int getCurrIndex(selecting object) {
		for (int i = 0; i <= saveObject.size() - 1; i++)
			if (i == saveObject.indexOf(object))
				return i;
		return 0;
	}

	public boolean getModifying() {
		if (saveObject.isEmpty() == true || (saveObject.get(getEditIndex())).getSelect() == false)
			return false;
		return true;
	}

	public selecting getSelObject(Point p) {
		for (int index = saveObject.size() - 1; index >= 0; index--)
			if ((saveObject.get(index)).containsPoint(p))
				return saveObject.get(index);
		return null;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
