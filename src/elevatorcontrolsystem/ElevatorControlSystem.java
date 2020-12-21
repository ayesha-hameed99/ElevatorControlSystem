/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorcontrolsystem;
import java.util.Scanner;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 *
 * @author asima
 */
class calculateWeight{
    Scanner input=new Scanner(System.in);
    double weight;
    int i=0;
    int p;
    void includePerson(){
       
    System.out.println("Enter total number of people: ");
    p=input.nextInt();
    if(p>10 || p<0){
        System.out.println("Not more than 10 people are allowed");
    }
    else{
    System.out.println("Wait till your weight is calculated");}
    }
    
boolean Weight(){
double sum=0;
    for(int i=1; i<=p;i++){
System.out.println("Enter weight of "+i +" person");
weight=input.nextDouble();

    sum+=weight;
    }
if(sum<=600){
System.out.println("Elevator can start");
return true;
}else   
{   
Beep();
System.out.println("Overweight elevatror");
 Beep();
 return false;
}

}
 void Beep(){
     Toolkit.getDefaultToolkit().beep();}
}


 class Button {
    Boolean open;
    Boolean close;
    public  boolean open(){
        System.out.println("successfully!The door is opened");
        return true;
    }
     public  boolean close(){
        System.out.println("successfully!The door is closed");
                 return   true;           
    }
}
 class EmergencyCall {
    

    public EmergencyCall (){
        
    }
   
    public void Notify()
    {
       System.out.println("Please help ,Something went wrong");
    }
    
      public void call()
    {
         System.out.println("Connecting to  control room ...");
    }
}

class level{
    Scanner input=new Scanner(System.in);
    final int maxP = 20;
	final int maxF = 20;
	final int minP = 1;
	final int minF = 1;
	
	int curF = 1;
	int desF = 0;
	int passFloor = 0;
	int numOfPass = 0;
	  ArrayList<Integer> listOfFloors;
   
	int[] destination_lists = new int[maxF];
    void displayLevel(){
        for(int i=1; i<=5;i++){
    System.out.println("Level "+i);}
    }
    int askPassengerFloor(int id) {
		boolean isValidEntry = false;
		int floor = 0;
		while(!isValidEntry) {
			System.out.println("Passenger #"+ (id+1) + " enter your floor: ");
			floor = input.nextInt();
                        
			if(floor < minF || floor > maxF) {
				System.out.println("Error. You have entered out of range floor. Valid [1-20]");
			} else if(floor == curF) {
				System.out.println("You are already in the " + curF + "F.");
			} else {
				destination_lists[floor-1]++;
				isValidEntry = true;
			}
		}
		return floor;
	}
    void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {}
	}
    void MoveUp(){
      
        { System.out.println("Elevator is going up");
        delay(1000);}
       // System.out.println("Level "+level+" arrived");
         Beep();
        // delay(600);
    }
    
     void MoveDown(){
    System.out.println("Elevator is going down");
     //  LocalTime lt= LocalTime.now();
       // long millis=System.currentTimeMillis();
    //    System.out.println(floor);
        delay(1000);
     //   System.out.println("Level "+level+" arrived");
         Beep();
       //  delay(600);
    }
    void notify(int num){
    System.out.println("");
    }
    void Beep(){
     Toolkit.getDefaultToolkit().beep();}
    
    void floorList(int p){
    for(int a = 0; a < p; a ++) {
         listOfFloors = new ArrayList<>();
				int floor = askPassengerFloor(a);
				if(!listOfFloors.contains(floor)) listOfFloors.add(floor);
			}
    }
    
    	int findShortest() {
        
		int shortest = Math.abs(curF - listOfFloors.get(0));
		int id = 0;
		for(int a = 1; a < listOfFloors.size(); a ++) {
			if(shortest > Math.abs(curF - listOfFloors.get(a))) {
				shortest = Math.abs(curF - listOfFloors.get(a));
				id = a;
			}
		}
		shortest = listOfFloors.get(id);
		listOfFloors.set(id, 100);
		return shortest;
	}
        void initialize_elevator() {
		for(int a = 0; a < listOfFloors.size(); a ++) {
			int shortest = findShortest();
			System.out.println("Next destination: "+ shortest + "F Passenger amount (" + destination_lists[shortest-1] + ")");
			delay(1500);
			while(curF < shortest) {
				MoveUp();
			}
			while(curF > shortest) {
				MoveDown();
			}
			while(destination_lists[shortest-1] > 0) {
				System.out.println("Unloading passenger (" + destination_lists[shortest-1]-- + ") at " + curF + "F");
				delay(1500);
			}
		}
		
	}
}


interface user{
    void Beep();
    void MoveUp(int l);
    void MoveDown();
    void Weight();
    void displayLevel();
}

public class ElevatorControlSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
      calculateWeight w=new calculateWeight();
      Button b=new Button();
      EmergencyCall EC=new EmergencyCall();
     level l=new level();
   
     b.open();  
     w.includePerson();
    l.delay(600);
    if(w.Weight()){
        b.close();
        l.displayLevel();
        	//l.askPassengerFloor(w.p);
                l.floorList(w.p);
                l.initialize_elevator();
			
       
        System.out.println("Enter current floor number");
        int f=input.nextInt();
     System.out.println("Enter desired level number: ");
     int n=input.nextInt();
     if(f<n){
  // l.MoveUp(f,n);}
   //  else{
   //  l.MoveDown(f,n);}
     
    } else{
    
    
    } 
    // w.Weight();
    // b.close();
     
  //  l.MoveDown(3);
     
    }
    
}
}