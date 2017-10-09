package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Shape;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.event.ActionEvent;



public class NBodyproblem extends JFrame {
    
    private JPanel contentPane;
    private JMyPaintPanel paintPane;
    private JPanel interfacePane;
    private JLabel lblPositionX;
    private JLabel lblPositionY;
    private JLabel lblMass;
    private JLabel lblkg;
    private JLabel lblVelocity;
    private JLabel lblVelX;
    private JLabel lblVelY;
    private JLabel lblms1;
    private JLabel lblms2;
    private JLabel lblScale;
    private JTextField tfPositionX;
    private JTextField tfPositionY;
    private JTextField tfMass;
    private JTextField tfVelX;
    private JTextField tfVelY;
    private JButton btnAddBody;
    private JButton btnClear;
    private JButton btnStart;
    private JButton btnStop;
    private JButton btnEnd;
    private JButton btnClearAll;
    private JButton btnPlus;
    private JButton btnMinus;
    
    private ArrayList<MassObject> Bodys = new ArrayList<MassObject>();
    private ArrayList<MassObject> BodysTemp = new ArrayList<MassObject>();
    private KreisZeichenobjekt circel;
    private RechteckZeichenobjekt blank;

    private boolean run;
    
    private final double defaultScaling = 1;		//Streckenskalierung, 1 Pixel entspricht scaling m (Hier 0.01AE)
    private final double time = 0.05; 		//Timescaling (in Sekunden), je kleiner, desto genauer wird die Bahn
    private final double cGamma = 6.674E-11;
    private double scaling = 1;
    private double scalingFactor = 10;			
    
