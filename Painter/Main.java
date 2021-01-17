package Painter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JFrame implements ActionListener {

	private JMenuBar menubar;
	private JMenu file;
	private JMenuItem openMenu, saveAs, loadImage, delImage;

	private actionPerforming actionPerform;
	private final Color fColor = Color.BLACK;
	private int thick = 5;

	private drawObject Canvas = new drawObject(fColor, thick);
	private Label currColorLB;
	private JLabel currStatusLB, currXLB, currYLB, thickLabel;

	JLabel imagelabel = new JLabel();

	private JTextField inputText;
	private String string = "";

	private JButton penButton, eraserButton, textButton, selectButton, lineButton, rectButton, ovalButton, triButton,
			resizeButton, moveButton, deleteButton, fillcolorButton, undoButton, redoButton, copyButton, upButton,
			downButton;

	private JButton newButton, openButton, saveButton;

	public GraphicsPanel DrawPanel;

	private String fileName = "Noname.cha";
	private File saveFile;

	private boolean isSelected = true;
	public static boolean points = false;

	public static void main(String args[]) {
		Main appl = new Main();
		appl.setVisible(true);
		appl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appl.setBackground(new Color(238, 243, 244));
		appl.setTitle("Java Paint Program");
		appl.setSize(1088, 912);
		appl.setBackground(Color.WHITE);
	}

	public Main() {
		super();
		actionPerform = new actionPerforming();
		setTitle(fileName);

		JPanel MenuPanel = new JPanel();
		MenuPanel.setLayout(new GridLayout(1, 1));

		menubar = new JMenuBar();
		menubar.setBackground(new Color(175, 187, 205, 255));
		file = new JMenu("Files");
		file.setForeground(Color.WHITE);

		menubar.add(file);

		openMenu = new JMenuItem("Open File");
		file.add(openMenu);
		openMenu.addActionListener(this);

		saveAs = new JMenuItem("Save As");
		file.add(saveAs);
		saveAs.addActionListener(this);

		file.addSeparator();

		loadImage = new JMenuItem("Load Image");
		file.add(loadImage);
		loadImage.addActionListener(new loadImage());

		delImage = new JMenuItem("Delete Image");
		file.add(delImage);
		delImage.addActionListener(new delImage());

		file.addSeparator();

		JMenuItem exit = new JMenuItem("Exit");
		exit.setBackground(new Color(238, 243, 244));
		file.add(exit);
		exit.addActionListener(new exitMenu());

		MenuPanel.add(menubar);
		MenuPanel.setBackground(new Color(238, 243, 244));
		MenuPanel.setBounds(0, 2, 998, 34);
		add(MenuPanel);
		// Implements 패널 -------------------------------------------------------//
		JPanel Implements = new JPanel();
		setBackground(new Color(238, 243, 244));
		// kit 패널

		JPanel Implements_kit = new JPanel();
		Implements_kit.setLayout(new GridLayout(16, 1));

		penButton = new JButton(new ImageIcon("pen.png"));
		penButton.addActionListener(this);
		penButton.addActionListener(actionPerform);
		penButton.setActionCommand("1");
		penButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(penButton);

		lineButton = new JButton(new ImageIcon("line.png"));
		lineButton.addActionListener(this);
		lineButton.addActionListener(actionPerform);
		lineButton.setActionCommand("2");
		lineButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(lineButton);

		ovalButton = new JButton(new ImageIcon("oval.png"));
		ovalButton.addActionListener(this);
		ovalButton.addActionListener(actionPerform);
		ovalButton.setActionCommand("3");
		ovalButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(ovalButton);

		rectButton = new JButton(new ImageIcon("Rect.png"));
		rectButton.addActionListener(this);
		rectButton.addActionListener(actionPerform);
		rectButton.setActionCommand("4");
		rectButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(rectButton);

		triButton = new JButton(new ImageIcon("tri.png"));
		triButton.addActionListener(this);
		triButton.addActionListener(actionPerform);
		triButton.setActionCommand("5");
		triButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(triButton);

		fillcolorButton = new JButton(new ImageIcon("FillColor.png"));
		fillcolorButton.addActionListener(this);
		fillcolorButton.addActionListener(actionPerform);
		fillcolorButton.setActionCommand("6");
		fillcolorButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(fillcolorButton);

		eraserButton = new JButton(new ImageIcon("eraser.png"));
		eraserButton.addActionListener(this);
		eraserButton.addActionListener(actionPerform);
		eraserButton.setActionCommand("7");
		eraserButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(eraserButton);

		selectButton = new JButton(new ImageIcon("Select.png"));
		selectButton.addActionListener(this);
		selectButton.addActionListener(actionPerform);
		selectButton.setActionCommand("8");
		selectButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(selectButton);

		moveButton = new JButton(new ImageIcon("Move.png"));
		moveButton.addActionListener(this);
		moveButton.addActionListener(actionPerform);
		moveButton.setActionCommand("9");
		moveButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(moveButton);

		resizeButton = new JButton(new ImageIcon("Resize.png"));
		resizeButton.addActionListener(this);
		resizeButton.addActionListener(actionPerform);
		resizeButton.setActionCommand("10");
		resizeButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(resizeButton);

		deleteButton = new JButton(new ImageIcon("Delete.png"));
		deleteButton.addActionListener(this);
		deleteButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(deleteButton);

		upButton = new JButton(new ImageIcon("up.png"));
		upButton.addActionListener(this);
		upButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(upButton);

		downButton = new JButton(new ImageIcon("down.png"));
		downButton.addActionListener(this);
		downButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(downButton);

		copyButton = new JButton(new ImageIcon("copy.png"));
		copyButton.addActionListener(this);
		copyButton.addActionListener(actionPerform);
		copyButton.setActionCommand("11");
		copyButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(copyButton);

		undoButton = new JButton(new ImageIcon("Undo.png"));
		undoButton.addActionListener(this);
		undoButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(undoButton);

		redoButton = new JButton(new ImageIcon("Redo.png"));
		redoButton.addActionListener(this);
		redoButton.setBackground(new Color(238, 243, 244));
		Implements_kit.add(redoButton);

		// 색 패널 생성----------------------------------------------------------##
		JPanel ImplementsColor = new JPanel();
		ImplementsColor.setLayout(null);
		JButton SetColorPanel = new JButton(new ImageIcon("selColor.png"));
		SetColorPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				currStatusLB.setText("색변경");
				Color color = JColorChooser.showDialog(Main.this, "Color Select", fColor);

				if (color == null)
					color = fColor;
				currColorLB.setBackground(color);
				Canvas.setColor(color);
			}
		});

		ImplementsColor.add(SetColorPanel);
		SetColorPanel.setBackground(new Color(238, 243, 244));
		SetColorPanel.setBounds(0, 0, 70, 70);
