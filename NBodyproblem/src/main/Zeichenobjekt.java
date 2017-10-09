package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Zeichenobjekt {
    
    protected int x1, x2, y1, y2;
    protected Color color;
    protected float linienbreite;
    
    Zeichenobjekt(int x1, int y1, int x2, int y2, Color color, float linienbreite){
	this.x1 = x1;
	this.x2 = x2;
	this.y1 = y1;
	this.y2 = y2;
	this.color = color;
	this.linienbreite = linienbreite;
    }
    
    public int getX1() {return x1;}
    public void setX1(int x1) {this.x1 = x1;}
    public int getX2() {return x2;}
    public void setX2(int x2) {this.x2 = x2;}
    public int getY1() {return y1;}
    public void setY1(int y1) {this.y1 = y1;}
    public int getY2() {return y2;}
    public void setY2(int y2) {this.y2 = y2;}
    public Color getColor(){return color;}
    public void setColor(Color c){this.color = c;}
    public float getLinienbreite(){return linienbreite;}
    public void setLinienbreite(float lb){this.linienbreite = lb;}
    abstract void paint(Graphics g);
}