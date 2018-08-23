package de.dhbwka.java.exercise._vorlagen;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

import javax.swing.*;

@SuppressWarnings("serial")
public class KlausurHelfer extends JFrame {

	// -KONSTRUKTOR->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public KlausurHelfer() {
		super("KlausurHelfer");
		JTabbedPane tab = new JTabbedPane();
		this.setContentPane(tab);

		// NAVIGATION DURCH DIE KLASSE:
		// ALLES WAS MIT /* KLICK>> */ ... /* <<KLICK */ MARKIERT IST,
		// *RECHTSKLICK* -> OPEN DECLARATION
		// IN ECLIPSE AUCH F3 ODER STRG-KLICK

		tab.addTab("IO        ", /* KLICK>> */IOTAB()/* <<KLICK */);
		tab.addTab("COMPONENTS", /* KLICK>> */COMPONENTSTAB()/* <<KLICK */);
		tab.addTab("LAYOUTS   ", /* KLICK>> */LAYOUTSTAB()/* <<KLICK */);
		tab.addTab("EVENTS    ", /* KLICK>> */EVENTSTAB()/* <<KLICK */);
		tab.addTab("THREADS   ", /* KLICK>> */THREADSTAB()/* <<KLICK */);
		tab.addTab("PAINT     ", /* KLICK>> */PAINTTAB()/* <<KLICK */);
		tab.addTab("DATASTRUCT", /* KLICK>> */DATASTRUCTTAB()/* <<KLICK */);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 480);
		this.setResizable(false);
		this.setVisible(true);
	}

	// -IO->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public JPanel IOTAB() {
		JPanel ioPanel = new JPanel();
		ioPanel.setLayout(new FlowLayout());

		JTextField textField = new JTextField("");
		JTextArea textArea = new JTextArea("");
		JButton button = new JButton("Zur Datei hinzufügen");

		textField.setPreferredSize(new Dimension(300, 30));
		ioPanel.add(textField);

		button.addActionListener((e) -> {
			/* !! */writeFile(textField.getText())/* !! */;
			textField.setText("");
			textArea.setText(/* !! */readFile()/* !! */);
		});
		ioPanel.add(button);

		ioPanel.add(new JLabel("Inhalt der Datei \"myDir/foo\":"));

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		textArea.setText(/* !! */readFile()/* !! */);

		ioPanel.add(scrollPane);

		return ioPanel;
	}

	private void writeFile(String s) {
		// ORDNER ERSTELLEN
		File myDir = new File("myDir");
		myDir.mkdir();

		// DATEI ERSTELLEN
		File fooFile = new File(myDir, "foo");
		try {
			fooFile.createNewFile();
		} catch (IOException e) {
			System.err.println("Error creating File " + fooFile.getAbsolutePath());
		}

		// DATEI SCHREIBEN
		try (PrintWriter pw = new PrintWriter(new FileWriter("myDir/foo", true))) {
			pw.println(s);
			pw.close();
		} catch (IOException e) {
			System.err.println("Fehler beim Schreiben: " + e.getMessage());
		}
	}

	private String readFile() {
		String content = "";
		try (BufferedReader br = new BufferedReader(new FileReader("myDir/foo"))) {
			for (int i = 0; br.ready(); i++)
				content += "Zeile " + i + ":  " + br.readLine() + "\n";
			br.close();
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen: " + e.getMessage());
		}
		return content;
	}

	// -COMPONENTS->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public JPanel COMPONENTSTAB() {
		JPanel componentPanel = new JPanel();

		componentPanel.setLayout(new BoxLayout(componentPanel, BoxLayout.PAGE_AXIS));
		componentPanel.add(new JLabel("JLabel"));
		componentPanel.add(new JTextField("JTextField"));
		componentPanel.add(new JPasswordField("JPasswordField"));
		componentPanel.add(new JButton("JButton"));
		componentPanel.add(new JToggleButton("JToggleButton"));
		componentPanel.add(new JCheckBox("JCheckBox"));
		componentPanel.add(new JComboBox<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		ButtonGroup group = new ButtonGroup();
		for (int i = 1; i <= 3; i++) {
			JRadioButton rb = new JRadioButton("Radio-Button-" + i);
			group.add(rb);
			componentPanel.add(rb);
		}

		return componentPanel;
	}

	// -LAYOUTS->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public JPanel LAYOUTSTAB() {
		JPanel layoutPanel = new JPanel();

		JPanel borderPanel = new JPanel();
		JPanel flowPanel = new JPanel();
		JPanel gridPanel = new JPanel();

		layoutPanel.setLayout(
				new GridLayout(2/* anzahl spalten */, 2/* anzahl zeilen */));
		// 0 = so viele wie nötig
		layoutPanel.add(borderPanel);
		layoutPanel.add(flowPanel);
		layoutPanel.add(gridPanel);

		borderPanel.setLayout(new BorderLayout(2, 2));
		borderPanel.add(new JButton("BORDER_N"), BorderLayout.NORTH);
		borderPanel.add(new JButton("BORDER_S"), BorderLayout.SOUTH);
		borderPanel.add(new JButton("BORDER_W"), BorderLayout.WEST);
		borderPanel.add(new JButton("BORDER_E"), BorderLayout.EAST);
		borderPanel.add(new JButton("BORDER_C"), BorderLayout.CENTER);
		borderPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

		flowPanel.setLayout(new FlowLayout());
		flowPanel.add(new JButton("FLOW_1"));
		flowPanel.add(new JButton("FLOW_2"));
		flowPanel.add(new JButton("FLOW_3"));
		flowPanel.add(new JButton("FLOW_4"));
		flowPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 3));

		gridPanel.setLayout(new GridLayout(2, 2));
		gridPanel.add(new JButton("GRID_1,1"));
		gridPanel.add(new JButton("GRID_1,2"));
		gridPanel.add(new JButton("GRID_2,1"));
		gridPanel.add(new JButton("GRID_2,2"));
		gridPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));

		return layoutPanel;
	}

	// -EVENTS->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public JPanel EVENTSTAB() {
		JPanel eventPanel = new JPanel();

		JButton button = new JButton("Klick mich");
		JTextField textField = new JTextField("Schreib was");
		JToggleButton toggle = new JToggleButton("Toggle mich");

		JLabel jl1 = new JLabel();
		JLabel jl2 = new JLabel();
		JLabel jl3 = new JLabel();

		// LAMBDA LISTENER
		button.addActionListener((e) -> {
			jl1.setText("Geklickt!");
		});

		// UNTERKLASSE LISTENER
		textField
				.addKeyListener(new /* KLICK>> */UnterListener(jl2)/* <<KLICK */);

		// ANONYMER INNERER LISTENER
		toggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (toggle.isSelected())
					jl3.setText("AN");
				else
					jl3.setText("AUS");
			}
		});

		eventPanel.setLayout(new GridLayout(0, 2));
		eventPanel.add(button);
		eventPanel.add(jl1);
		eventPanel.add(textField);
		eventPanel.add(jl2);
		eventPanel.add(toggle);
		eventPanel.add(jl3);

		return eventPanel;
	}

	private class UnterListener implements KeyListener {
		JLabel label;

		private UnterListener(JLabel l) {
			label = l;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			label.setText(e.getKeyChar() + " gedrückt.");
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

	// -THREADS->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public JPanel THREADSTAB() {
		JPanel threadPanel = new JPanel();
		JLabel label = new JLabel("PARTEY");
		label.setFont(new Font("Arial", Font.BOLD, 150));
		threadPanel.add(label);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					threadPanel.setBackground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
							(int) (Math.random() * 255)));
					label.setForeground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
							(int) (Math.random() * 255)));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		return threadPanel;
	}

	// -PAINT->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public JPanel PAINTTAB() {
		final int MARGIN_LEFT = 10;
		final int MARGIN_RIGHT = 10;
		final int MARGIN_TOP = 50;
		Color TOP_COL = new Color(0, 71, 129);
		Color BG_COL = new Color(121, 170, 211);
		Color RULER_COL = new Color(62, 133, 192);
		Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		String INFO_TEXT = "in %";

		final Party[] parties = { new Party(33.0f, "Union", Color.BLACK), new Party(20.5f, "SPD", Color.RED),
				new Party(12.6f, "AfD", Color.BLUE), new Party(10.7f, "FDP", Color.YELLOW),
				new Party(9.2f, "Linke", Color.MAGENTA), new Party(8.9f, "Gr\u00FCne", Color.GREEN),
				new Party(5.1f, "Andere", Color.DARK_GRAY) };
		double maxPercentageOfParties = 40f;

		JPanel paintPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				final int width = this.getWidth();
				final int height = this.getHeight();

				int maxBarWidth = (width - MARGIN_LEFT - MARGIN_RIGHT) / parties.length;
				// Max height of bar = 2/3 of component height
				int totalBarHeight = height * 2 / 3;
				// height for 1 percent point
				double heightPerPercent = totalBarHeight / maxPercentageOfParties;
				// After calculating height for 1 percent, add margin top to
				// total height
				totalBarHeight += MARGIN_TOP;
				g.setFont(FONT);
				g.setColor(BG_COL); // Background color
				g.fillRect(0, 0, width, height); // Background rectangle
				// Top background bar to write title on
				g.setColor(TOP_COL);
				g.fillRect(0, 0, width, 30);
				g.setColor(RULER_COL); // set line/ruler color
				g.fillRect(0, 30, width, 2); // line below top background bar
				// Thick line above bottom white bar
				g.fillRect(0, totalBarHeight, width, 10);
				// line below bottom white bar
				g.fillRect(0, totalBarHeight + 40, width, 2);
				// line below percentage of parties
				g.fillRect(0, totalBarHeight + 65, width, 2);
				g.setColor(Color.WHITE);
				// Bottom white bar
				g.fillRect(0, totalBarHeight + 10, width, 30);
				// Top title on the left
				g.drawString("BUNDESTAGSWAHL 1337", MARGIN_LEFT, 22);
				// Top info on the right
				g.drawString(INFO_TEXT, width - 40, 22);
				// thin white helper lines (10% intervals)
				for (int i = 1; i <= (int) (maxPercentageOfParties / 10); i++) {
					g.drawLine(0, (int) (totalBarHeight - i * 10 * heightPerPercent), width,
							(int) (totalBarHeight - i * 10 * heightPerPercent));
				}
				// draw parties with helper function
				for (int i = 0; i < parties.length; i++) {
					int partyHeight = (int) (parties[i].getPercentage() * heightPerPercent);
					this.drawBar(g, MARGIN_LEFT + i * maxBarWidth, totalBarHeight - partyHeight, maxBarWidth,
							partyHeight, parties[i]);
				}
			}

			public void drawBar(Graphics g, int x, int y, int width, int height, Party party) {
				int margin = 10;
				int shadow = 2;
				// draw shadow rectangle
				g.setColor(java.awt.Color.GRAY);
				g.fillRect(x + margin + shadow, y - shadow + 1, width - 2 * margin, height);
				g.setColor(party.getColor()); // draw actual colored bar over
												// shadow
				// above line
				g.fillRect(x + margin, y, width - 2 * margin, height);
				// below line
				g.fillRect(x + margin, y + height + 10, width - 2 * margin, 10);
				g.setColor(java.awt.Color.BLACK);
				// Draw name of party
				g.drawString(party.getName(), x + margin, y + height + 36);
				// Draw percentage value, but replace decimal '.' with ','
				g.drawString(Float.toString(party.getPercentage()).replace('.', ','), x + margin + 5, y + height + 60);
			}
		};

		return paintPanel;
	}

	private class Party {
		private float percentage;
		private String name;
		private Color color;

		public Party(float percentage, String name, Color color) {
			this.percentage = percentage;
			this.name = name;
			this.color = color;
		}

		public float getPercentage() {
			return this.percentage;
		}

		public String getName() {
			return this.name;
		}

		public Color getColor() {
			return this.color;
		}
	}

	// -DATASTRUCT->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public JPanel DATASTRUCTTAB() {
		JPanel structPanel = new JPanel();
		BinaryTree<String> tree = new BinaryTree<>();

		JPanel treePanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.BLACK);
				paintTree(g, tree, 0, this.getWidth(), 20);
			}

			private <T extends Comparable<T>> void paintTree(Graphics g, BinaryTree<T> tree, int leftbound,
					int rightbound, int y) {
				if (tree.value != null) {
					g.drawString((String) tree.getValue(), (leftbound + rightbound) / 2, y);
				}
				if (tree.left != null) {
					g.drawLine((leftbound + rightbound) / 2, y, (leftbound * 3 + rightbound) / 4, y + 15);
					paintTree(g, tree.left, leftbound, (leftbound + rightbound) / 2, y + 30);
				}
				if (tree.right != null) {
					g.drawLine((leftbound + rightbound) / 2, y, (leftbound + rightbound * 3) / 4, y + 15);
					paintTree(g, tree.right, (leftbound + rightbound) / 2, rightbound, y + 30);
				}
			}
		};
		treePanel.setPreferredSize(new Dimension(400, 350));
		treePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(300, 30));
		JButton button = new JButton("ADD");
		button.addActionListener((e) -> {
			tree.add(field.getText());
			field.setText("");
			treePanel.repaint();
			System.out.println(tree.traverse());
		});

		structPanel.add(field);
		structPanel.add(button);
		structPanel.add(treePanel);

		return structPanel;
	}

	private class BinaryTree<T extends Comparable<T>> {
		private T value;
		private BinaryTree<T> left;
		private BinaryTree<T> right;

		public BinaryTree() {
		}

		/**
		 * Recursive adding of newValue (no duplicates allowed).
		 *
		 * @param newValue
		 *            new value to add
		 *
		 * @return <code>true</code> if value has been added, <code>false</code>
		 *         otherwise
		 */
		public boolean add(T newValue) {
			if (this.value == null) {
				this.value = newValue;
			} else if (this.value.compareTo(newValue) == 0) {
				return false;
			} else if (this.value.compareTo(newValue) > 0) {
				return this.getLeft().add(newValue);
			} else if (this.value.compareTo(newValue) < 0) {
				return this.getRight().add(newValue);
			}
			return true;
		}

		/**
		 * Get the node's value
		 */
		public T getValue() {
			return this.value;
		}

		/**
		 * Get left node, if none is present (<code>this.left==null</code>)
		 * create a new node
		 *
		 * @return left
		 */
		private BinaryTree<T> getLeft() {
			if (this.left == null) {
				this.left = new BinaryTree<>();
			}
			return this.left;
		}

		/**
		 * Get right node, if none is present (<code>this.left==null</code>)
		 * create a new node
		 *
		 * @return right
		 */
		private BinaryTree<T> getRight() {
			if (this.right == null) {
				this.right = new BinaryTree<>();
			}
			return this.right;
		}

		/**
		 * Traverse BinaryTree in ascending order and
		 *
		 * @return all values in an ordered List<T>
		 */
		public List<T> traverse() {
			List<T> l = new LinkedList<>();
			this.doTraverse(l);
			return l;
		}

		/**
		 * Traverse BinaryTree in ascending order and add all values to list.
		 *
		 * @param list
		 *            list to add values to
		 */
		private void doTraverse(List<T> list) {
			if (this.left != null) {
				this.left.doTraverse(list);
			}
			if (this.value != null) {
				list.add(this.value);
			}
			if (this.right != null) {
				this.right.doTraverse(list);
			}
		}
	}

	// -MAIN->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public static void main(String[] args) {
		new KlausurHelfer();
	}
}
