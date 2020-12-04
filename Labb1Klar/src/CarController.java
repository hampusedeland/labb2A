import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController{ //satte C extends Car här
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>(); //satte C här //Kanske generisk lista
    //rara
    //methods:

    public static void main(String[] args) { //y-axeln är inverterad, så 0.0 i vänstra hörnet.
        // Instance of this class
        CarController cc = new CarController();
        Volvo240 volvo = new Volvo240(4, Color.BLUE,320,1600);
        Saab95 saab95 = new Saab95(2,Color.gray,400,1800);
        Scania scania = new Scania(2,Color.black,800,18000);
        Saab95 johannes = new Saab95(2,Color.white,400,1600);
        scania.setAngleTrBed(0);
        volvo.setCurrentdirection("east");
        saab95.setCurrentdirection("east");
        johannes.setCurrentdirection("east");
        scania.setCurrentdirection("east");
        saab95.setY(200);
        scania.setY(400);
        johannes.setY(600);
        cc.cars.add(volvo);
        cc.cars.add(saab95);
        cc.cars.add(scania);
        cc.cars.add(johannes);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc); //Beroende till CarviEW

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener { //Göra if satserna till en metod och anropa, för att göra det snyggare
        public void actionPerformed(ActionEvent e) {
            frame.drawPanel.getArrayList(cars);
            for (Car car : cars) { //nedansteående hade kunnat vara en metod
                if(car.getX()>=frame.getWidth()-100 && car.getCurrentdirection().contains("east")){      //FRÅGA hur vet vi hur stor bilden är, så den studsar på väggen
                    car.turnLeft();
                    car.turnLeft();
                }
                else if(car.getX()<0 && car.getCurrentdirection().contains("west")){
                    car.turnLeft();
                    car.turnLeft();
                }
                else if(car.getY()<0 && car.getCurrentdirection().contains("south")){
                    car.turnLeft();
                    car.turnLeft();
                }
                else if(car.getY()>=frame.getHeight()-300 && car.getCurrentdirection().contains("north")){
                    car.turnLeft();
                    car.turnLeft();
                }

                //car.move(); här var den först
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                //frame.drawPanel.moveit(x, y,car); //ändrar här
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint(); //beroende
                car.move();
               // frame.drawPanel.paintCar(car);
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }
    void liftBed(){
        for(Car car : cars){
            if(car.getHasLift()){
                if(car.getCurrentSpeed()==0) {
                    System.out.println("Weee raising my truck bed");
                    ((Liftable) car).setAngleTrBed(70);
                }
                else{
                    System.out.println("The car must stop, If you want to raise the truck bed");
                }

            }
        }
    }
    void setBedDown(){
        for(Car car : cars){
            if(car.getHasLift()){
                ((Liftable) car).setAngleTrBed(0);
            }
        }
    }

    void setTurboOn(){
        for(Car car : cars){ //Kanske en lista med turboable
            System.out.println("utanför");
            if (car.hasTurbo()) {
                System.out.println("innanför");
                ((Turboable) car).setTurboOn();
            }
        }
    }
    void setTurboOff(){
        for(Car car : cars){
            if (car.hasTurbo()) {
                ((Turboable) car).setTurboOff();
            }
              //  car.setTurboOff();
        }
    }
    void startCars(){
        for(Car car: cars){
            car.startEngine();
        }
    }

    void stopCars(){
        for(Car car: cars){
            car.stopEngine();
        }
    }
}
