package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class RechteckZeichenobjekt extends Zeichenobjekt {
    private boolean istGefuellt;
    
    RechteckZeichenobjekt(int x1, int y1, int x2, int y2, Color color, float linienbreite, boolean istGefuellt) {
	super(x1, y1, x2, y2, color, linienbreite);
	this.istGefuellt = istGefuellt;
    }
    public boolean getGefuellt() {
	return istGefuellt;
    }
    public void setGefuellt(boolean istGefuellt) {
	this.istGefuellt = istGefuellt;
    }
    @Override
    void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D)g;
	Rectangle2D.Float rechteck = new Rectangle2D.Float(getX1(), getY1(), getX2(), getY2());
	if (getGefuellt()) {
	    g2d.fill(rechteck);
	} else {
	    g2d.draw(rechteck);
	}
    }
}
