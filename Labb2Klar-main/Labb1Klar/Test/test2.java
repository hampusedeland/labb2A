import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class test2 {

    private Car volvon;

    @Before
    public void init(){
        volvon = new Car(4, Color.black, 100,1600);
    }

    @Test
    public void testTurnLeft(){
        volvon.setCurrentdirection("west");
        volvon.turnLeft();
        assertTrue(volvon.getCurrentdirection()=="south");
    }


    @Test
    public void testTurnRight(){
        //start con, starts north
        volvon.setCurrentdirection("north");
        //three right turns should result in the direction being west
        volvon.turnRight();
        volvon.turnRight();
        volvon.turnRight();
        assertTrue(volvon.getCurrentdirection()=="west");

    }

    @Test
    public void testMove() {
        volvon.setCurrentSpeed(1);
        volvon.setCurrentdirection("north");
        volvon.setX(0);
        volvon.setY(0);
        for (int i = 0; i <= 10; i++) {
            volvon.move();
            volvon.move();
            volvon.move();
        }
        //this should result in the car moving 10 steps
        //The car being directed north, and starting in (0,0)
        // should result in the car ending up in (0,10)
        assertTrue(volvon.getY() >= 9);

    }
    @Test
    public void testPosition() {
        volvon.setX(0);
        volvon.setY(0);

        assertTrue(volvon.getX()==0 && volvon.getY()==0);
    }

    @Test
    public void testSetCurrentdirectionOK(){
        volvon.setCurrentdirection("west");
        volvon.move();
        volvon.turnLeft();
        volvon.turnRight();

    }

    @Test
    public void testGasOK(){

        for(int i= 0 ; i<=200 ; i++) {
            volvon.gas(0.9);
        }
        assertTrue(volvon.getCurrentSpeed()<=volvon.getEnginePower());

    }

    @Test
    public void testBreakOK(){
        volvon.setCurrentSpeed(100);
        for(int i= 0 ; i<=200 ; i++) {
            volvon.brake(0.9);
        }
        //för man kan breaka med 1 som max
        assertTrue(volvon.getCurrentSpeed()<=1);

    }



    @Test
    public void testIncrementSpeedOK(){
        volvon.setCurrentSpeed(1);
        volvon.incrementSpeed(20);
        //vi får inte öka hastigheten med mer än 1, så därav ska hastigheten
        //vara oförändrad
        assertTrue(volvon.getCurrentSpeed()>=0.9);

    }

    @Test
    public void testDecremetSpeedOK(){
        volvon.setCurrentSpeed(1);
        volvon.decrementSpeed(20);
        //vi får inte öka hastigheten med mer än 1, så därav ska hastigheten
        //vara oförändrad
        assertTrue(volvon.getCurrentSpeed()==1);
    }

    @Test
    public void testStopEngineOK(){
        volvon.setCurrentSpeed(20);
        volvon.stopEngine();
        assertTrue(volvon.getCurrentSpeed()==0);
    }

    @Test
    public void testStartEngineOK(){
        volvon.setCurrentSpeed(0);
        volvon.startEngine();
        assertTrue(volvon.getCurrentSpeed()==0.1);

    }
}