package main;

import java.util.ArrayList;

public class CalcThread extends Thread{
    
    ArrayList<MassObject> BodysList = new ArrayList<MassObject>();
    double time;
    double defaultScaling;
    double cGamma;
    boolean edited;

    @Override
    public void run() {
	ArrayList<MassObject> BodysTemp = new ArrayList<MassObject>();
	for(int m = 0; m<BodysList.size();m++){
	    MassObject tempMObj = new MassObject();
	    tempMObj.equalize(BodysList.get(m));
	    for(int i = 0; i<BodysList.size(); i++){		
		if(i!=m){
		    tempMObj.setForcex(tempMObj.getForcex() + getForce(BodysList.get(i), tempMObj).getX());
		    tempMObj.setForcey(tempMObj.getForcey() + getForce(BodysList.get(i), tempMObj).getY());
		}
	    }	
	    BodysTemp.add(tempMObj);
	    
	}
	for(int i = 0; i<BodysList.size(); i++){	//Evtl rundungsfehler durch casten nach INT!
	    BodysTemp.get(i).getAll();
	    BodysTemp.get(i).setX(BodysTemp.get(i).getX()
		    + BodysTemp.get(i).getVelx()*time/defaultScaling 
		    + 0.5*getAcceleration(BodysTemp.get(i)).getX()*time*time/defaultScaling);
	    
	    BodysTemp.get(i).setY(BodysTemp.get(i).getY() 
		    + BodysTemp.get(i).getVely()*time/defaultScaling 
		    + 0.5*getAcceleration(BodysTemp.get(i)).getY()*time*time/defaultScaling);
	 
	    BodysTemp.get(i).setVelx(BodysTemp.get(i).getVelx() + time*getAcceleration(BodysTemp.get(i)).getX());
	    BodysTemp.get(i).setVely(BodysTemp.get(i).getVely() + time*getAcceleration(BodysTemp.get(i)).getY());
	    BodysTemp.get(i).setForcex(0);
	    BodysTemp.get(i).setForcey(0);
	    BodysList.set(i, BodysTemp.get(i));
	}
	this.setEdit(true);
	
    }
    Vector2D getAcceleration(MassObject Obj){
	Vector2D temp = new Vector2D(0,0);
	temp.setX(Obj.getForcex()/Obj.getMass());
	temp.setY(Obj.getForcey()/Obj.getMass());
	return temp;
    }
    Vector2D getForce(MassObject source, MassObject Obj){
	Vector2D temp = new Vector2D(source.getX()-Obj.getX(),source.getY()-Obj.getY());//Ortsvektor
	double r = temp.length();
	if(r == 0){
	    r = 0.000000001;
	}
	return temp.product(cGamma).product(1/Math.pow(r, 3)).product(source.getMass()).product(Obj.getMass());
    }
    
    public void setTime(double t){ this.time=t;}
    
    public double getTime(){return this.time;}
    
    public void setDefScaling(double ds){this.defaultScaling = ds;}
    
    public double getDefScaling(){return this.defaultScaling;}
    
    public void setGamma(double cG){this.cGamma=cG;}
    
    public void setList(ArrayList<MassObject> List){
	this.BodysList.equals(List);
    }
    
    public MassObject getList(int i){
	return BodysList.get(i);
    }
    
    public ArrayList<MassObject> getList(){
	return this.BodysList;
    }
    
    public void setEdit(boolean b){
	this.edited = b;
    }
}
