package main;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

public class JMyPaintPanel extends JPanel {
    private ArrayList<Zeichenobjekt> figuren;

    public JMyPaintPanel() {
	// TODO Auto-generated constructor stub
	figuren = new ArrayList<Zeichenobjekt>();
    }

    public JMyPaintPanel(LayoutManager arg0) {
	super(arg0);
	figuren = new ArrayList<Zeichenobjekt>();
	// TODO Auto-generated constructor stub
    }

    public JMyPaintPanel(boolean arg0) {
	super(arg0);
	figuren = new ArrayList<Zeichenobjekt>();
	// TODO Auto-generated constructor stub
    }

    public JMyPaintPanel(LayoutManager arg0, boolean arg1) {
	super(arg0, arg1);
	figuren = new ArrayList<Zeichenobjekt>();
	// TODO Auto-generated constructor stub
    }
    
    public void addZeichenobjekt(Zeichenobjekt obj) {
	figuren.add(obj);
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d=(Graphics2D)g;
	for(Zeichenobjekt zo: figuren) {
	    g2d.setColor(zo.getColor());
	    BasicStroke stil = new BasicStroke(zo.getLinienbreite(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	    g2d.setStroke(stil);
	    zo.paint(g2d);
	}
    }

}