//------------------------------------------------------------------------//

		JPanel ImplementsState = new JPanel();
		ImplementsState.setBorder(BorderFactory.createEtchedBorder());

		thickLabel = new JLabel("5", thickLabel.CENTER);

		// 현재 상태 패널
		ImplementsState.setLayout(new GridLayout(4, 2));
		ImplementsState.setBackground(new Color(238, 243, 244));

		Label PointLabel = new Label("굵기", Label.CENTER);
		ImplementsState.add(PointLabel, BorderLayout.NORTH);
		ImplementsState.add(thickLabel);

		Label CurrentColor = new Label("색상", Label.CENTER);
		ImplementsState.add(CurrentColor, BorderLayout.NORTH);
		currColorLB = new Label();
		currColorLB.setBackground(fColor);
		ImplementsState.add(currColorLB);

		Label xLabel = new Label("X", Label.CENTER);
		Label yLabel = new Label("Y", Label.CENTER);
		ImplementsState.add(xLabel, BorderLayout.CENTER);
		ImplementsState.add(yLabel, BorderLayout.CENTER);
		currXLB = new JLabel("0", currXLB.CENTER);
		ImplementsState.add(currXLB);
		currYLB = new JLabel("0", currYLB.CENTER);
		ImplementsState.add(currYLB);
//------------------------------------------------------------------------//
		JPanel ImplementsState2 = new JPanel();
		ImplementsState2.setBorder(BorderFactory.createEtchedBorder());
		ImplementsState2.setLayout(new GridLayout(2, 1));
		ImplementsState2.setBackground(new Color(238, 243, 244));

		Label CurrentStatus = new Label("상태", Label.CENTER);
		// CurrentStatus.setFont(new Font("돋움", Font.BOLD, 13));
		ImplementsState2.add(CurrentStatus, BorderLayout.NORTH);

		currStatusLB = new JLabel("선택안함", currStatusLB.CENTER);
		ImplementsState2.add(currStatusLB);
