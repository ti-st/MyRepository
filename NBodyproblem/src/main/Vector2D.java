package main;

public class Vector2D {
    private double x;
    private double y;
    
    Vector2D(double lx, double ly){
	this.x = lx;
	this.y = ly;
    }
    
    double getX(){
	return x;
    }
    
    void setX(double lx){
	x = lx;
    }
    
    double getY(){
	return y;
    }
    
    void setY(double ly){
	y = ly;
    }
    
    void sub(Vector2D v2){
	this.setX(this.getX()-v2.getX());
	this.setY(this.getY()-v2.getY());
    }
    
    void add(Vector2D v2){
	x = x + v2.getX();
	y = y + v2.getY();
    }
    
    double length(){
	return Math.sqrt(x*x+y*y);
    }
    
    void normalize(){
	double l = this.length();
	this.setX(this.getX()/l);
	this.setY(this.getY()/l);
    }
    
    double skalarproduct(Vector2D v1, Vector2D v2){
	return (v1.getX()*v2.getX()+v1.getY()*v2.getY());
    }
    
    Vector2D product(double d){
	this.setX(this.getX()*d);
	this.setY(this.getY()*d);
	return this;
    }
    
    double angle (Vector2D v1, Vector2D v2){
	return (Math.acos(skalarproduct(v1, v2)/(v1.length()*v2.length())));
    }
}
