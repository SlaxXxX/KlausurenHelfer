package de.dhbwka.java.exercise._vorlagen;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
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

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 480);
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

	// -MAIN->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public static void main(String[] args) {
		new KlausurHelfer();
	}
}
