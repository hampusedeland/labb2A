import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;


public class test1 {
    private Car saab;
    Saab95 saab123 = new Saab95(4, Color.black,124, 1610);
    Volvo240 volvon = new Volvo240(4,Color.BLUE,130,1400);
    Scania lastbilen = new Scania(2,Color.CYAN,422,16000);
    CarTransport transport = new CarTransport(2,Color.gray,500,16000,4,1,1);
    OrdinaryWorkshop hampus = new OrdinaryWorkshop(15);

    @Before

    public void init(){
        saab = new Car(2, Color.red, 125,900);



    }
    @Test
    public void testIfWeCanLoadSpecifiCar(){
        hampus.addToloadedCars(volvon);
        hampus.addToloadedCars(saab123);
        hampus.addToloadedCars(transport);
        hampus.addToloadedCars(lastbilen);
        System.out.println(hampus.getNumberOfLoadedCars());
        System.out.println(hampus.getInfoSpecificCar(3));

    }

    @Test
    public void testIfwecanloadTruck(){
        transport.addLoadedCars(volvon);
        transport.addLoadedCars(saab123);
        transport.addLoadedCars(saab123);



    }
    @Test
    public void TestCanWeLoadSameCar() {
        CarTransport trans2 = new CarTransport(2, Color.BLUE, 900, 16000, 4, 1, 1);
        transport.addLoadedCars(saab123);
        trans2.addLoadedCars(saab123);
    }

    @Test
    public void testWorkshopCarBrand(){
        OrdinaryWorkshop<Car> workish = new OrdinaryWorkshop<>(6);
        workish.addToloadedCars(saab123);
        workish.addToloadedCars(volvon);
        workish.removeSpecificCar(1);
        OrdinaryWorkshop<Saab95> workshop = new OrdinaryWorkshop<>(4);
        workshop.addToloadedCars(saab123);
        assertTrue(workshop.removeFromLoadedCars()=="Saab95");
      //  workish.getInfoSpecificCar(1);
    }

    @Test
    public void testWorkshopCarAllBrands(){
        OrdinaryWorkshop<Car> workshop = new OrdinaryWorkshop<>(4);
        workshop.addToloadedCars(saab);
        workshop.addToloadedCars(saab123);
        workshop.addToloadedCars(volvon);
        assertTrue(workshop.getNumberOfLoadedCars()==3);
    }

    @Test
    public void checkCoordWhileMoving(){

        transport.addLoadedCars(volvon);
        transport.addLoadedCars(saab123);
        transport.setRampUp();
        transport.setCurrentSpeed(10);
        transport.move();
        transport.move();

        transport.removeFromloadedcars();
        assertTrue(transport.getY()>=volvon.getY());
    }
    @Test
    public void maxCars(){
        transport.setRampDown();
        for(int i=0; i<5;i++){
            transport.addLoadedCars(new Car(4,Color.BLUE,130,1200));
        }
        assertThrows(IllegalArgumentException.class, () -> transport.addLoadedCars(transport));


    }
    @Test
    public void canNotLoadTruck(){
        assertThrows(IllegalArgumentException.class, () -> transport.addLoadedCars(transport));

    }
    //Testar transport i metoden över
    @Test
    public void canWeLoadoff(){
        transport.setRampDown();
        transport.addLoadedCars(saab123);
        transport.addLoadedCars(volvon);
        transport.lastaAvAllaBilar();
        assertTrue(transport.getNrLoadedCars()==0);



    }

    //default värdet på x och y ifrån Car klassen där x=0,y=0
    @Test
    public void areCarsNotNearby(){
        transport.setY(50);
        transport.setX(20);
        transport.setRampDown();
        assertThrows(IllegalArgumentException.class, () -> transport.addLoadedCars(saab123));

    }


    @Test
    public void doesTruckInheritRightMethod(){
        lastbilen.setAngleTrBed(0);
        lastbilen.startEngine();
        lastbilen.setCurrentdirection("north");
        lastbilen.move();
        lastbilen.move();
        assertTrue(lastbilen.getY()>=0);


    }
    @Test
    public void addingCarstotransport(){
        transport.setRampDown();
        transport.addLoadedCars(saab123);
        transport.addLoadedCars(volvon);
        System.out.println(transport.getNrLoadedCars());
        assertTrue(transport.getNrLoadedCars()==2);
    }