    private int f = 2000;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    NBodyproblem frame = new NBodyproblem();
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
    public NBodyproblem() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1024, 752);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new MigLayout("", "[grow][]", "[grow]"));
	
	paintPane = new JMyPaintPanel();
	paintPane.setBackground(Color.WHITE);
	paintPane.setBorder(new LineBorder(new Color(0, 0, 0)));
	contentPane.add(paintPane, "cell 0 0,grow");
	
	interfacePane = new JPanel();
	interfacePane.setLayout(new MigLayout("", "[][][][][][]", "[][][][][][][][][][]"));
	contentPane.add(interfacePane, "cell 1 0,alignx right,growy");
	
	lblPositionX = new JLabel("X:");
	interfacePane.add(lblPositionX, "cell 0 0");
	
	tfPositionX = new JTextField("");
	tfPositionX.setColumns(5);
	interfacePane.add(tfPositionX, "cell 1 0");
	
	lblPositionY = new JLabel("Y:");
	interfacePane.add(lblPositionY, "cell 3 0");
	
	tfPositionY = new JTextField("");
	tfPositionY.setColumns(5);
	interfacePane.add(tfPositionY, "cell 4 0");
	
	lblMass = new JLabel("Mass:");
	interfacePane.add(lblMass, "cell 0 1");
	
	tfMass = new JTextField("");
	tfMass.setColumns(10);
	interfacePane.add(tfMass, "cell 1 1");
	
	lblkg = new JLabel("E20 kg");
	interfacePane.add(lblkg, "cell 2 1");
	
	lblVelocity = new JLabel("Velocity");
	interfacePane.add(lblVelocity, "cell 0 2");
	
	lblVelX = new JLabel("Vx");
	interfacePane.add(lblVelX, "cell 0 3");
	
	tfVelX = new JTextField("");
	tfVelX.setColumns(10);
	interfacePane.add(tfVelX, "cell 1 3");
	
	lblms1 = new JLabel("m/s");
	interfacePane.add(lblms1, "cell 2 3");
	
	lblVelY = new JLabel("Vy");
	interfacePane.add(lblVelY, "cell 3 3");
	
	tfVelY = new JTextField("");
	tfVelY.setColumns(10);
	interfacePane.add(tfVelY, "cell 4 3");
	
	lblms2 = new JLabel("m/s");
	interfacePane.add(lblms2, "cell 5 3");
	
	lblScale = new JLabel("Scaling: 1:" + (int)scaling);
	interfacePane.add(lblScale, "cell 3 9");
	
	//paintingComponent.setPreferredSize(new Dimension(paintPane.getWidth(),paintPane.getHeight()));
        //unsere Komponente wird mittig im JFrame plaziert 
	//paintPane.add(paintingComponent,BorderLayout.LINE_START);
		
	btnAddBody = new JButton("Add body");
	btnAddBody.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {		//Erzeugt ein Objekt der Klasse MassObject und übergibt die Eingabedaten
		    MassObject Obj = new MassObject((Double.parseDouble(tfMass.getText())*Math.pow(10, 20)), 
			    Double.parseDouble(tfVelX.getText()), 
			    Double.parseDouble(tfVelY.getText()), 
			    (int)(defaultScaling*Double.parseDouble(tfPositionX.getText())), 
			    (int)(defaultScaling*Double.parseDouble(tfPositionY.getText()))
			    );
		    Bodys.add(Obj);					//Speichert anschließend das erzeugte Objekt in der Liste ab
		    ClearTextfields();					//Leert anschließend die Textfelder und springt zurück zum ersten
		    
		    //TODO: Gespeichertes Objekt in Pane einzeichnen!!!
		}
	});
	interfacePane.add(btnAddBody, "cell 1 4");
	
	btnClear = new JButton("Clear");
	btnClear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {		//Löscht alle Textfelder und springt zum ersten Textfeld
		    ClearTextfields();					
		}
	});
	interfacePane.add(btnClear, "cell 4 4");
	
	btnStart = new JButton("Start");
	btnStart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    run = true;
		    while (f>0){				//Berechnung der neuen Koordinaten
			CalcThread ct = new CalcThread();
			DrawThread dt = new DrawThread();
			ct.setList(Bodys);			//Übergabe der Objekte
			ct.setGamma(cGamma);			//Übergabe der Konfigurationen
			ct.setTime(time);
			ct.setDefScaling(defaultScaling);
			ct.setEdit(false);
			dt.setDefScaling(defaultScaling);	//Übergabe der Konfiguration
			dt.setScaling(scaling);	
			dt.setPane(paintPane);			//Übergabe der Zeichenfläche
			ct.start();
			dt.start();
			for(int i=0; i<Bodys.size(); i++){
			    Bodys.get(i).equals(ct.getList(i));
				}
			ct.setEdit(true);
			
			
			}
			f--;
		    }
			//run = false;			//MUSS REMOVED WERDEN
		    
	});
	interfacePane.add(btnStart, "cell 0 6");
	
	btnEnd = new JButton("End");
	btnEnd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    System.exit(0);
		}
	});
	interfacePane.add(btnEnd, "cell 3 6");
	
	btnStop = new JButton("Stop");
	btnStop.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    run = false;
		}
	});
	interfacePane.add(btnStop, "cell 0 7");
	
	btnClearAll = new JButton("Clear all");
	btnClearAll.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    ClearTextfields();
		    Bodys.clear();
		    BodysTemp.clear();
		    //TODO: ZEICHENPANE leeren!
		}
	});
	interfacePane.add(btnClearAll, "cell 3 7");
	
	btnPlus = new JButton("+");
	btnPlus.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    scaling = scaling*scalingFactor;
		}
	});
	interfacePane.add(btnPlus, "cell 0 9");
	
	btnMinus = new JButton("-");
	btnMinus.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    scaling = scaling/scalingFactor;
		}
	});
	interfacePane.add(btnMinus, "cell 1 9");
	
    }

    void ClearTextfields(){
	tfPositionX.setText("");
	    tfPositionY.setText("");
	    tfMass.setText("");
	    tfVelX.setText("");
	    tfVelY.setText("");
	    tfPositionX.requestFocus();
    }
}
