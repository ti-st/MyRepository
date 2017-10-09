package main;

public class MassObject {
    private double mass;
    private double velx;
    private double vely;
    private double x;
    private double y;
    private double forcex;
    private double forcey;
    
    MassObject(){
	this.mass = 0;
	this.velx = 0;
	this.vely = 0;
	this.x = 0;
	this.y = 0;
	this.forcex = 0;
	this.forcey = 0;
    }
        
    MassObject(double mass, double velx, double vely, int x, int y) {
	this.mass = mass;
	this.velx = velx;
	this.vely = vely;
	this.x = x;
	this.y = y;
	this.forcex = 0;
	this.forcey = 0;
    }
    
    double getX(){
	return x;
    }
    
    void setX(double newX){
	x = newX;
    }
    
    double getY(){
	return y;
    }
    
    void setY(double newY){
	y = newY;
    }
    
    double getMass(){
	return mass;
    }
    
    void setMass(double lmass){
	mass = lmass;
    }
    
    double getVelx(){
	return velx;
    }
    
    void setVelx(double lVelx){
	velx = lVelx;
    }
    
    double getVely(){
	return vely;
    }
    
    void setVely(double lVely){
	vely = lVely;
    }
    
    double getForcex(){
	return forcex;
    }
    
    void setForcex(double force){
	forcex = force;
    }
    
    double getForcey(){
	return forcey;
    }
    
    void setForcey(double force){
	forcey = force;
    }
    
    void equalize(MassObject Obj){
	this.x = Obj.x;
	this.y = Obj.y;
	this.velx = Obj.velx;
	this.vely = Obj.vely;
	this.mass = Obj.mass;
    }
    
    void getAll(){
	System.out.println("Koords: " + "(" + x + "," + y + ")" + ", Vel: " + "(" + velx + "," + vely + ")" + ", Force: " + "(" + forcex + "," + forcey + ")");
    }
    
    void clear(){
	x = 0;
	y = 0;
	mass = 0;
	velx = 0;
	vely = 0;
	forcex = 0;
	forcey = 0;
    }
}
