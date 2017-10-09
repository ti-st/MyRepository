package main;

/*
 * Programm Zeichnen4 (Unter Verwendung von Graphics2D statt Graphics)
 * Dem Anwender steht ein Panel, auf das er verschiedene geometrische
 * Figuren zeichnen kann, zur Verfügung. Position und Größe der Figuren
 * können frei bestimmt werden.
 * Die Zeichnungen bleiben beim Neuzeichnen z.B. nach Verschieben des
 * Fensters dauerhaft erhalten.
 * 
 * Hans-Peter Habelitz
 * 2014-10-20
 */

import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.border.LineBorder;

public class Zeichnen4 extends JFrame {

    private JPanel contentPane;
    private JTextField tfPositionX1;
    private JTextField tfPositionY1;
    private JTextField tfPositionX2;
    private JTextField tfPositionY2;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton rdbtnRechteck;
    private JRadioButton rdbtnKreis;
    private JRadioButton rdbtnOval;
    private JRadioButton rdbtnLinie;
    private JCheckBox chckbxGefuellt;
    private JLabel lblPositionX1;
    private JLabel lblPositionY1;
    private JLabel lblPositionX2;
    private JLabel lblPositionY2;
    private char figur = 'R';
    private int x1, y1, x2, y2;
    private Color farbe = Color.black;
    private Zeichenobjekt zo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Zeichnen4 frame = new Zeichnen4();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public Zeichnen4() {
	setTitle("Zeichnen4");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 697, 437);
	contentPane = new JMyPaintPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new MigLayout("", "[grow][]", "[][grow]"));

	JLabel lblZeichenflaeche = new JLabel("Zeichenfl\u00E4che");
	contentPane.add(lblZeichenflaeche, "cell 0 0");

	JMyPaintPanel panelZeichenflaeche = new JMyPaintPanel();
	panelZeichenflaeche.setBorder(new LineBorder(new Color(0, 0, 0)));
	panelZeichenflaeche.setBackground(new Color(255, 255, 255));
	contentPane.add(panelZeichenflaeche, "cell 0 1,grow");

	JPanel panelBedienelemente = new JPanel();
	contentPane.add(panelBedienelemente, "cell 1 1,grow");
	panelBedienelemente.setLayout(new MigLayout("", "[][grow][][][grow]",
		"[][][][][][][][][][][]"));

	lblPositionX1 = new JLabel("Position x:");
	panelBedienelemente.add(lblPositionX1, "cell 0 0,alignx trailing");

	tfPositionX1 = new JTextField();
	tfPositionX1.setText("0");
	panelBedienelemente.add(tfPositionX1, "cell 1 0,growx");
	tfPositionX1.setColumns(10);

	lblPositionY1 = new JLabel("y:");
	panelBedienelemente.add(lblPositionY1, "cell 3 0,alignx trailing");

	tfPositionY1 = new JTextField();
	tfPositionY1.setText("0");
	panelBedienelemente.add(tfPositionY1, "cell 4 0,growx");
	tfPositionY1.setColumns(10);

	lblPositionX2 = new JLabel("Breite:");
	panelBedienelemente.add(lblPositionX2, "cell 0 1,alignx trailing");

	tfPositionX2 = new JTextField();
	tfPositionX2.setText("0");
	panelBedienelemente.add(tfPositionX2, "cell 1 1,growx");
	tfPositionX2.setColumns(10);

	lblPositionY2 = new JLabel("H\u00F6he:");
	panelBedienelemente.add(lblPositionY2, "cell 3 1,alignx trailing");

	tfPositionY2 = new JTextField();
	tfPositionY2.setText("0");
	panelBedienelemente.add(tfPositionY2, "cell 4 1,growx");
	tfPositionY2.setColumns(10);

	rdbtnRechteck = new JRadioButton("Rechteck");
	rdbtnRechteck.setSelected(true);
	rdbtnRechteck.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setzeBeschriftungen();
		chckbxGefuellt.setVisible(true);
		lblPositionY2.setVisible(true);
		tfPositionY2.setVisible(true);
		figur = 'R';
	    }
	});
	buttonGroup.add(rdbtnRechteck);
	panelBedienelemente.add(rdbtnRechteck, "cell 1 3");

	chckbxGefuellt = new JCheckBox("gef\u00FCllt");
	panelBedienelemente.add(chckbxGefuellt, "cell 4 3");

	rdbtnKreis = new JRadioButton("Kreis");
	rdbtnKreis.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setzeBeschriftungen();
		chckbxGefuellt.setVisible(true);
		lblPositionY2.setVisible(false);
		tfPositionY2.setVisible(false);
		figur = 'K';
	    }
	});
	buttonGroup.add(rdbtnKreis);
	panelBedienelemente.add(rdbtnKreis, "cell 1 4");

	rdbtnOval = new JRadioButton("Oval");
	rdbtnOval.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setzeBeschriftungen();
		chckbxGefuellt.setVisible(true);
		lblPositionY2.setVisible(true);
		tfPositionY2.setVisible(true);
		figur = 'O';
	    }
	});
	buttonGroup.add(rdbtnOval);
	panelBedienelemente.add(rdbtnOval, "cell 1 5");

	rdbtnLinie = new JRadioButton("Linie");
	rdbtnLinie.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		setzeBeschriftungen();
		chckbxGefuellt.setVisible(false);
		lblPositionY2.setVisible(true);
		tfPositionY2.setVisible(true);
		figur = 'L';
	    }
	});
	buttonGroup.add(rdbtnLinie);
	panelBedienelemente.add(rdbtnLinie, "cell 1 6");

	JButton btnZeichnen = new JButton("Zeichnen");
	btnZeichnen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    x1 = Integer.parseInt(tfPositionX1.getText());
		    y1 = Integer.parseInt(tfPositionY1.getText());
		    x2 = Integer.parseInt(tfPositionX2.getText());
		    y2 = Integer.parseInt(tfPositionY2.getText());
		    switch (figur) {
		    case 'K':
			zo = new KreisZeichenobjekt(x1, y1, x2, y2, farbe,
				1.0f, chckbxGefuellt.isSelected());
			break;
		    }
		    panelZeichenflaeche.addZeichenobjekt(zo);
		    panelZeichenflaeche.repaint();
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null,
			    "Die Eingaben sind ungültig.");
		}
	    }
	});
	panelBedienelemente.add(btnZeichnen, "cell 1 8,alignx center");

	JButton btnEnde = new JButton("Ende");
	btnEnde.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	panelBedienelemente.add(btnEnde, "cell 1 10,alignx center");
	tfPositionX1.selectAll();

    }

    private void setzeBeschriftungen() {
	if (rdbtnLinie.isSelected()) {
	    lblPositionX1.setText("Startpunkt x:");
	    lblPositionX2.setText("Endpunkt x:");
	    lblPositionY2.setText("y:");
	} else {
	    lblPositionX1.setText("Position x:");
	    if (rdbtnKreis.isSelected()) {
		lblPositionX2.setText("Durchmesser:");
	    } else {
		lblPositionX2.setText("Breite:");
	    }
	    lblPositionY2.setText("Höhe:");
	}
    }
}