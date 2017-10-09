package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.*;

public class DrawThread extends Thread{
    KreisZeichenobjekt circel;
    ArrayList<MassObject> List = new ArrayList<MassObject>();
    double defaultScaling;
    double scaling;
    JMyPaintPanel g = new JMyPaintPanel();
    //Graphics2D g2d = (Graphics2D) g.getGraphics();
    boolean edited;
    
    public void run(){
	if(edited){
	    for(int i = 0; i<List.size(); i++){
		circel = new KreisZeichenobjekt((int)(Math.round(List.get(i).getX())/defaultScaling*scaling),(int) (Math.round(List.get(i).getY()/defaultScaling*scaling)), 10, 10, Color.BLACK, 1.0f,true);
		g.addZeichenobjekt(circel);
		g.repaint();
	    }
	}
    }
    
    public void setScaling(double s){this.scaling = s;}
    public double getScaling(){return this.scaling;}
    
    public void setDefScaling(double ds){this.defaultScaling = ds;}
    public double getDefScaling(){return this.defaultScaling;}
    
    public void setList(ArrayList<MassObject> L){
	this.List.equals(L);
    }
    
    public void setPane(JMyPaintPanel g){
	this.g = g;
    }
    
    public void setEdit(boolean b){
	this.edited = b;
    }
}