//-------------------------------------------------------------------------//

		// 메뉴 패널 생성
		JPanel ToolBar_Menu = new JPanel();
		ToolBar_Menu.setLayout(new GridLayout(3, 1));

		newButton = new JButton(new ImageIcon("new-page.png"));
		newButton.setBackground(new Color(205, 200, 255));
		newButton.addActionListener(this);
		ToolBar_Menu.add(newButton);

		openButton = new JButton(new ImageIcon("folder.png"));
		openButton.setBackground(new Color(205, 200, 255));
		openButton.addActionListener(this);
		ToolBar_Menu.add(openButton);

		saveButton = new JButton(new ImageIcon("save.png"));
		saveButton.setBackground(new Color(205, 200, 255));
		saveButton.addActionListener(this);
		ToolBar_Menu.add(saveButton);

		Implements.setLayout(null);
		Implements_kit.setBounds(1000, 0, 70, 560);
		Implements.add(Implements_kit);
		ImplementsState.setBounds(1000, 560, 70, 90);
		Implements.add(ImplementsState);
		ImplementsState2.setBounds(1000, 650, 70, 50);
		Implements.add(ImplementsState2);
		ImplementsColor.setBounds(1000, 700, 70, 70);
		Implements.add(ImplementsColor);
		ToolBar_Menu.setBounds(1000, 770, 70, 100);
		Implements.add(ToolBar_Menu);
