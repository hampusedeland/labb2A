import org.w3c.dom.events.EventException;

import java.awt.*;
import java.util.*;

public class CarTransport extends Scania {

    /**
     * En konstruktor för Scania biltransport generellt med undernämnda parametrar
     *
     * @param nrDoors
     * @param color
     * @param enginePower
     */

    public CarTransport(int nrDoors, Color color, double enginePower, int weight, int maxCars, int x, int y) {
        super(nrDoors, color, enginePower, weight);
        //settar bilen y-värden bangar hårdkodningen
        setX(x);
        setY(y);
        this.maxCars = maxCars;
        modelName= "CarHauler Extreme 720";
        setCurrentdirection("north");
    }
    private Integer maxCars;

    /** Gör en lista med lastade bilar som bara får vara ett objekt av typen "Car"
     *
     */
    private ArrayList<Car> loadedcars = new ArrayList<>();

    /** Metod för att hämta hur många bilar som är lastade
     *
     * @return antalet lastade bilar
     */
    public Integer getNrLoadedCars() {
        return loadedcars.size();
    }
    //alla extendar car så getX och getY finns i Car klassen

    /** För att lasta bilar måste rampen vara nere och enligt uppgift: 1. Bilen som ska lastas vara inom behörigt avstånd
     * 2. För stora bilar får inte lastas. 3. Samma bil kan inte lastas två gånger och rampen måste vara nere
     *
     * @param bil
     */
    public void addLoadedCars(Car bil){
        setRampDown();
        if(Math.abs(getX()-bil.getX())<=3 && Math.abs(getY()-bil.getY())<=3) {//bilen rimligt nära biltransporten
            if(bil.getWeight()<=3000) { //kanske vi ska hitta ett sätt att inte ladda på en cartransport på en cartransport
                if(!loadedcars.contains(bil) && getAngleTrBed() == 70 && !bil.isLoaded()) {//men problemet är då att då kan vi inte lasta på någon långtradare
                    //rampen måste vara nere

                    loadedcars.add(bil);
                    bil.setLoaded(true);
                    bil.setX(this.getX());
                    bil.setY(this.getY());
                }
                else {
                    throw new IllegalArgumentException("The car you're trying to load is already loaded");
                }
            }
            else{
                throw new IllegalFormatFlagsException("Make sure you are not loading a truck");
            }
        }
        else{
            throw new IllegalArgumentException("Make sure you are nearby the car you're trying to load");
        }
    }

    public String toString(int plats)  {
        if(loadedcars.get(plats)==null){
            throw new RuntimeException("Platsen är tom");

        }
        return  loadedcars.get(plats).modelName + " är lastad på plats nummer: " + plats;
    }

    /** Används vid avlastning av biltransporten.
     * Kan bara lastas av när rampen är nere.
     *
     */
    public void removeFromloadedcars(){
        if(getAngleTrBed() == 70) {
            if(loadedcars.size()>0){
                Car furthestOutCar = loadedcars.get(loadedcars.size()-1);
                furthestOutCar.setX(this.getX()+loadedcars.size()-1);
                loadedcars.remove(loadedcars.size()-1);
                furthestOutCar.setLoaded(false);
            }
        }
    }

    /** Vill alltid lasta av bilen längst ut vid avlastning
     */

    public void lastaAvAllaBilar(){
        for (int i = loadedcars.size()-1; i >-1; i-- ){
            removeFromloadedcars();
        }
    }


    public void setRampUp() {
        setAngleTrBed(0);
    }

    public void setRampDown() {
        setAngleTrBed(70);
    }

    /** Move metoden för biltransporten, vi anropar superklassen Cars move metod. För varje car i loadedcars betyder att den går igenom alla poå flaket.
     * och sätter den efter varje move, bilarnas koordinater till samma som cartransports.
     */
//scania är super av cartransport, scania har funktionerna att man inte
    //får flytta om flaket är uppe!
    //this är i det här fallet transport bilen
    @Override
    public void move(){
        super.move();

        for(Car car : loadedcars){
            car.setX(this.getX());
            car.setY(this.getY());
        }
    }
}