    @Test//när den börjar röra sig
    public  void moveTruckWhenAngNotZero(){
        lastbilen.setAngleTrBed(50);
        assertThrows(IllegalArgumentException.class, ()-> lastbilen.move());
    }
    @Test//när den rör sig
    public  void changingTrBedAngWhileMoving(){
        lastbilen.setCurrentSpeed(5);
        assertThrows(IllegalArgumentException.class, ()-> lastbilen.setAngleTrBed(72));
    }
    @Test
    public void volvospeedfactor() {
        assertTrue(volvon.speedFactor()==volvon.getEnginePower()*0.01*1.25);
    }
    @Test
    public void turboOnsaab(){
        saab123.setTurboOn();
        assertEquals(saab123.turboOn,true);

    }
    @Test
    public void turboOfSaab(){
        saab123.setTurboOff();
        assertEquals(saab123.turboOn,false);
    }

    @Test
    public void testSpeedfactorsaabok(){
        double noTurbo = saab123.speedFactor();
        saab123.setTurboOn();
        double withTurbo = saab123.speedFactor();
        int vad = Double.compare(noTurbo,withTurbo);
        assertTrue(vad<0);
    }

    @Test
    public void testTurboOff(){
        saab.setTurboOn();
        double noTurbo = saab.speedFactor();
        saab.setTurboOff();
        double withTurbo = saab.speedFactor();
        int vad = Double.compare(noTurbo,withTurbo);
        assertTrue(vad==0);
    }


    //ideer för tester
    @Test
    public void testcolor(){
        saab.setColor(Color.BLACK);
        assertEquals(saab.getColor(),Color.black);
    }
    @Test
    public void testenginepowernegative(){
        saab.setEnginePower(-100);
        assertTrue(saab.getEnginePower()>=0);
    }
    @Test
    public void testDoors (){
        assertTrue(saab123.getNrDoors()==4);
    }
    @Test public void turnRightOk(){
        saab.setCurrentdirection("north");
        saab.turnRight();
        assertEquals(saab.getCurrentdirection(),"east");
    }
    @Test
    public void testSpeedNegative(){

        assertThrows(IllegalArgumentException.class, () -> saab.setCurrentSpeed(-50)); /// hur man testar throws
    }
    @Test
    public void turboOn(){
        saab123.setTurboOn();
        assertEquals(saab123.turboOn,true);
    }
    @Test
    public void testTurnLeft(){
        saab.setCurrentdirection("west");
        saab.turnLeft();
        assertTrue(saab.getCurrentdirection()=="south");
    }

    @Test
    public void testTurnRight(){
        //start con, starts north
        saab.setCurrentdirection("north");
        //three right turns should result in the direction being west
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();
        assertTrue(saab.getCurrentdirection()=="west");

    }

    @Test
    public void testMove() {
        saab.setCurrentSpeed(1);
        saab.setCurrentdirection("north");
        saab.setX(0);
        saab.setY(0);
        for (int i = 0; i <= 10; i++) {
            saab.move();
            saab.move();
            saab.move();
        }
        //this should result in the car moving 10 steps
        //The car being directed north, and starting in (0,0)
        // should result in the car ending up in (0,10)
        assertTrue(saab.getY() >= 9);

    }
    @Test
    public void testPosition() {
        saab.setX(0);
        saab.setY(0);

        assertTrue(saab.getX()==0 && saab.getY()==0);
    }

    @Test
    public void testSetCurrentdirectionOK(){
        saab.setCurrentdirection("west");
        saab.move();
        saab.turnLeft();
        saab.turnRight();

    }

    @Test
    public void testGasOK(){

        for(int i= 0 ; i<=200 ; i++) {
            saab.gas(0.9);
        }
        assertTrue(saab.getCurrentSpeed()<=saab.getEnginePower());

    }

    @Test
    public void testBreakOK(){
        saab.setCurrentSpeed(100);
        for(int i= 0 ; i<=200 ; i++) {
            saab.brake(0.9);
        }
        //för man kan breaka med 1 som max
        assertTrue(saab.getCurrentSpeed()<=1);

    }

    @Test
    public void testIncrementSpeedOK(){
        saab.setCurrentSpeed(1);
        saab.incrementSpeed(20);
        //vi får inte öka hastigheten med mer än 1, så därav ska hastigheten
        //vara oförändrad
        assertTrue(saab.getCurrentSpeed()>=0.9);

    }

    @Test
    public void testDecremetSpeedOK(){
        saab.setCurrentSpeed(1);
        saab.decrementSpeed(20);
        //vi får inte öka hastigheten med mer än 1, så därav ska hastigheten
        //vara oförändrad
        assertTrue(saab.getCurrentSpeed()==1);
    }

    @Test
    public void testStopEngineOK(){
        saab.setCurrentSpeed(20);
        saab.stopEngine();
        assertTrue(saab.getCurrentSpeed()==0);
    }

    @Test
    public void testStartEngineOK(){
        saab.setCurrentSpeed(0);
        saab.startEngine();
        assertTrue(saab.getCurrentSpeed()==0.1);

    }

}