//------------------------------------------------------------------------//
		// 그림이 그려지는 패널
		DrawPanel = new GraphicsPanel();
		DrawPanel.setBackground(Color.WHITE);

		DrawPanel.setBounds(0, 35, 1000, 835);
		add(DrawPanel);
		Implements.setBounds(1000, 10, 120, 800);
		add(Implements);

		setResizable(false);
	}

	private void Open() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.CANCEL_OPTION)
			return;

		File file = fileChooser.getSelectedFile();

		if ((file == null) || (file.getName().equals(""))) {
			JOptionPane.showMessageDialog(this, "파일명 입력", "파일명 입력", JOptionPane.ERROR_MESSAGE);
		}

		else {
			try {

				ObjectInputStream open = new ObjectInputStream(new FileInputStream(file));
				Vector newobject = (Vector) open.readObject();
				Canvas.setDrawing(newobject);
				open.close();
				repaint();
				saveFile = file;
				fileName = saveFile.getName();
				setTitle("경로 : " + saveFile.getPath() + "파일 : " + fileName);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void Save() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int result = fileChooser.showSaveDialog(this);

		if (result == JFileChooser.CANCEL_OPTION)
			return;

		File file = fileChooser.getSelectedFile();

		if ((file == null) || (file.getName().equals(""))) {
			JOptionPane.showMessageDialog(this, "파일명이 없습니다", "파일명이 없습니다", JOptionPane.ERROR_MESSAGE);
		}

		try {
			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(file));
			save.writeObject(Canvas.getDrawing());
			save.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		if (file != saveFile) {
			saveFile = file;
			fileName = saveFile.getName();
			setTitle("경로 : " + saveFile.getPath() + " 파일 : " + fileName);
		}

	}

	public void actionPerformed(ActionEvent event) {

		DrawPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		Object action = event.getSource();

		if (action == newButton) {
			Canvas.newObject();
			repaint();
			setTitle("newObject");
			saveFile = null;
		} else if (action == openButton || action == openMenu) {
			Open();
		} else if (action == saveButton || action == saveAs) {
			Save();
		}

		else if (action == penButton) {
			currStatusLB.setText("펜");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if (action == lineButton) {
			currStatusLB.setText("선");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if (action == ovalButton) {
			currStatusLB.setText("원");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if (action == rectButton) {
			currStatusLB.setText("사각형");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if (action == triButton) {
			currStatusLB.setText("삼각형");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if (action == textButton) {
			currStatusLB.setText("텍스트삽입");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

			JFrame text = new JFrame("텍스트 입력");
			text.setVisible(true);
			text.setSize(350, 120);
			text.setLocation(400, 400);

			inputText = new JTextField(10);
			inputText.setToolTipText("텍스트를 입력해주세요.");
			text.add(inputText);

			inputText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == inputText) {
						string = String.format("%s", e.getActionCommand());
					}
				}
			});

		} else if (action == selectButton) {
			currStatusLB.setText("객체 선택");
			isSelected = true;
		}

		else if (action == fillcolorButton) {
			currStatusLB.setText("색 채우기");
			isSelected = true;
		}

		else if (action == moveButton) {
			currStatusLB.setText("객체 이동");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}

		else if (action == eraserButton) {
			currStatusLB.setText("지우개");
			isSelected = true;
			DrawPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		else if (action == resizeButton) {
			currStatusLB.setText("크기 변경");
			DrawPanel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
		}

		else if (action == undoButton) {
			currStatusLB.setText("입력 취소");
			Canvas.undo();
			repaint();
		}

		else if (action == redoButton) {
			currStatusLB.setText("입력 실행");
			Canvas.redo();
			repaint();
		} else if (action == upButton) {
			currStatusLB.setText("굵게");
			thick++;
			thickLabel.setText(Integer.toString(thick));
			Canvas.setThick(thick);
		}

		else if (action == downButton) {
			currStatusLB.setText("얇게");
			if (thick == 1) {
			} else
				thick--;
			thickLabel.setText(Integer.toString(thick));
			Canvas.setThick(thick);
		}

		else if (action == copyButton) {
			currStatusLB.setText("복사");
			isSelected = true;
			points = true;
			if (Canvas.getModifying() == true) {
				repaint();
				revalidate();
			}
		}

		else if (action == deleteButton) {
			currStatusLB.setText("객체 삭제");
			if (Canvas.getModifying() == true) {
				Canvas.delete();
				repaint();
			}
		}
	}

	// DrawPanel
	private class GraphicsPanel extends JPanel implements MouseInputListener {

		public GraphicsPanel() {
			addMouseListener(this);
			addMouseMotionListener(this);
		}

		public void paint(Graphics g) {
			super.paint(g);
			Canvas.draw(g);
		}

		public void mouseClicked(MouseEvent event) {
			try {

				if (!(event.isMetaDown()) && !(event.isAltDown())) {

					actionPerform.actions.mouseClicked(event.getPoint(), Canvas);
					repaint();
				}
			} catch (Exception e) {
			}

		}

		public void mousePressed(MouseEvent event) {
			try {

				if (!(event.isMetaDown()) && !(event.isAltDown())) {
					actionPerform.actions.mousePressed(event.getPoint(), Canvas);
					repaint();
				}
			} catch (Exception e) {
			}

		}

		public void mouseDragged(MouseEvent event) {
			try {
				if (!(event.isMetaDown()) && !(event.isAltDown()) && event.isShiftDown()) {
					actionPerform.actions.mouseDragShift(event.getPoint(), Canvas);
					repaint();
				} else if (!(event.isMetaDown()) && !(event.isAltDown())) {
					actionPerform.actions.mouseDragged(event.getPoint(), Canvas);
					repaint();
				}
			} catch (Exception e) {
			}

		}

		public void mouseReleased(MouseEvent event) {
			try {
				actionPerform.actions.mouseReleased(event.getPoint(), Canvas);
				repaint();
			} catch (Exception e) {
			}
		}

		public void mouseEntered(MouseEvent event) {
		}

		public void mouseExited(MouseEvent event) {
		}

		public void mouseMoved(MouseEvent event) {
			try {

				if (!(event.isMetaDown()) && !(event.isAltDown())) {
					currXLB.setText(String.format("%d", event.getX()));
					currYLB.setText(String.format("%d", event.getY()));

					repaint();
				}
			} catch (Exception e) {
			}
		}
	}

	private static File imgOpen() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("background"));
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			return f;
		}
		return null;
	}

	class loadImage implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File src = imgOpen();
			BufferedImage m;

			try {
				m = ImageIO.read(src);
				imagelabel.setIcon(new ImageIcon(m));
				DrawPanel.add(imagelabel);
				DrawPanel.revalidate();
			}

			catch (Exception err) {
			}
		}
	}

	class delImage implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (imagelabel.getIcon() != null) {
				imagelabel.setIcon(null);
			}
		}
	}

	class exitMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
