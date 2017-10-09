package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class KreisZeichenobjekt extends Zeichenobjekt {
    private boolean gefuellt;
    
    KreisZeichenobjekt(int x1, int y1, int x2, int y2, Color color, float linienbreite, boolean gefuellt) {
	super(x1, y1, x2, y2, color, linienbreite);
	this.gefuellt = gefuellt;
    }
    public boolean getGefuellt() {
	return gefuellt;
    }
    public void setGefuellt(boolean istGefuellt){
	this.gefuellt = istGefuellt;
    }
    @Override
    void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D)g;
	Ellipse2D.Float kreis = new Ellipse2D.Float(getX1(), getY1(), getX2(), getX2());
	if(getGefuellt()) {
	    g2d.fill(kreis);
	} else {
	    g2d.draw(kreis);
	}
    }

}
