package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JComponent;

public class FirstPaintComponent extends JComponent {
    private Shape shape;
    private Color c;
    
    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	if(shape != null && c!=null){
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.setColor(c);
	    g2d.draw(shape);
	}
    }
    
    public void setColor(Color c) {
	this.c = c;
    }
    
    public void setShape(Shape shape){
	this.shape = shape;
    }
}
